package com.example.testsystemapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.testsystemapi.model.User;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class MainController {

	@GetMapping("")
	public String viewHomePage() {
		return "Home page";
	}
	
	@PostMapping("/login")
	public String createUser(@RequestBody User user) {
		System.out.println(user);
		return "user is creating!";
	}
}
