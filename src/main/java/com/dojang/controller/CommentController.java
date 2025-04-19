package com.dojang.controller;

import com.dojang.dto.CommentDto;
import com.dojang.exception.CommentException;
import com.dojang.exception.PostException;
import com.dojang.exception.UserException;
import com.dojang.model.Comments;
import com.dojang.model.User;
import com.dojang.service.CommentService;
import com.dojang.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/comments")
public class CommentController {


    @Autowired
    private CommentService commentService;

    @Autowired
    private UserService userService;

    @PostMapping("/{postId}")
    public ResponseEntity<Comments> createCommentHandler(@RequestBody CommentDto commentDto, @PathVariable Integer postId, @RequestHeader("Authorization")String token) throws PostException, UserException {
        User user = userService.findUserProfileByJwt(token);

        Comments comments = new Comments();
        comments.setCreatedAt(LocalDateTime.now());
        comments.setContent(commentDto.getContent());
        Comments createdComment = commentService.createComment(comments, postId, user.getId());
        return new ResponseEntity<Comments>(createdComment, HttpStatus.CREATED);

    }


    @PutMapping("/like/{commentId}")
    public ResponseEntity<Comments> likeCommentHandler(@PathVariable Integer commentId, @RequestHeader("Authorization")String token) throws UserException, CommentException {
        System.out.println("----------- like comment id ---------- ");
        User user = userService.findUserProfileByJwt(token);
        Comments likedComment=commentService.likeComment(commentId, user.getId());
        System.out.println("liked comment - : "+likedComment);
        return new ResponseEntity<Comments>(likedComment,HttpStatus.OK);
    }




}
