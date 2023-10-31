package com.example.BTL.controller;




import java.util.*;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.BTL.entity.Book;
import com.example.BTL.entity.Cart;
import com.example.BTL.entity.Users;
import com.example.BTL.service.BookService;
import com.example.BTL.service.CartService;
import com.example.BTL.service.UserService;

import javax.servlet.http.HttpSession;






@Controller

public class BookController {
	
	@Autowired
	private BookService service;
	@Autowired
	private UserService userService;
	
	@Autowired
	private CartService cartService;
	
	
	
	@GetMapping("/home")
	public String home() {
		return "home";
	}
	@GetMapping("/book_register")
	public String bookRegister(HttpSession session) {
		if(session.getAttribute("UserName") != null){
		return "redirect:/userHome/available_books";
		}else {
			return"home";
		}
		
	}
	
	@GetMapping("/userHome/books")
	public ModelAndView book(HttpSession session) {
		if(session.getAttribute("UserName") != null) {
			List<Book> list = service.getAllBooks();
			return new ModelAndView("books", "book", list);
			}else {
				return new ModelAndView("redirect:/home");
			}
	}
	
	
	@GetMapping("/userHome/available_books")
	public ModelAndView getAllbook(HttpSession session) {

		if(session.getAttribute("UserName") != null) {
		List<Book> list = service.getAllBooks();
		return new ModelAndView("bookList", "book", list);
		}else {
			return new ModelAndView("redirect:/home");
		}
	}
	
	
	
	@PostMapping("/save")
	public String addBook(@ModelAttribute Book b, HttpSession session, 
						@RequestParam(required = false, name = "file") MultipartFile file)  {
	    boolean f = service.checkTitleAndAuthor(b.getTitle(), b.getAuthor());
	    if (f) {
	        session.setAttribute("msg", "tiêu đề hoặc tên tác giả bị trùng");
	        return "redirect:/editBook/-1";
	    } else {
	        
	        service.save(b, file);
	        return "redirect:/userHome/available_books";
	    }
	}


	
	@GetMapping("/login")
	public String login() {
		return "login";
	}
	@GetMapping("/register")
	public String register() {
		return "register";
	}
	
	@GetMapping("/userHome")
	public String userHome(HttpSession session) {
		if(session.getAttribute("UserName") != null) {
		return "user_home";
		}else {
			return "login";
		}
		
	}
	
	@PostMapping("/createUser")
	public String createUser(@ModelAttribute Users u, HttpSession session) {
		
		boolean f = userService.checkUserName(u.getUsername());
		if(f) {
			session.setAttribute("msg", "Tên người dùng đã tồn tại");
		}else {
			Users user = userService.creatUser(u);
			if(user != null) {
				session.setAttribute("msg", "Đăng kí thành công");
			}else {
				session.setAttribute("msg", "Xuất hiện lỗi ở server");
			}
		}
		return "redirect:/register";
	}
	
	
	@PostMapping("/login")
	public String checkLogin(@RequestParam(required = false, name = "username" ) String username,
							@RequestParam(required = false, name = "password" ) String password,
							HttpSession session) {
		
		if(userService.checkLogin(username, password)) {
			Users user = userService.getUserByUsername(username);
			session.setAttribute("user", user);
			
			session.setAttribute("UserName", username);
					 return "redirect:/userHome";
					
		}else {
			session.setAttribute("msg", "Tên người hoặc mật khẩu không tồn tại");
		}
		
		return "login";
	}
	
	
	
	@GetMapping("/logout")
	public String logout(HttpSession http) {
		http.removeAttribute("UserName");
		return "redirect:/home";
	}
	
	
	@RequestMapping("/editBook/{id}")
	public String editBook(@PathVariable("id") int id, Model model) {
	    if (id == -1) {    
	        model.addAttribute("book", new Book());
	    } else {
	        Book b = service.getBookById(id);
	        model.addAttribute("book", b);
	    }
	    return "bookEdit";
	}
	
	@RequestMapping("/deleteBook/{id}" )
	public String deleteBook(@PathVariable("id") int id) {
		
		service.deleteById(id);
		return"redirect:/userHome/available_books";
	}
	
	 @GetMapping("/bookDetail/{id}")
	    public String showBookDetail(@PathVariable("id") int id, Model model) {
	        Book book = service.getBookById(id);
	        model.addAttribute("book", book);
	        return "book_detail";
	    }
	 
	 @GetMapping("/add/{id}")
	 public String addToCart(@PathVariable("id") int id, HttpSession session) {
	     Book book = service.getBookById(id);
	     String username = (String) session.getAttribute("UserName"); 
	     
	     Users user = userService.getUserByUsername(username);
	     
	     cartService.addToCart(book, user, 1); 
	     
	     return "redirect:/cart";
	 }
	 
	 @GetMapping("/cart")
	 public String showCart(Model model, HttpSession session) {
	     String username = (String) session.getAttribute("UserName");
	     List<Cart> cartItems = cartService.getCartItems(username);
	     model.addAttribute("cartItems", cartItems);
	     return "cart";
	 }
	 
	 @GetMapping("/deleteCart/{id}")
	 public String deleteCartItems(@PathVariable("id") int id) {
	    
	     cartService.deleteByCartId(id);
	     return "redirect:/cart";
	 }

	 
	 
}
