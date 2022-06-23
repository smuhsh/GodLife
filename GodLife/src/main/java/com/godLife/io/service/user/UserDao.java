package com.godLife.io.service.user;

import com.godLife.io.service.domain.FriendBlack;
import com.godLife.io.service.domain.Msg;
import com.godLife.io.service.domain.OneInq;
import com.godLife.io.service.domain.Report;
import com.godLife.io.service.domain.User;

import java.util.List;
import java.util.Map;

import com.godLife.io.common.Search;

//==> 회원관리에서 CRUD 추상화/캡슐화한 DAO Interface Definition
public interface UserDao {
	
	////////////////////////////회원관리//////////////////
	
	// 회원가입
	public void addUser(User user) throws Exception ;
	
	// 본인정보 상세조회, 로그인 
	public User getUser(String userEmail) throws Exception ;
	
	// 카카오 로그인 학인	
	public int  getUserKakao(User user) throws Exception ;
	
	// 비밀번호 변경 
	public void updatePwd(User user) throws Exception ;
	
    // 본인정보 수정 
	public void updateUser(User user) throws Exception ;
	
    // 유저 전체 목록조회(관리자) 
	public List<User> getUserList(Search search) throws Exception ;
	
	//아이디 찾기 
	public String findUserEmail(String phone) throws Exception;
	
	//비밀번호 찾기 
	public int findUserPwd(Map<String, String> map) throws Exception;
	

//	// 핸드폰번호로 아이디, 비번찾기  
//	public User findUserPhone(String phone) throws Exception;
//	
//	// 이메일로 비번찾기  
//	public User findUserEmail(String userEmail)throws Exception;
	
	// 유저상세조회
	
	// id 중복체크
	public int checkUserEmail(String userEmail) throws Exception;
	// 닉네임 중복체크
	public int checkNick(String nick) throws Exception;
	
	//핸드폰 중복체크 
	public int checkPhone(String phone) throws Exception;
	
	////////////////////////////친구,블랙리스트 관리//////////////////
	
	// 본인의 친구 목록조회 
	public Map<String, Object> getFriendList(Search search, String userEmail) throws Exception ;
	
	// 본인의 블랙리스트 목록조회 
	public Map<String, Object> getBlackList(Search search, String userEmail) throws Exception ;
		
	// 친구요청받은 목록조회 
	public Map<String, Object> getFriendRequestList(Search search, String targetEmail) throws Exception ;
	
	// 친구 등록 
	public void addFriend(FriendBlack friendBlack) throws Exception ;
	
	// 블랙리스트 등록 
	public void addBlack(FriendBlack friendBlack) throws Exception ;
		
	// 친구요청 수락 >  acc_status 변경  
	public void updateAccStatus(FriendBlack friendBlack) throws Exception ;
	
	// 친구요청 거절
	public void deleteFriendRequest(FriendBlack friendBlack) throws Exception ;
	
	//친구 삭제 
	public void deleteFriend(int friendBlackNo) throws Exception ;
	
	//블랙리스트 삭제
	public void deleteBlack(int friendBlackNo) throws Exception ;
	
	// 친구신청 중복검사
     public int checkFriend(Map<String, String> map);
     
 	// 블랙리스트 신청 중복검사
     public int checkBlack(Map<String, String> map);
     
     
	
	////////////////////////////쪽지 관리//////////////////
	
	//쪽지 전송
	public void addMsg(Msg msg) throws Exception ;
	
	// 받은 쪽지 상세조회(다시확인)
	public Msg getRecvMsg(int msgNo) throws Exception ;
	
	// 보낸 쪽지 상세조회(다시확인)
	public Msg getSendMsg(int msgNo) throws Exception ;
	
	// 받은, 보낸 쪽지 삭제 
    public void deleteMsg(int msgNo) throws Exception ;
    
    // 받은 쪽지 목록조회 
    public Map<String, Object> getRecvMsgList(Search search, String recvEmail) throws Exception ;
    
    // 보낸 쪽지 목록조회 
    public Map<String, Object> getSendMsgList(Search search, String sendEmail) throws Exception ;
    
    //쪽지 블랙리스트 못보내게 유효성 검사 
    public int checkMsgBlack(Map<String, String> map);
    
    
	////////////////////////////일대일문의 //////////////////
    
    // 일대일 문의 등록 
    public void addOneInq(OneInq oneInq) throws Exception ;
    
    // 일대일 문의 목록조회 (회원, 관리자) 
    public Map<String, Object> getOneInqList(Search search, String userEmail) throws Exception ;
    
    // 일대일 문의 상세조회 
    public OneInq getOneInq(int oneInqNo) throws Exception ;
    
    // 일대일 문의 삭제 
    public void deleteOneInq(int oneInq) throws Exception ;
    
    // 일대일 문의 수정(회원) 
    public void updateOneInq(OneInq oneInq) throws Exception ;
    
    //일대일 문의 답변 작성(관리자) 
    public void updateOneInqComment(OneInq oneInq) throws Exception ;
    
    
////////////////////////////신고 ////////////////////////////////////////////
    
    // 쪽지 신고 등록 
    public void addUserReport(Report report) throws Exception ;
    
  //쪽지 신고 중복방지 
    public int checkMsgReport(Report report) throws Exception;
    
    //인증이미지 신고 중복방지 
    public int checkCertiImgReport(Report report) throws Exception;
    
    //댓글 신고 중복방지 
    public int checkCommentReport(Report report) throws Exception;
    
    
    
    //신고 유저 목록조회(관리자) 
    public List<User> getUserReportList(Search search) throws Exception ;
    
    //신고 유저 상세 목록조회(관리자) 
    public Map<String, Object> getUserReport(Search search, String targetEmail) throws Exception ;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public void updateUserRedCouponCountUse(User user) throws Exception;
    
    //레드카드 발급, 제거 
    public void updateRedCard(User user) throws Exception; 
    
    
    // 병문오빠 추가 
    public void updateUserRedCouponCount(User user) throws Exception;
    // 병문오빠 추가 
    public void updateUserTotalPoint(User user) throws Exception;
    // 병문오빠 추가 
    public void updateUserCertiCouponCount(User user) throws Exception;
    
 //========================================================================================   
    
	// 게시판 Page 처리를 위한 전체Row(totalCount) return, 유저 전체 목록조회(관리자) 
	public int getTotalCount(Search search) throws Exception ;
	
	// 게시판 Page 처리를 위한 전체Row(totalCount) return, 받은 쪽지 목록조회 
	public int getUserRecvMsgTotalCount(Search search, String recvEmail) throws Exception ;
	
	// 게시판 Page 처리를 위한 전체Row(totalCount) return, 나의 친구 목록조회
	public int getUserFriendListTotalCount(Search search, String userEmail) throws Exception ;
	
	// 게시판 Page 처리를 위한 전체Row(totalCount) return, 나의 친구 요청 목록조회
	public int getUserFriendRequestListTotalCount(Search search, String targetEmail) throws Exception ;
	
	// 게시판 Page 처리를 위한 전체Row(totalCount) return, 나의 블랙리스트 목록조회
	public int getUserBlackListTotalCount(Search search, String userEmail) throws Exception ;
	
	// 게시판 Page 처리를 위한 전체Row(totalCount) return, 보낸 쪽지 목록조회 
	public int getUserSendMsgTotalCount(Search search, String sendEmail) throws Exception ;
	
	// 게시판 Page 처리를 위한 전체Row(totalCount) return, 신고 유저 목록조회(관리자) 
	public int getUserReportListTotalCount(Search search) throws Exception ;
	
	// 게시판 Page 처리를 위한 전체Row(totalCount) return, 신고 유저 상세목록조회(관리자) 
	public int getUserReportTotalCount(Search search, String targetEmail) throws Exception ;

}
