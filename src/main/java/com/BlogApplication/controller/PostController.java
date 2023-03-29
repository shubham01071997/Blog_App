package com.BlogApplication.controller;


import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.BlogApplication.config.AppConstant;
import com.BlogApplication.dto.AprResponse;
import com.BlogApplication.dto.PostDto;
import com.BlogApplication.dto.PostResponse;
import com.BlogApplication.services.Postservice;

@RestController
@RequestMapping("/api/post")
public class PostController {
	
	@Autowired
	private Postservice Postservice;
	
	@Autowired
	private com.BlogApplication.services.FileService FileService;
	
	@Value("${project.image}")
	private String path; 
	
	@PostMapping("/user/{userId}/category/{categoryId}/add")
	public ResponseEntity<PostDto> createUser(@Valid @RequestBody PostDto PostDto,
			@PathVariable Integer userId
			,@PathVariable Integer categoryId
			){		
		PostDto create=Postservice.save(PostDto,categoryId,userId);
		return new ResponseEntity<PostDto>(create,HttpStatus.CREATED);	
	}
	
	@DeleteMapping("/delete/{postId}")
	public ResponseEntity<AprResponse> delete(@PathVariable Integer postId){
		Postservice.delete(postId);
		return new ResponseEntity<AprResponse>(new AprResponse("user delete successfully",true),HttpStatus.OK);	
	}
	
	@GetMapping("{postId}")
	public ResponseEntity<PostDto> findByPostId(@PathVariable Integer postId){
		return ResponseEntity.ok(Postservice.findById(postId));
	}
	
	@GetMapping("/getAllPost")
	public ResponseEntity<PostResponse> getAllPost(
			@RequestParam(value="pageNumber",defaultValue=AppConstant.PAGE_NUMBER,required=false)Integer pageNumber,
			@RequestParam(value="pageSize",defaultValue=AppConstant.PAGE_SIZE,required=false) Integer pageSize,
			@RequestParam(value="sortBy",defaultValue=AppConstant.SORT_BY,required=false) String sortBy,
			@RequestParam(value="sortDir",defaultValue=AppConstant.SORT_DIR,required=false) String sortDir
			){
		    PostResponse findAll = Postservice.findAll(pageNumber,pageSize,sortBy,sortDir);
		return new ResponseEntity<PostResponse>(findAll,HttpStatus.OK);
	}
	@GetMapping("/User/{userId}/post")
	public ResponseEntity<List<PostDto>>getPostByUser(@PathVariable Integer  userId){
	    List<PostDto> PostUser=Postservice.getPostByUser(userId);
		return new ResponseEntity<List<PostDto>>(PostUser,HttpStatus.OK);
	}
	
	@GetMapping("/Category/{categoryId}/post")
	public ResponseEntity<List<PostDto>>getPostByCategory(@PathVariable Integer  categoryId){
	    List<PostDto> PostUser=Postservice.getPostByCategory(categoryId);
		return new ResponseEntity<List<PostDto>>(PostUser,HttpStatus.OK);
	}
	
	@PutMapping("/update/{postId}")
	public ResponseEntity<PostDto> update(@PathVariable Integer postId,@RequestBody PostDto PostDto){
		PostDto update = Postservice.update(PostDto,postId);
		return ResponseEntity.ok(update);	
	}
	@GetMapping("/posts/search/{keyword}")
	public ResponseEntity<List<PostDto>> searchPostByTitle(
			@PathVariable("keyword") String keyword){
				return new ResponseEntity<List<PostDto>>(Postservice.serachPosts(keyword),HttpStatus.OK);		
	}
	
	@PostMapping("/image/upload/{postId}")
	public ResponseEntity<PostDto> uploadImage(
			@RequestParam("img") MultipartFile image,
			@PathVariable Integer postId) throws IOException{
		PostDto post = Postservice.findById(postId);
		String Filename=FileService.uploadImage(path, image);
		System.out.println(Filename);
		post.setImageName(Filename);
		PostDto update = Postservice.update(post,postId);
		return new ResponseEntity<PostDto>(update,HttpStatus.OK);
	}
	@GetMapping(value="image/{imageName}",produces=MediaType.IMAGE_JPEG_VALUE)
	public void downloadImage(
			@PathVariable("imageName") String imageName,
	       HttpServletResponse response)throws IOException{
		InputStream resouce = FileService.getResouce(path,imageName);
		response.setContentType(MediaType.IMAGE_JPEG_VALUE);
		System.out.println(resouce);
		System.out.println(response.getOutputStream());
		StreamUtils.copy(resouce,response.getOutputStream());		
	}
}
//
//localhost:8080/api/post/user/4/category/2/add
//localhost:8080/api/post/delete/7
//localhost:8080/api/post/
//localhost:8080/api/post/getAllPost