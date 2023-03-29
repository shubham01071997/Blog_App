package com.BlogApplication.services;

import java.util.List;

import com.BlogApplication.Entity.Post;
import com.BlogApplication.dto.PostDto;
import com.BlogApplication.dto.PostResponse;

public interface Postservice {
	public PostDto save(PostDto postdto,Integer categoryId,Integer UserId);
	public PostDto update(PostDto postdto,Integer postId);
	public void delete(Integer postId);
	public PostResponse findAll(Integer pageNumber,Integer pageSize,String sortBy,String sortDir);
	public PostDto findById(Integer postId);
	public List<PostDto>getPostByCategory(Integer categoryId);
	public List<PostDto>getPostByUser(Integer UserId);
	public List<PostDto> serachPosts(String keyword);
	

}
