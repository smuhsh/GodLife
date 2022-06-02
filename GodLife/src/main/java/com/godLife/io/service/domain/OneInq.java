package com.godLife.io.service.domain;

import java.sql.Date;

//==>�ϴ��Ϲ��Ǹ� �𵨸�(�߻�ȭ/ĸ��ȭ)�� Bean
public class OneInq {
	
	//field
	private int oneInqNo;
	private String userEmail;
	private String adminEmail;
	private String title;
	private String detail;
	private Date regDate;
	private String commentDetail;
	private Date commentDate;
	private String status;
	
	
	///Constructor
	public OneInq() {
	}
	
	///Method
	public int getOneInqNo() {
		return oneInqNo;
	}

	public void setOneInqNo(int oneInqNo) {
		this.oneInqNo = oneInqNo;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public String getAdminEmail() {
		return adminEmail;
	}

	public void setAdminEmail(String adminEmail) {
		this.adminEmail = adminEmail;
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

	public String getCommentDetail() {
		return commentDetail;
	}

	public void setCommentDetail(String commentDetail) {
		this.commentDetail = commentDetail;
	}

	public Date getCommentDate() {
		return commentDate;
	}

	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}


	
	@Override
	public String toString() {
		return "OneInq [oneInqNo=" + oneInqNo + ", userEmail=" + userEmail + ", adminEmail=" + adminEmail + ", title="
				+ title + ", detail=" + detail + ", regDate=" + regDate + ", commentDetail=" + commentDetail
				+ ", commentDate=" + commentDate + ", status=" + status + "]";
	}
	

}
