package com.blogapis.ctl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.blogapis.entity.Comment;
import com.blogapis.payloads.ApiResponce;
import com.blogapis.payloads.CommentDto;
import com.blogapis.services.CommentService;

@RestController
@RequestMapping("/api/")
public class CommentCtl {

	@Autowired
	private  CommentService commentService;
	
	@PostMapping("/user/{userId}/post/{postId}/comments")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto ,@PathVariable Integer postId , @PathVariable Integer userId){
		
		CommentDto comment = this.commentService.createComment(commentDto, postId, userId);
		
		return new ResponseEntity<CommentDto>(comment,HttpStatus.CREATED);
		
	}
	
	@DeleteMapping("/comments/{commentId}")
	public ResponseEntity<ApiResponce> dleteComment (@PathVariable Integer commentId){
		
		this.commentService.delteComment(commentId);
		
		return new ResponseEntity<ApiResponce>(new ApiResponce("Comment delteted successfully  !!", true) , HttpStatus.OK);
	}
	
}
