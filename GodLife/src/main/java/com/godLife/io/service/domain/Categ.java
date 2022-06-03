package com.godLife.io.service.domain;

//==>관심사를 모델링(추상화/캡슐화)한 Bean
public class Categ {
	
	//field
	
	private int categNo;
	private String categName;
	
	
	public Categ() {
		
	}


	public int getCategNo() {
		return categNo;
	}


	public void setCategNo(int categNo) {
		this.categNo = categNo;
	}


	public String getCategName() {
		return categName;
	}


	public void setCategName(String categName) {
		this.categName = categName;
	}


	@Override
	public String toString() {
		return "Categ [categNo=" + categNo + ", categName=" + categName + "]";
	}


}

