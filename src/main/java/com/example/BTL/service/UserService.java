package com.example.BTL.service;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.BTL.entity.Book;
import com.example.BTL.entity.Users;

@Service
public interface UserService {
	
	public Users creatUser(Users user);
	public Users save(Users user);
	public boolean checkUserName(String username);
	
	public Optional<Users> findById(Integer id);
	public Optional<Users> findByRole(Integer role);
	public Optional<Users> findByUsername(String username);
	boolean checkLogin(String username, String password);
	public Users getUserByUsername(String username);
	public List<Users> getAllUser();
	public void deleteUserById(int id);
	public Users getUserById(int id);
}
