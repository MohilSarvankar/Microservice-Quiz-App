package com.viper.auth_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.viper.auth_service.model.UserPrincipal;
import com.viper.auth_service.model.Users;
import com.viper.auth_service.repo.UserRepo;

@Service
public class MyUserDetailsService implements UserDetailsService{
	
	@Autowired
	private UserRepo repo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {		
		Users user = repo.findByName(username);
		
		if(user == null)
			throw new UsernameNotFoundException("User not found");
		
		return new UserPrincipal(user);
	}

}
