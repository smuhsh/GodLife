package com.godLife.io.web.badge;

import java.io.File;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.godLife.io.common.Page;
import com.godLife.io.common.Search;
import com.godLife.io.service.badge.BadgeService;
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
	
	@Resource(name="uploadPath")
	String uploadPath;
	
	
	@RequestMapping( value="addBadgeView", method=RequestMethod.GET )
	public String addBadge() throws Exception {

		System.out.println("/badge/addBadgeView : GET");
		
		return "/badge/addBadgeView.jsp";
	}

	@RequestMapping( value="addBadge", method=RequestMethod.POST )
	public String addBadge( @ModelAttribute("badge") Badge badge, 
							@RequestParam ("imageUpload" )  MultipartFile file) throws Exception {
		
	System.out.println("/badge/addBadge : POST");
	//Business Logic
	String fileName = file.getOriginalFilename();
	
	fileName =  uploadFile(fileName, file.getBytes());   // 파일 이름 중복 제거 
	
	badge.setBadgeImg(fileName);
	
	badgeService.addBadge(badge);
	//Redirect로 안하고 Forward로 했을때는 Session때문인지 새로고침 할때마다 반복된 행동으로 계속 추가 됨
	return "redirect:/badge/getBadgeList?badgeNo="+badge.getBadgeNo();
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

	   //이미지 업로드 파일명 랜덤 생성 메서드
 private String uploadFile(String originalName, byte[] fileData) throws Exception{
 
     // uuid 생성 
     UUID uuid = UUID.randomUUID();
     
     //savedName 변수에 uuid + 원래 이름 추가
     String savedName = uuid.toString()+"_"+originalName;
     System.out.println("Before : target path " + uploadPath);

     //uploadPath경로의 savedName 파일에 대한 file 객체 생성
     File target = new File(uploadPath, savedName);
     //fileData의 내용을 target에 복사함
     System.out.println("Before : FileCopyUtils");
     System.out.println("Before : target " + target);

     FileCopyUtils.copy(fileData, target);	
     
     System.out.println("After : FileCopyUtils");
     
     return savedName;
 }		
	
	
	
	
	@RequestMapping( value="updateBadgeView", method=RequestMethod.GET )
	public String updateBadge( @RequestParam("badgeNo") int badgeNo , Model model ) throws Exception{	
	
		System.out.println("/badge/updateBadgeView : GET");
		
		//Business Logic
		Badge badge = badgeService.getBadge(badgeNo);
		// Model 과 View 연결
		model.addAttribute("badge", badge);
		
		return "forward:/badge/updateBadgeView.jsp";

	}


	@RequestMapping( value="updateBadge", method=RequestMethod.POST )
	public String updateBadge( @ModelAttribute("badge") Badge badge , Model model , HttpSession session,
								@RequestParam ("imageUpload" )  MultipartFile file) throws Exception{

		System.out.println("/badge/updateBadge : POST");

		String fileName = file.getOriginalFilename();

		fileName =  uploadFile(fileName, file.getBytes());   // 파일 이름 중복 제거 

		badge.setBadgeImg(fileName);
		
		
		//Business Logic
		badgeService.updateBadge(badge);

		return "forward:/badge/getBadgeList?badgeNo="+badge.getBadgeNo();
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
		model.addAttribute("list1", map.get("list1"));
		model.addAttribute("list2", map.get("list2"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/badge/getBadgeList.jsp";
	}
	
	@RequestMapping (value = "deleteBadge")
	public String deleteBadge( @ModelAttribute("badge") Badge badgeNo , Model model ) throws Exception {
		
	System.out.println("/badge/deleteBadge : POST");
	//Business Logic
	badgeService.deleteBadge(badgeNo);

	return "forward:/badge/getBadgeList";
	
	
	}
	
	
	
	
	
	
}