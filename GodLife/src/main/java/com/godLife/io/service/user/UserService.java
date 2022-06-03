package com.godLife.io.service.user;

import java.util.Map;

import com.godLife.io.service.domain.FriendBlack;
import com.godLife.io.service.domain.Msg;
import com.godLife.io.service.domain.User;
import com.godLife.io.common.Search;

//==> 회원관리에서 서비스할 내용 추상화/캡슐화한 Service  Interface Definition 
public interface UserService {

	// 회원가입
	public void addUser(User user) throws Exception;
	
	// 본인정보확인 
	public User getUser(String userEmail) throws Exception;
	
	// 비밀번호 수정 
	public void updatePwd(User user) throws Exception;
	
	// 본인정보 수정 
	public void updateUser(User user) throws Exception;
	
	// 친구, 블랙리스트 목록 리스트 
	public Map<String , Object> getFriendBlackList(Search search, String userEmail) throws Exception;
	
	// 친구요청 
    public Map<String, Object> getFriendRequestList(Search search, String targetEmail) throws Exception ;
	
	// 친구 등록 
	public void addFriend(FriendBlack friendBlack) throws Exception;
	
	// 블랙리스트 등록 
	public void addBlack(FriendBlack friendBlack) throws Exception;
	
	//친구 요청수락 
	public void updateAccStatus(FriendBlack friendBlack) throws Exception;
	
	//친구 요청 거절   
	public void deleteFriend(FriendBlack friendBlack) throws Exception;
	
	//쪽지 보내기 
	public void addMsg(Msg msg)throws Exception;
	
	// 받은 쪽지 상세조회
	public Msg getRecvMsg(String recvEmail) throws Exception ;
	
	// 보낸 쪽지 상세조회
	public Msg getSendMsg(String sendEmail) throws Exception ;
	
	// 받은, 보낸 쪽지 삭제 
    public void deleteMsg(Msg msg) throws Exception ;
	
		
		
		
		

}
