package com.dojang.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.crypto.SecretKey;

import com.dojang.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;



@Component
public class JWTValidator extends OncePerRequestFilter {

	
	@Autowired
	private JwtProvider jwtProvider;


	@Autowired
	private UserDetailsService userDetailsService;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String jwt = request.getHeader(JwtConstant.JWT_HEADER);
		
		if (jwt!=null) {
			jwt = jwt.substring(7);
			
//			 Claims claims = Jwts.parser()
//			            .verifyWith(key)
//			            .build()
//			            .parseSignedClaims(jwt)
//			            .getPayload();
//			        return String.valueOf(claims.get("email"));
			
			try {
				
				
				SecretKey key= Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());
				
				Claims claims=Jwts.parser()
				.verifyWith(key).build()
				.parseSignedClaims(jwt)
				.getPayload();
				
				
				String email=String.valueOf(claims.get("email"));

				UserDetails userDetails = userDetailsService.loadUserByUsername(email);
				Authentication authentication=new UsernamePasswordAuthenticationToken(email,null, userDetails.getAuthorities());
				
				SecurityContextHolder.getContext().setAuthentication(authentication);
				
			} catch (Exception e) {
				response.setStatus(HttpStatus.UNAUTHORIZED.value());
			}
		}
//		else {
//			throw new BadCredentialsException("please provide a valid token");
//		}
		
		filterChain.doFilter(request, response);
		
	}

	

}
