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

	public String getToken(String username) {
		Map<String, Object> map = new HashMap<>();
		return Jwts.builder()
				   .claims()
				   .add(map)
				   .subject(username)
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
