package com.godLife.io.web.point;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.godLife.io.common.Page;
import com.godLife.io.common.Search;
import com.godLife.io.service.domain.Point;
import com.godLife.io.service.domain.User;
import com.godLife.io.service.point.PointService;


//==> ȸ������ RestController
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
	


}