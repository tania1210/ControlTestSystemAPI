package com.example.service;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.stereotype.Service;

import com.example.model.User;
import com.example.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public User createUser(User user) {
		return userRepo.save(user);
	}
}
