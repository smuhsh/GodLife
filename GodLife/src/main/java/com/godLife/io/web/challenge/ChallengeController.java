package com.godLife.io.web.challenge;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
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
import com.godLife.io.service.domain.CertiImg;
import com.godLife.io.service.domain.Challenge;
import com.godLife.io.service.domain.JoinChallenger;
import com.godLife.io.service.domain.MyBadge;
import com.godLife.io.service.domain.Point;
import com.godLife.io.service.domain.User;
import com.godLife.io.service.mybadge.MyBadgeService;
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
	
	@Autowired
	@Qualifier("myBadgeServiceImpl")
	private MyBadgeService myBadgeService;
	
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
		
		System.out.println("DB??? ????????? Challenge ??????: "+challenge);
		
		map = challengeService.addChallenge(challenge, joinChallenger);
		
		int challengeNo = (Integer)map.get("challengeNo");
		
		System.out.println("????????? ????????? ????????? ??????: "+challengeNo);
		
		return "redirect:/challenge/getChallenge?challengeNo="+challengeNo;
	}
	
	@RequestMapping(value = "addChallengeView", method=RequestMethod.POST)
	public String addChallengeView(@ModelAttribute Challenge challenge,
									MultipartHttpServletRequest mtfRequest,
									Model model,
									HttpServletRequest request,
									HttpSession session)throws Exception {
		
		//////////////////////////// ?????? ????????? /////////////////////////////////////////
		//??????
		String path = "C:\\Users\\bitcamp\\git\\GodLife\\GodLife\\src\\main\\webapp\\resources\\images\\uploadFiles\\";
		//???
		//String path = "C:\\Users\\?????????\\git\\GodLife\\GodLife\\src\\main\\webapp\\resources\\images\\uploadFiles\\";
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
		
		
		////////////////////////////?????? ????????? /////////////////////////////////////////
		
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
		
		System.out.println("???????????? : "+requset.getParameter("fileName"));
		
		
		System.out.println("updateChallenge challenge ?????? : "+challenge);
		
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
		
		System.out.println("???????????? ?????? ?????? 2: "+fileName);
		
		File file = new File(fileName);
		
		if(file.exists()) {
			if(file.delete()) {
				System.out.println("?????? ?????? ??????");
			}else {
				System.out.println("?????? ?????? ??????");
			}
		}else {
			System.out.println("????????? ???????????? ????????????.");
		}
		
		System.out.println("updateChallenge challenge ?????? : "+challenge);
		
		
		model.addAttribute("mf",mtfRequest);
		return "forward:/challenge/addChallengeView";
	}
	
	@RequestMapping(value="addChallengeCancel",method=RequestMethod.POST)
	public String addChallengeCancel(@RequestParam String fileName) {
		
		File file = new File(fileName);
		
		if(file.exists()) {
			if(file.delete()) {
				System.out.println("?????? ?????? ??????");
			}else {
				System.out.println("?????? ?????? ??????");
			}
		}else {
			System.out.println("????????? ???????????? ????????????.");
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
		System.out.println("search : "+search);
		map.put("challengeListOpt", challengeListOpt);
		
		System.out.println("challengeListOpt "+challengeListOpt);
		
		if(challengeListOpt.equals("friend")) {
			map = challengeService.getChallengeListFriend(map);
		}else {
			map = challengeService.getChallengeList(map);
		}
		
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		List<Challenge> challengeList = (List<Challenge>)map.get("list");
		
		for(Challenge challenge : challengeList) {
			System.out.println("challenge List : "+challenge);
		}
		
		System.out.println("totalCount: "+map.get("totalCount"));
		
		model.addAttribute("challengeList",challengeList);
		model.addAttribute("resultPage",resultPage);
		model.addAttribute("challengeListOpt",challengeListOpt);
		
		
		return "forward:/challenge/listChallenge.jsp";
	}
	
	
	
	
	@RequestMapping(value="deleteChallenge", method= RequestMethod.POST)
	public String deleteChallenge(@RequestParam int challengeNo,
								  User user,
								  Point point,
								  Model model)throws Exception {
		
		System.out.println("????????? ????????? ?????? : "+challengeNo);
		
		Map<String,Object> map = challengeService.deleteChallenge(challengeNo);
		
		List<String> userList = (List<String>)map.get("challengeJoinList");
		
		//???????????? ???????????? ???????????? ????????? ?????? ??????
		for(String userEmail : userList) {
			
			System.out.println("userEamil : "+userEmail);
			
			user = userService.getUser(userEmail);
			
			point.setUserEmail(user.getUserEmail());
			
			point.setUseStatus("1");
			
			point.setPoint((Integer)map.get("challengeJoinPoint"));
			
			point.setUseDetail("3");
			
			map.put("user", user);
			
			map.put("point", point);
			
			pointService.addPointPurchase(map);
			
		}
		
		
		return "redirect:/challenge/listChallenge?challengeListOpt=add";
	}
	
	@RequestMapping(value="getChallenge", method=RequestMethod.GET)
	public String getChallenge(@RequestParam int challengeNo,
							   User user,
							   HttpSession session,
							   Map<String,Object> map,
							   Model model,
							   JoinChallenger joinChallenger) throws Exception {
		
		System.out.println("challengeNo : "+challengeNo);
		user = (User)session.getAttribute("user");
		map.put("user", user);
		map.put("challengeNo", challengeNo);
		Challenge challenge = challengeService.getChallenge(map);
		List<JoinChallenger> joinChallengerList = 
				challengeService.getChallengeJoinerList(challenge.getChallengeNo());
		
		
		
		
		int reward = 0;
		if(challenge.getChallengeJoinFlag().equals("1")) {
			
			joinChallenger = challengeService.getChallengeJoiner(map);
			map = challengeService.getChallengeRewardElement(joinChallenger);
			if(joinChallenger.getChallengePercent() == 100) {
				int totalPoint = challenge.getJoinPoint()*challenge.getJoinCount();
				reward = (totalPoint
						- (
							(int)(challenge.getJoinPoint()*0.6)*(Integer)map.get("sixtyCount")+
							(int)(challenge.getJoinPoint()*0.7)*(Integer)map.get("seventyCount")+
							(int)(challenge.getJoinPoint()*0.8)*(Integer)map.get("eightyCount")+
							(int)(challenge.getJoinPoint())*(Integer)map.get("ninetyCount")
							
								)/(Integer)map.get("hundredCount"));
				System.out.println("?????? : "+reward);
			}else if(joinChallenger.getChallengePercent() >= 90){
				reward = (int)(challenge.getJoinPoint());
			}else if(joinChallenger.getChallengePercent() >= 80){
				reward = (int)(challenge.getJoinPoint()*0.8);
			}else if(joinChallenger.getChallengePercent() >= 70){
				reward = (int)(challenge.getJoinPoint()*0.7);
			}else if(joinChallenger.getChallengePercent() >= 60){
				reward = (int)(challenge.getJoinPoint()*0.6);
			}
			
			
		}
		joinChallenger.setChallengeReward(reward);
		User hostUser = userService.getUser(challenge.getHostEmail());
		model.addAttribute("joinChallengerList",joinChallengerList);
		model.addAttribute("joinChallenger",joinChallenger);
		model.addAttribute("challenge",challenge);
		model.addAttribute("hostUser",hostUser);
		model.addAttribute("user",user);
		
		return "forward:/challenge/getChallenge.jsp";
	}
	
	
	@RequestMapping(value="addChallengeJoin",method=RequestMethod.POST)
	public String addChallengeJoin(JoinChallenger joinChallenger,
								   @ModelAttribute Challenge challenge,
								   HttpSession session,
								   Map<String,Object> map,
								   Model model,
								   Point point) throws Exception {
		
		User user = (User)session.getAttribute("user");
		
		joinChallenger.setEmail(user.getUserEmail());
		joinChallenger.setChallengeNo(challenge.getChallengeNo());
		joinChallenger.setStatus("0");
		
		
		point.setUserEmail(user.getUserEmail());
		
		point.setUseStatus("2");
		
		point.setPoint(challenge.getJoinPoint());
		
		point.setUseDetail("2");
		
		map.put("user", user);
		
		map.put("point", point);
		
		pointService.addPointPurchase(map);
		
		challengeService.addChallengeJoin(joinChallenger);
		
		map.put("challengeNo", challenge.getChallengeNo());
		
		
		return "redirect:/challenge/getChallenge?challengeNo="+challenge.getChallengeNo();
	}
	
	
	@RequestMapping(value = "deleteChallengeJoin",method=RequestMethod.POST)
	public String deleteChallengeJoin(@ModelAttribute Challenge challenge,
									  JoinChallenger joinChallenger,
									  HttpSession session,
									  Map<String,Object> map,
									  Model model,
									  Point point) throws Exception {
		
		User user = (User)session.getAttribute("user");
		
		joinChallenger.setEmail(user.getUserEmail());
		joinChallenger.setChallengeNo(challenge.getChallengeNo());
		joinChallenger.setStatus("0");
		
		
		if(challenge.getHostEmail().equals(user.getUserEmail())) {
			
			map.put("user", user);
			map.put("challengeNo", challenge.getChallengeNo());
			
			Challenge deleteChallengeThumbnail = challengeService.getChallenge(map);
			//??????
			String path = "C:\\Users\\bitcamp\\git\\GodLife\\GodLife\\src\\main\\webapp\\resources\\images\\uploadFiles\\";
			//???
			//String path = "C:\\Users\\?????????\\git\\GodLife\\GodLife\\src\\main\\webapp\\resources\\images\\uploadFiles\\";
			File file = new File(path+deleteChallengeThumbnail.getChallengeThumbnailImg());
			
			if(file.exists()) {
				if(file.delete()) {
					System.out.println("?????? ?????? ??????");
				}else {
					System.out.println("?????? ?????? ??????");
				}
			}else {
				System.out.println("????????? ???????????? ????????????.");
			}
			
			map = challengeService.deleteChallenge(challenge.getChallengeNo());
			
			List<String> userList = (List<String>)map.get("challengeJoinList");
			
			//???????????? ???????????? ???????????? ????????? ?????? ??????
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
			
			System.out.println("?????? ?????????");
			
			return "redirect:/challenge/listChallenge";
			
		}
		
		
		
		int challengeJoinPoint = challengeService.deleteChallengeJoin(joinChallenger);
		
		
		point.setUserEmail(user.getUserEmail());
		
		point.setUseStatus("1");
		
		point.setPoint(challengeJoinPoint);
		
		point.setUseDetail("3");
		
		map.put("user", user);
		
		map.put("point", point);
		
		pointService.addPointPurchase(map);
		
		map.put("challengeNo", challenge.getChallengeNo());
		
		challenge = challengeService.getChallenge(map);
		
		
		return "redirect:/challenge/getChallenge?challengeNo="+challenge.getChallengeNo();                    
	}                                 
	        
	@RequestMapping(value="addChallengePick",method=RequestMethod.POST)
	public String addChallengePick(JoinChallenger joinChallenger,
								   @ModelAttribute Challenge challenge,
								   HttpSession session,
								   Map<String,Object> map,
								   Model model) {
		
		User user = (User)session.getAttribute("user");
		
		
		joinChallenger.setEmail(user.getUserEmail());
		joinChallenger.setChallengeNo(challenge.getChallengeNo());
		joinChallenger.setStatus("1");
		challengeService.addChallengeJoin(joinChallenger);
		
		map.put("user",user);
		map.put("challengeNo", challenge.getChallengeNo());
		
		
		challenge = challengeService.getChallenge(map);
		
		
		model.addAttribute("challenge",challenge);
		
		return "redirect:/challenge/getChallenge?challengeNo="+challenge.getChallengeNo();
	}
	
	
	@RequestMapping("listChallengeJoinCertiImg")
	public String listchallengeJoincertiImg(@ModelAttribute Challenge challenge,
										  @ModelAttribute User user,
										  HttpSession session,
										  Model model,
										  Map<String,Object> map) throws Exception {
		
		String opt;
		if(user.getUserEmail() == null) {
			user = (User)session.getAttribute("user");
			opt = "my";
		}else {
			user = userService.getUser(user.getUserEmail());
			opt = "thirdParty";
		}
		
		
		map.put("challengeNo", challenge.getChallengeNo());
		map.put("user", user);
		challenge = challengeService.getChallenge(map);
		
		System.out.println("userEmail : "+map.get("user"));
		
		JoinChallenger joinChallenger = challengeService.getChallengeJoiner(map);

		map.put("email", user.getUserEmail());
		List<CertiImg> certiImgList = challengeService.getChallengeJoinCertiImgList(map);
		
		
		Vector<CertiImg> certiImgs = new Vector(challenge.getTotalCertiCount());
		
		System.out.println("certiImgs capacity "+certiImgs.capacity());
		System.out.println("certiImgList size "+certiImgList.size());
		
		System.out.println("Challenge CertiDate :"+challenge.getCertiDate());
		int j=0;
		certi:for(int i=0; i<certiImgs.capacity(); i++) { 
			
			if(j>=certiImgList.size()) { 
				CertiImg certiImg = new CertiImg();
				certiImg.setCertiImg("temp.jpg");
				certiImg.setCertiDate(challenge.getCertiDate().get(i));
				certiImgs.add(certiImg);
				j++;
			}else {
				
				if(challenge.getCertiDate().get(i).equals(
						certiImgList.get(j).getCertiImgRegDate()+"")) {
					certiImgList.get(j).setCertiDate(challenge.getCertiDate().get(i));
					certiImgs.add(certiImgList.get(j));
					System.out.println("????????????");
					j++;
				}else {// ????????? ????????? ???????????? ????????? ????????? certiImgList?????? ????????? ???????????????.
						// ?????? ????????? ????????? ???????????????+coupon+challenge.getCertiDate(?????? ????????????)
					
					for(int a=0; a<certiImgList.size(); a++) {
						
						if(certiImgList.get(a).getCertiImg().contains(
								challenge.getCertiDate().get(i))) {
							certiImgList.get(a).setCertiDate(challenge.getCertiDate().get(i));
							certiImgs.add(certiImgList.get(j));
							j++;
							continue certi;
						}
						
					}
					
					CertiImg certiImg = new CertiImg();
					certiImg.setCertiImg("temp.jpg");
					certiImg.setCertiDate(challenge.getCertiDate().get(i));
					certiImgs.add(certiImg);
					System.out.println("???????????? ????????? ?????? ??????????????? ??????");
				}
				
			}
		}
		
		System.out.println("certiImgs "+certiImgs);
		model.addAttribute("challenge",challenge);
		model.addAttribute("certiImgs",certiImgs);
		model.addAttribute("joinChallenger",joinChallenger);
		model.addAttribute("opt",opt);
		
		return "forward:/challenge/listChallengeJoinCertiImg.jsp";
	}
	
	
	
	@RequestMapping(value="addChallengeCertiImg",method=RequestMethod.GET)
	public String addChallengeCertiImg(@ModelAttribute Challenge challenge,
									   Model model) {
		
		model.addAttribute("challenge",challenge);
		return "forward:/challenge/addChallengeCertiImg.jsp";	
	}
	
	@RequestMapping(value="addChallengeCertiImg",method=RequestMethod.POST)
	public String addChallengeCertiImg(CertiImg certiImg,
									   @ModelAttribute Challenge challenge,
									   MultipartHttpServletRequest mtfRequest,
									   HttpSession session,
									   @RequestParam(defaultValue = "general") String status,
									   MyBadge myBadge) throws Exception {
		
		User user = (User)session.getAttribute("user");
		//??????
		String path = "C:\\Users\\bitcamp\\git\\GodLife\\GodLife\\src\\main\\webapp\\resources\\images\\uploadFiles\\";
		//???
		//String path = "C:\\Users\\?????????\\git\\GodLife\\GodLife\\src\\main\\webapp\\resources\\images\\uploadFiles\\";
		MultipartFile mf = mtfRequest.getFile("certiImgFile");
		
		String originFileName = mf.getOriginalFilename();
		
		certiImg.setChallengeNo(challenge.getChallengeNo());
		certiImg.setUser(user);
		certiImg.setCertiImg(System.currentTimeMillis()+originFileName);
		
		if(status.equals("coupon")) {
			certiImg.setStatus("1");
		}else {
			certiImg.setStatus("0");
		}
		
		
		mf.transferTo(new File(path+certiImg.getCertiImg()));
		
		challengeService.addChallengeCertiImg(certiImg);
		
		myBadge.setBadgeNo(10003);
		myBadge.setUserEmail(user.getUserEmail());
	    myBadgeService.updateBadgeMyActCount(myBadge);
		
		return "redirect:/challenge/listChallengeJoinCertiImg?challengeNo="+challenge.getChallengeNo();
	}
	
	
	@RequestMapping(value="listChallengeJoinUser",method=RequestMethod.GET)
	public String listChallengeJoinUser(@ModelAttribute Challenge challenge,
										Model model) {
		
		
		
		List<JoinChallenger> joinChallengerList = 
				challengeService.getChallengeJoinerList(challenge.getChallengeNo());
		model.addAttribute("joinChallengerList",joinChallengerList);
		return "forward:/challenge/listChallengeJoinUser.jsp";
	}
	
	@RequestMapping(value="listChallengeCertiImg",method=RequestMethod.GET)
	public String listChallengeCertiImg(@RequestParam(required = false) String certiImgOpt,
										@ModelAttribute Search search,
										HttpSession session,
										Map<String,Object> map,
										Model model) {
		
		User user = (User)session.getAttribute("user");
		
		if(user == null) {
			user = new User();
			user.setUserEmail("noLogin");
		}
		
		if(search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		
		search.setPageSize(pageSize);
		
		map.put("certiImgOpt",certiImgOpt);
		map.put("search", search);
		map.put("user", user);
		
		map = challengeService.getChallengeCertiImgList(map);
		
		List<CertiImg> certiImgList = (List<CertiImg>)map.get("certiImgList");
		
		for(CertiImg certiImg : certiImgList) {
			System.out.println(certiImg);
		}
		
		model.addAttribute(certiImgList);
		model.addAttribute("page",search.getCurrentPage());
		model.addAttribute("certiImgOpt",certiImgOpt);
		model.addAttribute("user",user);
		
		return "/challenge/listCertiImg.jsp";
	}
	
	
	@RequestMapping(value="addChallengeReward",method=RequestMethod.POST)
	public String addChallengeReward(@ModelAttribute JoinChallenger joinChallenger,
									 Map<String,Object> map,
									 Point point,
									 HttpSession session,
									 MyBadge myBadge) throws Exception {
		
		
		User user = (User)session.getAttribute("user");
		
		System.out.println("JoinChallenger : "+joinChallenger);
		
		map.put("user", user);
		map.put("challengeNo", joinChallenger.getChallengeNo());
		
		Challenge challenge = challengeService.getChallenge(map);
		
		if(joinChallenger.getChallengePercent() == 100) {
			myBadge.setBadgeNo(10002);
			myBadge.setUserEmail(user.getUserEmail());
		    myBadgeService.updateBadgeMyActCount(myBadge);
		    
		}
	
	 	if(challenge.getChallengeCategName().trim().equals("??????")) {
	    	myBadge.setBadgeNo(10005);
			myBadge.setUserEmail(user.getUserEmail());
		    myBadgeService.updateBadgeMyActCount(myBadge);
	    }else if(challenge.getChallengeCategName().trim().equals("?????????")) {
	    	myBadge.setBadgeNo(10008);
			myBadge.setUserEmail(user.getUserEmail());
		    myBadgeService.updateBadgeMyActCount(myBadge);
	    }else if(challenge.getChallengeCategName().trim().equals("??????")) {
	    	myBadge.setBadgeNo(10007);
			myBadge.setUserEmail(user.getUserEmail());
		    myBadgeService.updateBadgeMyActCount(myBadge);
	    }else if(challenge.getChallengeCategName().trim().equals("??????")) {
	    	myBadge.setBadgeNo(10009);
			myBadge.setUserEmail(user.getUserEmail());
		    myBadgeService.updateBadgeMyActCount(myBadge);
	    }else if(challenge.getChallengeCategName().trim().equals("??????")) {
	    	myBadge.setBadgeNo(10006);
			myBadge.setUserEmail(user.getUserEmail());
		    myBadgeService.updateBadgeMyActCount(myBadge);
	    }
	 	
	 	myBadge.setBadgeNo(10004);
		myBadge.setUserEmail(user.getUserEmail());
	    myBadgeService.updateBadgeMyActCount(myBadge);
	 	
		point.setUserEmail(user.getUserEmail());
		
		point.setUseStatus("1");
		
		point.setPoint(joinChallenger.getChallengeReward());
		
		point.setUseDetail("4");
		
		map.put("user", user);
		
		map.put("point", point);
		
		pointService.addPointPurchase(map);
		
		joinChallenger.setEmail(user.getUserEmail());
		
		challengeService.updateChallengerewardFlag(joinChallenger);
		
		
		
		return "redirect:/challenge/getChallenge?challengeNo="+joinChallenger.getChallengeNo();
	}
	
	
	
	///////////Scheduled////////////////////
	
	@Scheduled(cron = "0 0 0 * * *")
	public void updateChallengeStart() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int date = cal.get(Calendar.DATE);
		
		String monthString = ""+month;
		
		
		if(monthString.length() != 2) {
			monthString = 0+monthString;
		}
		
		String nowDate = year+"-"+monthString+"-"+date;
		
		map.put("nowDate", nowDate);
		map.put("status", "1");
		
		challengeService.updateChallengeStatus(map);
				
	}
	
	@Scheduled(cron = "0 0 23 * * *")
	public void updateChallengeEnd() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		int month = cal.get(Calendar.MONTH)+1;
		int date = cal.get(Calendar.DATE);
		
		String monthString = ""+month;
		
		
		if(monthString.length() != 2) {
			monthString = 0+monthString;
		}
		
		String nowDate = year+"-"+monthString+"-"+date;
		
		map.put("nowDate", nowDate);
		map.put("status", "2");
			
		challengeService.updateChallengeStatus(map);
	}
	
	@RequestMapping(value="getChallengeCertiImg", method=RequestMethod.GET)
	   public String getChallengeCertiImg(@RequestParam int certiImgNo
	                              ,Model model,
	                              HttpSession session,
	                              Map<String,Object> map) {
	      User user = (User)session.getAttribute("user");
	      map.put("user", user);
	      map.put("certiImgNo", certiImgNo);
	      CertiImg certiImg = challengeService.getChallengeCertiImg(map);
	      System.out.println("??????????????? ?????? ??????");
	      System.out.println(certiImg);
	      
	      model.addAttribute("certiImg", certiImg);
	      model.addAttribute("user", user);
	      return "forward:/challenge/getChallengeCertiImg.jsp";
	   }
	
	@RequestMapping(value="deleteChallengeCertiImg", method=RequestMethod.POST) // ??????????????? ?????????????????? ??????
	public String deleteChallengeCertiImg(@ModelAttribute CertiImg certiImg,
										  @RequestParam String userEmail,
										  Model model,
										  Map<String,Object> map,
										  MyBadge myBadge) throws Exception {
		
		myBadge.setBadgeNo(10003);
		myBadge.setUserEmail(userEmail);
		myBadgeService.updateBadgeMyActCountMinus(myBadge);
		
		int certiImgNo = certiImg.getCertiImgNo();
		System.out.println("@@certiImgNo : "+certiImgNo);
		map.put("certiImgNo", certiImgNo);
		challengeService.deleteChallengeCertiImg(map);
		System.out.println("??????");
		
		return "redirect:/";
	}

}
