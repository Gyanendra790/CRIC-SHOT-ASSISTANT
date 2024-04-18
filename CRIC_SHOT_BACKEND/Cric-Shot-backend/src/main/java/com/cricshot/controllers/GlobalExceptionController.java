package com.cricshot.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.cricshot.exceptions.ApiException;
import com.cricshot.exceptions.ResourceNotFoundException;
import com.cricshot.payloads.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionController {
    @ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		String message=ex.getMessage();
		ApiResponse apiResponse=new ApiResponse(message,false);
		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.NOT_FOUND);
	}
    
    @ExceptionHandler(ApiException.class)
   	public ResponseEntity<ApiResponse> handleApiExceptionHandler(ApiException ex){
   		String message=ex.getMessage();
   		ApiResponse apiResponse=new ApiResponse(message,false);
   		return new ResponseEntity<ApiResponse>(apiResponse,HttpStatus.BAD_REQUEST);
   	}
	
}
