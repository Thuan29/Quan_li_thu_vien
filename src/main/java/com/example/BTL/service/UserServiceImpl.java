package com.example.BTL.service;

import java.util.List;
import java.util.Optional;



import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.example.BTL.entity.Users;
import com.example.BTL.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public Optional<Users> findById(Integer id) {
		
		return userRepository.findById(id);
	}
	
	@Override
	public Optional<Users> findByUsername(String username) {
		
		return Optional.ofNullable(userRepository.findByUsername(username));
	}
	
	public List<Users> getAllUser(){
		return userRepository.findAll();
	}
	
	@Override
	public Users creatUser(Users user) {
		user.setRole(0);
		return userRepository.save(user);
	}
	
	@Override
	public Users save(Users user) {
		return userRepository.save(user);
	}
	
	@Override 
	public boolean checkUserName(String username) {
		return userRepository.existsByUsername(username);
	}

	@Override
	public boolean checkLogin(String username, String password) {
		Optional<Users> optionalUser = findByUsername(username);
		if(optionalUser.isPresent() && optionalUser.get().getPassword().equals(password)) {
			return true;
		}
		return false;
	}

	@Override
	public Optional<Users> findByRole(Integer role) {
		return userRepository.findById(role);
	}

	@Override
	public Users getUserByUsername(String username) {
		  return userRepository.findByUsername(username);
	}
	@Override
	public void deleteUserById(int id) {
		userRepository.deleteById(id);
	}

	@Override
	public Users getUserById(int id) {
		return userRepository.findById(id).get();
	}
	
	
	
	
	
}
