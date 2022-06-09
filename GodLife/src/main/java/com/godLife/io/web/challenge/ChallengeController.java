package com.godLife.io.web.challenge;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.godLife.io.service.challenge.ChallengeService;
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
	public String addChallenge() {
		
		return "redirect:/challenge/addChallenge.jsp";
		
	}
	
}
