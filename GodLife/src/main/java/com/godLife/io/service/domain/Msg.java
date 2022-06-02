package com.godLife.io.service.domain;

import java.sql.Date;

//==>������ �𵨸�(�߻�ȭ/ĸ��ȭ)�� Bean
public class Msg {
	
	//Field 
	
	private int msgNo;
	private String sendEmail;
	private String nick;
	private String recvEmail;
	private String title;
	private String detail;
	private Date regDate;
	
	
	
	
	public int getMsgNo() {
		return msgNo;
	}
	public void setMsgNo(int msgNo) {
		this.msgNo = msgNo;
	}
	public String getSendEmail() {
		return sendEmail;
	}
	public void setSendEmail(String sendEmail) {
		this.sendEmail = sendEmail;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public String getRecvEmail() {
		return recvEmail;
	}
	public void setRecvEmail(String recvEmail) {
		this.recvEmail = recvEmail;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	
	
	@Override
	public String toString() {
		return "Msg [msgNo=" + msgNo + ", sendEmail=" + sendEmail + ", nick=" + nick + ", recvEmail=" + recvEmail
				+ ", title=" + title + ", detail=" + detail + ", regDate=" + regDate + "]";
	}
	
	
	
	
}


