package com.godLife.io.service.challenge;

import java.util.List;
import java.util.Map;

import com.godLife.io.service.domain.CertiImg;
import com.godLife.io.service.domain.Challenge;
import com.godLife.io.service.domain.JoinChallenger;
import com.godLife.io.service.domain.User;

public interface ChallengeDao {
	public void addChallenge(Challenge challenge, JoinChallenger joinChallenger);
	
	public Map<String, Object> getChallengeList(Map<String, Object> map);
	
	public Challenge getChallenge(Map map);
	
	public Map<String,Object> deleteChallenge(int challengeNo);
	
	public void addChallengeJoin(JoinChallenger joinChallenger);
	
	public int deleteChallengeJoin(JoinChallenger joinChallenger);
	
	public void addChallengeCertiImg(CertiImg certiImg);
	
	public List<CertiImg> getChallengeJoinCertiImg(Map<String,Object> map);
}
