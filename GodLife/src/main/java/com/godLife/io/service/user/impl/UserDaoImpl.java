package com.godLife.io.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.godLife.io.service.user.UserDao;
import com.godLife.io.common.Search;
import com.godLife.io.service.domain.FriendBlack;
import com.godLife.io.service.domain.Msg;
import com.godLife.io.service.domain.User;


//==> 회원관리 DAO CRUD 구현
@Repository("userDaoImpl")
public class UserDaoImpl implements UserDao{
	
	//Field
	@Autowired
	@Qualifier("sqlSessionTemplate")
	private SqlSession sqlSession;
	public void setSqlSession(SqlSession sqlSession) {
		this.sqlSession = sqlSession;
	}
	
	///Constructor
	public UserDaoImpl() {
		System.out.println(this.getClass());
	}
	
	///Method
	public void addUser(User user) throws Exception {
		sqlSession.insert("UserMapper.addUser", user);
	}
	
	public User getUser(String userEmail) throws Exception {
		return sqlSession.selectOne("UserMapper.getUser", userEmail);
	}
	
	public void updatePwd(User user) throws Exception {
		sqlSession.update("UserMapper.updatePwd", user);
	}
	
	public void updateUser(User user) throws Exception {
		sqlSession.update("UserMapper.updateUser", user);
	}
	
	
	public Map<String, Object> getFriendBlackList(Search search, String userEmail) throws Exception {
		
		   Map<String, Object> map=new HashMap<String, Object>();
		   FriendBlack friendBlack = new FriendBlack();
		   
		   map.put("endRowNum",  search.getEndRowNum()+"" );
		   map.put("startRowNum",  search.getStartRowNum()+"" );
		   map.put("userEmail", userEmail);
		   
		   List<FriendBlack> list = sqlSession.selectList("FriendBlackMapper.getFriendBlackList", map);
		   map.put("list", list);
		   
		   return map;
	}
	
	public Map<String, Object> getFriendRequestList(Search search, String targetEmail) throws Exception {
		
		   Map<String, Object> map=new HashMap<String, Object>();
		   FriendBlack friendBlack = new FriendBlack();
		   
		   map.put("endRowNum",  search.getEndRowNum()+"" );
		   map.put("startRowNum",  search.getStartRowNum()+"" );
		   map.put("targetEmail", targetEmail);
		   
		   List<FriendBlack> list = sqlSession.selectList("FriendBlackMapper.getFriendRequestList", map);
		   map.put("list", list);
		   
		   return map;
	}
	
	
	public void addFriend(FriendBlack friendBlack) throws Exception {
		sqlSession.insert("FriendBlackMapper.addFriend", friendBlack);
	}
	
	public void addBlack(FriendBlack friendBlack) throws Exception {
		sqlSession.insert("FriendBlackMapper.addBlack", friendBlack);
	}
	
	public void updateAccStatus(FriendBlack friendBlack) throws Exception {
		sqlSession.update("FriendBlackMapper.updateAccStatus", friendBlack);
	}
	
	public void deleteFriend(FriendBlack friendBlack) throws Exception {
		sqlSession.delete("FriendBlackMapper.deleteFriend", friendBlack);
	}
	
	public void addMsg(Msg msg) throws Exception {
		sqlSession.insert("MsgMapper.addMsg", msg);
	}
	
	public Msg getRecvMsg(String recvEmail) throws Exception {
		return sqlSession.selectOne("MsgMapper.getRecvMsg", recvEmail);
	}
	
	public Msg getSendMsg(String sendEmail) throws Exception {
		return sqlSession.selectOne("MsgMapper.getSendMsg", sendEmail);
	}
	
	public void deleteMsg(Msg msg) throws Exception {
		sqlSession.delete("MsgMapper.deleteMsg", msg);
	}

	
	
	
	
	
	
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("UserMapper.getTotalCount", search);
		}

	
	
	
	
	

	}

	

	



