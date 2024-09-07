package com.blogapis.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.blogapis.payloads.ApiResponce;
import com.blogapis.payloads.CategoryDto;
import com.blogapis.services.CategroyService;

import jakarta.validation.Valid;
import lombok.val;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/api/categories")

public class CategoryCtl {

	@Autowired
	private CategroyService categroyService;
	
	
	@PostMapping("/")
	public ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto cateogDto) {
		 CategoryDto categroy = this.categroyService.createCategroy(cateogDto);
		
		return new ResponseEntity<CategoryDto>(categroy,HttpStatus.CREATED);
	}
	
	@PutMapping("/{catId}")
	public ResponseEntity<CategoryDto> updateCategory (@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer catId) {
		 CategoryDto updatedcategroy = this.categroyService.updateCategroy(categoryDto, catId);
		
		return new ResponseEntity<CategoryDto>(updatedcategroy,HttpStatus.OK);
	}
	
	@DeleteMapping("/{catId}")
	public ResponseEntity< ApiResponce> deleteCategory( @PathVariable Integer catId){
		this.categroyService.deleteCategroy(catId);
		return new ResponseEntity<ApiResponce>(new ApiResponce("category is deleted successfully !!" , true),HttpStatus.OK);

	}

	@GetMapping("/{catId}")
	public ResponseEntity<CategoryDto> getCategory( @PathVariable Integer catId){
		CategoryDto singleCategroy = this.categroyService.getSingleCategroy(catId);
		return new ResponseEntity<CategoryDto>(singleCategroy ,HttpStatus.OK);

	}
	@GetMapping("/")
	public ResponseEntity<List<CategoryDto>> getAllCategory() {
		List<CategoryDto> allCategroies = this.categroyService.getAllCategroies();
		return ResponseEntity.ok(allCategroies);
	}
	
	
	
	
	
}
