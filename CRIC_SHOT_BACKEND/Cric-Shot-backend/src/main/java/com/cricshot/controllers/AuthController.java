package com.cricshot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cricshot.entities.Role;
import com.cricshot.entities.User;
import com.cricshot.exceptions.ApiException;
import com.cricshot.payloads.JwtAuthRequest;
import com.cricshot.payloads.RoleDto;
import com.cricshot.payloads.UserDto;
import com.cricshot.security.JwtAuthResponse;
import com.cricshot.security.JwtTokenHelper;
import com.cricshot.services.UserService;
import com.cricshot.services.UserServiceImpl;

@RestController
@RequestMapping("/api/v1/auth/")
public class AuthController {
	@Autowired
	private JwtTokenHelper jwtTokenHelper;
	@Autowired
	private UserDetailsService userDetailsService;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private UserService userService;
	@Autowired
	private UserServiceImpl userServiceImpl;
	
	
	@PostMapping("/login")
	public ResponseEntity<JwtAuthResponse> createToken(
			                            @RequestBody JwtAuthRequest request )throws Exception{
		this.authenticate(request.getUsername(),request.getPassword());
		UserDetails userDetails=this.userDetailsService.loadUserByUsername(request.getUsername());
		String token=this.jwtTokenHelper.generateToken(userDetails);
		JwtAuthResponse response=new JwtAuthResponse();
	    response.setToken(token);
	    response.setUser(this.userServiceImpl.userToDto((User)userDetails));
	    
	    
	    return new ResponseEntity<JwtAuthResponse>(response,HttpStatus.OK);
	}

	private void authenticate(String username, String password)throws Exception {
		UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password); 
		
		try {
		this.authenticationManager.authenticate(authenticationToken);
		}catch(BadCredentialsException e) {
			System.out.println("Invalid Details!!");
			throw new ApiException("Invalid username or password");
		}
		
		}
	
	//register new user API
	@PostMapping("/register")
	public ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
		UserDto registeredUser=this.userService.registerNewUser(userDto);
		return new ResponseEntity<UserDto>(registeredUser,HttpStatus.CREATED);
	}

}
