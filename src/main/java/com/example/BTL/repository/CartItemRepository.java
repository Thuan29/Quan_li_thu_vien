package com.example.BTL.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.BTL.entity.Cart;
import com.example.BTL.entity.Users;

@Repository
public interface  CartItemRepository  extends JpaRepository<Cart, Integer> {
	 List<Cart> findByUser(Users user);
	 void deleteByUser_Username(String username);
}
