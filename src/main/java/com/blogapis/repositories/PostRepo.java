package com.blogapis.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.blogapis.entity.Post;
import com.blogapis.entity.User;
import com.blogapis.entity.Category;

public interface PostRepo extends JpaRepository<Post, Integer> {

    List<Post> findByUser(User user);
    List<Post> findByCategory(Category category);
    // Custom method to search by title or content
    
    List<Post> findByTitleContainingOrContentContaining(String title, String content);
}
