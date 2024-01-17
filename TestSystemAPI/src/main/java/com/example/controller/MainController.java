package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.model.User;
import com.example.model.WebResponse;
import com.example.service.UserService;

@RestController
public class MainController {

//	@PostMapping("/signIn")
//	public ResponseEntity<?> signIn(@RequestBody LoginRequest request) {
//		return new ResponseEntity<>(
//				new WebResponse(
//						HttpStatus.OK.value(),
//						HttpStatus.OK.getReasonPhrase(),
//						"Successfuly signed in!"
//				), HttpStatus.OK
//		);
//	}
//	@Autowired
//	private UserService userService;
//	
    @GetMapping("/")
    public String viewHomePage() {
        return "Home page";
    }

    @PostMapping("/login")
    public String login() {
        return "login";
    }
    
    @PostMapping("/register")
    public String register() {
        return "register";
    }
    
    @PostMapping("/createUser")
    public String createUser(@ModelAttribute User user) {
    	System.out.println(user);
//    	userService.createUser(user);
    	return "register";
    }
}

