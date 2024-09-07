package com.blogapis.services;

import com.blogapis.payloads.CommentDto;

public interface CommentService {

	
	
	CommentDto createComment(CommentDto commentDto,Integer postId ,Integer userId);

	void delteComment(Integer commentid);
}
