package com.godLife.io.web.badge;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.godLife.io.common.Page;
import com.godLife.io.common.Search;
import com.godLife.io.service.badge.BadgeService;
import com.godLife.io.service.domain.Badge;





@RestController
//jason data를 요구하는 애들만 처리할 용도
@RequestMapping("/badge/*")
public class BadgeRestController {

	@Value("#{commonProperties['pageUnit']}")
	//@Value("#{commonProperties['pageUnit'] ?: 3}")
	int pageUnit;

	@Value("#{commonProperties['pageSize']}")
	//@Value("#{commonProperties['pageSize'] ?: 2}")
	int pageSize;

	@Autowired
	@Qualifier("badgeServiceImpl") //package service.product.impl;를 wiring
	BadgeService badgeService;

	//배지 상세 조회
	@RequestMapping( value="json/getBadge/{badgeNo}", method=RequestMethod.GET )
	public Badge getBadge( @PathVariable int badgeNo ) throws Exception {
		
		System.out.println("/badge/getBadge : GET");
		
		//Business Logic
		Badge badge= badgeService.getBadge(badgeNo);
	
		return badge;
	}



	//배지 전체 목록
	@RequestMapping( value="json/getBadgeList" )
	public Map getBadgeList( @RequestBody Search search , HttpServletRequest request) throws Exception{
	
		System.out.println("/badge/json/getBadgeList : GET / POST");
		
		if(search.getCurrentPage() ==0 ){
			search.setCurrentPage(1);
		}
		search.setPageSize(pageSize);
		
		// Business logic 수행
		Map<String , Object> map = badgeService.getBadgeList(search);
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		System.out.println(resultPage);
		
		// Model 과 View 연결
		map.put("list", map.get("list"));
		map.put("resultPage", resultPage);
		map.put("search", search);

		return map;
	}



}

