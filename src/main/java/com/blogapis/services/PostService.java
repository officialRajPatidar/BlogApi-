package com.blogapis.services;

import java.util.List;

import com.blogapis.payloads.PostDto;
import com.blogapis.payloads.PostResponce;

public interface PostService {

    PostDto createPost(PostDto postDto, Integer userId, Integer categoryId);
    
    PostDto updatePost(PostDto postDto, Integer postId);

    void deleteposts(Integer postId);
    
    PostResponce getAllPost(int pageNumber, int pageSize, String sortBy, String sortDir);
    
    PostDto getPostById(Integer postId);
    
    List<PostDto> getPostByCategory(Integer categoryId);
    
    List<PostDto> getPostByUser(Integer userId);
    
    List<PostDto> searchPosts(String keywords);
}
