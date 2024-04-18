package com.cricshot.services;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cricshot.entities.Image;
import com.cricshot.entities.User;
import com.cricshot.entities.Video;
import com.cricshot.exceptions.ResourceNotFoundException;
import com.cricshot.payloads.ImageDto;
import com.cricshot.payloads.UserDto;
import com.cricshot.payloads.VideoDto;
import com.cricshot.repositories.ImageRepository;
import com.cricshot.repositories.UserRepository;
import com.cricshot.repositories.VideoRepository;
@Service
public class VideoServiceImpl implements VideoService {
	
	@Autowired
	private VideoRepository videoRepository;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public VideoDto createVideo(VideoDto videoDto, Integer userId) {
		User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User id",userId));
		Video video=this.dtoToVideo(videoDto);
		video.setVideoAddedDate(new Date());
		video.setUser(user);
		
		Video newVideo=this.videoRepository.save(video);
		VideoDto videoDto1=this.videoToDto(newVideo);
		return videoDto1;
	}

	@Override
	public VideoDto getVideoById(Integer videoId) {
		Video video=this.videoRepository.findById(videoId).orElseThrow(()->new ResourceNotFoundException("Video","id",videoId));
		return this.videoToDto(video);
	}

	@Override
	public List<VideoDto> getAllVideo() {
		List<Video> videos=this.videoRepository.findAll();
		List<VideoDto> videoDtos=videos.stream().map(video ->this.videoToDto(video)).collect(Collectors.toList());
		return videoDtos;
	}

	@Override
	public List<VideoDto> getVideoByUser(Integer userId) {
		User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User id",userId));
		List<Video> videos=this.videoRepository.findByUser(user);
		List<VideoDto> videoDtos=videos.stream().map(video ->this.videoToDto(video)).collect(Collectors.toList());
		return videoDtos;
	}
	
	@Override
	public VideoDto findSecondLastEntryByUserId(Integer userId) {
		//User user=this.userRepository.findById(userId).orElseThrow(()->new ResourceNotFoundException("User","User id",userId));
		Video secondLastEntry=this.videoRepository.findTop2ByUserIdOrderByTimestampDesc(userId);
		if(secondLastEntry!=null) {
			return this.videoToDto(secondLastEntry);
		}else {
			return null;
		}
	}
	
	
	private Video dtoToVideo(VideoDto videoDto) {
		Video video=new Video();
		video.setVideoAddedDate(videoDto.getVideoAddedDate());
		video.setBetter_shot(videoDto.getBetter_shot());
		video.setBowled_avg_prob(videoDto.getBowled_avg_prob());
		video.setBowled_count(videoDto.getBowled_count());
		video.setBowled_shot_runs(videoDto.getBowled_shot_runs());
		video.setCover_drive_avg_prob(videoDto.getCover_drive_avg_prob());
		video.setCover_drive_count(videoDto.getCover_drive_count());
		video.setCover_drive_shot_runs(videoDto.getCover_drive_shot_runs());
		video.setDefence_avg_prob(videoDto.getDefence_avg_prob());
		video.setDefence_count(videoDto.getDefence_count());
		video.setDefence_shot_runs(videoDto.getDefence_shot_runs());
		video.setPredicted_video(videoDto.getPredicted_video());
		video.setPull_avg_prob(videoDto.getPull_avg_prob());
		video.setPull_count(videoDto.getPull_count());
		video.setPull_shot_runs(videoDto.getPull_shot_runs());
		video.setReverse_sweep_avg_prob(videoDto.getReverse_sweep_avg_prob());
		video.setReverse_sweep_count(videoDto.getReverse_sweep_count());
		video.setReverse_sweep_shot_runs(videoDto.getReverse_sweep_shot_runs());
		video.setShot_sequence(videoDto.getShot_sequence());
		video.setShots_played(videoDto.getShots_played());
		video.setWeak_shot(videoDto.getWeak_shot());
        if (videoDto.getUser() != null) {
            User user = new User();
            user.setId(videoDto.getUser().getId());
            user.setName(videoDto.getUser().getName());
            user.setEmail(videoDto.getUser().getEmail());
            user.setPassword(videoDto.getUser().getPassword());
            video.setUser(user);
        }
		return video;
	}
	
	private VideoDto videoToDto(Video video) {
		VideoDto videoDto=new VideoDto();
		videoDto.setVideoAddedDate(video.getVideoAddedDate());
		videoDto.setBetter_shot(video.getBetter_shot());
		videoDto.setBowled_avg_prob(video.getBowled_avg_prob());
		videoDto.setBowled_count(video.getBowled_count());
		videoDto.setBowled_shot_runs(video.getBowled_shot_runs());
		videoDto.setCover_drive_avg_prob(video.getCover_drive_avg_prob());
		videoDto.setCover_drive_count(video.getCover_drive_count());
		videoDto.setCover_drive_shot_runs(video.getCover_drive_shot_runs());
		videoDto.setDefence_avg_prob(video.getDefence_avg_prob());
		videoDto.setDefence_count(video.getDefence_count());
		videoDto.setDefence_shot_runs(video.getDefence_shot_runs());
		videoDto.setPredicted_video(video.getPredicted_video());
		videoDto.setPull_avg_prob(video.getPull_avg_prob());
		videoDto.setPull_count(video.getPull_count());
		videoDto.setPull_shot_runs(video.getPull_shot_runs());
		videoDto.setReverse_sweep_avg_prob(video.getReverse_sweep_avg_prob());
		videoDto.setReverse_sweep_count(video.getReverse_sweep_count());
		videoDto.setReverse_sweep_shot_runs(video.getReverse_sweep_shot_runs());
		videoDto.setShot_sequence(video.getShot_sequence());
		videoDto.setShots_played(video.getShots_played());
		videoDto.setWeak_shot(video.getWeak_shot());
        
        
       if (video.getUser() != null) {
            UserDto userDto = new UserDto();
            userDto.setId(video.getUser().getId());
            userDto.setName(video.getUser().getName());
            userDto.setEmail(video.getUser().getEmail());
            userDto.setPassword(video.getUser().getPassword());
            videoDto.setUser(userDto);
        }
        
        
		return videoDto;
	}

	@Override
	public int getTotalBowledCountByUserId(int userId) {
		return this.videoRepository.getTotalBowledCountByUserId(userId);	}

	@Override
	public int getTotalCoverDriveCountByUserId(int userId) {
		return this.videoRepository.getTotalCoverDriveCountByUserId(userId);
	}

	@Override
	public int getTotalReverseSweepCountByUserId(int userId) {
		return this.videoRepository.getTotalReverseSweepCountByUserId(userId);	}

	@Override
	public int getTotalDefenceCountByUserId(int userId) {
		return this.videoRepository.getTotalDefenceCountByUserId(userId);
	}

	@Override
	public int getTotalEntriesByUserId(int userId) {
		return this.videoRepository.getTotalEntriesByUserId(userId);
	}

	@Override
	public int getTotalPullCountByUserId(int userId) {
		return this.videoRepository.getTotalPullCountByUserId(userId);
	}

	 @Override
	    public List<VideoDto> findLatest4VideosByUserId(Integer userId) {
	        List<Video> videos = videoRepository.findTop4ByUserIdOrderByVideoAddedDateDesc(userId);
	        List<VideoDto> videoDtos=videos.stream().map(video ->this.videoToDto(video)).collect(Collectors.toList());
			return videoDtos;

	    }

	

}


