package com.godLife.io.service.domain;

import java.sql.Date;

public class OperatorJoinEvent {

	//Field
	private int joinEventNo;
	private String userEmail;
	private int eventNo;
	private Date regDate;
	private int rewardPoint;
	
	//Constructor
	public OperatorJoinEvent() {
		
	}
	
	//Method
	public int getJoinEventNo() {
		return joinEventNo;
	}

	public void setJoinEventNo(int joinEventNo) {
		this.joinEventNo = joinEventNo;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public int getEventNo() {
		return eventNo;
	}

	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public int getRewardPoint() {
		return rewardPoint;
	}

	public void setRewardPoint(int rewardPoint) {
		this.rewardPoint = rewardPoint;
	}

	@Override
	public String toString() {
		return "OperatorJoinEvent [joinEventNo=" + joinEventNo + ", userEmail=" + userEmail + ", eventNo=" + eventNo
				+ ", regDate=" + regDate + ", rewardPoint=" + rewardPoint + ", getJoinEventNo()=" + getJoinEventNo()
				+ ", getUserEmail()=" + getUserEmail() + ", getEventNo()=" + getEventNo() + ", getRegDate()="
				+ getRegDate() + ", getRewardPoint()=" + getRewardPoint() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

//	public OperatorJoinEvent(int joinEventNo, String userEmail, int eventNo, Date regDate, int rewardPoint) {
//		super();
//		this.joinEventNo = joinEventNo;
//		this.userEmail = userEmail;
//		this.eventNo = eventNo;
//		this.regDate = regDate;
//		this.rewardPoint = rewardPoint;
//	}
	
}
