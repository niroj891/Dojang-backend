package com.dojang.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.dojang.dao.UserDao;
import com.dojang.model.User;

@Component
public class CustomUserDetailsService implements UserDetailsService{

	@Autowired
	private UserDao userDao;

	public CustomUserDetailsService( UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		Optional<User> user = userDao.findByEmail(username);

		if (user.isPresent()) {
			User user1 = user.get();
			return org.springframework.security.core.userdetails.User.builder().username(user1.getEmail()).password(user1.getPassword()).roles(String.valueOf(user1.getRole())).build();
		}else {
			throw new UsernameNotFoundException("Username not found");
		}
	}

}
