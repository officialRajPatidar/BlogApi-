package com.blogapis.services;

import java.util.List;

import com.blogapis.payloads.CategoryDto;

public interface CategroyService {

	
	
	public CategoryDto createCategroy(CategoryDto categoryDto);

	public CategoryDto updateCategroy(CategoryDto categoryDto, Integer categroyId);
	public void deleteCategroy(Integer categroyId);
	
	public CategoryDto getSingleCategroy(Integer categroyId);
 List<CategoryDto> getAllCategroies();



	
	
}
