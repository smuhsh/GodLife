package com.godLife.io.web.point;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.godLife.io.common.Page;
import com.godLife.io.common.Search;
import com.godLife.io.service.domain.Point;
import com.godLife.io.service.domain.Product;
import com.godLife.io.service.domain.User;
import com.godLife.io.service.point.PointService;
import com.godLife.io.service.product.ProductService;
import com.godLife.io.service.user.UserService;

@Controller
@RequestMapping("/point/*")
public class PointController {

	/// Field
	@Autowired
	@Qualifier("pointServiceImpl")
	private PointService pointService;

	@Autowired
	@Qualifier("productServiceImpl")
	private ProductService productService;

	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	public PointController() {
		System.out.println(this.getClass());
	}

	@Value("#{commonProperties['pageUnit']}")
	// @Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	// @Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;

	@RequestMapping(value = "addPointPurchaseProduct", method = RequestMethod.POST)
	public String addPointPurchaseProdcut(Map<String, Object> map, @ModelAttribute("point") Point point, HttpSession session) throws Exception {

		System.out.println("/point/addPointPurchaseProduct : POST");
		System.out.println("#### 받은 point "+point);
		int productNo=point.getProductNo();
		Product product = productService.getProductPoint(productNo);
		point.setPoint(product.getProductPrice());
		point.setCash(product.getProductPrice());
		System.out.println("#### getPorudct 후 point "+point);
		User user = (User)session.getAttribute("user");
		

		map.put("user", user);
		System.out.println("@@@@@@@@user : "+user);
		map.put("point", point);
		
		pointService.addPointPurchaseProduct(map);

		return "forward:/point/getPointPurchaseList";
	}
	
	@RequestMapping(value = "addPointDonationView", method = RequestMethod.GET)
	public String addPurchaseView(Point point,Model model) throws Exception {

		System.out.println("/purchase/addPurchaseView : GET");

		Map<String, Object> map = pointService.getPointPurchaseDonationRank(point);
		
		model.addAttribute("list", map.get("list"));
		return "forward:/point/addPointDonationView.jsp";
	}
	
	@RequestMapping(value = "addPointPurchaseDonation", method = RequestMethod.POST)
	public String addPointPurchaseDonation(Map<String, Object> map, @ModelAttribute("point") Point point, HttpSession session)
			throws Exception {

		System.out.println("/point/addPointPurchaseDonation : POST");

		User user = (User) session.getAttribute("user");
		map.put("user", user);
		System.out.println("@@@@@@@@user : "+user);
		map.put("point", point);
		System.out.println(map);

		pointService.addPointPurchase(map);
		
		return "forward:/point/getPointPurchaseDonationList";
	}
	
	
	@RequestMapping(value = "getPointPurchaseList")
	public String getPointPurchaseList(@ModelAttribute("search") Search search, Model model, HttpServletRequest request)
			throws Exception {

		System.out.println("/point/getPointPurchaseList : GET / POST");
	
		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		User user = (User) request.getSession().getAttribute("user");
		String userEmail = user.getUserEmail();
		System.out.println("session userEmail : " + userEmail);

		Map<String, Object> map = pointService.getPointPurchaseList(search, user);

		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		System.out.println(resultPage);

		List<Object> list = (List<Object>) map.get("list");
		System.out.println(list);

		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		System.out.println("model :" + model);


		return "forward:/point/listPointPurchase.jsp";
	}

	@RequestMapping(value = "getPointPurchaseVoucherList")
	public String getPointPurchaseVoucherList(@ModelAttribute("search") Search search, Model model,
			HttpSession session) throws Exception {

		System.out.println("/point/getPointPurchaseVoucherList : GET / POST");

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		System.out.println("@@@@Search "+search);
		User user = (User) session.getAttribute("user");
		String userEmail = user.getUserEmail();
		System.out.println("session userEmail : " + userEmail);

		Map<String, Object> map = pointService.getPointPurchaseVoucherList(search, user);

		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		System.out.println(resultPage);

		List<Object> list = (List<Object>) map.get("list");
		System.out.println(list);

		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		System.out.println("model :" + model);

		return "forward:/point/listPointPurchaseVoucher.jsp";
	}

	@RequestMapping(value = "getPointPurchaseDonationList")
	public String getPointPurchaseDonationList(@ModelAttribute("search") Search search, Model model,
			HttpServletRequest request) throws Exception {

		System.out.println("/point/getPointPurchaseDonationList : GET / POST");

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		System.out.println("@@@@Search "+search);
		User user = (User) request.getSession().getAttribute("user");
		String userEmail = user.getUserEmail();
		System.out.println("session userEmail : " + userEmail);

		Map<String, Object> map = pointService.getPointPurchaseDonationList(search, user);

		Page resultPage = new Page(search.getCurrentPage(), ((Integer) map.get("totalCount")).intValue(), pageUnit,
				pageSize);
		System.out.println(resultPage);

		List<Object> list = (List<Object>) map.get("list");
		System.out.println(list);

		model.addAttribute("list", map.get("list"));
		model.addAttribute("resultPage", resultPage);
		model.addAttribute("search", search);
		System.out.println("model :" + model);

		return "forward:/point/listPointPurchaseDonation.jsp";
	}
	
	//coolSms api 사용
		@GetMapping(value = "sendPointVoucher") // 테스트완료 
		@ResponseBody
		public Map<String, Object> sendPointVoucher( @ModelAttribute("point") Point point,HttpSession session,Map<String, Object> map) throws Exception { // 휴대폰 문자보내기
			
			User user = (User)session.getAttribute("user");
			map.put("user", user);
			map.put("point", point);
			pointService.sendPointVoucher(map);
			
			return map;
		}
}
