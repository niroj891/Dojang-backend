package com.dojang.dto;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
	
private Integer id;
	
	private String caption;
	
	private String image;

	private LocalDateTime createdAt;
	
	private UserDto user;
	
	private List<CommentDto> comments=new ArrayList<>();
	
	private List<UserDto> liked= new ArrayList<>(); 
	
	private boolean likedByRequser;
	
	private boolean savedByRequser;

}
