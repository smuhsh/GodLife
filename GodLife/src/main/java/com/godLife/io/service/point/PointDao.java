package com.godLife.io.service.point;

import java.util.Map;

import com.godLife.io.service.domain.Point;
import com.godLife.io.common.Search;

public interface PointDao {

	public void addPurchase(Point point) throws Exception;
	
	public Map<String,Object> getPurchaseList(Search search,String userEmail) throws Exception;
	
	public int getTotalCount(Search search);
}
