package com.godLife.io.web.point;

import java.util.List;
import java.util.Map;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.godLife.io.common.Page;
import com.godLife.io.common.Search;
import com.godLife.io.service.domain.Point;
import com.godLife.io.service.domain.User;
import com.godLife.io.service.product.ProductService;
import com.godLife.io.service.point.PointService;
import com.godLife.io.service.user.UserService;


@Controller
@RequestMapping("/point/*")
public class PointController {
	
	///Field
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
	public String addPointPurchaseProdcut(@RequestParam("totalPoint") int totalPoint, @RequestParam("userEmail") String userEmail,
			@ModelAttribute("point") Point point) throws Exception{
		
		System.out.println("/point/addPointPurchaseProduct : POST");
		
		User user = new User();
		user.setUserEmail(userEmail);
		
		point.setUserEmail(userEmail);
		String useStatus = point.getUseStatus();
		System.out.println("useStatus : "+useStatus);
		
		String useDetail = point.getUseDetail();
		System.out.println("useDetail : "+useDetail);
		
		int usePoint = point.getPoint();
		System.out.println("usePoint : "+usePoint);
		
		int productNo = point.getProductNo();
		System.out.println("productNo " +productNo);
		
		if(useStatus=="1") {
			int sumPoint = totalPoint + usePoint;
			
			System.out.println(" sumPoint : " + sumPoint);
			user.setTotalPoint(sumPoint);
			
			userService.updateUserTotalPoint(user);
			
			if(useDetail=="1") {
				
				System.out.println(point);
				pointService.addPointPurchaseProduct(point);
				
				return "forward:/point/addPointPurchasePointProduct.jsp";
			}
			
		} else if(useStatus=="2") {
			int sumPoint = totalPoint - usePoint;

			System.out.println(" sumPoint : " + sumPoint);
			
			user.setTotalPoint(sumPoint);
			userService.updateUserTotalPoint(user);
			if(useDetail=="8") {
			
				if(productNo==10000) {
				
					int redCoupon = user.getRedCouponCount()+1;
					System.out.println("redCoupon : "+redCoupon);
					user.setRedCardCount(redCoupon);
				
					userService.updateUserRedCouponCount(user);
				
				} else if(productNo==10001) {
				
					int certiCoupon = user.getCertiCouponCount()+1;
					System.out.println("certiCoupon : "+certiCoupon);
					user.setCertiCouponCount(certiCoupon);
					userService.updateUserCertiCouponCount(user);
				}
				
				System.out.println(point);
				pointService.addPointPurchaseProduct(point);
				return "forward:/product/getProductCouponList.jsp";
				
			}else if(useDetail=="9") {
				if(productNo>=10002 || productNo <= 10006) {
				Random rnd =new Random();
				
				StringBuffer buf =new StringBuffer();

					for(int i=0;i<20;i++){

						// rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를, false 일 시 랜덤 한 숫자를 StringBuffer 에 append 한다.

						if(rnd.nextBoolean()){

							buf.append((char)((int)(rnd.nextInt(26))+97));
				        
							point.setVoucherUniqueNo(buf);
							
						}else{

							buf.append((rnd.nextInt(10)));
							point.setVoucherUniqueNo(buf);
						}
						break;
					}
				}
				
				System.out.println(point);
				pointService.addPointPurchaseProduct(point);
				return "forward:/product/getProductVoucherList.jsp";
			}
			
		}
		
		return "forward:/point/getPointPurchaseList.jsp";
	}
	
	@RequestMapping(value = "addPointPurchase", method = RequestMethod.POST)
	public void addPointPurchase(@RequestParam("totalPoint") int totalPoint, @RequestParam("userEmail") String userEmail,
			@ModelAttribute("point") Point point) throws Exception{
			
		System.out.println("/point/addPointPurchase : POST");
		
		User user = new User();
		user.setUserEmail(userEmail);
		
		point.setUserEmail(userEmail);
		String useStatus = point.getUseStatus();
		System.out.println("useStatus : "+useStatus);
		
		String useDetail = point.getUseDetail();
		System.out.println("useDetail : "+useDetail);
		
		int usePoint = point.getPoint();
		System.out.println("usePoint : "+usePoint);	
		
		if(useStatus=="1") {
			
			int sumPoint = totalPoint + usePoint;
			
			System.out.println(" sumPoint : " + sumPoint);
			user.setTotalPoint(sumPoint);
			
			userService.updateUserTotalPoint(user);
			
			System.out.println(point);
			pointService.addPointPurchase(point);
			
		}else if(useStatus=="2") {
			
			int sumPoint = totalPoint - usePoint;

			System.out.println(" sumPoint : " + sumPoint);
			
			user.setTotalPoint(sumPoint);
			userService.updateUserTotalPoint(user);
			
			System.out.println(point);
			pointService.addPointPurchase(point);
		}
		
		return ;
	}
	
	@RequestMapping(value = "addPointPurchaseDonation", method = RequestMethod.POST)
	public String addPointPurchaseDonation(@RequestParam("totalPoint") int totalPoint, @RequestParam("userEmail") String userEmail,
			@ModelAttribute("point") Point point) throws Exception{
			
		System.out.println("/point/addPointPurchaseDonation : POST");
		
		User user = new User();
		user.setUserEmail(userEmail);
		
		point.setUserEmail(userEmail);
		String useStatus = point.getUseStatus();
		System.out.println("useStatus : "+useStatus);
		
		String useDetail = point.getUseDetail();
		System.out.println("useDetail : "+useDetail);
		
		int usePoint = point.getPoint();
		System.out.println("usePoint : "+usePoint);	
		
		int sumPoint = totalPoint - usePoint;

		System.out.println(" sumPoint : " + sumPoint);
		
		user.setTotalPoint(sumPoint);
		userService.updateUserTotalPoint(user);
		
		System.out.println(point);
		pointService.addPointPurchase(point);
		
		return "forward:/point/listPointPurchaseDonation.jsp";
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

		Map<String, Object> map = pointService.getPointPurchaseList(search, userEmail);

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
	public String getPointPurchaseVoucherList(@ModelAttribute("search") Search search, Model model, HttpServletRequest request)
			throws Exception {

		System.out.println("/point/getPointPurchaseVoucherList : GET / POST");

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		User user = (User) request.getSession().getAttribute("user");
		String userEmail = "tndkdml@gmail.com";//user.getUserEmail();
		System.out.println("session userEmail : " + userEmail);

		Map<String, Object> map = pointService.getPointPurchaseVoucherList(search, userEmail);

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
	public String getPointPurchaseDonationList(@ModelAttribute("search") Search search, Model model, HttpServletRequest request)
			throws Exception {

		System.out.println("/point/getPointPurchaseDonationList : GET / POST");

		if (search.getCurrentPage() == 0) {
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);

		User user = (User) request.getSession().getAttribute("user");
		String userEmail = user.getUserEmail();
		System.out.println("session userEmail : " + userEmail);

		Map<String, Object> map = pointService.getPointPurchaseDonationList(search, userEmail);

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
}
