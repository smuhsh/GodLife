package com.godLife.io.web.user;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.godLife.io.service.user.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.godLife.io.common.Page;
import com.godLife.io.common.Search;
import com.godLife.io.service.domain.FriendBlack;
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
	
	////////////////////////////////회원관리/////////////////////////////////////////////////////////
	
	
	@RequestMapping( value="login", method=RequestMethod.GET )
	public String login() throws Exception{
		
		System.out.println("/user/logon : GET");

		return "redirect:/user/loginView.jsp"; // 로그인 페이지로 이동 
	}
	   
	@RequestMapping( value="login", method=RequestMethod.POST )
	public String login(@ModelAttribute("user") User user , HttpSession session, Model model ) throws Exception{
		
		System.out.println("/user/login : POST");
		//Business Logic
		User dbUser=userService.login(user.getUserEmail());
		
		System.out.println("입력받은 ID/PW : "+user);
		System.out.println("DB와 일치하는 ID/PW : "+dbUser);
		
		//db에 아이디가 없을 경우
		if(dbUser==null) {
			model.addAttribute("message", "가입되지않은 아이디입니다.");
			return "user/login"; // 로그인 페이지 
		}
		
		// 계정정지 상태 
		if(dbUser.getRole()=="2") {
			model.addAttribute("message", "레드카드 3장이상으로 계정정지 상태이며, 로그인할 수 없습니다.");
			return "user/login";	// 로그인 페이지 
		}
		
		if( user.getPwd().equals(dbUser.getPwd())){
			session.setAttribute("user", dbUser);
			
			return "redirect:/index.jsp"; // 메인페이지로 이동 
		}
		
		return "redirect:/index.jsp"; // 메인페이지로 이동 
	}
	
	
	@RequestMapping( value="logout", method=RequestMethod.GET )
	public String logout(HttpSession session ) throws Exception{
		
		System.out.println("/user/logout : POST");
		
		session.invalidate();
		
		return "redirect:/index.jsp"; // 메인페이지로 이동 
	}
	

	@RequestMapping( value="addUser", method=RequestMethod.GET )
	public String addUser() throws Exception{
	
		System.out.println("/user/addUser : GET");
		
		return "redirect:/user/addUserView.jsp"; // 회원가입 페이지로 이동 
	}
	
	@RequestMapping( value="addUser", method=RequestMethod.POST )
	public String addUser( @ModelAttribute("user") User user ) throws Exception {

		System.out.println("/user/addUser : POST");
		//Business Logic
		userService.addUser(user);
		
		return "redirect:/user/loginView.jsp"; // 회원가입하면 로그인페이지로 이동 
	}
	
	@RequestMapping( value="getUser", method=RequestMethod.GET )
	public String getUser( @RequestParam("userEmail") String userEmail , Model model ) throws Exception {
		
		System.out.println("/user/getUser : GET");
		//Business Logic
		User user = userService.getUser(userEmail);
		// Model 과 View 연결
		model.addAttribute("user", user);
		
		return "forward:/user/getUser.jsp"; // 본인정보 조회 페이지로 이동 
	}
	
	@RequestMapping( value="updateUser", method=RequestMethod.GET )
	public String updateUser( @RequestParam("userEmail") String userEmail , Model model ) throws Exception{

		System.out.println("/user/updateUser : GET");
		//Business Logic
		User user = userService.getUser(userEmail);
		// Model 과 View 연결
		model.addAttribute("user", user);
		
		return "forward:/user/updateUser.jsp"; // 본인정보 수정을 하기위한 조회페이지로 이동 
	}
	
	@RequestMapping( value="updateUser", method=RequestMethod.POST )
	public String updateUser( @ModelAttribute("user") User user , Model model , HttpSession session) throws Exception{

		System.out.println("/user/updateUser : POST");
		//Business Logic
		userService.updateUser(user);
		
		String sessionId=((User)session.getAttribute("user")).getUserEmail();
		if(sessionId.equals(user.getUserEmail())){
			session.setAttribute("user", user);
		}
		return "redirect:/user/getUser?userEmail="+user.getUserEmail(); // 수정된상태의 조회페이지로 이동 
	}
	
	@RequestMapping( value="listUser" )
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
		
		return "bbb"; // 이메일 찾기 페이지로 이동 
	}
	
	@PostMapping("findUserEmail")
	public String findUserEmail (String phone, String sms) throws Exception{
		System.out.println("/user/findUserEmail : POST");
		
		// sms 인증필요 보낸 sms와 유저sms가 일치해야함???? 
		// 다시 확인 
		
		User user= userService.findUserPhone(phone);
		 
		return "bbb"; // 이메일 찾기가 성공하고 이메일을 보여주는 페이지
	}
	
	@GetMapping("findUserPwd")
	public String findUserPwd() throws Exception{
		
		System.out.println("/user/findUserPwd : GET");
		
		return "bbb"; // 비밀번호 찾기 페이지로 이동 
	}
	
	@PostMapping("findUserPwd")
	public String findUserPwd(String userEmail, String phone, String sms, HttpSession session) throws Exception{
		
		System.out.println("/user/findPwd : POST");
		
		// sms인증 필요
		
		User user = userService.getUser(userEmail);
		User user1 = userService.findUserPhone(phone);
		
		if(user.getUserEmail() == user1.getUserEmail()){
			session.setAttribute("user", user);
			
			return "user/updateUserPwd";
		}
		
		return "user/updateUserPwd"; // 새 비밀번호 입력창으로 이동
	}	
	
	@PostMapping("updateUserPwd")
	public String updateUserPwd( @ModelAttribute("user") User user, HttpSession session, Model model  ) throws Exception {

		System.out.println("/user/updateUserPwd : POST");
		//Business Logic
		
		//뭔가를 추가해야함... 
		
		userService.updatePwd(user);
		
		return "bbb"; // 비밀번호 변경되고 로그인페이지 또는 메인페이지로 이동 
	}
	
	////////////////////////////////친구, 블랙리스트 관리/////////////////////////////////////////////////////////
	
	// 친구 목록조회
	// 블랙리스트 목록조회
	// 친구요청 목록조회 
	
	// 유저상세조회 에서 친구등록 레스트로 가야될듯.. 
	@RequestMapping( value="addFriend", method=RequestMethod.POST )
	public String addFriend( @ModelAttribute("friendBlack") FriendBlack friendBlack ) throws Exception {

		System.out.println("친구등록이 되라... ");
		
		// 이미 친구관계일때는 등록 x 중복안되게?... 어떻게할수없을까.... 
		//Business Logic
		userService.addFriend(friendBlack);
		
		return "aaa"; // 친구 팝업창으로 이동?..  
	}
	
	//블랙리스트 등록 레스트로 가야할듯.
	@RequestMapping( value="addBlack", method=RequestMethod.POST )
	public String addBlack( @ModelAttribute("friendBlack") FriendBlack friendBlack ) throws Exception {

		System.out.println("블랙리스트등록이 되라... ");
		
		// 이미 블랙리스트 등록했을때는...  등록 x 중복안되게?... 어떻게할수없을까.... 
		//Business Logic
		userService.addBlack(friendBlack);
		
		return "aaa"; //  팝업창으로 이동?..  
	}
	
	
	
	
	
	////////////////////////////////쪽지  관리/////////////////////////////////////////////////////////
	
	////////////////////////////////일대일 문의 관리/////////////////////////////////////////////////////////
	
	////////////////////////////////신고 관리/////////////////////////////////////////////////////////
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}	
	
