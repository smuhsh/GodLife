package com.godLife.io.service.challenge.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.godLife.io.service.challenge.ChallengeDao;
import com.godLife.io.service.domain.Challenge;

@Repository("challengeDaoImpl")
public class ChallengeDaoImpl implements ChallengeDao {
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;

	@Override
	public void addChallenge(Challenge challenge) {
		
		sqlSession.insert("ChallengeMapper.insertChallenge",challenge);
		
		// 챌린지 번호 필요
		
		System.out.println("insert된 챌린지 번호 : "+challenge.getChallengeNo());
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("challengeNo", challenge.getChallengeNo());
		map.put("certiCycle", challenge.getCertiCycle()); //list타입
		map.put("certiDate", challenge.getCertiDate()); //list타입
		
		sqlSession.insert("ChallengeMapper.insertCertiDay",map);
		sqlSession.insert("ChallengeMapper.insertCertiDate",map);
		
		sqlSession.insert("ChallengeMapper.insertJoinChallenger",challenge);
		
		
	}

	@Override
	public Map<String, Object> getChallengeList(Map<String, Object> map) {
		
		
		List<Challenge> list = sqlSession.selectList("ChallengeMapper.getChallengeList",map);
		
		return map;
	}
	
}
