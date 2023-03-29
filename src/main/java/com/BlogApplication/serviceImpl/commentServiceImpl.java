package com.BlogApplication.serviceImpl;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.BlogApplication.Entity.Comment;
import com.BlogApplication.Entity.Post;
import com.BlogApplication.Exception.ResourceNotFoundException;
import com.BlogApplication.Repository.postRepository;
import com.BlogApplication.dto.CommentDto;
import com.BlogApplication.services.comentService;
@Service
public class commentServiceImpl implements comentService{
	
	@Autowired
	private ModelMapper moddelMapper;
	
	@Autowired
	private com.BlogApplication.Repository.CommnetRepository CommnetRepository;
	
	@Autowired
	private postRepository postRepository;
	
	@Override
	public CommentDto save(CommentDto commentDto,Integer postId) {
		Post post=postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","Id",postId));
		
		Comment com = moddelMapper.map(commentDto,Comment.class);
		com.setPost(post);
		Comment save = CommnetRepository.save(com);
		return moddelMapper.map(save,CommentDto.class);
	}

	@Override
	public void delete(Integer commentId) {
		Comment com = CommnetRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("Comment","id",commentId));
		CommnetRepository.deleteById(com.getId());	
	}
}
