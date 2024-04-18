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
import com.cricshot.payloads.VideoDto;
import com.cricshot.services.VideoService;

@RestController
@RequestMapping("/api/v1/auth")
public class VideoController {
	
	@Autowired 
	private VideoService videoService;
	
	 //create
	@PostMapping("/users/{userId}/videos")
	public ResponseEntity<VideoDto> createVideo(
			@RequestBody VideoDto videoDto,
			@PathVariable Integer userId)
	{
		VideoDto createVideo=this.videoService.createVideo(videoDto, userId);
	    return new ResponseEntity<VideoDto>(createVideo,HttpStatus.CREATED);
	}
	
	
	//get by user
	@GetMapping("/users/{userId}/videos")
	public ResponseEntity<List<VideoDto>> getVideosByUser(
			@PathVariable Integer userId)
	{
		List<VideoDto> videos=this.videoService.getVideoByUser(userId);
	    return new ResponseEntity<List<VideoDto>>(videos,HttpStatus.OK);
	}
	
	//Get second last entry
	@GetMapping("/users/{userId}/videos/secondlastentry")
	public ResponseEntity<VideoDto> getSecondLastEntryByUserId(@PathVariable Integer userId)
	{
		VideoDto entry=this.videoService.findSecondLastEntryByUserId(userId);
		if(entry!=null) {
			return ResponseEntity.ok().body(entry); 
		}else {
			return ResponseEntity.notFound().build();
		}
	}
	@GetMapping("users/{userId}/videos/totalEntries")
    public int getTotalEntriesByUserId(@PathVariable int userId) {
        return this.videoService.getTotalEntriesByUserId(userId);
    }

    @GetMapping("users/{userId}/videos/totalBowled")
    public int getTotalBowledCountByUserId(@PathVariable int userId) {
        return this.videoService.getTotalBowledCountByUserId(userId);
    }

    @GetMapping("users/{userId}/videos/totalCoverDrive")
    public int getTotalCoverDriveCountByUserId(@PathVariable int userId) {
        return this.videoService.getTotalCoverDriveCountByUserId(userId);
    }
    
    @GetMapping("users/{userId}/videos/totalReverseSweep")
    public int getTotalReverseSweepCountByUserId(@PathVariable int userId) {
        return this.videoService.getTotalReverseSweepCountByUserId(userId);
    }

    @GetMapping("users/{userId}/videos/totalDefence")
    public int getTotalDefenceCountByUserId(@PathVariable int userId) {
        return this.videoService.getTotalDefenceCountByUserId(userId);
    }

    
    @GetMapping("users/{userId}/videos/totalPull")
    public int getTotalPullCountByUserId(@PathVariable int userId) {
        return this.videoService.getTotalPullCountByUserId(userId);
    }
    
  //latest 4 images
	 @GetMapping("/user/{userId}/latest4Videos")
	    public ResponseEntity<List<VideoDto>> getLatestVideossForUser(@PathVariable Integer userId) {
	        List<VideoDto> videos= videoService.findLatest4VideosByUserId(userId);
	        return new ResponseEntity<List<VideoDto>>(videos,HttpStatus.OK);
	    }
    
    }
