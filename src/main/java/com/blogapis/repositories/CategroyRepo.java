package com.blogapis.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogapis.entity.Category;

public interface CategroyRepo extends JpaRepository<Category, Integer> {

}
