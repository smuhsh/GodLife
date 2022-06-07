package com.godLife.io.service.user.test;

import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.godLife.io.service.domain.FriendBlack;
import com.godLife.io.service.domain.Msg;
import com.godLife.io.service.domain.User;
import com.godLife.io.service.user.UserService;
import com.godLife.io.service.user.impl.UserServiceImpl;
import com.godLife.io.common.Search;

/*
 *   FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)

//==> Meta-Data 를 다양하게 Wiring 하자...
//@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
@ContextConfiguration   (locations = {   "classpath:config/context-common.xml",
                                                   "classpath:config/context-aspect.xml",
                                                   "classpath:config/context-mybatis.xml",
                                                   "classpath:config/context-transaction.xml" })
//@ContextConfiguration(locations = { "classpath:config/context-common.xml" })
public class UserServiceTest {

   //==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
   @Autowired
   @Qualifier("userServiceImpl")
   private UserService userService;

   //@Test
   public void testAddUser() throws Exception {
      
      User user = new User();
      
      user.setUserEmail("lovejuf@naver.com");
      user.setPwd("1111");
      user.setNick("화이팅");
      user.setCategNo(2);
      user.setPhone("01080077545");
      
      userService.addUser(user);
      
   }
   
   @Test
   public void testGetUser() throws Exception {
      
      User user = new User();
      
      user = userService.getUser("chilee4650@naver.com");
      
      System.out.println(user);
      

      Assert.assertEquals("chilee4650@naver.com", user.getUserEmail());

   
   }   

   //@Test
   public void testUpdatePwd() throws Exception{
       
      User user = userService.getUser("lovejuf@naver.com");
      
      user.setPwd("dltndk1234");
      
      userService.updatePwd(user);
      
      user = userService.getUser("testUserId");
      }
   
   //@Test
   public void testUpdateUser() throws Exception{
       
      User user = userService.getUser("tndkdml@gmail.com");
      
      user.setNick("갓갓라이프");
         
      userService.updateUser(user);
   //   user = userService.getUser("testUserId");
      }
   

   //@Test 
   public void testGetFriendBlackListAll() throws Exception{
      
      Search search = new Search();
      search.setCurrentPage(1);
      search.setPageSize(3);
      
//          FriendBlack friendBlack = new FriendBlack();
//          friendBlack.setUserEmail("chilee4650@naver.com");
          
      Map<String,Object> map = userService.getFriendList(search, "chilee4650@naver.com");
      
      List<Object> list = (List<Object>)map.get("list");
      Assert.assertEquals(1, list.size());
      
     //==> console 확인
      System.out.println("리스트" +list);
      
          Integer totalCount = (Integer)map.get("totalCount");
          System.out.println("토탈카운트 : "+totalCount);
      
      System.out.println("=======================================");
      
      search.setCurrentPage(1);
      search.setPageSize(3);
      search.setSearchCondition("0");
      search.setSearchKeyword("");
      
      map = userService.getFriendList(search, "chilee4650@naver.com");
      
      list = (List<Object>)map.get("list");
      Assert.assertEquals(1, list.size());
      
      //==> console 확인
      System.out.println(list);
      
      totalCount = (Integer)map.get("totalCount");
      System.out.println(map);
   }
       
       //@Test 
       public void testGetFriendRequestListAll() throws Exception{
          
          Search search = new Search();
          search.setCurrentPage(1);
          search.setPageSize(3);
          
          Map<String,Object> map = userService.getFriendRequestList(search, "kimhoyam@hotmail.com");
          
          List<Object> list = (List<Object>)map.get("list");
          Assert.assertEquals(3, list.size());
          
         //==> console 확인
          System.out.println(list);
          
          Integer totalCount = (Integer)map.get("totalCount");
         System.out.println("토탈카운트 :"+totalCount);
          
          System.out.println("=======================================");
          
          search.setCurrentPage(1);
          search.setPageSize(3);
         search.setSearchCondition("0");
          search.setSearchKeyword("");
          
          map = userService.getFriendRequestList(search, "kimhoyam@hotmail.com");
          
          list = (List<Object>)map.get("list");
          Assert.assertEquals(3, list.size());
          
          //==> console 확인
          System.out.println(list);
          
         totalCount = (Integer)map.get("totalCount");
          System.out.println(map);
       }
   
       
//       @Test // 결과 이상하게 나옴.. 
//       public void testGetFriendBlackListByNick() throws Exception{
//          
//          Search search = new Search();
//          search.setCurrentPage(1);
//          search.setPageSize(3);
//	 	  search.setSearchCondition("0");
//	 	  search.setSearchKeyword("김호");
//	 	  
//	      FriendBlack friendBlack = new FriendBlack();
//	      friendBlack.setUserEmail("tndkdml@gmail.com");
//	          
//          Map<String,Object> map = userService.getFriendBlackList(search, "tndkdml@gmail.com");
//          
//          List<Object> list = (List<Object>)map.get("list");
//          Assert.assertEquals(1, list.size());
//          
//         //==> console 확인
//          System.out.println(list);
//          
////          Integer totalCount = (Integer)map.get("totalCount");
////          System.out.println(totalCount);
//          
//          System.out.println("=======================================");
//          
//          search.setCurrentPage(1);
//          search.setPageSize(3);
//          search.setSearchCondition("0");
//          search.setSearchKeyword("김호");
//          
//          map = userService.getFriendBlackList(search, "tndkdml@gmail.com");
//          
//          list = (List<Object>)map.get("list");
//          Assert.assertEquals(1, list.size());
//          
//          //==> console 확인
//          //System.out.println(list);
//          
////          totalCount = (Integer)map.get("totalCount");
////          System.out.println(map);
//       }
       
   
       
     //@Test
     public void testAddFriend() throws Exception {
        
        FriendBlack friendBlack = new FriendBlack();
        
        friendBlack.setUserEmail("tndkdml@gmail.com");
        friendBlack.setTargetEmail("user04@io.com");
        
        userService.addFriend(friendBlack);
     }
     
     //@Test
     public void testAddBlack() throws Exception {
        
        FriendBlack friendBlack = new FriendBlack();
        
        friendBlack.setUserEmail("tndkdml@gmail.com");
        friendBlack.setTargetEmail("user04@io.com");
        
        userService.addBlack(friendBlack);
     }
   
         //@Test
     public void testUpdateAccStatus() throws Exception{
         
        FriendBlack friendblack =new FriendBlack(); 
        friendblack.setUserEmail("tndkdml@gmail.com");
        friendblack.setTargetEmail("kimhoyam@hotmail.com");
        
        
        friendblack.setAccStatus("2");
            
            userService.updateAccStatus(friendblack);
            }
         
   
            //@Test
        public void testDeleteFriend() throws Exception{
            
           FriendBlack friendblack =new FriendBlack(); 
           friendblack.setUserEmail("tndkdml@gmail.com");
           friendblack.setTargetEmail("chilee4650@naver.com");
           
           friendblack.setAccStatus("2");
               
               userService.deleteFriend(friendblack);
               }   
   
     //@Test
     public void testAddMsg() throws Exception {
        
        Msg msg = new Msg();
        
        msg.setSendEmail("chilee4650@naver.com");
        msg.setRecvEmail("user02@io.com");
        msg.setTitle("안녕하세요");
        msg.setDetail("제 이름은 000입니다");
        
        userService.addMsg(msg);
     }
     
	  //@Test
	  public void testGetRecvMsg() throws Exception {
	     
	     Msg msg = new Msg();
	     
	     msg = userService.getRecvMsg(10000);
	     
	     System.out.println("msg : "+msg);
	   
	  }   
	  
	  //@Test
	  public void testGetSendMsg() throws Exception {
	     
	     Msg msg = new Msg();
	     
	     msg = userService.getSendMsg(10000);
	     
	     System.out.println("msg : "+msg);
	       
	      }  
	   
	 //@Test
	 public void testDeleteMsg() throws Exception{
		 
		 Msg msg = new Msg(); 
		 
		userService.deleteMsg(10000);
		 
		 System.out.println(msg);
		 
		 
		 
		 
	    }   

   
   
   
   
         
         
       
   















}
