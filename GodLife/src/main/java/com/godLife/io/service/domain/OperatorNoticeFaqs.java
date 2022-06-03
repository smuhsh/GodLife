package com.godLife.io.service.domain;

import java.sql.Date;

public class OperatorNoticeFaqs {
	
	private Integer noticeFaqNo;
	private String userEmail;
	private char status;
	private char noticeMust;
	private String title;
	private String detail;
	private String img;
	private char faqTag;
	private Date regDate;
	
	//Constructor
	public void OperatorNotice() {
		
	}
	
	//Method
	public Integer getNoticeFaqNo() {
		return noticeFaqNo;
	}

	public void setNoticeFaqNo(Integer noticeFaqNo) {
		this.noticeFaqNo = noticeFaqNo;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	public char getStatus() {
		return status;
	}

	public void setStatus(char status) {
		this.status = status;
	}

	public char getNoticeMust() {
		return noticeMust;
	}

	public void setNoticeMust(char noticeMust) {
		this.noticeMust = noticeMust;
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

	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}

	public char getFaqTag() {
		return faqTag;
	}

	public void setFaqTag(char faqTag) {
		this.faqTag = faqTag;
	}

	public Date getRegDate() {
		return regDate;
	}

	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}

	@Override
	public String toString() {
		return "NoticeFaqs [noticeFaqNo=" + noticeFaqNo + ", userEmail=" + userEmail + ", status=" + status
				+ ", noticeMust=" + noticeMust + ", title=" + title + ", detail=" + detail + ", img=" + img
				+ ", faqTag=" + faqTag + ", regDate=" + regDate + "]";
	}

	public OperatorNoticeFaqs(Integer noticeFaqNo, String userEmail, char status, char noticeMust, String title, String detail,
			String img, char faqTag, Date regDate) {
		super();
		this.noticeFaqNo = noticeFaqNo;
		this.userEmail = userEmail;
		this.status = status;
		this.noticeMust = noticeMust;
		this.title = title;
		this.detail = detail;
		this.img = img;
		this.faqTag = faqTag;
		this.regDate = regDate;
	}
	

}
