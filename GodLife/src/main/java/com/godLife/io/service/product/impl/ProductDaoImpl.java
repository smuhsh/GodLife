package com.godLife.io.service.product.impl;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.godLife.io.common.Search;
import com.godLife.io.service.domain.Product;
import com.godLife.io.service.product.ProductDao;


//==> 회원관리 DAO CRUD 구현
@Repository("productDaoImpl")
public class ProductDaoImpl implements ProductDao{
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public ProductDaoImpl() {
		System.out.println(this.getClass());
	}

	///Method
	public void addProductCoupon(Product product) throws Exception {
		sqlSession.insert("ProductMapper.addProductCoupon", product);		
	}
	
	public void addProductVoucher(Product product) throws Exception {
		sqlSession.insert("ProductMapper.addProductVoucher", product);		
	}
	
	public void addProductPoint(Product product) throws Exception {
		sqlSession.insert("ProductMapper.addProductPoint", product);		
	}
///////////////////////////////////////////////////////////	
	public Product getProductCoupon(int productNo) throws Exception {
		return sqlSession.selectOne("ProductMapper.getProductCoupon", productNo);
	}
	public Product getProductVoucher(int productNo) throws Exception {
		return sqlSession.selectOne("ProductMapper.getProductVoucher", productNo);
	}
	public Product getProductPoint(int productNo) throws Exception {
		return sqlSession.selectOne("ProductMapper.getProductPoint", productNo);
	}
///////////////////////////////////////////////////////////	
	public List<Product> getProductCouponList(Search search) throws Exception {
		return sqlSession.selectList("ProductMapper.getProductCouponList", search);
	}
	
	public List<Product> getProductVoucherList(Search search) throws Exception {
		return sqlSession.selectList("ProductMapper.getProductVoucherList", search);
	}

	public List<Product> getProductPointList(Search search) throws Exception {
		return sqlSession.selectList("ProductMapper.getProductPointList", search);
	}
///////////////////////////////////////////////////////////		
	
	public void updateProductCoupon(Product product) throws Exception {
		sqlSession.update("ProductMapper.updateProductCoupon", product);
	}

	public void updateProductVoucher(Product product) throws Exception {
		sqlSession.update("ProductMapper.updateProductVoucher", product);
	}

	public void updateProductPoint(Product product) throws Exception {
		sqlSession.update("ProductMapper.updateProductPoint", product);
	}
///////////////////////////////////////////////////////////			
	public void deleteProductCoupon(Product productNo) throws Exception {
		sqlSession.delete("ProductMapper.deleteProductCoupon", productNo);
	}
	public void deleteProductVoucher(Product productNo) throws Exception {
		sqlSession.delete("ProductMapper.deleteProductVoucher", productNo);
	}
	public void deleteProductPoint(Product productNo) throws Exception {
		sqlSession.delete("ProductMapper.deleteProductPoint", productNo);
	}
///////////////////////////////////////////////////////////		
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("ProductMapper.getTotalCount", search);
	}


	
	
}