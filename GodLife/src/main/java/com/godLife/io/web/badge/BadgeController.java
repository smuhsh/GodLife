package com.godLife.io.web.badge;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

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
import com.godLife.io.service.badge.BadgeService;
import com.godLife.io.service.domain.Badge;
import com.godLife.io.service.mybadge.MyBadgeService;
import com.godLife.io.service.domain.Badge;


//==> 상품관리 Controller
@Controller
@RequestMapping("/badge/*")
public class BadgeController {
	
	///Field
	@Autowired
	@Qualifier("badgeServiceImpl")
	private BadgeService badgeService;

	//setter Method 구현 않음
		
	public BadgeController(){
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
	
	
	@RequestMapping( value="addBadge", method=RequestMethod.GET )
	public String addBadge() throws Exception {

		System.out.println("/badge/addBadgeView : GET");
		
		return "/badge/addBadgeView.jsp";
	}

	@RequestMapping( value="addBadge", method=RequestMethod.POST )
	public String addBadge( @ModelAttribute("badge") Badge badge ) throws Exception {
		
		System.out.println("/badge/addBadge : POST");
		//Business Logic
		badgeService.addBadge(badge);

		return "forward:/badge/getBadgeList.jsp";

	}

	@RequestMapping( value="getBadge", method=RequestMethod.GET )
	public String getBadge( @RequestParam("badgeNo") int badgeNo , Model model ) throws Exception {
		
		System.out.println("/badge/getBadge : GET");
		
		//Business Logic
		Badge badge = badgeService.getBadge(badgeNo);
		// Model 과 View 연결
		model.addAttribute("badge", badge);
		//setAttribute로 쓰고 value값이 들어간다면,
		
		return "forward:/badge/getBadge.jsp";
	}
	/////////////////////////////////Pass/////////////////////////////	

	
	@RequestMapping( value="updateBadge", method=RequestMethod.GET )
	public String updateBadge( @RequestParam("badgeNo") int badgeNo , Model model ) throws Exception{	
	
		System.out.println("/badge/updateBadge : GET");
		
		//Business Logic
		Badge badge = badgeService.getBadge(badgeNo);
		// Model 과 View 연결
		model.addAttribute("badge", badge);
		
		return "forward:/badge/updateBadgeView.jsp";

	}


	@RequestMapping( value="updateBadge", method=RequestMethod.POST )
	public String updateBadge( @ModelAttribute("badge") Badge badge , Model model , HttpSession session) throws Exception{

		System.out.println("/badge/updateBadge : POST");
		
		//Business Logic
		badgeService.updateBadge(badge);

		return "redirect:/badge/getBadge?badgeNo="+badge.getBadgeNo();
	}

	@RequestMapping( value="getBadgeList" )
	public String getBadgeList( @ModelAttribute("search") Search search , Model model , HttpServletRequest request) throws Exception{
		
		System.out.println("/badge/getBadgeList : GET / POST");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		Map<String , Object> map = badgeService.getBadgeList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/badge/getBadgeList.jsp";
	}
	
	@RequestMapping (value = "deleteBadge")
	public String deleteBadge( @ModelAttribute("badge") Badge badgeNo , Model model ) throws Exception {
		
	System.out.println("/badge/deleteBadge : POST");
	//Business Logic
	badgeService.deleteBadge(badgeNo);

	return "redirect:/badge/listBadge.jsp";
	
	
	}
	
	
	
	
	
	
}