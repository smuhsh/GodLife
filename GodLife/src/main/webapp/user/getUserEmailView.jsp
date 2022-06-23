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


<title>아이디 찾기</title>

<link rel="stylesheet" href="/css/getUserEmailView.css" />

<script type="text/javascript" >



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
				$(".successPhoneChk").css("color","blue");
				$("#phone").attr("autofocus",true);
       	}else{	        		
       		$("#phone2").attr("disabled",false);
       		$("#phoneChk2").css("display","inline-block");
       		$(".successPhoneChk").text("인증번호를 입력한 뒤 본인인증을 눌러주십시오.");
       		$(".successPhoneChk").css("color","blue");
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


//============= "아이디 찾기"  Event 연결 =============
$(function() {
	//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
	
	$( "#find_pwdBtn" ).on("click" , function() {
	
  var phone=$("input[name='phone']").val();
  var phone2=$("input[name='phone2']").val();
  var successPhoneCkt = $(".successPhoneChk").text();
  
  if(phone == null || phone.length < 8){
	  alert("핸드폰 번호를 다시 확인해주세요.");
		return;
  }
  
  if(phone2 == null || phone2.length < 4){
	  alert("인증번호를 다시 확인해주세요");
		return;ㄴ
  }
  
  if(successPhoneCkt != '인증번호가 일치합니다.'   ){
	 // alert("@@뭐나오냐..."+successPhoneCkt);
		alert("인증번호가 일치하지 않습니다. ");
		return;
				}
	
		$("form").attr("method" , "POST").attr("action" , "/user/findUserEmail").submit();
	});
});	
	
</script>

</head>
<body>
	<div id="content" style="height: 61.5%;">
		<div class="section_login" id="user">
			<form id="form" name="frmMember">
			<h2 class="tit_login">아이디 찾기</h2>
			<div class="write_form find_view">
			
					<strong class="tit_label">휴대폰번호</strong>
					<input id="phone" type="text" name="phone" title="전화번호 입력" placeholder="예) 01011111111" required/>
									<span id="phoneChk" class="doubleChk">인증번호 보내기</span><br/>
									<input id="phone2" type="text" name="phone2" title="인증번호 입력" disabled required/>
									<span id="phoneChk2" class="doubleChk">본인인증</span>
									<br></br>
									<span class="point successPhoneChk" style ="line-height:50%" ><br>※ 휴대폰 번호 입력 후 인증번호 보내기를 해주십시오.</span>
									<input type="hidden" id="phoneDoubleChk"/>
					
					
					
					<button type="button" class="btn_type1" id="find_pwdBtn"><span class="txt_type">아이디 찾기</span></button>
				
			</div>
			</form>
		</div>
	</div>
</body>






</html>