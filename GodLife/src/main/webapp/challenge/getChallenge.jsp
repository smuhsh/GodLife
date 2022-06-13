<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!--   jQuery , Bootstrap CDN  -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
	
	<!--  CSS 추가 : 툴바에 화면 가리는 현상 해결 :  주석처리 전, 후 확인-->
	<link rel="stylesheet" href="/resources/css/getChallenge.css" type="text/css">
	<link rel="stylesheet" href="/resources/css/title.css" type="text/css">
	<link rel="stylesheet" href="/resources/css/search.css" type="text/css">

<title>Insert title here</title>
</head>
<body>
<form name="challenge">
				
	<div class="container">
	<jsp:include page="/layout/toolbar.jsp" />
			  <div class="col-xs-6 col-sm-1">
			  </div>
			  <div class="col-xs-6 col-sm-10">
			  	<div class="page-header">
			  		<h2>챌린지 조회</h2>
				</div>
				
			    <div class="thumbnail">
			    <div id="imgArea">
			    	 <img src="/resources/images/uploadFiles/${challenge.challengeThumbnailImg }" 
				      onerror="this.src='https://dummyimage.com/850x230/1af0d4/000000.gif'">
			    </div>
			      <div class="caption">
			        <p id="title">${challenge.challengeTitle }</p>
			        <p class="rightInfo" id="joinCount">참가 인원수 : ${challenge.joinCount } 명</p>
			        <p class="rightInfo" >시작일 : ${challenge.startDate } </p>
			        <p class="rightInfo" >종료일 : ${challenge.endDate }</p>
			        <div  id="certiyCycle">
				        <p class="rightInfo" >인증 주기 : 
				        <c:forEach var="certiCycle" items="${challenge.certiCycleName }">
				        	${certiCycle }
				        </c:forEach>
				        </p>
			        </div>
			        <p class="rightInfo" >입장 포인트 : ${challenge.joinPoint }</p>
			        <p class="rightInfo" >총 포인트 : ${challenge.joinPoint * challenge.joinCount}</p>
			        <p class="rightInfo" ></p>
			        
			        <div id="leftInfo">
			        	<p>호스트</p>
			        	<div id="hostArea">
			        		<img id="profile" src="/resources/images/uploadFiles/${hostUser.profileImg }"
			        		onerror="this.src='/resources/images/Default-Profile-Picture-Free-PNG-Image.png'">
			        		<p id="nick">${challenge.hostNick }</p>
			        	</div>
			        	<p id="categ">관심사 : ${challenge.challengeCategName }</p>
			        	
			        	<div>
			        		<p id="detail">챌린지 소개</p>
			        		<textarea readonly>${challenge.challengeDetail }</textarea>
			        		
			        		<p id="rule">챌린지 규칙</p>
			        		<textarea readonly>${challenge.challengeRule }</textarea>
			        	</div>
			        </div>
			        <div id="centerButton">
			        		<button type="button" class="btn btn-default abc">나가기</button>
				        		<c:forEach var="i" begin="1" step="1" end="20">
				        		&nbsp;
				        		</c:forEach>
			        		<button type="button" class="btn btn-default abc">참여하기</button>
			        </div>
			        
			        <div id="rightButton">
			        	<button type="button" class="btn btn-default abc">찜하기</button>
			        	<button type="button" class="btn btn-default abc">공유하기</button>
			        </div>
			      </div>
			    </div>
				
			
			 </div>
			 
			 <div class="col-xs-6 col-sm-1">
			 </div>
		</div>
	
</form>
</body>
</html>