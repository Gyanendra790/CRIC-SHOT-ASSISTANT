package com.cricshot.payloads;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class VideoDto {
	private Date videoAddedDate;
    private int shots_played;
    private List<String> shot_sequence=new ArrayList<>();
    private int bowled_count;
    private int cover_drive_count;
    private int defence_count;
    private int pull_count;
    private int reverse_sweep_count;
    private double bowled_avg_prob;
    private double cover_drive_avg_prob;
    private double defence_avg_prob;
    private double pull_avg_prob;
    private double reverse_sweep_avg_prob;
    private double bowled_shot_runs;
    private double cover_drive_shot_runs;
    private double defence_shot_runs;
    private double pull_shot_runs;
    private double reverse_sweep_shot_runs;
    private String better_shot;
    private String weak_shot;
    private byte[] predicted_video;
	UserDto user;
	public VideoDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Date getVideoAddedDate() {
		return videoAddedDate;
	}
	public void setVideoAddedDate(Date videoAddedDate) {
		this.videoAddedDate = videoAddedDate;
	}
	public int getShots_played() {
		return shots_played;
	}
	public void setShots_played(int shots_played) {
		this.shots_played = shots_played;
	}
	public List<String> getShot_sequence() {
		return shot_sequence;
	}
	public void setShot_sequence(List<String> shot_sequence) {
		this.shot_sequence = shot_sequence;
	}
	public int getBowled_count() {
		return bowled_count;
	}
	public void setBowled_count(int bowled_count) {
		this.bowled_count = bowled_count;
	}
	public int getCover_drive_count() {
		return cover_drive_count;
	}
	public void setCover_drive_count(int cover_drive_count) {
		this.cover_drive_count = cover_drive_count;
	}
	public int getDefence_count() {
		return defence_count;
	}
	public void setDefence_count(int defence_count) {
		this.defence_count = defence_count;
	}
	public int getPull_count() {
		return pull_count;
	}
	public void setPull_count(int pull_count) {
		this.pull_count = pull_count;
	}
	public int getReverse_sweep_count() {
		return reverse_sweep_count;
	}
	public void setReverse_sweep_count(int reverse_sweep_count) {
		this.reverse_sweep_count = reverse_sweep_count;
	}
	public double getBowled_avg_prob() {
		return bowled_avg_prob;
	}
	public void setBowled_avg_prob(double bowled_avg_prob) {
		this.bowled_avg_prob = bowled_avg_prob;
	}
	public double getCover_drive_avg_prob() {
		return cover_drive_avg_prob;
	}
	public void setCover_drive_avg_prob(double cover_drive_avg_prob) {
		this.cover_drive_avg_prob = cover_drive_avg_prob;
	}
	public double getDefence_avg_prob() {
		return defence_avg_prob;
	}
	public void setDefence_avg_prob(double defence_avg_prob) {
		this.defence_avg_prob = defence_avg_prob;
	}
	public double getPull_avg_prob() {
		return pull_avg_prob;
	}
	public void setPull_avg_prob(double pull_avg_prob) {
		this.pull_avg_prob = pull_avg_prob;
	}
	public double getReverse_sweep_avg_prob() {
		return reverse_sweep_avg_prob;
	}
	public void setReverse_sweep_avg_prob(double reverse_sweep_avg_prob) {
		this.reverse_sweep_avg_prob = reverse_sweep_avg_prob;
	}
	public double getBowled_shot_runs() {
		return bowled_shot_runs;
	}
	public void setBowled_shot_runs(double bowled_shot_runs) {
		this.bowled_shot_runs = bowled_shot_runs;
	}
	public double getCover_drive_shot_runs() {
		return cover_drive_shot_runs;
	}
	public void setCover_drive_shot_runs(double cover_drive_shot_runs) {
		this.cover_drive_shot_runs = cover_drive_shot_runs;
	}
	public double getDefence_shot_runs() {
		return defence_shot_runs;
	}
	public void setDefence_shot_runs(double defence_shot_runs) {
		this.defence_shot_runs = defence_shot_runs;
	}
	public double getPull_shot_runs() {
		return pull_shot_runs;
	}
	public void setPull_shot_runs(double pull_shot_runs) {
		this.pull_shot_runs = pull_shot_runs;
	}
	public double getReverse_sweep_shot_runs() {
		return reverse_sweep_shot_runs;
	}
	public void setReverse_sweep_shot_runs(double reverse_sweep_shot_runs) {
		this.reverse_sweep_shot_runs = reverse_sweep_shot_runs;
	}
	public String getBetter_shot() {
		return better_shot;
	}
	public void setBetter_shot(String better_shot) {
		this.better_shot = better_shot;
	}
	public String getWeak_shot() {
		return weak_shot;
	}
	public void setWeak_shot(String weak_shot) {
		this.weak_shot = weak_shot;
	}
	public byte[] getPredicted_video() {
		return predicted_video;
	}
	public void setPredicted_video(byte[] predicted_video) {
		this.predicted_video = predicted_video;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	
	
	
}
