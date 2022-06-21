package com.godLife.io.web.user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import org.apache.maven.doxia.module.fml.model.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.godLife.io.service.user.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.godLife.io.common.Page;
import com.godLife.io.common.Search;
import com.godLife.io.service.domain.FriendBlack;
import com.godLife.io.service.domain.Msg;
import com.godLife.io.service.domain.OneInq;
import com.godLife.io.service.domain.Report;
import com.godLife.io.service.domain.User;

import com.godLife.io.service.user.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.godLife.io.common.Page;
import com.godLife.io.common.Search;
import com.godLife.io.service.domain.FriendBlack;
import com.godLife.io.service.domain.Msg;
import com.godLife.io.service.domain.OneInq;
import com.godLife.io.service.domain.User;

//==> 회원관리 Controller
@Controller
@RequestMapping("/user/*")
public class UserController {
   
   ///Field
   @Autowired
   @Qualifier("userServiceImpl")
   private UserService userService;
   //setter Method 구현 않음
      
   public UserController(){
      System.out.println(this.getClass());
   }
   
   @Value("#{commonProperties['pageUnit']}")
   int pageUnit;
   @Value("#{commonProperties['pageSize']}")
   int pageSize;
   
     @Resource(name="uploadPath")
       String uploadPath;
   
   ////////////////////////////////회원관리/////////////////////////////////////////////////////////
   
     //카카오 로그인 
     @GetMapping( "kakaoLogin" )
      public String kakaoLogin( @RequestParam( "code") String code , Model model , HttpSession session ) throws Exception{
          // 사용자 로그인 및  동의 후 , 인가 코드를 발급받아 302 redirect를 통해  ,  이 메소드 도착함

         System.out.println( "##kakaoLogin## 페이지 도착 " );
         System.out.println( "##code {} ##" + code ); // 코드 출력

         // 사용자 정보를 가져오기 위하여 code로  access 토큰 가져오기 !
         String access_Token = userService.getKaKaoAccessToken( code );
         System.out.println( "##access_Token 가져옴 ::  {} ##" + access_Token );

         // 카카오 getUserInfo 에서 access_Token를 통하여  userInfo 가져오기
         Map<String, Object> userInfo = userService.getKakoUserInfo( access_Token );
         System.out.println( "##access_Token {} ##" +access_Token );
         System.out.println( "##email {} ##" + userInfo.get( "email" ) );

         String kakaouserId = ( String ) userInfo.get( "email" );
         User user = new User();
         user.setUserEmail(kakaouserId);
         user.setPwd( "12345" ); // 카카오 로그인시 비밀번호
         if ( userService.getUser(kakaouserId) == null ) {  // 카카로 로그인 ID가 우리 사이트에 존재 x
             System.out.println( "로그인한 카카오 아이디가 존재 하지 않습니다. " );

             model.addAttribute("kakaouserId" ,kakaouserId);

             return "forward:/user/addUserKaKao.jsp"; 

         } else {
             // 카카오 로그인시 ID가 우리 사이트에 존재 할때

             // Model 과 View 연결
             user = userService.getUser(kakaouserId)  ;
             session.setAttribute( "user" , user );
             System.out.println( "로그인 성공. " );

             return "/";
         } // 존재 할때

     }
     
     @PostMapping( value="addUserKaKao") 
     public String addUserKaKao( @ModelAttribute("user") User user, Model model, HttpSession session) throws Exception {
    	 
    	 System.out.println(" addUserKaKao :: 에온 user  "+user);
    	 
    	 user.setPwd("12345");
    	 user.setJoinPath("2");
        //Business Logic
        userService.addUser(user);
        
        user = userService.getUser(user.getUserEmail());
   	 System.out.println(" getUser :: 에온 user  "+user);

     session.setAttribute("user", user);
        
        System.out.println("/user/addUser : POST   끝");

        return "/";
     }   
     
     
   @GetMapping( value="login")  // 완료 
   public String login() throws Exception{
      
      System.out.println("/user/login : GET");

      return "redirect:/user/loginView.jsp"; // 로그인 페이지로 이동 
   }
      
   
   @PostMapping( value="login") // 완료
   public String login(@ModelAttribute("user") User user , HttpSession session, Model model) throws Exception{
      
      System.out.println("/user/login : POST");
      
      //Business Logic
      User dbUser=userService.getUser(user.getUserEmail());
      //User dbUser1 = userService.getUser(user.getAccountStatus());
      
      System.out.println("getUser 결과 : "+dbUser);
      
      //아이디가 없을 경우(알러트창,, 드디어성공) 
      if(dbUser == null){
         model.addAttribute("msg", "아이디 및 비밀번호가 일치하지 않습니다."); // 해당 메세지 알러트창으로 어떻게?
         model.addAttribute("url", "/user/loginView.jsp"); // 메세지 알러트창 
         return "alert.jsp"; 
      }
      
      
      // 계정정지 상태 (이거안됨...왜안될까...)
      if(dbUser.getAccountStatus() == "2") {
         model.addAttribute("msg", "레드카드 3장이상으로 계정정지 상태이며, 로그인할 수 없습니다.");
         model.addAttribute("url", "/user/loginView.jsp"); 
         return "alert.jsp"; 
      }
      
      
      // 일치할경우(로그인성공)
      if( user.getPwd().equals(dbUser.getPwd())){
         session.setAttribute("user", dbUser);
      
      System.out.println("세션 만들어짐...");
      System.out.println(session.getAttribute("user"));
      
      return "/"; // 메인페이지로 이동 
   
      
      //비밀번호가 일치하지않을때 
   }else{
	   model.addAttribute("msg", "아이디 및 비밀번호가 일치하지 않습니다.");
	   model.addAttribute("url", "/user/loginView.jsp");
	   return "alert.jsp";
   }
   
}  
   
   @GetMapping( value="logout") // 완료  
   public String logout(HttpSession session ) throws Exception{
      
      System.out.println("/user/logout : POST");
      
      session.invalidate();
      
      return "redirect:/"; // 메인페이지로 이동 
   }
   

   @GetMapping( value="addUser") // 완료 
   public String addUser() throws Exception{
   
      System.out.println("/user/addUser : GET");
      
      return "redirect:/user/addUserView.jsp"; // 회원가입 페이지로 이동 
   }
   
   
   @PostMapping( value="addUser") // 완료 
   public String addUser( @ModelAttribute("user") User user, Model model) throws Exception {

      System.out.println("/user/addUser : POST");
      
      //Business Logic
      userService.addUser(user);
      model.addAttribute("msg", "GodLife에 가입해주셔서 감사합니다.");
      model.addAttribute("url","/user/loginView.jsp");
      
      return "alert.jsp";
      
      //return "redirect:/user/loginView.jsp"; // 회원가입하면 로그인페이지로 이동 
   }
   
   
   @GetMapping( value="getUser") // 완료
   public String getUser( @RequestParam("userEmail") String userEmail , Model model) 
                     throws Exception {
      
               
      System.out.println("/user/getUser : GET");
      //Business Logic
      User user = userService.getUser(userEmail);
      
      // Model 과 View 연결
      model.addAttribute("user", user);
      
      return "forward:/user/getUser.jsp"; // 본인정보 조회 페이지로 이동 
   }
   
   @GetMapping( value="getUserTarget") // 타유저정보조회, 배지랑 챌린지관련 부분 안됨  
   public String getUserTarget( @RequestParam("userEmail") String userEmail, Model model) 
                     throws Exception {
               
      System.out.println("타유저 상세조회 시작");
      
      //Business Logic
      User user = userService.getUser(userEmail);
      // Model 과 View 연결
      model.addAttribute("user", user);
      
      return "forward:/user/getUserTarget.jsp"; // 타유저 상세페이지 조회 
   }
   
   
   @GetMapping( value="updateUser") // 테스트 완료 
   public String updateUser( @RequestParam("userEmail") String userEmail , Model model ) throws Exception{

      System.out.println("/user/updateUser : GET");
      
      //Business Logic
      User user = userService.getUser(userEmail);
      
      System.out.println("유저값 제발나와... : "+user); // 왜 레드카드 쿠폰개수 못가져오는거니... 
      
      // Model 과 View 연결
      model.addAttribute("user", user);
      
      return "forward:/user/updateUser.jsp"; // 본인정보 수정을 하기위한 조회페이지로 이동 
   }
   
   @PostMapping( value="updateUser")  
   public String updateUser( @ModelAttribute("user") User user , Model model , HttpSession session,
                       @RequestParam ("fileInfo" )  MultipartFile file)throws Exception{
      
         System.out.println("/user/updateUser : POST");
         
          String fileName = file.getOriginalFilename();
          
          fileName =  uploadFile(fileName, file.getBytes());   // 파일 이름 중복 제거 
           System.out.println("파일이름" +  fileName );
           
           user.setProfileImg(fileName);
         
         //Business Logic
         userService.updateUser(user);
         
         String sessionId=((User)session.getAttribute("user")).getUserEmail();
         if(sessionId.equals(user.getUserEmail())){
            session.setAttribute("user", user);
         }
         
         return "redirect:/user/getUser?userEmail="+user.getUserEmail(); // 수정된상태의 조회페이지로 이동 
   }
   
   
    //파일명 랜덤 생성 메서드
    private String uploadFile(String originalName, byte[] fileData) throws Exception{
    
        // uuid 생성 
        UUID uuid = UUID.randomUUID();
        
        //savedName 변수에 uuid + 원래 이름 추가
        String savedName = uuid.toString()+"_"+originalName;
        //uploadPath경로의 savedName 파일에 대한 file 객체 생성
        File target = new File(uploadPath, savedName);
        //fileData의 내용을 target에 복사함
        FileCopyUtils.copy(fileData, target);
        
        return savedName;
    }
   
    
   @RequestMapping( value="listUser" )  // 테스트완료, 매퍼에서 서치검색어 like로 바꾸기 
   public String listUser( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{
      
      
      System.out.println("/user/listUser : GET / POST");
      
      if(search.getCurrentPage() ==0 ){
         search.setCurrentPage(1);
      }
      search.setPageSize(pageSize);
      
      // Business logic 수행
      Map<String , Object> map=userService.getUserList(search);
      
      Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
      System.out.println(resultPage);
      
      // Model 과 View 연결
      model.addAttribute("list", map.get("list"));
      model.addAttribute("resultPage", resultPage);
      model.addAttribute("search", search);
      
      return "forward:/user/listUser.jsp"; // 유저전체 목록조회로 이동(관리자) 
   }
   
   
   @GetMapping("findUserEmail") 
   public String findUserEmail () throws Exception{

      System.out.println("/user/findId : GET");
      
      return "redirect:/user/getUserEmailView.jsp"; // 이메일 찾기 페이지로 이동 
   }
   
   @PostMapping(value = "findUserEmail")
   public String findUserEmail(HttpServletResponse response, @RequestParam("phone") String phone, Model md) throws Exception{
      
      md.addAttribute("userEmail", userService.findUserEmail(response, phone));
      
      return "forward:/user/getUserEmail.jsp";
   }
   
   
   /* 비밀번호 찾기 */
   @GetMapping(value = "findUserPwd")
   public String findUserPwd () throws Exception{

      System.out.println("/user/findUserPwd : GET");
      
      return "redirect:/user/getUserPwdView.jsp"; // 비밀번호 찾기 페이지로 이동 
   }
   
   
   @GetMapping(value="updateUserPwd") 
   public String findUserPwd( @RequestParam("phone")String phone, @RequestParam("userEmail") String userEmail,HttpSession session,
                        Model model) throws Exception {

      System.out.println("비밀번호 찾기 시작");
      //Business Logic
      int cnt = userService.findUserPwd(phone, userEmail);
      
      if(cnt == 0) {
         System.out.println("이메일, 폰있나 개수 : "+cnt);
         model.addAttribute("msg", "이메일 및 핸드폰번호를 확인해주세요");
         return "redirect:/user/getUserPwdView.jsp";
      }
      
      User user = new User();
      user.setUserEmail(userEmail);
      session.setAttribute("user", user);
      
      return "forward:/user/updateUserPwd.jsp";//비밀번호 수정 페이지로 이동 
   }
   
   
   
   @PostMapping( value="updateUserPwd")  
   public String updateUserPwd( @RequestParam("userEmail") String userEmail ,@RequestParam("pwd") String pwd, Model model , HttpSession session
                      )throws Exception{
      
         System.out.println("@@updateUserPwd user"+userEmail);
         User user=new User();
         user.setUserEmail(userEmail);
         user.setPwd(pwd);
         
         //Business Logic
         System.out.println("@@@updatepwd start user :" +user);
         userService.updatePwd(user);
         
         
//         String sessionId=((User)session.getAttribute("user")).getUserEmail();
//         if(sessionId.equals(user.getUserEmail())){
//            session.setAttribute("user", user);
//         }
         
         return "redirect:/user/loginView.jsp";// 비밀번호가 변경되고 로그인페이지로 이동 
   }
   
   //coolSms api 사용
   @GetMapping(value = "phoneCheck") // 테스트완료 
   @ResponseBody
   public String sendSMS(@RequestParam("phone") String userPhoneNumber) throws Exception { // 휴대폰 문자보내기
      int randomNumber = (int)((Math.random()* (9999 - 1000 + 1)) + 1000);//난수 생성

      userService.certifiedPhoneNumber(userPhoneNumber,randomNumber);
      
      return Integer.toString(randomNumber);
   }
   
   ///////////////////////////////마이페이지/////////////////////////////////////////////////////////
   
   
   
   
   ////////////////////////////////친구, 블랙리스트 관리/////////////////////////////////////////////////////////
   
   // 친구 목록조회
   @RequestMapping( value = "listFriend") // 검색 확인해주기 
   public String listFriend (@ModelAttribute("search") Search search, 
                        Model model, HttpServletRequest request, HttpSession session)throws Exception{
      
      System.out.println("listFriend : GET / POST");
      
      User user = (User)session.getAttribute("user"); // 타켓이메일 없애고 세션 박아버림
      String userEmail = user.getUserEmail();
      
      
      if(search.getCurrentPage() ==0 ){
         search.setCurrentPage(1);
      }
      search.setPageSize(pageSize);
      
      // Business logic 수행
      Map<String , Object> map=userService.getFriendList(search, userEmail);
      
      Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
      System.out.println(resultPage);
      
      // Model 과 View 연결
      model.addAttribute("list", map.get("list"));
      model.addAttribute("resultPage", resultPage);
      model.addAttribute("search", search);
            
      return "forward:/user/listFriend.jsp"; // 친구 목록조회 리스트로 이동             
   }
   
   
   // 블랙리스트 목록조회
   @RequestMapping( value = "listBlack") // 검색 확인해주기  
   public String listBlack (@ModelAttribute("search") Search search, 
                        Model model, HttpServletRequest request, HttpSession session)throws Exception{
      
      System.out.println("listFriend : GET / POST");
      
      User user = (User)session.getAttribute("user"); // 타켓이메일 없애고 세션 박아버림
      String userEmail = user.getUserEmail();
      
      if(search.getCurrentPage() ==0 ){
         search.setCurrentPage(1);
      }
      search.setPageSize(pageSize);
      
      // Business logic 수행
      Map<String , Object> map=userService.getBlackList(search, userEmail);
      
      Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
      System.out.println(resultPage);
      
      // Model 과 View 연결
      model.addAttribute("list", map.get("list"));
      model.addAttribute("resultPage", resultPage);
      model.addAttribute("search", search);
            
     return "forward:/user/listBlack.jsp"; // 블랙리스트 목록조회 리스트로 이동             
                        
   }
   
   // 친구요청 목록조회 
   @RequestMapping( value = "listFriendRequest") // 검색 확인해주기 
   public String listFriendRequest (@ModelAttribute("search") Search search,  
                        Model model, HttpServletRequest request,HttpSession session)throws Exception{
      
      System.out.println("listFriendRequest : GET / POST");
      
      User user = (User)session.getAttribute("user");
      String targetEmail = user.getUserEmail();
      
      if(search.getCurrentPage() ==0 ){
         search.setCurrentPage(1);
      }
      search.setPageSize(pageSize);
      
      // Business logic 수행
      Map<String , Object> map=userService.getFriendRequestList(search, targetEmail);
      
      Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
      System.out.println(resultPage);
      
      // Model 과 View 연결
      model.addAttribute("list", map.get("list"));
      model.addAttribute("resultPage", resultPage);
      model.addAttribute("search", search);
            
       return "forward:/user/listFriendRequest.jsp";
                        
   }
   
   @RequestMapping( value="addFriend") // 완료
   public String addFriend( @ModelAttribute("friendBlack") FriendBlack friendBlack, 
                      @RequestParam("userEmail") String userEmail, 
                      @RequestParam("targetEmail") String targetEmail, HttpSession session, Model model) throws Exception {
                     
      System.out.println("나의 친구등록이 되라... ");
      
      User user = (User)session.getAttribute("user");
      friendBlack.setUserEmail(user.getUserEmail());
      
      System.out.println("친구에서 유저이메일은?"+friendBlack.getUserEmail());
      System.out.println("친구에서 타켓이메일은?"+targetEmail);
      
      System.out.println("유저 :"+user);
      
      //친구 중복 등록 방지 (성공...후아..) 
      int checkFriend = userService.checkFriend(friendBlack.getUserEmail(),targetEmail);

      System.out.println("개수!!!!!!뭐야!!!!!"+checkFriend);
     
      
      if(checkFriend > 0) {
    	  
         //이미 친구라면 (개수가 1일때 이미 친구상태, 0일때 친구 아님) 
         model.addAttribute("msg", "이미 친구로 등록된 유저입니다. 친구 목록조회에서 확인부탁드립니다."); 
         model.addAttribute("url", "/user/listFriend?userEmail="+user.getUserEmail());
         return "alert.jsp";
         
      }else {
    	  userService.addFriend(friendBlack);
      }
      
    //이미 친구라면 (개수가 1일때 이미 친구상태, 0일때 친구 아님) 
      model.addAttribute("msg", "친구 요청이 완료되었습니다. 요청이 수락되면 친구 목록조회에서 확인할 수 있습니다."); 
      model.addAttribute("url", "/user/getUserTarget?userEmail="+friendBlack.getTargetEmail());
      return "alert.jsp";
      
   }
   
   
   
   @RequestMapping( value="addBlack") // 완료
   public String addBlack( @ModelAttribute("friendBlack") FriendBlack friendBlack, 
                      @RequestParam("userEmail") String userEmail, 
                      @RequestParam("targetEmail") String targetEmail, HttpSession session, Model model) throws Exception {
                     
      System.out.println("블랙리스트 등록되라... 중복안되게... ... ");
      
      User user = (User)session.getAttribute("user");
      friendBlack.setUserEmail(user.getUserEmail()); // 유저이메일 세션으로 박아버림 
      
      System.out.println("블랙리스트에서 유저이메일은?"+friendBlack.getUserEmail());
      System.out.println("블랙리스트에서 타켓이메일은?"+targetEmail);
      
      //블랙리스트 등록 중복 방지 
      int checkBlack = userService.checkBlack(friendBlack.getUserEmail(),targetEmail);

      System.out.println("개수!!!!!!뭐야!!!!!@@@@"+checkBlack);
     
      
      if(checkBlack > 0) {
    	  
         //이미 블랙리스트라면 (개수가 1일때 이미 블랙리스트상태, 0일때 블랙리스트 아님) 
         model.addAttribute("msg", "이미 블랙리스트로 등록된 상태입니다. 블랙리스트 목록조회에서 확인바랍니다."); 
         model.addAttribute("url", "/user/listBlack?userEmail="+user.getUserEmail());
         return "alert.jsp";
         
      }else {
    	  userService.addBlack(friendBlack);
      }
      
    //이미 블랙리스트라면 (개수가 1일때 이미 친구상태, 0일때 친구 아님) 
      model.addAttribute("msg", "블랙리스트 등록이 완료되었습니다. 블랙리스트 목록조회에서 확인가능합니다."); 
      model.addAttribute("url", "/user/getUserTarget?userEmail="+friendBlack.getTargetEmail());
      return "alert.jsp";
      
   }
   
   
   //친구 요청 수락 (친구요청 목록조회에서)  // 완료 
   @RequestMapping( value="updateAccStatus")
   public String updateAccStatus( @ModelAttribute("friendBlack") FriendBlack friendBlack , 
                           @RequestParam("userEmail") String userEmail,
                           Model model , HttpSession session) throws Exception{
      
      System.out.println("친구 요청수락 시작 ");
      //Business Logic
      userService.updateAccStatus(friendBlack);
      
      User user = (User)session.getAttribute("user");
      
      model.addAttribute("msg", "친구 수락이 완료되었습니다. 친구 목록조회에서 확인가능합니다."); 
      model.addAttribute("url", "/user/listFriendRequest?targetEmail="+user.getUserEmail());
      return "alert.jsp";
      
       //return "forward:/user/listFriendRequest";
   }
   
   //친구 요청 거절 (친구요청 목록조회에서)  // 완료
   @RequestMapping( value="deleteFriendRequest")
   public String deleteFriendRequest( @ModelAttribute("friendBlack") FriendBlack friendBlack , 
                        @RequestParam("userEmail") String userEmail,
                        Model model , HttpServletRequest request, HttpSession session) throws Exception{
      
      System.out.println("친구 거절 시작 ");
      //Business Logic
      userService.deleteFriendRequest(friendBlack);
      //따로 뭐 더 안해줘도되는건가.. 
      
      User user = (User)session.getAttribute("user");
      
      model.addAttribute("msg", "친구 거절이 완료되었습니다."); 
      model.addAttribute("url", "/user/listFriendRequest?targetEmail="+user.getUserEmail());
      return "alert.jsp";
      
      // return "forward:/user/listFriendRequest"; // 수정된상태의 조회페이지로 이동 
   }
   
   
   @RequestMapping(value = "deleteUserFriend") // 친구목록에서 선택삭제
  	public String deleteUserFriend(@RequestParam("checkList") int[] reviewList, HttpSession session) throws Exception {

  		System.out.println("친구목록에서 친구삭제!!!");

  		for (int i = 0; i < reviewList.length; i++) {
  			System.out.println(reviewList[i]); // 친구블랙리스트번호가 나옴 
  		}
  		for (int i = 0; i < reviewList.length; i++) {
  			userService.deleteFriend(reviewList[i]);
  		}
  		
  		User user = (User)session.getAttribute("user");
  		
  		return "redirect:/user/listFriend?userEmail="+user.getUserEmail(); //내 친구 목록조회로 이동 
  	}
   
   
   @RequestMapping(value = "deleteUserBlack") // 블랙리스트목록에서 선택삭제
 	public String deleteUserBlack(@RequestParam("checkList") int[] reviewList, HttpSession session) throws Exception {

 		System.out.println("블랙리스트목록에서 친구삭제!!!");

 		for (int i = 0; i < reviewList.length; i++) {
 			System.out.println(reviewList[i]); // 친구블랙리스트번호가 나옴 
 		}
 		for (int i = 0; i < reviewList.length; i++) {
 			userService.deleteBlack(reviewList[i]);
 		}
 		
 		User user = (User)session.getAttribute("user");
 		
 		return "redirect:/user/listBlack?userEmail="+user.getUserEmail(); //내 블랙리스트 목록으로 이동  
 	}
   
   ////////////////////////////////쪽지관리/////////////////////////////////////////////////////////
   
   @GetMapping( value="addUserMsg") // 완료
   public String addMsg() throws Exception{
   
      System.out.println("쪽지보내기 : GET");
      
      return "forward:/user/addUserMsgView.jsp"; // 쪽지 보낼수있는 화면으로 이동 
   }
   
   @PostMapping( value="addUserMsg") 
   public String addMsg( @ModelAttribute("msg") Msg msg, 
		   				@RequestParam("recvEmail") String recvEmail,
                    Model model, HttpSession session) throws Exception {

      System.out.println("쪽지보내기 : POST");
      
      User user = (User)session.getAttribute("user");
      msg.setSendEmail(user.getUserEmail());    // 보내는사람 세션으로 박아버리기
      
      // 블랙리스트에게 보내는거 방지 
      int checkMsgBlack = userService.checkMsgBlack(user.getUserEmail(), recvEmail);

      System.out.println("개수!!!!!!뭐야!!!!!@@@"+checkMsgBlack);
      
      if(checkMsgBlack > 0) {
    	  
    	  //블랙리스트 관계(내가 등록했거나, 상대가 등록했거나) 쪽지 못보냄... 
    	  model.addAttribute("msg", "블랙리스트 관계는 쪽지를 주고받을 수 없습니다.");
    	  model.addAttribute("url", "/user/addUserMsg?sendEmail="+user.getUserEmail());
    	  return "alert.jsp";
      
      }else {
    	  
    	  //블랙리스트 관계아니니까.. 쪽지 보낼 수 있따...
    	  userService.addMsg(msg);
    	  model.addAttribute("msg", "쪽지가 성공적으로 전송되었습니다. 보낸쪽지함에서 확인 가능합니다.");
    	  model.addAttribute("url", "/user/listUserSendMsg?sendEmail="+user.getUserEmail()); // 보낸쪽지함으로이동 
    	  return "alert.jsp";
      }
      
   }
   
   
   
   //쪽지 답장 답장 띄우는 것만 일단 목록에서... 
   @GetMapping( value="addUserMsgReply") 
   public String addUserMsgReply( @ModelAttribute("msg") Msg msg,
		   						
                    Model model, HttpSession session) throws Exception {

      System.out.println("쪽지 답장보내기 : POST");
      
      User user = (User)session.getAttribute("user");
      msg.setSendEmail(user.getUserEmail());  // 보내는사람 세션으로 박아버리기
      
      System.out.println("쪽지 보내는사람은?"+user.getUserEmail());
      System.out.println("쪽지 받는사람은?" + msg.getSendEmail());
      
     model.addAttribute("msg", msg);
      
      //Business Logic
      userService.addMsg(msg);
      return "forward:/user/addUserMsgReply.jsp"; // 답장 보낼수있는 화면으로 이동 
   }
   
   
   
   
   // 쪽지 답장보내기 post
   
   @GetMapping( value="getUserRecvMsg")
   public String getMsg( @RequestParam("msgNo") int msgNo , Model model ) throws Exception {
      
      System.out.println("받은 쪽지 상세조회 : GET");
      
      //Business Logic
      Msg msg = userService.getRecvMsg(msgNo);
      
      // Model 과 View 연결
      model.addAttribute("msg", msg);
      
      return "forward:/user/getUserRecvMsg.jsp";  // 받은 쪽지 상세페이지로 이동 
   }
   
   @GetMapping( value="getUserSendMsg")
   public String getSendMsg( @RequestParam("msgNo") int msgNo , Model model ) throws Exception {
      
      System.out.println("보낸 쪽지 상세조회 :  GET");
      //Business Logic
      Msg msg = userService.getSendMsg(msgNo);
      // Model 과 View 연결
      model.addAttribute("msg", msg);
      
      return "forward:/user/getUserSendMsg.jsp";  // 보낸 쪽지 상세페이지로 이동 
   }
   
   
   @RequestMapping(value = "deleteUserRecvMsg") // 받은쪽지목록조회에서 선택삭제 배열사용
	public String deleteUserRecvMsg(@RequestParam("checkList") int[] reviewList, HttpSession session) throws Exception {

		System.out.println("받은쪽지목록조회에서 선택삭제!!!");

		for (int i = 0; i < reviewList.length; i++) {
			System.out.println(reviewList[i]); // 쪽지번호가 나옴 
		}
		for (int i = 0; i < reviewList.length; i++) {
			userService.deleteMsg(reviewList[i]);
		}
		
		User user = (User)session.getAttribute("user");
		return "redirect:/user/listUserRecvMsg?recvEmail="+user.getUserEmail(); 
	}
   
   
   @RequestMapping(value = "deleteUserSendMsg") // 보낸쪽지목록조회에서 선택삭제 배열사용
  	public String deleteUserSendMsg(@RequestParam("checkList") int[] reviewList, HttpSession session) throws Exception {

  		System.out.println("보낸 쪽지 목록에서 선택삭제!!!");

  		for (int i = 0; i < reviewList.length; i++) {
  			System.out.println(reviewList[i]); // 쪽지번호가 나옴 
  		}
  		for (int i = 0; i < reviewList.length; i++) {
  			userService.deleteMsg(reviewList[i]);
  		}
  		
  		User user = (User)session.getAttribute("user");
  		return "redirect:/user/listUserSendMsg?sendEmail="+user.getUserEmail(); 
  	}
   
   
   //받은 쪽지 목록조회
   @RequestMapping( value = "listUserRecvMsg")
   public String listRecvMsg (@ModelAttribute("search") Search search, 
                        //@RequestParam("recvEmail")String recvEmail, 
                        HttpSession session,
                        Model model, HttpServletRequest request)throws Exception{
      
      System.out.println("listRecvMsg : GET / POST");
      
      if(search.getCurrentPage() ==0 ){
         search.setCurrentPage(1);
      }
      search.setPageSize(pageSize);
      
      User user = (User)session.getAttribute("user"); // 파라미터말고 세션으로 박아버리기 
      String recvEmail = user.getUserEmail();
      
      // Business logic 수행
      Map<String , Object> map=userService.getRecvMsgList(search, recvEmail);
      
      Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
      System.out.println(resultPage);
      
      // Model 과 View 연결
      model.addAttribute("list", map.get("list"));
      model.addAttribute("resultPage", resultPage);
      model.addAttribute("search", search);
            
       return "forward:/user/listUserRecvMsg.jsp";               
   }
   
   
   ////보낸 쪽지 목록조회 
   @RequestMapping( value = "listUserSendMsg")
   public String listSendMsg (@ModelAttribute("search") Search search, 
		   					//@RequestParam("sendEmail")String sendEmail, 
		   					Model model, HttpServletRequest request, HttpSession session)throws Exception{
      
      System.out.println("listSendMsg : GET / POST");
      
      if(search.getCurrentPage() ==0 ){
         search.setCurrentPage(1);
      }
      search.setPageSize(pageSize);
      
      User user = (User)session.getAttribute("user");
      String sendEmail = user.getUserEmail();
      
      // Business logic 수행
      Map<String , Object> map=userService.getSendMsgList(search, sendEmail);
      
      Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
      System.out.println(resultPage);
      
      
      // Model 과 View 연결
      model.addAttribute("list", map.get("list"));
      model.addAttribute("resultPage", resultPage);
      model.addAttribute("search", search);
            
      return "forward:/user/listUserSendMsg.jsp";   // 보낸 쪽지 목록으로 이동                   
   }
   
   ////////////////////////////////일대일 문의 관리 /////////////////////////////////////////////////////////
   
   
   
   @GetMapping("addOneInq")
   public String addOneInq() throws Exception{
      
      System.out.println("addOneInq : get");
      
      return "aaa"; // 일대일문의 등록 페이지로 이동 
   }
   
   @PostMapping("addOneInq")
   public String addOneInq(@ModelAttribute("oneInq") OneInq oneInq) throws Exception{
      
      System.out.println("addOneInq : post");
      
      userService.addOneInq(oneInq);
      
      return "aaa"; // 일대일 문의 등록이 완료되고 상세조회로 이동? 
   }
   
   @GetMapping("getOneInq")
   public String getOneInq( @RequestParam("oneInqNo") int oneInqNo , Model model ) throws Exception {
      
      System.out.println("oneInqNo : GET");
      //Business Logic
      OneInq oneInq = userService.getOneInq(oneInqNo);
      // Model 과 View 연결
      model.addAttribute("oneInq", oneInq);
      
      return "bbb";// 일대일문의 상세페이지로 이동 
   }
   
   @GetMapping("updateOneInq")
   public String updateOneInq(@RequestParam("oneInqNo") int oneInqNo, Model model) throws Exception{
      
      System.out.println("updateOneInq : GET");
      
      OneInq oneInq = userService.getOneInq(oneInqNo);
      
      model.addAttribute("oneInq", oneInq);
      
      return "Bbb"; // 일대일문의 상세페이지에서 수정하기위한 페이지로 이동 
   }
   
   @PostMapping("updateOneInq")
   public String updateOneInq(@ModelAttribute("oneInqNo") OneInq oneInq) throws Exception{
      
      System.out.println("updateOneInq : POST");
      
      userService.updateOneInq(oneInq);
      
      return "redirect:/user/getOneInq?oneInqNo=" + oneInq.getOneInqNo(); // 수정된 상세페이지로 이동 
   }
   
   
   @PostMapping("updateOneInqComment")
   public String updateOneInqComment(@ModelAttribute("oneInqNo") OneInq oneInq) throws Exception{
      
      System.out.println("updateOneInq : POST");
      
      userService.updateOneInq(oneInq);
      
      //관리자만 답변할수있게끔.. 어떻게하지.. 
      
      return "redirect:/user/getOneInq?oneInqNo=" + oneInq.getOneInqNo(); // 수정된 상세페이지로 이동 
   }
   
   
   @GetMapping("deleteOneInq")
   public String deleteOneInq(@RequestParam("oneInqNo") int oneInqNo) throws Exception{
      
      System.out.println("deleteOneInq : GET");
      
      userService.deleteOneInq(oneInqNo);
      
      return "bbb"; // 일대일문의 리스트 화면으로 이동 
   }
   
   @GetMapping("listOneInq")
   public String listOneInq(@ModelAttribute("search") Search search, HttpSession session, HttpServletRequest request,Model model) 
         throws Exception{
      
      System.out.println("listOneInq : get");
      
      if(search.getCurrentPage() == 0) {
         search.setCurrentPage(1);
      }
      
      search.setPageSize(pageSize);
      
      session = request.getSession();
      User user = (User)session.getAttribute("user");
      
      Map<String, Object> map = userService.getOneInqList(search, user.getUserEmail());
      
      Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
      
      System.out.println(resultPage);
      
      model.addAttribute("list", map.get("list"));
      model.addAttribute("resultPage", resultPage);
      model.addAttribute("search", search);
      
      return "GGG"; //일대일문의 리스트로 이동 
   }
   
   
   ////////////////////////////////신고 관리/////////////////////////////////////////////////////////
   
   //신고등록 
   @PostMapping( value="addMsgReport") // 완료 (닉네임으로 하면안됨...) 
   public String addMsgReport( @ModelAttribute("report") Report report,  
		   					Model model, HttpSession session) throws Exception {
	   						   
      System.out.println("쪽지 신고등록 시작");
      
      User user = (User)session.getAttribute("user");
      
      report.setReporterEmail(user.getUserEmail()); // 신고자 세션으로 박아버리기 
      
      System.out.println("@@@@데이터 잘나오니..."+report);
      
      //Business Logic
      userService.addMsgReport(report);
      
      model.addAttribute("msg", "신고 접수가 완료되었습니다.");
      model.addAttribute("url","/user/listUserRecvMsg?recvEmail="+user.getUserEmail()); // 받은 쪽지함으로 이동... 
      
      return "alert.jsp";
      
   }
   
   //레드카드 소멸쿠폰 사용 
   @GetMapping("updateUserRedCouponCount")
   public String updateUserRedCouponCount(HttpSession session, Model model) throws Exception{
      
      System.out.println("쿠폰사용... ");
      
      User user = (User)session.getAttribute("user");
      System.out.println("유저 : "+user);
      
      //레드카드 쿠폰 개수가 0개일때 
      if(user.getRedCouponCount() < 1) {
    	  
    	  model.addAttribute("msg", "레드카드 쿠폰 개수를 확인해주세요");
          model.addAttribute("url","/user/getUser?userEmail="+user.getUserEmail()); // 다시 본인 상세조회로 이동  
          
          return "alert.jsp";
      }
      
      //레드카드 개수가 0개일때 
      if(user.getRedCardCount() < 1) {
    	  
    	  model.addAttribute("msg", "보유한 레드카드가 없습니다.");
          model.addAttribute("url","/user/getUser?userEmail="+user.getUserEmail()); // 다시 본인 상세조회로 이동  
          
          return "alert.jsp";
    	  
      }else{
    	  
          userService.updateUserRedCouponCount(user);
          
          int redCoupon = user.getRedCouponCount()-1;
          System.out.println("레드카드쿠폰개수 : " + redCoupon);
          user.setRedCardCount(redCoupon); // 레드카드쿠폰개수 한개뺸걸로 박아버리기...  
          
          int redcardCount = user.getRedCardCount()-1;
          System.out.println("레드카드개수나와라..."+redcardCount);
          user.setRedCardCount(redcardCount); //레드카드개수도 한개뺀걸로 박아버리기
          
          model.addAttribute("msg", "쿠폰 사용이 완료되었습니다.");
          model.addAttribute("url","/user/getUser?userEmail="+user.getUserEmail()); // 다시 본인 상세조회로 이동  
          
          return "alert.jsp";
    	  
      }
      
      
      
      
   }
   
   
   
   //신고유저 목록조회 
   
   //레드카드발급 
   
   //계정정지 
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
}   
   