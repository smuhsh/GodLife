package com.godLife.io.service.challenge.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.godLife.io.common.ChallengeUtil;
import com.godLife.io.common.Page;
import com.godLife.io.common.Search;
import com.godLife.io.service.challenge.ChallengeService;
import com.godLife.io.service.domain.CertiImg;
import com.godLife.io.service.domain.Challenge;
import com.godLife.io.service.domain.JoinChallenger;
import com.godLife.io.service.domain.Review;
import com.godLife.io.service.domain.User;
import com.godLife.io.service.user.UserService;


/*
 *	FileName :  UserServiceTest.java
 * ㅇ JUnit4 (Test Framework) 과 Spring Framework 통합 Test( Unit Test)
 * ㅇ Spring 은 JUnit 4를 위한 지원 클래스를 통해 스프링 기반 통합 테스트 코드를 작성 할 수 있다.
 * ㅇ @RunWith : Meta-data 를 통한 wiring(생성,DI) 할 객체 구현체 지정
 * ㅇ @ContextConfiguration : Meta-data location 지정
 * ㅇ @Test : 테스트 실행 소스 지정
 */
@RunWith(SpringJUnit4ClassRunner.class)

//==> Meta-Data 를 다양하게 Wiring 하자...
//@ContextConfiguration(locations = { "classpath:config/context-*.xml" })
@ContextConfiguration	(locations = {	"classpath:config/context-common.xml",
																	"classpath:config/context-aspect.xml",
																	"classpath:config/context-mybatis.xml",
																	"classpath:config/context-transaction.xml" })
//@ContextConfiguration(locations = { "classpath:config/context-common.xml" })
public class ChallengeServiceTest {

	//==>@RunWith,@ContextConfiguration 이용 Wiring, Test 할 instance DI
	@Autowired
	@Qualifier("challengeServiceImpl")
	private ChallengeService challengeService;
	
	@Autowired
	@Qualifier("userServiceImpl")
	private UserService userService;
	
	@Value("#{commonProperties['pageUnit']}")
	int pageUnit;
	@Value("#{commonProperties['pageSize']}")
	int pageSize;
	
	
	//@Test
	public void addChallenge() throws Exception {
		Challenge challenge = new Challenge();
		//데이터 넣을때 닉네임 같이 넣자.
		
		
		
		List<String> certiCycle = new ArrayList<String>();
		certiCycle.add("1");//일
		//certiCycle.add("2");//월
		certiCycle.add("3");//화
		//certiCycle.add("4");//수
		//certiCycle.add("5");//목
		//certiCycle.add("6");//금
		//certiCycle.add("7");//토
		
		challenge.setHostEmail("user04@io.com");//HostEmail
		User user = userService.getUser(challenge.getHostEmail());
		challenge.setHostNick(user.getNick());
		challenge.setChallengeTitle("테스트 챌린지"); // 챌린지 제목
		challenge.setChallengeThumbnailImg("챌린지.jpg");//썸네일 이미지
		challenge.setChallengeDetail("테스트 챌린지입니다.");// 소개
		challenge.setChallengeRule("챌린지."); // 규칙
		challenge.setChallengeCategNo(1); // 관심사 번호 1:운동
		challenge.setStartDate("2022-06-08"); // 시작 날짜
		challenge.setEndDate("2022-06-18"); // 종료 날짜
		challenge.setCertiCycle(certiCycle); // 인증주기
		challenge.setOpenRange("1");//0이면 전체 1이면 친구
		challenge.setJoinPoint(1000);// 입장 포인트
		challenge.setJoinCount(1);//호스트 기본참여자명
		challenge.setChallengeStatus("0");// 0 시작전 1 진행중 2 종료
		challenge = ChallengeUtil.certiCycle(challenge); // 인증 날짜 및 총 인증 횟수
		
		
		System.out.println(challenge.getTotalCertiCount());
		for(int i=0; i<challenge.getCertiDate().size(); i++) {
			System.out.println("인증 날짜 : "+challenge.getCertiDate().get(i)); //인증 날짜
		}
		
		JoinChallenger joinChallenger = new JoinChallenger();
		joinChallenger.setEmail(challenge.getHostEmail());
		joinChallenger.setStatus("0");
		
		
		
		challengeService.addChallenge(challenge,joinChallenger);
		
	}
	
	@Test
	public void getChallengeList() { //전체 목록 조회
		Search search = new Search();
		
		
		search.setCurrentPage(3);
		
		search.setPageSize(pageSize);
		
		//search.setOrderCondition(2); // 1 운동
		//search.setSearchCondition("2");//1 신규 2 인기
		//search.setSearchKeyword("챌");
		
		System.out.println(search.getStartRowNum()+"  "+search.getEndRowNum());
		
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		User user = new User(); //로그인
		//User user = null; //비로그인
		//비로그인 이라면 아랫줄 주석
		user.setUserEmail("user01@io.com");
		//////////////////////////////////////////////
		
		//ChallengeList 옵션
		String challengeListOpt = "total"; // 전체목록 조회 defalut = total // 비로그인은 total 고정
		//String challengeListOpt = "pick"; //찜목록
		//String challengeListOpt = "add"; // 등록 목록
		//String challengeListOpt = "join"; // 참여 목록

		
		map.put("search", search);
		System.out.println("searchKeyword: "+search.getSearchKeyword());
		map.put("user", user);
		map.put("challengeListOpt", challengeListOpt);
		map = challengeService.getChallengeList(map); // 리스트 정보 포함
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		List challengeList = (List)map.get("list");
		
		System.out.println("total : "+map.get("totalCount"));
		System.out.println("챌린지 리스트");
		for(Object challenge : challengeList) {
			System.out.println(challenge);
		}
		
		
		
		//Map<String , Object> map=userService.getUserList(search);
	}
	
	
	//@Test
	public void getChallenge() throws Exception{
		int challengeNo = 10017; // hostEmail : chilee4650@naver.com
		
		//회원조회
		User user = new User();
		user.setUserEmail("chilee4650@naver.com"); //
		
		//비회원 조회
//		User user = null;
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("user",user);
		map.put("challengeNo", challengeNo);
		
		
		Challenge challenge = challengeService.getChallenge(map); // user는 참여자 인지 판별 
		
		
//		User hostUser = userService.getUser(challenge.getHostEmail()); //hostNick를 가져오기 위함.
//		challenge.setHostNick(hostUser.getNick());
		
		System.out.println("Challenge 상세정보");
		System.out.println(challenge);
		
		System.out.println("호스트 닉네임");
		System.out.println(challenge.getHostNick());
		
		System.out.println("Challenge 인증주기");
		for(String list : challenge.getCertiCycleName()) {
			System.out.print(list);
		}
		
		System.out.println();
		System.out.println("인증 날짜");
		for(String list : challenge.getCertiDate()) {
			System.out.println(list);
		}
		
		System.out.println("참여 여부");
		if(challenge.getChallengeJoinFlag().equals("1")) {
			System.out.println("참여");
		}else {
			System.out.println("참여 안함.");
		}
		
		
	}
	
	@Test
	public void deleteChallenge() {
		//10007 ~ 10011
		
		int challengerNo = 10022;
		
		Map<String,Object> map = challengeService.deleteChallenge(challengerNo);
		
		List<String> challengeJoinList = (List)map.get("challengeJoinList");
		
		
		//환불 금액
		System.out.println("환불 금액");
		System.out.println(map.get("challengeJoinPoint"));
		
		System.out.println("삭제 된 챌린지에 참여했었던 회원 명단");
		for(String challengeJoiner : challengeJoinList) {
			System.out.println(challengeJoiner);
		}
		
	}
	
	
	//@Test
	public void addChallengeJoinAndPick() {
		
		int challengeNo = 10012;
		JoinChallenger joinChallenger = new JoinChallenger(); 
		joinChallenger.setChallengeNo(challengeNo);
		joinChallenger.setEmail("chilee4650@naver.com");
		joinChallenger.setStatus("0"); //0이면 참여 / 1이면 찜 
		
		challengeService.addChallengeJoin(joinChallenger);
		
		
	}
	
	//@Test
	public void deleteChallengeJoinAndPick() {
		//챌린지 찜하기 / 참여 나가기 userEmail + challengeNo 필요
		
		JoinChallenger joinChallenger = new JoinChallenger();
		
		joinChallenger.setEmail("chilee4650@naver.com");
		joinChallenger.setChallengeNo(10010);
		joinChallenger.setStatus("0"); // 0 나가기 , 1 찜 삭제
		
		int challengeJoinPoint = challengeService.deleteChallengeJoin(joinChallenger);
		
		System.out.println(challengeJoinPoint);
		
		if(challengeJoinPoint != 0) {
			System.out.println(challengeJoinPoint+" 포인트가 환불되었습니다.");
		}else {
			System.out.println("찜목록에서 제거되었습니다.");
		}
		
	}
	
	//@Test
	public void addChallengeCertiImg() {//파일업로드는 view에서
		//10015
		CertiImg certiImg = new CertiImg();
		
		certiImg.setChallengeNo(10011);
		certiImg.setEmail("user02@io.com");
		certiImg.setCertiImg("user02.io.com.jpg");
		
		challengeService.addChallengeCertiImg(certiImg);
		
		
		
	}
	
	//@Test
	public void getChallengeJoinCertiImgList() {
		//내가 참여중인 챌린지 인증이미지 목록조회 겸 같이 참여중인 회원의 인증이미지 목록조회
		//검색 기능이 없음.
		int challengeNo = 10017;
		String email = "user01@io.com";// 내 이메일 넣으면 그게 내가 참여한 챌린지의 인증이미지.
		//getChallengeJoinCertiImg()
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("challengeNo", challengeNo);
		map.put("email",email);
		
		List<CertiImg> certiImgs = challengeService.getChallengeJoinCertiImgList(map);
		
		System.out.println("내가 참여중인(참여중인 회원) 챌린지 인증이미지 목록 ");
		for(CertiImg certiImg : certiImgs) {
			System.out.println(certiImg);
		}
		
	}
	
	
	//@Test
	public void getChallengeCertiImgList() {
		
		//로그인
		User user = new User();
		user.setUserEmail("chilee4650@naver.com");
		//비로그인
//		User user = null;
		
		Search search = new Search();
		
		
		search.setCurrentPage(1);
		
		search.setPageSize(pageSize);
		
		//search.setOrderCondition(4); // 1 운동
		//search.setSearchCondition("1");//1 신규 2 인기
		//search.setSearchKeyword("user"); // 닉네임 검색
		
		//String certiImgOpt = "my";
		String certiImgOpt = null;
		
		
		
		System.out.println(search.getStartRowNum()+"  "+search.getEndRowNum());
		
		
		Map<String,Object> map = new HashMap<String, Object>();
		
		map.put("certiImgOpt",certiImgOpt);
		map.put("search", search);
		map.put("user", user);
		
		
		map = challengeService.getChallengeCertiImgList(map);
		
		
		Page resultPage = new Page( search.getCurrentPage(), ((Integer)map.get("totalCount")).intValue(), pageUnit, pageSize);
		
		
		List<CertiImg> certiImgList = (List<CertiImg>)map.get("certiImgList");
		
		System.out.println("totalCount : "+map.get("totalCount"));
		System.out.println("인증이미지 목록");
		for(CertiImg certiImg : certiImgList) {
			System.out.println(certiImg);
		}
		
		
		
	}
	
	//@Test
	public void getChallengeCertiImg() {
		
		int certiImgNo = 10028; // 인증이미지 번호
		
		CertiImg certiImg = challengeService.getChallengeCertiImg(certiImgNo);
		
		System.out.println("인증이미지 상세 조회");
		System.out.println(certiImg);
		
		// 댓글은 ajax사용
		// 상세 조회 하려는 인증이미지 번호를 사용하여 ajax로 댓글을 불러올 예정이다.
		
	}
	
	
	//@Test
	public void addChallengeReview() {
		
		Review review = new Review();
		//review.setStatus("0");// 댓글
		review.setStatus("1");// 좋아요
		//review.setStatus("2");// 싫어요
		
		review.setCertiImgNo(10028); // 댓글 혹은 좋아요/싫어요를 등록할 인증이미지
		review.setEmail("user04@io.com");
		review.setComment("잘찍었네요");
		
		challengeService.addChallengeReview(review);
		
		
		
	}
	
	
	//@Test
	public void getChallengeCommentList() {
		
		int certiImgNo = 10028; // 댓글 목록을 가져올 인증이미지번호
		
		List<Review> commentList = challengeService.getChallengeCommentList(certiImgNo);
		
		System.out.println(certiImgNo+"번 인증이미지 댓글");
		for(Review comment : commentList) {
			System.out.println(comment.getNick()+":"+comment.getComment());
		}
		
	}
	
	//@Test
	public void updateChallengeReview() {
		
		Review review = new Review();
		
		review.setReviewNo(10006);
		review.setComment("진짜 별로인데");
		
		
		challengeService.updateChallengeReview(review);
		
	}
	
	
	//@Test
	public void deleteChallengeReview() {
		// deleteChallengeReview는 댓글 삭제만 지원한다.
		//(추후에 좋아요 싫어요 한번 더 누른다면 비활성화 하는것도 만들어 볼만...)
		
		int reviewNo = 10000;
		
		challengeService.deleteChallengeReview(reviewNo);
		
	}
	
	//@Test
	public void deleteChallengeCertiImg() {
		
		int certiImgNo = 10028; // hidden으로 같이 올라온 정보
		
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("certiImgNo", certiImgNo);
		
		challengeService.deleteChallengeCertiImg(map);
		
	}
	
}	
