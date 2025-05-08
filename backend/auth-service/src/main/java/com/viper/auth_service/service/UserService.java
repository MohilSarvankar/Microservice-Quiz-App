package com.viper.auth_service.service;

import java.util.Collection;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.viper.auth_service.model.Users;
import com.viper.auth_service.repo.UserRepo;

@Service
public class UserService {
	
	@Autowired
	private UserRepo repo;
	
	@Autowired
	private AuthenticationManager authManager;
	
	private BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);

	public ResponseEntity<String> register(Users user) {
		
		if(repo.findByName(user.getName()) != null)
			return new ResponseEntity<>("User already exists",HttpStatus.INTERNAL_SERVER_ERROR);
		
		try {
			user.setPassword(encoder.encode(user.getPassword()));
			repo.save(user);
		}
		catch(Exception e) {
			e.printStackTrace();
			return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return new ResponseEntity<>("User created successfully", HttpStatus.OK);
	}
	

	public ResponseEntity<String> verify(Users user) {
		Authentication authentication =
		authManager.authenticate(new UsernamePasswordAuthenticationToken(user.getName(), 
				user.getPassword(),
				Collections.singleton(new SimpleGrantedAuthority(user.getRole().toString()))));
		
		if(authentication.isAuthenticated())
			return new ResponseEntity<>("Success", HttpStatus.OK);
			
		return new ResponseEntity<>("Failure", HttpStatus.UNAUTHORIZED);
	}
	
}
