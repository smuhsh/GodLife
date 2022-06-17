package com.godLife.io.service.mybadge.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.godLife.io.common.Search;
import com.godLife.io.service.domain.MyBadge;
import com.godLife.io.service.domain.Point;
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

	public Map<String, Object> getBadgeMyList(Search search) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("endRowNum",  search.getEndRowNum()+"" );
		map.put("startRowNum",  search.getStartRowNum()+"" );
		System.out.println("@@@@dao Search : "+search);
		map.put("search", search);
		List<Point> list = sqlSession.selectList("MyBadgeMapper.getBadgeMyList", search);
		System.out.println("@@@daoImpl list1 : "+list);
		map.put("list", list);

		
		return map;
	}

	// 내 배지 활동 횟수량 증가
	public void updateBadgeMyActCount(MyBadge myBadge) throws Exception{
		sqlSession.update("MyBadgeMapper.updateBadgeMyActCount", myBadge);
	}
	
	
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("MyBadgeMapper.getTotalCount", search);
	}
	
}