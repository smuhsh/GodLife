package com.godLife.io.service.domain;

import java.sql.Date;

//==>신고관리
public class Report {
	
	//Field
	
	private int reportNo;
	private String reporterEmail;
	private String targetEmail;
	private String reason;
	private Date regDate;
	private String reportPlace;
	private int certiImgNO;
	private int commentNo;
	private int msgNo;
	
	private String nick; // 닉네임가져오기위해서 추가 
	
	//Constructor
	public Report() {
	}

	public int getReportNo() {
		return reportNo;
	}

	public void setReportNo(int reportNo) {
		this.reportNo = reportNo;
	}

	public String getReporterEmail() {
		return reporterEmail;
	}

	public void setReporterEmail(String reporterEmail) {
		this.reporterEmail = reporterEmail;
	}

	public String getTargetEmail() {
		return targetEmail;
	}

	public void setTargetEmail(String targetEmail) {
		this.targetEmail = targetEmail;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	public String getReportPlace() {
		return reportPlace;
	}

	public void setReportPlace(String reportPlace) {
		this.reportPlace = reportPlace;
	}

	public int getCertiImgNO() {
		return certiImgNO;
	}

	public void setCertiImgNO(int certiImgNO) {
		this.certiImgNO = certiImgNO;
	}

	public int getCommentNo() {
		return commentNo;
	}

	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}

	public int getMsgNo() {
		return msgNo;
	}

	public void setMsgNo(int msgNo) {
		this.msgNo = msgNo;
	}

	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
	}
	
	@Override
	public String toString() {
		return "Report [reportNo=" + reportNo + ", reporterEmail=" + reporterEmail + ", targetEmail=" + targetEmail
				+ ", reason=" + reason + ", regDate=" + regDate + ", reportPlace=" + reportPlace + ", certiImgNO="
				+ certiImgNO + ", commentNo=" + commentNo + ", msgNo=" + msgNo + ", nick=" + nick + "]";
	}

}
