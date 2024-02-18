package com.app.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.app.model.User;
import com.app.repository.RegistrationRepository;
import com.app.repository.UserRepository;
import com.app.security.PasswordEncoder;
import com.app.security.Role;

@Service
public class RegistrationService {
	
	private RegistrationRepository registrationRepository;
	private UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public RegistrationService(RegistrationRepository registrationRepository, UserRepository userRepository) {
		this.passwordEncoder = new PasswordEncoder();
		this.registrationRepository = registrationRepository;
		this.userRepository = userRepository;
	}
	
	public boolean registration(String firstName, String lastName, String email, String password) {
		if(userIsPresent(email)) {
			return false;
		}else {
			String hashedPassword = passwordEncoder.bCryptPasswordEncoder().encode(password);
			User user = new User(firstName, lastName, email, hashedPassword, Role.USER, false, true);
			registrationRepository.save(user);
			return true;
		}
	    
	}
	
	private boolean userIsPresent(String email) {
		Optional<User> existingUser = userRepository.findByEmail(email);
		if(existingUser.isPresent()) {
			return true;
		}else {
			return false;
		}
	}
}
