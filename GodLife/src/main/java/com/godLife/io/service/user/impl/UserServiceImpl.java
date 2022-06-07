package com.godLife.io.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.godLife.io.service.user.UserService;
import com.godLife.io.common.Search;
import com.godLife.io.service.domain.FriendBlack;
import com.godLife.io.service.domain.Msg;
import com.godLife.io.service.domain.User;
import com.godLife.io.service.user.UserDao;

//==> 회원관리 서비스 구현
@Service("userServiceImpl")
public class UserServiceImpl implements UserService{

	///Field
	@Autowired
	@Qualifier("userDaoImpl")
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	///Constructor
	public UserServiceImpl() {
		System.out.println(this.getClass());
	}
	
	///Method
	public void addUser(User user) throws Exception {
		userDao.addUser(user);
	}
	
	public User getUser(String userEmail) throws Exception {
		return userDao.getUser(userEmail);
	}
	
	public void updatePwd(User user) throws Exception {
		userDao.updatePwd(user);
	}
	
	public void updateUser(User user) throws Exception {
		userDao.updateUser(user);
	}
	
	public Map<String , Object > getFriendBlackList(Search search, String userEmail) throws Exception {
//		List<FriendBlack> list= userDao.getFriendBlackList(search, userEmail);
//		int totalCount = userDao.getTotalCount(search);
		
		Map<String, Object> map = userDao.getFriendBlackList(search, userEmail);
//		map.put("list", list );
//		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	public Map<String , Object > getFriendRequestList(Search search, String targetEmail) throws Exception {
//		List<FriendBlack> list= userDao.getFriendBlackList(search, userEmail);
//		int totalCount = userDao.getTotalCount(search);
		
		Map<String, Object> map = userDao.getFriendRequestList(search, targetEmail);
//		map.put("list", list );
//		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	
	public void addFriend(FriendBlack friendBlack) throws Exception {
		userDao.addFriend(friendBlack);
	}
	
	public void addBlack(FriendBlack friendBlack) throws Exception {
		userDao.addBlack(friendBlack);
	}
	
	public void updateAccStatus(FriendBlack friendBlack) throws Exception {
		userDao.updateAccStatus(friendBlack);
	}
	
	public void deleteFriend(FriendBlack friendBlack) throws Exception {
		userDao.deleteFriend(friendBlack);
	}
	
	public void addMsg(Msg msg) throws Exception {
		userDao.addMsg(msg);
	}
	
	public Msg getRecvMsg(String recvEmail) throws Exception {
		return userDao.getRecvMsg(recvEmail);
	}
	
	public Msg getSendMsg(String sendEmail) throws Exception {
		return userDao.getSendMsg(sendEmail);
	}
	
	public void deleteMsg(Msg msg) throws Exception {
		userDao.deleteMsg(msg);
	}
	
	public void updateUserTotalPoint(User user) throws Exception{
		userDao.updateUserTotalPoint(user);
	}
	
	public void updateUserRedCouponCount(User user) throws Exception{
		userDao.updateUserRedCouponCount(user);
	}
	
	public void updateUserCertiCouponCount(User user) throws Exception{
		userDao.updateUserCertiCouponCount(user);
	}
	
	
	
	
}
