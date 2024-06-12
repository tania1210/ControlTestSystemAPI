package com.app.controller.authentication;

import com.app.auth.AuthenticationRequest;
import com.app.auth.AuthenticationResponse;
import com.app.auth.RegistrationRequest;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.authentication.AuthenticationService;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class AuthenticationController {

	private AuthenticationService service;

	@PostMapping("/register")
	public ResponseEntity<AuthenticationResponse> register(@Parameter(description = "Name of the user") @RequestParam String firstname,
														   @Parameter(description = "Last name of the user") @RequestParam String lastName,
														   @Parameter(description = "Surname of the user") @RequestParam String surName,
														   @Parameter(description = "email of the user") @RequestParam String email,
														   @Parameter(description = "password of the user") @RequestParam String password) {
//		RegistrationRequest request =
		return  ResponseEntity.ok(service.register(new RegistrationRequest(firstname, lastName, surName, email, password)));
	}

	@PostMapping("/authenticate")
	public ResponseEntity<AuthenticationResponse> auth(@Parameter(description = "email of the user") @RequestParam String email,
													   @Parameter(description = "password of the user") @RequestParam String password) {
		return ResponseEntity.ok(service.authenticate(new AuthenticationRequest(email, password)));
	}


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
