package com.example.BTL.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BTL.entity.Book;

import com.example.BTL.entity.Rating;
import com.example.BTL.entity.Users;
import com.example.BTL.repository.RatingRepository;

@Service
public class RatingService {
	@Autowired
	RatingRepository ratingRepository;
	@Autowired
	BookService bookService;
	
	public void saveRating(Book book, Users user, int ratingValue) {
		Rating rate = new Rating();
		rate.setBook(book);
		rate.setUser(user);
		rate.setRatingValue(ratingValue);
		ratingRepository.save(rate);
	}
	
	 public List<Rating> getRatingByBookId(int id) {
		   
		    Book book = bookService.getBookById(id);
		    return book.getRating();
		}
	
}
