package com.godLife.io.service.domain;

public class OperatorReward {
	
	private int rewardNo;
	private int eventNo;
	private char reward;
	
	//Constructor
	public OperatorReward() {
		
	}
	
	//Method
	public int getRewardNo() {
		return rewardNo;
	}

	public void setRewardNo(int rewardNo) {
		this.rewardNo = rewardNo;
	}

	public int getEventNo() {
		return eventNo;
	}

	public void setEventNo(int eventNo) {
		this.eventNo = eventNo;
	}

	public char getReward() {
		return reward;
	}

	public void setReward(char reward) {
		this.reward = reward;
	}

	@Override
	public String toString() {
		return "OperatorReward [rewardNo=" + rewardNo + ", eventNo=" + eventNo + ", reward=" + reward + "]";
	}

	public OperatorReward(int rewardNo, int eventNo, char reward) {
		super();
		this.rewardNo = rewardNo;
		this.eventNo = eventNo;
		this.reward = reward;
	}
	

}
