package com.godLife.io.web.point;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.godLife.io.common.Page;
import com.godLife.io.common.Search;
import com.godLife.io.service.domain.Point;
import com.godLife.io.service.domain.User;
import com.godLife.io.service.point.PointService;


//==>  point RestController
@RestController
@RequestMapping("/pointRest/*")
public class PointRestController {

	/// Field
	@Autowired
	@Qualifier("pointServiceImpl")
	private PointService pointService;

	public PointRestController() {
		System.out.println(this.getClass());
	}

	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;

	@RequestMapping(value = "/pointRest/getPointPurchaseDonationRank", method = RequestMethod.GET)
	public Map<String, Object> getPointPurchaseDonationRank(Point point) throws Exception {

		System.out.println("/pointRest/getPointPurchaseDonationRank : GET");

		return pointService.getPointPurchaseDonationRank(point);
	}
	
	//coolSms api 사용
	@GetMapping(value = "sendPointVoucher") // 테스트완료 
	@ResponseBody
	public String sendPointVoucher( @ModelAttribute("point") Point point,HttpSession session,Map<String, Object> map) throws Exception { // 휴대폰 문자보내기
		
		User user = (User)session.getAttribute("user");
		map.put("user", user);
		map.put("point", point);
		pointService.sendPointVoucher(map);
		
		return "forward:/point/getPointPurchaseVoucherList";
	}
	


}