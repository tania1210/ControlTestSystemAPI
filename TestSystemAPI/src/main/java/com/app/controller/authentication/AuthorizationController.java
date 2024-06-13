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

	@PostMapping( consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> auth(@RequestBody AuthenticationRequest request) {
		System.out.println("Received login request for email: " + request.getEmail());
		try {
			AuthenticationResponse response = service.authenticate(request);
			System.out.println("Login successful for email: " + request.getEmail());
			return ResponseEntity.ok(response);
		} catch (EmailNotFoundException e) {
			System.out.println("Login failed for email: " + request.getEmail() + " " + e.getMessage());
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}catch (AuthenticationException e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
		}
	}

//	@PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
//	public ResponseEntity<AuthenticationResponse> auth(@Parameter(description = "email of the user") @RequestBody String email,
//													   @Parameter(description = "password of the user") @RequestBody String password) {
//
////		return ResponseEntity.ok(service.authenticate(new AuthenticationRequest(email, password)));
//
//		System.out.println("Received login request for email: {}" + email);
//		try {
//			AuthenticationResponse response = service.authenticate(new AuthenticationRequest(email, password));
//			System.out.println("Login successful for email: {}" + email);
//			return ResponseEntity.ok(response);
//		} catch (Exception e) {
//			System.out.println("Login failed for email: {}" + email + e);
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(null);
//		}
//	}


//	@PostMapping("login")
//	public ResponseEntity<String> authentication(@RequestParam("email") String email, @RequestParam("password") String password) {
//		ResponseEntity<String> message = null;
//		try {
//			message = authenticationService.login(email, password);
//		}catch(UserNotFoundException e) {
//			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
//		}catch(IncorrectPasswordException e) {
//			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect password");
//		}
//
//		return message;
//	}
}
