package com.BlogApplication.security;

import com.BlogApplication.dto.UserDto;

public class jwtAuthResponse {
	private String token;
	private UserDto user;

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
}
