package com.godLife.io.web.challenge;

import java.io.File;

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

import com.godLife.io.common.ChallengeUtil;
import com.godLife.io.service.challenge.ChallengeService;
import com.godLife.io.service.domain.Challenge;
import com.godLife.io.service.domain.JoinChallenger;
import com.godLife.io.service.domain.User;
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
							   JoinChallenger joinChallenger)throws Exception {
		
		User user = (User)session.getAttribute("user");
		
		System.out.println("DB에 등록될 Challenge 정보: "+challenge);
		
		challenge.setHostEmail(user.getUserEmail());
		
		challenge.setHostNick(user.getNick());
		
		challenge = ChallengeUtil.certiCycle(challenge);
		
		joinChallenger.setEmail(challenge.getHostEmail());
		
		joinChallenger.setStatus("0");
		
		challengeService.addChallenge(challenge, joinChallenger);
		
		return "/main.jsp";
	}
	
	@RequestMapping(value = "addChallengeView", method=RequestMethod.POST)
	public String addChallengeView(@ModelAttribute Challenge challenge,
									MultipartHttpServletRequest mtfRequest,
									Model model,
									HttpServletRequest request)throws Exception {
		
		//////////////////////////// 파일 업로드 /////////////////////////////////////////
		
		String path = "C:\\Users\\bitcamp\\git\\GodLife\\GodLife\\src\\main\\webapp\\images\\uploadFiles\\";
		
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
		
		
		challenge = ChallengeUtil.certiCycle(challenge);
		
		challenge = ChallengeUtil.setCategName(challenge);
		
		System.out.println("Challenge : "+challenge);
		
		model.addAttribute("challenge",challenge);
		model.addAttribute("fileName",path+challenge.getChallengeThumbnailImg());
		model.addAttribute("challengeFileName",challenge.getChallengeThumbnailImg());
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
	
	
}
