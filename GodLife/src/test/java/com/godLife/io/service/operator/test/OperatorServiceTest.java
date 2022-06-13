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
import com.godLife.io.service.domain.Point;
import com.godLife.io.service.domain.User;
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
		operatorEvents.setEventNo(Integer.parseInt(("4")));
		operatorEvents.setEventTitle("이벤트제목테스트");
		operatorEvents.setThumbnailImg("newEventThumbNail.jpg");
		operatorEvents.setDetail("이벤트내용테스트");
		operatorEvents.setEventImg("newEventImg.jpg");
		operatorEvents.setRoullJoinPoint(Integer.parseInt(("1000")));
		
		operatorService.addOperatorEvents(operatorEvents);
		
		//==> console check
		System.out.println(operatorEvents);
		
		//==> API check
		Assert.assertEquals(4, operatorEvents.getEventNo());
		Assert.assertEquals("이벤트제목테스트", operatorEvents.getEventTitle());
		Assert.assertEquals("newEventThumbNail.jpg", operatorEvents.getThumbnailImg());
		Assert.assertEquals("이벤트내용테스트", operatorEvents.getDetail());
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
	 //@Test
	 public void testAddOperatorNoticeFaqs() throws Exception {
		 
		 OperatorNoticeFaqs operatorNoticeFaqs = new OperatorNoticeFaqs();
		 //operatorNoticeFaqs.setNoticeFaqNo(Integer.parseInt(("10003"))); //seq_notice_faqs_notice_faq_no.NEXTVAL
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
	 
	 //@Test
	 public void testGetOperatorNoticeFaqs() throws Exception {
		 
		 OperatorNoticeFaqs operatorNoticeFaqs = new OperatorNoticeFaqs();
		 
		 operatorNoticeFaqs.setStatus("1"); //Notice:0	Faqs:1
		 operatorNoticeFaqs.setNoticeMust("0");	//default:0	Must:1 		
		 operatorNoticeFaqs.setTitle("인증은 어떻게 하는건가요?");
		 operatorNoticeFaqs.setDetail("FAQ 게시판입니다. 인증을 하려면 인증을 하세요.");
		 //operatorNoticeFaqs.setImg("account.jpg");
		 operatorNoticeFaqs.setFaqTag("0");//certification:0 payment:1 reward:2
		 
		 operatorService.getOperatorNoticeFaqs(10002);
		 
		 //==> console check
		 System.out.println(operatorNoticeFaqs);
		 
		 //==> API check
		 Assert.assertEquals("1", operatorNoticeFaqs.getStatus());
		 Assert.assertEquals("0", operatorNoticeFaqs.getNoticeMust());
		 Assert.assertEquals("인증은 어떻게 하는건가요?", operatorNoticeFaqs.getTitle());
		 Assert.assertEquals("FAQ 게시판입니다. 인증을 하려면 인증을 하세요.", operatorNoticeFaqs.getDetail());
		 //Assert.assertEquals("account.jpg", operatorNoticeFaqs.getImg());
		 Assert.assertEquals("0", operatorNoticeFaqs.getFaqTag());
		 
		 Assert.assertNotNull(operatorService.getOperatorNoticeFaqs(10002));
		 
	 }
	 
	 //@Test
	 public void testUpdateOperatorNoticeFaqs() throws Exception{
		 
		 OperatorNoticeFaqs operatorNoticeFaqs = operatorService.getOperatorNoticeFaqs(10003);
		 Assert.assertNotNull(operatorNoticeFaqs);
		 
		 Assert.assertEquals("1", operatorNoticeFaqs.getStatus());	//Notice:0	Faqs:1
		 Assert.assertEquals("0", operatorNoticeFaqs.getNoticeMust());	//default:0	Must:1 
		 Assert.assertEquals("결제는 어떻게 하는거죠?", operatorNoticeFaqs.getTitle());
		 Assert.assertEquals("1.계좌이체 2.간편결제 3.카톡페이", operatorNoticeFaqs.getDetail());
		 Assert.assertEquals("account.jpg", operatorNoticeFaqs.getImg());
		 Assert.assertEquals("1", operatorNoticeFaqs.getFaqTag());	//certification:0 payment:1 reward:2
		 
		 operatorNoticeFaqs.setStatus("1");
		 operatorNoticeFaqs.setNoticeMust("1");
		 operatorNoticeFaqs.setTitle("결제는 어떻게 하는거죠?");
		 operatorNoticeFaqs.setDetail("1.계좌이체 2.간편결제 3.카톡페이");
		 operatorNoticeFaqs.setImg("account.jpg");
		 operatorNoticeFaqs.setFaqTag("1");
		 
		 operatorService.updateOperatorNoticeFaqs(operatorNoticeFaqs);
		 
		 operatorNoticeFaqs = operatorService.getOperatorNoticeFaqs(10003);
		 Assert.assertNotNull(operatorNoticeFaqs);
		 
		 //==> console check
		 System.out.println(operatorNoticeFaqs);
		 
		 //==> API check
		 Assert.assertEquals("1", operatorNoticeFaqs.getStatus());
		 Assert.assertEquals("1", operatorNoticeFaqs.getNoticeMust());
		 Assert.assertEquals("결제는 어떻게 하는거죠?", operatorNoticeFaqs.getTitle());
		 Assert.assertEquals("1.계좌이체 2.간편결제 3.카톡페이", operatorNoticeFaqs.getDetail());
		 Assert.assertEquals("account.jpg", operatorNoticeFaqs.getImg());
		 Assert.assertEquals("1", operatorNoticeFaqs.getFaqTag());
		 
	 }
	 
	 @Test	
	 public void testGetOperatorNoticeFaqsListAll() throws Exception{
		 
		 Search search = new Search();
		 User user = new User();
		 OperatorNoticeFaqs operatorNoticeFaqs = new OperatorNoticeFaqs();
		 
		 search.setCurrentPage(1);
		 search.setPageSize(0);
		 //Map<String,Object> map = operatorService.getOperatorNoticeFaqsList(search);
		 Map<String,Object> map = operatorService.getOperatorNoticeFaqsList(search, user, operatorNoticeFaqs);
		 
		 List<Object> list = (List<Object>)map.get("list");
		 Assert.assertEquals(0, list.size());
		 
		 //==> console check
		 System.out.println(list);
		 
		 Integer totalCount = (Integer)map.get("totalCount");
		 System.out.println(totalCount);
		 
		 System.out.println("=======================================");
		 
		 search.setCurrentPage(1);
		 search.setPageSize(0);
		 search.setSearchCondition("0");
		 search.setSearchKeyword("");
		 map = operatorService.getOperatorNoticeFaqsList(search, user, operatorNoticeFaqs);
		 
		 list = (List<Object>)map.get("list");
		 Assert.assertEquals(0, list.size());
		 
		 //==> console check
		 System.out.println(list);
		 
		 totalCount = (Integer)map.get("totalCount");
		 System.out.println(totalCount);
		 
	 }
	 
	 //@Test
	 public void testGetOperatorNoticeFaqsListByNoticeFaqNo() throws Exception{
		 
		 Search search = new Search();
		 User user = new User();
		 OperatorNoticeFaqs operatorNoticeFaqs = new OperatorNoticeFaqs();
		 
		 search.setCurrentPage(0);
		 search.setPageSize(3);
		 search.setSearchCondition("0");
		 search.setSearchKeyword("10001");
		 Map<String,Object> map = operatorService.getOperatorNoticeFaqsList(search, user, operatorNoticeFaqs);
		 
		 List<Object> list = (List<Object>)map.get("list");
		 Assert.assertEquals(0, list.size());
		 
		 //==> console check
		 System.out.println(list);
		 
		 Integer totalCount = (Integer)map.get("totalCount");
		 System.out.println(totalCount);
		 
		 System.out.println("=======================================");
		 
		 search.setSearchCondition("0");
		 search.setSearchKeyword(""+System.currentTimeMillis());
		 map = operatorService.getOperatorNoticeFaqsList(search, user, operatorNoticeFaqs);
		 
		 list = (List<Object>)map.get("list");
		 Assert.assertEquals(0, list.size());
		 
		 //==> console check
		 System.out.println(list);
		 
		 totalCount = (Integer)map.get("totalCount");
		 System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetOperatorNoticeFaqsListByTitle() throws Exception{
		 
		 Search search = new Search();
		 User user = new User();
		 OperatorNoticeFaqs operatorNoticeFaqs = new OperatorNoticeFaqs();
		 
		 search.setCurrentPage(1);
		 search.setPageSize(3);
		 search.setSearchCondition("1");
		 search.setSearchKeyword("결제는 어떻게 하는거죠?");
		 Map<String,Object> map = operatorService.getOperatorNoticeFaqsList(search, user, operatorNoticeFaqs);
		 
		 List<Object> list = (List<Object>)map.get("list");
		 Assert.assertEquals(0, list.size());
		 
		 //==> console check
		 System.out.println(list);
		 
		 Integer totalCount = (Integer)map.get("totalCount");
		 System.out.println(totalCount);
		 
		 System.out.println("=======================================");
		 
		 search.setSearchCondition("1");
		 search.setSearchKeyword(""+System.currentTimeMillis());
		 map = operatorService.getOperatorNoticeFaqsList(search, user, operatorNoticeFaqs);
		 
		 list = (List<Object>)map.get("list");
		 Assert.assertEquals(0, list.size());
		 
		 //==> console check
		 System.out.println(list);
		 
		 totalCount = (Integer)map.get("totalCount");
		 System.out.println(totalCount);
	 }
	 
	 //OperatorJoinEvent
	 //@Test
	 public void testAddOperatorJoinEvent() throws Exception {
		 
		 OperatorJoinEvent operatorJoinEvent = new OperatorJoinEvent();
		 //operatorJoinEvent.setJoinEventNo(Integer.parseInt(("10011"))); //seq_notice_faqs_notice_faq_no.NEXTVAL
		 operatorJoinEvent.setUserEmail("user01.io.com");
		 operatorJoinEvent.setEventNo(2);
		 operatorJoinEvent.setRewardPoint(1000);
		 
		 
		 operatorService.addOperatorJoinEvent(operatorJoinEvent);
		 
		 //==> console check
		 System.out.println(operatorJoinEvent);
		 
		 //==> API check
		 Assert.assertEquals("user01.io.com", operatorJoinEvent.getUserEmail());
	 }
	 
	 //2022-06-07 19:32 shhwang Error
	 //2022-06-08 10:26 shhwang	Clear (As unsatisfied parameter type, but remain and modify sql)
	 //2022-06-10 14:50 shhwang clear
	 //@Test
	 public void testGetOperatorJoinEvent() throws Exception {
		 
		 OperatorJoinEvent operatorJoinEvent = new OperatorJoinEvent();
		 OperatorReward operatorReward = new OperatorReward();
		 
		 operatorJoinEvent.setJoinEventNo(Integer.parseInt(("10005")));
		 //operatorJoinEvent.setUserEmail("user02.io.com");
		 //operatorReward.setEventNo(Integer.parseInt(("1")));
		 //operatorReward.setRewardPoint(2);
		 
		 operatorService.getOperatorJoinEvent(10005);
		 
		 //==> console check
		 System.out.println(operatorJoinEvent);
		 
		 //==> API check
		 Assert.assertEquals(10005, operatorJoinEvent.getJoinEventNo());
		 //Assert.assertEquals("user02.io.com", operatorJoinEvent.getUserEmail());
		 //Assert.assertEquals(1, operatorReward.getEventNo());
		 //Assert.assertEquals(2, operatorReward.getRewardPoint());
		 
		 Assert.assertNotNull(operatorService.getOperatorJoinEvent(10005));
		 
	 }
	 
	 //@Test	
	 public void testGetOperatorJoinEventListAll() throws Exception{
		 
		 Search search = new Search();
		 search.setCurrentPage(1);
		 search.setPageSize(3);
		 Map<String,Object> map = operatorService.getOperatorJoinEventList(search);
		 
		 List<Object> list = (List<Object>)map.get("list");
		 Assert.assertEquals(6, list.size());
		 
		 //==> console check
		 System.out.println(list);
		 
		 Integer totalCount = (Integer)map.get("totalCount");
		 System.out.println(totalCount);
		 
		 System.out.println("=======================================");
		 
		 search.setCurrentPage(1);
		 search.setPageSize(3);
		 search.setSearchCondition("0");
		 search.setSearchKeyword("");
		 map = operatorService.getOperatorJoinEventList(search);
		 
		 list = (List<Object>)map.get("list");
		 Assert.assertEquals(6, list.size());
		 
		 //==> console check
		 System.out.println(list);
		 
		 totalCount = (Integer)map.get("totalCount");
		 System.out.println(totalCount);
		 
	 }
	 
	 //@Test
	 public void testGetOperatorJoinEventListByJoinEventNo() throws Exception{
		 
		 Search search = new Search();
		 search.setCurrentPage(1);
		 search.setPageSize(3);
		 search.setSearchCondition("0");
		 search.setSearchKeyword("10000");
		 Map<String,Object> map = operatorService.getOperatorJoinEventList(search);
		 
		 List<Object> list = (List<Object>)map.get("list");
		 Assert.assertEquals(6, list.size());
		 
		 //==> console check
		 System.out.println(list);
		 
		 Integer totalCount = (Integer)map.get("totalCount");
		 System.out.println(totalCount);
		 
		 System.out.println("=======================================");
		 
		 search.setSearchCondition("0");
		 search.setSearchKeyword(""+System.currentTimeMillis());
		 map = operatorService.getOperatorJoinEventList(search);
		 
		 list = (List<Object>)map.get("list");
		 Assert.assertEquals(0, list.size());
		 
		 //==> console check
		 System.out.println(list);
		 
		 totalCount = (Integer)map.get("totalCount");
		 System.out.println(totalCount);
	 }
	 
	 //@Test
	 public void testGetOperatorJoinEventListByUserEmail() throws Exception{
		 
		 Search search = new Search();
		 search.setCurrentPage(1);
		 search.setPageSize(3);
		 search.setSearchCondition("1");
		 search.setSearchKeyword("user05.io.com");
		 Map<String,Object> map = operatorService.getOperatorJoinEventList(search);
		 
		 List<Object> list = (List<Object>)map.get("list");
		 Assert.assertEquals(24, list.size());
		 
		 //==> console check
		 System.out.println(list);
		 
		 Integer totalCount = (Integer)map.get("totalCount");
		 System.out.println(totalCount);
		 
		 System.out.println("=======================================");
		 
		 search.setSearchCondition("1");
		 search.setSearchKeyword(""+System.currentTimeMillis());
		 map = operatorService.getOperatorJoinEventList(search);
		 
		 list = (List<Object>)map.get("list");
		 Assert.assertEquals(0, list.size());
		 
		 //==> console check
		 System.out.println(list);
		 
		 totalCount = (Integer)map.get("totalCount");
		 System.out.println(totalCount);
		 
	 }
	 
	 //Reward shhwang 2022-06-08 16:48 
	 //@Test
	 public void testAddOperatorReward() throws Exception {
		 
		 OperatorReward operatorReward = new OperatorReward();
		 
		 operatorReward.setRewardNo(Integer.parseInt(("10006")));
		 operatorReward.setEventNo(Integer.parseInt(("2")));
		 operatorReward.setRewardPoint(1000);
		 
		 operatorService.addOperatorReward(operatorReward);
		 
		 //==> console check
		 System.out.println(operatorReward);
		
		//==> API check
		Assert.assertEquals(10006, operatorReward.getRewardNo());
		Assert.assertEquals(2, operatorReward.getEventNo());
		Assert.assertEquals(1000, operatorReward.getRewardPoint());
	}
	 
	//shhwang 2022-06-08 18:29 실행완료 됐는데 초록불이 안 들어오고 테이블에도 update 안 들어온다 뭐지?
	//shhwang 2022-06-09 09:43 clear 
	//@Test
	public void testUpdateOperatorRoullReward() throws Exception{
		
		OperatorReward operatorRoullReward = operatorService.getOperatorReward(10022);
		Assert.assertNotNull(operatorRoullReward);
		 
		Assert.assertEquals("1", operatorRoullReward.getRewardPoint());
		
		operatorRoullReward.setRewardPoint(2);
		
		operatorService.updateOperatorRoullReward(operatorRoullReward);
		
		operatorRoullReward = operatorService.getOperatorReward(10011);
		Assert.assertNotNull(operatorRoullReward);
		 
		//==> console check
		System.out.println(operatorRoullReward);
			
		//==> API check
		Assert.assertEquals("2", operatorRoullReward.getRewardPoint());
		
	}
	//shhwang 2022-06-08 19:49 작성 중
	//shhwang 2022-06-09 09:54 자고 일어나나까 돌아간다 근데 update 가 안된다
	//@Test
	public void testUpdateOperatorDayReward() throws Exception{
		OperatorJoinEvent user = new OperatorJoinEvent();
		user.setUserEmail("user06.io.com");
		OperatorReward operatorDayReward = operatorService.getOperatorReward(10028);
		Assert.assertNotNull(operatorDayReward);
		//OperatorReward operatorDayReward = operatorService.getOperatorReward(10028);
		//Assert.assertNotNull(operatorDayReward);
		
		Assert.assertEquals("1", operatorDayReward.getRewardPoint());
		
		operatorDayReward.setRewardPoint(5);
		
		operatorService.updateOperatorDayReward(operatorDayReward, user);
		//operatorService.updateOperatorDayReward(operatorDayReward);
		
		operatorDayReward = operatorService.getOperatorReward(10028);
		Assert.assertNotNull(operatorDayReward);
		
		//==> console check
		System.out.println(operatorDayReward);
		
		//==> API check
		Assert.assertEquals("1", operatorDayReward.getRewardPoint());
		
	}
	 
}