<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="/css/mypage.css" />
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>

<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>


</head>
<body>
	<!-- 가로메뉴바,세로메뉴바 고정 -->
	<!-- 프로필, 스크랩, 팔로우, 메세지 -->
	
	<!-- controller에서 넘어온 id/pg ,id는 임의로 만들어둔 변수명. 나중에 session값 처리-->
	<input type="hidden" id="id" name="id" value="${memId}">

	<div id="myPageTop" class="page_aticle mypage_top">
		<h2 class="screen_out">마이페이지</h2>
		
		<div class="mypagetop_user">

			<div class="inner_mypagetop">
			

				<div class="grade_user">

					<div class="grade_wrap" >
						<div class="grade_logo class0">회원</div>
					</div>


					<!--판매중 동적태그-->					
					<div id="deal0">
						<a id="deal" href="/jaju/mypage/myDealRecode" class="link_wrap"></a>
					</div>
					
							<p class="info"></p>
							<div class="spacer"></div>
					
				</div>
				
				<div class="list_mypage">
					<div class="list">
						
						<div id="deal_count" href="/jaju/mypage/myScrap" class="link_wrap">
							<div class="link_title">거래내역</div>
							
								<a href="/jaju/mypage/mySaleRecode" id="sale" ></a>
								<a href="/jaju/mypage/myBuyRecode" id="buy" ></a>
						
						</div>
						
						<a id="scrap_count" href="/jaju/mypage/myScrap" class="link_wrap">
							<div class="link_title">찜목록</div>
							<p class="info"></p>
							<div class="spacer"></div>
						</a>

						<!-- 팔로우 창은 window open으로 띄우기 -->
						<a id="follow_count" href="/jaju/mypage/myFollow" class="link_wrap">
							<div class="link_title">팔로우</div>
							<p class="info"></p>
							<div class="spacer"></div>
						</a> 
						
						<a id="message_count" href="#" class="link_wrap" onclick="javascript:messageMove()">
							<div class="link_title">쪽지</div>
							<div class="spacer"></div>
							<p class="info">
								<span class="expire" id="expire"></span>
							</p>
						</a>
						
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- <div class="container"></div> -->
 
 
 
<script>
function messageMove(){
	 //팝업 창 가운데 띄워주기
	 var popupWidth=520;
	 var popupHeight=520;
	 var popupX = (window.screen.width/2)-(popupWidth/2);
	 // 만들 팝업창 width 크기의 1/2 만큼 보정값으로 빼주었음
	 var popupY= (window.screen.height/2)-(popupHeight/2);
	 // 만들 팝업창 height 크기의 1/2 만큼 보정값으로 빼주었음
	
	
	window.open("/jaju/mypage/myMessage", 
			 "my_Message", 
			 'status=no, height=' + popupHeight  + ', width=' + popupWidth  + ', left='+ popupX + ', top='+ popupY); } 
 
 </script>
<script type="text/javascript" src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
<script type="text/javascript" src="/jaju/js/mypageMain.js"></script>
</body>
</html>