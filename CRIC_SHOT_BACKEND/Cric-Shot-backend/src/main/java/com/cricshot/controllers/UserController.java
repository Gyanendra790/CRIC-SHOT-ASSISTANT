package com.cricshot.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cricshot.exceptions.InvalidPasswordException;
import com.cricshot.exceptions.ResourceNotFoundException;
import com.cricshot.payloads.ChangePasswordDto;
import com.cricshot.payloads.UserDto;
import com.cricshot.services.UserService;

@RestController
@RequestMapping("/api/v1/auth/users")
public class UserController {
     @Autowired
	private UserService userService;
	
	 //POST-create user:
     @PostMapping("/")
     public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto)
     {
    	 UserDto createUserDto=this.userService.createUser(userDto);
    	 return new ResponseEntity<>(createUserDto,HttpStatus.CREATED);
     }
     
     //GET user:
     @GetMapping("/")
     public ResponseEntity<List<UserDto>> getAllUsers()
     {
    	 return ResponseEntity.ok(this.userService.getAllUsers());
     }
	
     //GET single user
     @GetMapping("/{userId}")
     public ResponseEntity<UserDto> getSingleUser(@PathVariable Integer userId)
     {
    	 return ResponseEntity.ok(this.userService.getUserById(userId));
     }
     
     @PostMapping("/{userId}/change-password")
     public ResponseEntity<?> changePassword(@PathVariable("userId") Integer userId,
                                             @RequestParam("oldPassword") String oldPassword,
                                             @RequestParam("newPassword") String newPassword) {
         try {
             userService.changePassword(userId, oldPassword, newPassword);
             return ResponseEntity.ok().body("Password changed successfully");
         } catch (ResourceNotFoundException e) {
             return ResponseEntity.notFound().build();
         } catch (InvalidPasswordException e) {
             return ResponseEntity.badRequest().body(e.getMessage());
         }
     }
}
