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
	public void addChallenge(Challenge challenge,JoinChallenger joinChallenger) {
		challengeDao.addChallenge(challenge,joinChallenger);
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
	public CertiImg getChallengeCertiImg(int certiImgNo) {
		return challengeDao.getChallengeCertiImg(certiImgNo);
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
	public List<Review> getChallengeCommentList(int certiImgNo) {
		return challengeDao.getChallengeCommentList(certiImgNo);
	}


	@Override
	public void deleteChallengeCertiImg(Map<String, Object> map) {
		challengeDao.deleteChallengeCertiImg(map);
	}


	

}
