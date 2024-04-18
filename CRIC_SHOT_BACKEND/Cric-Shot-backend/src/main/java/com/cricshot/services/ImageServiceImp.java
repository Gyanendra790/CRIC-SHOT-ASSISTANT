package com.cricshot.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cricshot.entities.Image;
import com.cricshot.entities.User;
import com.cricshot.exceptions.ResourceNotFoundException;
import com.cricshot.payloads.ImageDto;
import com.cricshot.payloads.UserDto;
import com.cricshot.repositories.ImageRepository;
import com.cricshot.repositories.UserRepository;

@Service
public class ImageServiceImp implements ImageService {
	@Autowired
	private ImageRepository imageRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public ImageDto createImage(ImageDto imageDto, Integer userId) {
		User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User id",userId));
		Image image=this.dtoToPost(imageDto);
		image.setImageAddedDate(new Date());
		image.setUser(user);
		
		Image newImage=this.imageRepository.save(image);
		ImageDto imageDto1=this.postToDto(newImage);
		return imageDto1;
	}

	@Override
	public ImageDto getImageById(Integer imageId) {
		Image image=this.imageRepository.findById(imageId).orElseThrow(()->new ResourceNotFoundException("Image","id",imageId));
		return this.postToDto(image);
	}

	@Override
	public List<ImageDto> getAllImage() {
		List<Image> images=this.imageRepository.findAll();
		List<ImageDto> postDtos=images.stream().map(image ->this.postToDto(image)).collect(Collectors.toList());
		return postDtos;
	}

	@Override
	public List<ImageDto> getImageByUser(Integer userId) {
		User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User id",userId));
		List<Image> images=this.imageRepository.findByUser(user);
		List<ImageDto> imageDtos=images.stream().map(image ->this.postToDto(image)).collect(Collectors.toList());
		return imageDtos;
	}
	
	
	private Image dtoToPost(ImageDto imageDto) {
		Image image=new Image();
		image.setConfidence(imageDto.getConfidence());
		image.setPredicted_shot(imageDto.getPredicted_shot());
		image.setResult_image_1(imageDto.getResult_image_1());
		image.setResult_image_2(imageDto.getResult_image_2());
		image.setResult_image_3(imageDto.getResult_image_3());
		image.setImageAddedDate(imageDto.getImageAddedDate());
        if (imageDto.getUser() != null) {
            User user = new User();
            user.setId(imageDto.getUser().getId());
            user.setName(imageDto.getUser().getName());
            user.setEmail(imageDto.getUser().getEmail());
            user.setPassword(imageDto.getUser().getPassword());
            image.setUser(user);
        }
		return image;
	}
	
	private ImageDto postToDto(Image image) {
		ImageDto imageDto=new ImageDto();
        imageDto.setImageAddedDate(image.getImageAddedDate());
        imageDto.setConfidence(image.getConfidence());
        imageDto.setPredicted_shot(image.getPredicted_shot());
        imageDto.setResult_image_1(image.getResult_image_1());
        imageDto.setResult_image_2(image.getResult_image_2());
        imageDto.setResult_image_3(image.getResult_image_3());
        
       if (image.getUser() != null) {
            UserDto userDto = new UserDto();
            userDto.setId(image.getUser().getId());
            userDto.setName(image.getUser().getName());
            userDto.setEmail(image.getUser().getEmail());
            userDto.setPassword(image.getUser().getPassword());
            imageDto.setUser(userDto);
        }
        
        
		return imageDto;
	}

	 @Override
	    public List<ImageDto> findLatest4ImagesByUserId(Integer userId) {
	        List<Image> images = imageRepository.findTop4ByUserIdOrderByImageAddedDateDesc(userId);
	        List<ImageDto> imageDtos=images.stream().map(image ->this.postToDto(image)).collect(Collectors.toList());
			return imageDtos;

	    }
	
}
