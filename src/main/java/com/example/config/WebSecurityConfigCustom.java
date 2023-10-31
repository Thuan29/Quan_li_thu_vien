//package com.example.config;
//
//
//
//
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.annotation.Order;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//
//
//
//
//
//@Configuration
//@EnableWebSecurity
//@Order(1000)
//public class WebSecurityConfigCustom extends WebSecurityConfigurerAdapter { 
//
//	@Bean
//	public UserDetailsService getUserDetailsServices() {
//		return new UserDetailsServiceImpl();
//	}
//	@Bean
//	public BCryptPasswordEncoder passwordEncoder()
//	{
//	    return new BCryptPasswordEncoder();
//	}
//	
//	
//	@Bean
//	public DaoAuthenticationProvider getDaoAuthenticationProvider() {
//		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
//		daoAuthenticationProvider.setUserDetailsService(getUserDetailsServices());
//		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
//		return daoAuthenticationProvider;
//	}
//	
//	
//	@Override
//	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//		auth.authenticationProvider(getDaoAuthenticationProvider());
//	}
//
//
//
//	@Override
//	protected void configure(HttpSecurity http) throws Exception { 
//		  http
//      	.authorizeRequests()
//          	.antMatchers("/home/**").permitAll()
//          	.anyRequest().authenticated()
//          	.and()
//      	.formLogin()
//          	.loginPage("/signin")
//          	.permitAll()
//          	.and()
//      	.logout()
//          	.permitAll();
//		}
//	}
