package com.godLife.io.web.challenge;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godLife.io.common.Page;
import com.godLife.io.common.Search;
import com.godLife.io.service.challenge.ChallengeService;
import com.godLife.io.service.domain.CertiImg;
import com.godLife.io.service.domain.Challenge;
import com.godLife.io.service.domain.JoinChallenger;
import com.godLife.io.service.domain.Point;
import com.godLife.io.service.domain.Review;
import com.godLife.io.service.domain.User;
import com.godLife.io.service.point.PointService;
import com.godLife.io.service.user.UserService;


@RestController
@RequestMapping("/challenge/challengeRest/*")
public class ChallengeRestController {
	
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
	
	
	@RequestMapping(value="deleteChallenge", method=RequestMethod.POST)
	public List<Challenge> deleteChallenge(@RequestBody Challenge challenge,
								User user,
								Point point,
								Search search,
								Model model,
								HttpSession session)throws Exception {
			
		System.out.println("삭제될 챌린지 번호 : "+challenge.getChallengeNo());
		
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("user", user);
		map.put("challengeNo", challenge.getChallengeNo());
		
		Challenge deleteChallengeThumbnail = challengeService.getChallenge(map);
		
		String path = "C:\\Users\\bitcamp\\git\\GodLife\\GodLife\\src\\main\\webapp\\resources\\images\\uploadFiles\\";
		
		File file = new File(path+deleteChallengeThumbnail.getChallengeThumbnailImg());
		
		if(file.exists()) {
			if(file.delete()) {
				System.out.println("파일 삭제 성공");
			}else {
				System.out.println("파일 삭제 실패");
			}
		}else {
			System.out.println("파일이 존재하지 않습니다.");
		}
		
		
		map = challengeService.deleteChallenge(challenge.getChallengeNo());
		
		List<String> userList = (List<String>)map.get("challengeJoinList");
		
		//챌린지에 참여했던 유저들의 포인트 환불 작업
		for(String userEmail : userList) {
			
			user = userService.getUser(userEmail);
			
			point.setUserEmail(user.getUserEmail());
			
			point.setUseStatus("1");
			
			point.setPoint((Integer)map.get("challengeJoinPoint"));
			
			point.setUseDetail("3");
			
			map.put("user", user);
			
			map.put("point", point);
			
			pointService.addPointPurchase(map);
			
		}
		
		
		
		
		
		/////////////////////////delete ///////////////////////////////////
		
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		
		search.setPageSize(pageSize);
		
		user = (User)session.getAttribute("user");
		
		String challengeListOpt = "add";
		
		map.put("user", user);
		map.put("search", search);
		map.put("challengeListOpt", challengeListOpt);
		
		map = challengeService.getChallengeList(map);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		List<Challenge> challengeList = (List<Challenge>)map.get("list");
		
		for(Challenge challenges : challengeList) {
			System.out.println("challenge List : "+challenges);
		}
		
		System.out.println("totalCount: "+map.get("totalCount"));
		
		model.addAttribute("challengeList",challengeList);
		model.addAttribute("resultPage",resultPage);
		
		return challengeList;
	}
	
	
	@RequestMapping(value="getChallengePick",method=RequestMethod.GET)
	public Map<String,Object> getChallengePick(HttpSession session,
											   @ModelAttribute Challenge challenge){
		
		User user = (User)session.getAttribute("user");
		
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("user", user);
		map.put("challengeNo", challenge.getChallengeNo());
		
		int pickCount = challengeService.getChallengePick(map);
		
		System.out.println("pickCount : "+pickCount);
		
		if(pickCount != 0) {
			map.put("result", "중복아님");
		}
		
		System.out.println("result "+map.get("result"));
		
		return map;
	}
	
	@RequestMapping(value="deleteChallengePick",method=RequestMethod.POST)
	public Challenge deleteChallengePick(HttpSession session,
									  @RequestBody Challenge challenge,
									  JoinChallenger joinChallenger) {
		
		
		User user = (User)session.getAttribute("user");
		
		joinChallenger.setEmail(user.getUserEmail());
		joinChallenger.setChallengeNo(challenge.getChallengeNo());
		joinChallenger.setStatus("1");
		
		challengeService.deleteChallengeJoin(joinChallenger);
		
		return challenge;
	}
	
	@RequestMapping(value="deleteChallengeCertiImg",method=RequestMethod.POST)
	public CertiImg deleteChallengeCertiImg(@RequestBody CertiImg certiImg) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("certiImgNo", certiImg.getCertiImgNo());
		
		challengeService.deleteChallengeCertiImg(map);
		return certiImg;
	}
	
	@RequestMapping(value="getChallengeCertiImgList",method=RequestMethod.GET)
	public List<CertiImg> getChallengeCertiImgList(@RequestParam(required = false) String certiImgOpt,
												   @RequestParam int pageNo,
												   Search search,
												   HttpSession session,
												   Map<String,Object> map){
		
		User user = (User)session.getAttribute("user");
		search.setCurrentPage(pageNo);
		search.setPageSize(pageSize);
		
		map.put("certiImgOpt",certiImgOpt);
		map.put("search", search);
		map.put("user", user);
		map = challengeService.getChallengeCertiImgList(map);
		System.out.println("map : "+map);
		List<CertiImg> certiImgList = (List<CertiImg>)map.get("certiImgList");
		
		for(CertiImg certiImg : certiImgList) {
			System.out.println(certiImg);
		}
		
		return certiImgList;
	}
	
	@RequestMapping(value="addChallengeReviewLike",method=RequestMethod.GET)
	public CertiImg addChallengeReviewLike(@RequestParam int certiImgNo,HttpSession session,CertiImg certiImg) {
		
		User user = (User)session.getAttribute("user");
		Review review = new Review();
		review.setStatus("1");
		review.setCertiImgNo(certiImgNo);
		review.setEmail(user.getUserEmail());
		challengeService.addChallengeReview(review);
		certiImg=(challengeService.getChallengeCertiImg(certiImgNo));
		System.out.println("RestController Review certiImg"+certiImg);
		return certiImg;
	}
	
	@RequestMapping(value="addChallengeReviewDislike",method=RequestMethod.GET)
	public CertiImg addChallengeReviewDislike(@RequestParam int certiImgNo,HttpSession session,CertiImg certiImg) {
		
		User user = (User)session.getAttribute("user");
		Review review = new Review();
		review.setStatus("2");
		review.setCertiImgNo(certiImgNo);
		review.setEmail(user.getUserEmail());
		System.out.println("review "+ review);
		challengeService.addChallengeReview(review);
		certiImg=(challengeService.getChallengeCertiImg(certiImgNo));
		System.out.println("RestController Review certiImg"+certiImg);
		return certiImg;
	}
	
	@RequestMapping(value="duplicationLike",method=RequestMethod.GET)
	public Map<String,Object> duplicationLike(@RequestParam int certiImgNo,@RequestParam String status,HttpSession session,CertiImg certiImg) {
		User user = (User)session.getAttribute("user");

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("userEmail", user.getUserEmail());
		map.put("certiImgNo", certiImgNo);
		map.put("status", status);
		challengeService.getChallengeReview(map);
		System.out.println("total"+map.get("totalCount"));
		int totalCount = (Integer)map.get("totalCount");
		
		return map;
	}
	@RequestMapping(value="getChallengeCommentList", method=RequestMethod.GET)
	public List<Review> getChallengeCommentList(@RequestParam int certiImgNo) {
		
		 // 댓글 목록을 가져올 인증이미지번호
		
		List<Review> commentList = challengeService.getChallengeCommentList(certiImgNo);
		
		System.out.println(certiImgNo+"번 인증이미지 댓글");
		for(Review comment : commentList) {
			System.out.println(comment.getNick()+":"+comment.getComment());
		}
		
		return commentList;
	}
	
	@RequestMapping(value="addChallengeReview",method=RequestMethod.POST)
	public List<Review> addChallengeReview(@RequestBody Review review,
										   HttpSession session){
		
		
		
		
		User user = (User)session.getAttribute("user");

		System.out.println(review);
		review.setEmail(user.getUserEmail()); // 댓글은 로그인 후에 입력가능...
		challengeService.addChallengeReview(review);
		
		List<Review> commentList = challengeService.getChallengeCommentList(review.getCertiImgNo());
		
		System.out.println(review.getCertiImgNo()+"번 인증이미지 댓글");
		for(Review comment : commentList) {
			System.out.println(comment.getNick()+":"+comment.getComment());
		}
		
		return commentList;
	}
}
