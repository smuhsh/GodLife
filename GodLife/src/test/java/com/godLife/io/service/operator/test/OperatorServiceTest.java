package com.godLife.io.service.operator.test;

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

import com.godLife.io.service.domain.OperatorEvents;
//import com.godLife.io.service.domain.OperatorJoinEvent;
//import com.godLife.io.service.domain.OperatorNoticeFaqs;
//import com.godLife.io.service.domain.OperatorReward;
import com.godLife.io.service.operator.OperatorService;



@RunWith(SpringJUnit4ClassRunner.class)

//==> Meta-Data 를 다양하게 Wiring 하자...
//@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
																	"classpath:config/context-aspect.xml",
																	"classpath:config/context-mybatis.xml",
																	"classpath:config/context-transaction.xml" })
//@ContextConfiguration(locations = { "classpath:config/context-common.xml" })
public class OperatorServiceTest {

	@Autowired
	@Qualifier("operatorServiceImpl")
	private OperatorService operatorService;

	@Test
	public void testAddOperatorEvents() throws Exception {
		
		OperatorEvents operatorEvents = new OperatorEvents();
		operatorEvents.setEventNo(Integer.parseInt(("8")));
		operatorEvents.setEventTitle("final Event Title Test");
		operatorEvents.setThumbnailImg("newEventThumbNail.jpg");
		operatorEvents.setDetail("neweventDetail");
		operatorEvents.setEventImg("newEventImg.jpg");
		operatorEvents.setRoullJoinPoint(Integer.parseInt(("1000")));
		
		operatorService.addOperatorEvents(operatorEvents);
		
		//==> console check
		System.out.println(operatorEvents);
		
		//==> API check
		Assert.assertEquals(8, operatorEvents.getEventNo());
		Assert.assertEquals("final Event Title Test", operatorEvents.getEventTitle());
		Assert.assertEquals("newEventThumbNail.jpg", operatorEvents.getThumbnailImg());
		Assert.assertEquals("neweventDetail", operatorEvents.getDetail());
		Assert.assertEquals("newEventImg.jpg", operatorEvents.getEventImg());
		Assert.assertEquals(1000, operatorEvents.getRoullJoinPoint());
	}
	
//	//@Test
//	public void testGetUser() throws Exception {
//		
//		OperatorEvents operatorEvents = new OperatorEvents();
//		
////			user.setUserId("testUserId");
////			user.setUserName("testUserName");
////			user.setPassword("testPasswd");
////			user.setSsn("1111112222222");
////			user.setPhone("111-2222-3333");
////			user.setAddr("��⵵");
////			user.setEmail("test@test.com");
//		
//		user = userService.getUser("testUserId");
//
//		//==> console Ȯ��
//		//System.out.println(user);
//		
//		//==> API Ȯ��
//		Assert.assertEquals("testUserId", user.getUserId());
//		Assert.assertEquals("testUserName", user.getUserName());
//		Assert.assertEquals("testPasswd", user.getPassword());
//		Assert.assertEquals("111-2222-3333", user.getPhone());
//		Assert.assertEquals("��⵵", user.getAddr());
//		Assert.assertEquals("test@test.com", user.getEmail());
//
//		Assert.assertNotNull(userService.getUser("user02"));
//		Assert.assertNotNull(userService.getUser("user05"));
//	}
//	
//	//@Test
//	 public void testUpdateUser() throws Exception{
//		 
//		User user = userService.getUser("testUserId");
//		Assert.assertNotNull(user);
//		
//		Assert.assertEquals("testUserName", user.getUserName());
//		Assert.assertEquals("111-2222-3333", user.getPhone());
//		Assert.assertEquals("��⵵", user.getAddr());
//		Assert.assertEquals("test@test.com", user.getEmail());
//
//		user.setUserName("change");
//		user.setPhone("777-7777-7777");
//		user.setAddr("change");
//		user.setEmail("change@change.com");
//		
//		userService.updateUser(user);
//		
//		user = userService.getUser("testUserId");
//		Assert.assertNotNull(user);
//		
//		//==> console Ȯ��
//		//System.out.println(user);
//			
//		//==> API Ȯ��
//		Assert.assertEquals("change", user.getUserName());
//		Assert.assertEquals("777-7777-7777", user.getPhone());
//		Assert.assertEquals("change", user.getAddr());
//		Assert.assertEquals("change@change.com", user.getEmail());
//	 }
//	 
//	//@Test
//	public void testCheckDuplication() throws Exception{
//
//		//==> �ʿ��ϴٸ�...
////			User user = new User();
////			user.setUserId("testUserId");
////			user.setUserName("testUserName");
////			user.setPassword("testPasswd");
////			user.setSsn("1111112222222");
////			user.setPhone("111-2222-3333");
////			user.setAddr("��⵵");
////			user.setEmail("test@test.com");
////			
////			userService.addUser(user);
//		
//		//==> console Ȯ��
//		//System.out.println(userService.checkDuplication("testUserId"));
//		//System.out.println(userService.checkDuplication("testUserId"+System.currentTimeMillis()) );
//	 	
//		//==> API Ȯ��
//		Assert.assertFalse( userService.checkDuplication("testUserId") );
//	 	Assert.assertTrue( userService.checkDuplication("testUserId"+System.currentTimeMillis()) );
//		 	
//	}
//	
//	 //==>  �ּ��� Ǯ�� �����ϸ�....
//	 //@Test
//	 public void testGetUserListAll() throws Exception{
//		 
//	 	Search search = new Search();
//	 	search.setCurrentPage(1);
//	 	search.setPageSize(3);
//	 	Map<String,Object> map = userService.getUserList(search);
//	 	
//	 	List<Object> list = (List<Object>)map.get("list");
//	 	Assert.assertEquals(3, list.size());
//	 	
//		//==> console Ȯ��
//	 	//System.out.println(list);
//	 	
//	 	Integer totalCount = (Integer)map.get("totalCount");
//	 	System.out.println(totalCount);
//	 	
//	 	System.out.println("=======================================");
//	 	
//	 	search.setCurrentPage(1);
//	 	search.setPageSize(3);
//	 	search.setSearchCondition("0");
//	 	search.setSearchKeyword("");
//	 	map = userService.getUserList(search);
//	 	
//	 	list = (List<Object>)map.get("list");
//	 	Assert.assertEquals(3, list.size());
//	 	
//	 	//==> console Ȯ��
//	 	//System.out.println(list);
//	 	
//	 	totalCount = (Integer)map.get("totalCount");
//	 	System.out.println(totalCount);
//	 }
//	 
//	 //@Test
//	 public void testGetUserListByUserId() throws Exception{
//		 
//	 	Search search = new Search();
//	 	search.setCurrentPage(1);
//	 	search.setPageSize(3);
//	 	search.setSearchCondition("0");
//	 	search.setSearchKeyword("admin");
//	 	Map<String,Object> map = userService.getUserList(search);
//	 	
//	 	List<Object> list = (List<Object>)map.get("list");
//	 	Assert.assertEquals(1, list.size());
//	 	
//		//==> console Ȯ��
//	 	//System.out.println(list);
//	 	
//	 	Integer totalCount = (Integer)map.get("totalCount");
//	 	System.out.println(totalCount);
//	 	
//	 	System.out.println("=======================================");
//	 	
//	 	search.setSearchCondition("0");
//	 	search.setSearchKeyword(""+System.currentTimeMillis());
//	 	map = userService.getUserList(search);
//	 	
//	 	list = (List<Object>)map.get("list");
//	 	Assert.assertEquals(0, list.size());
//	 	
//		//==> console Ȯ��
//	 	//System.out.println(list);
//	 	
//	 	totalCount = (Integer)map.get("totalCount");
//	 	System.out.println(totalCount);
//	 }
//	 
//	 //@Test
//	 public void testGetUserListByUserName() throws Exception{
//		 
//	 	Search search = new Search();
//	 	search.setCurrentPage(1);
//	 	search.setPageSize(3);
//	 	search.setSearchCondition("1");
//	 	search.setSearchKeyword("SCOTT");
//	 	Map<String,Object> map = userService.getUserList(search);
//	 	
//	 	List<Object> list = (List<Object>)map.get("list");
//	 	Assert.assertEquals(3, list.size());
//	 	
//		//==> console Ȯ��
//	 	//System.out.println(list);
//	 	
//	 	Integer totalCount = (Integer)map.get("totalCount");
//	 	System.out.println(totalCount);
//	 	
//	 	System.out.println("=======================================");
//	 	
//	 	search.setSearchCondition("1");
//	 	search.setSearchKeyword(""+System.currentTimeMillis());
//	 	map = userService.getUserList(search);
//	 	
//	 	list = (List<Object>)map.get("list");
//	 	Assert.assertEquals(0, list.size());
//	 	
//		//==> console Ȯ��
//	 	//System.out.println(list);
//	 	
//	 	totalCount = (Integer)map.get("totalCount");
//	 	System.out.println(totalCount);
//	 }	 
}