<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- 참조 : http://getbootstrap.com/css/   참조 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
<title>아이디 찾기 결과</title>

<link rel="stylesheet" href="/css/getUserEmailView.css" />

	<!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">
	
	//============= "로그인"  Event 연결 =============
	 $(function() {
		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		$( "#find_idBtn" ).on("click" , function() {
			$("form").attr("method" , "GET").attr("action" , "/user/login").submit();
		});
	});	
	
		//============= "비밀번호 찾기"  Event 연결 =============
	 $(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$( "#find_idBtn2" ).on("click" , function() {
				$("form").attr("method" , "GET").attr("action" , "/user/findUserPwd").submit();
			});
		});	
	
	</script>
	



</head>

<body>
<div id="content">
<div class="section_login">

		<h3 class="tit_login">회원님의 아이디는 
		<br></br>
		${userEmail } 입니다. </h3>
		
		<div class="write_form find_view">
		
			<form id="form" name="fm">

				<button type="button" class="btn_type1" id="find_idBtn"><span class="txt_type">로그인</span></button>
				<button type="button" class="btn_type1" id="find_idBtn2"><span class="txt_type">비밀번호 찾기</span></button>
			</form>
			
			
			</div>
		</div>
	</div>
</body>
</html>