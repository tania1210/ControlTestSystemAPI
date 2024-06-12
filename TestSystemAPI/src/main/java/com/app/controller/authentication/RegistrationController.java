package com.app.controller.authentication;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.authentication.RegistrationService;

import exceptions.UserAlreadyExistsException;
import lombok.AllArgsConstructor;

@RestController("api/v1/signUp")
@AllArgsConstructor
public class RegistrationController {
	
	private RegistrationService registrationService;
	
	@PostMapping
	public ResponseEntity<String> createUser(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName, @RequestParam("surName") String surName,
												@RequestParam("email") String email, @RequestParam("password") String password) {
		ResponseEntity<String> message = null;
		
		try {
			message = registrationService.registration(firstName, lastName, surName, email, password);
			
		}catch(UserAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("User with email " + email + " already exists");
		}
		return message;
	    
	}
	


}
