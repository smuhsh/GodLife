package com.godLife.io.service.domain;

import java.sql.Date;

public class CertiImg {
	private int certiImgNo;
	private int challengeNo;
	private String email;
	private Date CertiImgRegDate;
	private int like;
	private int dislike;
	private int carteg;
	private String status;
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
		return CertiImgRegDate;
	}
	public int getLike() {
		return like;
	}
	public int getDislike() {
		return dislike;
	}
	public int getCarteg() {
		return carteg;
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
		CertiImgRegDate = certiImgRegDate;
	}
	public void setLike(int like) {
		this.like = like;
	}
	public void setDislike(int dislike) {
		this.dislike = dislike;
	}
	public void setCarteg(int carteg) {
		this.carteg = carteg;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "CertiImg [certiImgNo=" + certiImgNo + ", challengeNo=" + challengeNo + ", email=" + email
				+ ", CertiImgRegDate=" + CertiImgRegDate + ", like=" + like + ", dislike=" + dislike + ", carteg="
				+ carteg + ", status=" + status + "]";
	}
	
	
	
	
	
}
