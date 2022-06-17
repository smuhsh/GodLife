<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>

<!-- Bootstrap Dropdown Hover CSS -->
<link href="/css/animate.min.css" rel="stylesheet">
<link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">

<style>
	@font-face {
			    font-family: 'oneMobile';
			    src: url('/resources/css/font/ONE Mobile Title.ttf') format('truetype');
				}
	body{
		
		font-family: 'oneMobile';
	}
	
	#header{
		 
		font-size:18px;
		text-align:center;
	}
	#img{
		text-align:center;
	}
	#percent{
		text-align:center;
	}
	#coupon{
		width:240px;
		height:35px;
	}
</style>

<script type="text/javascript">

	$(function(){
		$("button#upload").on("click",function(){
			
			const date = new Date();
			
			const protoDay = date.getDate()+"";
			
			const protoMonth = (date.getMonth()+1)+"";
			
			const year = date.getFullYear()+"";
			
			var month;
			var day;
			
			if(protoDay.length==1){
				day = "0"+protoDay;
			}else{
				day = protoDay;
			}
			if(protoMonth.length==1){
				month = "0"+protoMonth;
			}else{
				month = protoMonth;
			}

			var challengeNo = $(this).data("challengeno");
			var nowDate = year+"-"+month+"-"+day;
			var uploadDate = $(this).data("certidate");
			
			if(nowDate == uploadDate){
				self.location = "/challenge/addChallengeCertiImg?challengeNo="+challengeNo;
			}else{
				alert("인증 가능한 날짜가 아닙니다.");
				return;
			}
			
			
		});
	});
	
	$(function(){
		$("button#delete").on("click",function(){
			var certiImgNo=$(this).data("param");
			if(window.confirm("인증 이미지 삭제시 달성률에 적용되며.\n"+
							  "인증 가능한 날짜가 아니면 재 업로드가 불가능합니다.\n"+
							  "삭제 하시겠습니까?")){
			$.ajax({
				
				url:"/challenge/challengeRest/deleteChallengeCertiImg",
				method:"POST",
				dataType:"json",
				headers:{
					"Accept":"application/json",
					"content-Type":"application/json"
				},
				data:JSON.stringify({
					certiImgNo:certiImgNo
				}),
				success:function(json,status){
					if(${opt == 'my'}){
					 window.location.href =
						'/challenge/listChallengeJoinCertiImg?challengeNo=${challenge.challengeNo}&totalCertiCount=${challenge.totalCertiCount}';
					}else{
						self.location.reload();
					}
					
				}
				
			});
			}//if
		});
	});
	
</script>

<title>Insert title here</title>
</head>
<body>


<div class="page-header">
	<c:if test="${opt == 'my' }">
		<p id="header">참여중인 챌린지 인증 이미지 목록조회</p>
	</c:if>
	<c:if test="${opt == 'thirdParty' }">
		<p id="header">${joinChallenger.joinNick }님의 인증 이미지 목록조회</p>
	</c:if>
	<p id="percent">달성률 : ${joinChallenger.challengePercent } %</p>
	<c:if test="${opt == 'my' }">
		<button type="button" id="coupon" class="btn btn-default abc">인증이미지 대처 쿠폰 사용</button>
	</c:if>
</div>
<div>
  <div class="col-md-1"></div>
	  <div class="col-md-10">
	  		 <div style="display:flex;  flex-wrap: wrap;">
	  		 	<c:set var="i" value="0" />
				<c:forEach var="certiImg" items="${certiImgs }">
					<c:set var="i" value="${i +1}" />
				 <div style="flex-direction: row;">
				 	<div id="img">
				 		<c:if test="${opt == 'my' }">
					 		<p>인증 가능한 날짜</p>
					 		<p>${certiImg.certiDate}</p>
				 		</c:if>
				 		<c:if test="${opt == 'thirdParty' }">
					 		<p>${i } 번째 인증 이미지</p>
				 		</c:if>
				      <img style="width:150px; height:150px;" src="/resources/images/uploadFiles/${certiImg.certiImg }" 
						onerror="this.src='https://dummyimage.com/150x150/1af0d4/000000.gif'">
			     	</div>
			     	<c:if test="${opt == 'my'}">
			     	<center>
				     	<div>
				     		<c:if test="${certiImg.certiImg == 'temp.jpg'}">
					      		<button type="button" id="upload" class="btn btn-default abc"
					      		 data-certidate="${certiImg.certiDate}"
					      		 data-challengeno="${challenge.challengeNo}">업로드</button>
				     		</c:if>
				     		<c:if test="${certiImg.certiImg != 'temp.jpg'}">
				     			<button type="button" id="delete" class="btn btn-default abc"
					      		 data-param="${certiImg.certiImgNo }">삭제</button>
				     		</c:if>
				      	</div>
			      	</center>
			      	</c:if>
			      	<c:if test="${opt == 'thirdParty' }">
			      		<c:if test="${sessionScope.user.userEmail == challenge.hostEmail }">
			      			<c:if test="${certiImg.certiImg != 'temp.jpg'}">
				     			<button type="button" id="delete" class="btn btn-default abc"
					      		 data-param="${certiImg.certiImgNo }">삭제</button>
				     		</c:if>
			      		</c:if>
			      	</c:if>
			      	<c:if test="${sessionScope.user.userEmail != challenge.hostEmail }">
			      		<c:if test="${sessionScope.user.role == 2 }">
			      			<c:if test="${certiImg.certiImg != 'temp.jpg'}">
				     			<button type="button" id="delete" class="btn btn-default abc"
					      		 data-param="${certiImg.certiImgNo }">삭제</button>
				     		</c:if>
			      		</c:if>
			      	</c:if>
				</div>
				&nbsp;&nbsp;
				</c:forEach>
			</div>
	  	</div>
  <div class="col-md-1"></div>
</div>
</body>
</html>