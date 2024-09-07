package com.blogapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapis.entity.Comment;

public interface CommentRepo extends JpaRepository<Comment, Integer> {

}
