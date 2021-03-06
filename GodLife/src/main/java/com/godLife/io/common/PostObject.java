package com.godLife.io.common;



public class PostObject {
	private int certiImgNo;
	private int reviewNo;
	private String comment;
	private String status;
	private int currentPage;
	private String certiImgOpt;
	private String userEmail;
	public int getCertiImgNo() {
		return certiImgNo;
	}
	public int getReviewNo() {
		return reviewNo;
	}
	public String getComment() {
		return comment;
	}
	public String getStatus() {
		return status;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCertiImgNo(int certiImgNo) {
		this.certiImgNo = certiImgNo;
	}
	public void setReviewNo(int reviewNo) {
		this.reviewNo = reviewNo;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	
	public String getCertiImgOpt() {
		return certiImgOpt;
	}
	public void setCertiImgOpt(String certiImgOpt) {
		this.certiImgOpt = certiImgOpt;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	@Override
	public String toString() {
		return "PostObject [certiImgNo=" + certiImgNo + ", reviewNo=" + reviewNo + ", comment=" + comment + ", status="
				+ status + ", currentPage=" + currentPage + ", certiImgOpt=" + certiImgOpt + ", userEmail=" + userEmail
				+ "]";
	}
	


	
	
	
	
	
	
	
}
