package com.godLife.io.service.domain;

import java.sql.Date;
import java.util.List;

public class Point {
	
	private int purchaseNo;
	private String userEmail;
	private String useStatus;
	private int productNo;
	private String payOpt;
	private int cash;
	private int point;
	private String useDetail;
	private String voucherUniqueNo;
	private String donationPlace;
	private Date regDate;
	private String phone;
	private String productName;
	
	
	public int getPurchaseNO() {
		return purchaseNo;
	}
	public void setPurchaseNO(int purchaseNo) {
		this.purchaseNo = purchaseNo;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	public String getUseStatus() {
		return useStatus;
	}
	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public String getPayOpt() {
		return payOpt;
	}
	public void setPayOpt(String payOpt) {
		this.payOpt = payOpt;
	}
	public int getCash() {
		return cash;
	}
	public void setCash(int cash) {
		this.cash = cash;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getUseDetail() {
		return useDetail;
	}
	public void setUseDetail(String useDetail) {
		this.useDetail = useDetail;
	}
	
	
	public Date getRegDate() {
		return regDate;
	}
	public void setRegDate(Date regDate) {
		this.regDate = regDate;
	}
	public String getDonationPlace() {
		return donationPlace;
	}
	public void setDonationPlace(String donationPlace) {
		this.donationPlace = donationPlace;
	}
	
	@Override
	public String toString() {
		return "Point [purchaseNo=" + purchaseNo + ", userEmail=" + userEmail + ", useStatus=" + useStatus
				+ ", productNo=" + productNo + ", payOpt=" + payOpt + ", cash=" + cash + ", point=" + point
				+ ", useDetail=" + useDetail + ", voucherUniqueNo=" + voucherUniqueNo + ", donationPlace="
				+ donationPlace + ", regDate=" + regDate + ", phone=" + phone + ", productName=" + productName + "]";
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}
	public void setVoucherUniqueNo(String voucherUniqueNo) {
		this.voucherUniqueNo =voucherUniqueNo;
	}
	public String getVoucherUniqueNo(){
		return voucherUniqueNo;
	}
}
