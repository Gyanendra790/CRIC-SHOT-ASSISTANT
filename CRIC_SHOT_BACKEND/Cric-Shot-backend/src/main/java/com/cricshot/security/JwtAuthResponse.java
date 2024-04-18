package com.cricshot.security;

import com.cricshot.payloads.UserDto;

public class JwtAuthResponse {
	
	private String token;

	private UserDto user;

	public JwtAuthResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public UserDto getUser() {
		return user;
	}

	public void setUser(UserDto user) {
		this.user = user;
	}

	
	
}
