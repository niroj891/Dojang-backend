package com.dojang.dto;

import java.util.ArrayList;
import java.util.List;

import com.dojang.model.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserProfileDto {
	
	private Integer id;
	private String username;
	private String email;
	private String firstName;
	private String lastName;
	private String mobile;
	
	private String gender;
	private String image;
	
	private List<UserDto> follower = new ArrayList<>();
	
	private List<User> following = new ArrayList<>();

//	private List<StoryDto> stories = new ArrayList<>();
//	
//	private List<ReelsDto> reels=new ArrayList<>();

	private List<PostDto> savedPosts = new ArrayList<>();
	
	private List<PostDto> reposts = new ArrayList<>();

}
