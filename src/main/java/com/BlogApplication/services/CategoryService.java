package com.BlogApplication.services;

import java.util.List;

import com.BlogApplication.dto.CategoryDto;

public interface CategoryService {
	public CategoryDto save(CategoryDto CategoryDto);
	public CategoryDto update(CategoryDto CategoryDto,Integer categoryId);
	public CategoryDto getCategoryById(Integer categoryId);
	public void delete(Integer categoryId);
	public List<CategoryDto> getAllCategory();

}
