package com.godLife.io.service.point.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import com.godLife.io.service.domain.Point;
import com.godLife.io.service.point.PointDao;
import com.godLife.io.service.point.PointService;
import com.godLife.io.common.Search;

@Service("pointServiceImpl")
public class PointServiceImpl implements PointService { 
	
	@Autowired
	@Qualifier("pointDaoImpl")
	private PointDao pointDao;
	
	public void setPointDao(PointDao pointDao) {
		this.pointDao = pointDao;
	}
	
	///Constructor
	public PointServiceImpl() {
		System.out.println(this.getClass());
	}
	
	
	@Override
	public void addPurchase(Point point) throws Exception{
		pointDao.addPurchase(point);
	}
	
	@Override
	public Map<String, Object> getPurchaseList(Search search, String userEmail) throws Exception {

		int totalCount = pointDao.getTotalCount(search);

		Map<String, Object> map = pointDao.getPurchaseList(search, userEmail);
		map.put("totalCount", new Integer(totalCount));

		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
