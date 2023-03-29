package com.BlogApplication.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.BlogApplication.dto.AprResponse;
import com.BlogApplication.dto.CommentDto;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	
	@Autowired
	private com.BlogApplication.services.comentService comentService;
	
	@PostMapping("/add/{postId}")
	public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto CommentDto,
			@PathVariable Integer postId){
		CommentDto save = comentService.save(CommentDto, postId);
		return new ResponseEntity<CommentDto>(save,HttpStatus.CREATED);
	}
	
	@DeleteMapping("/delete/{commentId}")
	public ResponseEntity<AprResponse> delete(@PathVariable Integer commentId){
		comentService.delete(commentId);
		return new ResponseEntity<AprResponse>(new AprResponse("comment delete successfully",true),HttpStatus.OK);	
	}
}
