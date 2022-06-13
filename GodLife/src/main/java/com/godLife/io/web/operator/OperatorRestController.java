package com.godLife.io.web.operator;

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
import org.springframework.web.bind.annotation.RestController;

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


//==>RestController
@RestController
@RequestMapping("/operator/*")
public class OperatorRestController {
	
	///Field
	@Autowired
	@Qualifier("operatorServiceImpl")
	private OperatorService operatorService;
	//setter Method 구현 않음
		
	public OperatorRestController(){
		System.out.println(this.getClass());
	}
	
	@RequestMapping( value="json/getOperator/{noticeFaqNo}", method=RequestMethod.GET )
	public OperatorNoticeFaqs getOperatorNoticeFaqs( @PathVariable int noticeFaqNo ) throws Exception{
		
		System.out.println("/operator/json/getOperatorNoticeFaqs : GET");
		
		//Business Logic
		return operatorService.getOperatorNoticeFaqs(noticeFaqNo);
	}

}