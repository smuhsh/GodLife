package com.godLife.io.service.point.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import com.godLife.io.service.domain.Point;
import com.godLife.io.service.domain.User;
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
	public void addPointPurchaseProduct(Map<String,Object> map) throws Exception{
		pointDao.addPointPurchaseProduct(map);
	}
	
	@Override
	public void addPointPurchase(Map<String,Object> map) throws Exception{
		pointDao.addPointPurchase(map);
	}

	@Override
	public Map<String,Object> getPointPurchaseList(Search search,User user) throws Exception {
		System.out.println("getPointPurchaseList user : "+user);
		
		int totalCount = pointDao.getTotalCount(user);
		
		System.out.println("getPointPurchaseList totalCount : "+totalCount);
		Map<String, Object> map = pointDao.getPointPurchaseList(search, user);
		map.put("totalCount", new Integer(totalCount));

		return map;
	}

	@Override
	public Map<String, Object> getPointPurchaseVoucherList(Search search, User user) throws Exception {
		System.out.println("getPointPurchaseVoucherList user : "+user);
		int totalCount = pointDao.getVoucherTotalCount(user);
		System.out.println("getPointPurchaseVoucherList totalCount : "+totalCount);
		Map<String, Object> map = pointDao.getPointPurchaseVoucherList(search, user);
		map.put("totalCount", new Integer(totalCount));

		return map;
	}

	@Override
	public Map<String, Object> getPointPurchaseDonationList(Search search, User user) throws Exception {
		System.out.println("getPointPurchaseDonationList user : "+user);
		
		int totalCount = pointDao.getDonationTotalCount( search, user);
		
		System.out.println("getPointPurchaseDonationList totalCount : "+totalCount);
		Map<String, Object> map = pointDao.getPointPurchaseDonationList(search, user);
		map.put("totalCount", new Integer(totalCount));

		return map;
	}

	@Override
	public Map<String, Object> getPointPurchaseDonationRank(Point point) throws Exception {
		Map<String, Object> map = pointDao.getPointPurchaseDonationRank(point);
		return map;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
