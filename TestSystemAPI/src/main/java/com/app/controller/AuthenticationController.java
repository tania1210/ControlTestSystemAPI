package com.app.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.AuthenticationService;

import exceptions.IncorrectPasswordException;
import exceptions.UserNotFoundException;
import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthenticationController {

	private AuthenticationService authenticationService;

	@PostMapping("login")
	public ResponseEntity<String> authentication(@RequestParam("email") String email, @RequestParam("password") String password) {
		ResponseEntity<String> message = null;
		try {
			message = authenticationService.login(email, password);
		}catch(UserNotFoundException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
		}catch(IncorrectPasswordException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
		}

		return message;
	}
}
