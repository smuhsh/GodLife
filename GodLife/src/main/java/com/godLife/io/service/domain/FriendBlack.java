package com.godLife.io.service.domain;

import java.sql.Date;


//친구블랙리스트 관리 
public class FriendBlack {
	

	//field
	private int friendBlackNo;
	private String nick; // 유저와조인하기위해 닉네임 추가, 매퍼에도 추가 
	private String profileImg;
	private String userEmail;
	private String targetEmail;
	private String fbStatus;
	private String accStatus;
	
	
	public FriendBlack() {
	}
	
	public int getFriendBlackNo() {
		return friendBlackNo;
	}
	public void setFriendBlackNo(int friendBlackNo) {
		this.friendBlackNo = friendBlackNo;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getProfileImg() {
		return profileImg;
	}
	public void setProfileImg(String profileImg) {
		this.profileImg = profileImg;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getTargetEmail() {
		return targetEmail;
	}
	public void setTargetEmail(String targetEmail) {
		this.targetEmail = targetEmail;
	}
	public String getFbStatus() {
		return fbStatus;
	}
	public void setFbStatus(String fbStatus) {
		this.fbStatus = fbStatus;
	}
	public String getAccStatus() {
		return accStatus;
	}
	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}
	
	
	@Override
	public String toString() {
		return "FriendBlack [friendBlackNo=" + friendBlackNo + ", nick=" + nick + ", profileImg=" + profileImg
				+ ", userEmail=" + userEmail + ", targetEmail=" + targetEmail + ", fbStatus=" + fbStatus
				+ ", accStatus=" + accStatus + "]";
	}
	
	
	
	

}
