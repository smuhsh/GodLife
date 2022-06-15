package com.godLife.io.service.badge.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.godLife.io.common.Search;
import com.godLife.io.service.badge.BadgeDao;
import com.godLife.io.service.badge.BadgeService;
import com.godLife.io.service.domain.Badge;





@Service("badgeServiceImpl")
public class BadgeServiceImpl implements BadgeService{
		
	///Field
	@Autowired
	@Qualifier("badgeDaoImpl")
	private BadgeDao badgeDao;
	public void setBadgeDao(BadgeDao badgeDao) {
		this.badgeDao = badgeDao;
	}
	
	///Constructor
	public BadgeServiceImpl() {
		System.out.println(this.getClass());
	}

	///Method
	public void addBadge(Badge badge) throws Exception {
		badgeDao.addBadge(badge);
	}
	
	public Badge getBadge(int badgeNo) throws Exception {
		return badgeDao.getBadge(badgeNo);
	}
	
	public Map<String , Object> getBadgeList(Search search) throws Exception {
		 badgeDao.getBadgeList(search);
		int totalCount = badgeDao.getTotalCount(search);
		
		Map<String, Object> map = badgeDao.getBadgeList(search);

		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	public void updateBadge(Badge badge) throws Exception {
		badgeDao.updateBadge(badge);
	}
	
	public void deleteBadge(Badge badgeNo) throws Exception {
		badgeDao.deleteBadge(badgeNo);
	}

	
//	public void addBadgeView(Badge badge) throws Exception {
//		badgeDao.addBadgeView(badge);
//	}
//	public void addBadgeConfirm(Badge badge) throws Exception {
//		badgeDao.addBadgeConfirm(badge);
//	}
//	public void addBadgeImg(Badge badge) throws Exception {
//		badgeDao.addBadgeImg(badge);
//	}
//////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////



//////////////////////////////////////////////////////////////	
//	public void updateBadgeImg(Badge badge) throws Exception {
//		badgeDao.updateBadgeImg(badge);
//	}

//	public void updateBadgeConfirm(Badge badge) throws Exception {
//		badgeDao.updateBadgeConfirm(badge);
//	}
	
//////////////////////////////////////////////////////////////
	

//	public void deleteBadgeConfirm(int badgeNo) throws Exception {
//		badgeDao.deleteBadgeConfirm(badgeNo);
//	}

}

