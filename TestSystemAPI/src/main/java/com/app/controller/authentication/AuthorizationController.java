package com.app.controller.authentication;

import com.app.auth.AuthenticationRequest;
import com.app.auth.AuthenticationResponse;
import exceptions.EmailNotFoundException;
import org.springframework.security.core.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.app.service.authentication.AuthorizationService;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/login")
@AllArgsConstructor
public class AuthorizationController {

	private AuthorizationService service;
//(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@PostMapping
	public ResponseEntity<?> auth(@RequestParam String email,
								  @RequestParam String password) {
		System.out.println("Received login request for email: " + email);
		try {
			AuthenticationResponse response = service.authenticate(new AuthenticationRequest(email, password));
			System.out.println("Login successful for email: " + email);
			return ResponseEntity.ok(response);
		} catch (EmailNotFoundException e) {
			System.out.println("Login failed for email: " + email + " " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		} catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
		}
	}
}
