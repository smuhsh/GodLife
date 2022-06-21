package com.godLife.io.web.operator;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.spel.ast.Operator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.godLife.io.common.Page;
import com.godLife.io.common.Search;
import com.godLife.io.service.domain.OperatorEvents;
import com.godLife.io.service.domain.OperatorNoticeFaqs;
import com.godLife.io.service.domain.OperatorJoinEvent;
import com.godLife.io.service.domain.OperatorReward;
import com.godLife.io.service.domain.Point;
import com.godLife.io.service.domain.User;
import com.godLife.io.service.operator.OperatorService;
import com.godLife.io.service.point.PointService;

@Controller
@RequestMapping("/operator/*")
public class OperatorController {
	
	///Field
	@Autowired
	@Qualifier("operatorServiceImpl")
	private OperatorService operatorService;

	@Autowired
	@Qualifier("pointServiceImpl")
	private PointService pointService;
	//setter Method 구현 않음
		
	public OperatorController(){
		System.out.println(this.getClass());
	}
	
	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;
	
	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;
	
	//OperatorEvents
	@RequestMapping(value="addOperatorEvents", method=RequestMethod.GET)
	public String addOperatorEvents() throws Exception {
		
		System.out.println("/operator/addOperatorEvents : GET");
		
		return "redirect:/operator/addOperatorEvents.jsp";
	}
	
	@RequestMapping(value="addOperatorEvents", method=RequestMethod.POST)
	public String addOperatorEvents(@ModelAttribute("operator") OperatorEvents operatorEvents) throws Exception {
		
		System.out.println("/operator/addOperatorEvents : POST");
		//Business Logic
		operatorService.addOperatorEvents(operatorEvents);
		
		return "redirect:/operator/addOperatorEvents.jsp";
	}
	
	@RequestMapping(value="getOperatorEvents", method=RequestMethod.GET)
	public String getOperatorEvents( @RequestParam("eventNo") int eventNo , Model model ) throws Exception {
		
		System.out.println("/operator/getOperatorEvents : GET");
		//Business Logic
		OperatorEvents operatorEvents = operatorService.getOperatorEvents(eventNo);
		// Connect Model and View 
		model.addAttribute("operatorEvents", operatorEvents);
		
		return "forward:/operator/getOperatorEvents.jsp";
	}
	
	@RequestMapping(value="updateOperatorEvents", method=RequestMethod.GET)
	public String updateOperatorEvents( @RequestParam("eventNo") int eventNo , Model model ) throws Exception{

		System.out.println("/update/updateOperatorEvents : GET");
		//Business Logic
		OperatorEvents operatorEvents = operatorService.getOperatorEvents(eventNo);
		// Connect Model and View 
		model.addAttribute("operatorEvents", operatorEvents);
		return "forward:/operator/updateOperatorEvents.jsp";
	}
	
	@RequestMapping(value="updateOperatorEvents", method=RequestMethod.POST)
	public String updateOperatorEvents( @ModelAttribute("operator") OperatorEvents operatorEvents , Model model , HttpSession session) throws Exception{
		
		System.out.println("/update/updateOperatorEvents : POST");
		//Business Logic
		operatorService.updateOperatorEvents(operatorEvents);
		
		Integer sessionId=((OperatorEvents)session.getAttribute("operator")).getEventNo();
		if(sessionId.equals(operatorEvents.getEventNo())){
			session.setAttribute("operator", operatorEvents);
		}
		return "redirect:/operator/getOperatorEvents?EventNo="+operatorEvents.getEventNo();
	}
	
	@RequestMapping("/listOperatorEvents")
	public String listOperatorEvents( @ModelAttribute("search") Search search, Model model , HttpServletRequest request) throws Exception{
	//public String listOperatorEvents(@ModelAttribute("search") Search search, Model model, @RequestParam(value="menu", required=false) String menu) throws Exception{
		System.out.println("/listOperatorEvents");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String, Object> map = operatorService.getOperatorEventsList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		List<Object> list = (List<Object>) map.get("list");
		System.out.println("@@@@@@@@@list :"+list);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);

		return "forward:/operator/listOperatorEvents.jsp";
		
	}
	
	//OperatorNotice
	@RequestMapping(value="addOperatorNotice", method=RequestMethod.GET)
	public String addOperatorNotice() throws Exception {		
		System.out.println("/operator/addOperatorNotice : GET");		
		return "redirect:/operator/addOperatorNotice.jsp";
	}
	
	//STATUS: 0,1(공지,FAQ) NOTICE_MUST: 0,1(DEFAULT, MUST) FAQ_TAG: 0~2(인증,결제,상금) 
	@RequestMapping(value="addOperatorNotice", method=RequestMethod.POST)
	//public String listOperatorEvents(@ModelAttribute("search") Search search, Model model, @RequestParam(value="menu", required=false) String menu) throws Exception{	
	public String addOperatorNotice(@ModelAttribute("operatorNotice") OperatorNoticeFaqs operatorNotice) throws Exception {		
		System.out.println("/operator/addOperatorNotice : POST");
		//Business Logic
		operatorService.addOperatorNotice(operatorNotice);		
		return "redirect:/operator/addOperatorNotice.jsp";
	}
	
//	@RequestMapping(value="getOperatorNoticeFaqs", method=RequestMethod.GET)
//	public String getOperatorNoticeFaqs( @RequestParam("eventNo") int eventNo , Model model ) throws Exception {
//		
//		System.out.println("/operator/getOperatorNoticeFaqs : GET");
//		//Business Logic
//		OperatorNoticeFaqs operatorNoticeFaqs = operatorService.getOperatorNoticeFaqs(eventNo);
//		// Connect Model and View 
//		model.addAttribute("operatorNoticeFaqs", operatorNoticeFaqs);
//		
//		return "forward:/operator/getOperatorNoticeFaqs.jsp";
//	}
	@RequestMapping(value="getOperatorNotice", method=RequestMethod.GET)
	public String getOperatorNotice( @RequestParam("noticeFaqNo") int noticeFaqNo , Model model ) throws Exception {		
		System.out.println("/operator/getOperatorNotice : GET");
		//Business Logic
		OperatorNoticeFaqs operatorNotice = operatorService.getOperatorNotice(noticeFaqNo);
		// Connect Model and View 
		model.addAttribute("operatorNotice", operatorNotice);		
		return "forward:/operator/getOperatorNotice.jsp";
	}
	
	@RequestMapping(value="updateOperatorNotice", method=RequestMethod.GET)
	public String updateOperatorNotice( @RequestParam("noticeFaqNo") int noticeFaqNo , Model model ) throws Exception{
		
		System.out.println("/update/updateOperatorNotice : GET");
		//Business Logic
		OperatorNoticeFaqs operatorNotice = operatorService.getOperatorNotice(noticeFaqNo);
		// Connect Model and View 
		model.addAttribute("operatorNotice", operatorNotice);
		return "forward:/operator/updateOperatorNotice.jsp";
	}
	
	@RequestMapping(value="updateOperatorNotice", method=RequestMethod.POST)
	public String updateOperatorNotice( @ModelAttribute("operator") OperatorNoticeFaqs operatorNotice , Model model , HttpSession session) throws Exception{
		
		System.out.println("/update/updateOperatorNotice : POST");
		//Business Logic
		operatorService.updateOperatorNotice(operatorNotice);
		
		Integer sessionId=((OperatorNoticeFaqs)session.getAttribute("operator")).getNoticeFaqNo();
		if(sessionId.equals(operatorNotice.getNoticeFaqNo())){
			session.setAttribute("operator", operatorNotice);
		}
		return "redirect:/operator/getOperatorNotice?NoticeFaqNo="+operatorNotice.getNoticeFaqNo();
	}
	
//	@RequestMapping("/listOperatorNoticeFaqs")
//	public String listOperatorNoticeFaqs(@ModelAttribute("search") Search search, Model model, @RequestParam(value="menu", required=false) String menu) throws Exception{
//		System.out.println("/listOperatorNoticeFaqs");
//		
//		if(search.getCurrentPage() ==0 ){
//			search.setCurrentPage(1);
//		}
//		search.setPageSize(pageSize);
//		
//		Map<String, Object> map = operatorService.getOperatorNoticeFaqsList(search);
//		
//		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
//		System.out.println(resultPage);
//		
//		model.addAttribute("list", map.get("list"));
//		model.addAttribute("resultPage", resultPage);
//		model.addAttribute("search", search);
//		
//		if(menu!=null && menu.equals("search")) {
//			return "forward:/operator/listOperatorNoticeFaqs?menu=search";
//		}
//		else 
//			return "forward:/operator/listOperatorNoticeFaqs.jsp";
//		
//	}
	@RequestMapping( value="listOperatorNotice" )
	public String listOperatorNotice( @ModelAttribute("search") Search search, OperatorNoticeFaqs operatorNotice , Model model , HttpSession session) throws Exception{
		
		System.out.println("/operator/listOperatorNotice : GET / POST");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);		
		User user = (User) session.getAttribute("user");
		// Business logic 수행
		//Map<String , Object> map=operatorService.getOperatorNoticeFaqsList(search);
		Map<String , Object> map=operatorService.getOperatorNoticeList(search, user, operatorNotice);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		List<Object> list = (List<Object>) map.get("list");
		System.out.println("@@@@@@@@@list :"+list);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		System.out.println("model :" + model);
		
		return "forward:/operator/listOperatorNotice.jsp";
	}

	//OperatorFaqs
	@RequestMapping(value="addOperatorFaqs", method=RequestMethod.GET)
	public String addOperatorFaqs() throws Exception {		
		System.out.println("/operator/addOperatorFaqs : GET");		
		return "redirect:/operator/addOperatorFaqs.jsp";
	}
	
	@RequestMapping(value="addOperatorFaqs", method=RequestMethod.POST)
	public String addOperatorFaqs(@ModelAttribute("operatorFaqs") OperatorNoticeFaqs operatorFaqs) throws Exception {		
		System.out.println("/operator/addOperatorFaqs : POST");
		//Business Logic
		operatorService.addOperatorFaqs(operatorFaqs);		
		return "redirect:/operator/addOperatorFaqs.jsp";
	}
	
//	@RequestMapping(value="getOperatorFaqs", method=RequestMethod.GET)
//	//public String getOperatorFaqs( @RequestParam("noticeFaqNo") int noticeFaqNo , Model model ) throws Exception {		
//	public String getOperatorFaqs( @RequestParam("title") String title , Model model ) throws Exception {		
//		System.out.println("/operator/getOperatorFaqs : GET");
//		//Business Logic
//		//OperatorNoticeFaqs operatorFaqs = operatorService.getOperatorFaqs(noticeFaqNo);
//		OperatorNoticeFaqs operatorFaqs = operatorService.getOperatorFaqs(title);
//		// Connect Model and View 
//		model.addAttribute("operatorFaqs", operatorFaqs);		
//		return "forward:/operator/getOperatorFaqs.jsp";
//	}
	
	@RequestMapping(value="updateOperatorFaqs", method=RequestMethod.GET)
	public String updateOperatorFaqs( @RequestParam("title") String title , Model model ) throws Exception{
		
		System.out.println("/update/updateOperatorFaqs : GET");
		//Business Logic
//		OperatorNoticeFaqs operatorFaqs = operatorService.getOperatorFaqs(title);
		// Connect Model and View 
//		model.addAttribute("operatorFaqs", operatorFaqs);
		return "forward:/operator/updateOperatorFaqs.jsp";
	}
	
	@RequestMapping(value="updateOperatorFaqs", method=RequestMethod.POST)
	public String updateOperatorFaqs( @ModelAttribute("operator") OperatorNoticeFaqs operatorFaqs , Model model , HttpSession session) throws Exception{
		
		System.out.println("/update/updateOperatorFaqs : POST");
		//Business Logic
		operatorService.updateOperatorFaqs(operatorFaqs);
		
		Integer sessionId=((OperatorNoticeFaqs)session.getAttribute("operator")).getNoticeFaqNo();
		if(sessionId.equals(operatorFaqs.getNoticeFaqNo())){
			session.setAttribute("operator", operatorFaqs);
		}
		return "redirect:/operator/getOperatorFaqs?NoticeFaqNo="+operatorFaqs.getNoticeFaqNo();
	}
	
	@RequestMapping( value="listOperatorFaqs" )
	public String listOperatorFaqs( @ModelAttribute("search") Search search, OperatorNoticeFaqs operatorFaqs , Model model , HttpSession session) throws Exception{
		
		System.out.println("/operator/listOperatorFaqs : GET / POST");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);		
		User user = (User) session.getAttribute("user");
		// Business logic 수행
		Map<String , Object> map=operatorService.getOperatorFaqsList(search, user, operatorFaqs);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		List<Object> list = (List<Object>) map.get("list");
		System.out.println("@@@@@@@@@list :"+list);
		
		// Model 과 View 연결
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		System.out.println("model :" + model);
		
		return "forward:/operator/listOperatorFaqs.jsp";
	}
	
	
	
	
	
	
	
	
	
	
	//OperatorJoinEvent
	@RequestMapping(value="addOperatorJoinEvent", method=RequestMethod.GET)
	public String addOperatorJoinEvent() throws Exception {
		
		System.out.println("/operator/addOperatorJoinEvent : GET");
		
		return "redirect:/operator/addOperatorJoinEvent.jsp";
	}
	
	//2022-06-20 shhwang addOperatorJoinEvent 를 addOperatorJoinDayEvent addOperatorJoinRoullEvent 로 변경함
//	@RequestMapping(value="addOperatorJoinEvent", method=RequestMethod.POST)
//	public String addOperatorJoinEvent(@ModelAttribute("operator") OperatorJoinEvent operatorJoinEvent, HttpSession session,Map<String,Object> map) throws Exception {
//		
//		System.out.println("/operator/addOperatorJoinEvent : POST");
//		//Business Logic
//		
//		User user = (User)session.getAttribute("user");
//		
//		operatorJoinEvent.setUserEmail(user.getUserEmail());
//		
//		Point point = new Point();
//		
//		if(operatorJoinEvent.getEventNo()==2) {
//		point.setUseStatus("2");
//		point.setPoint(1000);
//		point.setUseDetail("5");
//		
//		map.put("user", user);
//		map.put("point", point);
//		
//		pointService.addPointPurchase(map);
//		}
//		
//		System.out.println("oper@@@@@@@@@ : "+ operatorJoinEvent);
//		
//		operatorService.addOperatorJoinEvent(operatorJoinEvent);
//		point.setUserEmail(user.getUserEmail());
//		point.setUseStatus("1");
//		point.setPoint(operatorJoinEvent.getRewardPoint());
//		point.setUseDetail("6");
//		
//		map.put("user", user);
//		map.put("point", point);
//		pointService.addPointPurchase(map);
//		
//		
//		return "forward:/operator/addOperatorJoinEvent.jsp";
//	}
	//addOperatorJoinDayEvent 매일출석1 보상1,5 (100,10000) 14,28 일차에 보상5
	@RequestMapping(value="addOperatorJoinDayEvent", method=RequestMethod.GET)
	public String addOperatorJoinDayEvent(@ModelAttribute("operator") OperatorJoinEvent operatorJoinEvent, HttpSession session,Map<String,Object> map) throws Exception {
		
		System.out.println("/operator/addOperatorJoinDayEvent : GET");
		//Business Logic
		
		User user = (User)session.getAttribute("user");
		
		operatorJoinEvent.setUserEmail(user.getUserEmail());
		
		Point point = new Point();
		
		if(operatorJoinEvent.getEventNo()==1) {
			point.setUseStatus("1");
			point.setPoint(100);
			point.setUseDetail("6");
			
			map.put("user", user);
			map.put("point", point);
			
			pointService.addPointPurchase(map);
		}
		
		System.out.println("oper@@@@@@@@@ : "+ operatorJoinEvent);
		
		operatorService.addOperatorJoinDayEvent(operatorJoinEvent);
		point.setUserEmail(user.getUserEmail());
		point.setUseStatus("1");
		point.setPoint(operatorJoinEvent.getRewardPoint());
		point.setUseDetail("5");
		
		map.put("user", user);
		map.put("point", point);
		pointService.addPointPurchase(map);
		
		
		return "forward:/operator/addOperatorJoinDayEvent.jsp";
	}
	
	//addOperatorJoinRoullEvent 룰렛2 보상2~5 (100,1000,3000,5000,10000)
	@RequestMapping(value="addOperatorJoinRoullEvent", method=RequestMethod.GET)
	public String addOperatorJoinEvent(@ModelAttribute("operator") OperatorJoinEvent operatorJoinEvent, HttpSession session,Map<String,Object> map) throws Exception {
		
		System.out.println("/operator/addOperatorJoinRoullEvent : GET");
		//Business Logic
		
		User user = (User)session.getAttribute("user");
		
		operatorJoinEvent.setUserEmail(user.getUserEmail());
		
		Point point = new Point();
		
		if(operatorJoinEvent.getEventNo()==2) {
			point.setUseStatus("2");
			point.setPoint(1000);
			point.setUseDetail("5");
			
			map.put("user", user);
			map.put("point", point);
			
			pointService.addPointPurchase(map);
		}
		
		System.out.println("oper@@@@@@@@@ : "+ operatorJoinEvent);
		
		operatorService.addOperatorJoinRoullEvent(operatorJoinEvent);
		point.setUserEmail(user.getUserEmail());
		point.setUseStatus("1");
		point.setPoint(operatorJoinEvent.getRewardPoint());
		point.setUseDetail("6");
		
		map.put("user", user);
		map.put("point", point);
		pointService.addPointPurchase(map);
		
		
		return "forward:/operator/addOperatorJoinRoullEvent.jsp";
	}
	
//	@RequestMapping(value="getOperatorJoinEvent", method=RequestMethod.GET)
//	public String getOperatorJoinEvent( @RequestParam("eventNo") int joinEventNo , Model model ) throws Exception {
//		
//		System.out.println("/operator/getOperatorJoinEvent : GET");
//		//Business Logic
//		OperatorJoinEvent operatorJoinEvent = operatorService.getOperatorJoinEvent(joinEventNo);
//		// Connect Model and View 
//		model.addAttribute("operatorJoinEvent", operatorJoinEvent);		
//		return "forward:/operator/getOperatorJoinEvent.jsp";
//	}
	@RequestMapping(value="getOperatorJoinDayEvent", method=RequestMethod.GET)
	public String getOperatorJoinDayEvent( @RequestParam("eventNo") int eventNo,@RequestParam("userEmail") String userEmail , Model model  ) throws Exception {
		
		System.out.println("/operator/getOperatorJoinDayEvent : GET");
		//Business Logic
		
		User user = new User();
		user.setUserEmail(userEmail);
		
		if(user.getUserEmail().equals(null)) {
			System.out.println("이메일 빈칸");
			OperatorJoinEvent operatorJoinEvent = operatorService.getOperatorJoinDayEvent(eventNo);
			model.addAttribute("operatorJoinEvent", operatorJoinEvent);	
			return "forward:/operator/getOperatorJoinDayEvent.jsp";
			
		}else if(!user.getUserEmail().equals(null)) {
			System.out.println("로그인");
		OperatorJoinEvent operatorJoinEvent = new OperatorJoinEvent();
		operatorJoinEvent.setEventNo(eventNo);
		operatorJoinEvent.setUserEmail(user.getUserEmail());
		
		Map<String,Object>map=operatorService.getOperatorJoinDayEventUser(operatorJoinEvent);
		System.out.println("맵 operatorJoinEvent"+map.get("operatorJoinEvent"));
		System.out.println("맵 totalCount"+map.get("totalCount"));
	
		
		model.addAttribute("operatorJoinEvent", map.get("operatorJoinEvent"));
		model.addAttribute("totalCount", (Integer)map.get("totalCount"));
		model.addAttribute("totalCountPlus", (Integer)map.get("totalCount")+1);
		model.addAttribute("user", user);
		System.out.println("model: "+model);
		return "forward:/operator/getOperatorJoinDayEvent.jsp";
		}
		
		return null;
		

	}
	@RequestMapping(value="getOperatorJoinRoullEvent", method=RequestMethod.GET)
	public String getOperatorJoinRoullEvent( @RequestParam("eventNo") int eventNo , Model model ) throws Exception {
		
		System.out.println("/operator/getOperatorJoinRoullEvent : GET");
		//Business Logic
		OperatorJoinEvent operatorJoinEvent = operatorService.getOperatorJoinRoullEvent(eventNo);
		// Connect Model and View 
		model.addAttribute("operatorJoinEvent", operatorJoinEvent);		
		return "forward:/operator/getOperatorJoinRoullEvent.jsp";
	}

	//2022-06-20 shhwang 불필요
//	@RequestMapping(value="updateOperatorJoinEvent", method=RequestMethod.GET)
//	public String updateOperatorJoinEvent( @RequestParam("eventNo") int joinEventNo , Model model ) throws Exception{
//		
//		System.out.println("/update/updateOperatorJoinEvent : GET");
//		//Business Logic
//		OperatorJoinEvent operatorJoinEvent = operatorService.getOperatorJoinEvent(joinEventNo);
//		// Connect Model and View 
//		model.addAttribute("operatorJoinEvent", operatorJoinEvent);
//		return "forward:/operator/updateOperatorJoinEvent.jsp";
//	}
	
//	@RequestMapping(value="updateOperatorJoinEvent", method=RequestMethod.POST)
//	public String updateOperatorJoinEvent( @ModelAttribute("operator") OperatorJoinEvent operatorJoinEvent , Model model , HttpSession session) throws Exception{
//		
//		System.out.println("/update/updateOperatorJoinEvent : POST");
//		//Business Logic
//		operatorService.updateOperatorJoinEvent(operatorJoinEvent);
//	
//		Integer sessionId=((OperatorJoinEvent)session.getAttribute("operator")).getJoinEventNo();
//		if(sessionId.equals(operatorJoinEvent.getJoinEventNo())){
//			session.setAttribute("operator", operatorJoinEvent);
//		}
//		return "forward:/operator/getOperatorJoinEvent";
//	}
	
	@RequestMapping("/listOperatorJoinEvent")
	public String listOperatorJoinEvent(@ModelAttribute("search") Search search, Model model, @RequestParam(value="status", required=false) String status) throws Exception{
		System.out.println("/listOperatorJoinEvent");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String, Object> map = operatorService.getOperatorJoinEventList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		return "forward:/operator/listOperatorJoinEvent.jsp?menu=0";
		
	}
	

	
}