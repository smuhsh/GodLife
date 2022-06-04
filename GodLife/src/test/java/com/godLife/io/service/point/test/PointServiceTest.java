package com.godLife.io.service.point.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


import com.godLife.io.service.domain.Point;
import com.godLife.io.service.domain.User;
import com.godLife.io.service.point.PointService;
import com.godLife.io.common.Page;
import com.godLife.io.common.Search;



/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)

//==> Meta-Data 를 다양하게 Wiring 하자...
//@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
																	"classpath:config/context-aspect.xml",
																	"classpath:config/context-mybatis.xml",
																	"classpath:config/context-transaction.xml" })
//@ContextConfiguration(locations = { "classpath:config/context-common.xml" })
public class PointServiceTest {

	@Autowired
	@Qualifier("pointServiceImpl")
	private PointService pointService;
	
	@Test
	public void testAddPointPurchaseProduct() throws Exception {
		
		Point point = new Point();
		point.setUserEmail("user77@io.com");
		point.setUseStatus("1");
		point.setProductNo(10010);
		point.setPayOpt("1");
		point.setCash(50000);
		point.setPoint(50000);
		point.setUseDetail("1");
		point.setVoucherUniqueNo(null);
		
		pointService.addPointPurchaseProduct(point);
	}
	
	//@Test
	public void testAddPointPurchase() throws Exception {
		
		Point point = new Point();
		point.setUserEmail("user77@io.com");
		point.setUseStatus("2");
		point.setPoint(50000);
		point.setDonationPlace("양로원");
		point.setUseDetail("7");
		
		pointService.addPointPurchase(point);
	}
	
	
	
	
	

	}


