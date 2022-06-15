package com.godLife.io.service.operator;

import java.util.List;
import java.util.Map;

import com.godLife.io.common.Search;
import com.godLife.io.service.domain.OperatorEvents;
import com.godLife.io.service.domain.OperatorJoinEvent;
import com.godLife.io.service.domain.OperatorReward;
import com.godLife.io.service.domain.User;
import com.godLife.io.service.domain.OperatorNoticeFaqs;

public interface OperatorDao {
	
	//Insert
	public void addOperatorEvents(OperatorEvents operatorEvents ) throws Exception;
	public void addOperatorJoinEvent(OperatorJoinEvent operatorJoinEvent ) throws Exception;
	public void addOperatorReward(OperatorReward operatorReward ) throws Exception;
	//public void addOperatorNoticeFaqs(OperatorNoticeFaqs operatorNoticeFaqs ) throws Exception;
	public void addOperatorNotice(OperatorNoticeFaqs operatorNotice ) throws Exception;
	public void addOperatorFaqs(OperatorNoticeFaqs operatorFaqs ) throws Exception;
	
	//SELECT ONE
	public OperatorEvents getOperatorEvents(int eventNo) throws Exception;
	//public OperatorJoinEvent getOperatorJoinEvent(String userEmail) throws Exception;
	public OperatorJoinEvent getOperatorJoinEvent(int joinEventNo) throws Exception;
	public OperatorReward getOperatorReward(int rewardNo) throws Exception;
	//public OperatorNoticeFaqs getOperatorNoticeFaqs(int noticeFaqNo) throws Exception;
	public OperatorNoticeFaqs getOperatorNotice(int noticeFaqNo) throws Exception;
	//public OperatorNoticeFaqs getOperatorFaqs(int noticeFaqNo) throws Exception;
	public OperatorNoticeFaqs getOperatorFaqs(String title) throws Exception;
	
	//SELECT LIST
	public List<OperatorEvents> getOperatorEventsList(Search search) throws Exception;
	public List<OperatorJoinEvent> getOperatorJoinEventList(Search search) throws Exception;
	//public List<OperatorNoticeFaqs> getOperatorNoticeFaqsList(Search search) throws Exception;
	//public Map<String,Object> getOperatorNoticeFaqsList(Search search, User user, OperatorNoticeFaqs operatorNoticeFaqs) throws Exception;
	public Map<String,Object> getOperatorNoticeList(Search search, User user, OperatorNoticeFaqs operatorNotice) throws Exception;
	public Map<String,Object> getOperatorFaqsList(Search search, User user, OperatorNoticeFaqs operatorFaqs) throws Exception;
	
	//UPDATE
	public void updateOperatorEvents(OperatorEvents operatorEvents) throws Exception;
	public void updateOperatorJoinEvent(OperatorJoinEvent operatorJoinEvent) throws Exception;
	//public void updateOperatorReward(OperatorReward operatorReward) throws Exception;
	public void updateOperatorDayReward(OperatorReward operatorDayReward, OperatorJoinEvent user) throws Exception;
	public void updateOperatorRoullReward(OperatorReward operatorRoullReward) throws Exception;
	//public void updateOperatorNoticeFaqs(OperatorNoticeFaqs operatorNoticeFaqs) throws Exception;
	public void updateOperatorNotice(OperatorNoticeFaqs operatorNotice) throws Exception;
	public void updateOperatorFaqs(OperatorNoticeFaqs operatorFaqs) throws Exception;
	
	//DELETE
	public void deleteOperatorEvents(OperatorEvents operatorEvents) throws Exception;
	//public void deleteOperatorNoticeFaqs(OperatorNoticeFaqs operatorNoticeFaqs) throws Exception;
	public void deleteOperatorNotice(OperatorNoticeFaqs operatorNotice) throws Exception;
	public void deleteOperatorFaqs(OperatorNoticeFaqs operatorFaqs) throws Exception;
	
	// Page All Row(totalCount)  return
	//public int getTotalCount(Search search) throws Exception ;
	public int getOperatorEventsTotalCount(Search search) throws Exception ;
	public int getOperatorJoinEventTotalCount(Search search) throws Exception ;
	public int getOperatorRewardTotalCount(Search search) throws Exception ;
	//public int getOperatorNoticeFaqsTotalCount(Search search) throws Exception ;
	public int getOperatorNoticeTotalCount(Search search) throws Exception ;
	public int getOperatorFaqsTotalCount(Search search) throws Exception ;
	
}
