package com.example.BTL.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.BTL.entity.Book;
import com.example.BTL.entity.Users;
import com.example.BTL.service.UserService;


@Controller
public class UserController {
	@Autowired
	UserService userService;
	
	@GetMapping("/userHome/acc")
	public ModelAndView userAcc(HttpSession session) {
		if(session.getAttribute("UserName") != null) {
			List<Users> list = userService.getAllUser();
			return new ModelAndView("accList","user", list);
			}else {
				return new ModelAndView("redirect:/home");
			}
		
	}
	@RequestMapping("/deleteUser/{id}" )
	public String deleteBook(@PathVariable("id") int id) {
		
		userService.deleteUserById(id);
		return"redirect:/userHome/acc";
	}
	@RequestMapping("/editUser/{id}")
	public String editBook(@PathVariable("id") int id, Model model) {
	    if (id == -1) {    
	        model.addAttribute("user", new Users());
	    } else {
	       Users user = userService.getUserById(id);
	        model.addAttribute("user", user);
	    }
	    return "accEdit";
	}
	
	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute Users u, HttpSession session) {
		
		boolean f = userService.checkUserName(u.getUsername());
		if(f) {
			session.setAttribute("msg", "Tên người dùng đã tồn tại");
		}else {
			Users user = userService.save(u);
			if(user != null) {
				session.setAttribute("msg", "Đăng kí thành công");
			}else {
				session.setAttribute("msg", "Xuất hiện lỗi ở server");
			}
		}
		return "redirect:/userHome/acc";
	}
	
}
