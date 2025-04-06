package com.dojang.config;

import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dojang.model.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtProvider {
	
	private  SecretKey key = Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
	
	public  String generateToken(Authentication auth) {
		
		    // Cast to user entity
		String jwt =Jwts.builder()
				.setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime()+86400000))
				.claim("email",auth.getName())
				
				.signWith(key)
				.compact();
		
		return jwt;
	}
	
	public  String getEmailFromJwtToken(String jwt) {
		
		// Remove bearer prefix if present
		if(jwt != null && jwt.startsWith("Bearer")) {
			jwt = jwt.substring(7);
		}
		
		 Claims claims = Jwts.parser()
		            .verifyWith(key)
		            .build()
		            .parseSignedClaims(jwt)
		            .getPayload();
		        return String.valueOf(claims.get("email"));
	}

}
