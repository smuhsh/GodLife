package com.godLife.io.service.point;

import java.util.Map;

import com.godLife.io.service.domain.Point;
import com.godLife.io.service.domain.User;
import com.godLife.io.common.Search;

public interface PointDao {

	public void addPointPurchaseProduct(Map<String,Object> map) throws Exception;
	
	public void addPointPurchase(Map<String,Object> map) throws Exception;
	
	public Map<String,Object> getPointPurchaseList(Search search,User user) throws Exception;

	public Map<String,Object> getPointPurchaseVoucherList(Search search,User user) throws Exception;
	
	public Map<String,Object> getPointPurchaseDonationList(Search search,User user) throws Exception;
	
	public Map<String,Object> getPointPurchaseDonationRank(Point point) throws Exception;
	
	public int getTotalCount(Search search,User user);
	
	public int getVoucherTotalCount(User user);
	
	public int getDonationTotalCount(Search search,User user);
}
