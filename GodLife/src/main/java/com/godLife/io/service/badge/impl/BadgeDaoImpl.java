package com.godLife.io.service.badge.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.godLife.io.common.Search;
import com.godLife.io.service.badge.BadgeDao;
import com.godLife.io.service.domain.Badge;
import com.godLife.io.service.domain.MyBadge;



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
	public void addBadgeView(Badge badge) throws Exception {
		sqlSession.insert("ProductMapper.addBadgeView", badge);
	}
	public void addBadge(Badge badge) throws Exception {
		sqlSession.insert("ProductMapper.addBadge", badge);
	}
	public void addBadgeConfirm(Badge badge) throws Exception {
		sqlSession.insert("ProductMapper.addBadgeConfirm", badge);
	}
	public void addBadgeImg(Badge badge) throws Exception {
		sqlSession.insert("ProductMapper.addBadgeImg", badge);
	}
//////////////////////////////////////////////////////////////////////////////////
	public Badge getBadge(int badgeNo) throws Exception {
		return sqlSession.selectOne("ProductMapper.getBadge", badgeNo);
	}
	public MyBadge getBadgeMy(int myBadgeNo) throws Exception {
		return sqlSession.selectOne("ProductMapper.getBadgeMy", myBadgeNo);
	}
//////////////////////////////////////////////////////////////////////////////////
	public List<Badge> getBadgeList(Search search) throws Exception {
		return sqlSession.selectList("ProductMapper.getBadgeList", search);
	}
	public List<MyBadge> getBadgeMyList(Search search) throws Exception {
		return sqlSession.selectList("ProductMapper.getBadgeMyList", search);
	}
//////////////////////////////////////////////////////////////////////////////////
	public void updateBadgeImg(Badge badge) throws Exception {
		sqlSession.update("ProductMapper.updateBadgeImg", badge);
	}
	public void updateBadge(Badge badge) throws Exception {
		sqlSession.update("ProductMapper.updateBadge", badge);
	}
	public void updateBadgeConfirm(Badge badge) throws Exception {
		sqlSession.update("ProductMapper.updateBadgeConfirm", badge);
	}
//////////////////////////////////////////////////////////////////////////////////
	public void deleteBadge(int badgeNo) throws Exception {
		sqlSession.delete("ProductMapper.deleteBadge", badgeNo);
	}
	public void deleteBadgeConfirm(int badgeNo) throws Exception {
		sqlSession.delete("ProductMapper.deleteBadgeConfirm", badgeNo);
	}
	
//////////////////////////////////////////////////////////////////////////////////
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("ProductMapper.getTotalCount", search);
	}
	
}