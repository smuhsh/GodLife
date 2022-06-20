package com.godLife.io.service.domain;

//==>회원정보를 모델링(추상화/캡슐화)한 Bean
public class MyBadge {
	
	//field
	
	private int myBadgeNo;
	private String userEmail;
	private int badgeNo;
	private int actCount;
	private Badge badge;
	//리스트에서 2가지로 구분 하기 위해 넣음 0 : 활동배지, 1 : 관심사 배지
	

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
	public Badge getBadge() {
		return badge;
	}
	public void setBadge(Badge badge) {
		this.badge = badge;
	}
	
	@Override
	public String toString() {
		return "MyBadge [myBadgeNo=" + myBadgeNo + ", userEmail=" + userEmail + ", badgeNo=" + badgeNo + ", actCount="
				+ actCount + ", badge=" + badge + "]";
	}

	




	

}

