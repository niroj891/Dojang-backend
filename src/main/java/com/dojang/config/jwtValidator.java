package com.dojang.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class jwtValidator extends OncePerRequestFilter {

	
	@Autowired
	private JwtProvider jwtProvider;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String jwt = request.getHeader(JwtConstant.JWT_HEADER);
		
		if (jwt!=null) {
			
			try {
				String email = jwtProvider.getEmailFromJwtToken(jwt);
				List<GrantedAuthority>authorities = new ArrayList<>();
				
				Authentication auhentication = new UsernamePasswordAuthenticationToken(email,null,authorities);
				
				SecurityContextHolder.getContext().setAuthentication(auhentication);
			
			} catch (Exception e) {
				throw new BadCredentialsException("Invalid token");
			}
			
		}
//		else {
//			throw new BadCredentialsException("please provid a valid token");
//		}
		
		filterChain.doFilter(request, response);
		
	}

	

}
