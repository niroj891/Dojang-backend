package com.dojang.controller;

import java.util.Optional;

import com.dojang.dto.UserDto;
import com.dojang.exception.UserException;
import com.dojang.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dojang.config.JwtProvider;
import com.dojang.dao.UserDao;
import com.dojang.model.User;
import com.dojang.request.LoginRequest;
import com.dojang.response.AuthResponse;
import com.dojang.service.CustomUserDetailsService;
import com.dojang.service.UserService;

@RestController
@RequestMapping("/auth")

public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CustomUserDetailsService customUserDetails;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@PostMapping("/signup")
	public AuthResponse createUser(@RequestBody UserDto user) throws Exception{
		
		Optional<User> isExist = userDao.findByEmail(user.getEmail());
		if(isExist.isPresent()) {
			throw new Exception ("This email is already used with another account");
		}
		User newUser = new User();
		newUser.setEmail(user.getEmail());
		newUser.setFirstName(user.getFirstName());
		newUser.setLastName(user.getLastName());
		newUser.setGender(user.getGender());
		newUser.setRole(user.getRole());
		newUser.setUsername(user.getUserName());
		newUser.setPhoneNumber(user.getPhoneNumber());
		newUser.setPassword(passwordEncoder.encode(user.getPassword()));
		User savedUser = userDao.save(newUser);
		Authentication authentication = new UsernamePasswordAuthenticationToken(savedUser.getEmail(), savedUser.getPassword());
		String token = jwtProvider.generateToken(authentication);
		AuthResponse res = new AuthResponse (token,"Register Success",null);
		return res;
	}

	@PostMapping("/signin")
	public ResponseEntity<AuthResponse> signin(@RequestBody LoginRequest loginRequest) throws UserException {
		try {
			UserDetails userDetails = customUserDetails.loadUserByUsername(loginRequest.getEmail());

			try {
				Authentication authentication = authenticate(loginRequest.getEmail(), loginRequest.getPassword());

				if (authentication.isAuthenticated()) {
					String token = jwtProvider.generateToken(authentication);
					User userByEmail = userService.findUserByEmail(loginRequest.getEmail());
					AuthResponse res = new AuthResponse(token, "success", userByEmail.getRole().toString());
					return new ResponseEntity<>(res, HttpStatus.OK);
				}
			} catch (BadCredentialsException e) {
				AuthResponse response = new AuthResponse(null, "Incorrect password", "failed");
				return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
			}

			AuthResponse response = new AuthResponse(null, "Authentication failed", "failed");
			return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);

		} catch (UsernameNotFoundException e) {
			AuthResponse authResponse = new AuthResponse(null, "Incorrect email", null);
			return new ResponseEntity<>(authResponse, HttpStatus.UNAUTHORIZED);
		}
	}

	private Authentication authenticate(String email, String password) {
		UserDetails userDetails = customUserDetails.loadUserByUsername(email);
		if (userDetails==null) {
			throw new BadCredentialsException ("Invalid username");
		}
		if(!passwordEncoder.matches(password,userDetails.getPassword())){
			throw new BadCredentialsException("password not matched");
		}
		return new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
	}
}
