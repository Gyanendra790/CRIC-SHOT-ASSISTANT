package com.cricshot.payloads;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;

public class ImageDto {

	private Date imageAddedDate;
    private String confidence;
    private String predicted_shot;
    private byte[] result_image_1;
    private byte[] result_image_2;
    private byte[] result_image_3;
    UserDto user;
	public ImageDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Date getImageAddedDate() {
		return imageAddedDate;
	}
	public void setImageAddedDate(Date imageAddedDate) {
		this.imageAddedDate = imageAddedDate;
	}
	public String getConfidence() {
		return confidence;
	}
	public void setConfidence(String confidence) {
		this.confidence = confidence;
	}
	public String getPredicted_shot() {
		return predicted_shot;
	}
	public void setPredicted_shot(String predicted_shot) {
		this.predicted_shot = predicted_shot;
	}
	public byte[] getResult_image_1() {
		return result_image_1;
	}
	public void setResult_image_1(byte[] result_image_1) {
		this.result_image_1 = result_image_1;
	}
	public byte[] getResult_image_2() {
		return result_image_2;
	}
	public void setResult_image_2(byte[] result_image_2) {
		this.result_image_2 = result_image_2;
	}
	public byte[] getResult_image_3() {
		return result_image_3;
	}
	public void setResult_image_3(byte[] result_image_3) {
		this.result_image_3 = result_image_3;
	}
	public UserDto getUser() {
		return user;
	}
	public void setUser(UserDto user) {
		this.user = user;
	}
	
    
	
}
