package com.example.BTL.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.BTL.entity.Book;
import com.example.BTL.entity.Cart;
import com.example.BTL.entity.Users;
import com.example.BTL.repository.CartItemRepository;

@Service
public class CartService {
	
	
	@Autowired
	private CartItemRepository cartItemRepository;
	@Autowired
	private UserService userService;
	
	
	 public void addToCart(Book book, Users user, int quantity) {
	        Cart cartItem = new Cart();
	        cartItem.setBook(book);
	        cartItem.setUser(user);
	        cartItem.setQuantity(quantity);
	        cartItemRepository.save(cartItem);
	    }
	 
	 public List<Cart> getCartItems(String username) {
		    Users user = userService.getUserByUsername(username);
		    return user.getCartItems();
		}
	 
	  public void deleteByCartId(int id) {
		  
	        cartItemRepository.deleteById(id);
	    }

	
	
}
