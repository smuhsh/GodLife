package com.godLife.io.service.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import com.godLife.io.service.domain.FriendBlack;
import com.godLife.io.service.domain.Msg;
import com.godLife.io.service.domain.OneInq;
import com.godLife.io.service.domain.Report;
import com.godLife.io.service.domain.User;
import com.godLife.io.common.Search;

//==> 회원관리에서 서비스할 내용 추상화/캡슐화한 Service  Interface Definition 
public interface UserService {
	
	
	public String getAccessToken (String authorize_code);

	// 회원가입 *
	public void addUser(User user) throws Exception;
	
	// 본인정보확인, 로그인 *
	public User getUser(String userEmail) throws Exception;
	
	// 비밀번호 수정 
	public void updatePwd(User user) throws Exception;
	
	// 본인정보 수정 *
	public void updateUser(User user) throws Exception;
	
	//유저 전체 목록조회 *
	public Map<String , Object> getUserList(Search search) throws Exception;
	
	//아이디 찾기 
	public String findUserEmail(HttpServletResponse response, String phone) throws Exception;
	
//	// 핸드폰번호로 아이디, 비번찾기  *
//	public User findUserPhone(String phone) throws Exception;
//	
//	// 이메일로 비번찾기  *
//	public User findUserEmail(String userEmail)throws Exception;
	
	//비밀번호 찾기 
	public int findUserPwd(String phone, String userEmail)throws Exception;
	

	
	
	
	
	
		
    // 유저상세조회 > 쿼리 아직 완성 x
	
	// id 중복체크
	public int checkUserEmail(String userEmail) throws Exception;
	// 닉네임 중복체크
	public int checkNick(String nick) throws Exception;
	
	
	//인증문자 
	public void certifiedPhoneNumber(String userPhoneNumber, int randomNumber) throws Exception;
	
		
	//================친구, 블랙리스트 ======================================
	
	// 본인의 친구 목록조회 *
	public Map<String, Object> getFriendList(Search search, String userEmail) throws Exception ;
	
	// 본인의 블랙리스트 목록조회 *
	public Map<String, Object> getBlackList(Search search, String userEmail) throws Exception ;
	
	// 친구요청 *
    public Map<String, Object> getFriendRequestList(Search search, String targetEmail) throws Exception ;
	
	// 친구 등록 *
	public void addFriend(FriendBlack friendBlack) throws Exception;
	
	// 블랙리스트 등록 *
	public void addBlack(FriendBlack friendBlack) throws Exception;
	
	//친구 요청수락 *
	public void updateAccStatus(FriendBlack friendBlack) throws Exception;
	
	// 친구요청 거절 
	public void deleteFriendRequest(FriendBlack friendBlack) throws Exception ;
	
	//친구 삭제 
	public void deleteFriend(FriendBlack friendBlack) throws Exception ;
	
	// 친구신청 중복검사
	public int isAlreadyAppliedFriend(String userEmail, String targetEmail);
	
	
	
	
	
	//================쪽지 ======================================
	
	//쪽지 보내기 * 
	public void addMsg(Msg msg)throws Exception;
	
    // 받은 쪽지 상세조회(다시확인) *
	public Msg getRecvMsg(int msgNo) throws Exception ;
		
	// 보낸 쪽지 상세조회(다시확인) *
	public Msg getSendMsg(int msgNo) throws Exception ;
		
	// 받은, 보낸 쪽지 삭제 *
    public void deleteMsg(int msgNo) throws Exception ;
    
    // 받은 쪽지 목록조회 *
    public Map<String, Object> getRecvMsgList(Search search, String recvEmail) throws Exception ;
    
    // 보낸 쪽지 목록조회 *
    public Map<String, Object> getSendMsgList(Search search, String sendEmail) throws Exception ;
    
    
    //================일대일 문의 ======================================
    
    // 일대일 문의 등록 *
    public void addOneInq(OneInq oneInq) throws Exception ;
    
    // 일대일 문의 목록조회 (회원, 관리자) *
    public Map<String, Object> getOneInqList(Search search, String userEmail) throws Exception ;
    
    // 일대일 문의 상세조회 *
    public OneInq getOneInq(int oneInqNo) throws Exception ;
    
    // 일대일 문의 삭제 *
    public void deleteOneInq(int oneInq) throws Exception ;
    
    // 일대일 문의 수정(회원) *
    public void updateOneInq(OneInq oneInq) throws Exception ;
    
    //일대일 문의 답변 작성(관리자) *
    public void updateOneInqComment(OneInq oneInq) throws Exception ;
    
    
  //================신고 ======================================
    
    // 신고 등록 
    public void addReport(Report report) throws Exception ;

    // 병문오빠 추가건 
    public void updateUserTotalPoint(User user) throws Exception;
    
    public void updateUserRedCouponCount(User user) throws Exception;
    
    public void updateUserCertiCouponCount(User user) throws Exception;

    
  	
  	
    //신고 유저 목록조회(관리자) 
    
    //레드카드 발급, 제거 
    public void updateRedCard(User user) throws Exception; 
    
    
	
		
		
		
		

}
