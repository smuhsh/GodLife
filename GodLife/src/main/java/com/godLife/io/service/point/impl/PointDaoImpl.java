package com.godLife.io.service.point.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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
	public void addPointPurchaseProduct(Map<String,Object> map) throws Exception {
		User user = (User)map.get("user");
		Point point = (Point)map.get("point");
		
		System.out.println("point 확인: "+ point);
		
		String useStatus=point.getUseStatus();
		System.out.println("useStatus : "+useStatus);
		
		String useDetail=point.getUseDetail();
		System.out.println("useDetail : "+useDetail);
		
		int usePoint = point.getPoint();
		System.out.println("usePoint : "+usePoint);	
		
		int totalPoint = user.getTotalPoint();
		System.out.println("totalPoint : "+totalPoint);
		
		int productNo = point.getProductNo();
		System.out.println("productNo : "+productNo);
		
		
		if (useStatus.equals("1")) {
			int sumPoint = totalPoint + usePoint;

			System.out.println(" 계산 : " + sumPoint);
			user.setTotalPoint(sumPoint);

			System.out.println("map : "+map);
			System.out.println("point : "+ point);
			sqlSession.insert("PointMapper.addPointPurchase", point);
			userService.updateUserTotalPoint(user);

		} else if (useStatus.equals("2")) {
			int sumPoint = totalPoint - usePoint;

			System.out.println(" 계산 : " + sumPoint);

			user.setTotalPoint(sumPoint);
			userService.updateUserTotalPoint(user);
			if (useDetail.equals("8")) {

				if (productNo == 10000) {

					int redCoupon = user.getRedCouponCount() + 1;
					System.out.println("redCoupon : " + redCoupon);
					user.setRedCardCount(redCoupon);
					
					
					userService.updateUserRedCouponCount(user);

				} else if (productNo == 10001) {

					int certiCoupon = user.getCertiCouponCount() + 1;
					System.out.println("certiCoupon : " + certiCoupon);
					user.setCertiCouponCount(certiCoupon);
					userService.updateUserCertiCouponCount(user);
				}

				System.out.println("point : "+ point);
				sqlSession.insert("PointMapper.addPointPurchase", point);

			} else if (useDetail.equals("9")) {
				if (productNo >= 10002 || productNo <= 10006) {
					Random rnd = new Random();
					String voucherUniqueNo = new String();
					StringBuffer buf = new StringBuffer();

					for (int i = 0; i < 20; i++) {

						// rnd.nextBoolean() 는 랜덤으로 true, false 를 리턴. true일 시 랜덤 한 소문자를, false 일 시 랜덤 한
						// 숫자를 StringBuffer 에 append 한다.

						if (rnd.nextBoolean()) {

							buf.append((char) ((int) (rnd.nextInt(26)) + 97));
							voucherUniqueNo = buf.toString();
							point.setVoucherUniqueNo(voucherUniqueNo);

						} else {

							buf.append((rnd.nextInt(10)));
							voucherUniqueNo = buf.toString();
							point.setVoucherUniqueNo(voucherUniqueNo);
						}
						break;
					}
				}
			}
		}
		System.out.println("point : "+ point);
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
		System.out.println("@@@@dao Search : "+search);
		map.put("search", search);
		List<Point> list = sqlSession.selectList("PointMapper.getPointPurchaseDonationList", map);
		map.put("list", list);
		
		return map;
	}
	
	@Override
	public Map<String,Object> getPointPurchaseDonationRank(Point point) throws Exception{
		Map<String, Object> map = new HashMap<String, Object>();
		List<Point> list = sqlSession.selectList("PointMapper.getPointPurchaseDonationRank", map);
		System.out.println("@@@List : "+ list);
		map.put("list", list);
		return map;
	}
	
	@Override
	public int getTotalCount(User user) {
		System.out.println("daoimpl getTotalCount User : "+user);
		return sqlSession.selectOne("PointMapper.getTotalCount", user);
	}
	
	@Override
	public int getVoucherTotalCount(User user) {
		System.out.println("daoimpl getVoucherTotalCount User : "+user);
		return sqlSession.selectOne("PointMapper.getVoucherTotalCount", user);
	}
	
	@Override
	public int getDonationTotalCount(Search search,User user) {
		Map<String, Object> map = new HashMap<String, Object>();
		String userEmail=user.getUserEmail();
		map.put("endRowNum",  search.getEndRowNum()+"" );
		map.put("startRowNum",  search.getStartRowNum()+"" );
		map.put("userEmail",userEmail);
		System.out.println("@@@@dao Search : "+search);
		map.put("search", search);
		return sqlSession.selectOne("PointMapper.getDonationTotalCount", map);
	}
}
