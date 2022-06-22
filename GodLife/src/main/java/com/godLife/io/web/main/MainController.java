package com.godLife.io.web.main;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.godLife.io.common.Search;
import com.godLife.io.service.challenge.ChallengeService;
import com.godLife.io.service.domain.CertiImg;
import com.godLife.io.service.domain.Challenge;
import com.godLife.io.service.domain.User;

@Controller
public class MainController {
	
	@Autowired
	@Qualifier("challengeServiceImpl")
	private ChallengeService challengeService;
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	
	@RequestMapping("/")
	public String main(Map<String,Object> map,
					   HttpSession session,
					   Search search,
					   Model model) {
		//공통

		System.out.println("메인");
		search.setPageSize(pageSize);
		search.setCurrentPage(1);
		User user = (User)session.getAttribute("user");
		System.out.println("user : "+user);
		String challengeListOpt = "total";
		map.put("challengeListOpt", challengeListOpt);
		map.put("search", search);
		map.put("user", user);
		
		//Best 챌린지 목록 준비
		search.setSearchCondition("1");//0 신규 1 인기
		map = challengeService.getChallengeList(map);
		List<Challenge> bestChallengeList = (List<Challenge>)map.get("list");
		//Best 챌린지 목록 준비 끝
		
		//신규 챌린지 목록 준비
		search.setSearchCondition("0");
		map.remove("black");
		map.remove("friend");
		map = challengeService.getChallengeList(map);
		List<Challenge> newChallengeList = (List<Challenge>)map.get("list");
		//신규 챌린지 목록 준비 끝
		System.out.println("메인");
		//관심사 챌린지 목록 준비(카테고리중 인기순으로 나옴)
		if(user != null) {
			search.setOrderCondition(user.getCategNo());
			map.remove("black");
			map.remove("friend");
			map = challengeService.getChallengeList(map);
			List<Challenge> categChallengeList = (List<Challenge>)map.get("list");
			System.out.println("categ : "+categChallengeList);
			model.addAttribute("categChallengeList",categChallengeList);
		}
		//관심사 챌린지 목록 준비 끝
		
		//친구가 등록한 챌린지 목록 준비
		if(user != null) {
			search.setOrderCondition(0);
			map.remove("black");
			map.remove("friend");
			map = challengeService.getChallengeListFriend(map);
			List<Challenge> friendChallengeList = (List<Challenge>)map.get("list");
			System.out.println("total : "+map.get("totalCount"));
			model.addAttribute("friendChallengeList",friendChallengeList);
		}
		//친구가 등록한 챌린지 목록 준비 끝
		
		//베스트 인증 이미지 목록
		
		search.setSearchCondition("2");//0 신규 1 인기
		map = challengeService.getChallengeCertiImgList(map);
		List<CertiImg> bestCertiImgList = (List<CertiImg>)map.get("certiImgList");
		//베스트 인증 이미지 목록 준비끝
		
		System.out.println("bestCert : "+ bestCertiImgList);
		System.out.println("메인");
		model.addAttribute("bestChallengeList",bestChallengeList);
		model.addAttribute("newChallengeList",newChallengeList);
		model.addAttribute("bestCertiImgList", bestCertiImgList);
		
		
		return "/main.jsp";
	}

}
