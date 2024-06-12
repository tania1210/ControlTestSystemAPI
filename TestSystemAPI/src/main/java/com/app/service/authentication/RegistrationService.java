package com.app.service.authentication;

import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repository.RegistrationRepository;
import com.app.repository.UserRepository;
import com.app.security.PasswordEncoderConfig;
import com.app.security.Role;

import exceptions.UserAlreadyExistsException;

@Service
public class RegistrationService {
	
	private RegistrationRepository registrationRepository;
	private UserRepository userRepository;
	private final PasswordEncoderConfig passwordEncoder;
	
	public RegistrationService(RegistrationRepository registrationRepository, UserRepository userRepository) {
		this.passwordEncoder = new PasswordEncoderConfig();
		this.registrationRepository = registrationRepository;
		this.userRepository = userRepository;
	}
	
	public ResponseEntity<String> registration(String firstName, String lastName, String surName, String email, String password) throws UserAlreadyExistsException{
		Optional<User> existingUser = userRepository.findByEmail(email);
		if(existingUser.isPresent()) {
			throw new UserAlreadyExistsException();
		}
		
		String hashedPassword = passwordEncoder.bCryptPasswordEncoder().encode(password);
		User user = new User(firstName, lastName, surName, email, hashedPassword, Role.USER, false, true);
		registrationRepository.save(user);
		return ResponseEntity.ok().body("User is registered");		
	    
	}

}
