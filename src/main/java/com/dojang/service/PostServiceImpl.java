package com.dojang.service;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.dojang.model.Event;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dojang.dao.PostDao;
import com.dojang.dao.UserDao;
import com.dojang.exception.PostException;
import com.dojang.exception.UserException;
import com.dojang.model.Post;
import com.dojang.model.User;
import org.springframework.web.multipart.MultipartFile;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private UserDao userDao;

	@Override
	public Post createPost(Post post, Integer userId) throws UserException, IOException {
		User user = userService.findUserById(userId);
		

		Post newPost =new Post();
		newPost.setUser(user);
		newPost.setCaption(post.getCaption());
		newPost.setCreatedAt(LocalDateTime.now());
		String imageName = savePostImage(newPost.getImageFile());
		newPost.setImage(imageName);
		newPost.setVideo(post.getVideo());
		Post createdPost =postDao.save(newPost);

		return createdPost;
	}

	public String savePostImage(MultipartFile image) throws IOException {
		String desktopPath = System.getProperty("user.home") + File.separator + "Desktop";
		File directory = new File(desktopPath, "dojang_app/images/post");

		if (!directory.exists()) {
			directory.mkdirs();
		}

		if (image == null || image.getOriginalFilename() == null || image.getOriginalFilename().isBlank()) {
			throw new IllegalArgumentException("Invalid file");
		}


		String savingName = image.getOriginalFilename().substring(image.getOriginalFilename().lastIndexOf('.'));
		String randomFileName = UUID.randomUUID().toString();
		savingName  = randomFileName+ savingName;
		File uploadFile = new File(directory, savingName);
		image.transferTo(uploadFile);

		return savingName;

	}



	@Override
	public String deletePost(Integer postId, Integer userId) throws UserException, PostException {
		Post post =findePostById(postId);
		
		User user=userService.findUserById(userId);
		System.out.println(post.getUser().getId()+" ------ "+user.getId());
		if(post.getUser().getId().equals(user.getId())) {
			System.out.println("inside delete");
			postDao.deleteById(postId);
		
		return "Post Deleted Successfully";
		}
		
		
		throw new PostException("You Dont have access to delete this post");
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) throws UserException {
		
		List<Post> posts=postDao.findByUserId(userId);
		return posts;
	}
	

	@Override
	public Post findePostById(Integer postId) throws PostException {
		Optional<Post> opt = postDao.findById(postId);
		if(opt.isPresent()) {
			return opt.get();
		}
		throw new PostException("Post not exist with id: "+postId);
	}
	

	@Override
	public List<Post> findAllPost() throws PostException {
		List<Post> posts = postDao.findAllByOrderByCreatedAtDesc();
		return posts;
	}
	

	@Override
	public Post savedPost(Integer postId, Integer userId) throws PostException, UserException {
		Post post=findePostById(postId);
		User user=userService.findUserById(userId);
		if(!user.getSavedPosts().contains(post)) {
			user.getSavedPosts().add(post);
			
			
		}
		else {
			user.getSavedPosts().remove(post);
			
		}
		userDao.save(user);
		return post;
	}
	

	@Override
	public Post likePost(Integer postId, Integer userId) throws UserException, PostException {

		User user= userService.findUserById(userId);
		
		Post post=findePostById(postId);
		
		if(!post.getLiked().contains(user)) {
			post.getLiked().add(user);
		}
		else {
			post.getLiked().remove(user);
		}
	
		return postDao.save(post);
	}

}
