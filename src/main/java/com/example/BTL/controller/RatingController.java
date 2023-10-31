package com.example.BTL.controller;


import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.BTL.entity.Book;
import com.example.BTL.entity.Rating;
import com.example.BTL.entity.Users;
import com.example.BTL.service.BookService;
import com.example.BTL.service.RatingService;
import com.example.BTL.service.UserService;

@Controller
public class RatingController {
	@Autowired
	RatingService ratingService;
	@Autowired
	UserService userService;
	@Autowired
	BookService bookService;
	
	@PostMapping("/book/{id}/rating")
	public String saveRating(@PathVariable("id") int id, @RequestParam(required = false, name="rate") int ratingValue, HttpSession session) {
	  
		String username = (String) session.getAttribute("UserName"); 
	    Users user = userService.getUserByUsername(username);

	  
	    Book book = bookService.getBookById(id);

	  
	    ratingService.saveRating(book, user, ratingValue);

	    return "redirect:/bookDetail/{id}";
	}
	  @GetMapping("/book/{id}/ratings")
	    public String showRatingForm(@PathVariable("id") int bookid, Model model) {
		  	List<Rating> ratings = ratingService.getRatingByBookId(bookid);
	        model.addAttribute("ratings", ratings);
	        return "rating_form";
	    }

}
