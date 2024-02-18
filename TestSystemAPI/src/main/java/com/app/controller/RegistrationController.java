package com.app.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.RegistrationService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class RegistrationController {
	
	private RegistrationService registrationService;

	
	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}
	
	@PostMapping("/registration")
	public ResponseEntity<String> createUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName,
												@RequestParam("email") String email, @RequestParam("password") String password) {
		if(registrationService.registration(firstName, lastName, email, password)) {
			return ResponseEntity.ok("User registered successfully!");
		}else {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("email is already registered");
		}
	    
	}
	


}
