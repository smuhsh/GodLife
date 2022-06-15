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
	
	//checkdupulication 알아보기 
	//전체적으로 세션 설정하기, 로그인안했을때.. 
	
	@GetMapping( value="login")  // 테스트완료 
	public String login() throws Exception{
		
		System.out.println("/user/login : GET");

		return "redirect:/user/loginView.jsp"; // 로그인 페이지로 이동 
	}
	   
	
	@PostMapping( value="login") // 테스트완료 ??? 아이디 없을때,, 이런거 다시 체크하기 
	public String login(@ModelAttribute("user") User user , HttpSession session, Model model) throws Exception{
		
		System.out.println("/user/login : POST");
		
		//Business Logic
		User dbUser=userService.getUser(user.getUserEmail());
		System.out.println("getUser 결과 : "+dbUser);
		
		//db에 아이디가 없을 경우
		if(dbUser.getUserEmail()==null && dbUser.getPwd()==null) {
			model.addAttribute("message", "아이디 및 비밀번호가 올바르지 않습니다."); // 해당 메세지 알러트창으로 어떻게?
			model.addAttribute("url", "redirect:/user/loginView.jsp"); // 이거아닌듯.. 
			//return "redirect:/user/loginView.jsp"; // 로그인 페이지 
		}
		
		// 계정정지 상태 
		if(dbUser.getAccountStatus()=="2") {
			model.addAttribute("message1", "레드카드 3장이상으로 계정정지 상태이며, 로그인할 수 없습니다."); // 해당 메세지 알러트창으로 어떻게?
			return "redirect:/user/loginView.jsp";	// 로그인 페이지 
		}
		
		// 관리자일때 
		if( user.getPwd().equals(dbUser.getPwd())){
			session.setAttribute("user", dbUser);
		}
		
		System.out.println("세션 만들어짐...");
		System.out.println(session.getAttribute("user"));
		
		return "redirect:/index.jsp"; // 메인페이지로 이동 
	}
	
	
	@GetMapping( value="logout") // 테스트완료  
	public String logout(HttpSession session ) throws Exception{
		
		System.out.println("/user/logout : POST");
		
		session.invalidate();
		
		return "redirect:/index.jsp"; // 메인페이지로 이동 
	}
	

	@GetMapping( value="addUser") // 테스트완료 
	public String addUser() throws Exception{
	
		System.out.println("/user/addUser : GET");
		
		return "redirect:/user/addUserView.jsp"; // 회원가입 페이지로 이동 
	}
	
	@PostMapping( value="addUser") // 테스트완료 
	public String addUser( @ModelAttribute("user") User user, Model model) throws Exception {

		System.out.println("/user/addUser : POST");
		//Business Logic
		userService.addUser(user);
		
		model.addAttribute("message", "GodLife에 가입해주셔서 감사합니다.");
		
		return "redirect:/user/loginView.jsp"; // 회원가입하면 로그인페이지로 이동 
	}
	
	
	@GetMapping( value="getUser") // 본인정보조회, 테스트완료
	public String getUser( @RequestParam("userEmail") String userEmail , Model model) 
						   throws Exception {
		
					
		System.out.println("/user/getUser : GET");
		//Business Logic
		User user = userService.getUser(userEmail);
		
		// Model 과 View 연결
		model.addAttribute("user", user);
		
		return "forward:/user/getUser.jsp"; // 본인정보 조회 페이지로 이동 
	}
	
	@GetMapping( value="getUserTarget") // 타유저정보조회, 쿼리 여러개값나와서.안돌아감... 
	public String getUserTarget( @RequestParam("nick") String nick , Model model) 
						   throws Exception {
					
		System.out.println("타유저 상세조회 시작");
		//Business Logic
		List<User> user = userService.getUserTarget(nick);
		
		// Model 과 View 연결
		model.addAttribute("user", user);
		
		return "forward:/user/getUserTarget.jsp"; // 타유저 상세페이지 조회 
	}
	
	
	@GetMapping( value="updateUser") // 테스트 완료 
	public String updateUser( @RequestParam("userEmail") String userEmail , Model model ) throws Exception{

		System.out.println("/user/updateUser : GET");
		
		//Business Logic
		User user = userService.getUser(userEmail);
		
		System.out.println("유저값 제발나와... : "+user); // 유저값이 안나옴... 
		
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
	
	
	@RequestMapping( value="listUser" )  // 테스트완료, 시간되면 매퍼에서 서치검색어 like로 바꾸기 
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
	
	
	
	
	
	
	
	
	
//	@GetMapping("findUserPwd")
//	public String findUserPwd() throws Exception{
//		
//		System.out.println("/user/findUserPwd : GET");
//		
//		return "bbb"; // 비밀번호 찾기 페이지로 이동 
//	}
//	
//	@PostMapping("findUserPwd")
//	public String findUserPwd(String userEmail, String phone, String sms, HttpSession session) throws Exception{
//		
//		System.out.println("/user/findPwd : POST");
//		
//		// sms인증 필요
//		
//		User user = userService.getUser(userEmail);
//		User user1 = userService.findUserPhone(phone);
//		
//		if(user.getUserEmail() == user1.getUserEmail()){
//			session.setAttribute("user", user);
//			
//			return "user/updateUserPwd";
//		}
//		
//		return "user/updateUserPwd"; // 새 비밀번호 입력창으로 이동
//	}	
	
	@PostMapping("updateUserPwd") 
	public String updateUserPwd( @ModelAttribute("user") User user, HttpSession session, Model model  ) throws Exception {

		System.out.println("/user/updateUserPwd : POST");
		//Business Logic
		
		//뭔가를 추가해야함... 
		
		userService.updatePwd(user);
		
		return "bbb"; // 비밀번호 변경되고 로그인페이지 또는 메인페이지로 이동 
	}
	
	//coolSms api 사용
	@GetMapping(value = "phoneCheck") // 테스트완료 
	@ResponseBody
	public String sendSMS(@RequestParam("phone") String userPhoneNumber) throws Exception { // 휴대폰 문자보내기
		int randomNumber = (int)((Math.random()* (9999 - 1000 + 1)) + 1000);//난수 생성

		userService.certifiedPhoneNumber(userPhoneNumber,randomNumber);
		
		return Integer.toString(randomNumber);
	}
	
	
	//타유저 상세조회 . 인젝션해야되는데... 개어려워... 
	
	
	///////////////////////////////마이페이지/////////////////////////////////////////////////////////
	
	
	
	
	
	////////////////////////////////친구, 블랙리스트 관리/////////////////////////////////////////////////////////
	
	// 친구 목록조회
	@RequestMapping( value = "listFriend") // 테스트완료 (썸네일 이미지 출력과, 닉네임 클릭 시 회원상세정보로 이동하는거 만들면될듯..) ,, 어자피 타유저상세조회라서.. 그땐 닉으로할까.. 
	public String listFriend (@ModelAttribute("search") Search search, 
							  @RequestParam("userEmail")String userEmail, 
							   Model model, HttpServletRequest request)throws Exception{
		
		System.out.println("listFriend : GET / POST");
		
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
	@RequestMapping( value = "listBlack") // 테스트 완료, 카운트 수 이상함 
	public String listBlack (@ModelAttribute("search") Search search, 
							   @RequestParam("userEmail")String userEmail, 
							   Model model, HttpServletRequest request)throws Exception{
		
		System.out.println("listFriend : GET / POST");
		
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
	@RequestMapping( value = "listFriendRequest") // 테스트완료, 카운트값, 서치값만 확인 
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

	
	// 유저상세조회 에서 친구등록 레스트로 가야될듯.???... 
	@PostMapping( value="addFriend")
	public String addFriend( @ModelAttribute("friendBlack") FriendBlack friendBlack ) throws Exception {

		System.out.println("나의 친구등록이 되라... ");
		
		// 이미 친구관계일때는 등록 x 중복안되게?... 어떻게할수없을까.... boolean? 
		//Business Logic
		userService.addFriend(friendBlack);
		
		return "aaa"; // 친구 팝업창으로 이동?.. 어디로이동... 그대로 유저상세조회? 
	}
	
	
	//블랙리스트 등록 레스트로 가야할듯.
	@RequestMapping( value="addBlack", method=RequestMethod.POST )
	public String addBlack( @ModelAttribute("friendBlack") FriendBlack friendBlack ) throws Exception {

		System.out.println("나의 블랙리스트등록이 되라... ");
		
		// 이미 블랙리스트 등록했을때는...  등록 x 중복안되게?... 어떻게할수없을까.... 
		//Business Logic
		userService.addBlack(friendBlack);
		
		return "aaa"; //  팝업창으로 이동?..  
	}
	
	
	//친구 요청 수락 (친구요청 목록조회에서)  // 테스트완료 
	@RequestMapping( value="updateAccStatus")
	public String updateAccStatus( @ModelAttribute("friendBlack") FriendBlack friendBlack , 
								   @RequestParam("userEmail") String userEmail,
								   Model model , HttpSession session) throws Exception{
		
		System.out.println("친구 요청수락 시작 ");
		//Business Logic
		userService.updateAccStatus(friendBlack);
		//따로 뭐 더 안해줘도되는건가.. 
		
		 return "forward:/user/listFriendRequest";
	}
	
	//친구 요청 거절 (친구요청 목록조회에서)  // 테스트완료 
	@RequestMapping( value="deleteFriend")
	public String deleteFriend( @ModelAttribute("friendBlack") FriendBlack friendBlack , 
								@RequestParam("userEmail") String userEmail,
								Model model , HttpServletRequest request) throws Exception{
		
		System.out.println("친구 거절 시작 ");
		//Business Logic
		userService.deleteFriend(friendBlack);
		//따로 뭐 더 안해줘도되는건가.. 
		
		 return "forward:/user/listFriendRequest"; // 수정된상태의 조회페이지로 이동 
	}
	
	
	//친구 삭제 (친구 목록에서, 이것도 세션처리??)
	
	//블랙리스트 삭제 (블랙리스트 목록, 이것도 세션처리?? ) 
	
	//친구등록 중복방지, 블랙리스트 등록 중복방지.... 어떻게할까.... 
	
	
	
	////////////////////////////////쪽지관리/////////////////////////////////////////////////////////
	
	@GetMapping( value="addUserMsg")
	public String addMsg() throws Exception{
	
		System.out.println("쪽지보내기 : GET");
		
		return "forward:/user/addUserMsgView.jsp"; // 쪽지 보낼수있는 화면으로 이동 
	}
	
	@PostMapping( value="addUserMsg")
	public String addMsg( @ModelAttribute("msg") Msg msg ) throws Exception {

		System.out.println("쪽지보내기 : POST");
		//Business Logic
		userService.addMsg(msg);
		
		return "bbb"; // 쪽지를 보내고 보낸 쪽지함으로 이동? 또는 상세조회페이지로 이동 
	}
	
	@GetMapping( value="getUserRecvMsg")
	public String getMsg( @RequestParam("msgNo") int msgNo , Model model ) throws Exception {
		
		System.out.println("받은 쪽지 조회 : GET");
		
		//Business Logic
		Msg msg = userService.getRecvMsg(msgNo);
		
		// Model 과 View 연결
		model.addAttribute("msg", msg);
		
		return "forward:/user/getUserRecvMsg.jsp";  // 받은 쪽지 상세페이지로 이동 
	}
	
	@GetMapping( value="getSendMsg")
	public String getSendMsg( @RequestParam("msgNo") int msgNo , Model model ) throws Exception {
		
		System.out.println("보낸 쪽지 조회 :  GET");
		
		//Business Logic
		Msg msg = userService.getSendMsg(msgNo);
		
		// Model 과 View 연결
		model.addAttribute("msg", msg);
		
		return "bbb"; // 보낸 쪽지 상세페이지로 이동 
	}
	
	// 레스트로 가야할수도.. 
	@GetMapping( value="deleteMsg")
	public String deleteMsg( @RequestParam("msgNo") int msgNo )throws Exception {
		
		System.out.println("쪽지 삭제되라");
		
		userService.deleteMsg(msgNo);
		
		return "ccc"; // 쪽지 삭제가 되고, 어디로 이동??...??? 
	}
	
	
	//받은 쪽지 목록조회 
	@RequestMapping( value = "listUserRecvMsg")
	public String listRecvMsg (@ModelAttribute("search") Search search, 
							   @RequestParam("recvEmail")String recvEmail, 
							   Model model, HttpServletRequest request)throws Exception{
		
		System.out.println("listRecvMsg : GET / POST");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
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
	@RequestMapping( value = "listSendMsg")
	public String listSendMsg (@ModelAttribute("search") Search search, 
							   @RequestParam("sendEmail")String sendEmail, 
							   Model model, HttpServletRequest request)throws Exception{
		
		System.out.println("listSendMsg : GET / POST");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		Map<String , Object> map=userService.getSendMsgList(search, sendEmail);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
				
	  return "aaa"; // 보낸 쪽지 목록으로 이동 						
								
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
	
	//레드카드 소멸쿠폰 사용 
	@GetMapping("updateUserRedCouponCount")
	public String updateUserRedCouponCount(HttpSession session) throws Exception{
		
		System.out.println("updateUserRedCouponCount : GET");
		
		User user = (User)session.getAttribute("user");
		System.out.println("유저 : "+user);
		
		int redCoupon = user.getRedCouponCount() - 1;
		
        System.out.println("redCoupon : " + redCoupon);
        
        user.setRedCardCount(redCoupon);
        userService.updateUserRedCouponCount(user);
        
        return "forward:/user/getUser.jsp"; // 쿠폰쓰고 다시 본인 상세조회로 포워드?리다이렉트?? 
	}
	
	
	
	
	
	//신고유저 목록조회 
	
	//레드카드발급 
	
	//계정정지 
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	
	
