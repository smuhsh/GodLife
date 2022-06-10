package com.godLife.io.service.product.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.godLife.io.common.Search;
import com.godLife.io.service.domain.Badge;
import com.godLife.io.service.domain.Product;
import com.godLife.io.service.product.ProductDao;
import com.godLife.io.service.product.ProductService;



//==> 상품관리 서비스 구현
@Service("productServiceImpl")
public class ProductServiceImpl implements ProductService{
		
	///Field
	@Autowired
	@Qualifier("productDaoImpl")
	private ProductDao productDao;
	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}
	
	///Constructor
	public ProductServiceImpl() {
		System.out.println(this.getClass());
	}
///////////////////////////////////////////	
	public void addProductCoupon(Product product) throws Exception {
		productDao.addProductCoupon(product);
	}
	public void addProductVoucher(Product product) throws Exception {
		productDao.addProductVoucher(product);
	}
	public void addProductPoint(Product product) throws Exception {
		productDao.addProductPoint(product);
	}
///////////////////////////////////////////		
	public Product getProductCoupon(int productNo) throws Exception {
		return productDao.getProductCoupon(productNo);
	}
	public Product getProductVoucher(int productNo) throws Exception {
		return productDao.getProductVoucher(productNo);
	}
	public Product getProductPoint(int productNo) throws Exception {
		return productDao.getProductPoint(productNo);
	}
////////////////////////////////////////////////////////////////////////////
	public Map<String , Object> getProductCouponList(Search search) throws Exception {
		
		List<Product> list= productDao.getProductCouponList(search);
		int totalCount = productDao.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	public Map<String , Object> getProductVoucherList(Search search) throws Exception {
		
		List<Product> list= productDao.getProductVoucherList(search);
		int totalCount = productDao.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	public Map<String , Object> getProductPointList(Search search) throws Exception {
		
		List<Product> list= productDao.getProductPointList(search);
		int totalCount = productDao.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
////////////////////////////////////////////////////////////////////////////
	
	public void updateProductCoupon(Product product) throws Exception {
		productDao.updateProductCoupon(product);
	}
	public void updateProductVoucher(Product product) throws Exception {
		productDao.updateProductVoucher(product);
	}
	public void updateProductPoint(Product product) throws Exception {
		productDao.updateProductPoint(product);
	}
////////////////////////////////////////////////////////////////////////////	
	public void deleteProductCoupon(Product productNo) throws Exception {
		productDao.deleteProductCoupon(productNo);
	}
	public void deleteProductVoucher(Product productNo) throws Exception {
		productDao.deleteProductVoucher(productNo);
	}
	public void deleteProductPoint(Product productNo) throws Exception {
		productDao.deleteProductPoint(productNo);
	}




}