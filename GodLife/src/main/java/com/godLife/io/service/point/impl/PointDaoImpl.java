package com.godLife.io.service.point.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.godLife.io.service.domain.Point;
import com.godLife.io.service.domain.User;
import com.godLife.io.service.point.PointDao;
import com.godLife.io.service.user.UserService;
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
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	public PointDaoImpl() {
		System.out.println(this.getClass());
	}

	@Override
	public void addPointPurchaseProduct(Point point) throws Exception {
		sqlSession.insert("PointMapper.addPointPurchaseProduct", point);
	}

	@Override
	public void addPointPurchase(Map<String,Object> map) throws Exception {
		User user = (User)map.get("user");
		Point point = (Point)map.get("point");
		
		System.out.println("point 확인: "+ point);
		
		String useStatus=point.getUseStatus();
		System.out.println("useStatus : "+useStatus);
		
		int usePoint = point.getPoint();
		System.out.println("usePoint : "+usePoint);	
		
		int totalPoint = user.getTotalPoint();
		System.out.println("totalPoint : "+totalPoint);
		
		
		if(useStatus.equals("2")) {
			
		int sumPoint = totalPoint - usePoint;
			
		System.out.println(" 계산 : " + sumPoint);
		user.setTotalPoint(sumPoint);
		
		System.out.println("map : "+map);
		System.out.println("point : "+ point);
		sqlSession.insert("PointMapper.addPointPurchase", point);
		userService.updateUserTotalPoint(user);
		
		}else if(useStatus.equals("1")) {
				
			int sumPoint = totalPoint + usePoint;

			System.out.println(" 계산 : " + sumPoint);
				
			user.setTotalPoint(sumPoint);
			userService.updateUserTotalPoint(user);
			System.out.println("map : "+map);
			System.out.println("point : "+ point);
			sqlSession.insert("PointMapper.addPointPurchase", point);
		} else {
			System.out.println("Error");
		}
		
	}

	@Override
	public Map<String, Object> getPointPurchaseList(Search search, User user) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String userEmail=user.getUserEmail();
		map.put("endRowNum",  search.getEndRowNum()+"" );
		map.put("startRowNum",  search.getStartRowNum()+"" );
		map.put("userEmail",userEmail);
		
		List<Point> list = sqlSession.selectList("PointMapper.getPointPurchaseList", map);
		map.put("list", list);
		
		return map;
	}

	@Override
	public Map<String, Object> getPointPurchaseVoucherList(Search search, User user) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String userEmail=user.getUserEmail();
		map.put("endRowNum",  search.getEndRowNum()+"" );
		map.put("startRowNum",  search.getStartRowNum()+"" );
		map.put("userEmail",userEmail);
		
		List<Point> list = sqlSession.selectList("PointMapper.getPointPurchaseVoucherList", map);
		map.put("list", list);
		
		return map;
	}

	@Override
	public Map<String, Object> getPointPurchaseDonationList(Search search, User user) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String userEmail=user.getUserEmail();
		map.put("endRowNum",  search.getEndRowNum()+"" );
		map.put("startRowNum",  search.getStartRowNum()+"" );
		map.put("userEmail",userEmail);
		
		List<Point> list = sqlSession.selectList("PointMapper.getPointPurchaseDonationList", map);
		map.put("list", list);
		
		return map;
	}

	@Override
	public int getTotalCount(User user) {
		return sqlSession.selectOne("PointMapper.getTotalCount", user);
	}
	
	@Override
	public int getVoucherTotalCount(User user) {
		return sqlSession.selectOne("PointMapper.getVoucherTotalCount", user);
	}
	
	@Override
	public int getDonationTotalCount(User user) {
		return sqlSession.selectOne("PointMapper.getDonationTotalCount", user);
	}
}
