package com.godLife.io.service.domain;

//==>회원정보를 모델링(추상화/캡슐화)한 Bean
public class Badge {
	
	//field
	
	private int badgeNo;
	private String badgeName;
	private String badgeImg;
	private int actCount;
	private String grade;
	private String badgeDetail;
	private String status;
	
	///Constructor
	public Badge() {
	}
	public int getBadgeNo() {
		return badgeNo;
	}
	public void setBadgeNo(int badgeNo) {
		this.badgeNo = badgeNo;
	}
	public String getBadgeName() {
		return badgeName;
	}
	public void setBadgeName(String badgeName) {
		this.badgeName = badgeName;
	}
	public String getBadgeImg() {
		return badgeImg;
	}
	public void setBadgeImg(String badgeImg) {
		this.badgeImg = badgeImg;
	}
	public int getActCount() {
		return actCount;
	}
	public void setActCount(int actCount) {
		this.actCount = actCount;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	public String getBadgeDetail() {
		return badgeDetail;
	}
	public void setBadgeDetail(String badgeDetail) {
		this.badgeDetail = badgeDetail;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
	@Override
	public String toString() {
		return "Badge [badgeNo=" + badgeNo + ", badgeName=" + badgeName + ", badgeImg=" + badgeImg + ", actCount="
				+ actCount + ", grade=" + grade + ", badgeDetail=" + badgeDetail + ", status=" + status + "]";
	}
	
	
	



}

