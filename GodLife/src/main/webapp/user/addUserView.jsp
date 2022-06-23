<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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


<title>회원가입</title>

<link rel="stylesheet" href="/css/addUserView.css" />

	<!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">
	
	var AA = 0; 
	var emailCheck = 0; 
	var nick33 = 0; 
	var pwd33 = 0;
	var phone22 = 0;
	
	
	//==>"이메일" 유효성Check  Event 처리 및 연결
		 $(function() {
			 
			 $("input[name='userEmail']").on("change" , function() {
				 var email=$("input[name='userEmail']").val();
				 if(email != "" && (email.indexOf('@') < 1 || email.indexOf('.') == -1) ){ // 이메일형식이 아니니까 가입 x 
			    	alert("이메일 형식이 아닙니다.");
					 
			     }
			});
		});	
	
	
		//==>"이메일" 중복검사 검사
		
		function checkUserEmail(){
	        var userEmail = $('#userEmail').val(); //id값이 "id"인 입력란의 값을 저장
	        
	        $.ajax({
	        	 url: '/user/json/checkUserEmail', //Controller에서 요청 받을 주소
	            type:'post', //POST 방식으로 전달
	            data:{userEmail:userEmail},
	       
	            success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받는다 
	                if(cnt == 0){ //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 아이디 
	                    $('.id_ok').css("display","inline-block"); 
	                    $('.id_already').css("display", "none");
	                    emailCheck = 0; 
	                } else { // cnt가 1일 경우 -> 이미 존재하는 아이디
	                    $('.id_already').css("display","inline-block");
	                    $('.id_ok').css("display", "none");
	                    //alert("이메일을 다시 입력해주세요");
	                    emailCheck = 1; // 1이아니여야 가입가능  
	                    
	                }
	            },
	            error:function(){
	                alert("에러입니다");
	            }
	        });
	        };
	        
	        
			//==>"핸드폰" 중복검사 
			
			function checkUserPhone(){
		        var phone = $('#phone').val(); //
		        
		        $.ajax({
		        	 url: '/user/json/checkPhone', //Controller에서 요청 받을 주소
		            type:'post', //POST 방식으로 전달
		            data:{phone:phone},
		       
		            success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받는다 
		                if(cnt == 0){ //cnt가 1이 아니면(=0일 경우) -> 회원가입 가능한 핸드폰번호  
		                    phone22 = 0; // 1이아니여야 가입가능  
		                } else { // cnt가 1일 경우 -> 이미 존재하는 핸드폰번호라서 가입 불가능
		                    phone22 = 1; // 1이아니여야 가입가능  
		                }
		            },
		            error:function(){
		                alert("에러입니다");
		            }
		        });
		        };
			
		
		
	   //==>"닉네임" 중복검사 
	
	   function checkNick(){
		        var nick = $('#nick').val(); //닉네임값이 "userNickname"인 입력란의 값을 저장
		        
		        $.ajax({
		            url: '/user/json/checkNick', //Controller에서 요청 받을 주소
		            method : 'post', //POST 방식으로 전달
		            dataType : "json",
		            data:{nick:nick},
		            
		            success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받는다 
		            	
		                if(cnt == 0){ //cnt가 1이 아니면(=0일 경우) -> 사용 가능한 닉네임
		                    $('.id_ok2').css("display","inline-block"); 
		                    $('.id_already2').css("display", "none");
		                    nick33 = 0;
		                } else { // cnt가 1일 경우 -> 이미 존재하는 닉네임
		                    $('.id_already2').css("display","inline-block");
		                    $('.id_ok2').css("display", "none");
		                    nick33 = 1; // 1이 아니여야 가입가능   
		                   alert("닉네임을 다시 입력해주세요");
		                }
		            },
		            
		        });
		        };
		        
		        
		        //비밀번호 유효성검사 (8자리 이상, 숫자포함, 영대문자포함, 영소문자포함, 특수문자 포함)
			   function chkPW(){
				var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
				var pw = $("#pwd").val();
				if(false === reg.test(pw)) {
				//alert('비밀번호는 8자 이상, 20자이하 이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.');
				pwd33 = 1; // 1이아니여야 통과 
				}else {
				console.log("통과");
				pwd33 = 0;
				}
		}
		        
		        //비밀번호 확인 유효성검사 (8자리 이상, 숫자포함, 영대문자포함, 영소문자포함, 특수문자 포함)
			   function chkPW2(){
				var reg = /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-]).{8,}$/;
				var pw2 = $("#pwd2").val();
				if(false === reg.test(pw2)) {
				//alert('비밀번호는 8자 이상, 20자이하 이어야 하며, 숫자/대문자/소문자/특수문자를 모두 포함해야 합니다.');
				pwd33 = 1; // 1이아니여야 통과 
				}else {
				console.log("통과");
				pwd33 = 0;
				}
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
		    	
					//============= "가입"  Event 연결 =============
				 $(function() {
					//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
					$( "#writeBtn" ).on("click" , function() {
						fncAddUser();
					});
				});	
				
				function fncAddUser() {
					
					var id=$("input[name='userEmail']").val();
					var pw=$("input[name='pwd']").val();
					var pw_confirm=$("input[name='pwd2']").val();
					var name=$("input[name='nick']").val();
					var successPhoneCkt = $(".successPhoneChk").text();
					console.log(   "successPhoneCkt" + successPhoneCkt ) ; 
					
					if(emailCheck == 1){
						alert("이미 등록된 이메일입니다.");
						return;
					}
					
					if(pwd33 != 0){
						//alert("비밀번호 체크"+pwd33);
						alert("비밀번호 형식이 올바르지 않습니다. ");
						return;
					}
					
					if(nick33 != 0){
						alert("이미 등록된 닉네임입니다.");
						return;
					}
					
					if(name == null || name.length <1){
						alert("닉네임은  반드시 입력하셔야 합니다.");
						return;
					}
					
					if ($("#phone").val() == "" || $("#phone").val().length != 11 || isNaN($("#phone").val())) {
						alert("휴대폰번호를 정확히 입력해 주세요.");
						return;
					}
		
					if(phone22 != 0){
						//alert("@@핸드폰번호 값 뭐나오니...."+phone22)
						alert("이미 가입된 핸드폰번호입니다. ");
						return;
					}
					
				   if(successPhoneCkt != '인증번호가 일치합니다.'   ){
					alert("인증번호가 일치하지 않습니다. ");
					return;
							}
				   
					
					if(id == null || id.length <1){
						alert("이메일은 반드시 입력하셔야 합니다.");
						return;
					}
					
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
					
					$("form").attr("method" , "POST").attr("action" , "/user/addUser").submit();
				}
		   
</script>		


</head>
<body>
	<div class="page_aticle">
		<div class="type_form member_join" id="user">
			<form id="form" name="frmMember">
				<div class="field_head">
					<h3 class="tit">회원가입</h3>
					<p class="sub"><span class="ico">*</span>필수입력사항</p>
				</div>
				<table class="tbl_comm">
				
					<tr class="fst">
						<th>이메일<span class="ico">*<span class="screen_out">필수항목</span></span></th>
						<td>
							<input type="text" class="form-control" id="userEmail" name="userEmail" placeholder="이메일" oninput = "checkUserEmail()" >
							<span id="helpBlock" class="id_ok">사용 가능한 이메일입니다.</span>
			 				 <span id="helpBlock" class="id_already">이미 등록된 이메일입니다.</span>
						</td>
					</tr>
					
					<tr>
						<th>비밀번호<span class="ico">*<span class="screen_out">필수항목</span></span></th>
						<td>
							<input type="password" class="form-control" id="pwd" name="pwd" placeholder="8~20자리, 숫자/영대,소문자/특수문자 포함" maxlength = "20" oninput = "chkPW()">
						</td>
					</tr>
					
					<tr>
						<th>비밀번호확인<span class="ico">*<span class="screen_out">필수항목</span></span></th>
						<td>
							<input type="password" class="form-control" id="pwd2" name="pwd2" placeholder="8~20자리, 숫자/영대,소문자/특수문자 포함" maxlength = "20" oninput = "chkPW2()">
						</td>
					</tr>
					
					<tr>
						<th>닉네임<span class="ico">*<span class="screen_out">필수항목</span></span></th>
						<td>
						      <input type="text" class="form-control" id="nick" name="nick" placeholder="1~12자리 닉네임"  maxlength = "12" oninput = "checkNick()">
						   <span id="helpBlock" class="id_ok2">사용 가능한 닉네임입니다.</span>
						  <span id="helpBlock" class="id_already2">이미 등록된 닉네임입니다.</span>
						  </td>
					</tr>
					
					
					<tr class="field_phone">
						<th>휴대폰<span class="ico">*<span class="screen_out">필수항목</span></span></th>
						<td>
							<div class="phone_num">
								<input id="phone" type="text" name="phone" title="전화번호 입력" placeholder="예) 01011111111" oninput = "checkUserPhone()" required/>
									<span id="phoneChk" class="doubleChk">인증번호 보내기</span><br/>
									<input id="phone2" type="text" name="phone2" title="인증번호 입력" disabled required/>
									<span id="phoneChk2" class="doubleChk">본인인증</span>
								
									<span class="point successPhoneChk" style ="line-height:50%" ><br>※ 휴대폰 번호 입력 후인증번호 보내기를 해주십시오.</span>
									<input type="hidden" id="phoneDoubleChk"/>
								</div>
							</td>
						</tr>
						

					<tr class="categNo">
						<th>관심사<span class="ico">*<span class="screen_out">필수항목</span></span></th>
						<td>
						<label class="checked">
								<input type="radio" name="categNo" value="1" checked="checked">
								<span class="ico"></span>운동
							</label>
							<label class="">
								<input type="radio" name="categNo" value="2">
								<span class="ico"></span>식습관
							</label>
							<label class="">
								<input type="radio" name="categNo" value="3">
								<span class="ico"></span>공부
							</label>
							<label class="">
								<input type="radio" name="categNo" value="4">
								<span class="ico"></span>취미
							</label>
							<label class="">
								<input type="radio" name="categNo" value="5">
								<span class="ico"></span>생활
							</label>
						</td>
					</tr>
				</table>
				
				
				
				<div id="formSubmit" class="form_footer">
					<div id="checkDiv" class="checkDiv"></div>
					<button type="button" class="btn active btn_join" id="writeBtn">가입하기</button>
				</div>
			</form>
		</div>
		
	</div>
</body>
</html>