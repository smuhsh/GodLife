package com.godLife.io.service.user.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.godLife.io.service.user.UserService;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import net.nurigo.java_sdk.api.Message;
import net.nurigo.java_sdk.exceptions.CoolsmsException;

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
	
	
	public int  getUserKakao(User user) throws Exception {
		return userDao.getUserKakao(user);
	}

	
	
	//================회원=======================================================
	
	public String getKaKaoAccessToken(String authorize_code) throws Exception{


		System.out.println("토큰 받으러 getKaKaoAccessToken 서비스에 옴 ");
		//System.out.println;("받은 인가 코드 getKaKaoAccessToken {} " , authorize_code);

		String access_Token = "";
		String refresh_Token = "";
		//요청하는 인증 URL		
		String reqURL = "https://kauth.kakao.com/oauth/token";  

		try {
			URL url = new URL(reqURL);
            
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			// POST 요청을 위해 기본값이 false인 setDoOutput을 true로
            
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			// POST 요청에 필요로 요구하는 파라미터 스트림을 통해 전송
            
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()));
			StringBuilder sb = new StringBuilder();
			sb.append("grant_type=authorization_code");  // 인가 코드  요청 authorization_code 
            
			sb.append("&client_id=6d708d50985428b8450271c1e7e98b04&"); //본인이 발급받은 key
			sb.append("&redirect_uri=http://localhost:8080/user/kakaoLogin"); // 본인이 설정한 주소
            
			sb.append("&code=" + authorize_code); // 인가 코드 까지 삽입
			bw.write(sb.toString());
			bw.flush(); //bw 출력 
            
			// 결과 코드가 200이라면 성공
			int responseCode = conn.getResponseCode();
			System.out.println("responseCode : {}" + responseCode);
            
			
			// 요청을 통해 얻은 JSON타입의 Response 메세지 읽어오기
			BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String line = "";
			String result = "";
            
			//카카오에서 주는 정보 한줄한줄 읽어서 result에 저장 
			while ((line = br.readLine()) != null) {
				result += line;
			}
			System.out.println("response body : {}" + result);
            
			// Gson 라이브러리에 포함된 클래스로 JSON파싱 객체 생성
			JsonParser parser = new JsonParser();
			JsonElement element = parser.parse(result);
            
			access_Token = element.getAsJsonObject().get("access_token").getAsString();
			refresh_Token = element.getAsJsonObject().get("refresh_token").getAsString();
            
			System.out.println("access_token : {} " + access_Token);
			System.out.println("refresh_token :{} " +refresh_Token);
			System.out.println("== kakao 정보 끝 ==");

			br.close();
			bw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return access_Token;
		//통신 결과가 200이어야 OK
		
	}
	public HashMap<String, Object> getKakoUserInfo(String access_Token) throws Exception {
		// 요청하는 클라이언트마다 가진 정보가 다를 수 있기에 HashMap타입으로 선언
				HashMap<String, Object> userInfo = new HashMap<String, Object>();
				String reqURL = "https://kapi.kakao.com/v2/user/me";
				try {
					URL url = new URL(reqURL);
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("GET");

					// 요청에 필요한 Header에 포함될 내용
					conn.setRequestProperty("Authorization", "Bearer " + access_Token);

					int responseCode = conn.getResponseCode();
					System.out.println("responseCode : " + responseCode);

					BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));

					String line = "";
					String result = "";

					while ((line = br.readLine()) != null) {
						result += line;
					}
					System.out.println("response body : " + result);

					JsonParser parser = new JsonParser();
					JsonElement element = parser.parse(result);

					JsonObject properties = element.getAsJsonObject().get("properties").getAsJsonObject();
					JsonObject kakao_account = element.getAsJsonObject().get("kakao_account").getAsJsonObject();

		 			String email = kakao_account.getAsJsonObject().get("email").getAsString();

		 			userInfo.put("email", email);

				} catch (IOException e) {
					e.printStackTrace();
				}
				return userInfo;
		
	}
	
	
	
// 이메일 중복체크
	public int checkUserEmail(String userEmail) throws Exception {
		int cnt = userDao.checkUserEmail(userEmail);
		System.out.println("cnt: " + cnt);
		return cnt;
	}
	
	// id 중복체크
	public int checkNick(String nick) throws Exception {
		int cnt = userDao.checkNick(nick);
		System.out.println("cnt: " + cnt);
		return cnt;
	}
	
	//핸드폰 중복체크 
	public int checkPhone(String phone) throws Exception {
		int cnt = userDao.checkPhone(phone);
		System.out.println("cnt: " + cnt);
		return cnt;
	}
	
	
	// 아이디찾기 
	public String findUserEmail(HttpServletResponse response, String phone) throws Exception {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		String userEmail = userDao.findUserEmail(phone);
		
		if (userEmail == null) {
			out.println("<script>");
			out.println("alert('가입된 이메일이 없습니다.');");
			out.println("history.go(-1);");
			out.println("</script>");
			out.close();
			return null;
		} else {
			return userEmail;
		}
	}
	
	//비밀번호 찾기
	public int findUserPwd(String phone, String userEmail) throws Exception{
		
		Map<String, String>map = new HashMap<>();
		map.put("phone", phone);
		map.put("userEmail", userEmail);
		
		int cnt = userDao.findUserPwd(map);
		System.out.println("개수"+cnt);
		
		return userDao.findUserPwd(map);
	}
	
	
	
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
	
	
	public Map<String , Object > getUserList(Search search) throws Exception {
		List<User> list= userDao.getUserList(search);
		int totalCount = userDao.getTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	
//	public User findUserPhone(String phone) throws Exception {
//		return userDao.findUserPhone(phone);
//	}
//	
//	public User findUserEmail(String userEmail) throws Exception {
//		return userDao.findUserEmail(userEmail);
//	}
	
	//인증 문자 보내기 
	public void certifiedPhoneNumber(String userPhoneNumber, int randomNumber) throws Exception {
		String api_key = "NCSFLNAKPLATWT5U";
	    String api_secret = "UQHE4HDGLZ99FWYC4YHSECRYKMLHGVZI";
	    Message coolsms = new Message(api_key, api_secret);

	    // 4 params(to, from, type, text) are mandatory. must be filled
	    HashMap<String, String> params = new HashMap<String, String>();
	    params.put("to", "01080077545");    // 수신전화번호
	    params.put("from", "01080077545");    // 발신전화번호. 테스트시에는 발신,수신 둘다 본인 번호로 하면 됨
	    params.put("type", "SMS");
	    params.put("text", "[TEST] 인증번호는" + "["+randomNumber+"]" + "입니다."); // 문자 내용 입력
	    params.put("app_version", "test app 1.2"); // application name and version

	    try {
	        JSONObject obj = (JSONObject) coolsms.send(params);
	        System.out.println(obj.toString());
	      } catch (CoolsmsException e) {
	        System.out.println(e.getMessage());
	        System.out.println(e.getCode());
	      }
	}
	
	//================친구, 블랙리스트=================================================
	
	//친구 요청목록조회
	public Map<String , Object > getFriendList(Search search, String userEmail) throws Exception {
		int totalCount = userDao.getUserFriendListTotalCount(search, userEmail);
		
		Map<String, Object> map = userDao.getFriendList(search, userEmail);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	public Map<String , Object > getBlackList(Search search, String userEmail) throws Exception {
		int totalCount = userDao.getUserBlackListTotalCount(search, userEmail);
		
		Map<String, Object> map = userDao.getBlackList(search, userEmail);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	
	public Map<String , Object > getFriendRequestList(Search search, String targetEmail) throws Exception {
		int totalCount = userDao.getUserFriendRequestListTotalCount(search, targetEmail);
		
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
	
	public void deleteFriendRequest(FriendBlack friendBlack) throws Exception {
		userDao.deleteFriendRequest(friendBlack);
	}
	
	public void deleteFriend(int friendBlackNo) throws Exception {
		userDao.deleteFriend(friendBlackNo);
	}
	
	public void deleteBlack(int friendBlackNo) throws Exception {
		userDao.deleteBlack(friendBlackNo);
	}
	
	// 친구신청 중복검사
	public int checkFriend(String userEmail, String targetEmail) {
		Map<String, String> map = new HashMap<>();
		map.put("userEmail", userEmail);
		map.put("targetEmail", targetEmail);
		int checkFriend = userDao.checkFriend(map);
		
		return checkFriend;
	}
	
	
	// 블랙리스트신청 중복검사
	public int checkBlack(String userEmail, String targetEmail) {
		Map<String, String> map = new HashMap<>();
		map.put("userEmail", userEmail);
		map.put("targetEmail", targetEmail);
		int checkBlack = userDao.checkBlack(map);
		
		return checkBlack;
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
		int totalCount = userDao.getUserRecvMsgTotalCount(search, recvEmail);
		Map<String, Object> map = userDao.getRecvMsgList(search, recvEmail);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	
	public Map<String , Object > getSendMsgList(Search search, String sendEmail) throws Exception {
		int totalCount = userDao.getUserSendMsgTotalCount(search, sendEmail);

		Map<String, Object> map = userDao.getSendMsgList(search, sendEmail);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
	}
	
	
	// 쪽지 유효성 검사 블랙리스트 못보내게
	public int checkMsgBlack(String userEmail, String targetEmail) {
		Map<String, String> map = new HashMap<>();
		map.put("userEmail", userEmail);
		map.put("targetEmail", targetEmail);
		int checkMsgBlack = userDao.checkMsgBlack(map);
		
		return checkMsgBlack;
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
	
	//쪽지 신고등록 
	public void addUserReport(Report report) throws Exception {
		userDao.addUserReport(report);
	}
	
	
	// 쪽지 신고 중복방지
	public int checkMsgReport(Report report) throws Exception {

		int checkMsgReport = userDao.checkMsgReport(report);
		
		return checkMsgReport;
	}
	
	// 인증이미지 신고 중복방지
	public int checkCertiImgReport(Report report) throws Exception {

		int checkcommentNoReport = userDao.checkCertiImgReport(report);
		
		return checkcommentNoReport;
	}
		
	// 댓글 신고 중복방지
	public int checkCommentReport(Report report) throws Exception {

		int checkCommentReport = userDao.checkCommentReport(report);
		
		return checkCommentReport;
	}
	
	//신고회원 목록조회(관리자) 
	public Map<String , Object > getUserReportList(Search search) throws Exception {
		List<User> list= userDao.getUserReportList(search);
		int totalCount = userDao.getUserReportListTotalCount(search);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("list", list );
		map.put("totalCount", new Integer(totalCount));
		return map;
	}
	
	
	
	//신고회원 상세목록조회 (목록에서 목록...) 
	public Map<String , Object > getUserReport(Search search, String targetEmail) throws Exception {
		int totalCount = userDao.getUserReportTotalCount(search, targetEmail); 
		
		Map<String, Object> map = userDao.getUserReport(search, targetEmail);
		map.put("totalCount", new Integer(totalCount));
		
		return map;
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
	
	
	
	//레드카드 발급 
	public void updateRedCard(User user) throws Exception{
		userDao.updateRedCard(user);
	}

	public void updateUserRedCouponCountUse(User user) throws Exception{
		userDao.updateUserRedCouponCountUse(user);
	}
	
	
}