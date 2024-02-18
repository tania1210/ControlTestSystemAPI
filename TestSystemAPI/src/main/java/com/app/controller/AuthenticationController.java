package com.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.AuthenticationService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthenticationController {

	private AuthenticationService authenticationService;

	
	public AuthenticationController(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}
	
	
	@PostMapping("login")
	public ResponseEntity<String> authentication(@RequestParam("email") String email, @RequestParam("password") String password) {
		ResponseEntity<String> message = authenticationService.login(email, password);
		System.out.print(message);
		return message;
	}
}
