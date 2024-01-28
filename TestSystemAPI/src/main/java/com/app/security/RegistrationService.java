package com.app.security;

import org.springframework.stereotype.Service;

import com.app.model.RegistrationRequest;

@Service
public class RegistrationService {
	
	public String register(RegistrationRequest request) {
		return "works";
	}
}
