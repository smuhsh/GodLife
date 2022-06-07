package com.godLife.io.service.operator.test;

import java.sql.Date;
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
import com.godLife.io.service.domain.OperatorJoinEvent;
import com.godLife.io.service.domain.OperatorNoticeFaqs;
import com.godLife.io.service.domain.OperatorReward;
import com.godLife.io.service.operator.OperatorService;
import com.sun.glass.ui.Pixels.Format;



@RunWith(SpringJUnit4ClassRunner.class)

//==> Meta-Data Wiring
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
	
	//OperatorEvents
	//@Test
	public void testAddOperatorEvents() throws Exception {
		
		OperatorEvents operatorEvents = new OperatorEvents();
		operatorEvents.setEventNo(Integer.parseInt(("10")));
		operatorEvents.setEventTitle("final Event Title Test");
		operatorEvents.setThumbnailImg("newEventThumbNail.jpg");
		operatorEvents.setDetail("neweventDetail");
		operatorEvents.setEventImg("newEventImg.jpg");
		operatorEvents.setRoullJoinPoint(Integer.parseInt(("1000")));
		
		operatorService.addOperatorEvents(operatorEvents);
		
		//==> console check
		System.out.println(operatorEvents);
		
		//==> API check
		Assert.assertEquals(10, operatorEvents.getEventNo());
		Assert.assertEquals("final Event Title Test", operatorEvents.getEventTitle());
		Assert.assertEquals("newEventThumbNail.jpg", operatorEvents.getThumbnailImg());
		Assert.assertEquals("neweventDetail", operatorEvents.getDetail());
		Assert.assertEquals("newEventImg.jpg", operatorEvents.getEventImg());
		Assert.assertEquals(1000, operatorEvents.getRoullJoinPoint());
	}
	
	//@Test
	public void testGetOperatorEvents() throws Exception {
		
		OperatorEvents operatorEvents = new OperatorEvents();
		
		operatorEvents.setEventNo(Integer.parseInt(("8")));
		operatorEvents.setEventTitle("final Event Title Test");
		operatorEvents.setThumbnailImg("newEventThumbNail.jpg");
		operatorEvents.setDetail("neweventDetail");
		operatorEvents.setEventImg("newEventImg.jpg");
		operatorEvents.setRoullJoinPoint(Integer.parseInt(("1000")));
		
		operatorEvents = operatorService.getOperatorEvents(8);

		//==> console check
		System.out.println(operatorEvents);
		
		//==> API check
		Assert.assertEquals(8, operatorEvents.getEventNo());
		Assert.assertEquals("final Event Title Test", operatorEvents.getEventTitle());
		Assert.assertEquals("newEventThumbNail.jpg", operatorEvents.getThumbnailImg());
		Assert.assertEquals("neweventDetail", operatorEvents.getDetail());
		Assert.assertEquals("newEventImg.jpg", operatorEvents.getEventImg());
		Assert.assertEquals(1000, operatorEvents.getRoullJoinPoint());

		Assert.assertNotNull(operatorService.getOperatorEvents(8));
		
	}
	
	//@Test
	public void testupdateOperatorEvents() throws Exception{
		
		OperatorEvents operatorEvents = operatorService.getOperatorEvents(8);
		Assert.assertNotNull(operatorEvents);
		 
		Assert.assertEquals("final Event Title Test", operatorEvents.getEventTitle());
		Assert.assertEquals("newEventThumbNail.jpg", operatorEvents.getThumbnailImg());
		Assert.assertEquals("neweventDetail", operatorEvents.getDetail());
		Assert.assertEquals("newEventImg.jpg", operatorEvents.getEventImg());
		
		operatorEvents.setEventTitle("핑크빈이 돌아왔다!");
		operatorEvents.setThumbnailImg("pinkbeanThumbnailImg.gif");
		operatorEvents.setDetail("핑아일체에 도전하세요.");
		operatorEvents.setEventImg("pinkbean.gif");
		
		operatorService.updateOperatorEvents(operatorEvents);
		
		operatorEvents = operatorService.getOperatorEvents(8);
		Assert.assertNotNull(operatorEvents);
		 
		//==> console check
		System.out.println(operatorEvents);
			
		//==> API check
		Assert.assertEquals("핑크빈이 돌아왔다!", operatorEvents.getEventTitle());
		Assert.assertEquals("pinkbeanThumbnailImg.gif", operatorEvents.getThumbnailImg());
		Assert.assertEquals("핑아일체에 도전하세요.", operatorEvents.getDetail());
		Assert.assertEquals("pinkbean.gif", operatorEvents.getEventImg());
		
	}
	
	//@Test	
	public void testGetOperatorEventsListAll() throws Exception{
		
		Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	Map<String,Object> map = operatorService.getOperatorEventsList(search);

	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console check
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("");
	 	map = operatorService.getOperatorEventsList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(3, list.size());
	 	
		//==> console check
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	}
	
	//@Test
	public void testGetOperatorEventsListByEventNo() throws Exception{
		
		Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword("10");
	 	Map<String,Object> map = operatorService.getOperatorEventsList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console check
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("0");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = operatorService.getOperatorEventsList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console check
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetOperatorEventsListByEventTitle() throws Exception{
		 
	 	Search search = new Search();
	 	search.setCurrentPage(1);
	 	search.setPageSize(3);
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword("핑크빈이 돌아왔다!");
	 	Map<String,Object> map = operatorService.getOperatorEventsList(search);
	 	
	 	List<Object> list = (List<Object>)map.get("list");
	 	Assert.assertEquals(1, list.size());
	 	
		//==> console check
	 	System.out.println(list);
	 	
	 	Integer totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 	
	 	System.out.println("=======================================");
	 	
	 	search.setSearchCondition("1");
	 	search.setSearchKeyword(""+System.currentTimeMillis());
	 	map = operatorService.getOperatorEventsList(search);
	 	
	 	list = (List<Object>)map.get("list");
	 	Assert.assertEquals(0, list.size());
	 	
		//==> console check
	 	System.out.println(list);
	 	
	 	totalCount = (Integer)map.get("totalCount");
	 	System.out.println(totalCount);
	 }
	 
	 //OperatorNoticeFaqs
	 @Test
	 public void testAddOperatorNoticeFaqs() throws Exception {
		 
		 OperatorNoticeFaqs operatorNoticeFaqs = new OperatorNoticeFaqs();
//		 operatorNoticeFaqs.setNoticeFaqNo(Integer.parseInt(("10003")));
		 operatorNoticeFaqs.setUserEmail("admin@io.com");
		 operatorNoticeFaqs.setStatus("1");	//Notice:0	Faqs:1
		 operatorNoticeFaqs.setNoticeMust("0");	//default:0	Must:1 		
		 operatorNoticeFaqs.setTitle("결제는 어떻게 하는거죠?");
		 operatorNoticeFaqs.setDetail("계좌이체 하세요.");
		 operatorNoticeFaqs.setImg("account.jpg");
		 operatorNoticeFaqs.setFaqTag("1");//certification:0 payment:1 reward:2
		 
		 operatorService.addOperatorNoticeFaqs(operatorNoticeFaqs);
		 
		 //==> console check
		 System.out.println(operatorNoticeFaqs);
		 
		 //==> API check
		 Assert.assertEquals("admin@io.com", operatorNoticeFaqs.getUserEmail());
		 Assert.assertEquals("1", operatorNoticeFaqs.getStatus());
		 Assert.assertEquals("0", operatorNoticeFaqs.getNoticeMust());
		 Assert.assertEquals("결제는 어떻게 하는거죠?", operatorNoticeFaqs.getTitle());
		 Assert.assertEquals("계좌이체 하세요.", operatorNoticeFaqs.getDetail());
		 Assert.assertEquals("account.jpg", operatorNoticeFaqs.getImg());
		 Assert.assertEquals("1", operatorNoticeFaqs.getFaqTag());
	 }
	 
//	 //@Test
//	 public void testGetOperatorEvents() throws Exception {
//		 
//		 OperatorEvents operatorEvents = new OperatorEvents();
//		 
//		 operatorEvents.setEventNo(Integer.parseInt(("8")));
//		 operatorEvents.setEventTitle("final Event Title Test");
//		 operatorEvents.setThumbnailImg("newEventThumbNail.jpg");
//		 operatorEvents.setDetail("neweventDetail");
//		 operatorEvents.setEventImg("newEventImg.jpg");
//		 operatorEvents.setRoullJoinPoint(Integer.parseInt(("1000")));
//		 
//		 operatorEvents = operatorService.getOperatorEvents(8);
//		 
//		 //==> console check
//		 System.out.println(operatorEvents);
//		 
//		 //==> API check
//		 Assert.assertEquals(8, operatorEvents.getEventNo());
//		 Assert.assertEquals("final Event Title Test", operatorEvents.getEventTitle());
//		 Assert.assertEquals("newEventThumbNail.jpg", operatorEvents.getThumbnailImg());
//		 Assert.assertEquals("neweventDetail", operatorEvents.getDetail());
//		 Assert.assertEquals("newEventImg.jpg", operatorEvents.getEventImg());
//		 Assert.assertEquals(1000, operatorEvents.getRoullJoinPoint());
//		 
//		 Assert.assertNotNull(operatorService.getOperatorEvents(8));
//		 
//	 }
//	 
//	 //@Test
//	 public void testupdateOperatorEvents() throws Exception{
//		 
//		 OperatorEvents operatorEvents = operatorService.getOperatorEvents(8);
//		 Assert.assertNotNull(operatorEvents);
//		 
//		 Assert.assertEquals("final Event Title Test", operatorEvents.getEventTitle());
//		 Assert.assertEquals("newEventThumbNail.jpg", operatorEvents.getThumbnailImg());
//		 Assert.assertEquals("neweventDetail", operatorEvents.getDetail());
//		 Assert.assertEquals("newEventImg.jpg", operatorEvents.getEventImg());
//		 
//		 operatorEvents.setEventTitle("핑크빈이 돌아왔다!");
//		 operatorEvents.setThumbnailImg("pinkbeanThumbnailImg.gif");
//		 operatorEvents.setDetail("핑아일체에 도전하세요.");
//		 operatorEvents.setEventImg("pinkbean.gif");
//		 
//		 operatorService.updateOperatorEvents(operatorEvents);
//		 
//		 operatorEvents = operatorService.getOperatorEvents(8);
//		 Assert.assertNotNull(operatorEvents);
//		 
//		 //==> console check
//		 System.out.println(operatorEvents);
//		 
//		 //==> API check
//		 Assert.assertEquals("핑크빈이 돌아왔다!", operatorEvents.getEventTitle());
//		 Assert.assertEquals("pinkbeanThumbnailImg.gif", operatorEvents.getThumbnailImg());
//		 Assert.assertEquals("핑아일체에 도전하세요.", operatorEvents.getDetail());
//		 Assert.assertEquals("pinkbean.gif", operatorEvents.getEventImg());
//		 
//	 }
//	 
//	 //@Test	
//	 public void testGetOperatorEventsListAll() throws Exception{
//		 
//		 Search search = new Search();
//		 search.setCurrentPage(1);
//		 search.setPageSize(3);
//		 Map<String,Object> map = operatorService.getOperatorEventsList(search);
//		 
//		 List<Object> list = (List<Object>)map.get("list");
//		 Assert.assertEquals(3, list.size());
//		 
//		 //==> console check
//		 System.out.println(list);
//		 
//		 Integer totalCount = (Integer)map.get("totalCount");
//		 System.out.println(totalCount);
//		 
//		 System.out.println("=======================================");
//		 
//		 search.setCurrentPage(1);
//		 search.setPageSize(3);
//		 search.setSearchCondition("0");
//		 search.setSearchKeyword("");
//		 map = operatorService.getOperatorEventsList(search);
//		 
//		 list = (List<Object>)map.get("list");
//		 Assert.assertEquals(3, list.size());
//		 
//		 //==> console check
//		 System.out.println(list);
//		 
//		 totalCount = (Integer)map.get("totalCount");
//		 System.out.println(totalCount);
//		 
//	 }
//	 
//	 //@Test
//	 public void testGetOperatorEventsListByEventNo() throws Exception{
//		 
//		 Search search = new Search();
//		 search.setCurrentPage(1);
//		 search.setPageSize(3);
//		 search.setSearchCondition("0");
//		 search.setSearchKeyword("10");
//		 Map<String,Object> map = operatorService.getOperatorEventsList(search);
//		 
//		 List<Object> list = (List<Object>)map.get("list");
//		 Assert.assertEquals(1, list.size());
//		 
//		 //==> console check
//		 System.out.println(list);
//		 
//		 Integer totalCount = (Integer)map.get("totalCount");
//		 System.out.println(totalCount);
//		 
//		 System.out.println("=======================================");
//		 
//		 search.setSearchCondition("0");
//		 search.setSearchKeyword(""+System.currentTimeMillis());
//		 map = operatorService.getOperatorEventsList(search);
//		 
//		 list = (List<Object>)map.get("list");
//		 Assert.assertEquals(0, list.size());
//		 
//		 //==> console check
//		 System.out.println(list);
//		 
//		 totalCount = (Integer)map.get("totalCount");
//		 System.out.println(totalCount);
//	 }
//	 
//	 //@Test
//	 public void testGetOperatorEventsListByEventTitle() throws Exception{
//		 
//		 Search search = new Search();
//		 search.setCurrentPage(1);
//		 search.setPageSize(3);
//		 search.setSearchCondition("1");
//		 search.setSearchKeyword("핑크빈이 돌아왔다!");
//		 Map<String,Object> map = operatorService.getOperatorEventsList(search);
//		 
//		 List<Object> list = (List<Object>)map.get("list");
//		 Assert.assertEquals(1, list.size());
//		 
//		 //==> console check
//		 System.out.println(list);
//		 
//		 Integer totalCount = (Integer)map.get("totalCount");
//		 System.out.println(totalCount);
//		 
//		 System.out.println("=======================================");
//		 
//		 search.setSearchCondition("1");
//		 search.setSearchKeyword(""+System.currentTimeMillis());
//		 map = operatorService.getOperatorEventsList(search);
//		 
//		 list = (List<Object>)map.get("list");
//		 Assert.assertEquals(0, list.size());
//		 
//		 //==> console check
//		 System.out.println(list);
//		 
//		 totalCount = (Integer)map.get("totalCount");
//		 System.out.println(totalCount);
//	 }
	 
}