package com.viper.auth_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.viper.auth_service.model.Users;
import com.viper.auth_service.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody Users user) {
		return service.register(user);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> login(@RequestBody Users user){
		return service.verify(user);
	}
}
