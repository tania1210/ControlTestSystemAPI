package com.app.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repository.UserRepository;
import com.app.security.PasswordEncoder;

@Service
public class AuthenticationService {

	private UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public AuthenticationService(UserRepository userRepository) {
		this.passwordEncoder = new PasswordEncoder();
		this.userRepository = userRepository;
	}
	
	public ResponseEntity<String> login(String email, String password) {
		Optional<User> registeredUser = userIsRegistered(email);
		if(registeredUser == null) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("user is not registered");
		}else {
//			String haschedPasswordFromAPI = passwordEncoder.bCryptPasswordEncoder().encode(password);
			String haschedPasswordFromBD = registeredUser.get().getPassword();
//			System.out.println("---\nhaschedPasswordFromAPI = " + haschedPasswordFromAPI + "\nhaschedPasswordFromBD = " + haschedPasswordFromBD + "\n---");
			if(password.equals(haschedPasswordFromBD)) {
				return ResponseEntity.ok("user is authenticated");
			}else {
				return ResponseEntity.status(HttpStatus.CONFLICT).body("password is not correct");
			}
		}

	}
	
	public Optional<User> userIsRegistered(String email) {
		Optional<User> registeredUser = userRepository.findByEmail(email);
		if(registeredUser.isPresent()) {
			return registeredUser;
		}else {
			return null;
		}
	}
}
