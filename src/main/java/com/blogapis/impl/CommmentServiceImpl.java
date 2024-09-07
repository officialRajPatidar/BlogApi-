package com.blogapis.impl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapis.entity.Comment;
import com.blogapis.entity.Post;
import com.blogapis.entity.User;
import com.blogapis.exception.ResourceNotFoundException;
import com.blogapis.payloads.CommentDto;
import com.blogapis.repositories.CommentRepo;
import com.blogapis.repositories.PostRepo;
import com.blogapis.repositories.UserRepo;
import com.blogapis.services.CommentService;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class CommmentServiceImpl implements CommentService {

	@Autowired
	private PostRepo postRepo;
	@Autowired
	private CommentRepo commentRepo;
	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ModelMapper mapper;
	
	@Override
	public CommentDto createComment(CommentDto commentDto, Integer postId , Integer userId) {
        User user = this.userRepo.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "User id", userId));
		Post post= this.postRepo.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "post id", postId));
        Comment comment = this.mapper.map(commentDto, Comment.class);
		
        comment.setPost(post);
        comment.setUser(user);
        
        Comment save = this.commentRepo.save(comment);
		return this.mapper.map(save, CommentDto.class);
	}

	@Override
	public void delteComment(Integer commentId) {
		Comment comment = this.commentRepo.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment", "comment id", commentId));
  this.commentRepo.delete(comment);
	}

}
