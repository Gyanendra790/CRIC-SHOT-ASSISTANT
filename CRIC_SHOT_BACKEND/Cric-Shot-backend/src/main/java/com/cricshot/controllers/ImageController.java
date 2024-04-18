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
import org.springframework.web.bind.annotation.RestController;

import com.cricshot.payloads.ImageDto;
import com.cricshot.services.ImageService;

@RestController
@RequestMapping("/api/v1/auth")
public class ImageController {
	@Autowired
	private ImageService imageService;
	
	    //create
		@PostMapping("/users/{userId}/images")
		public ResponseEntity<ImageDto> createImage(
				@RequestBody ImageDto imageDto,
				@PathVariable Integer userId)
		{
			ImageDto createImage=this.imageService.createImage(imageDto, userId);
		    return new ResponseEntity<ImageDto>(createImage,HttpStatus.CREATED);
		}
		
		
		//get by user
		@GetMapping("/user/{userId}/images")
		public ResponseEntity<List<ImageDto>> getImagesByUser(
				@PathVariable Integer userId)
		{
			List<ImageDto> images=this.imageService.getImageByUser(userId);
		    return new ResponseEntity<List<ImageDto>>(images,HttpStatus.OK);
		}
		
		//latest 4 images
		 @GetMapping("/user/{userId}/latest4Images")
		    public ResponseEntity<List<ImageDto>> getLatestImagesForUser(@PathVariable Integer userId) {
		        List<ImageDto> images= imageService.findLatest4ImagesByUserId(userId);
		        return new ResponseEntity<List<ImageDto>>(images,HttpStatus.OK);
		    }

}
