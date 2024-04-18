package com.cricshot.services;

import java.util.List;

import com.cricshot.payloads.ImageDto;
import com.cricshot.payloads.VideoDto;

public interface VideoService {

	//create
	public VideoDto createVideo(VideoDto videoDto,Integer videoId);
	//Get
	public VideoDto getVideoById(Integer videoId);
	//Get All Video
	public List<VideoDto> getAllVideo();
	//Get All video by user
	List<VideoDto> getVideoByUser(Integer userId);
    //Get Second last entry of the user
	public VideoDto findSecondLastEntryByUserId(Integer userId);
	//Get total bowled count
	public int getTotalBowledCountByUserId(int userId);
	//Get total cover drive count
	public int getTotalCoverDriveCountByUserId(int userId);
	//Get total reverse sweep count
	public int getTotalReverseSweepCountByUserId(int userId);
	//Get total defence count
	public int getTotalDefenceCountByUserId(int userId);
	//Get total Pull count
		public int getTotalPullCountByUserId(int userId);
	//Get total match played
	 public int getTotalEntriesByUserId(int userId);
	//Get latest 4 video data
	    List<VideoDto> findLatest4VideosByUserId(Integer userId);
	
}
