package com.godLife.io.service.challenge.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.godLife.io.service.challenge.ChallengeDao;
import com.godLife.io.service.challenge.ChallengeService;
import com.godLife.io.service.domain.CertiImg;
import com.godLife.io.service.domain.Challenge;
import com.godLife.io.service.domain.JoinChallenger;
import com.godLife.io.service.domain.Review;
import com.godLife.io.service.domain.User;


@Service("challengeServiceImpl")
public class ChallengeServiceImpl implements ChallengeService {
	
	
	//Field
	@Autowired
	@Qualifier("challengeDaoImpl")
	private ChallengeDao challengeDao;
	
	
	@Override
	public Map<String,Object> addChallenge(Challenge challenge,JoinChallenger joinChallenger) {
		return challengeDao.addChallenge(challenge,joinChallenger);
	}


	@Override
	public Map<String, Object> getChallengeList(Map<String, Object> map) {
		
		return challengeDao.getChallengeList(map);
	
	}


	@Override
	public Challenge getChallenge(Map map) {
		return challengeDao.getChallenge(map);
	}


	@Override
	public Map<String,Object> deleteChallenge(int challengeNo) {
		return challengeDao.deleteChallenge(challengeNo);
	}


	@Override
	public void addChallengeJoin(JoinChallenger joinChallenger) {
		challengeDao.addChallengeJoin(joinChallenger);
	}


	@Override
	public int deleteChallengeJoin(JoinChallenger joinChallenger) {
		return challengeDao.deleteChallengeJoin(joinChallenger);
	}


	@Override
	public void addChallengeCertiImg(CertiImg certiImg) {
		challengeDao.addChallengeCertiImg(certiImg);
	}


	@Override
	public List<CertiImg> getChallengeJoinCertiImgList(Map<String, Object> map) {
		return challengeDao.getChallengeJoinCertiImgList(map);
	}


	@Override
	public Map<String,Object> getChallengeCertiImgList(Map<String, Object> map) {
		return challengeDao.getChallengeCertiImgList(map);
	}


	@Override
	public CertiImg getChallengeCertiImg(Map<String,Object> map) {
		return challengeDao.getChallengeCertiImg(map);
	}


	@Override
	public void addChallengeReview(Review review) {
		challengeDao.addChallengeReview(review);
		
	}


	@Override
	public void deleteChallengeReview(int reviewNo) {
		challengeDao.deleteChallengeReview(reviewNo);
	}


	@Override
	public void updateChallengeReview(Review review) {
		challengeDao.updateChallengeReview(review);
	}


	@Override
	public List<Review> getChallengeCommentList(Map<String,Object> map) {
		return challengeDao.getChallengeCommentList(map);
	}


	@Override
	public void deleteChallengeCertiImg(Map<String, Object> map) {
		challengeDao.deleteChallengeCertiImg(map);
	}


	@Override
	public int getChallengePick(Map<String,Object> map) {
		return challengeDao.getChallengePick(map);
	}


	@Override
	public void updateChallengeStatus(Map<String, Object> map) {
		challengeDao.updateChallengeStatus(map);
	}


	@Override
	public JoinChallenger getChallengeJoiner(Map<String, Object> map) {
		return challengeDao.getChallengeJoiner(map);
	}


	@Override
	public List<JoinChallenger> getChallengeJoinerList(int challengeNo) {
		return challengeDao.getChallengeJoinerList(challengeNo);
	}


	@Override
	public Map<String, Object> getChallengeListFriend(Map<String, Object> map) {
		return challengeDao.getChallengeListFriend(map);
	}


	@Override
	public List<Review> getChallengeMoreCommentList(Map<String, Object> map) {
		return challengeDao.getChallengeMoreCommentList(map);
	}


	@Override
	public void deleteChallengeReviewLike(Map<String, Object> map) {
		challengeDao.deleteChallengeReviewLike(map);
		
	}


	@Override
	public Map<String, Object> getChallengeRewardElement(JoinChallenger joinChallenger) {
		return challengeDao.getChallengeRewardElement(joinChallenger);
	}


	@Override
	public void updateChallengerewardFlag(JoinChallenger joinChallenger) {
		challengeDao.updateChallengerewardFlag(joinChallenger);
		
	}


	@Override
	public Map<String,Object> getChallengeMoreCertiImgList(Map<String, Object> map) {
		return challengeDao.getChallengeMoreCertiImgList(map);
	}


	
	//moon
	@Override
	public Map<String, Object> getChallengeTargetList(JoinChallenger joinChallenger) {
		return challengeDao.getChallengeTargetList(joinChallenger);
	}
	

}
