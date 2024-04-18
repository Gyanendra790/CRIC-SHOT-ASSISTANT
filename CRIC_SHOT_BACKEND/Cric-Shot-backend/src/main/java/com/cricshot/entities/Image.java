package com.cricshot.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="image")
public class Image {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
	private Date imageAddedDate;
    private String confidence;
    private String predicted_shot;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] result_image_1;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] result_image_2;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] result_image_3;
    @ManyToOne
    @JoinColumn(name = "image_user_id")
    private User user;
	public Image() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
    
	
}
