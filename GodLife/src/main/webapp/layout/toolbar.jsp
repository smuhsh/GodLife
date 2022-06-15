<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GodLife</title>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

<link rel="stylesheet" href="/css/toolbar.css" />

<style type="text/css">
</style>

</head>
<body>

	<!-- header -->
	<div class="header">

		<div align="center" class="header_bar">
			<ul class="member_items">
			
				<c:if test="${user.userEmail== null}">
					<li class="sign_up_item"><a href="/user/addUser">회원가입</a></li>
					<li class="sign_in_item"><a href="/user/login">로그인</a></li>
				</c:if>


				<c:if test="${user.userEmail != null}">
				
	             <li class="service_center_item">
					<a class="service_center" href="/user/getUser?userEmail=${sessionScope.user.userEmail}"> 마이페이지</a>
					<button type="button" class="service_center_icon"></button>
					
					<ul class="service_center_detail">
						<li><a href="/user/getUser?userEmail=${sessionScope.user.userEmail}">개인정보 조회</a></li>
						<li><a href="링크">포인트 구매</a></li>
						<li><a href="링크">상품권 구매</a></li>
						<li><a href="링크">쿠폰 구매</a></li>
						<li><a href="링크">기부하기</a></li>
						
						</ul>
						</li>
	                 
						</c:if>
					
					<c:if test="${user.userEmail != null}">
					<li class="sign_in_item"><a href="/user/logout">로그아웃</a></li>
				</c:if>

			</ul>
		</div>

		<div class="logo_image">
		<h1 class="logo">
		<a href="/"> 
				<img src="/images/uploadFiles/GodLife 임시로고.png" />
				</a>
		</h1>
		</div>
		

		<div class="menuDiv">

			<ul class="menu">
				<!-- == main -->
				<li class="all_category header_a_li">
					<!-- == MAIN01 == ��泥댁뭅��怨�由� --> <a href="#" class="button">
						<button type="button" class="menu_button"></button> 챌린지 관심사
				</a> <!-- ��泥댁뭅��怨�由� hover�� ������. -->
					<ul class="all_category_item" id="all_category_item">

						<li><a class="1">운동</a></li>
						<li><a class="2">식습관</a></li>
						<li><a class="3">공부</a></li>
						<li><a class="4"> 취미</a></li>
						<li><a class="5"> 생활</a></li>
					</ul>
				</li>

					<li class="new-product-category">
					<a	href="/operator/listOperatorEvent" style="text-decoration:none;">이벤트</a></li>
					
					<li class="new-product-category">
					<a	href="/operator/listOperatorNotice" style="text-decoration:none;">공지사항</a></li>
					
					<li class="new-product-category">
					<a	href="/operator/listOperatorFaqs" style="text-decoration:none;">FAQ</a></li>
					
			</ul>

		</div>
	</div>



	<script type="text/javascript"
		src="http://code.jquery.com/jquery-3.6.0.min.js"></script>
		
	<script type="text/javascript">
	
		$(document).ready(function() {
			
			//전체 카테코리 호버 이벤트
			$(".menu>.all_category").mouseover(function() {
				$(this).children(".all_category_item").stop().slideDown();
			});
			
			$(".menu>.all_category").mouseleave(function() {
				$(this).children(".all_category_item").stop().slideUp();
			});
			
		
		//마이페이지 호버이벤트
		$(".member_items .service_center_item").mouseover(function() {
			$(this).children(".service_center_detail").stop().slideDown();
		});
		
		$(".member_items .service_center_item").mouseleave(function() {
			$(this).children(".service_center_detail").stop().slideUp();
		});
		
	
		$('#all_category_item>li').click(function(){
			//alert
			location.href = '/challenge/listChallenge?searchCondtion=' + $(this).children('a').attr('class');
		});
		
});	
		
		//header 고정 이벤트
	    $(function(){
	        var top_pos= $('.menuDiv').offset().top;
	        win = window;
	        $(win).on('scroll',
	           function(){
	             var pos = $(this).scrollTop();
	             
	             if(pos >= top_pos){
	                $('.menuDiv').addClass('fix');
	             } 
	              else{
	                $('.menuDiv').removeClass('fix');
	              }
	        });         
	      });
		
		
		
	</script>
</body>
</html>