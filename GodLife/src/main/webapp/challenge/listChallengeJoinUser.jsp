<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
<title>Insert title here</title>

<style>
	@font-face {
		    font-family: 'oneMobile';
		    src: url('/resources/css/font/ONE Mobile Title.ttf') format('truetype');
		}
		
		.container{
			 font-family: 'oneMobile';
		}
		
		 img#profile{
        	width:40px;
        	height:40px;
        	border-radius: 30px 30px 30px 30px
        }
        
        .join-attr{
        	position:relative;
        	top:10px;
        	text-align:center;
        }
        
        .join-attr-th{
        	text-align:center;
        	position:relative;
        	top:5px;
        }
        
        #profile-td{
        	width:60px;
        }
        
        #header{
        	font-size:18px;
        	text-align:center;
        }
        
        a#link{
        	text-decoration: none;
        	color:black;
        }
</style>

</head>

<body>
	<div class="container">
	
		<div class="page-header">
		  <p id="header">챌린지 참여자 목록</p>
		</div>
	
	 	<table class="table table-bordered">
			<tr>
				<th class="join-attr-th"><p>프로필</p></th>
				<th class="join-attr-th"><p>닉네임</p></th>
				<th class="join-attr-th"><p>달성률</p></th>
				<th class="join-attr-th"><p>인증 이미지</p></th>
			</tr>
			<c:forEach var="joinChallenger" items="${joinChallengerList }">
				<tr>
					<td id="profile-td">
						<img id="profile" src="/resources/images/uploadFiles/${joinChallenger.profile }"
			        		onerror="this.src='/resources/images/Default-Profile-Picture-Free-PNG-Image.png'">
					</td>
					<td>
						<p class="join-attr">${joinChallenger.joinNick }</p>
					</td>
					<td>
						<p class="join-attr">${joinChallenger.challengePercent }%</p>
					</td>
					<td>
						<p class="join-attr">
							<a id="link" 
							href="/challenge/listChallengeJoinCertiImg?
							challengeNo=${joinChallenger.challengeNo }&userEmail=${joinChallenger.email}">
								목록보기
							</a>
						</p>
					</td>
				</tr>
			</c:forEach>
		
		</table>	
	</div>
</body>
</html>