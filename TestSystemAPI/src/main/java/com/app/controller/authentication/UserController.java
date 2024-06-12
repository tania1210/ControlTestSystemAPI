package com.app.controller.authentication;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.repository.UserRepository;

@RestController
@RequestMapping("/user")
public class UserController {
	
	private final UserRepository userRepository;
	
	public UserController(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
//	@GetMapping
//	public ResponseEntity getAllUsers() {
//		System.out.println("getAllUsers");
//		return ResponseEntity.ok(this.userRepository.findAll());
//	}
//	
//	@GetMapping("/{id}")
//	@Operation(summary = "info search to id")
//	public User getById(Long id) { //@Parameter(description = "only one to one")	@PathVariable("id")
//		System.out.println("---------getById----- id = " + id);
//		Optional<User> optionalUser = userRepository.findById(id);
//		System.out.println("---- result:" + optionalUser);
//		return optionalUser.orElse(null);
//		
//	}
	

}
