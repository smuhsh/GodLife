package com.godLife.io.service.domain;

import java.sql.Date;

public class OperatorEvents {
	
	//Field
	private int eventNo;
	private String eventTitle;
	private String thumbnailImg;
	private String detail;
	private String eventImg;
	private Date roullJoinPoint;
	private Date regDate;
	
	//Constructor
	public void OperatorEvents() {
		
	}
	
	//Method
	public int getEventNo() {
		return eventNo;
	}

	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
	}

	public String getEventTitle() {
		return eventTitle;
	}

	public void setEventTitle(String eventTitle) {
		this.eventTitle = eventTitle;
	}

	public String getThumbnailImg() {
		return thumbnailImg;
	}

	public void setThumbnailImg(String thumbnailImg) {
		this.thumbnailImg = thumbnailImg;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

	public String getEventImg() {
		return eventImg;
	}

	public void setEventImg(String eventImg) {
		this.eventImg = eventImg;
	}

	public Date getRoullJoinPoint() {
		return roullJoinPoint;
	}

	public void setRoullJoinPoint(Date roullJoinPoint) {
		this.roullJoinPoint = roullJoinPoint;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "OperatorEvents [eventNo=" + eventNo + ", eventTitle=" + eventTitle + ", thumbnailImg=" + thumbnailImg
				+ ", detail=" + detail + ", eventImg=" + eventImg + ", roullJoinPoint=" + roullJoinPoint + ", regDate="
				+ regDate + "]";
	}

	public OperatorEvents(int eventNo, String eventTitle, String thumbnailImg, String detail, String eventImg,
			Date roullJoinPoint, Date regDate) {
		super();
		this.eventNo = eventNo;
		this.eventTitle = eventTitle;
		this.thumbnailImg = thumbnailImg;
		this.detail = detail;
		this.eventImg = eventImg;
		this.roullJoinPoint = roullJoinPoint;
		this.regDate = regDate;
	}
	
}
