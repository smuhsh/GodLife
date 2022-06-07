package com.godLife.io.service.domain;

import java.sql.Date;

public class Review {
	private int reviewNo;
	private int certiImgNo;
	private String email;
	private String nick;
	private String comment;
	private Date reviewRegDate;
	private String status;

	public int getReviewNo() {
		return reviewNo;
	}
	public int getCertiImgNo() {
		return certiImgNo;
	}
	public String getEmail() {
		return email;
	}
	public String getComment() {
		return comment;
	}
	public Date getReviewRegDate() {
		return reviewRegDate;
	}
	public String getStatus() {
		return status;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public void setCertiImgNo(int certiImgNo) {
		this.certiImgNo = certiImgNo;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setReviewRegDate(Date reviewRegDate) {
		this.reviewRegDate = reviewRegDate;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	@Override
	public String toString() {
		return "Resview [reviewNo=" + reviewNo + ", certiImgNo=" + certiImgNo + ", email=" + email + ", nick=" + nick
				+ ", comment=" + comment + ", reviewRegDate=" + reviewRegDate + ", status=" + status + "]";
	}
	
	
	
}
