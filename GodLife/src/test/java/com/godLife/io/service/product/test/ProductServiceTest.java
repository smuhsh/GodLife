package com.godLife.io.service.product.test;



import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.godLife.io.common.Search;
import com.godLife.io.service.domain.Product;
import com.godLife.io.service.product.ProductService;


/*
 *	FileName :  ProductServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */


//@RunWith(SpringJUnit4ClassRunner.class)
////==> Meta-Data 를 다양하게 Wiring 하자...
////@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
//@ContextConfiguration(locations = { "classpath:config/context-common.xml",
//									"classpath:config/context-aspect.xml",
//									"classpath:config/context-mybatis.xml",
//									"classpath:config/context-transaction.xml"  })

////@ContextConfiguration(locations = { "classpath:config/context-common.xml" })
//public class ProductServiceTest {
//
//	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
//	@Autowired
//	@Qualifier("productServiceImpl")
//	private ProductService productService;
//
//	//@Test
//	public void testAddProduct() throws Exception {
//		
//		Product product = new Product();
//		
//		product.setProductName("testProductCouponName");
//		product.setProductPrice(100000);
//		product.setProductDetail("testDetail");
//		product.setProductImg("testCouponImg");
//		product.setStatus("1");
//		
//		productService.addProduct(product);
//		
//		product.setProductNo(10010);
//		product = productService.getProduct(product.getProductNo());
//
////		==> console 확인
//		System.out.println(product);
//		
//		//==> API 확인
//		Assert.assertEquals("testProductCouponName", product.getProductName());
//		Assert.assertEquals(100000, product.getProductPrice());
//		Assert.assertEquals("testDetail", product.getProductDetail());
//		Assert.assertEquals("testCouponImg", product.getProductImg());
//		Assert.assertEquals("1", product.getStatus());
//	}
//	
//	@Test
//	public void testGetProductCoupoon() throws Exception {
//		
//		Product product = new Product();
//		
//		product.setProductNo(10011);
//		product = productService.getProductCoupon(product.getProductNo());
//
//		//==> console 확인
//		System.out.println(product);
//		
//		//==> API 확인
//		//왼쪽 : 내가 바라는 값 //오른쪽 : 컴터가 디비에서 가져온 값 (왼쪽 오른쪽 값이 같다면 초록불!)
//		Assert.assertEquals("testProductCouponName", product.getProductName());
//		Assert.assertEquals(100000, product.getProductPrice());
//		Assert.assertEquals("testDetail", product.getProductDetail());
//		Assert.assertEquals("testCouponImg", product.getProductImg());
//		Assert.assertEquals("1", product.getStatus());
//		
//		//오류 내고 싶으면 오류내고, validation 체크용
//		Assert.assertNotNull(productService.getProductCoupon(product.getProductNo()));
//		
//
//	}
//	
//	//@Test
//	 public void testUpdateProduct() throws Exception{
//		 
//		Product product = productService.getProduct(10075);
//
//		
//		Assert.assertNotNull(product);
//		
//		Assert.assertEquals("testProductCouponName", product.getProductName());
//		Assert.assertEquals(100000, product.getProductPrice());
//		Assert.assertEquals("testDetail", product.getProductDetail());
//		Assert.assertEquals("testCouponImg", product.getProductImg());
//		Assert.assertEquals("1", product.getStatus());
//
//		product.setProductName("testNewProductCouponName");
//		product.setProductPrice(10000);
//		product.setProductDetail("testNewDetail");
//		product.setProductImg("testNewCouponImg");
//		product.setStatus("1");
//		
//		
//		
//		productService.updateProduct(product);
//		
//		product = productService.getProduct(10075);
//		
//		Assert.assertNotNull(product);
//		
//		//==> console 확인
//		System.out.println(product);
//			
//		//==> API 확인
//		Assert.assertEquals("testNewProductCouponName", product.getProductName());
//		Assert.assertEquals(10000, product.getProductPrice());
//		Assert.assertEquals("testNewDetail", product.getProductDetail());
//		Assert.assertEquals("testNewCouponImg", product.getProductImg());
//		Assert.assertEquals("1", product.getStatus());
//	 }
//
////	 //==>  주석을 풀고 실행하면....
////	 //@Test
////	 public void testGetProductCouponListAll() throws Exception{
////		 
////	 	Search search = new Search();
////	 	search.setCurrentPage(1);
////	 	//Total Page가 48이지
////	 	search.setPageSize(48);
////	 	Map<String,Object> map = productService.getProductCouponList(search);
////	 	
////	 	List<Object> list = (List<Object>)map.get("list");
////	 	Assert.assertEquals(48, list.size());
////	 	
////		//==> console 확인
////	 	//System.out.println(list);
////	 	
////	 	Integer totalCount = (Integer)map.get("totalCount");
////	 	System.out.println(totalCount);
////	 	
////	 	System.out.println("=======================================");
////	 	
////	 	search.setCurrentPage(1);
////	 	search.setPageSize(3);
////	 	search.setSearchCondition("0");
////	 	search.setSearchKeyword("");
////	 	map = productService.getProductCouponList(search);
////	 	
////	 	list = (List<Object>)map.get("list");
////	 	Assert.assertEquals(3, list.size());
////	 	
////	 	//==> console 확인
////	 	//System.out.println(list);
////	 	
////	 	totalCount = (Integer)map.get("totalCount");
////	 	System.out.println(totalCount);
////	 }
////	 
////	 //@Test
////	 public void testGetProductListByProdNo() throws Exception{
////		 
////	 	Search search = new Search();
////	 	search.setCurrentPage(1);
////	 	search.setPageSize(48);
////	 	search.setSearchCondition("10075");
////	 	search.setSearchKeyword("testNewCouponImg");
////	 	Map<String,Object> map = productService.getProductList(search);
////	 	
////	 	List<Object> list = (List<Object>)map.get("list");
////	 	Assert.assertEquals(48, list.size());
////	 	
////		//==> console 확인
////	 	//System.out.println(list);
////	 	
////	 	Integer totalCount = (Integer)map.get("totalCount");
////	 	System.out.println(totalCount);
////	 	
////	 	System.out.println("=======================================");
////	 	
////	 	search.setSearchCondition("0");
////	 	search.setSearchKeyword(""+System.currentTimeMillis());
////	 	map = productService.getProductList(search);
////	 	
////	 	list = (List<Object>)map.get("list");
////	 	Assert.assertEquals(0, list.size());
////	 	
////		//==> console 확인
////	 	//System.out.println(list);
////	 	
////	 	totalCount = (Integer)map.get("totalCount");
////	 	System.out.println(totalCount);
////	 }
////	 
////	 //@Test
////	 public void testGetProductListByProdName() throws Exception{
////		 
////	 	Search search = new Search();
////	 	search.setCurrentPage(1);
////	 	search.setPageSize(48);
////	 	search.setSearchCondition("10075");
////	 	search.setSearchKeyword("testNewCouponImg");
////	 	Map<String,Object> map = productService.getProductList(search);
////	 	
////	 	List<Object> list = (List<Object>)map.get("list");
////	 	Assert.assertEquals(48, list.size());
////	 	
////		//==> console 확인
////	 	System.out.println(list);
////	 	
////	 	Integer totalCount = (Integer)map.get("totalCount");
////	 	System.out.println(totalCount);
////	 	
////	 	System.out.println("=======================================");
////	 	
////	 	search.setSearchCondition("1");
////	 	search.setSearchKeyword(""+System.currentTimeMillis());
////	 	map = productService.getProductList(search);
////	 	
////	 	list = (List<Object>)map.get("list");
////	 	Assert.assertEquals(0, list.size());
////	 	
////		//==> console 확인
////	 	System.out.println(list);
////	 	
////	 	totalCount = (Integer)map.get("totalCount");
////	 	System.out.println(totalCount);
////	 }
////	 
////	 //@Test
////	 public void testDeleteProduct() throws Exception{
////		 
////		Product product = productService.getProduct(10075);
////
////		
////		Assert.assertNotNull(product);
////		
////		Assert.assertEquals("testNewProductCouponName", product.getProductName());
////		Assert.assertEquals(10000, product.getProductPrice());
////		Assert.assertEquals("testNewDetail", product.getProductDetail());
////		Assert.assertEquals("testNewCouponImg", product.getProductImg());
////		Assert.assertEquals("1", product.getStatus());
////		
////		productService.deleteProduct(product);
////		
////		product = productService.getProduct(10075);
////		
////		Assert.assertNull(product);
////			
////	 }
//	 
//	 
//
//	 
//}

