package com.godLife.io.service.mybadge.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.godLife.io.common.Search;
import com.godLife.io.service.domain.Badge;
import com.godLife.io.service.domain.MyBadge;
import com.godLife.io.service.domain.User;
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
	public MyBadge getBadgeMy(int myBadgeNo, User user) throws Exception {
		return myBadgeDao.getBadgeMy(myBadgeNo, user);
	}
///////////////////////////	
	public Map<String , Object> getBadgeMyList(Search search, User user, Badge badge) throws Exception {
		System.out.println("getBadgeMyList user : "+ user);
		//int totalCount = myBadgeDao.getTotalCount(search);
	
		Map<String, Object> map = myBadgeDao.getBadgeMyList(search, user, badge);
		
		
		return map;
	}

	public void updateBadgeMyActCount(MyBadge myBadge) throws Exception {
		System.out.println("mybadge serviceimp myBadge : "+myBadge);
		
		myBadgeDao.updateBadgeMyActCount(myBadge);
	}

	public void updateBadgeMyActCountMinus(MyBadge myBadge) throws Exception {
		System.out.println("mybadge serviceimp myBadge : "+myBadge);
		
		myBadgeDao.updateBadgeMyActCountMinus(myBadge);
	}
	

	
	
	
	
	

}