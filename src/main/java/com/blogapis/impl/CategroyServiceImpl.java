package com.blogapis.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.blogapis.entity.Category;
import com.blogapis.exception.ResourceNotFoundException;
import com.blogapis.payloads.CategoryDto;
import com.blogapis.repositories.CategroyRepo;
import com.blogapis.services.CategroyService;

@Service
public class CategroyServiceImpl implements CategroyService {

    private final CategroyRepo categroyRepo;
    private final ModelMapper mapper;

    @Autowired
    public CategroyServiceImpl(CategroyRepo categroyRepo, ModelMapper mapper) {
        this.categroyRepo = categroyRepo;
        this.mapper = mapper;
    }

    @Override
    public CategoryDto createCategroy(CategoryDto categoryDto) {
        Category cat = this.mapper.map(categoryDto, Category.class);
        Category addedcat = this.categroyRepo.save(cat);
        return this.mapper.map(addedcat, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategroy(CategoryDto categoryDto, Integer categroyId) {
        Category cat = this.categroyRepo.findById(categroyId)
                .orElseThrow(() -> new ResourceNotFoundException("Categroy", "Categroy Id", categroyId));
        cat.setCategoryTitle(categoryDto.getCategoryTitle());
        cat.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedcategory = this.categroyRepo.save(cat);
        return this.mapper.map(updatedcategory, CategoryDto.class);
    }

    public void deleteCategroy(Integer categroyId) {
        Category cat = this.categroyRepo.findById(categroyId)
                .orElseThrow(() -> new ResourceNotFoundException("Categroy", "Categroy Id", categroyId));
        this.categroyRepo.delete(cat);
    }

    @Override
    public CategoryDto getSingleCategroy(Integer categroyId) {
        Category cat = this.categroyRepo.findById(categroyId)
                .orElseThrow(() -> new ResourceNotFoundException("Categroy", "Categroy Id", categroyId));
        return this.mapper.map(cat, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategroies() {
        List<Category> categories = this.categroyRepo.findAll();
        return categories.stream()
                .map(cat -> this.mapper.map(cat, CategoryDto.class))
                .collect(Collectors.toList());
    }
}
