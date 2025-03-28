package com.dojang.service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dojang.config.JwtProvider;
import com.dojang.dao.UserDao;
import com.dojang.exception.UserException;
import com.dojang.model.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private PasswordEncoder passwordEncoder;
	


	@Override
	public User registerUser(User user) throws UserException{
		
		Optional<User> isEmailExist = userDao.findByEmail(user.getEmail());
		
		if (isEmailExist.isPresent()) {
			throw new UserException("Email Already Exist");
		}
			
			String encodedPassword=passwordEncoder.encode(user.getPassword());
			
			User newUser=new User();
			
			newUser.setEmail(user.getEmail());
			newUser.setPassword(encodedPassword);
			newUser.setFirstName(user.getFirstName());
			newUser.setLastName(user.getLastName());
			
			return userDao.save(newUser);
			
			
	}

	@Override
	public User findUserById(Integer id) throws UserException{
		
		Optional<User> opt = userDao.findById(id);
		
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new UserException("user not exist with user id" + id);
		
	}
	

	@Override
	public User findUserByEmail(String username)throws UserException {
		
		Optional<User> opt=userDao.findByEmail(username);
		
		if(opt.isPresent()) {
			User user=opt.get();
			return user;
		}
		
		throw new UserException("user not exist with username "+username);
	}
	
	

	@Override
	public String followUser(Integer reqUserId, Integer followUserId) throws UserException {
		
	
		User followUser=findUserById(followUserId);
		User reqUser=findUserById(reqUserId); 
		
		
		if(followUser.getFollower().contains(reqUser)) {
			followUser.getFollower().remove(reqUser);
			reqUser.getFollowing().remove(followUser);
		}
		else {
			followUser.getFollower().add(reqUser);
		    reqUser.getFollowing().add(followUser);
		}
		
		userDao.save(followUser);
		userDao.save(reqUser);
		
		return "Success";
	}
	
	

	@Override
	public User updateUserDetails(User updatedUser, User existingUser) throws UserException{
		
		
		if(updatedUser.getFirstName()!=null) {
			existingUser.setFirstName(updatedUser.getFirstName());
		}
		
		if(updatedUser.getLastName()!=null) {
			existingUser.setLastName(updatedUser.getLastName());
		}
		
		if(updatedUser.getEmail()!=null) {
			existingUser.setEmail(updatedUser.getEmail());
		}
		
		if(updatedUser.getPhoneNumber()!=null) {
			existingUser.setPhoneNumber(updatedUser.getPhoneNumber());
		}
		
		if(updatedUser.getImage()!=null) {
			existingUser.setImage(updatedUser.getImage());
		}
		
		if(updatedUser.getGender()!=null) {
			existingUser.setGender(updatedUser.getGender());
		}
		
		
		
		if(updatedUser.getId()==existingUser.getId()) {
			System.out.println(" u "+updatedUser.getId()+" e "+existingUser.getId());
			throw new UserException("you can't update another user"); 
		}
		
		return userDao.save(existingUser);
	}

	@Override
	public Set<User> searchUser(String query) throws UserException {
		// TODO Auto-generated method stub
		Set<User> users = userDao.findByQuery(query);
		if(users.size()==0) {
			throw new UserException("user not exist.....");
		}
		return users;
		  
	}

	@Override
	public List<User> findAll() {
		
		return userDao.findAll();
	}

	@Override
	public void deleteUser(User user) {
		// TODO Auto-generated method stub
		userDao.delete(user);
	}

	@Override
	public User findUserProfileByJwt(String jwt) throws UserException {
		
		String email =jwtProvider.getEmailFromJwtToken(jwt);
		System.out.println("email" + email);
		Optional<User> user=userDao.findByEmail(email);
		
		if(user.isEmpty()) {
			throw new UserException("user not exist with email "+email);
		}
		System.out.println("email user "+user.get().getEmail());
		return user.get();
	}

	@Override
	public List<User> findUsersByUserIds(List<Integer> userIds) {
		// TODO Auto-generated method stub
		return null;
	}

	
	
	

}
