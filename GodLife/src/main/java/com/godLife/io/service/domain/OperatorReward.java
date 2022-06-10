package com.godLife.io.service.domain;

public class OperatorReward {
	
	//Field
	private int rewardNo;
	private int eventNo;
	private int rewardPoint;
	
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

	public int getRewardPoint() {
		return rewardPoint;
	}

	public void setRewardPoint(int rewardPoint) {
		this.rewardPoint = rewardPoint;
	}

	@Override
	public String toString() {
		return "OperatorReward [rewardNo=" + rewardNo + ", eventNo=" + eventNo + ", rewardPoint=" + rewardPoint
				+ ", getRewardNo()=" + getRewardNo() + ", getEventNo()=" + getEventNo() + ", getRewardPoint()="
				+ getRewardPoint() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode() + ", toString()="
				+ super.toString() + "]";
	}

	public OperatorReward(int rewardNo, int eventNo, int rewardPoint) {
		super();
		this.rewardNo = rewardNo;
		this.eventNo = eventNo;
		this.rewardPoint = rewardPoint;
	}

}
