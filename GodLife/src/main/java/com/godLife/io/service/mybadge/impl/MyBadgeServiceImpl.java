package com.godLife.io.service.mybadge.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.godLife.io.common.Search;
import com.godLife.io.service.domain.Badge;
import com.godLife.io.service.domain.MyBadge;
import com.godLife.io.service.mybadge.MyBadgeDao;
import com.godLife.io.service.mybadge.MyBadgeService;



@Service("myBadgeServiceImpl")
public class MyBadgeServiceImpl implements MyBadgeService{
		
	///Field
	@Autowired
	@Qualifier("myBadgeDaoImpl")
	private MyBadgeDao myBadgeDao;
	public void setMyBadgeDao(MyBadgeDao myBadgeDao) {
		this.myBadgeDao = myBadgeDao;
	}
	
	///Constructor
	public MyBadgeServiceImpl() {
		System.out.println(this.getClass());
	}

	///Method
///////////////////////////	
	public MyBadge getBadgeMy(int myBadgeNo) throws Exception {
		return myBadgeDao.getBadgeMy(myBadgeNo);
	}
///////////////////////////	
	public Map<String , Object> getBadgeMyList(Search search) throws Exception {

		int totalCount = myBadgeDao.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	public void updateBadgeMyActCount(MyBadge myBadge) throws Exception {
		myBadgeDao.updateBadgeMyActCount(myBadge);
	}

	

	
	
	
	
	

}