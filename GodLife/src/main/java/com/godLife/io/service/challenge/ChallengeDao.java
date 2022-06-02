package com.godLife.io.service.challenge;

import java.util.Map;

import com.godLife.io.service.domain.Challenge;

public interface ChallengeDao {
	public void addChallenge(Challenge challenge);
	
	public Map<String, Object> getChallengeList(Map<String, Object> map);
}
