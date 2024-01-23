package com.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
		System.out.println("create Product Repository");
		this.userRepository = userRepository;
	}
	
	@GetMapping
	public ResponseEntity getAllUsers() {
		System.out.println("getAllUsers");
		return ResponseEntity.ok(this.userRepository.findAll());
	}
}
