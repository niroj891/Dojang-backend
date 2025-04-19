package com.dojang.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.dojang.dto.CommentDto;
import com.dojang.dto.UserDto;
import com.dojang.model.Comments;
import com.dojang.model.User;

public class CommentDtoMapper {
	
	public static CommentDto toCommentDTO(Comments comment) {
		
		UserDto userDto=UserDtoMapper.userDTO(comment.getUser());
		List<User> likedUsers=new ArrayList<>(comment.getLiked());
		List<UserDto> userDtos = UserDtoMapper.userDTOS(likedUsers);
		
		CommentDto commentDto=new CommentDto();
		commentDto.setContent(comment.getContent());
		//commentDto.setCreatedAt(comment.getCreatedAt());
		commentDto.setId(comment.getId());
		
		return commentDto;
	}
	
	public static List<CommentDto> toCommentDtos(List<Comments> comments){
		List<CommentDto> commentDtos=new ArrayList<>();
		
		for(Comments comment: comments) {
			
			CommentDto commentDto=toCommentDTO(comment);
			commentDtos.add(commentDto);
			
		}
		
		return commentDtos;
	}

}
