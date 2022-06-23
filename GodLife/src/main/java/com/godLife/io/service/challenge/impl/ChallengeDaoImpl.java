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
import com.godLife.io.service.domain.CertiImg;
import com.godLife.io.service.domain.Challenge;
import com.godLife.io.service.domain.JoinChallenger;
import com.godLife.io.service.domain.Review;
import com.godLife.io.service.domain.User;

@Repository("challengeDaoImpl")
public class ChallengeDaoImpl implements ChallengeDao {
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;

	@Override
	public Map<String,Object> addChallenge(Challenge challenge,JoinChallenger joinChallenger) {
		
		sqlSession.insert("ChallengeMapper.insertChallenge",challenge);
		
		// 챌린지 번호 필요
		
		System.out.println("insert된 챌린지 번호 : "+challenge.getChallengeNo());
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("challengeNo", challenge.getChallengeNo());
		map.put("certiCycle", challenge.getCertiCycle()); //list타입
		map.put("certiDate", challenge.getCertiDate()); //list타입
		
		joinChallenger.setChallengeNo(challenge.getChallengeNo());
		// hostEamil / challengeNo / status = "0"
		
		sqlSession.insert("ChallengeMapper.insertCertiDay",map);
		sqlSession.insert("ChallengeMapper.insertCertiDate",map);
		
		sqlSession.insert("ChallengeMapper.insertJoinChallenger",joinChallenger);
		
		return map;
	}

	public Map<String, Object> getChallengeList(Map<String, Object> map) {
		
		User user = (User)map.get("user");
		String challengeListOpt = (String)map.get("challengeListOpt");
		System.out.println("challengeListOpt : "+challengeListOpt);
		if(user != null) {
			
			if(challengeListOpt.equals("total")) {
				//전체 목록 조회 challengeListOpt = total;
				System.out.println("로그인");
				List<String> black = new ArrayList<String>();
				List<String> friend = new ArrayList<String>();
				List<String> targetEmail = sqlSession.selectList("ChallengeMapper.getFbTarget",map);
				List<String> userEmail = sqlSession.selectList("ChallengeMapper.getFbUser",map);
				
				if(targetEmail != null) {
					for(int i=0; i<targetEmail.size(); i++) {
						black.add(targetEmail.get(i));
					}
				}
				if(userEmail != null) {
					for(int i=0; i<userEmail.size(); i++) {
						black.add(userEmail.get(i));
					}
				}
				
				System.out.println("black : "+black); // map 에 black 가 생김. 위는 null 이란 뜻.
				
				map.put("black", black);
				
				targetEmail = null;  // 만일을 대비해서 null로 초기화
				userEmail = null;      
				
				targetEmail = sqlSession.selectList("ChallengeMapper.getFbTarget",map);  
				userEmail = sqlSession.selectList("ChallengeMapper.getFbUser",map);      
				
				
				if(targetEmail != null) {
					for(int i=0; i<targetEmail.size(); i++) {
						friend.add(targetEmail.get(i));
					}
				}
				if(userEmail != null) {
					for(int i=0; i<userEmail.size(); i++) {
						friend.add(userEmail.get(i));
					}
				}
				
				System.out.println("friend : "+friend);
				
				map.put("friend", friend);
				
				List<Challenge> list = sqlSession.selectList("ChallengeMapper.getChallengeListLogin",map);
				
				int totalCount = sqlSession.selectOne("ChallengeMapper.getChallengeListLoginTotal",map);
				
				map.put("totalCount", totalCount);
				map.put("list", list);
				
			}else {
				//그외 목록 조회 challengeListOpt = pick / join / add
				List<Challenge> list = sqlSession.selectList("ChallengeMapper.getChallengeList",map);
				int totalCount = sqlSession.selectOne("ChallengeMapper.getChallengeListCount",map);
				System.out.println("DAO : "+totalCount);
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

	@Override
	public Map<String,Object> deleteChallenge(int challengeNo) {
		
		JoinChallenger joinChallenger = new JoinChallenger();
		joinChallenger.setChallengeNo(challengeNo);
		
		List<String> challengeJoinList = 
				sqlSession.selectList("ChallengeMapper.getChallengeJoinList",challengeNo);
				//챌린지 참여자 명단 조회 -> 포인트 환불 용도
		int challengeJoinPoint = sqlSession.selectOne("ChallengeMapper.selectChallengeJoinPoint",joinChallenger);
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		//참여자 명단 및 환불 포인트
		map.put("challengeJoinList", challengeJoinList);
		map.put("challengeJoinPoint", challengeJoinPoint);
		map.put("challengeNo",challengeNo);
		
		sqlSession.delete("ChallengeMapper.deleteCertiCycle",challengeNo);//인증주기 삭제
//		sqlSession.delete("ChallengeMapper.deleteCertiImg",map);
		sqlSession.delete("ChallengeMapper.deleteChallengeJoin",joinChallenger);//참여자 목록 삭제
		//동적 쿼리로 사용하기위해 이 부분만 파라메터를 JoinChallenger로 처리
		//챌린지 나가기에 같이 사용 (필요정보 : 참여자 이메일 / challengeNo)
		
	
		sqlSession.delete("ChallengeMapper.deleteChallenge",challengeNo);
		
		return map;
	}

	@Override
	public void addChallengeJoin(JoinChallenger joinChallenger) {
		
		sqlSession.insert("ChallengeMapper.insertJoinChallenger",joinChallenger);
		int joinCount = sqlSession.selectOne("ChallengeMapper.getJoinCount",joinChallenger);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("joinCount", joinCount);
		map.put("joinChallenger", joinChallenger);
		sqlSession.update("ChallengeMapper.updateChallengeJoinCount",map);
		
	}

	@Override
	public int deleteChallengeJoin(JoinChallenger joinChallenger) {
		// 챌린지 나가기일시
		int challengeJoinPoint = 0;
		if(joinChallenger.getStatus().equals("0")) {
		challengeJoinPoint = 
			sqlSession.selectOne("ChallengeMapper.selectChallengeJoinPoint",joinChallenger);
			sqlSession.delete("ChallengeMapper.deleteChallengeJoin",joinChallenger);
			int joinCount = sqlSession.selectOne("ChallengeMapper.getJoinCount",joinChallenger);
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("joinCount", joinCount);
			map.put("joinChallenger", joinChallenger);
			sqlSession.update("ChallengeMapper.updateChallengeJoinCount",map);
		}else {
			sqlSession.delete("ChallengeMapper.deleteChallengeJoin",joinChallenger);
		}
		
		return challengeJoinPoint;
	}

	@Override
	public void addChallengeCertiImg(CertiImg certiImg) {
		//인증이미지 업로드
		
		Map<String,Object> map = new HashMap<String,Object>();
//				map.put("challengeNo", certiImg.getChallengeNo());
		
		map.put("challengeNo", certiImg.getChallengeNo());
		
		Challenge challenge = sqlSession.selectOne("ChallengeMapper.getChallenge",map);
		
		certiImg.setCategNo(challenge.getChallengeCategNo());
		
		sqlSession.insert("ChallengeMapper.insertCertiImg",certiImg);
		
		map.put("certiImgUp", "Up");
		map.put("certiImg", certiImg);
		//챌린지 참여자의 인증횟수 업데이트. map 사용
		sqlSession.update("ChallengeMapper.updateChallengeJoin",map);
		int certiCount = sqlSession.selectOne("ChallengeMapper.selectCertiCount",certiImg);
		int totalCertiCount = sqlSession.selectOne("ChallengeMapper.selectTotalCertiCount",certiImg);
		System.out.println("인증횟수 : "+certiCount);
		System.out.println("챌린지 총 인증횟수 : "+totalCertiCount);
		double percent = (double)certiCount / totalCertiCount * 100;
		percent = Math.round((percent*100)/100.0);
		System.out.println("달성률 : "+percent);
		map.put("percent", percent);
		
		sqlSession.update("ChallengeMapper.updateChallengeJoin",map);
	}

	@Override
	public List<CertiImg> getChallengeJoinCertiImgList(Map<String, Object> map) {
		List<CertiImg> list = 
				sqlSession.selectList("ChallengeMapper.getChallengeJoinCertiImgList",map);
		return list;
	}

	@Override
	public Map<String,Object> getChallengeCertiImgList(Map<String, Object> map) {
		
		User user = (User)map.get("user");
		
		if(user!=null) {
			
				System.out.println("로그인");
				List<String> black = new ArrayList<String>();
				
				List<String> targetEmail = sqlSession.selectList("ChallengeMapper.getFbTarget",map);
				List<String> userEmail = sqlSession.selectList("ChallengeMapper.getFbUser",map);
				
				if(targetEmail != null) {
					for(int i=0; i<targetEmail.size(); i++) {
						black.add(targetEmail.get(i));
					}
				}
				if(userEmail != null) {
					for(int i=0; i<userEmail.size(); i++) {
						black.add(userEmail.get(i));
					}
				}
				
				System.out.println("black : "+black);
				
				map.put("black", black);
			
				List<CertiImg> certiImgList = 
						sqlSession.selectList("ChallengeMapper.getChallengeCertiImgList",map);
				
				map.put("certiImgList", certiImgList);
				
				List<Review> likeAndDislikeList =
						sqlSession.selectList("ChallengeMapper.getChallengeReviewLikeAndDislike",map);
				System.out.println("likeAndDislike : "+likeAndDislikeList);
				
				List<CertiImg> resultCertiImgList = new ArrayList<CertiImg>();
				
		   retry:for(int i=0; i<certiImgList.size(); i++) {
					for(int j=0; j<likeAndDislikeList.size();j++) {
						if(certiImgList.get(i).getCertiImgNo() == 
								likeAndDislikeList.get(j).getCertiImgNo()) {
							certiImgList.get(i).setLikeAndDislikeFlag(likeAndDislikeList.get(j).getStatus());
							resultCertiImgList.add(certiImgList.get(i));
							continue retry;
						}
					}
					
					resultCertiImgList.add(certiImgList.get(i));
				}
				
				
				
				int totalCount = sqlSession.selectOne("ChallengeMapper.getChallengeCertiImgListCount",map);
				map.put("certiImgList", resultCertiImgList);
				map.put("totalCount", totalCount);
				//
		}else {
				List<CertiImg> certiImgList = 
					sqlSession.selectList("ChallengeMapper.getChallengeCertiImgList",map);
				System.out.println("비로그인");
				int totalCount = sqlSession.selectOne("ChallengeMapper.getChallengeCertiImgListCount",map);
				map.put("certiImgList", certiImgList);
				map.put("totalCount", totalCount);
		}
		
		
		
		return map;
	}

	@Override
	public CertiImg getChallengeCertiImg(Map<String,Object> map) {
		int certiImgNo = (Integer)map.get("certiImgNo");
		User user = (User)map.get("user");
		CertiImg certiImg = new CertiImg();
		Review review = new Review();
		if(user != null) {
			certiImg = sqlSession.selectOne("ChallengeMapper.getChallengeCertiImg",certiImgNo);
			review = sqlSession.selectOne("ChallengeMapper.getChallengeLikeDisLike",map);
			System.out.println("Review : "+review);
			if(review != null) {
				certiImg.setLikeAndDislikeFlag(review.getStatus());	
			}
		}else {
			certiImg = sqlSession.selectOne("ChallengeMapper.getChallengeCertiImg",certiImgNo);
		}
		
		return certiImg;
	}

	@Override
	public void addChallengeReview(Review review) {
		
		if(review.getStatus().equals("0")) {
			
			sqlSession.insert("ChallengeMapper.insertChallengeReview",review);
			
		}else{
			
			sqlSession.insert("ChallengeMapper.insertChallengeReview",review);
			sqlSession.update("ChallengeMapper.updateCertiImgLikeAndDislike",review);
		}
		
	}

	@Override
	public void deleteChallengeReview(int reviewNo) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("reviewNo", reviewNo);
		
		sqlSession.delete("ChallengeMapper.deleteChallengeReview",map);
		
	}

	@Override
	public void updateChallengeReview(Review review) {
		sqlSession.update("ChallengeMapper.updateChallengeReview",review);
	}

	@Override
	public List<Review> getChallengeCommentList(Map<String,Object> map) {
		List<Review> commentList = sqlSession.selectList("ChallengeMapper.getChallengeCommentList",map);
		return commentList;
	}

	@Override
	public void deleteChallengeCertiImg(Map<String, Object> map) {
		
		int certiImgNo = (Integer)map.get("certiImgNo");
		CertiImg certiImg = sqlSession.selectOne("ChallengeMapper.getChallengeCertiImg",certiImgNo);
		
		map.put("certiImg", certiImg);
		//인증이미지와 관련된 review를 전부 삭제
		sqlSession.delete("ChallengeMapper.deleteChallengeReview",map);
		//인증이미지 삭제
		sqlSession.delete("ChallengeMapper.deleteChallengeCertiImg",map);
		
		System.out.println("userEmail : "+certiImg.getUser().getUserEmail());
		System.out.println("challengeNo : "+certiImg.getChallengeNo());
		//인증 횟수 차감
		sqlSession.update("ChallengeMapper.updateChallengeJoin",map);
		
		int certiCount = sqlSession.selectOne("ChallengeMapper.selectCertiCount",certiImg);
		int totalCertiCount = sqlSession.selectOne("ChallengeMapper.selectTotalCertiCount",certiImg);
		System.out.println("인증횟수 : "+certiCount);
		System.out.println("챌린지 총 인증횟수 : "+totalCertiCount);
		double percent = (double)certiCount / totalCertiCount * 100;
		System.out.println("math쓰기전 : "+percent);
		percent = Math.round((percent*100)/100.0);
		System.out.println("달성률 : "+percent);
		map.put("percent", percent);
		
		sqlSession.update("ChallengeMapper.updateChallengeJoin",map);
	}

	@Override
	public int getChallengePick(Map<String,Object> map) {
		
		int pickCount = sqlSession.selectOne("ChallengeMapper.getChallengePick",map);
		
		return pickCount;
	}

	@Override
	public void updateChallengeStatus(Map<String, Object> map) {
		sqlSession.update("ChallengeMapper.updateChallengeStatus",map);
	}

	@Override
	public JoinChallenger getChallengeJoiner(Map<String, Object> map) {
		JoinChallenger joinChallenger = sqlSession.selectOne("ChallengeMapper.getChallengeJoiner",map);
		return joinChallenger;
	}

	@Override
	public List<JoinChallenger> getChallengeJoinerList(int challengeNo) {
		List<JoinChallenger> list = 
		sqlSession.selectList("ChallengeMapper.getChallengeJoinerList",challengeNo);
		return list;
	}

	@Override
	public Map<String, Object> getChallengeListFriend(Map<String, Object> map) {
		
		System.out.println("search dao : "+map.get("search"));
		
		map.put("black", "black");
		
		List<String> targetEmail = sqlSession.selectList("ChallengeMapper.getFbTarget",map);  
		List<String> userEmail = sqlSession.selectList("ChallengeMapper.getFbUser",map);      
		List<String> friend = new ArrayList<String>();
		
		if(targetEmail != null) {
			for(int i=0; i<targetEmail.size(); i++) {
				friend.add(targetEmail.get(i));
			}
		}
		if(userEmail != null) {
			for(int i=0; i<userEmail.size(); i++) {
				friend.add(userEmail.get(i));
			}
		}
		
		System.out.println("friend : "+friend);
		
		map.put("friend", friend);
		
		List<Challenge> list = sqlSession.selectList("ChallengeMapper.getChallengeListFriend",map);
		int totalCount = sqlSession.selectOne("ChallengeMapper.getChallengeListFriendTotal",map);
		map.put("totalCount", totalCount);
		map.put("list", list);
		return map;
	}

	@Override
	public List<Review> getChallengeMoreCommentList(Map<String, Object> map) {
		
		List<Review> commentList = sqlSession.selectList("ChallengeMapper.getChallengeMoreCommentList",map);
		return commentList;
		
	}

	@Override
	public void deleteChallengeReviewLike(Map<String, Object> map) {
		int reviewNo = sqlSession.selectOne("ChallengeMapper.getDeleteReviewNo",map);
		System.out.println("reviewNo : "+reviewNo);
		map.put("reviewNo", reviewNo);
		sqlSession.delete("ChallengeMapper.deleteChallengeReview",map);
		Review review = (Review)map.get("review");
		System.out.println("DAO review : "+review );
		sqlSession.update("ChallengeMapper.updateCertiImgLikeAndDislike",review);
	}

	@Override
	public Map<String, Object> getChallengeRewardElement(JoinChallenger joinChallenger) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("joinChallenger", joinChallenger);
		map.put("sixty", "60");
		int sixtyCount = sqlSession.selectOne("ChallengeMapper.getChallengeRewardElement",map);
		map.remove("sixty");
		map.put("sixtyCount", sixtyCount);
		
		map.put("seventy", "70");
		int seventyCount = sqlSession.selectOne("ChallengeMapper.getChallengeRewardElement",map);
		map.remove("seventy");
		map.put("seventyCount", seventyCount);
		
		map.put("eighty", "80");
		int eightyCount = sqlSession.selectOne("ChallengeMapper.getChallengeRewardElement",map);
		map.remove("eighty");
		map.put("eightyCount", eightyCount);
		
		map.put("ninety", "90");
		int ninetyCount = sqlSession.selectOne("ChallengeMapper.getChallengeRewardElement",map);
		map.remove("ninety");
		map.put("ninetyCount",ninetyCount);
		
		map.put("hundred","100");
		int hundredCount = sqlSession.selectOne("ChallengeMapper.getChallengeRewardElement",map);
		map.put("hundredCount", hundredCount);
		
		map.put("challengeNo",joinChallenger.getChallengeNo());
		
		Challenge challenge = sqlSession.selectOne("ChallengeMapper.getChallenge",map);
		
		map.put("challenge", challenge);
		
		return map;
	}

	@Override
	public void updateChallengerewardFlag(JoinChallenger joinChallenger) {
		sqlSession.update("ChallengeMapper.updateChallengeRewardFlag",joinChallenger);
		
	}

	@Override
	public Map<String,Object> getChallengeMoreCertiImgList(Map<String, Object> map) {
User user = (User)map.get("user");
		
		if(user!=null) {
			
				System.out.println("로그인");
				List<String> black = new ArrayList<String>();
				
				List<String> targetEmail = sqlSession.selectList("ChallengeMapper.getFbTarget",map);
				List<String> userEmail = sqlSession.selectList("ChallengeMapper.getFbUser",map);
				
				if(targetEmail != null) {
					for(int i=0; i<targetEmail.size(); i++) {
						black.add(targetEmail.get(i));
					}
				}
				if(userEmail != null) {
					for(int i=0; i<userEmail.size(); i++) {
						black.add(userEmail.get(i));
					}
				}
				
				System.out.println("black : "+black);
				
				map.put("black", black);
			
				List<CertiImg> certiImgList = 
						sqlSession.selectList("ChallengeMapper.getChallengeMoreCertiImgList",map);
				
				map.put("certiImgList", certiImgList);
				
				List<Review> likeAndDislikeList =
						sqlSession.selectList("ChallengeMapper.getChallengeReviewLikeAndDislike",map);
				System.out.println("likeAndDislike : "+likeAndDislikeList);
				
				List<CertiImg> resultCertiImgList = new ArrayList<CertiImg>();
				
		   retry:for(int i=0; i<certiImgList.size(); i++) {
					for(int j=0; j<likeAndDislikeList.size();j++) {
						if(certiImgList.get(i).getCertiImgNo() == 
								likeAndDislikeList.get(j).getCertiImgNo()) {
							certiImgList.get(i).setLikeAndDislikeFlag(likeAndDislikeList.get(j).getStatus());
							resultCertiImgList.add(certiImgList.get(i));
							continue retry;
						}
					}
					
					resultCertiImgList.add(certiImgList.get(i));
				}
				
				
				
				int totalCount = sqlSession.selectOne("ChallengeMapper.getChallengeCertiImgListCount",map);
				map.put("certiImgList", resultCertiImgList);
				map.put("totalCount", totalCount);
				//
		}
		
		
		
		return map;
	}

	
	@Override
	public Map<String, Object> getChallengeTargetList(JoinChallenger joinChallenger) {
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		List<Challenge> list3 = sqlSession.selectList("ChallengeMapper.getChallengeTargetList",joinChallenger);
		
		map.put("list3",list3);
		
		return map;
	}
}
