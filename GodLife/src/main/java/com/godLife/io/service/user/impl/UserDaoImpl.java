package com.godLife.io.service.user.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import com.godLife.io.service.user.UserDao;
import com.godLife.io.service.user.UserService;
import com.godLife.io.common.Search;
import com.godLife.io.service.domain.FriendBlack;
import com.godLife.io.service.domain.Msg;
import com.godLife.io.service.domain.OneInq;
import com.godLife.io.service.domain.Report;
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
	 ///Field
	   @Autowired
	   @Qualifier("userServiceImpl")
	   private UserService userService;
	
	///Constructor
	public UserDaoImpl() {
		System.out.println(this.getClass());
	}
	
	//================회원=======================================================
	public int  getUserKakao(User user) throws Exception {
 		return sqlSession.selectOne("UserMapper.getUserKakao", user);
	}

	//아이디 찾기 
	public String findUserEmail(String phone) throws Exception{
		return sqlSession.selectOne("UserMapper.findUserEmail", phone);
	}
	
	//비밀번호 찾기 
	public int findUserPwd(Map<String, String> map)throws Exception{
		return sqlSession.selectOne("UserMapper.findUserPwd", map);
	}
	
	
// userEmail 중중복체크
	public int checkUserEmail(String userEmail) throws Exception {
		return sqlSession.selectOne("UserMapper.checkUserEmail", userEmail);
	}
	
	// nick중복체크
	public int checkNick(String nick){
		return sqlSession.selectOne("UserMapper.checkNick", nick);
	}	
	
	// 핸드폰 중복체크
	public int checkPhone(String phone){
		return sqlSession.selectOne("UserMapper.checkPhone", phone);
	}	
	
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
	
	
	//유저 전체 목록조회(관리자) 
	public List<User> getUserList(Search search) throws Exception {
		return sqlSession.selectList("UserMapper.getUserList", search);
	}
	
	
	//================친구, 블랙리스트=================================================
	
	
	public Map<String, Object> getFriendList(Search search, String userEmail) throws Exception {
		
		   Map<String, Object> map=new HashMap<String, Object>();
		   FriendBlack friendBlack = new FriendBlack();
		   
		   map.put("search", search);
		   map.put("endRowNum",  search.getEndRowNum()+"" );
		   map.put("startRowNum",  search.getStartRowNum()+"" );
		   map.put("userEmail", userEmail);
		   
		   List<FriendBlack> list = sqlSession.selectList("FriendBlackMapper.getFriendList", map);
		   map.put("list", list);
		   
		   return map;
	}
	
	
	public Map<String, Object> getBlackList(Search search, String userEmail) throws Exception {
		
		   Map<String, Object> map=new HashMap<String, Object>();
		   FriendBlack friendBlack = new FriendBlack();
		   
		   map.put("search", search);
		   map.put("endRowNum",  search.getEndRowNum()+"" );
		   map.put("startRowNum",  search.getStartRowNum()+"" );
		   map.put("userEmail", userEmail);
		   
		   List<FriendBlack> list = sqlSession.selectList("FriendBlackMapper.getBlackList", map);
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
	
	public void deleteFriendRequest(FriendBlack friendBlack) throws Exception {
		sqlSession.delete("FriendBlackMapper.deleteFriendRequest", friendBlack);
	}
	
	public void deleteFriend(int friendBlackNo) throws Exception {
		sqlSession.delete("FriendBlackMapper.deleteFriend", friendBlackNo);
	}
	
	public void deleteBlack(int friendBlackNo) throws Exception {
		sqlSession.delete("FriendBlackMapper.deleteBlack", friendBlackNo);
	}
	
	
	// 친구신청 중복검사
	public int checkFriend(Map<String, String> map) {
		return sqlSession.selectOne("FriendBlackMapper.checkFriend", map);
	}
	
	// 블랙리스트신청 중복검사
	public int checkBlack(Map<String, String> map) {
		return sqlSession.selectOne("FriendBlackMapper.checkBlack", map);
	}
	
	
	
	
	//================쪽지================================================
	public void addMsg(Msg msg) throws Exception {
		sqlSession.insert("MsgMapper.addMsg", msg);
	}
	
	public Msg getRecvMsg(int msgNo) throws Exception {
		return sqlSession.selectOne("MsgMapper.getRecvMsg", msgNo);
	}
	
	public Msg getSendMsg(int msgNo) throws Exception {
		return sqlSession.selectOne("MsgMapper.getSendMsg", msgNo);
	}
	
	public void deleteMsg(int msgNo) throws Exception {
		sqlSession.delete("MsgMapper.deleteMsg", msgNo);
	}
	
	public Map<String, Object> getRecvMsgList(Search search, String recvEmail) throws Exception {
		
		   Map<String, Object> map=new HashMap<String, Object>();
		   
		   Msg msg = new Msg(); 
		   map.put("search", search);
		   map.put("endRowNum",  search.getEndRowNum()+"" );
		   map.put("startRowNum",  search.getStartRowNum()+"" );
		   map.put("recvEmail", recvEmail);
		   
		   List<Msg> list = sqlSession.selectList("MsgMapper.getRecvMsgList", map);
		   map.put("list", list);
		   
		   return map;
	}
	
	public Map<String, Object> getSendMsgList(Search search, String sendEmail) throws Exception {
		
		   Map<String, Object> map=new HashMap<String, Object>();
		   
		   Msg msg = new Msg(); 
		   
		   map.put("search", search);
		   map.put("endRowNum",  search.getEndRowNum()+"" );
		   map.put("startRowNum",  search.getStartRowNum()+"" );
		   map.put("sendEmail", sendEmail);
		   
		   List<Msg> list = sqlSession.selectList("MsgMapper.getSendMsgList", map);
		   map.put("list", list);
		   
		   return map;
	}
	
	// 친구신청 중복검사
	public int checkMsgBlack(Map<String, String> map) {
		return sqlSession.selectOne("MsgMapper.checkMsgBlack", map);
	}

	public void updateUserTotalPoint(User user) throws Exception{
		sqlSession.update("UserMapper.updateUserTotalPoint", user);
	}
	
	//================일대일문의================================================
	
	public void addOneInq(OneInq oneInq) throws Exception {
		sqlSession.insert("OneInqMapper.addOneInq", oneInq);
	}
	
	
	public Map<String, Object> getOneInqList(Search search, String userEmail) throws Exception {
		
		   Map<String, Object> map=new HashMap<String, Object>();
		   OneInq oneInq = new OneInq();
		   
		   map.put("endRowNum",  search.getEndRowNum()+"" );
		   map.put("startRowNum",  search.getStartRowNum()+"" );
		   map.put("userEmail", userEmail);
		   
		   List<FriendBlack> list = sqlSession.selectList("OneInqMapper.getOneInqList", map);
		   map.put("list", list);
		   
		   return map;
	}
	
	
	public OneInq getOneInq(int oneInqNo) throws Exception {
		return sqlSession.selectOne("OneInqMapper.getOneInq", oneInqNo);
	}
	
	public void deleteOneInq(int oneInq) throws Exception {
		sqlSession.delete("OneInqMapper.deleteOneInq", oneInq);
	}
	
	public void updateOneInq(OneInq oneInq) throws Exception {
		sqlSession.update("OneInqMapper.updateOneInq", oneInq);
	}
	
	public void updateOneInqComment(OneInq oneInq) throws Exception {
		sqlSession.update("OneInqMapper.updateOneInqComment", oneInq);
	}
	
	//================신고등록================================================
	
	//쪽지 신고등록
	public void addUserReport(Report report) throws Exception {
		 int msgNo=report.getMsgNo();
	      int ceritImgNo=report.getCertiImgNO();
	      int commentNo=report.getCommentNo();
	      if(msgNo!=0) {
	    	  sqlSession.insert("ReportMapper.addMsgReport", report);
	      }
	      
	      if(ceritImgNo!=0) {
	  		sqlSession.insert("ReportMapper.addCertiImgReport", report);
	  	  }
	      
	      if(commentNo!=0) {
	  		sqlSession.insert("ReportMapper.addCommentReport", report);
	  	  }
	      User user = new User();
	      user.setUserEmail(report.getTargetEmail());
	      user=userService.getUser(user.getUserEmail());
	      System.out.println("@@@ addreport targetUser info : "+user);
	      int reportCount=user.getReportCount();
	      user.setReportCount(reportCount+1);
	      System.out.println("@@@ addreport targetUser reportCount info : "+user.getReportCount());
	      sqlSession.update("UserMapper.updateUserReportCount", user);
	      
	      if(user.getReportCount()>=5) {
	    	  user.setReportCount(0);
	    	  int getRedCardCount = user.getRedCardCount();
	    	  user.setRedCardCount(getRedCardCount+1);
	    	  sqlSession.update("UserMapper.updateRedCard", user);
	    	  System.out.println("레드카드 보유개수 증가");
	    	  sqlSession.update("UserMapper.updateUserReportCount", user);
	      }
	}
	
	//쪽지 신고 중복방지 
	public int checkMsgReport(Report report) {
		return sqlSession.selectOne("ReportMapper.checkMsgReport", report);
	}
	//인증이미지 신고 중복방지 
	public int checkCertiImgReport(Report report) {
		return sqlSession.selectOne("ReportMapper.checkCertiImgReport", report);
	}
	//댓글 신고 중복방지 
	public int checkCommentReport(Report report) {
		return sqlSession.selectOne("ReportMapper.checkCommentReport", report);
	}
	
	//신고회원목록(관리자)
	public List<User> getUserReportList(Search search) throws Exception {
		return sqlSession.selectList("UserMapper.getUserReportList", search);
	}
	
	
	//신고 유저 상세목록조회 
	public Map<String, Object> getUserReport(Search search, String targetEmail) throws Exception {
		   
			Map<String, Object> map=new HashMap<String, Object>();
		   
		   map.put("endRowNum",  search.getEndRowNum()+"" );
		   map.put("startRowNum",  search.getStartRowNum()+"" );
		   map.put("targetEmail", targetEmail);
		   
		   List<Msg> list = sqlSession.selectList("ReportMapper.getUserReport", map);
		   map.put("list", list);
		   
		   return map;
	}
	
	
	public void updateUserRedCouponCount(User user) throws Exception{
		System.out.println("userDaoImpl : "+user);
		sqlSession.update("UserMapper.updateUserRedCoupon",user );
	}

	
	public void updateUserCertiCouponCount(User user) throws Exception{
		System.out.println("userDaoImpl : "+user);
		sqlSession.update("UserMapper.updateUserCertiCoupon",user);
	}
	
	public void updateRedCard(User user)  throws Exception{
		System.out.println(user);
		sqlSession.update("UserMapper.updateRedCard",user);
	}
	
	public void updateUserRedCouponCountUse(User user) throws Exception{
		System.out.println("userDaoImpl : "+user);
		int redCouponCount =user.getRedCouponCount();
		user.setRedCouponCount(redCouponCount-1);
		sqlSession.update("UserMapper.updateUserRedCoupon",user );
		
		int getRedCardCount = user.getRedCardCount();
		user.setRedCardCount(getRedCardCount-1);
  	  	sqlSession.update("UserMapper.updateRedCard", user);
	}
	
	
	//==========================================================================================================
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  (전체유저목록조회 / 관리자용)
	public int getTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("UserMapper.getTotalCount", search);
		}
	
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return. (받은쪽지 목록조회) 
	public int getUserRecvMsgTotalCount(Search search, String recvEmail) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>(); 
		
		map.put("search", search);
		map.put("recvEmail", recvEmail);
		
		return sqlSession.selectOne("MsgMapper.getUserRecvMsgTotalCount", map);
		
		}
	
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return. (친구목록조회)
	public int getUserFriendListTotalCount(Search search, String userEmail) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>(); 
		
		map.put("search", search);
		map.put("userEmail", userEmail);
		
		return sqlSession.selectOne("FriendBlackMapper.getUserFriendListTotalCount", map);
		}
	
   // 게시판 Page 처리를 위한 전체 Row(totalCount)  return. (친구요청 목록조회)
	public int getUserFriendRequestListTotalCount(Search search, String targetEmail) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("search", search);
		map.put("targetEmail", targetEmail);
		return sqlSession.selectOne("FriendBlackMapper.getUserFriendRequestListTotalCount", map);
		}
	
 // 게시판 Page 처리를 위한 전체 Row(totalCount)  return. (블랙리스트 목록조회)
	public int getUserBlackListTotalCount(Search search, String userEmail) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>(); 
		map.put("search", search);
		map.put("userEmail", userEmail);
		return sqlSession.selectOne("FriendBlackMapper.getUserBlackListTotalCount", map);
		}
	
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount)  return. (보낸쪽지 목록조회) 
	public int getUserSendMsgTotalCount(Search search, String sendEmail) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>(); 
		
		map.put("search", search);
		map.put("sendEmail",sendEmail);
		
		return sqlSession.selectOne("MsgMapper.getUserSendMsgTotalCount", map);
		
		}
	
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount) (신고목록조회 / 관리자용) 
	public int getUserReportListTotalCount(Search search) throws Exception {
		return sqlSession.selectOne("UserMapper.getUserReportListTotalCount", search);
		}
	
	
	// 게시판 Page 처리를 위한 전체 Row(totalCount) (신고유저 상세목록조회) 
	public int getUserReportTotalCount(Search search, String targetEmail) throws Exception {
		
		Map<String, Object> map = new HashMap<String, Object>(); 
		
		map.put("search", search);
		map.put("targetEmail",targetEmail);
		
		return sqlSession.selectOne("ReportMapper.getUserReportTotalCount", map);
		
		}

	
	
	
	
	
	

	}