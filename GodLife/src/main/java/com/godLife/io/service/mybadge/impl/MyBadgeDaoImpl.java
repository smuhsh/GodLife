package com.godLife.io.service.mybadge.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.godLife.io.common.Search;
import com.godLife.io.service.domain.MyBadge;
import com.godLife.io.service.mybadge.MyBadgeDao;



@Repository("myBadgeDaoImpl")
public class MyBadgeDaoImpl implements MyBadgeDao{
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public MyBadgeDaoImpl() {
		System.out.println(this.getClass());
	}
	
	///Method
	
	public MyBadge getBadgeMy(int myBadgeNo) throws Exception {
		return sqlSession.selectOne("MyBadgeMapper.getBadgeMy", myBadgeNo);
	}

	public List<MyBadge> getBadgeMyList(Search search) throws Exception {
		return sqlSession.selectList("MyBadgeMapper.getBadgeMyList", search);
	}

	// 내 배지 활동 횟수량 증가
	public void updateBadgeMyActCount(int actCount) throws Exception{
		sqlSession.update("MyBadgeMapper.updateBadgeMyActCount", actCount);
	}
	
	
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("MyBadgeMapper.getTotalCount", search);
	}
	
}