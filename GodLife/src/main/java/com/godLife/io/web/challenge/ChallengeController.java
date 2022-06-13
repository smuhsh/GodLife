package com.godLife.io.web.challenge;

import java.io.File;
import java.util.List;
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
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.databind.ser.std.StdKeySerializers.Default;
import com.godLife.io.common.ChallengeUtil;
import com.godLife.io.common.Page;
import com.godLife.io.common.Search;
import com.godLife.io.service.challenge.ChallengeService;
import com.godLife.io.service.domain.Challenge;
import com.godLife.io.service.domain.JoinChallenger;
import com.godLife.io.service.domain.Point;
import com.godLife.io.service.domain.User;
import com.godLife.io.service.point.PointService;
import com.godLife.io.service.user.UserService;

@Controller
@RequestMapping("/challenge/*")
public class ChallengeController {
	
	@Autowired
	@Qualifier("challengeServiceImpl")
	private ChallengeService challengeService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Autowired
	@Qualifier("pointServiceImpl")
	private PointService pointService;
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	
	@RequestMapping(value = "addChallenge", method=RequestMethod.GET)
	public String addChallengeView() {
		
		return "redirect:/challenge/addChallenge.jsp";
		
	}
	

	@RequestMapping(value="addChallenge",method=RequestMethod.POST)
	public String addChallenge(@ModelAttribute Challenge challenge,
							   HttpSession session,
							   JoinChallenger joinChallenger,
							   Point point,
							   Map<String,Object> map)throws Exception {
		
		User user = (User)session.getAttribute("user");
		
		
		challenge.setHostEmail(user.getUserEmail());
		
		challenge.setHostNick(user.getNick());
		
		challenge.setJoinCount(1);
		
		challenge.setChallengeStatus("0");
		
		challenge = ChallengeUtil.certiCycle(challenge);
		
		joinChallenger.setEmail(challenge.getHostEmail());
		
		joinChallenger.setStatus("0");
		
		point.setUserEmail(challenge.getHostEmail());
		
		point.setUseStatus("2");
		
		point.setPoint(challenge.getJoinPoint());
		
		point.setUseDetail("2");
		
		map.put("user", user);
		
		map.put("point", point);
		
		pointService.addPointPurchase(map);
		
		System.out.println("DB에 등록될 Challenge 정보: "+challenge);
		
		challengeService.addChallenge(challenge, joinChallenger);
		
		return "/main.jsp";
	}
	
	@RequestMapping(value = "addChallengeView", method=RequestMethod.POST)
	public String addChallengeView(@ModelAttribute Challenge challenge,
									MultipartHttpServletRequest mtfRequest,
									Model model,
									HttpServletRequest request,
									HttpSession session)throws Exception {
		
		//////////////////////////// 파일 업로드 /////////////////////////////////////////
		
		String path = "C:\\Users\\bitcamp\\git\\GodLife\\GodLife\\src\\main\\webapp\\resources\\images\\uploadFiles\\";
		
		MultipartFile mf = mtfRequest.getFile("thumbnail");
		
		if(mf==null) {
			mf = (MultipartFile)request.getAttribute("mf");
		}
		if(challenge == null) {
			challenge = (Challenge)request.getAttribute("challenge");
		}
		String originFileName = mf.getOriginalFilename();
		
		challenge.setChallengeThumbnailImg(System.currentTimeMillis()+originFileName);
		
		mf.transferTo(new File(path+challenge.getChallengeThumbnailImg()));
		
		
		////////////////////////////파일 업로드 /////////////////////////////////////////
		
		User user = (User)session.getAttribute("user");
		
		
		
		challenge = ChallengeUtil.certiCycle(challenge);
		
		challenge = ChallengeUtil.setCategName(challenge);
		
		System.out.println("Challenge : "+challenge);
		
		model.addAttribute("challenge",challenge);
		model.addAttribute("fileName",path+challenge.getChallengeThumbnailImg());
		model.addAttribute("challengeFileName",challenge.getChallengeThumbnailImg());
		model.addAttribute("user",user);
		return "forward:/challenge/addChallengeView.jsp";
		
	}
	
	
	
	@RequestMapping(value = "updateChallenge",method = RequestMethod.GET)
	public String updateChallengeView(@ModelAttribute("challenge") Challenge challenge,
									  HttpServletRequest requset,
									  Model model) {
		
		System.out.println("파일경로 : "+requset.getParameter("fileName"));
		
		
		System.out.println("updateChallenge challenge 정보 : "+challenge);
		
		model.addAttribute("fileName",requset.getParameter("fileName"));
		model.addAttribute("ChallengeFileName",requset.getParameter("challengeFileName"));
		return "forward:/challenge/updateChallenge.jsp";
	}
	
	
	
	@RequestMapping(value = "updateChallenge",method = RequestMethod.POST)
	public String updateChallenge(@ModelAttribute("challenge") Challenge challenge,
								  MultipartHttpServletRequest mtfRequest,
								  Model model,
								  HttpServletRequest request) {
		
		String fileName = request.getParameter("fileName");
		
		System.out.println("삭제대상 파일 경로 2: "+fileName);
		
		File file = new File(fileName);
		
		if(file.exists()) {
			if(file.delete()) {
				System.out.println("파일 삭제 성공");
			}else {
				System.out.println("파일 삭제 실패");
			}
		}else {
			System.out.println("파일이 존재하지 않습니다.");
		}
		
		System.out.println("updateChallenge challenge 정보 : "+challenge);
		
		
		model.addAttribute("mf",mtfRequest);
		return "forward:/challenge/addChallengeView";
	}
	
	@RequestMapping(value="addChallengeCancel",method=RequestMethod.POST)
	public String addChallengeCancel(@RequestParam String fileName) {
		
		File file = new File(fileName);
		
		if(file.exists()) {
			if(file.delete()) {
				System.out.println("파일 삭제 성공");
			}else {
				System.out.println("파일 삭제 실패");
			}
		}else {
			System.out.println("파일이 존재하지 않습니다.");
		}
		
		return "redirect:/main.jsp";
	}
	
	@RequestMapping(value="listChallenge", method=RequestMethod.GET)
	public String listChallenge(@ModelAttribute("search") Search search,
								Map<String,Object> map,
								HttpSession session,
								@RequestParam(defaultValue="total") String challengeListOpt,
								Model model) {
		
		
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		
		search.setPageSize(pageSize);
		
		User user = (User)session.getAttribute("user");
		
		map.put("user", user);
		map.put("search", search);
		map.put("challengeListOpt", challengeListOpt);
		
		map = challengeService.getChallengeList(map);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		List<Challenge> challengeList = (List<Challenge>)map.get("list");
		
		for(Challenge challenge : challengeList) {
			System.out.println("challenge List : "+challenge);
		}
		
		System.out.println("totalCount: "+map.get("totalCount"));
		
		model.addAttribute("challengeList",challengeList);
		model.addAttribute("resultPage",resultPage);
		
		return "forward:/challenge/listChallenge.jsp";
	}
	
	
	@RequestMapping(value="getChallenge", method=RequestMethod.GET)
	public String getChallenge(@RequestParam int challengeNo) {
		
		
		
		return "";
	}
	
	
	
}
