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
	public void updateProductCoupon(Product product) throws Exception ;
	
	public void updateProductVoucher(Product product) throws Exception ;
	
	public void updateProductPoint(Product product) throws Exception ;
///////////////////////////////////////////////////////////			
	public void deleteProductCoupon(Product productNo) throws Exception ;
	
	public void deleteProductVoucher(Product productNo) throws Exception ;
	
	public void deleteProductPoint(Product productNo) throws Exception ;

		
	
}
