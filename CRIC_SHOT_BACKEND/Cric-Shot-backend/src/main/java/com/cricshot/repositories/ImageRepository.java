package com.cricshot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cricshot.entities.Image;
import com.cricshot.entities.User;

public interface ImageRepository extends JpaRepository<Image,Integer> {
	
	List<Image> findByUser(User user);
	
	//getting latest 4 images of a particular user
	
	 List<Image> findTop4ByUserIdOrderByImageAddedDateDesc(Integer userId);

}
