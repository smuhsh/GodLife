package com.godLife.io.service.product;

import java.util.List;
import java.util.Map;

import com.godLife.io.common.Search;
import com.godLife.io.service.domain.Product;

//==> 회원관리에서 서비스할 내용 추상화/캡슐화한 Service  Interface Definition 
public interface ProductService {
	
	///////////////////////////////////////////////////////////////
	
	public void addProductCoupon(Product product) throws Exception ;

	public void addProductVoucher(Product product) throws Exception ;
	
	public void addProductPoint(Product product) throws Exception ;
	///////////////////////////////////////////////////////////////	
	
	public Product getProductCoupon(int productNo) throws Exception ;
	
	public Product getProductVoucher(int productNo) throws Exception ;
	
	public Product getProductPoint(int productNo) throws Exception ;
///////////////////////////////////////////////////////////		
	public Map<String, Object> getProductCouponList(Search search) throws Exception ;
	
	public Map<String, Object> getProductVoucherList(Search search) throws Exception ;
	
	public Map<String, Object> getProductPointList(Search search) throws Exception ;
///////////////////////////////////////////////////////////		
	public void updateProduct(Product product) throws Exception ;	
	
	public void deleteProduct(Product productNo) throws Exception ;
	
//	// 신규 쿠폰 상품 등록 UI 요청 
//	public void addProductCouponView(Product product) throws Exception ;	
//	// 신규 쿠폰 상품 등록 완료 요청
//	public void addProductCoupon(Product product) throws Exception ;
//	// 신규 쿠폰 상품 등록 확인 요청
//	public void addProductCouponConfirm(Product product) throws Exception ;
//	// 신규 배지 이미지 등록 완료
//	public void addProductCouponImg(Product product) throws Exception ;
//	
//	// 신규 상품권 상품 등록 UI 요청 
//	public void addProductVoucherView(Product product) throws Exception ;	
//	// 신규 상품권 상품 등록 완료 요청
//	public void addProductVoucher(Product product) throws Exception ;
//	// 신규 상품권 상품 등록 확인 요청
//	public void addProductVoucherConfirm(Product product) throws Exception ;
//	// 신규 상품권 이미지 등록 완료
//	public void addProductVoucherImg(Product product) throws Exception ;
//	
//	// 신규 포인트 상품 등록 UI 요청 
//	public void addProductPointView(Product product) throws Exception ;	
//	// 신규 포인트 상품 등록 완료 요청
//	public void addProductPoint(Product product) throws Exception ;
//	// 신규 포인트 상품 등록 확인 요청
//	public void addProductPointConfirm(Product product) throws Exception ;
//	// 신규 포인트 이미지 등록 완료
//	public void addProductPointImg(Product product) throws Exception ;
	
	///////////////////////////////////////////////////////////////	
	

	
//	// 쿠폰 상품 상세 내용 요청 
//	public Product getProductCoupon(int productNo) throws Exception ;
//	// 상품권 상품 상세 내용 요청 
//	public Product getProductVoucher(int productNo) throws Exception ;
//	// 포인트 상품 상세 내용 요청 
//	public Product getProductPoint(int productNo) throws Exception ;
	
	///////////////////////////////////////////////////////////////	
	
	

	
//	// 쿠폰 상품 전체 목록 UI 요청
//	public Map<String, Object> getProductCouponList(Search search) throws Exception ;
//	// 상품권 상품 전체 목록 UI 요청
//	public Map<String, Object> getProductVoucherList(Search search) throws Exception ;
//	// 포인트 상품 전체 목록 UI 요청
//	public Map<String, Object> getProductPointList(Search search) throws Exception ;
	
	///////////////////////////////////////////////////////////////
	

	
//	// 쿠폰 상품 이미지 수정
//	public void updateProductCouponImg(Product product) throws Exception ;	
//	// 쿠폰 상품 내용 수정 완료 요청
//	public void updateProductCoupon(Product product) throws Exception ;	
//	// 쿠폰 상품 내용 수정 확인 요청
//	public void updateProductCouponConfirm(Product product) throws Exception ;	
//	
//	// 상품권 상품 이미지 수정
//	public void updateProductVoucherImg(Product product) throws Exception ;	
//	// 상품권 상품 내용 수정 완료 요청
//	public void updateProductVoucher(Product product) throws Exception ;	
//	// 상품권 상품 내용 수정 확인 요청
//	public void updateProductVoucherConfirm(Product product) throws Exception ;
//	
//	// 포인트 상품 이미지 수정
//	public void updateProductPointImg(Product product) throws Exception ;	
//	// 포인트 상품 내용 수정 완료 요청
//	public void updateProductPoint(Product product) throws Exception ;	
//	// 포인트 상품 내용 수정 확인 요청
//	public void updateProductPointConfirm(Product product) throws Exception ;
	
	///////////////////////////////////////////////////////////////
	
	// DELETE (기존꺼에 추가 Data)
	

	
//	// 쿠폰 상품 삭제 완료 요청
//	public void deleteProductCoupon(int productNo) throws Exception ;
//	// 쿠폰 상품 삭제 확인 요청
//	public void deleteProductCouponConfirm(int productNo) throws Exception ;	
//
//	// 상품권 상품 삭제 완료 요청
//	public void deleteProductVoucher(int productNo) throws Exception ;
//	// 상품권 상품 삭제 확인 요청
//	public void deleteProductVoucherConfirm(int productNo) throws Exception ;	
//
//	// 포인트 상품 삭제 완료 요청
//	public void deleteProductPoint(int productNo) throws Exception ;
//	// 포인트 상품 삭제 확인 요청
//	public void deleteProductPointConfirm(int productNo) throws Exception ;		
	
		
	
}
