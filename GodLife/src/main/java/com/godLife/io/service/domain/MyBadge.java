package com.godLife.io.service.domain;

//==>회원정보를 모델링(추상화/캡슐화)한 Bean
public class MyBadge {
	
	//field
	
	private int myBadgeNo;
	private String userEmail;
	private int badgeNo;
	private int actCount;
	
	
	///Constructor
	public MyBadge() {
	}
	
	public int getMyBadgeNo() {
		return myBadgeNo;
	}
	public void setMyBadgeNo(int myBadgeNo) {
		this.myBadgeNo = myBadgeNo;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public int getBadgeNo() {
		return badgeNo;
	}
	public void setBadgeNo(int badgeNo) {
		this.badgeNo = badgeNo;
	}
	public int getActCount() {
		return actCount;
	}
	public void setActCount(int actCount) {
		this.actCount = actCount;
	}



	
	///Method
	@Override
	public String toString() {
		return "MyBadge [myBadgeNo=" + myBadgeNo + ", userEmail=" + userEmail + ", badgeNo=" + badgeNo + ", actCount="
				+ actCount + "]";
	}


}

