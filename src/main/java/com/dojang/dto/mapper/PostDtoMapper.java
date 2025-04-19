package com.dojang.dto.mapper;

import java.util.ArrayList;
import java.util.List;

import com.dojang.dto.CommentDto;
import com.dojang.dto.PostDto;
import com.dojang.dto.UserDto;
import com.dojang.model.Post;
import com.dojang.model.User;
import com.dojang.utils.PostUtil;

public class PostDtoMapper {
	
public static PostDto toPostDto(Post post,User user) {
		
		UserDto userDto=UserDtoMapper.userDTO(post.getUser());
		List<User> likedUsers=new ArrayList<>(post.getLiked());
		List<UserDto> userDtos=UserDtoMapper.userDTOS(likedUsers);
		List<CommentDto> comments=CommentDtoMapper.toCommentDtos(post.getComments());
		
		PostDto postDto=new PostDto();
		postDto.setCaption(post.getCaption());
		postDto.setCreatedAt(post.getCreatedAt());
		postDto.setId(post.getId());
		postDto.setImage(post.getImage());
		postDto.setUser(userDto);
		postDto.setLiked(userDtos);
		postDto.setComments(comments);
		postDto.setVideo(post.getVideo());

		postDto.setLikedByRequser(PostUtil.isLikedByReqUser(post, user));
		postDto.setSavedByRequser(PostUtil.isSaved(post, user));
		
		return postDto;
		
	}
	
	public static List<PostDto> toPostDtos(List<Post> posts, User user){
		List<PostDto> postDtos=new ArrayList<>();
		
		for(Post post:posts) {
			PostDto postDto=toPostDto(post,user);
			postDtos.add(postDto);
		}
		return postDtos;
	}

}
