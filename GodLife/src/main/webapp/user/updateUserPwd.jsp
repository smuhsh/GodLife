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

<title>비밀번호 수정</title>

<link rel="stylesheet" href="/css/getUserEmailView.css" />

<script type="text/javascript" >


//============= "비밀번호 찾기"  Event 연결 =============
$(function() {
	//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
	
	$( "#find_pwdBtn" ).on("click" , function() {
		var userEmail=$("input[name='userEmail']").val();
		var pw=$("input[name='pwd']").val();
		var pw_confirm=$("input[name='pwd2']").val();
		
		if(pw == null || pw.length <1){
			alert("비밀번호는  반드시 입력하셔야 합니다.");
			return;
		}
		
		if(pw_confirm == null || pw_confirm.length <1){
			alert("비밀번호 확인은  반드시 입력하셔야 합니다.");
			return;
		}
  
		if( pw != pw_confirm ) {				
			alert("비밀번호가 일치하지않습니다.");
			return;
		}
	
		$("form").attr("method" , "POST").attr("action" , "/user/updateUserPwd").submit();
		alert("비밀번호 변경이 완료되었습니다");
	});
});	
	
</script>

</head>
<body>
	<div id="content" style="height: 61.5%;">
		<div class="section_login">
			<form id="form" name="frmMember">
			<h2 class="tit_login">비밀번호 수정</h2>
			<div class="write_form find_view">
			
			
					<tr>
						<th>비밀번호</th>
						<td>
							<input type="password" class="form-control" id="pwd" name="pwd" placeholder="비밀번호">
						</td>
					</tr>
					
					<tr>
						<th>비밀번호확인</th>
						<td>
							<input type="password" class="form-control" id="pwd2" name="pwd2" placeholder="비밀번호 확인">
						</td>
					</tr>
					
					
					<button type="button" class="btn_type1" id="find_pwdBtn"><span class="txt_type">수정하기</span></button>
				
						<input type="hidden" name="userEmail" value="${user.userEmail}">
				
			</div>
			</form>
		</div>
	</div>
</body>






</html>