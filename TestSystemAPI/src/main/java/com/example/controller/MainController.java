package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.model.User;
import com.example.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "Main Controller")
public class MainController {

	@Autowired
	private UserService userService;
	
    @GetMapping("/")
    @ApiOperation("Get home page message")
    public String viewHomePage() {
        return "Home page";
    }

    @PostMapping("/login")
    @ApiOperation("Authentication")
    public String authenticateUser( User user) {
        System.out.println(user);
        return "User is authentication!";
    }
    
    @PostMapping("/register")
    @ApiOperation("Create new user")
    public String createUser( User user) {
    	userService.createUser(user);
        System.out.println(user);
        return "User is creating!";
    }
}

