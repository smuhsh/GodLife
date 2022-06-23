package com.godLife.io.web.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.godLife.io.service.user.UserService;
import com.godLife.io.service.domain.User;

//==> 회원관리 RestController
@RestController
@RequestMapping("/user/*")
public class UserRestController {
	
	///Field
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	//setter Method 구현 않음
		
	public UserRestController(){
		System.out.println(this.getClass());
	}
	
	// useremail 중복체크
	@RequestMapping( value="json/checkUserEmail", method=RequestMethod.POST )
	public int checkId( @RequestParam("userEmail") String userEmail) throws Exception{
			int cnt = userService.checkUserEmail(userEmail);
			return cnt;
	}
	
	// nick 중복체크
	@RequestMapping( value="json/checkNick", method=RequestMethod.POST )
	public int checkNickname( @RequestParam("nick") String nick) throws Exception{
			int cnt = userService.checkNick(nick);
			return cnt;
	}
	
	// 핸드폰 중복체크
	@RequestMapping( value="json/checkPhone", method=RequestMethod.POST )
	public int checkPhone( @RequestParam("phone") String phone) throws Exception{
			int cnt = userService.checkPhone(phone);
			return cnt;
	}
	
		
//	//친구중복검사 체크 (레스트안쓰고 컨트롤러에서 다 처리해버림)... 
//	@RequestMapping( value="json/checkFriend", method=RequestMethod.POST)
//	public int checkFriend( //@RequestParam("userEmail") String userEmail,
//							@RequestParam("targetEmail") String targetEmail, 
//							HttpSession session) throws Exception{
//			
//		System.out.println("친구중복검사 체크 시작");
//		 User user = (User)session.getAttribute("user");
//		 String userEmail = user.getUserEmail();
//		 
//			int cnt = userService.checkFriend(userEmail, targetEmail);
//			
//			System.out.println("여기까지 제발와라...."+cnt);
//			
//			return cnt;
//	}
//	
	//블랙리스트 중복검사 체크 
	
	
	
	
	

}
	
