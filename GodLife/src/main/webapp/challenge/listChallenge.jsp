<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
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
	<style>
        body {
            padding-top : 70px;
        }
        
        img{
        	width : 280px;
        	height : 250px;
        }
        
        div.thumbnail{
        	width:300px;
        	height:400px;
        	background-color: gray;
        }
        div#imgArea{
        	width:280px;
        	height:250px;
        	position:relative;
        	left:5px;
        	top:5px;
        }
        div#startEndDate{
        	position:relative;
        	bottom:250px;
        	left:10px;
        }
        
        h4#title{
        	overflow:hidden;
        	text-overflow:ellipsis;
        	white-space:nowrap;
        	width: 130px;
        	background-color: #8cd3e5;
        }
        
        h5#joinCount{
        	position:relative;
        	bottom:27px;
        	left:170px;
        }
       	
       	h5.info{
       		position:relative;
       		bottom:32px;
       		background-color: #8cd3e5;
       	}
       	.status{
       		position:relative;
       		bottom:32px;
       		left:130px;
       	}
       	h4#regDate{
       		position:relative;
       		bottom:36px;
       	}
       	
   	</style>
   	
   	<script type="text/javascript">
   		function fncGetList(currentPage){
   			$("#currentPage").val(currentPage)
   			$("form[name='challengeList']").attr("action","/challenge/listChallenge").submit();
   		}
   	</script>
<title>Insert title here</title>
</head>
<body>
<form name="challengeList" method="GET" >
	<div class="container">
		 <jsp:include page="/layout/toolbar.jsp" />
		 <c:forEach var="challenge" items="${challengeList}">
		  <div class="col-sm-6 col-md-4">
		    <div class="thumbnail">
		    <div id="imgArea">
		      <img src="/images/uploadFiles/${challenge.challengeThumbnailImg }" 
		      onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'">
		      	<div id="startEndDate">
		      			<h4>기간 : ${challenge.startDate} ~ ${challenge.endDate }</h4>
		      	</div>
		      </div>
		      <div class="caption">
			       <h4 id="title">${challenge.challengeTitle }</h4>
			       <h5 id="joinCount">참여자 수 : (${challenge.joinCount })</h5>
			       <h5 class="info">Host : ${challenge.hostNick }</h5>
			       <h5 class="info">관심사 : ${challenge.challengeCategName }</h5>
			       <c:if test="${challenge.challengeStatus == 0}">
			       		<h4 class="status">진행상황 : 시작전</h4>
			       </c:if>
			       <c:if test="${challenge.challengeStatus == 1}">
			       		<h4 class="status">진행상황 : 진행중</h4>
			       </c:if>
			       <c:if test="${challenge.challengeStatus == 2}">
			       		<h4 class="status">진행상황 : 종료</h4>
			       </c:if>
			       <h4 id="regDate">등록일 : ${challenge.challengeRegDate }</h4>
		      </div>
		    </div>
		  </div>
		</c:forEach>
	</div>
	<input type="hidden" id="currentPage" name="currentPage" value=""/>
	<div class="container">
		<jsp:include page="../common/pageNavigator_new.jsp"/>
	</div>
</form>
</body>
</html>