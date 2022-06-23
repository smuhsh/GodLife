package com.godLife.io.service.mybadge.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.godLife.io.common.Search;
import com.godLife.io.service.badge.BadgeService;
import com.godLife.io.service.domain.Badge;
import com.godLife.io.service.domain.MyBadge;
import com.godLife.io.service.domain.User;
import com.godLife.io.service.mybadge.MyBadgeDao;
import com.godLife.io.service.user.UserService;



@Repository("myBadgeDaoImpl")
public class MyBadgeDaoImpl implements MyBadgeDao{
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;

	@Autowired
	@Qualifier("badgeServiceImpl")
	private BadgeService badgeService;	
	
	
	///Constructor
	public MyBadgeDaoImpl() {
		System.out.println(this.getClass());
	}
	
	///Method
///////////////////////////////////////
	
	public MyBadge getBadgeMy(int myBadgeNo, User user) throws Exception {
		return sqlSession.selectOne("MyBadgeMapper.getBadgeMy", myBadgeNo);
	}
///////////////////////////////////////	
	public Map<String, Object> getBadgeMyList(Search search, User user, Badge badge) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String userEmail=user.getUserEmail();
		String badgeName=badge.getBadgeName();
		map.put("endRowNum",  search.getEndRowNum()+"" );
		map.put("startRowNum",  search.getStartRowNum()+"" );
		map.put("userEmail", userEmail);
		System.out.println("@@@@myBadgeDaoImpl Search : "+search);
		map.put("search", search);
		System.out.println("@@@@@ daoimpl map : "+map);
		List<MyBadge> list1 = sqlSession.selectList("MyBadgeMapper.getBadgeMyABList", map);
		System.out.println("@@@myBadgeDaoImpl list1 : "+list1);
		map.put("list1", list1);
		List<MyBadge> list2 = sqlSession.selectList("MyBadgeMapper.getBadgeMyIBList", map);
		System.out.println("@@@myBadgeDaoImpl list2 : "+list2);
		map.put("list2", list2);
		return map;
	}
///////////////////////////////////////	
	// 내 배지 활동 횟수량 증가
	public void updateBadgeMyActCount(MyBadge myBadge) throws Exception{
		sqlSession.update("MyBadgeMapper.updateBadgeMyActCount", myBadge);
	}
	
	// 인증이미지 삭제시 활동 횟수량 감소
	public void updateBadgeMyActCountMinus(MyBadge myBadge) throws Exception{
		sqlSession.update("MyBadgeMapper.updateBadgeMyActCountMinus", myBadge);
	}
	
	
	
	public int getTotalCount(Search search, User user, Badge badge) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String userEmail=user.getUserEmail();
		String badgeImg=badge.getBadgeImg();
		
		map.put("endRowNum",  search.getEndRowNum()+"" );
		map.put("startRowNum",  search.getStartRowNum()+"" );
		map.put("userEmail",userEmail);
		map.put("badgeImg",badgeImg);
		System.out.println("@@@@dao Search : "+search);
		map.put("search", search);
		return sqlSession.selectOne("MyBadgeMapper.getTotalCount", map);

	}
	
}