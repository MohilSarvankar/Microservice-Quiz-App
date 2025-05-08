package com.viper.auth_service.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viper.auth_service.model.Users;

@Repository
public interface UserRepo extends JpaRepository<Users, Integer>{
	
	public Users findByName(String name);
}
