package com.godLife.io.service.challenge.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.godLife.io.service.challenge.ChallengeDao;
import com.godLife.io.service.domain.Challenge;
import com.godLife.io.service.domain.User;

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

	public Map<String, Object> getChallengeList(Map<String, Object> map) {
		
		User user = (User)map.get("user");
		String challengeListOpt = (String)map.get("challengeListOpt");
		System.out.println("challengeListOpt : "+challengeListOpt);
		if(user != null) {
			
			if(challengeListOpt.equals("total")) {
				//전체 목록 조회 challengeListOpt = total;
				System.out.println("로그인");
				List<Challenge> list = sqlSession.selectList("ChallengeMapper.getChallengeListLogin",map);
				
				int totalCount = sqlSession.selectOne("ChallengeMapper.getChallengeListLoginTotal",map);
				
				map.put("totalCount", totalCount);
				map.put("list", list);
				
			}else {
				//그외 목록 조회 challengeListOpt = pick / join / add
				List<Challenge> list = sqlSession.selectList("ChallengeMapper.getChallengeList",map);
				int totalCount = 0;
				map.put("totalCount", totalCount);
				map.put("list", list);
			}
			
		}else {
			//challengeListOpt와 무관
			System.out.println("비로그인");
			List<Challenge> list = sqlSession.selectList("ChallengeMapper.getChallengeList",map);
			int totalCount = 0;
			map.put("totalCount", totalCount);
			map.put("list", list);
		}
		
		return map;
	}

	@Override
	public Challenge getChallenge(Map map) { // challengeNo + 유저 정보
		
		User user = (User)map.get("user");
		
		/////////////////////// 로그인 / 비로그인 모두 같은 정보 조회 ///////////////////////
		
		//챌린지 정보
		Challenge challenge = sqlSession.selectOne("ChallengeMapper.getChallenge",map); //완
		System.out.println("challenge : "+challenge);
		//인증 주기(요일) 정보
		// 1,2,3,4,5,6,7... 이라는 요일정보를 가지고 있음. //완
		List<String> certiCycle = sqlSession.selectList("ChallengeMapper.getCertiDay",map);
		List<String> certiCycleName = new ArrayList<String>();// 없어도 될거같긴한데 일단 나중 문제
		
		for(int i=0; i<certiCycle.size(); i++) {
			if(certiCycle.get(i).equals("1")) {
				certiCycleName.add("일");
			}
			if(certiCycle.get(i).equals("2")) {
				certiCycleName.add("월");			
			}
			if(certiCycle.get(i).equals("3")) {
				certiCycleName.add("화");
			}
			if(certiCycle.get(i).equals("4")) {
				certiCycleName.add("수");
			}
			if(certiCycle.get(i).equals("5")) {
				certiCycleName.add("목");
			}
			if(certiCycle.get(i).equals("6")) {
				certiCycleName.add("금");				
			}
			if(certiCycle.get(i).equals("7")) {
				certiCycleName.add("토");
			}
		}
		System.out.println("CertiCycle : "+certiCycle);
		challenge.setCertiCycle(certiCycle);//certiCycleName 없이 바로 여기에넣어서 확인하면 될거같긴한데... 나중에 더 검토후 판단
		challenge.setCertiCycleName(certiCycleName);
		System.out.println("CertiCycleName : "+challenge.getCertiCycleName());
		
		//인증 날짜 정보 //완
		List<String> certiDate = sqlSession.selectList("ChallengeMapper.getCertiDate",map);
		
		challenge.setCertiDate(certiDate);
		
		/////////////////////// 로그인 / 비로그인 모두 같은 정보 조회 ///////////////////////
		
		
		/////////////////////// 로그인 / 비로그인 정보가 다른 조회 //////////////////////////
		
		if(user != null) { // 로그인 한 유저일때만 조회
			
			//유저 참여자 판별
			int join = sqlSession.selectOne("ChallengeMapper.getJoiner",map);//해당 유저가 참여자인지 판별(있다면 1 반환)
			if(join != 0) {
				challenge.setChallengeJoinFlag("1"); // 참여자
			}else {
				challenge.setChallengeJoinFlag("0"); // 비참여자
			}
		}else {
			challenge.setChallengeJoinFlag("0"); // 비로그인 유저는 항상 비참여자이어야 한다.
		}
		/////////////////////// 로그인 / 비로그인 정보가 다른 조회 //////////////////////////
		
		
		
		return challenge;
	}
	
}
