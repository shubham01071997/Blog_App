package com.BlogApplication.serviceImpl;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.BlogApplication.Entity.Category;
import com.BlogApplication.Entity.Post;
import com.BlogApplication.Entity.User;
import com.BlogApplication.Exception.ResourceNotFoundException;
import com.BlogApplication.dto.PostDto;
import com.BlogApplication.dto.PostResponse;
import com.BlogApplication.services.Postservice;
import com.BlogApplication.Repository.CategoryRepository;
import com.BlogApplication.Repository.UserRepository;
import com.BlogApplication.Repository.postRepository;

@Service
public class postServicesImpl implements Postservice {

	@Autowired
	private postRepository postRepository;
	
	@Autowired
    private ModelMapper moddelMapper;
	
	@Autowired
	private UserRepository userrepo;
	
	@Autowired
    private CategoryRepository CategoryRepo;
	
	@Override
	public PostDto save(PostDto postdto,Integer categoryId,Integer UserId) {
		User user=userrepo.findById(UserId).orElseThrow(()-> new ResourceNotFoundException("User","Id",UserId));
		Category category = CategoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
		Post post=moddelMapper.map(postdto, Post.class);
		post.setImageName("default.png");
		post.setUser(user);
		post.setCategory(category);
		Post newPost=postRepository.save(post);
		return moddelMapper.map(newPost, PostDto.class);
	}

	@Override
	public PostDto update(PostDto postdto, Integer postId) {
		Post post=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
		post.setTitle(postdto.getTitle());
		post.setContent(postdto.getContent());
		post.setImageName(postdto.getImageName());
		Post updatePost=postRepository.save(post);
		return moddelMapper.map(updatePost,PostDto.class);
	}

	@Override
	public void delete(Integer postId) {
		Post post=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
		postRepository.deleteById(post.getPostId());
	}

	@Override
	public PostResponse findAll(Integer pageNumber,Integer pageSize,String sortBy,String sortDir) {
		 //List<Post> findAll = postRepository.findAll();
		// int pageSize=5;
		 //int pageNumber=1;
		Sort sort=null;
		if(sortDir.equalsIgnoreCase("asc")){
			sort=sort.by(sortBy).ascending();
		}else {
			sort=sort.by(sortBy).descending();
		}
		 Pageable p=PageRequest.of(pageNumber,pageSize,sort);
		 Page<Post> findAll = postRepository.findAll(p);
		 List<Post> content = findAll.getContent();
		 List<PostDto> collect = content.stream().map(post->moddelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		 PostResponse postResponse=new PostResponse();
		 postResponse.setContent(collect);
		 postResponse.setPageNumber(findAll.getNumber());
		 postResponse.setPageSize(findAll.getSize());
		 postResponse.setTotalElement(findAll.getTotalElements());
		 postResponse.setTotalPages(findAll.getTotalPages());
		 postResponse.setLastPage(findAll.isLast());
		 return postResponse;
	}

	@Override
	public PostDto findById(Integer postId) {
		Post post=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
		return moddelMapper.map(post,PostDto.class);
	}

	@Override
	public List<PostDto> getPostByCategory(Integer categoryId) {
		Category findbycategory = CategoryRepo.findById(categoryId)
				.orElseThrow(()-> new ResourceNotFoundException("Category","categoryId",categoryId));
		List<Post> posts=postRepository.findByCategory(findbycategory);
		List<PostDto> collect = posts.stream().map(post->moddelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<PostDto> getPostByUser(Integer UserId) {
		User user=userrepo.findById(UserId).orElseThrow(()-> new ResourceNotFoundException("User","Id",UserId));
		List<Post> posts=postRepository.findByUser(user);
		List<PostDto> collect = posts.stream().map(post->moddelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return collect;
	}

	@Override
	public List<PostDto> serachPosts(String keyword) {
		List<Post> posts = postRepository.searchByTitle("%"+keyword+"%");
		List<PostDto> collect = posts.stream().map(post->moddelMapper.map(post,PostDto.class)).collect(Collectors.toList());
		return collect;
	}
}
