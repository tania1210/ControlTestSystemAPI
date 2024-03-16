package com.app.service;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repository.UserRepository;
import com.app.security.PasswordEncoder;

import exceptions.IncorrectPasswordException;
import exceptions.UserNotFoundException;

@Service
public class AuthenticationService {

	private UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public AuthenticationService(UserRepository userRepository) {
		this.passwordEncoder = new PasswordEncoder();
		this.userRepository = userRepository;
	}
	
	public ResponseEntity<String> login(String email, String password) throws UserNotFoundException, IncorrectPasswordException{
		Optional<User> registeredUser = userRepository.findByEmail(email);
		if(!registeredUser.isPresent()) {
			throw new UserNotFoundException();
		}
		
		String haschedPasswordFromBD = registeredUser.get().getPassword();
		if(passwordEncoder.bCryptPasswordEncoder().matches(password, haschedPasswordFromBD)) {
				return ResponseEntity.ok("user is authenticated");
			}else {
				throw new IncorrectPasswordException();		
		}

	}
	
	
}
