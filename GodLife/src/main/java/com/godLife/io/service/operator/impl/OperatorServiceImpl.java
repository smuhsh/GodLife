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
import com.godLife.io.service.domain.User;
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
	
//	public void addOperatorJoinEvent(OperatorJoinEvent operatorJoinEvent) throws Exception {
//		operatorDao.addOperatorJoinEvent(operatorJoinEvent);
//	}
	public void addOperatorJoinDayEvent(OperatorJoinEvent operatorJoinEvent) throws Exception {
		operatorDao.addOperatorJoinDayEvent(operatorJoinEvent);
	}
	public void addOperatorJoinRoullEvent(OperatorJoinEvent operatorJoinEvent) throws Exception {
		operatorDao.addOperatorJoinRoullEvent(operatorJoinEvent);
	}
//	public OperatorJoinEvent addOperatorJoinRoullEvent(OperatorJoinEvent operatorJoinEvent) throws Exception {
//		operatorDao.addOperatorJoinRoullEvent(operatorJoinEvent);
//		return operatorJoinEvent;
//	}	
	
	public void addOperatorReward(OperatorReward operatorReward) throws Exception {
		operatorDao.addOperatorReward(operatorReward);
	}

//	public void addOperatorNoticeFaqs(OperatorNoticeFaqs operatorNoticeFaqs) throws Exception {
//		operatorDao.addOperatorNoticeFaqs(operatorNoticeFaqs);
//	}
	public void addOperatorNotice(OperatorNoticeFaqs operatorNotice) throws Exception {
		operatorDao.addOperatorNotice(operatorNotice);
	}
	public void addOperatorFaqs(OperatorNoticeFaqs operatorFaqs) throws Exception {
		operatorDao.addOperatorFaqs(operatorFaqs);
	}

	//Method get
	public OperatorEvents getOperatorEvents(int eventNo) throws Exception {
		return operatorDao.getOperatorEvents(eventNo);
	}
	//public OperatorJoinEvent getOperatorJoinEvent(String userEmail) throws Exception {
	//	return operatorDao.getOperatorJoinEvent(userEmail);
	//}	
	
//	public OperatorJoinEvent getOperatorJoinEvent(int joinEventNo) throws Exception {
//		return operatorDao.getOperatorJoinEvent(joinEventNo);
//	}
	public OperatorJoinEvent getOperatorJoinDayEvent(int joinEventNo) throws Exception {
		System.out.println("@@@@@@@@@@@ getOperatorJoinDayEvent"+joinEventNo);
		return operatorDao.getOperatorJoinDayEvent(joinEventNo);
	}
	public Map<String , Object> getOperatorJoinDayEventUser(OperatorJoinEvent operatorJoinEvent) throws Exception {
		System.out.println("@@@@@@@@@@@ getOperatorJoinDayEventUser 시작 :"+operatorJoinEvent);
		Map<String,Object> map = new HashMap<String,Object>();
		int totalCount = operatorDao.getOperatorJoinDayEventUserToTal(operatorJoinEvent);
		map.put("operatorJoinEvent", operatorDao.getOperatorJoinDayEventUser(operatorJoinEvent));
		map.put("totalCount",totalCount);
		System.out.println("serviceImpl getOperatorJoinDayEventUser 종료 map"+map);
		return map;
	}
	
	public OperatorJoinEvent getOperatorJoinRoullEvent(int joinEventNo) throws Exception {
		return operatorDao.getOperatorJoinRoullEvent(joinEventNo);
	}
	
	public OperatorReward getOperatorReward(int rewardNo) throws Exception {
		return operatorDao.getOperatorReward(rewardNo);
	}
	
//	public OperatorNoticeFaqs getOperatorNoticeFaqs(int noticeFaqNo) throws Exception {
//		return operatorDao.getOperatorNoticeFaqs(noticeFaqNo);
//	}
	public OperatorNoticeFaqs getOperatorNotice(int noticeFaqNo) throws Exception {
		return operatorDao.getOperatorNotice(noticeFaqNo);
	}
//	public OperatorNoticeFaqs getOperatorFaqs(int noticeFaqNo) throws Exception {
//		return operatorDao.getOperatorFaqs(noticeFaqNo);
//	}
	public OperatorNoticeFaqs getOperatorFaqs(String title) throws Exception {
		System.out.println("@@@@@@@serviceimpl title"+title);
		
		return operatorDao.getOperatorFaqs(title);
	}
	
	//Method List
	public Map<String, Object> getOperatorEventsList(Search search) throws Exception {
		List<OperatorEvents> list = operatorDao.getOperatorEventsList(search);
		//int totalCount = operatorDao.getTotalCount(search);
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
	
//	public Map<String, Object> getOperatorNoticeFaqsList(Search search) throws Exception {
//		List<OperatorNoticeFaqs> list = operatorDao.getOperatorNoticeFaqsList(search);
//		int totalCount = operatorDao.getOperatorNoticeFaqsTotalCount(search);
//		
//		Map<String, Object> map = new HashMap<String, Object>();
//		map.put("list", list );
//		map.put("totalCount", new Integer(totalCount));
//		
//		return map;
//	}
//	public Map<String, Object> getOperatorNoticeFaqsList(Search search, User user, OperatorNoticeFaqs operatorNoticeFaqs) throws Exception {
//		Map<String, Object> map = operatorDao.getOperatorNoticeFaqsList(search, user, operatorNoticeFaqs);
//		int totalCount = operatorDao.getOperatorNoticeFaqsTotalCount(search);
//		
//		//Map<String, Object> map = new HashMap<String, Object>();
//		map.put("totalCount", new Integer(totalCount));
//		
//		return map;
//	}
	public Map<String, Object> getOperatorNoticeList(Search search, User user, OperatorNoticeFaqs operatorNotice) throws Exception {
		Map<String, Object> map = operatorDao.getOperatorNoticeList(search, user, operatorNotice);
		int totalCount = operatorDao.getOperatorNoticeTotalCount(search);
		
		//Map<String, Object> map = new HashMap<String, Object>();
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	public Map<String, Object> getOperatorFaqsList(Search search, User user, OperatorNoticeFaqs operatorFaqs) throws Exception {
		Map<String, Object> map = operatorDao.getOperatorFaqsList(search, user, operatorFaqs);
		int totalCount = operatorDao.getOperatorFaqsTotalCount(search);
		
		//Map<String, Object> map = new HashMap<String, Object>();
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
	//public void updateOperatorReward(OperatorReward operatorReward) throws Exception {
	//	operatorDao.updateOperatorReward(operatorReward);
	//}
	
	public void updateOperatorDayReward(OperatorReward operatorDayReward, OperatorJoinEvent user) throws Exception {
		operatorDao.updateOperatorDayReward(operatorDayReward, user);
	}
	public void updateOperatorRoullReward(OperatorReward operatorRoullReward) throws Exception {
		operatorDao.updateOperatorRoullReward(operatorRoullReward);
	}

//	public void updateOperatorNoticeFaqs(OperatorNoticeFaqs operatorNoticeFaqs) throws Exception {
//		operatorDao.updateOperatorNoticeFaqs(operatorNoticeFaqs);
//	}
	public void updateOperatorNotice(OperatorNoticeFaqs operatorNoticeFaqs) throws Exception {
		operatorDao.updateOperatorNotice(operatorNoticeFaqs);
	}
	public void updateOperatorFaqs(OperatorNoticeFaqs operatorNoticeFaqs) throws Exception {
		operatorDao.updateOperatorFaqs(operatorNoticeFaqs);
	}
	
	//Method delete
	public void deleteOperatorEvents(OperatorEvents operatorEvents) throws Exception {
		operatorDao.deleteOperatorEvents(operatorEvents);		
	}	
	
//	public void deleteOperatorNoticeFaqs(OperatorNoticeFaqs operatorNoticeFaqs) throws Exception {
//		operatorDao.deleteOperatorNoticeFaqs(operatorNoticeFaqs);
//	}
	public void deleteOperatorNotice(OperatorNoticeFaqs operatorNoticeFaqs) throws Exception {
		operatorDao.deleteOperatorNotice(operatorNoticeFaqs);
	}
	public void deleteOperatorFaqs(OperatorNoticeFaqs operatorFaqs) throws Exception {
		operatorDao.deleteOperatorFaqs(operatorFaqs);
	}


}
