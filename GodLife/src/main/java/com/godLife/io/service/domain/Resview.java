package com.godLife.io.service.domain;

import java.sql.Date;

public class Resview {
	private int resviewNo;
	private int certiImgNo;
	private String email;
	private String comment;
	private Date commentRegDate;
	private String status;
	public int getResviewNo() {
		return resviewNo;
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
	public Date getCommentRegDate() {
		return commentRegDate;
	}
	public String getStatus() {
		return status;
	}
	public void setResviewNo(int resviewNo) {
		this.resviewNo = resviewNo;
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
	public void setCommentRegDate(Date commentRegDate) {
		this.commentRegDate = commentRegDate;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Resviews [resviewNo=" + resviewNo + ", certiImgNo=" + certiImgNo + ", email=" + email + ", comment="
				+ comment + ", commentRegDate=" + commentRegDate + ", status=" + status + "]";
	}
	
	
}
