package com.app.controller.authentication;

import com.app.model.User;
import exceptions.InvalidPasswordException;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.service.authentication.RegistrationService;

import exceptions.UserAlreadyExistsException;
import lombok.AllArgsConstructor;

@RestController
@RequestMapping("api/v1/signUp")
public class RegistrationController {

	private RegistrationService registrationService;

	public RegistrationController(RegistrationService registrationService) {
		this.registrationService = registrationService;
	}

	@PostMapping
	public ResponseEntity<?> createUser(@Parameter(description = "firstName of the user") @RequestParam String firstName,
										@Parameter(description = "lastName of the user") @RequestParam String lastName,
										@Parameter(description = "surName of the user") @RequestParam String surName,
										@Parameter(description = "email of the user") @RequestParam String email,
										@Parameter(description = "password of the user") @RequestParam String password) {

		try {
			User user = registrationService.registration(firstName, lastName, surName, email, password);
			return ResponseEntity.status(HttpStatus.CREATED).body(user);
		} catch (UserAlreadyExistsException e) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("User with email " + email + " already exists");
		} catch (InvalidPasswordException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
