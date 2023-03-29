package com.BlogApplication.services;

import com.BlogApplication.dto.CommentDto;

public interface comentService {
	public CommentDto save(CommentDto commentDto,Integer postId);
	public void delete(Integer commentId);

}
