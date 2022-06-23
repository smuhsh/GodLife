<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
     
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>로그인</title>
<link rel="stylesheet" href="/css/loginView.css" />


    <script src = "https://developers.kakao.com/sdk/js/kakao.js"></script>
</head>
<body>

<div id="content">
	<div class="section_login"  id="user">
		<h3 class="tit_login">로그인</h3>
		<div class="write_form">
			<div class="write_view login_view">
				<form method="post" name="form" id="form">
					<input type="text" name="userEmail" id="userEmail" size="20" tabindex="1" value="" placeholder="이메일을 입력해주세요">
					<div id="idDiv"></div>
					<input type="password" name="pwd" id="pwd" size="20" tabindex="2" placeholder="비밀번호를 입력해주세요">
					<div id="pwdDiv"></div>
					
						<div class="login_search">
							<a href="/user/findUserEmail" class="link">아이디 찾기</a>
							<span class="bar"></span>
							<a href="/user/findUserPwd" class="link">비밀번호 찾기</a>
						</div>
						<br></br>
						
					<button type="button" class="btn_type1" id="loginBtn"><span class="txt_type">로그인</span></button>
				</form>
				<a href="/user/addUser" class="btn_type2 btn_member"><span class="txt_type">회원가입</span></a>
				
				<br>
				
				<a class = "p-2"  
                                   href = "https://kauth.kakao.com/oauth/authorize?client_id=6d708d50985428b8450271c1e7e98b04&redirect_uri=http://localhost:8080/user/kakaoLogin&response_type=code">
                                  <img id = "kakao"  src = "/images/uploadFiles/카카오 로그인.png"></a>
			</div>
		</div>
	</div>
</div>

</body>


<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" >


//============= "로그인"  Event 연결 =============
$( function() {
	
	$("#userEmail").focus();
	
	//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
	$("button").on("click" , function() {
		var id=$("input:text").val();
		var pw=$("input:password").val();
		
		if(id == null || id.length <1) {
			alert('이메일 를 입력하지 않으셨습니다.');
			$("#userEmail").focus();
			return;
		}
		
		if(pw == null || pw.length <1) {
			alert('패스워드를 입력하지 않으셨습니다.');
			$("#pwd").focus();
			return;
		}
		
		$("form").attr("method","POST").attr("action","/user/login").attr("target","_parent").submit();
	});
});	



</script>
</html>