package com.BlogApplication.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApplication.dto.AprResponse;
import com.BlogApplication.dto.CategoryDto;
import com.BlogApplication.services.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
	
	@Autowired
	private CategoryService categoryservice;
	
	@PostMapping("/add")
	public ResponseEntity<CategoryDto> createUser(@Valid @RequestBody CategoryDto CategoryDto){		
		CategoryDto create=categoryservice.save(CategoryDto);
		return new ResponseEntity<>(create,HttpStatus.CREATED);	
	}
	
	@PutMapping("/update/{categoryId}")
	public ResponseEntity<CategoryDto> createUser(@Valid @RequestBody CategoryDto CategoryDto,@PathVariable Integer categoryId){
		CategoryDto update=categoryservice.update(CategoryDto,categoryId);
		return new ResponseEntity<>(update,HttpStatus.CREATED);		
	}
	
	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<AprResponse> deleteUser(@PathVariable Integer categoryId){
		categoryservice.delete(categoryId);
		return new ResponseEntity<AprResponse>(new AprResponse("category delete successfully",true),HttpStatus.OK);	
	}
	
	@GetMapping("{categoryId}")
	public ResponseEntity<CategoryDto> getAllCategory(@PathVariable Integer categoryId){
		return ResponseEntity.ok(categoryservice.getCategoryById(categoryId));
	}
	
	@GetMapping("/getAllCategory")
	public ResponseEntity<List<CategoryDto>> getAllUser(){
		return ResponseEntity.ok(categoryservice.getAllCategory());
	}
	
	
	
}
