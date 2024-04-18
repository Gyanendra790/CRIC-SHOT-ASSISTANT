package com.cricshot.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cricshot.entities.Image;
import com.cricshot.entities.User;
import com.cricshot.entities.Video;

public interface VideoRepository extends JpaRepository<Video,Integer> {
	
	List<Video> findByUser(User user);
	
	@Query(value="SELECT * FROM video WHERE video_user_id=:userId ORDER BY video_added_date DESC LIMIT 1,1",nativeQuery=true)
	Video findTop2ByUserIdOrderByTimestampDesc(Integer userId);
	
	 @Query("SELECT SUM(v.bowled_count) FROM Video v WHERE v.user.id = :userId")
	    int getTotalBowledCountByUserId(@Param("userId") int userId);

	 @Query("SELECT SUM(v.cover_drive_count) FROM Video v WHERE v.user.id = :userId")
	    int getTotalCoverDriveCountByUserId(@Param("userId") int userId);
    
	 @Query("SELECT SUM(v.reverse_sweep_count) FROM Video v WHERE v.user.id = :userId")
	    int getTotalReverseSweepCountByUserId(@Param("userId") int userId);

	 @Query("SELECT SUM(v.pull_count) FROM Video v WHERE v.user.id = :userId")
	    int getTotalPullCountByUserId(@Param("userId") int userId);
    
	 @Query("SELECT SUM(v.defence_count) FROM Video v WHERE v.user.id = :userId")
	    int getTotalDefenceCountByUserId(@Param("userId") int userId);

	 @Query("SELECT COUNT(v) FROM Video v WHERE v.user.id = :userId")
	    int getTotalEntriesByUserId(@Param("userId") int userId);
	 
	 List<Video> findTop4ByUserIdOrderByVideoAddedDateDesc(Integer userId);
    
}
