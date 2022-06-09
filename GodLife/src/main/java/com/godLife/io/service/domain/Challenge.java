package com.godLife.io.service.domain;

import java.sql.Date;
import java.util.List;

public class Challenge {
	
	private int challengeNo;
	private String hostEmail;
	private String challengeTitle;
	private String challengeThumbnailImg;
	private String challengeDetail;
	private String challengeRule;
	private int challengeCategNo;
	private List<String> certiCycle;
	private String startDate;
	private String endDate;
	private String openRange;
	private int joinPoint;
	private Date challengeRegDate;
	private String challengeStatus;
	private int joinCount;
	private List<String> certiDate;
	private int totalCertiCount;
	private String challengeCategName;
	private List<String>certiCycleName;
	private String challengeJoinFlag;
	private String hostNick;
	
	
	
	
	public int getChallengeNo() {
		return challengeNo;
	}
	public String getHostEmail() {
		return hostEmail;
	}
	public String getChallengeTitle() {
		return challengeTitle;
	}
	public String getChallengeThumbnailImg() {
		return challengeThumbnailImg;
	}
	public String getChallengeDetail() {
		return challengeDetail;
	}
	public String getChallengeRule() {
		return challengeRule;
	}
	public int getChallengeCategNo() {
		return challengeCategNo;
	}
	public List<String> getCertiCycle() {
		return certiCycle;
	}
	public String getStartDate() {
		return startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public String getOpenRange() {
		return openRange;
	}
	public int getJoinPoint() {
		return joinPoint;
	}
	public Date getChallengeRegDate() {
		return challengeRegDate;
	}
	public String getChallengeStatus() {
		return challengeStatus;
	}
	public int getJoinCount() {
		return joinCount;
	}
	public List<String> getCertiDate() {
		return certiDate;
	}
	public int getTotalCertiCount() {
		return totalCertiCount;
	}
	public void setChallengeNo(int challengeNo) {
		this.challengeNo = challengeNo;
	}
	public void setHostEmail(String hostEmail) {
		this.hostEmail = hostEmail;
	}
	public void setChallengeTitle(String challengeTitle) {
		this.challengeTitle = challengeTitle;
	}
	public void setChallengeThumbnailImg(String challengeThumbnailImg) {
		this.challengeThumbnailImg = challengeThumbnailImg;
	}
	public void setChallengeDetail(String challengeDetail) {
		this.challengeDetail = challengeDetail;
	}
	public void setChallengeRule(String challengeRule) {
		this.challengeRule = challengeRule;
	}
	public void setChallengeCategNo(int challengeCategNo) {
		this.challengeCategNo = challengeCategNo;
	}
	public void setCertiCycle(List<String> certiCycle) {
		this.certiCycle = certiCycle;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public void setOpenRange(String openRange) {
		this.openRange = openRange;
	}
	public void setJoinPoint(int joinPoint) {
		this.joinPoint = joinPoint;
	}
	public void setChallengeRegDate(Date challengeRegDate) {
		this.challengeRegDate = challengeRegDate;
	}
	public void setChallengeStatus(String challengeStatus) {
		this.challengeStatus = challengeStatus;
	}
	public void setJoinCount(int joinCount) {
		this.joinCount = joinCount;
	}
	public void setCertiDate(List<String> certiDate) {
		this.certiDate = certiDate;
	}
	public void setTotalCertiCount(int totalCertiCount) {
		this.totalCertiCount = totalCertiCount;
	}
	
	public String getChallengeCategName() {
		return challengeCategName;
	}
	public List<String> getCertiCycleName() {
		return certiCycleName;
	}
	public String getChallengeJoinFlag() {
		return challengeJoinFlag;
	}
	public void setChallengeCategName(String challengeCategName) {
		this.challengeCategName = challengeCategName;
	}
	public void setCertiCycleName(List<String> certiCycleName) {
		this.certiCycleName = certiCycleName;
	}
	public void setChallengeJoinFlag(String challengeJoinFlag) {
		this.challengeJoinFlag = challengeJoinFlag;
	}
	public String getHostNick() {
		return hostNick;
	}
	public void setHostNick(String hostNick) {
		this.hostNick = hostNick;
	}
	@Override
	public String toString() {
		return "Challenge [challengeNo=" + challengeNo + ", hostEmail=" + hostEmail + ", challengeTitle="
				+ challengeTitle + ", challengeThumbnailImg=" + challengeThumbnailImg + ", challengeDetail="
				+ challengeDetail + ", challengeRule=" + challengeRule + ", challengeCategNo=" + challengeCategNo
				+ ", certiCycle=" + certiCycle + ", startDate=" + startDate + ", endDate=" + endDate + ", openRange="
				+ openRange + ", joinPoint=" + joinPoint + ", challengeRegDate=" + challengeRegDate
				+ ", challengeStatus=" + challengeStatus + ", joinCount=" + joinCount + ", certiDate=" + certiDate
				+ ", totalCertiCount=" + totalCertiCount + ", challengeCategName=" + challengeCategName
				+ ", certiCycleName=" + certiCycleName + ", challengeJoinFlag=" + challengeJoinFlag + ", hostNick="
				+ hostNick + "]";
	}
	
	
	
	
	
}
