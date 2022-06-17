<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
	
<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->
   	
<!-- 왼쪽 레이아웃 삽입-->
<jsp:include page="/user/mypageMain.jsp" />

<!-- CSS-->
<link rel="stylesheet" href="/css/getUser.css" />


<style>

	body {
           padding-top : 50px;
       }
       
      #getUser{
       padding-top : 130px;
       }
       
       
       .id_ok{
	color:#064ACB;
	display: none;
	font-size : 14px;
	}
	
	.id_already{
	color:#064ACB; 
	display: none;
	font-size : 14px;
	}
	
	.id_ok2{
	color:#064ACB;
	display: none;
	font-size : 14px;
	}
	.id_already2{
	color:#064ACB; 
	display: none;
	font-size : 14px;
	}
	
	.doubleChk{
	color:#064ACB;
	font-size : 14px;
	
	}
	
 </style>

<style>

img
{
border : 5px solid white;
width : 200px;
height : 200px;
float : center;
}

</style>

<!--  자바스크립트 -->
<script type="text/javascript">

		
		   //==>"받는사람 이메일" 있는지 없는지 검사 
		   
		   
		   
		   
		
			//============= "쪽지보내기"  Event 연결 =============
		 $(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$( "#writeBtn" ).on("click" , function() {
				fncAddUserMsg();
			});
		});	
		   
		
		function fncAddUserMsg() {
			
			var recvEmail=$("input[name='recvEmail']").val();
			var name=$("input[name='title']").val();
			
			
			if(recvEmail == null || recvEmail.length <1){
				alert("이메일은  반드시 입력하셔야 합니다.");
				return;
			}
			
			if(name == null || name.length <1){
				alert("제목은  반드시 입력하셔야 합니다.");
				return;
			}
			
			$("form").attr("method" , "POST").attr("action" , "/user/addUserMsg").submit();
			alert("전송이 완료되었습니다")
		}
		
		
		
		//============= "내용 글자수 검사" =============
			
			
		$(document).ready(function() {
	    $('#detail').on('keyup', function() {
	        $('#test_cnt').html("("+$(this).val().length+" / 500)");
	 
	        if($(this).val().length > 500) {
	            $(this).val($(this).val().substring(0, 500));
	            $('#test_cnt').html("(500 / 500)");
	        }
	    });
	});
		
		
	</script>
	
<title>쪽지 보내기</title>
</head>
<body>
	<div class="page_aticle">
		<div class="type_form getUser" id="getUser">
			<form id="form" name="frmMember" >
			
			<input type="hidden" name="sendEmail" value="${msg.sendEmail}"/>
			
				<div class="field_head">
					<h3 class="tit">쪽지 보내기</h3>
				</div>
				
				<table class="tbl_comm">
				
				<tr>
						<th>이메일</th>
						<td>
						      <input type="text" class="form-control" id="recvEmail" name="recvEmail" oninput = "checkNick()"  >
						  </td>
					</tr>
				
					<tr>
						<th>제목</th>
						<td>
						      <input type="text" class="form-control" id="title" name="title" oninput = "checkNick()"  >
						  </td>
					</tr>
					
					<tr class="detail">
						<th>내용</th>
						<td>
								<textarea id = "detail" name="detail" cols="30" rows="10" ></textarea>
								<div id="test_cnt" style ="font-size : 13px">(0 / 500)</div>
						</td>
					</tr>
					
					</table>
					
	
				<div id="formSubmit" class="form_footer">
					<div id="checkDiv" class="checkDiv"></div>
					<button type="button" class="btn active btn_join" id="writeBtn">전송</button>
				</div>
			</form>
		</div>
		
		
	</div>
</body>
</html>