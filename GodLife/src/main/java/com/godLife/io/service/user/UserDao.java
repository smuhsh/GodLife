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
	
	// 로그인 
	public User login(String userEmail)throws Exception;
	
	// 본인정보 상세조회
	public User getUser(String userEmail) throws Exception ;
	
	// 비밀번호 변경 
	public void updatePwd(User user) throws Exception ;
	
    // 본인정보 수정 
	public void updateUser(User user) throws Exception ;
	
    // 유저 전체 목록조회(관리자) 
	public List<User> getUserList(Search search) throws Exception ;
	
	// 핸드폰번호로 아이디, 비번찾기  
	public User findUserPhone(String phone) throws Exception;
	
	// 이메일로 비번찾기  
	public User findUserEmail(String userEmail)throws Exception;
	
	
	// 유저상세조회
	
	
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
	
	// 친구요청 거절, 삭제 >  친구블랙리스트 테이블에서 삭제 
	public void deleteFriend(FriendBlack friendBlack) throws Exception ;
	
	// 친구 중복 방지 
	
	
	
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
    
    
    
////////////////////////////신고 //////////////////
    
    // 신고 등록 
    public void addReport(Report report) throws Exception ;
    
    
    
    //신고 유저 목록조회(관리자) 
    
    
    //레드 카드 발급, 제거 
    
    
    
    
    
    // 병문오빠 추가 
    public void updateUserRedCouponCount(User user) throws Exception;
    // 병문오빠 추가 
    public void updateUserTotalPoint(User user) throws Exception;
    // 병문오빠 추가 
    public void updateUserCertiCouponCount(User user) throws Exception;
    
    
    
	
	// 게시판 Page 처리를 위한 전체Row(totalCount)  return
	public int getTotalCount(Search search) throws Exception ;
	
	

	


}
