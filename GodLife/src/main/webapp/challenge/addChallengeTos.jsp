<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

 <meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--   jQuery , Bootstrap CDN  -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
	
	<!--  CSS 추가 : 툴바에 화면 가리는 현상 해결 :  주석처리 전, 후 확인-->
	<style>
		@font-face {
		    font-family: 'oneMobile';
		    src: url('/resources/css/font/ONE Mobile Title.ttf') format('truetype');
		}
		
         body {
            
            background-size: cover;
        }
        .container{
        padding-top : 200px;
        font-family: 'oneMobile';
        }
        p {
        	font-size:14px;
        }
        
        .list-group-item{
        	height:500px;
        }
        
        
        
       
       
   	</style>
   	
   	<script type="text/javascript">
   		$(function(){
   			
   			$("button#addChallenge").on("click",function(){
   				$(self.location).attr("href","/challenge/addChallenge");
   			});
   			
   			$("button#cancel").on("click",function(){
   				$(self.location).attr("href","/main.jsp");
   			});
   			
   		});
   	</script>

</head>
<body>
<jsp:include page="/layout/toolbar.jsp" />
	
	
	<div class="container">
		<div class="row">
		  <div class="col-md-2"></div>
		  <div class="col-md-8">
		   <h3 class="text-center bg-info">챌린지 이용 약관</h3>
				
				<ul class="list-group">
				  <li class="list-group-item">
				    <p class="text-left">1.등록 완료된 챌린지에 대해서 관리자는 절대 관여하지 않습니다.</p>
				  	<p class="text-left">2.챌린지를 등록 했다면 성실히 참여해 주시기 바랍니다.</p>
				  	<p class="text-left">3.비매너 행위를 하여 신고 횟수가 누적될시 이용제제의 대상이 됩니다.</p>
				  	<p class="text-left">4.챌린지 생성후 시작날짜가 되면 자동으로 진행되며 삭제가 불가능합니다.</p>
				  	<p class="text-left">5.기간이 짧아서 인증주기가 포함되어있지 않아 포인트 환급에 문제가 생겨도 책임 지지않습니다.</p>
				  </li>
				</ul>
		  	<center>
			  <div class="buttonGroup">
			  		<button type="button" id="cancel" class="btn btn-danger">취소</button>
			  		&nbsp;&nbsp;&nbsp;
			  		<button type="button" id="addChallenge" class="btn btn-primary">챌린지 등록</button>
			  </div>
		  </div>
			</center>
		  <div class="col-md-2"></div>
		</div>
	</div>

</body>
</html>