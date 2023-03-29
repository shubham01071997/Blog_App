package com.BlogApplication.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.BlogApplication.Entity.Category;

public class CategoryDto {
	private int categoryId;
	@NotBlank
	@Size(min=4,message="Title must be greater than 4 letter")
	private String categoryTitle;
	@NotBlank
	@Size(min=10,message="Description must be greater than 4 letter")
	private String categoryDescription;
	public int getCategoryId() {
		return categoryId;
	}
	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}
	public String getCategoryTitle() {
		return categoryTitle;
	}
	public void setCategoryTitle(String categoryTitle) {
		this.categoryTitle = categoryTitle;
	}
	public String getCategoryDescription() {
		return categoryDescription;
	}
	public void setCategoryDescription(String categoryDescription) {
		this.categoryDescription = categoryDescription;
	}
	
}
