package com.cricshot.services;

import java.util.List;

import com.cricshot.payloads.ImageDto;

public interface ImageService {

	        //create
			public ImageDto createImage(ImageDto imageDto,Integer imageId);
			//Get
			public ImageDto getImageById(Integer imageId);
			//Get All Image
			public List<ImageDto> getAllImage();
			//Get All image by user
			List<ImageDto> getImageByUser(Integer userId);
			//Get latest 4 image data
			    List<ImageDto> findLatest4ImagesByUserId(Integer userId);
			}

	
