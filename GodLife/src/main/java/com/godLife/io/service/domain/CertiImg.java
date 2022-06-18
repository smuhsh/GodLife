package com.godLife.io.service.domain;

import java.sql.Date;

public class CertiImg {
	private int certiImgNo;
	private int challengeNo;
	private String certiImg;
	private String email;
	private String nick;
	private Date certiImgRegDate;
	private int like;
	private int dislike;
	private int categNo;
	private String categName;
	private String status;
	private String certiDate;
	public int getCertiImgNo() {
		return certiImgNo;
	}
	public int getChallengeNo() {
		return challengeNo;
	}
	public String getEmail() {
		return email;
	}
	public Date getCertiImgRegDate() {
		return certiImgRegDate;
	}
	public int getLike() {
		return like;
	}
	public int getDislike() {
		return dislike;
	}
	public int getCategNo() {
		return categNo;
	}
	public String getStatus() {
		return status;
	}
	public void setCertiImgNo(int certiImgNo) {
		this.certiImgNo = certiImgNo;
	}
	public void setChallengeNo(int challengeNo) {
		this.challengeNo = challengeNo;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setCertiImgRegDate(Date certiImgRegDate) {
		this.certiImgRegDate = certiImgRegDate;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public void setDislike(int dislike) {
		this.dislike = dislike;
	}
	public void setCategNo(int categNo) {
		this.categNo = categNo;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCertiImg() {
		return certiImg;
	}
	public void setCertiImg(String certiImg) {
		this.certiImg = certiImg;
	}
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getCertiDate() {
		return certiDate;
	}
	public void setCertiDate(String certiDate) {
		this.certiDate = certiDate;
	}
	public String getCategName() {
		return categName;
	}
	public void setCategName(String categName) {
		this.categName = categName;
	}
	@Override
	public String toString() {
		return "CertiImg [certiImgNo=" + certiImgNo + ", challengeNo=" + challengeNo + ", certiImg=" + certiImg
				+ ", email=" + email + ", nick=" + nick + ", certiImgRegDate=" + certiImgRegDate + ", like=" + like
				+ ", dislike=" + dislike + ", categNo=" + categNo + ", categName=" + categName + ", status=" + status
				+ ", certiDate=" + certiDate + "]";
	}
	

	
	
	
}
