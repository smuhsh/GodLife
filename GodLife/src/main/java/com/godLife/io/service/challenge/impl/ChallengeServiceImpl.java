package com.godLife.io.service.challenge.impl;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.godLife.io.service.challenge.ChallengeDao;
import com.godLife.io.service.challenge.ChallengeService;
import com.godLife.io.service.domain.Challenge;
import com.godLife.io.service.domain.User;


@Service("challengeServiceImpl")
public class ChallengeServiceImpl implements ChallengeService {
	
	
	//Field
	@Autowired
	@Qualifier("challengeDaoImpl")
	private ChallengeDao challengeDao;
	
	
	@Override
	public void addChallenge(Challenge challenge) {
		challengeDao.addChallenge(challenge);
	}


	@Override
	public Map<String, Object> getChallengeList(Map<String, Object> map) {
		
		return challengeDao.getChallengeList(map);
	
	}


	@Override
	public Challenge getChallenge(Map map) {
		return challengeDao.getChallenge(map);
	}

}
