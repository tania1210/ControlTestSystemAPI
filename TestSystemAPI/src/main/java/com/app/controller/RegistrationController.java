package com.app.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.RegistrationService;

import exceptions.UserAlreadyExistsException;
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
		ResponseEntity<String> message = null;
		
		try {
			message = registrationService.registration(firstName, lastName, email, password);
			
		}catch(UserAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("User with email " + email + " already exists");
		}
		return message;
	    
	}
	


}
