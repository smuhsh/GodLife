<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보낸 쪽지 상세조회</title>
<link rel="stylesheet" href="/css/addUserMsgView.css" />
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

</head>
<body>

<!-- 쪽지 viewer -->
<form id="myMessageFormForm" name="myMessageFormForm">

	  <input type="hidden" name="msgNo" id="msgNo" value="${msg.msgNo}"> 
		
	<div class="head_aticle">
		<h2 class="tit" align="center">보낸 쪽지 상세조회</h2>
	</div>
	
	<table border="2" id="myMessageFormTable" class="myMessageFormTable" bordercolor="#ddd" cellspacing="0" cellpadding="5" align="center" frame="hsides" rules="rows"
			>
			
		<tr>
			<td align="left" colspan="3" class="title">받은 사람 : 
			<input type="text" id="nick" name="nick"  placeholder="${msg.nick}"  readonly style="width:450px; height:20px; background-color: #f2f2f2; border:0; outline:0;">
			<div id="message_subject_div_check"></div></td>
		</tr>
		
		
		<tr>
			<td align="left" colspan="3" class="title">제목 : 
			<input type="text" id="title" name="title" placeholder="${msg.title}"  readonly style="width:450px; height:20px; background-color: #f2f2f2; border:0; outline:0;">
			<div id="message_subject_div_check"></div></td>
		</tr>
		
		<tr>	
			<td class="content" colspan="3" valign="top">
				<div class="content_div" align="left">
					<pre><textarea name="detail" id="detail" placeholder="${msg.detail }"  readonly style="width:450px; height:240px; 
							border:0; resize:none; background-color: #f4f4f4;"></textarea></pre>
				</div>
				<div id="content_div_check"></div>
			</td>
		</tr>
		
		<tr>
			<td colspan="3" align="center" class="moveToListTd">
				<!--  <button class="sendBtn" id="sendBtn" ><a href="/jaju/mypage/myMessageSend?pg=1">답장</a></button>  -->
				<!-- <button type="reset"   id="resetBtn" >확인 </button>resetBtn
				<!-- <span class="backBtn" id="backBtn"><a href="/jaju/mypage/myMessage?pg=1">&emsp;쪽지함 이동&emsp;</a></span> -->
			</td>
		</tr>
	
	</table>
</form>
<!-- 이부분은 js에서 append 나 html로 붙여넣기하기 -->
		
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>	

<script type="text/javascript" >

//============= "확인"  Event 처리 및  연결 =============
$(function() {
	//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
	$("#resetBtn").on("click" , function() {
		$("form")[0].reset();
	});
});	

</script>

</body>
</html>