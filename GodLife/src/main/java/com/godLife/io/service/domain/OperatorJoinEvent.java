package com.godLife.io.service.domain;

import java.sql.Date;

public class OperatorJoinEvent {
	
	private int joinEventNo;
	private String userEmail;
	private String rewardNo;
	private Date regDate;
	
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

	public String getRewardNo() {
		return rewardNo;
	}

	public void setRewardNo(String rewardNo) {
		this.rewardNo = rewardNo;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "OperatorJoinEvent [joinEventNo=" + joinEventNo + ", userEmail=" + userEmail + ", rewardNo=" + rewardNo
				+ ", regDate=" + regDate + "]";
	}

	public OperatorJoinEvent(int joinEventNo, String userEmail, String rewardNo, Date regDate) {
		super();
		this.joinEventNo = joinEventNo;
		this.userEmail = userEmail;
		this.rewardNo = rewardNo;
		this.regDate = regDate;
	}


}
