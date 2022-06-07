package com.godLife.io.service.badge.test;



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
import com.godLife.io.service.badge.BadgeService;
import com.godLife.io.service.domain.Badge;
import com.godLife.io.service.domain.Badge;



/*
 *	FileName :  ProductServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */


@RunWith(SpringJUnit4ClassRunner.class)
//==> Meta-Data 를 다양하게 Wiring 하자...
//@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
@ContextConfiguration(locations = { "classpath:config/context-common.xml",
									"classpath:config/context-aspect.xml",
									"classpath:config/context-mybatis.xml",
									"classpath:config/context-transaction.xml"  })

//@ContextConfiguration(locations = { "classpath:config/context-common.xml" })
public class BadgeServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("badgeServiceImpl")
	private BadgeService badgeService;

	//@Test
	public void testAddBadge() throws Exception {
		
		Badge badge = new Badge();
		
		badge.setBadgeName("하나도부럽지가않아");
		badge.setBadgeImg("장기하이미지");
//		badge.setActCount(0);
//		badge.setGrade("0");
		badge.setBadgeDetail("하나도부럽지가않아");
		badge.setStatus("0");
		
		badgeService.addBadge(badge);
		
		badge.setBadgeNo(10075);
		badge = badgeService.getBadge(badge.getBadgeNo());

//		==> console 확인
		System.out.println(badge);
		
		//==> API 확인
		Assert.assertEquals("하나도부럽지가않아", badge.getBadgeName());
		Assert.assertEquals("장기하이미지", badge.getBadgeImg());
//		Assert.assertEquals(0, badge.getActCount());
//		Assert.assertEquals("0", badge.getGrade());
		Assert.assertEquals("하나도부럽지가않아", badge.getBadgeDetail());
		Assert.assertEquals("0", badge.getStatus());
	}
	
	//@Test
	public void testGetBadge() throws Exception {
		
		Badge badge = new Badge();
		
		badge.setBadgeNo(10075);
		badge = badgeService.getBadge(badge.getBadgeNo());

		//==> console 확인
		System.out.println(badge);
		
		//==> API 확인
		//왼쪽 : 내가 바라는 값 //오른쪽 : 컴터가 디비에서 가져온 값 (왼쪽 오른쪽 값이 같다면 초록불!)
		Assert.assertEquals("하나도부럽지가않아", badge.getBadgeName());
		Assert.assertEquals("장기하이미지", badge.getBadgeImg());
//		Assert.assertEquals(0, badge.getActCount());
//		Assert.assertEquals("0", badge.getGrade());
		Assert.assertEquals("하나도부럽지가않아", badge.getBadgeDetail());
		Assert.assertEquals("0", badge.getStatus());
		
		//오류 내고 싶으면 오류내고, validation 체크용
		Assert.assertNotNull(badgeService.getBadge(badge.getBadgeNo()));
		

	}
	
	 //@Test
	 public void testUpdateBadge() throws Exception{
		 
		Badge badge = badgeService.getBadge(10075);

		
		Assert.assertNotNull(badge);
		
		Assert.assertEquals("하나도부럽지가않아", badge.getBadgeName());
		Assert.assertEquals("장기하이미지", badge.getBadgeImg());
//		Assert.assertEquals(0, badge.getActCount());
//		Assert.assertEquals("0", badge.getGrade());
		Assert.assertEquals("하나도부럽지가않아", badge.getBadgeDetail());
		Assert.assertEquals("0", badge.getStatus());

		badge.setBadgeName("하나는부러워");
		badge.setBadgeImg("아이유이미지");
		badge.setActCount(2);
		badge.setGrade("2");
		badge.setBadgeDetail("하나는부러워");
		badge.setStatus("1");
		
		
		
		badgeService.updateBadge(badge);
		
		badge = badgeService.getBadge(10075);
		
		Assert.assertNotNull(badge);
		
		//==> console 확인
		System.out.println(badge);
			
		//==> API 확인
		Assert.assertEquals("하나는부러워", badge.getBadgeName());
		Assert.assertEquals("아이유이미지", badge.getBadgeImg());
		Assert.assertEquals(2, badge.getActCount());
		Assert.assertEquals("2", badge.getGrade());
		Assert.assertEquals("하나는부러워", badge.getBadgeDetail());
		Assert.assertEquals("1", badge.getStatus());
	 }

	 //==>  주석을 풀고 실행하면....
	 //@Test
	 public void testGetBadgeListAll() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	//Total 개수가 47개
	 	search.setPageSize(47);
	 	Map<String,Object> map = badgeService.getBadgeList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(47, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = badgeService.getBadgeList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
	 	//==> console 확인
	 	//System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetBadgeListByProdNo() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(47);
	 	search.setSearchCondition("10075");
	 	search.setSearchKeyword("아이유이미지");
	 	Map<String,Object> map = badgeService.getBadgeList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(47, list.size());
	 	
		//==> console 확인
	 	//System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = badgeService.getBadgeList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetBadgeListByProdName() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(47);
	 	search.setSearchCondition("10075");
	 	search.setSearchKeyword("하나는부러워");
	 	Map<String,Object> map = badgeService.getBadgeList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(47, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = badgeService.getBadgeList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console 확인
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testDeleteBadge() throws Exception{
		 
		Badge badge = badgeService.getBadge(10075);

		
		Assert.assertNotNull(badge);
		
		Assert.assertEquals("하나는부러워", badge.getBadgeName());
		Assert.assertEquals("아이유이미지", badge.getBadgeImg());
		Assert.assertEquals(2, badge.getActCount());
		Assert.assertEquals("2", badge.getGrade());
		Assert.assertEquals("하나는부러워", badge.getBadgeDetail());
		Assert.assertEquals("1", badge.getStatus());
		
		badgeService.deleteBadge(badge);
		
		badge = badgeService.getBadge(10075);
		
		Assert.assertNull(badge);
			
	 }
	 
	 

	 
}

