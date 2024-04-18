package com.cricshot.services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cricshot.config.AppConstants;
import com.cricshot.entities.Role;
import com.cricshot.entities.User;
import com.cricshot.exceptions.InvalidPasswordException;
import com.cricshot.exceptions.ResourceNotFoundException;
import com.cricshot.payloads.RoleDto;
import com.cricshot.payloads.UserDto;
import com.cricshot.repositories.RoleRepository;
import com.cricshot.repositories.UserRepository;
@Service
public class UserServiceImpl implements UserService {
     @Autowired
	private UserRepository userRepository;
     @Autowired
     private PasswordEncoder passwordEncoder;
     @Autowired
     private RoleRepository roleRepository;
	
	@Override
	public UserDto createUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		User savedUser=userRepository.save(user);
		return this.userToDto(savedUser);
	}

	@Override
	public UserDto getUserById(Integer userId) {
     User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));
	return this.userToDto(user);
	}

	@Override
	public List<UserDto> getAllUsers() {
		List<User> users=this.userRepository.findAll();
		List<UserDto> userDtos=users.stream().map(user ->this.userToDto(user)).collect(Collectors.toList());
		return userDtos;
	}
	
	public User dtoToUser(UserDto userDto) {
		User user=new User();
		user.setId(userDto.getId());
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setPassword(userDto.getPassword());
		for (RoleDto roleDto : userDto.getRoles()) {
            user.getRoles().add(roleDto.toRole());
        }
		return user;
	}
	
	public UserDto userToDto(User user) {
		UserDto userDto=new UserDto();
		userDto.setId(user.getId());
		userDto.setName(user.getName());
		userDto.setEmail(user.getEmail());
		userDto.setPassword(user.getPassword());
		 for (Role role : user.getRoles()) {
	            userDto.getRoles().add(RoleDto.fromRole(role));
	        }
		return userDto;
	}

	@Override
	public UserDto registerNewUser(UserDto userDto) {
		User user=this.dtoToUser(userDto);
		//encoded the password
		user.setPassword(this.passwordEncoder.encode(user.getPassword()));
		//roles 
		Role role=this.roleRepository.findById(AppConstants.NORMAL_USER).get();
	    user.getRoles().add(role);
	    User newUser=this.userRepository.save(user);
	    return this.userToDto(newUser);
	}

	@Override
	public void changePassword(Integer userId, String oldPassword, String newPassword) {
	User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","id",userId));

   // Validate old password
    if (!passwordEncoder.matches(oldPassword, user.getPassword())) {
    throw new InvalidPasswordException("Old password is incorrect");
    }

    // Update password
    user.setPassword(passwordEncoder.encode(newPassword));
    userRepository.save(user);
		
	}

}
