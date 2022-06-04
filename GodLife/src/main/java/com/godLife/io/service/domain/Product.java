package com.godLife.io.service.domain;

//==>회원정보를 모델링(추상화/캡슐화)한 Bean
public class Product {
	
	//field
	
	private int productNo;
	private String productName;
	private int productPrice;
	private String productDetail;
	private String productImg;
	private String status;
	
	
	///Constructor
	public Product() {
	}
	public int getProductNo() {
		return productNo;
	}
	public void setProductNo(int productNo) {
		this.productNo = productNo;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public int getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(int productPrice) {
		this.productPrice = productPrice;
	}
	public String getProductDetail() {
		return productDetail;
	}
	public void setProductDetail(String producDetail) {
		this.productDetail = producDetail;
	}
	public String getProductImg() {
		return productImg;
	}
	public void setProductImg(String productImg) {
		this.productImg = productImg;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	

	//M
	@Override
	public String toString() {
		return "Product [productNo=" + productNo + ", productName=" + productName + ", productPrice=" + productPrice
				+ ", producDetail=" + productDetail + ", productImg=" + productImg + ", status=" + status + "]";
	}



}
