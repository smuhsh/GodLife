<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>


<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	
	<!-- 참조 : http://getbootstrap.com/css/   참조 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
       body > div.container{
        	border: 3px solid #D6CDB7;
            margin-top: 10px;
        }
    </style>
    
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">
	
		//============= "가입"  Event 연결 =============
		 $(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$( "button.btn.btn-primary" ).on("click" , function() {
				fncAddUser();
			});
		});	
		
		
		//============= "취소"  Event 처리 및  연결 =============
		$(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$("a[href='#' ]").on("click" , function() {
				$("form")[0].reset();
			});
		});	
	
		
		
		
		function fncAddUser() {
			
			var userEmail=$("input[name='userEmail']").val();
			var pwd=$("input[name='pwd']").val();
			var nick=$("input[name='nick']").val();
			
			
			if(userEmail == null || userEmail.length <1){
				alert("아이디는 반드시 입력하셔야 합니다.");
				return;
			}
			if(pwd == null || pwd.length <1){
				alert("패스워드는  반드시 입력하셔야 합니다.");
				return;
			}
			if(nick == null || nick.length <1){
				alert("닉네임은  반드시 입력하셔야 합니다.");
				return;
			}
				
			$("form").attr("method" , "POST").attr("action" , "/user/addUser").submit();
		}
	
		
		//휴대폰 번호 인증
		 $(function(){
			 
		var code2 = "";
		$("#phoneChk").click(function(){
			alert("인증번호 발송이 완료되었습니다.\n휴대폰에서 인증번호 확인을 해주십시오.");
			var phone = $("#phone").val();
			$.ajax({
		        type:"GET",
		        url:"/user/phoneCheck?phone=" + phone,
		        cache : false,
		        success:function(data){
		        	if(data == "error"){
		        		alert("휴대폰 번호가 올바르지 않습니다.")
						$(".successPhoneChk").text("유효한 번호를 입력해주세요.");
						$(".successPhoneChk").css("color","red");
						$("#phone").attr("autofocus",true);
		        	}else{	        		
		        		$("#phone2").attr("disabled",false);
		        		$("#phoneChk2").css("display","inline-block");
		        		$(".successPhoneChk").text("인증번호를 입력한 뒤 본인인증을 눌러주십시오.");
		        		$(".successPhoneChk").css("color","green");
		        		$("#phone").attr("readonly",true);
		        		code2 = data;
		        	}
		        }
		    });
		});

		//휴대폰 인증번호 대조
		$("#phoneChk2").click(function(){
			if($("#phone2").val() == code2){
				$(".successPhoneChk").text("인증번호가 일치합니다.");
				$(".successPhoneChk").css("color","green");
				$("#phoneDoubleChk").val("true");
				$("#phone2").attr("disabled",true);
			}else{
				$(".successPhoneChk").text("인증번호가 일치하지 않습니다. 확인해주시기 바랍니다.");
				$(".successPhoneChk").css("color","red");
				$("#phoneDoubleChk").val("false");
				$(this).attr("autofocus",true);
			}
		});
   });	

		
		 ///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	</script>		
    
</head>

<body>

	<!-- ToolBar Start /////////////////////////////////////-->
	<div class="navbar  navbar-default">
        <div class="container">
        	<a class="navbar-brand" href="/index.jsp">GodLife</a>
   		</div>
   	</div>
   	<!-- ToolBar End /////////////////////////////////////-->

	<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
	
		<h1 class="bg-primary text-center">회 원 가 입</h1>
		
		<!-- form Start /////////////////////////////////////-->
		<form class="form-horizontal" form action="/sendMail/id" id="findForm" method="post">
		
 		<div class="form-group">
		    <label for="userEmail" class="col-sm-offset-1 col-sm-3 control-label">아이디</label>
		    <div class="col-sm-4">
		      <input type="userEmail" class="form-control" id="userEmail" name="userEmail" placeholder="아이디">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="pwd" class="col-sm-offset-1 col-sm-3 control-label">비밀번호</label>
		    <div class="col-sm-4">
		      <input type="pwd" class="form-control" id="pwd" name="pwd" placeholder="비밀번호">
		    </div>
		  </div>
		  
		  
		  <div class="form-group">
		    <label for="nick" class="col-sm-offset-1 col-sm-3 control-label">닉네임</label>
		    <div class="col-sm-4">
		      <input type="nick" class="form-control" id="nick" name="nick" placeholder="닉네임">
		    </div>
		  </div>
		  
		    <div class="form-group">
		    <label for="categNo" class="col-sm-offset-1 col-sm-3 control-label">관심사</label>
		    <div class="col-sm-4">
		      <input type="checkbox" id="sports" name="categNo" value="1">
					<label for="sports">운동</label>
					<input type="checkbox" id="eatingHabit" name="categNo" value="2">
					<label for="eatingHabit">식습관</label>
					<input type="checkbox" id="studying" name="categNo" value="3">
					<label for="studying">공부</label>
					<input type="checkbox" id="hobby" name="categNo" value="4">
					<label for="hobby">취미</label>
					<input type="checkbox" id="life" name="categNo" value="5">
					<label for="life">생활</label>
		    </div>
		  </div>
		  
			  <tr class="mobileNo">
		<th>
			<label for="phone">휴대폰 번호</label>
		</th>
		<td>
			<p>
				<input id="phone" type="text" name="phone" title="전화번호 입력" required/>
				<span id="phoneChk" class="doubleChk">인증번호 보내기</span><br/>
				<input id="phone2" type="text" name="phone2" title="인증번호 입력" disabled required/>
				<span id="phoneChk2" class="doubleChk">본인인증</span>
				<span class="point successPhoneChk">휴대폰 번호 입력후 인증번호 보내기를 해주십시오.</span>
				<input type="hidden" id="phoneDoubleChk"/>
			</p>
			<p class="tip">
				최초 가입 시에만 사용하고 있습니다. 따로 저장되지 않습니다.(번호만 입력해주세요.)
			</p>
		</td>
	</tr>
	
		  <div class="form-group">
		    <div class="col-sm-offset-4  col-sm-4 text-center">
		      <button type="button" class="btn btn-primary"  >가입</button>
			  <a class="btn btn-primary btn" href="#" role="button">취소</a>
		    </div>
		  </div>
		</form>
		<!-- form Start /////////////////////////////////////-->
		
 	</div>
	<!--  화면구성 div end /////////////////////////////////////-->
	
</body>

</html>