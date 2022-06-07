package com.godLife.io.service.badge.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.godLife.io.common.Search;
import com.godLife.io.service.badge.BadgeDao;
import com.godLife.io.service.domain.Badge;



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
	
	public List<Badge> getBadgeList(Search search) throws Exception {
		return sqlSession.selectList("BadgeMapper.getBadgeList", search);
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