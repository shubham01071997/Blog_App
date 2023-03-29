package com.BlogApplication.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogApplication.Entity.Category;
import com.BlogApplication.Exception.ResourceNotFoundException;
import com.BlogApplication.Repository.CategoryRepository;
import com.BlogApplication.dto.CategoryDto;
import com.BlogApplication.services.CategoryService;
@Service
public class categoryServicesImpl implements CategoryService {
	
	@Autowired
    private CategoryRepository CategoryRepo;
	
	@Autowired
	private ModelMapper moddelMapper;
	
	@Override
	public CategoryDto save(CategoryDto CategoryDto) {
		Category cat= moddelMapper.map(CategoryDto,Category.class);
		Category save = CategoryRepo.save(cat);
		return moddelMapper.map(save,CategoryDto.class);
	}

	@Override
	public CategoryDto update(CategoryDto CategoryDto, Integer categoryId) {
		Category cats = CategoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
		cats.setCategoryTitle(CategoryDto.getCategoryTitle());
		cats.setCategoryDescription(CategoryDto.getCategoryDescription());
		Category update = CategoryRepo.save(cats);
		return moddelMapper.map(update,CategoryDto.class);
	}

	@Override
	public CategoryDto getCategoryById(Integer categoryId) {
		Category findbycategory = CategoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
		return moddelMapper.map(findbycategory,CategoryDto.class);
	}

	@Override
	public void delete(Integer categoryId) {
		Category findbycategory = CategoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
		 CategoryRepo.deleteById(findbycategory.getCategoryId());
	}

	@Override
	public List<CategoryDto> getAllCategory() {
		 List<Category> findAll = CategoryRepo.findAll();
		 List<CategoryDto> collect = findAll.stream().map(category->moddelMapper.map(category,CategoryDto.class)).collect(Collectors.toList());
		return collect;
	}

}
