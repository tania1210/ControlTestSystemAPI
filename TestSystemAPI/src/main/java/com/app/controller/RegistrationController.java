package com.app.controller;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.model.Role;
import com.app.model.User;
import com.app.repository.RegistrationRepository;
import com.app.repository.UserRepository;
import com.app.security.PasswordEncoder;

import lombok.AllArgsConstructor;

@RestController
@RequestMapping(path = "api/v1/registration")
@AllArgsConstructor
public class RegistrationController {
	
	private RegistrationRepository registrationRepository;
	private UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public RegistrationController(RegistrationRepository registrationRepository, UserRepository userRepository) {
		this.passwordEncoder = new PasswordEncoder();
		System.out.println("create Registration Repository");
		this.registrationRepository = registrationRepository;
		this.userRepository = userRepository;
	}
	
	@PostMapping("/registration")
	public ResponseEntity<String> createUser(
	    @RequestParam("firstName") String firstName,
	    @RequestParam("lastName") String lastName,
	    @RequestParam("email") String email,
	    @RequestParam("password") String password) {
		Optional<User> existingUser = userRepository.findByEmail(email);
		if(existingUser.isPresent()) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("email is already registered");
		}
		String hashedPassword = passwordEncoder.bCryptPasswordEncoder().encode(password);

	    User user = new User(firstName, lastName, email, hashedPassword, Role.USER, false, true);
	    // Збереження користувача в базі даних
	    registrationRepository.save(user);
	    return ResponseEntity.ok("User registered successfully!");
	}

}
