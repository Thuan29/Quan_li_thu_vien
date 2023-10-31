//package com.example.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//import com.example.BTL.entity.Users;
//import com.example.BTL.repository.UserRepository;
//
//
//@Service
//public class UserDetailsServiceImpl implements UserDetailsService{
//
//	
//	@Autowired
//	private UserRepository userRepo;
//	
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		
//		Users user = userRepo.findByUsername(username);
//		
//		if(user != null) {
//			return new CustomUserDetail(user);
//		}
//		
//		throw new UsernameNotFoundException("user not availble");
//	}
//	
//}
