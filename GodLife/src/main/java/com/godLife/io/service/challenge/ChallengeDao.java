package com.godLife.io.service.challenge;

import java.util.List;
import java.util.Map;

import com.godLife.io.service.domain.CertiImg;
import com.godLife.io.service.domain.Challenge;
import com.godLife.io.service.domain.JoinChallenger;
import com.godLife.io.service.domain.Review;
import com.godLife.io.service.domain.User;

public interface ChallengeDao {
	public Map<String,Object> addChallenge(Challenge challenge, JoinChallenger joinChallenger);
	
	public Map<String, Object> getChallengeList(Map<String, Object> map);
	
	public Challenge getChallenge(Map map);
	
	public Map<String,Object> deleteChallenge(int challengeNo);
	
	public void addChallengeJoin(JoinChallenger joinChallenger);
	
	public int deleteChallengeJoin(JoinChallenger joinChallenger);
	
	public void addChallengeCertiImg(CertiImg certiImg);
	
	public List<CertiImg> getChallengeJoinCertiImgList(Map<String,Object> map);
	
	public Map<String,Object> getChallengeCertiImgList(Map<String,Object> map);
	
	public CertiImg getChallengeCertiImg(Map<String,Object> map);
	
	public void addChallengeReview(Review review);
	
	public void updateChallengeReview(Review review);
	
	public void deleteChallengeReview(int reviewNo);
	
	public List<Review> getChallengeCommentList(Map<String,Object> map);
	
	public void deleteChallengeCertiImg(Map<String,Object> map);
	
	public int getChallengePick(Map<String,Object> map);
	
	public void updateChallengeStatus(Map<String,Object> map);
	
	public JoinChallenger getChallengeJoiner(Map<String,Object> map);
	
	public List<JoinChallenger> getChallengeJoinerList(int challengeNo);
	
	public Map<String, Object> getChallengeListFriend(Map<String,Object> map);
	
	public List<Review> getChallengeMoreCommentList(Map<String,Object> map);
	
	public void deleteChallengeReviewLike(Map<String,Object> map);
	
	public Map<String,Object> getChallengeRewardElement(JoinChallenger joinChallenger);
	
	public void updateChallengerewardFlag(JoinChallenger joinChallenger);
	
	public Map<String,Object> getChallengeMoreCertiImgList(Map<String,Object> map);

	
	
	//moon
		public Map<String,Object> getChallengeTargetList(JoinChallenger joinChallenger);
}
