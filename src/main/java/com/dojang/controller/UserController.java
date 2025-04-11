package com.dojang.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.dojang.dto.ParticipationDto;
import com.dojang.model.Event;
import com.dojang.model.Participation;
import com.dojang.model.PlayerStatus;
import com.dojang.service.EventService;
import com.dojang.service.ParticipationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dojang.dto.UserDto;
import com.dojang.dto.UserProfileDto;
import com.dojang.dto.mapper.UserDtoMapper;
import com.dojang.exception.UserException;
import com.dojang.model.User;
import com.dojang.response.ApiResponse;
import com.dojang.service.UserService;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {
	
	@Autowired
	private UserService userService;

	@Autowired
	private EventService eventService;

	@Autowired
	private ParticipationService participationService;
	
	@GetMapping("/profile")
	public ResponseEntity<UserProfileDto> getUserProfileHandler(@RequestHeader("Authorization") String jwt) throws UserException {

		User user = userService.findUserProfileByJwt(jwt);
		user.setPassword(null);
		
		UserProfileDto userDto=UserDtoMapper.reqUserDTO(user,user);

		return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserProfileDto> findUserByIdHandler(@PathVariable Integer id,
			@RequestHeader("Authorization") String jwt) throws UserException{
		User requser = userService.findUserProfileByJwt(jwt);
		User user=userService.findUserById(id);
		
		UserProfileDto userDto=UserDtoMapper.reqUserDTO(user,requser);

		return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
	}
	
	
	@PutMapping("/follow/{followUserId}")
	public ResponseEntity<ApiResponse> followUserHandler(@RequestHeader("Authorization") String token, @PathVariable Integer followUserId) throws UserException{
		User reqUser=userService.findUserProfileByJwt(token);
		
		String message=userService.followUser(reqUser.getId(), followUserId);
		ApiResponse res=new ApiResponse("follow",true);
		return new ResponseEntity<>(res,HttpStatus.OK);
	}

	
	
	@GetMapping("/search")
	public ResponseEntity<List<UserDto>> searchUserHandler(@RequestParam("q")String query) throws UserException{
		
		Set<User> users=userService.searchUser(query);
		
		List<UserDto> userDtos=UserDtoMapper.userDTOS(new ArrayList<>(users));
		
		return new ResponseEntity<>(userDtos,HttpStatus.OK);
	}
	
	@PutMapping("/update")
	public ResponseEntity<UserProfileDto> updateUser(@RequestHeader("Authorization") String token, @RequestBody User user) throws UserException{
		
		User reqUser=userService.findUserProfileByJwt(token);
		User updatedUser=userService.updateUserDetails(user, reqUser);
		
		UserProfileDto userDto=UserDtoMapper.reqUserDTO(updatedUser,reqUser);

		return new ResponseEntity<>(userDto, HttpStatus.ACCEPTED);
	}

	@PostMapping("/events/register/{eventId}")
	public ResponseEntity<?> registerInEvent(@PathVariable Integer eventId, @RequestBody ParticipationDto participationDto) throws UserException {
		String email = SecurityContextHolder.getContext().getAuthentication().getName();
		User userByEmail = userService.findUserByEmail(email);
		Event event = eventService.getById(eventId);
		Optional<Participation> first = participationService.getAll().stream()
				.filter(participation -> participation.getEvent()
				.getEventId().equals(eventId) && participation.getUser()
				.getEmail().equals(email)).findFirst();
		
		if (first.isPresent()){
			return  new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
		}
		else {
		Participation participation = new Participation();
		participation.setFirstName(participationDto.getFirstName());
		participation.setLastName(participationDto.getLastName());
		participation.setPlayerStatus(participationDto.getPlayerStatus());
		participation.setDojangName(participationDto.getDojangName());
		participation.setUser(userByEmail);
		participation.setEvent(event);
		participation.setWeightCategory(participationDto.getWeightCategory());
		participationService.saveParticipation(participation);
		participation.setPlayerStatus(PlayerStatus.NOTOUT);
		return new ResponseEntity<>("User registered",HttpStatus.OK);
		}
	}
}
