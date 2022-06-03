package com.godLife.io.service.point;

import java.util.Map;

import com.godLife.io.service.domain.Point;
import com.godLife.io.common.Search;

public interface PointDao {

	public void addPointPurchaseProduct(Point point) throws Exception;
	
	public void addPointPurchase(Point point) throws Exception;
	
	public Map<String,Object> getPointPurchaseList(Search search,String userEmail) throws Exception;

	public Map<String,Object> getPointPurchaseVoucherList(Search search,String userEmail) throws Exception;
	
	public Map<String,Object> getPointPurchaseDonationList(Search search,String userEmail) throws Exception;
	
	public int getTotalCount(Search search);
}
