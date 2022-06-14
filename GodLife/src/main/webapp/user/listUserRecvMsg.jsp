<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>받은 쪽지함</title>
<link rel="stylesheet" href="/css/listUserRecvMsg.css" />
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
</head>
<body>

<div class="myMessageWrap">
 
    <form id="messageForm" method="get" action="deleteMyMessage" >
    
	<div class="head_aticle">
		<h2 class="tit">받은 쪽지함</h2>
	</div>
	
	<div class="mainMessage">
	<br>
	
		<div class="message_Btn">
			<span class="deleteBtn" id="deleteBtn"><a href="#">&emsp;삭제&emsp;</a></span>
		</div><!-- message_Btn -->
		
		<table border="3"  id="followListTable" cellpadding="5" width="850px" bordercolor="#ededed"  align="center" frame="hsides" rules="rows">
		
		<!-- 쪽지 틀 -->
			<tr class="message_title" style="height:35px;">
				<td align="center">No</td>
				<td align="center">보낸이</td>
				<td align="center" style="width: 200px;">제목</td>
				<td align="center">작성일</td>
			</tr>
		</table>
				
				
		<div class="messageListDiv">
				<table border="4"   id="messageListTable" cellpadding="5" width="850px" bordercolor="#ededed"  align="center" frame="hsides" rules="rows">
					<tbody>
					  <c:set var="i" value="0" />
					  <c:forEach var="msg" items="${list}">
						<c:set var="i" value="${ i+1 }" />
						
						<tr>
						  <td align="center">${ i }</td>
						  <td align="center"  title="Click : 회원정보 확인">${msg.nick} 
						  <td align="center"  style="width: 200px;">${msg.title}</td>
						  <td align="center">${msg.regDate}</td>
						  <td align="center">
						  	<input type="hidden" value="${msg.recvEmail}">
						  </td>
						</tr>
			          </c:forEach>
			        
			        </tbody>
				</table>


		</div>
		
	</div><!-- mainMessage -->
	</form>
</div><!-- myMessageWrap -->
		
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>	
<script src="/jaju/js/myMessage.js"></script>
</body>
</html>