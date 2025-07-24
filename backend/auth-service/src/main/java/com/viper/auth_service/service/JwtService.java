package com.viper.auth_service.service;

import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.stereotype.Service;

import com.viper.auth_service.model.Users;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
	
	private String key = "somerandomkey";
	
	public JwtService() {
		try {
			KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
			SecretKey sk = keyGen.generateKey();
			key = Base64.getEncoder().encodeToString(sk.getEncoded());
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
	}

	public String getToken(Users user) {
		Map<String, Object> map = new HashMap<>();
		map.put("role", user.getRole());
		
		return Jwts.builder()
				   .claims()
				   .add(map)
				   .subject(user.getName())
				   .issuedAt(new Date(System.currentTimeMillis()))
				   .expiration(new Date(System.currentTimeMillis() + 1000*60*60))
				   .and()
				   .signWith(getKey())
				   .compact();
	}

	private SecretKey getKey() {
		return Keys.hmacShaKeyFor(key.getBytes());
	}

}
