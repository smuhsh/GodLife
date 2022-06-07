package com.godLife.io.service.user.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.godLife.io.service.user.UserService;
import com.fasterxml.jackson.core.JsonParser;
import com.godLife.io.common.Search;
import com.godLife.io.service.domain.FriendBlack;
import com.godLife.io.service.domain.Msg;
import com.godLife.io.service.domain.OneInq;
import com.godLife.io.service.domain.Report;
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
	
	//================회원=======================================================
	
	
	public void addUser(User user) throws Exception {
		userDao.addUser(user);
	}
	
	public User login(String userEmail) throws Exception {
		return userDao.login(userEmail);
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
	
	
	public Map<String , Object > getUserList(Search search) throws Exception {
		List<User> list= userDao.getUserList(search);
		int totalCount = userDao.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	public User findUserPhone(String phone) throws Exception {
		return userDao.findUserPhone(phone);
	}
	
	public User findUserEmail(String userEmail) throws Exception {
		return userDao.findUserEmail(userEmail);
	}
	
	
	//================친구, 블랙리스트=================================================
	
	public Map<String , Object > getFriendList(Search search, String userEmail) throws Exception {
		int totalCount = userDao.getTotalCount(search);
		
		Map<String, Object> map = userDao.getFriendList(search, userEmail);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	public Map<String , Object > getBlackList(Search search, String userEmail) throws Exception {
		int totalCount = userDao.getTotalCount(search);
		
		Map<String, Object> map = userDao.getBlackList(search, userEmail);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	
	public Map<String , Object > getFriendRequestList(Search search, String targetEmail) throws Exception {
		int totalCount = userDao.getTotalCount(search);
		
		Map<String, Object> map = userDao.getFriendRequestList(search, targetEmail);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	
	public void addFriend(FriendBlack friendBlack) throws Exception {
		// 불리언값으로 변경 ?? 
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
	
	
	//================쪽지================================================
	
	public void addMsg(Msg msg) throws Exception {
		userDao.addMsg(msg);
	}
	
	public Msg getRecvMsg(int msgNo) throws Exception {
		return userDao.getRecvMsg(msgNo);
	}
	
	public Msg getSendMsg(int msgNo) throws Exception {
		return userDao.getSendMsg(msgNo);
	}
	
	public void deleteMsg(int msgNo) throws Exception {
		userDao.deleteMsg(msgNo);
	}
	
	public Map<String , Object > getRecvMsgList(Search search, String recvEmail) throws Exception {
		int totalCount = userDao.getTotalCount(search);

		Map<String, Object> map = userDao.getRecvMsgList(search, recvEmail);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	public Map<String , Object > getSendMsgList(Search search, String sendEmail) throws Exception {
		int totalCount = userDao.getTotalCount(search);

		Map<String, Object> map = userDao.getSendMsgList(search, sendEmail);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	
	
	
	
	
	
	

	//================일대일문의================================================
	
	public void addOneInq(OneInq oneInq) throws Exception {
		userDao.addOneInq(oneInq);
	}
	
	public Map<String , Object > getOneInqList(Search search, String userEmail) throws Exception {
//		List<FriendBlack> list= userDao.getOneInqList(search, userEmail);
//		int totalCount = userDao.getTotalCount(search);
		
		Map<String, Object> map = userDao.getOneInqList(search, userEmail);
//		map.put("list", list );
//		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	public OneInq getOneInq(int oneInqNo) throws Exception {
		return userDao.getOneInq(oneInqNo);
	}
	
	public void deleteOneInq(int oneInq) throws Exception {
		userDao.deleteOneInq(oneInq);
	}
	
	public void updateOneInq(OneInq oneInq) throws Exception {
		userDao.updateOneInq(oneInq);
	}
	
	public void updateOneInqComment(OneInq oneInq) throws Exception {
		userDao.updateOneInqComment(oneInq);
	}
	
	//================신고================================================
	
	public void addReport(Report report) throws Exception {
		userDao.addReport(report);
	}

	@Override
	public String getAccessToken(String authorize_code) {
		// TODO Auto-generated method stub
		return null;
	}

	


	
	
	
	
	
	
	
	
	
}
