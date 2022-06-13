package com.godLife.io.web.challenge;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godLife.io.common.Page;
import com.godLife.io.common.Search;
import com.godLife.io.service.challenge.ChallengeService;
import com.godLife.io.service.domain.Challenge;
import com.godLife.io.service.domain.User;
import com.godLife.io.service.point.PointService;
import com.godLife.io.service.user.UserService;


@RestController
@RequestMapping("/challenge/challengeRest/*")
public class ChallengeRestController {
	
	@Autowired
	@Qualifier("challengeServiceImpl")
	private ChallengeService challengeService;
	
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	
	@RequestMapping(value="listChallenge", method=RequestMethod.GET)
	public List listChallenge(@ModelAttribute Search search,
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
		
		
		return challengeList;
	}
	
}
