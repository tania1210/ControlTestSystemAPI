package com.app.controller;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.User;
import com.app.repository.UserRepository;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

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
	
	@GetMapping("/{id}")
	@Operation(summary = "info search to id")
	public User getById(@Parameter(description = "only one to one")	@PathVariable("id") Long id) {
		System.out.println("---------getById----- id = " + id);
		Optional<User> optionalUser = userRepository.findById(id);
		System.out.println("---- result:" + optionalUser);
		return optionalUser.orElse(null);
		
	}
}
