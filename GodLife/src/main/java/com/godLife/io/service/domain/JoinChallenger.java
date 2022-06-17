package com.godLife.io.service.domain;

public class JoinChallenger {
	private int joinChallengeNo;
	private String email;
	private int challengeNo;
	private double challengePercent;
	private int challengeReward;
	private String challengeRewardFlag;
	private String joinNick;
	private String status;
	private String profile;
	public int getJoinChallengeNo() {
		return joinChallengeNo;
	}
	public String getEmail() {
		return email;
	}
	public int getChallengeNo() {
		return challengeNo;
	}
	public double getChallengePercent() {
		return challengePercent;
	}
	public int getChallengeReward() {
		return challengeReward;
	}
	public String getChallengeRewardFlag() {
		return challengeRewardFlag;
	}
	public void setJoinChallengeNo(int joinChallengeNo) {
		this.joinChallengeNo = joinChallengeNo;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setChallengeNo(int challengeNo) {
		this.challengeNo = challengeNo;
	}
	public void setChallengePercent(double challengePercent) {
		this.challengePercent = challengePercent;
	}
	public void setChallengeReward(int challengeReward) {
		this.challengeReward = challengeReward;
	}
	public void setChallengeRewardFlag(String challengeRewardFlag) {
		this.challengeRewardFlag = challengeRewardFlag;
	}
	public String getJoinNick() {
		return joinNick;
	}
	public void setJoinNick(String joinNick) {
		this.joinNick = joinNick;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getProfile() {
		return profile;
	}
	public void setProfile(String profile) {
		this.profile = profile;
	}
	@Override
	public String toString() {
		return "JoinChallenger [joinChallengeNo=" + joinChallengeNo + ", email=" + email + ", challengeNo="
				+ challengeNo + ", challengePercent=" + challengePercent + ", challengeReward=" + challengeReward
				+ ", challengeRewardFlag=" + challengeRewardFlag + ", joinNick=" + joinNick + ", status=" + status
				+ ", profile=" + profile + "]";
	}
	
	
	
	
	
	
	
	
	
	
}
