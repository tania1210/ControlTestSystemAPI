package com.app.controller.authentication;

import com.app.auth.AuthenticationRequest;
import com.app.auth.AuthenticationResponse;
import exceptions.EmailNotFoundException;
import exceptions.IncorrectPasswordException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

	@Operation(summary = "авторизуватись", description = "надає доступ до відповідних ресурсів")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "користувач авторизувався"),
			@ApiResponse(responseCode = "404", description = "користувача з вказаною поштою не знайдено/не дійсний пароль")
	})
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
		} catch (IncorrectPasswordException e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
		}
	}
}
