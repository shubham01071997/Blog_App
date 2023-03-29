package com.BlogApplication.services;

import java.util.List;

import com.BlogApplication.Entity.User;
import com.BlogApplication.dto.UserDto;

public interface UserService {
	public UserDto RegisterUser(UserDto user);
	public UserDto createUser(UserDto user);
	public UserDto updateUser(UserDto user,Integer userid);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUser();
	public void deleteById(Integer userId);

}
