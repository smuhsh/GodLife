package com.godLife.io.web.mybadge;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.godLife.io.common.Page;
import com.godLife.io.common.Search;
import com.godLife.io.service.domain.Badge;
import com.godLife.io.service.domain.MyBadge;
import com.godLife.io.service.domain.User;
import com.godLife.io.service.mybadge.MyBadgeService;
import com.godLife.io.service.user.UserService;


//==> 상품관리 Controller
@Controller
@RequestMapping("/myBadge/*")
public class MyBadgeController {
	
	///Field
	@Autowired
	@Qualifier("myBadgeServiceImpl")
	private MyBadgeService myBadgeService;
	//setter Method 구현 않음
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;	
	
	
	
	
	
	
	public MyBadgeController(){
		System.out.println(this.getClass());
	}
	
	//==> classpath:config/common.properties  ,  classpath:config/commonservice.xml 참조 할것
	//==> 아래의 두개를 주석을 풀어 의미를 확인 할것
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	


	@RequestMapping( value="getBadgeMy", method=RequestMethod.GET )
	public String getBadgeMy( @RequestParam("myBadgeNo") int myBadgeNo , User user, Model model ) throws Exception {
		
		System.out.println("/myBadge/getBadgeMy : GET");
		
		//Business Logic
		MyBadge myBadge = myBadgeService.getBadgeMy(myBadgeNo, user);
		// Model 과 View 연결
		model.addAttribute("myBadge", myBadge);
		//setAttribute로 쓰고 value값이 들어간다면,
		
		return "forward:/myBadge/getBadgeMy.jsp";
	}
///////////////////////////////////////////Pass/////////////////////////////	

	
///////////////////////////////////////////Pass/////////////////////////////	
	
	@RequestMapping( value="getBadgeMyList" )
	public String getBadgeMyList( @ModelAttribute("search") Search search , Model model ,Badge badge, HttpServletRequest request) throws Exception{

		System.out.println("/myBadge/getBadgeMyList : GET / POST");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		User user = (User) request.getSession().getAttribute("user");
		String userEmail = user.getUserEmail();
		
		String badgeImg = badge.getBadgeImg();
		System.out.println("session badgeImg : " + badgeImg);
		System.out.println("session  : " + userEmail);
		
		
		Map<String , Object> map = myBadgeService.getBadgeMyList(search, user, badge);
		
		System.out.println("@@@@@@ controller getBadgeMyList Map@@@@@@@@@@@@@ :" + map);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		System.out.println(resultPage);
		
		List<Object> list1 = (List<Object>) map.get("list1");
		System.out.println(list1);
		List<Object> list2 = (List<Object>) map.get("list2");
		System.out.println(list2);		
		
		// Model 과 View 연결
		model.addAttribute("list1", map.get("list1"));
		model.addAttribute("list2", map.get("list2"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		System.out.println(search);
		
		return "forward:/myBadge/getBadgeMyList.jsp";
	}

}