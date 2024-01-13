package com.example.testsystemapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.testsystemapi.model.User;
import com.example.testsystemapi.repository.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	
	public User createUser(User user) {
		return userRepo.save(user);
	}
}
