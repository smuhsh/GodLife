package com.godLife.io.service.operator.impl;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.godLife.io.common.Search;
import com.godLife.io.service.domain.OperatorEvents;
import com.godLife.io.service.domain.OperatorJoinEvent;
import com.godLife.io.service.domain.OperatorReward;
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
		sqlSession.insert("OperatorEventsMapper.addOperatorEvents", operatorEvents);		
	}
	
	public void addOperatorJoinEvent(OperatorJoinEvent operatorJoinEvent) throws Exception {
		sqlSession.insert("OperatorJoinEventMapper.addOperatorJoinEvent",operatorJoinEvent);
	}
	
	public void addOperatorReward(OperatorReward operatorReward) throws Exception {
		sqlSession.insert("OperatorRewardMapper.addOperatorReward", operatorReward);
	}
	
	public void addOperatorNoticeFaqs(OperatorNoticeFaqs operatorNoticeFaqs) throws Exception {
		sqlSession.insert("OperatorNoticeFaqsMapper.addOperatorNoticeFaqs",operatorNoticeFaqs);
	}
	
	///Method get
	public OperatorEvents getOperatorEvents(int eventNo) throws Exception {
		return sqlSession.selectOne("OperatorEventsMapper.getOperatorEvents", eventNo);
	}

	public OperatorJoinEvent getOperatorJoinEvent(int joinEventNo) throws Exception {
		return sqlSession.selectOne("OperatorJoinEventMapper.getOperatorJoinEvent", joinEventNo);
	}
	
	public OperatorReward getOperatorReward(int rewardNo) throws Exception {
		return sqlSession.selectOne("OperatorRewardMapper.getOperatorReward",rewardNo);
	}
	
	public OperatorNoticeFaqs getOperatorNoticeFaqs(int noticeFaqNo) throws Exception {
		return sqlSession.selectOne("OperatorNoticeFaqsMapper.getOperatorNoticeFaqs",noticeFaqNo);
	}
	
	///Method List
	public List<OperatorEvents> getOperatorEventsList(Search search) throws Exception {
//		return sqlSession.selectList("OperatorEventsListMapper.getOperatorEventsList",search);
		return sqlSession.selectList("OperatorEventsMapper.getOperatorEventsList",search);
	}
	
	public List<OperatorJoinEvent> getOperatorJoinEventList(Search search) throws Exception {
		return sqlSession.selectList("OperatorJoinEventMapper.getOperatorJoinEventList",search);
	}
	
	public List<OperatorReward> getOperatorRewardList(Search search) throws Exception {
		return sqlSession.selectList("OperatorRewardMapper.getOperatorRewardList",search);
	}
	
	public List<OperatorNoticeFaqs> getOperatorNoticeFaqsList(Search search) throws Exception {
		return sqlSession.selectList("OperatorNoticeFaqsMapper.getOperatorNoticeFaqsList",search);
	}
	
	///Method Update
	public void updateOperatorEvents(OperatorEvents operatorEvents) throws Exception {
		sqlSession.update("OperatorEventsMapper.updateOperatorEvents",operatorEvents);
	}
	
	public void updateOperatorJoinEvent(OperatorJoinEvent operatorJoinEvent) throws Exception {
		sqlSession.update("OperatorJoinEventMapper.updateOperatorJoinEvent",operatorJoinEvent);
	}
	
	public void updateOperatorReward(OperatorReward operatorReward) throws Exception {
		sqlSession.update("OperatorRewardMapper.updateOperatorReward", operatorReward);
	}
	public void updateOperatorNoticeFaqs(OperatorNoticeFaqs operatorNoticeFaqs) throws Exception {
		sqlSession.update("OperatorNoticeFaqsMapper.updateOperatorNoticeFaqs",operatorNoticeFaqs);
	}

	//Method delete
	public void deleteOperatorEvents(OperatorEvents operatorEvents) throws Exception {
		sqlSession.delete("OperatorEventsMapper.deleteOperatorEvents",operatorEvents);		
	}

	public void deleteOperatorJoinEvent(OperatorJoinEvent operatorJoinEvent) throws Exception {
		sqlSession.delete("OperatorJoinEventMapper.deleteOperatorJoinEvent",operatorJoinEvent);				
	}

	public void deleteOperatorReward(OperatorReward operatorReward) throws Exception {
		sqlSession.delete("OperatorRewardMapper.deleteOperatorReward",operatorReward);						
	}
	
	public void deleteOperatorNoticeFaqs(OperatorNoticeFaqs operatorNoticeFaqs) throws Exception {
		sqlSession.delete("OperatorNoticeFaqsMapper.deleteOperatorNoticeFaqs",operatorNoticeFaqs);								
	}
	
	//Page Row(totalCount)  return
//	public int getTotalCount(Search search) throws Exception {
//		return sqlSession.selectOne("OperatorEventsMapper.getTotalCount", search);
//	}	
	
	//Method Page All Row(totalCount)
	public int getOperatorEventsTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("OperatorEventsMapper.getOperatorEventsTotalCount");
	}

	public int getOperatorJoinEventTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("OperatorJoinEventMapper.getOperatorJoinEventTotalCount");
	}

	public int getOperatorRewardTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("OperatorRewardMapper.getOperatorRewardTotalCount");
	}

	public int getOperatorNoticeFaqsTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("OperatorNoticeFaqsMapper.getOperatorNoticeFaqsTotalCount");
	}
	

}