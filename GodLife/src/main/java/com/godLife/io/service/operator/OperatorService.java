package com.godLife.io.service.operator;

import java.util.Map;

import com.godLife.io.common.Search;
import com.godLife.io.service.domain.OperatorEvents;
import com.godLife.io.service.domain.OperatorJoinEvent;
import com.godLife.io.service.domain.OperatorReward;
import com.godLife.io.service.domain.OperatorNoticeFaqs;

public interface OperatorService {
	
	public void addOperatorEvents(OperatorEvents operatorEvents ) throws Exception;
	public void addOperatorJoinEvent(OperatorJoinEvent operatorJoinEvent ) throws Exception;
	public void addOperatorReward(OperatorReward operatorReward ) throws Exception;
	public void addOperatorNoticeFaqs(OperatorNoticeFaqs operatorNoticeFaqs ) throws Exception;
	
	public OperatorEvents getOperatoreEvents(int eventNo) throws Exception;
	public OperatorJoinEvent getOperatoreJoinEvent(int joinEventNo) throws Exception;
	public OperatorReward getOperatoreReward(int rewardNo) throws Exception;
	public OperatorNoticeFaqs getOperatorNoticeFaqs(int noticeFaqNo) throws Exception;	

	public Map<String , Object> getOperatorEventsList(Search search) throws Exception;
	public Map<String , Object> getOperatorJoinEventList(Search search) throws Exception;
	public Map<String , Object> getOperatorRewardList(Search search) throws Exception;
	public Map<String , Object> getOperatorNoticeFaqsList(Search search) throws Exception;
	
	public void updateOperatorEvents(OperatorEvents operatorEvents) throws Exception;
	public void updateOperatorJoinEvent(OperatorJoinEvent operatorJoinEvent) throws Exception;
	public void updateOperatorReward(OperatorReward operatorReward) throws Exception;
	public void updateOperatorNoticeFaqs(OperatorNoticeFaqs operatorNoticeFaqs) throws Exception;
	
}
