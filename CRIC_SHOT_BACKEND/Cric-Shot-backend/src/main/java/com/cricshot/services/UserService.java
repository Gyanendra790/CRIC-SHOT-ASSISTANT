package com.cricshot.services;

import java.util.List;

import com.cricshot.payloads.UserDto;

public interface UserService {
    UserDto registerNewUser(UserDto userDto);
	UserDto createUser(UserDto userDto);
	UserDto getUserById(Integer userId);
	List<UserDto> getAllUsers();
	void changePassword(Integer userId, String oldPassword, String newPassword);
	
}
