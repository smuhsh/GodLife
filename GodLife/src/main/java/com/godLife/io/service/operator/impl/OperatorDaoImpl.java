package com.godLife.io.service.operator.impl;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.godLife.io.common.Search;
import com.godLife.io.service.domain.OperatorEvents;
import com.godLife.io.service.domain.OperatorJoinEvent;
import com.godLife.io.service.domain.OperatorReward;
import com.godLife.io.service.domain.Point;
import com.godLife.io.service.domain.User;
import com.godLife.io.service.domain.OperatorNoticeFaqs;
import com.godLife.io.service.operator.OperatorDao;

@Repository("operatorDaoImpl")
public class OperatorDaoImpl implements OperatorDao{
	
	///Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public OperatorDaoImpl() {
		System.out.println(this.getClass());
	}

	///Method Add
	public void addOperatorEvents(OperatorEvents operatorEvents) throws Exception {
		//sqlSession.insert("OperatorEventsMapper.addOperatorEvents", operatorEvents);		
		sqlSession.insert("OperatorMapper.addOperatorEvents", operatorEvents);		
	}
	
	public void addOperatorJoinEvent(OperatorJoinEvent operatorJoinEvent) throws Exception {
		//sqlSession.insert("OperatorJoinEventMapper.addOperatorJoinEvent",operatorJoinEvent);
		sqlSession.insert("OperatorMapper.addOperatorJoinEvent", operatorJoinEvent);
	}
	
	public void addOperatorReward(OperatorReward operatorReward) throws Exception {
		//sqlSession.insert("OperatorRewardMapper.addOperatorReward", operatorReward);
		sqlSession.insert("OperatorMapper.addOperatorReward", operatorReward);
	}
	
	public void addOperatorNoticeFaqs(OperatorNoticeFaqs operatorNoticeFaqs) throws Exception {
		//sqlSession.insert("OperatorNoticeFaqsMapper.addOperatorNoticeFaqs",operatorNoticeFaqs);
		sqlSession.insert("OperatorMapper.addOperatorNoticeFaqs", operatorNoticeFaqs);
	}
	
	///Method get
	public OperatorEvents getOperatorEvents(int eventNo) throws Exception {
		//return sqlSession.selectOne("OperatorEventsMapper.getOperatorEvents", eventNo);
		return sqlSession.selectOne("OperatorMapper.getOperatorEvents", eventNo);
	}
	//public OperatorJoinEvent getOperatorJoinEvent(String userEmail) throws Exception {
	//return sqlSession.selectOne("OperatorJoinEventMapper.getOperatorJoinEvent", userEmail);
	//}
	public OperatorJoinEvent getOperatorJoinEvent(int joinEventNo) throws Exception {
		//return sqlSession.selectOne("OperatorJoinEventMapper.getOperatorJoinEvent", joinEventNo);
		return sqlSession.selectOne("OperatorMapper.getOperatorJoinEvent", joinEventNo);
	}
	
	public OperatorReward getOperatorReward(int rewardNo) throws Exception {
		//return sqlSession.selectOne("OperatorRewardMapper.getOperatorReward",rewardNo);
		return sqlSession.selectOne("OperatorMapper.getOperatorReward",rewardNo);
	}
	
	public OperatorNoticeFaqs getOperatorNoticeFaqs(int noticeFaqNo) throws Exception {
		//return sqlSession.selectOne("OperatorNoticeFaqsMapper.getOperatorNoticeFaqs",noticeFaqNo);
		return sqlSession.selectOne("OperatorMapper.getOperatorNoticeFaqs",noticeFaqNo);
	}
	
	///Method List
	public List<OperatorEvents> getOperatorEventsList(Search search) throws Exception {
		//return sqlSession.selectList("OperatorEventsListMapper.getOperatorEventsList",search);
		//return sqlSession.selectList("OperatorEventsMapper.getOperatorEventsList",search);
		return sqlSession.selectList("OperatorMapper.getOperatorEventsList",search);
	}
	
	public List<OperatorJoinEvent> getOperatorJoinEventList(Search search) throws Exception {
		//return sqlSession.selectList("OperatorJoinEventMapper.getOperatorJoinEventList",search);
		return sqlSession.selectList("OperatorMapper.getOperatorJoinEventList",search);
	}
		
//	public List<OperatorNoticeFaqs> getOperatorNoticeFaqsList(Search search) throws Exception {
//		//return sqlSession.selectList("OperatorNoticeFaqsMapper.getOperatorNoticeFaqsList",search);
//		return sqlSession.selectList("OperatorMapper.getOperatorNoticeFaqsList",search);
//	}
	public Map<String, Object> getOperatorNoticeFaqsList(Search search, User user, OperatorNoticeFaqs operatorNoticeFaqs) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>();
		String userEmail=user.getUserEmail();
		String title = operatorNoticeFaqs.getTitle();
		Date regDate = operatorNoticeFaqs.getRegDate();
		
		map.put("userEmail",userEmail);
		map.put("title",title);
		map.put("regDate",regDate);
		
		List<OperatorNoticeFaqs> list = sqlSession.selectList("OperatorMapper.getOperatorNoticeFaqsList", map);
		System.out.println("@@@@@@@@@@dao list : "+list);
		map.put("list", list);
		
		return map;
		
		
	}	
	///Method Update
	public void updateOperatorEvents(OperatorEvents operatorEvents) throws Exception {
		//sqlSession.update("OperatorEventsMapper.updateOperatorEvents",operatorEvents);
		sqlSession.update("OperatorMapper.updateOperatorEvents",operatorEvents);
	}
	
	public void updateOperatorJoinEvent(OperatorJoinEvent operatorJoinEvent) throws Exception {
		//sqlSession.update("OperatorJoinEventMapper.updateOperatorJoinEvent",operatorJoinEvent);
		sqlSession.update("OperatorMapper.updateOperatorJoinEvent",operatorJoinEvent);
	}
	
	public void updateOperatorDayReward(OperatorReward operatorDayReward) throws Exception {
		//sqlSession.update("OperatorRewardMapper.updateOperatorDayReward", operatorDayReward);
		sqlSession.update("OperatorMapper.updateOperatorDayReward", operatorDayReward);
	}
	
	public void updateOperatorReward(OperatorReward operatorRoullReward) throws Exception {
		//sqlSession.update("OperatorRewardMapper.updateOperatorReward", operatorReward);
		sqlSession.update("OperatorMapper.updateOperatorRoullReward", operatorRoullReward);
	}
	
	@Override
	public void updateOperatorDayReward(OperatorReward operatorDayReward, OperatorJoinEvent user) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		String userEmail = user.getUserEmail();
		int rewardNo = operatorDayReward.getRewardNo();
		int rewardPoint = operatorDayReward.getRewardPoint();
		map.put("rewardNo", rewardNo);
		map.put("userEmail", userEmail);
		map.put("rewardPoint", rewardPoint);
		//sqlSession.update("OperatorRewardMapper.updateOperatorDayReward", map);
		sqlSession.update("OperatorMapper.updateOperatorDayReward", map);
		
	}

	public void updateOperatorRoullReward(OperatorReward operatorRoullReward) throws Exception {
		//sqlSession.update("OperatorRewardMapper.updateOperatorRoullReward", operatorRoullReward);
		sqlSession.update("OperatorMapper.updateOperatorRoullReward", operatorRoullReward);
	}

	public void updateOperatorNoticeFaqs(OperatorNoticeFaqs operatorNoticeFaqs) throws Exception {
		//sqlSession.update("OperatorNoticeFaqsMapper.updateOperatorNoticeFaqs",operatorNoticeFaqs);
		sqlSession.update("OperatorMapper.updateOperatorNoticeFaqs",operatorNoticeFaqs);
	}

	//Method delete
	public void deleteOperatorEvents(OperatorEvents operatorEvents) throws Exception {
		//sqlSession.delete("OperatorEventsMapper.deleteOperatorEvents",operatorEvents);		
		sqlSession.delete("OperatorMapper.deleteOperatorEvents",operatorEvents);		
	}
	
	public void deleteOperatorNoticeFaqs(OperatorNoticeFaqs operatorNoticeFaqs) throws Exception {
		//sqlSession.delete("OperatorNoticeFaqsMapper.deleteOperatorNoticeFaqs",operatorNoticeFaqs);								
		sqlSession.delete("OperatorMapper.deleteOperatorNoticeFaqs",operatorNoticeFaqs);								
	}
	
	//Page Row(totalCount)  return
	//public int getTotalCount(Search search) throws Exception {
	//	return sqlSession.selectOne("OperatorEventsMapper.getTotalCount", search);
	//}	
	
	//Method Page All Row(totalCount)
	public int getOperatorEventsTotalCount(Search search) throws Exception {
		//return sqlSession.selectOne("OperatorEventsMapper.getOperatorEventsTotalCount");
		return sqlSession.selectOne("OperatorMapper.getOperatorEventsTotalCount");
	}

	public int getOperatorJoinEventTotalCount(Search search) throws Exception {
		//return sqlSession.selectOne("OperatorJoinEventMapper.getOperatorJoinEventTotalCount");
		return sqlSession.selectOne("OperatorMapper.getOperatorJoinEventTotalCount");
	}

	public int getOperatorRewardTotalCount(Search search) throws Exception {
		//return sqlSession.selectOne("OperatorRewardMapper.getOperatorRewardTotalCount");
		return sqlSession.selectOne("OperatorMapper.getOperatorRewardTotalCount");
	}

	public int getOperatorNoticeFaqsTotalCount(Search search) throws Exception {
		//return sqlSession.selectOne("OperatorNoticeFaqsMapper.getOperatorNoticeFaqsTotalCount");
		return sqlSession.selectOne("OperatorMapper.getOperatorNoticeFaqsTotalCount");
	}



}