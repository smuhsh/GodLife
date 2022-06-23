package com.godLife.io.web.challenge;

import java.io.File;
import java.util.ArrayList;
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
import com.godLife.io.common.PostObject;
import com.godLife.io.common.Search;
import com.godLife.io.service.challenge.ChallengeService;
import com.godLife.io.service.domain.CertiImg;
import com.godLife.io.service.domain.Challenge;
import com.godLife.io.service.domain.JoinChallenger;
import com.godLife.io.service.domain.MyBadge;
import com.godLife.io.service.domain.Point;
import com.godLife.io.service.domain.Review;
import com.godLife.io.service.domain.User;
import com.godLife.io.service.mybadge.MyBadgeService;
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
	
	@Autowired
	@Qualifier("myBadgeServiceImpl")
	private MyBadgeService myBadgeService;
	
	
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
	public CertiImg deleteChallengeCertiImg(@RequestBody PostObject postObject,
										    CertiImg certiImg,
											MyBadge myBadge,
											User user) throws Exception {
		
		
		
		System.out.println("post: "+postObject);
		
		certiImg.setUser(user);
		
		certiImg.getUser().setUserEmail(postObject.getUserEmail());
		
		certiImg.setCertiImgNo(postObject.getCertiImgNo());
		System.out.println("certiImg : "+certiImg);
		myBadge.setBadgeNo(10003);
		myBadge.setUserEmail(certiImg.getUser().getUserEmail());
		myBadgeService.updateBadgeMyActCountMinus(myBadge);
		
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
	
	@RequestMapping(value="getChallengeCommentList", method=RequestMethod.GET)
	public List<Review> getChallengeCommentList(@RequestParam int certiImgNo,
												@ModelAttribute Search search,
												Map<String,Object> map) { //
		
		 // 댓글 목록을 가져올 인증이미지번호
		
		
		search.setPageSize(pageSize);
		
		map.put("search", search);
		map.put("certiImgNo", certiImgNo);
		
		List<Review> commentList = challengeService.getChallengeCommentList(map);
		
		System.out.println(certiImgNo+"번 인증이미지 댓글");
		for(Review comment : commentList) {
			System.out.println(comment.getReviewNo()+":"+comment.getNick()+":"+comment.getComment());
		}
		
		return commentList;
	}// 한번 더 요청
	
	
	
	
	@RequestMapping(value="addChallengeReview",method=RequestMethod.POST)
	public List<Review> addChallengeReview(@RequestBody PostObject postObject,
										   Review review,
										   Search search,
										   HttpSession session,
										   Map<String,Object> map){//
		
		
		
		
		User user = (User)session.getAttribute("user");

		System.out.println(review);
		review.setCertiImgNo(postObject.getCertiImgNo());
		review.setComment(postObject.getComment());
		review.setStatus(postObject.getStatus());
		review.setEmail(user.getUserEmail()); // 댓글은 로그인 후에 입력가능...
		challengeService.addChallengeReview(review);
		
		search.setPageSize(pageSize);
		search.setCurrentPage(postObject.getCurrentPage());
		
		map.put("search", search);
		map.put("certiImgNo", review.getCertiImgNo());
		
		List<Review> commentList = new ArrayList<Review>();
		
		if(search.getCurrentPage() >= 2) {
			commentList = challengeService.getChallengeMoreCommentList(map);
			System.out.println("more : "+commentList);
		}else {
			commentList = challengeService.getChallengeCommentList(map);
		}
		
		
		
		System.out.println(review.getCertiImgNo()+"번 인증이미지 댓글");
		for(Review comment : commentList) {
			System.out.println(comment.getNick()+":"+comment.getComment());
		}
		
		return commentList;
	}
	
	
	@RequestMapping(value="updateChallengeReview",method=RequestMethod.POST)
	public List<Review> updateChallengeReview(@RequestBody PostObject postObject,
											  Review review,
			                                  Search search,
											  Map<String,Object> map){//
		
		review.setReviewNo(postObject.getReviewNo());
		review.setComment(postObject.getComment());
		review.setCertiImgNo(postObject.getCertiImgNo());
		
		challengeService.updateChallengeReview(review);
		search.setPageSize(pageSize);
		search.setCurrentPage(postObject.getCurrentPage());
		
		map.put("search", search);
		map.put("certiImgNo", postObject.getCertiImgNo());
		List<Review> commentList = new ArrayList<Review>();
		
		if(search.getCurrentPage() >= 2) {
			commentList = challengeService.getChallengeMoreCommentList(map);
		}else {
			commentList = challengeService.getChallengeCommentList(map);
		}
		
		System.out.println(review.getCertiImgNo()+"번 인증이미지 댓글");
		for(Review comment : commentList) {
			System.out.println(comment.getNick()+":"+comment.getComment());
		}
		
		return commentList;
	}
	
	@RequestMapping(value="deleteChallengeReview",method=RequestMethod.POST)
	public List<Review> deleteChallengeReview(@RequestBody PostObject postObject,
											  Search search,
											  Map<String,Object> map){//
		
		
		
		System.out.println("PostObject ::"+postObject);
		
		challengeService.deleteChallengeReview(postObject.getReviewNo());
		search.setPageSize(pageSize);
		search.setCurrentPage(postObject.getCurrentPage());
		
		map.put("search", search);
		map.put("certiImgNo", postObject.getCertiImgNo());
		
		List<Review> commentList = new ArrayList<Review>();
		
		if(search.getCurrentPage() >= 2) {
			commentList = challengeService.getChallengeMoreCommentList(map);
		}else {
			commentList = challengeService.getChallengeCommentList(map);
		}
		
		System.out.println(postObject.getCertiImgNo()+"번 인증이미지 댓글");
		for(Review comment : commentList) {
			System.out.println(comment.getNick()+":"+comment.getComment());
		}
		
		return commentList;
	}
	
	
	@RequestMapping(value="addChallengeReviewLike",method=RequestMethod.POST)
	public CertiImg addChallengeReviewLike(@RequestBody Review review,
										   HttpSession session,
										   CertiImg certiImg,
										   Map<String,Object> map) {
		User user = (User)session.getAttribute("user");
		review.setEmail(user.getUserEmail());
		
		challengeService.addChallengeReview(review);
		
		map.put("user", user);
		map.put("certiImgNo", review.getCertiImgNo());
		
		
		certiImg = challengeService.getChallengeCertiImg(map);
		
		System.out.println("certiImg : "+certiImg);
		
		return certiImg;
	}
	
	
	
	@RequestMapping(value="getChallengeCertiImg",method=RequestMethod.GET)
	public CertiImg getChallengeCertiImg(@ModelAttribute CertiImg certiImg,
								 HttpSession session,
								 Map<String,Object> map) {
		
		User user = (User)session.getAttribute("user");
		
		System.out.println("CertiImgNo : "+certiImg.getCertiImgNo());
		
		map.put("user", user);
		map.put("certiImgNo", certiImg.getCertiImgNo());
		
		certiImg = challengeService.getChallengeCertiImg(map);
		
		System.out.println("certiImg : "+certiImg);
		
		return certiImg;
		
		// 데이터 내려오고 유효성 검사
	}
	
	@RequestMapping(value="deleteChallengeReviewLike",method=RequestMethod.POST)
	public CertiImg deleteChallengeReviewLike(@RequestBody Review review,
											  CertiImg certiImg,
											  HttpSession session,
											  Map<String,Object> map) {
		
		User user = (User)session.getAttribute("user");
		
		System.out.println("delete review : "+review);
		
		map.put("user", user);
		map.put("review", review);
		challengeService.deleteChallengeReviewLike(map);
		
		
		map.put("certiImgNo", review.getCertiImgNo());
		
		
		certiImg = challengeService.getChallengeCertiImg(map);
		
		System.out.println("certiImg : "+certiImg);
		
		return certiImg;
	}
	
	@RequestMapping(value="addChallengeReviewDislike",method=RequestMethod.POST)
	public CertiImg addChallengeReviewDislike(@RequestBody Review review,
											  CertiImg certiImg,
											  HttpSession session,
											  Map<String,Object> map) {
		
		User user = (User)session.getAttribute("user");
		review.setEmail(user.getUserEmail());
		
		challengeService.addChallengeReview(review);
		
		map.put("user", user);
		map.put("certiImgNo", review.getCertiImgNo());
		
		
		certiImg = challengeService.getChallengeCertiImg(map);
		
		return certiImg;
	}
	
	@RequestMapping(value="deleteChallengeCertiImgList",method=RequestMethod.POST)
	public List<CertiImg> deleteChallengeCertiImgsList(@RequestBody PostObject postObject,
													Search search,
													HttpSession session,
													Map<String,Object> map,
													MyBadge myBadge) throws Exception {
		
		System.out.println("PostObject : "+postObject);
		User user = (User)session.getAttribute("user");
		
		myBadge.setBadgeNo(10003);
		myBadge.setUserEmail(user.getUserEmail());
		myBadgeService.updateBadgeMyActCountMinus(myBadge);
		
		
		search.setCurrentPage(postObject.getCurrentPage());
		search.setPageSize(pageSize);
		
		map.put("certiImgOpt",postObject.getCertiImgOpt());
		map.put("search", search);
		map.put("user", user);
		
		map.put("certiImgNo", postObject.getCertiImgNo());
		
		challengeService.deleteChallengeCertiImg(map);
		
		System.out.println("map : "+map);
		
		System.out.println("요청 페이지 : "+search.getCurrentPage());
		
		if(search.getCurrentPage() >= 2) {
			map = challengeService.getChallengeMoreCertiImgList(map);
		}else {
			map = challengeService.getChallengeCertiImgList(map);
		}
		List<CertiImg> certiImgList = (List<CertiImg>)map.get("certiImgList");
		for(CertiImg certiImg : certiImgList) {
			System.out.println(certiImg);
		}
		
		return certiImgList;
	}
}
