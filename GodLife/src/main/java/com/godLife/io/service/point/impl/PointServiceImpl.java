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
	public void addPointPurchaseProduct(Point point) throws Exception{
		pointDao.addPointPurchaseProduct(point);
	}
	
	@Override
	public void addPointPurchase(Point point) throws Exception{
		pointDao.addPointPurchase(point);
	}

	@Override
	public Map<String, Object> getPointPurchaseList(Search search, String userEmail) throws Exception {
		
		int totalCount = pointDao.getTotalCount(search);

		Map<String, Object> map = pointDao.getPointPurchaseList(search, userEmail);
		map.put("totalCount", new Integer(totalCount));

		return map;
	}

	@Override
	public Map<String, Object> getPointPurchaseVoucherList(Search search, String userEmail) throws Exception {
		
		int totalCount = pointDao.getTotalCount(search);

		Map<String, Object> map = pointDao.getPointPurchaseVoucherList(search, userEmail);
		map.put("totalCount", new Integer(totalCount));

		return map;
	}

	@Override
	public Map<String, Object> getPointPurchaseDonationList(Search search, String userEmail) throws Exception {
		
		int totalCount = pointDao.getTotalCount(search);

		Map<String, Object> map = pointDao.getPointPurchaseDonationList(search, userEmail);
		map.put("totalCount", new Integer(totalCount));

		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
