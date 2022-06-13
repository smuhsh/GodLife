package com.godLife.io.web.operator;

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
	public String listOperatorEvents(@ModelAttribute("search") Search search, Model model, @RequestParam(value="menu", required=false) String menu) throws Exception{
		System.out.println("/listOperatorEvents");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String, Object> map = operatorService.getOperatorEventsList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);

		if(menu!=null && menu.equals("manage")) {
			return "forward:/operator/listOperatorEvents.jsp?menu=manage";
		}
		else 
			return "forward:/operator/listOperatorEvents.jsp";
		
	}
	
	//OperatorNoticeFaqs
	@RequestMapping(value="addOperatorNoticeFaqs", method=RequestMethod.GET)
	public String addOperatorNoticeFaqs() throws Exception {
		
		System.out.println("/operator/addOperatorNoticeFaqs : GET");
		
		return "redirect:/operator/addOperatorNoticeFaqs.jsp";
	}
	
	//STATUS: 0,1(공지,FAQ) NOTICE_MUST: 0,1(DEFAULT, MUST) FAQ_TAG: 0~2(인증,결제,상금) 
	@RequestMapping(value="addOperatorNoticeFaqs", method=RequestMethod.POST)
	//public String listOperatorEvents(@ModelAttribute("search") Search search, Model model, @RequestParam(value="menu", required=false) String menu) throws Exception{	
	public String addOperatorNoticeFaqs(@ModelAttribute("operator") OperatorNoticeFaqs operatorNoticeFaqs) throws Exception {
		
		System.out.println("/operator/addOperatorNoticeFaqs : POST");
		//Business Logic
		operatorService.addOperatorNoticeFaqs(operatorNoticeFaqs);
		
		return "redirect:/operator/addOperatorNoticeFaqs.jsp";
	}
	
	@RequestMapping(value="getOperatorNoticeFaqs", method=RequestMethod.GET)
	public String getOperatorNoticeFaqs( @RequestParam("eventNo") int eventNo , Model model ) throws Exception {
		
		System.out.println("/operator/getOperatorNoticeFaqs : GET");
		//Business Logic
		OperatorNoticeFaqs operatorNoticeFaqs = operatorService.getOperatorNoticeFaqs(eventNo);
		// Connect Model and View 
		model.addAttribute("operatorNoticeFaqs", operatorNoticeFaqs);
		
		return "forward:/operator/getOperatorNoticeFaqs.jsp";
	}
	
	@RequestMapping(value="updateOperatorNoticeFaqs", method=RequestMethod.GET)
	public String updateOperatorNoticeFaqs( @RequestParam("eventNo") int eventNo , Model model ) throws Exception{
		
		System.out.println("/update/updateOperatorNoticeFaqs : GET");
		//Business Logic
		OperatorNoticeFaqs operatorNoticeFaqs = operatorService.getOperatorNoticeFaqs(eventNo);
		// Connect Model and View 
		model.addAttribute("operatorNoticeFaqs", operatorNoticeFaqs);
		return "forward:/operator/updateOperatorNoticeFaqs.jsp";
	}
	
	@RequestMapping(value="updateOperatorNoticeFaqs", method=RequestMethod.POST)
	public String updateOperatorNoticeFaqs( @ModelAttribute("operator") OperatorNoticeFaqs operatorNoticeFaqs , Model model , HttpSession session) throws Exception{
		
		System.out.println("/update/updateOperatorNoticeFaqs : POST");
		//Business Logic
		operatorService.updateOperatorNoticeFaqs(operatorNoticeFaqs);
		
		Integer sessionId=((OperatorNoticeFaqs)session.getAttribute("operator")).getNoticeFaqNo();
		if(sessionId.equals(operatorNoticeFaqs.getNoticeFaqNo())){
			session.setAttribute("operator", operatorNoticeFaqs);
		}
		return "redirect:/operator/getOperatorNoticeFaqs?EventNo="+operatorNoticeFaqs.getNoticeFaqNo();
	}
	
	@RequestMapping("/listOperatorNoticeFaqs")
	public String listOperatorNoticeFaqs(@ModelAttribute("search") Search search, Model model, @RequestParam(value="status", required=false) String status) throws Exception{
		System.out.println("/listOperatorNoticeFaqs");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		Map<String, Object> map = operatorService.getOperatorNoticeFaqsList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		
		if(status!=null && status.equals("0")) {
			return "forward:/operator/listOperatorNoticeFaqs.jsp?menu=0";
		}
		else 
			return "forward:/operator/listOperatorNoticeFaqs.jsp";
		
	}
	
	//OperatorJoinEvent
	@RequestMapping(value="addOperatorJoinEvent", method=RequestMethod.GET)
	public String addOperatorJoinEvent() throws Exception {
		
		System.out.println("/operator/addOperatorJoinEvent : GET");
		
		return "redirect:/operator/addOperatorJoinEvent.jsp";
	}
	
	@RequestMapping(value="addOperatorJoinEvent", method=RequestMethod.POST)
	public String addOperatorJoinEvent(@ModelAttribute("operator") OperatorJoinEvent operatorJoinEvent, HttpSession session) throws Exception {
		
		System.out.println("/operator/addOperatorJoinEvent : POST");
		//Business Logic
		
		User user = (User)session.getAttribute("user");
		
		operatorJoinEvent.setUserEmail(user.getUserEmail());
		
		Point point = new Point();
		
		if(operatorJoinEvent.getEventNo()==2) {
		point.setUserEmail(user.getUserEmail());
		point.setUseStatus("2");
		point.setPoint(1000);
		point.setUseDetail("5");
		
		pointService.addPointPurchase(point);
		}
		
		System.out.println("oper@@@@@@@@@ : "+ operatorJoinEvent);
		
		operatorService.addOperatorJoinEvent(operatorJoinEvent);
		point.setUserEmail(user.getUserEmail());
		point.setUseStatus("1");
		point.setPoint(operatorJoinEvent.getRewardPoint());
		point.setUseDetail("6");
		pointService.addPointPurchase(point);
		
		
		return "forward:/operator/addOperatorJoinEvent.jsp";
	}
	
	@RequestMapping(value="getOperatorJoinEvent", method=RequestMethod.GET)
	public String getOperatorJoinEvent( @RequestParam("eventNo") int joinEventNo , Model model ) throws Exception {
		
		System.out.println("/operator/getOperatorJoinEvent : GET");
		//Business Logic
		OperatorJoinEvent operatorJoinEvent = operatorService.getOperatorJoinEvent(joinEventNo);
		// Connect Model and View 

		
		model.addAttribute("operatorJoinEvent", operatorJoinEvent);
		
		return "forward:/operator/getOperatorJoinEvent.jsp";
	}
	
	@RequestMapping(value="updateOperatorJoinEvent", method=RequestMethod.GET)
	public String updateOperatorJoinEvent( @RequestParam("eventNo") int joinEventNo , Model model ) throws Exception{
		
		System.out.println("/update/updateOperatorJoinEvent : GET");
		//Business Logic
		OperatorJoinEvent operatorJoinEvent = operatorService.getOperatorJoinEvent(joinEventNo);
		// Connect Model and View 
		model.addAttribute("operatorJoinEvent", operatorJoinEvent);
		return "forward:/operator/updateOperatorJoinEvent.jsp";
	}
	
	@RequestMapping(value="updateOperatorJoinEvent", method=RequestMethod.POST)
	public String updateOperatorJoinEvent( @ModelAttribute("operator") OperatorJoinEvent operatorJoinEvent , Model model , HttpSession session) throws Exception{
		
		System.out.println("/update/updateOperatorJoinEvent : POST");
		//Business Logic
		operatorService.updateOperatorJoinEvent(operatorJoinEvent);
	
		Integer sessionId=((OperatorJoinEvent)session.getAttribute("operator")).getJoinEventNo();
		if(sessionId.equals(operatorJoinEvent.getJoinEventNo())){
			session.setAttribute("operator", operatorJoinEvent);
		}
		return "forward:/operator/getOperatorJoinEvent";
	}
	
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