package com.godLife.io.service.user;

import com.godLife.io.service.domain.FriendBlack;
import com.godLife.io.service.domain.Msg;
import com.godLife.io.service.domain.User;

import java.util.List;
import java.util.Map;

import com.godLife.io.common.Search;

//==> 회원관리에서 CRUD 추상화/캡슐화한 DAO Interface Definition
public interface UserDao {
	
	// 회원가입
	public void addUser(User user) throws Exception ;
	
	// 본인정보 상세조회
	public User getUser(String userEmail) throws Exception ;
	
	// 비밀번호 변경 
	public void updatePwd(User user) throws Exception ;
	
   // 본인정보 수정 
	public void updateUser(User user) throws Exception ;
	
	// 친구블랙리스트 목록조회(아직 블랙리스트는 안됨) 
	public Map<String, Object> getFriendBlackList(Search search, String userEmail) throws Exception ;
	
	// 친구요청 
	public Map<String, Object> getFriendRequestList(Search search, String targetEmail) throws Exception ;
	
	// 친구 등록 
	public void addFriend(FriendBlack friendBlack) throws Exception ;
	
	// 블랙리스트 등록 
	public void addBlack(FriendBlack friendBlack) throws Exception ;
		
	// 친구요청 수락 >  acc_status 변경  
	public void updateAccStatus(FriendBlack friendBlack) throws Exception ;
	
	// 친구요청 거절 >  친구블랙리스트 테이블에서 삭제 
	public void deleteFriend(FriendBlack friendBlack) throws Exception ;
	
	//쪽지 보내기
	public void addMsg(Msg msg) throws Exception ;
	
	// 받은 쪽지 상세조회
	public Msg getRecvMsg(String recvEmail) throws Exception ;
	
	// 보낸 쪽지 상세조회
	public Msg getSendMsg(String sendEmail) throws Exception ;
	
	// 받은, 보낸 쪽지 삭제 
    public void deleteMsg(Msg msg) throws Exception ;
    
    
    
    // 병문오빠 추가 
    public void updateUserRedCouponCount(User user) throws Exception;
    // 병문오빠 추가 
    public void updateUserTotalPoint(User user) throws Exception;
    // 병문오빠 추가 
    public void updateUserCertiCouponCount(User user) throws Exception;
    
    
    
	
	// 게시판 Page 처리를 위한 전체Row(totalCount)  return
	public int getTotalCount(Search search) throws Exception ;
	
	

	


}
