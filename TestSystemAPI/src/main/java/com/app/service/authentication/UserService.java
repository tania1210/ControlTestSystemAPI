package com.app.service.authentication;

import com.app.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.repository.UserRepository;

import java.util.Collections;


public class UserService {

	private final static String USER_NOT_FOUND_MSG = "user with email %s not found";
	private UserRepository userRepository;


	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
		System.out.println("UserService initialized with UserRepository: " + userRepository);
	}
}
