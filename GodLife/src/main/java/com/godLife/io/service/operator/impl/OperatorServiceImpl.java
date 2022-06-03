package com.godLife.io.service.operator.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.godLife.io.common.Search;
import com.godLife.io.service.domain.OperatorEvents;
import com.godLife.io.service.domain.OperatorJoinEvent;
import com.godLife.io.service.domain.OperatorReward;
import com.godLife.io.service.domain.OperatorNoticeFaqs;
import com.godLife.io.service.operator.OperatorService;
import com.godLife.io.service.operator.OperatorDao;

//==> Operator(Event, JoinEvent, Reward, NoticeFaq)Service
@Service("operatorServiceImpl")
public class OperatorServiceImpl implements OperatorService{
	
	//Field
	@Autowired
	@Qualifier("operatorDaoImpl")
	private OperatorDao operatorDao;
	public void setOperatorDao(OperatorDao operatorDao) {
		this.operatorDao = operatorDao;
	}
	
	//Constructor
	public OperatorServiceImpl() {
		// TODO Auto-generated constructor stub
		System.out.println(this.getClass());
	}
	
	//Method Add
	public void addOperatorEvents(OperatorEvents operatorEvents) throws Exception {
		operatorDao.addOperatorEvents(operatorEvents);
	}
	
	public void addOperatorJoinEvent(OperatorJoinEvent operatorJoinEvent) throws Exception {
		operatorDao.addOperatorJoinEvent(operatorJoinEvent);
	}
	
	public void addOperatorReward(OperatorReward operatorReward) throws Exception {
		operatorDao.addOperatorReward(operatorReward);
	}

	public void addOperatorNoticeFaqs(OperatorNoticeFaqs operatorNoticeFaqs) throws Exception {
		operatorDao.addOperatorNoticeFaqs(operatorNoticeFaqs);
	}

	//Method get
	public OperatorEvents getOperatoreEvents(int eventNo) throws Exception {
		return operatorDao.getOperatorEvents(eventNo);
	}
	
	public OperatorJoinEvent getOperatoreJoinEvent(int joinEventNo) throws Exception {
		return operatorDao.getOperatorJoinEvent(joinEventNo);
	}
	
	public OperatorReward getOperatoreReward(int rewardNo) throws Exception {
		return operatorDao.getOperatorReward(rewardNo);
	}
	
	public OperatorNoticeFaqs getOperatorNoticeFaqs(int noticeFaqNo) throws Exception {
		return operatorDao.getOperatorNoticeFaqs(noticeFaqNo);
	}
	
	//Method List
	public Map<String, Object> getOperatorEventsList(Search search) throws Exception {
		List<OperatorEvents> list = operatorDao.getOperatorEventsList(search);
//		int totalCount = operatorDao.getTotalCount(search);
		int totalCount = operatorDao.getOperatorEventsTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	public Map<String, Object> getOperatorJoinEventList(Search search) throws Exception {
		List<OperatorJoinEvent> list = operatorDao.getOperatorJoinEventList(search);
		int totalCount = operatorDao.getOperatorJoinEventTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}


	public Map<String, Object> getOperatorRewardList(Search search) throws Exception {
		List<OperatorReward> list = operatorDao.getOperatorRewardList(search);
		int totalCount = operatorDao.getOperatorRewardTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	public Map<String, Object> getOperatorNoticeFaqsList(Search search) throws Exception {
		List<OperatorNoticeFaqs> list = operatorDao.getOperatorNoticeFaqsList(search);
		int totalCount = operatorDao.getOperatorNoticeFaqsTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}

	//Method Update
	public void updateOperatorEvents(OperatorEvents operatorEvents) throws Exception {
		operatorDao.updateOperatorEvents(operatorEvents);		
	}

	public void updateOperatorJoinEvent(OperatorJoinEvent operatorJoinEvent) throws Exception {
		operatorDao.updateOperatorJoinEvent(operatorJoinEvent);
	}

	public void updateOperatorReward(OperatorReward operatorReward) throws Exception {
		operatorDao.updateOperatorReward(operatorReward);
	}

	public void updateOperatorNoticeFaqs(OperatorNoticeFaqs operatorNoticeFaqs) throws Exception {
		operatorDao.updateOperatorNoticeFaqs(operatorNoticeFaqs);
	}
	
	//Method delete
	public void deleteOperatorEvents(OperatorEvents operatorEvents) throws Exception {
		operatorDao.deleteOperatorEvents(operatorEvents);		
	}
	
	public void deleteOperatorJoinEvent(OperatorJoinEvent operatorJoinEvent) throws Exception {
		operatorDao.deleteOperatorJoinEvent(operatorJoinEvent);
	}
	
	public void deleteOperatorReward(OperatorReward operatorReward) throws Exception {
		operatorDao.deleteOperatorReward(operatorReward);
	}
	
	public void deleteOperatorNoticeFaqs(OperatorNoticeFaqs operatorNoticeFaqs) throws Exception {
		operatorDao.deleteOperatorNoticeFaqs(operatorNoticeFaqs);
	}

}
