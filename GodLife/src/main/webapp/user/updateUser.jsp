<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


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
	
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
   
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
		body {
            padding-top : 50px;
        }
    </style>
    
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">
	
		//============= "수정"  Event 연결 =============
		 $(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$( "button.btn.btn-primary" ).on("click" , function() {
				fncUpdateUser();
			});
		});	
		
		
		//============= "취소"  Event 처리 및  연결 =============
		$(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$("a[href='#' ]").on("click" , function() {
				$("form")[0].reset();
			});
		});	
		
		//=============아이디" 유효성Check  Event 처리 =============
		 $(function() {
			 
			 $("input[name='userEmail']").on("change" , function() {
					
				 var userEmail=$("input[name='userEmail']").val();
			    
				 if(userEmail != "" && (userEmail.indexOf('@') < 1 || userEmail.indexOf('.') == -1) ){
			    	alert("이메일 형식이 아닙니다.");
			     }
			});
			 
		});	
		
		///////////////////////////////////////////////////////////////////////
		function fncUpdateUser() {
		
						
			$("form").attr("method" , "POST").attr("action" , "/user/updateUser").submit();
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
		
	
	</script>
	
</head>

<body>

	<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->
	
	<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
	
		<div class="page-header text-center">
	       <h3 class=" text-info">회원정보수정</h3>
	       <h5 class="text-muted">내 정보를 <strong class="text-danger">최신정보로 관리</strong>해 주세요.</h5>
	    </div>
	    
	    <!-- form Start /////////////////////////////////////-->
		<form name="detailForm"  class="form-horizontal" encType="multipart/form-data" >
		
		  <div class="form-group">
		    <label for="userEmail" class="col-sm-offset-1 col-sm-3 control-label">아 이 디</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="userEmail" name="userEmail" value="${user.userEmail }" placeholder="중복확인하세요"  readonly>
		       <span id="helpBlock" class="help-block">
		      	<strong class="text-danger">아이디는 수정불가</strong>
		      </span>
		    </div>
		  </div>
		  
		  
		  <div class="form-group">
		    <label for="nick" class="col-sm-offset-1 col-sm-3 control-label">닉네임</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="nick" name="nick" value="${user.nick}" placeholder="변경닉네임">
		    </div>
		  </div>
		  
		    <div class="form-group">
		    <label for="intro" class="col-sm-offset-1 col-sm-3 control-label">자기소개</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="intro" name="intro" value="${user.intro}" placeholder="자기소개를 입력해주세요">
		    </div>
		  </div>
		  
		  	<div class="form-group">
	   		 <label for="profileImg" class="col-sm-offset-1 col-sm-3 control-label"> 프로필 이미지 </label>
		 	   <div class="col-sm-4">
		      <input type="file" class="form-control" id="fileInfo" multiple="multiple"  name="fileInfo">
	    		</div>
	    	
		     </div> 
		  
			
		  
		   <tr class="mobileNo">
		<th>
			<label for="phone">휴대폰 번호 변경</label>
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
				변경할 휴대폰번호에 인증번호를 보내 본인인증을 완료해주세요. 
			</p>
		</td>
	</tr>
	
	
	
	 <div class="form-group">
		    <label for="categNo" class="col-sm-offset-1 col-sm-3 control-label" >관심사</label>
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
	

		  <div class="form-group">
		    <div class="col-sm-offset-4  col-sm-4 text-center">
		      <button type="button" class="btn btn-primary"  >수 &nbsp;정</button>
			  <a class="btn btn-primary btn" href="#" role="button">취 &nbsp;소</a>
		    </div>
		  </div>
		</form>
		<!-- form Start /////////////////////////////////////-->
	    
 	</div>
	<!--  화면구성 div Start /////////////////////////////////////-->
 	
</body>

</html>