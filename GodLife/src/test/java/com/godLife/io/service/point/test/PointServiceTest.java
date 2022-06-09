package com.godLife.io.service.point.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpSession;

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
import com.godLife.io.service.user.UserService;
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
@ContextConfiguration(locations = { "classpath:config/context-common.xml", "classpath:config/context-aspect.xml",
		"classpath:config/context-mybatis.xml", "classpath:config/context-transaction.xml" })
//@ContextConfiguration(locations = { "classpath:config/context-common.xml" })
public class PointServiceTest {

	@Autowired
	@Qualifier("pointServiceImpl")
	private PointService pointService;
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	 //@Test
	public void testAddPointPurchaseProduct() throws Exception {

		User user = new User();
		Point point = new Point();
		user.setUserEmail("user77@io.com");
		user.setTotalPoint(500000);
		int totalPoint =user.getTotalPoint();
		
		point.setPurchaseNO(0);
		point.setUserEmail("user77@io.com");
		point.setUseStatus("2");
		point.setProductNo(10002);
		point.setPayOpt(null);
		point.setCash(0);
		point.setPoint(3000);
		point.setUseDetail("9");
		point.setVoucherUniqueNo(null);
		point.setRegDate(null);
		
		String useStatus = point.getUseStatus();
		System.out.println("useStatus : "+useStatus);
		
		String useDetail = point.getUseDetail();
		System.out.println("useDetail : "+useDetail);
		
		int usePoint = point.getPoint();
		System.out.println("usePoint : "+usePoint);
		
		int productNo = point.getProductNo();
		System.out.println("productNo " +productNo);
		
		
		if(useStatus=="1") {
			int sumTotalPoint = totalPoint + usePoint;
			
			System.out.println(" sumPoint : " + sumTotalPoint);
			user.setTotalPoint(sumTotalPoint);
			
			userService.updateUserTotalPoint(user);
			
			if(useDetail=="1") {
				
				System.out.println(point);
				pointService.addPointPurchaseProduct(point);
				
			}
			
		} else if(useStatus=="2") {
			int sumTotalPoint = totalPoint - usePoint;

			System.out.println(" sumPoint : " + sumTotalPoint);
			
			user.setTotalPoint(sumTotalPoint);
			userService.updateUserTotalPoint(user);
			if(useDetail=="8") {
			
				if(productNo==10000) {
				
					int redCoupon = user.getRedCouponCount()+1;
					System.out.println("redCoupon : "+redCoupon);
					user.setRedCardCount(redCoupon);
				
					userService.updateUserRedCouponCount(user);
				
				} else if(productNo==10001) {
				
					int certiCoupon = user.getCertiCouponCount()+1;
					System.out.println("certiCoupon : "+certiCoupon);
					user.setCertiCouponCount(certiCoupon);
					userService.updateUserCertiCouponCount(user);
				}
				
				System.out.println(point);
				pointService.addPointPurchaseProduct(point);
				
			}else if(useDetail=="9") {
				if(productNo>=10002 || productNo <= 10006) {
				Random rnd =new Random();
				String voucherUniqueNo=new String(); 
				StringBuffer buf =new StringBuffer();

					for(int i=0;i<10;i++){

						// rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를, false 일 시 랜덤 한 숫자를 StringBuffer 에 append 한다.

						if(rnd.nextBoolean()){
							
							buf.append((char)((int)(rnd.nextInt(26))+97));
							
							
						}else{

							buf.append((rnd.nextInt(10)));
						
						}
					
					}
					voucherUniqueNo=buf.toString();
					point.setVoucherUniqueNo(voucherUniqueNo);
				}
				
				System.out.println(point);
				pointService.addPointPurchaseProduct(point);
				
			}
			
		}
		
	
	}

		
	

	//@Test
	public void testAddPointPurchase() throws Exception {
		Map<String,Object> map = new HashMap<String, Object>();
		
		User user = new User();
		user.setUserEmail("user77@io.com");
		user.setTotalPoint(1000000);
		
		
		Point point = new Point();
		point.setUserEmail("user77@io.com");
		point.setUseStatus("2");
		point.setPoint(50000);
		point.setDonationPlace("양로원");
		point.setUseDetail("7");

		map.put("user", user);
		map.put("point", point);
		System.out.println(user);
		System.out.println(point);
		System.out.println(map);
		
		pointService.addPointPurchase(map);
	}

	@Test
	public void testGetPointPurchaseList() throws Exception {

		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);

		User user = new User();
		user.setUserEmail("user77@io.com");
		Map<String, Object> map = pointService.getPointPurchaseList(search, user);

		List<Object> list = (List<Object>) map.get("list");
		Assert.assertEquals(3, list.size());

		// ==> console 확인
		System.out.println(list);

		Integer totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);

	}
	
	//@Test
	public void testGetPointPurchaseVoucherList() throws Exception {
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		
		User user = new User();
		user.setUserEmail("user77@io.com");
		
		Map<String, Object> map =pointService.getPointPurchaseVoucherList(search, user);
		
		List<Object> list = (List<Object>) map.get("list");
		Assert.assertEquals(1, list.size());
		
		System.out.println(list);
		
		Integer totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);
	}
	
	//@Test
	public void testGetPointPurchaseDonationList() throws Exception {
		
		Search search = new Search();
		search.setCurrentPage(1);
		search.setPageSize(3);
		
		User user = new User();
		user.setUserEmail("user77@io.com");
		
		Map<String, Object> map =pointService.getPointPurchaseDonationList(search, user);
		
		List<Object> list = (List<Object>) map.get("list");
		Assert.assertEquals(3,list.size());
		
		System.out.println(list);
		
		Integer totalCount = (Integer) map.get("totalCount");
		System.out.println(totalCount);
	}
}
