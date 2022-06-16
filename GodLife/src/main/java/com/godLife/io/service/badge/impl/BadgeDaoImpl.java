package com.godLife.io.service.badge.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.godLife.io.common.Search;
import com.godLife.io.service.badge.BadgeDao;
import com.godLife.io.service.domain.Badge;
import com.godLife.io.service.domain.Point;



@Repository("badgeDaoImpl")
public class BadgeDaoImpl implements BadgeDao{
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public BadgeDaoImpl() {
		System.out.println(this.getClass());
	}
	
	///Method
	
	public void addBadge(Badge badge) throws Exception {
		sqlSession.insert("BadgeMapper.addBadge", badge);
	}
	
	public Badge getBadge(int badgeNo) throws Exception {
		return sqlSession.selectOne("BadgeMapper.getBadge", badgeNo);
	}
	
	public Map<String, Object> getBadgeList(Search search) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("endRowNum",  search.getEndRowNum()+"" );
		map.put("startRowNum",  search.getStartRowNum()+"" );
		System.out.println("@@@@dao Search : "+search);
		map.put("search", search);
		List<Point> list1 = sqlSession.selectList("BadgeMapper.getBadgeABList", search);
		System.out.println("@@@daoImpl list1 : "+list1);
		map.put("list1", list1);
		List<Point> list2 = sqlSession.selectList("BadgeMapper.getBadgeIBList", search);
		System.out.println("@@@daoImpl list2 : "+list2);
		map.put("list2", list2);
		return map;
	}
	
	public void updateBadge(Badge badge) throws Exception {
		sqlSession.update("BadgeMapper.updateBadge", badge);
	}


	
	public void deleteBadge(Badge badgeNo) throws Exception {
		sqlSession.delete("BadgeMapper.deleteBadge", badgeNo);
	}
	
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("BadgeMapper.getTotalCount", search);
	}
	
//	public void addBadgeView(Badge badge) throws Exception {
//		sqlSession.insert("BadgeMapper.addBadgeView", badge);
//	}
//	public void addBadgeConfirm(Badge badge) throws Exception {
//		sqlSession.insert("BadgeMapper.addBadgeConfirm", badge);
//	}
//	public void addBadgeImg(Badge badge) throws Exception {
//		sqlSession.insert("BadgeMapper.addBadgeImg", badge);
//	}
//////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////////////////////////////
//	public void updateBadgeImg(Badge badge) throws Exception {
//		sqlSession.update("BadgeMapper.updateBadgeImg", badge);
//	}

//	public void updateBadgeConfirm(Badge badge) throws Exception {
//		sqlSession.update("BadgeMapper.updateBadgeConfirm", badge);
//	}
//////////////////////////////////////////////////////////////////////////////////

//	public void deleteBadgeConfirm(int badgeNo) throws Exception {
//		sqlSession.delete("BadgeMapper.deleteBadgeConfirm", badgeNo);
//	}
	
//////////////////////////////////////////////////////////////////////////////////

	
}