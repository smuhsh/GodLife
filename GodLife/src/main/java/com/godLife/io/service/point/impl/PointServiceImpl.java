package com.godLife.io.service.point.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


import com.godLife.io.service.domain.Point;
import com.godLife.io.service.domain.User;
import com.godLife.io.service.point.PointDao;
import com.godLife.io.service.point.PointService;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

import com.godLife.io.common.Search;

@Service("pointServiceImpl")
public class PointServiceImpl implements PointService { 
	
	@Autowired
	@Qualifier("pointDaoImpl")
	private PointDao pointDao;
	
	public void setPointDao(PointDao pointDao) {
		this.pointDao = pointDao;
	}
	
	///Constructor
	public PointServiceImpl() {
		System.out.println(this.getClass());
	}
	
	
	@Override
	public void addPointPurchaseProduct(Map<String,Object> map) throws Exception{
		pointDao.addPointPurchaseProduct(map);
	}
	
	@Override
	public void addPointPurchase(Map<String,Object> map) throws Exception{
		System.out.println("@@@@pointServiceImp map :"+map);
		pointDao.addPointPurchase(map);
	}

	@Override
	public Map<String,Object> getPointPurchaseList(Search search,User user) throws Exception {
		System.out.println("getPointPurchaseList user : "+user);
		
		int totalCount = pointDao.getTotalCount(search, user);
		
		System.out.println("getPointPurchaseList totalCount : "+totalCount);
		Map<String, Object> map = pointDao.getPointPurchaseList(search, user);
		map.put("totalCount", new Integer(totalCount));

		return map;
	}
	
	@Override
	public Map<String, Object> getPointPurchasePointList(Search search, User user) throws Exception {
		System.out.println("getPointPurchasePointList user : "+user);
		int totalCount = pointDao.getPointTotalCount(search,user);
		System.out.println("getPointPurchaseVoucherList totalCount : "+totalCount);
		Map<String, Object> map = pointDao.getPointPurchasePointList(search, user);
		map.put("totalCount", new Integer(totalCount));

		return map;
	}

	@Override
	public Map<String, Object> getPointPurchaseVoucherList(Search search, User user) throws Exception {
		System.out.println("getPointPurchaseVoucherList user : "+user);
		int totalCount = pointDao.getVoucherTotalCount(search,user);
		System.out.println("getPointPurchaseVoucherList totalCount : "+totalCount);
		Map<String, Object> map = pointDao.getPointPurchaseVoucherList(search, user);
		map.put("totalCount", new Integer(totalCount));

		return map;
	}

	@Override
	public Map<String, Object> getPointPurchaseDonationList(Search search, User user) throws Exception {
		System.out.println("getPointPurchaseDonationList user : "+user);
		
		int totalCount = pointDao.getDonationTotalCount( search, user);
		
		System.out.println("getPointPurchaseDonationList totalCount : "+totalCount);
		Map<String, Object> map = pointDao.getPointPurchaseDonationList(search, user);
		map.put("totalCount", new Integer(totalCount));

		return map;
	}

	@Override
	public Map<String, Object> getPointPurchaseDonationRank(Point point) throws Exception {
		Map<String, Object> map = pointDao.getPointPurchaseDonationRank(point);
		return map;
	}
	
	public void sendPointVoucher(Map<String, Object> map) throws Exception {
		String api_key = "NCSOUIL3U4BKDNTU";
	    String api_secret = "PECGMMRB6KBESSNFX14HFI2NY7Q4VBEN";
	    Message coolsms = new Message(api_key, api_secret);
	    
	    User user = (User)map.get("user");
	    String phone = user.getPhone();
	    String nick = user.getNick();
	    Point point = (Point)map.get("point");
	    String productName = point.getProductName();
	    String voucherUnique = point.getVoucherUniqueNo();
	    Date regDate = point.getRegDate();
	    
	    
	    // 4 params(to, from, type, text) are mandatory. must be filled
	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", phone);    // 수신전화번호
	    params.put("from", "01026941237");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
	    params.put("type", "SMS");
	    params.put("text","구매자 : "+ nick+"\n상품권 명 : "+productName+"\n상품권 번호 : "+voucherUnique+"\n구매일자 : "+regDate); // 문자 내용 입력
	    params.put("app_version", "test app 1.2"); // application name and version

	    try {
	        JSONObject obj = (JSONObject) coolsms.send(params);
	        System.out.println(obj.toString());
	      } catch (CoolsmsException e) {
	        System.out.println(e.getMessage());
	        System.out.println(e.getCode());
	      }
		}

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
