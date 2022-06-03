package com.godLife.io.service.point.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.godLife.io.service.domain.Point;
import com.godLife.io.service.point.PointDao;
import com.godLife.io.common.Search;

@Repository("pointDaoImpl")
public class PointDaoImpl implements PointDao {

	// Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;

	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}

	public PointDaoImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addPointPurchaseProduct(Point point) throws Exception {
		sqlSession.insert("PointMapper.addPointPurchaseProduct", point);
	}

	@Override
	public void addPointPurchase(Point point) throws Exception {
		sqlSession.insert("PointMapper.addPointPurchase", point);
	}

	@Override
	public Map<String, Object> getPointPurchaseList(Search search, String userEmail) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("endRowNum",  search.getEndRowNum()+"" );
		map.put("startRowNum",  search.getStartRowNum()+"" );
		map.put("userEmail",userEmail);
		
		List<Point> list = sqlSession.selectList("PointMapper.getPointPurchaseList", map);
		map.put("list", list);
		
		return map;
	}

	@Override
	public Map<String, Object> getPointPurchaseVoucherList(Search search, String userEmail) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("endRowNum",  search.getEndRowNum()+"" );
		map.put("startRowNum",  search.getStartRowNum()+"" );
		map.put("userEmail",userEmail);
		
		List<Point> list = sqlSession.selectList("PointMapper.getPointPurchaseVoucherList", map);
		map.put("list", list);
		
		return map;
	}

	@Override
	public Map<String, Object> getPointPurchaseDonationList(Search search, String userEmail) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("endRowNum",  search.getEndRowNum()+"" );
		map.put("startRowNum",  search.getStartRowNum()+"" );
		map.put("userEmail",userEmail);
		
		List<Point> list = sqlSession.selectList("PointMapper.getPointPurchaseDonationList", map);
		map.put("list", list);
		
		return map;
	}

	@Override
	public int getTotalCount(Search search) {
		return sqlSession.selectOne("PointMapper.getTotalCount", search);
	}
}
