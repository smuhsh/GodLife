<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>

<html lang="ko">

<head>
<meta charset="UTF-8">

<!-- 참조 : http://getbootstrap.com/css/   참조 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<!-- Bootstrap Dropdown Hover CSS -->
<link href="/css/animate.min.css" rel="stylesheet">
<link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
<!-- Bootstrap Dropdown Hover JS -->
<script src="/javascript/bootstrap-dropdownhover.min.js"></script>
<!-- jQuery UI toolTip 사용 CSS-->
<link rel="stylesheet"
   href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- jQuery UI toolTip 사용 JS-->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<!-- CDN(Content Delivery Network) 호스트 사용 -->
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<!--  ///////////////////////// CSS ////////////////////////// -->

<!--  ///////////////////////// JavaScript ////////////////////////// -->
<link rel="stylesheet" href="/css/admin.css" type="text/css">

<!--  ///////////////////////// JavaScript ////////////////////////// -->
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<!-- CDN(Content Delivery Network) 호스트 사용 -->
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<!-- iamport.payment.js -->
  <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
  
<style>
	@font-face {
			    font-family: 'oneMobile';
			    src: url('/resources/css/font/ONE Mobile Title.ttf') format('truetype');
			}
	
	
	body {

	   font-weight: bold; 
	   font-family: 'oneMobile';
	}
	
	fieldset {
	   width: 400px;
	   text-align: center;
	   backgrond-color: white;
	   font-family: 'oneMobile';
	}
		
	.detail {
	   color : white ;
	   margin : 20px;
	   width: 380px;
	   height: auto;
	   padding-top: 1px;
	   font-weight: bold; 
	   font-family:impact;
	   font-family: 'oneMobile';
	}

	.backdefault{
	width: 180px;
	height: 180px;
	background-image: url("/images/uploadFiles/DefaultBackGround.PNG");
	align-items:center;
	display: flex;
	 justify-content:center;
	}
	.backbronze{
	width: 180px;
	height: 180px;
	background-image: url("/images/uploadFiles/badgeBronze.jpg");
	align-items:center;
	display: flex;
	 justify-content:center;
	}
	.backsilver{
	width: 180px;
	height: 180px;
	background-image: url("/images/uploadFiles/badgeSilver.jpg");
	align-items:center;
	display: flex;
	 justify-content:center;
	}
	.backgold{
	width: 180px;
	height: 180px;
	background-image: url("/images/uploadFiles/badgeGold.jpg");
	align-items:center;
	display: flex;
	 justify-content:center;
	}
	
	.images{
	width: 147px;
    height: 145px; 
    border-radius: 70%;
    overflow: hidden;
	}
	

</style>

	<script type="text/javascript">	

	//============= images 에 배지 상세 정보(관리자 모드/수정 삭제로 들어가기)  Event  처리(Click) =============
	 //==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
	$(function() { 
	     $( "button.btn.btn-default.allList" ).on("click" , function() {
	    	 self.location ="/myBadge/getBadgeAllList.jsp";
	     });
	});

	 
	 
	 
	 
	//============= productName 에 상품정보보기 Ajax이용 (일반 회원용)  Event  처리(Click) =============
	$(function(){
		$( ".myBadgeABDetail" ).on("click", function() {
			var productNo = $(this).data("value");

			 $.ajax( 
	                 {
	                    url : "/myBadge/json/getBadgeMyList/"+myBadgeNo,
	                    method : "GET",
	                    dataType : "json",
	                    headers : {
	                       "Accept" : "application/json",
	                       "Content-Type" : "application/json"
	                    },

	                    success : function(JSONData , status) {
	                    	
	                       const displayDetail = 
	                    	   `<div class="row">
		                         	<div class="detail">상품 명 :&nbsp \${JSONData.MyBadgeName} </div>
		                         	<div class="detail">상품 상세정보: <br/> \${JSONData.MyBadgeDetail} </div>
	                     		</div>`
	                       $("div.detail").remove();
	                       $( "#"+JSONData.productNo+"" ).append(displayDetail);
	                       console.log(JSONData , status);
	                 }
	              }); 
	        });
		//=========================================================//
		 //==> prodNo LINK Event End User 에게 보일수 있도록 
	    $( "div#badgeName" ).css("color" , "red");
	    $("div#badgeName").css("font-size", "20px");
	});	
	
	
	</script>
	
</head>
<!-- body시작 -->
<body>

   <form class="form-horizontal" style="margin: 40px;">
      <!-- ToolBar Start /////////////////////////////////////-->
      <jsp:include page="/layout/toolbar.jsp" />
      <!-- ToolBar End /////////////////////////////////////-->
	  
      <!--  화면구성 div Start /////////////////////////////////////-->
      <div class="container" >
		<br><br><br><br><br><br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<div class="row" style="font-size:30px;">
		  <h2 class="col-md-12" align="center" style="font-size: larger;">배지 전체 목록 (유저 모드)</h2>			
		</div>
		<br>
		<br>
			<div class="row">
				<a class="btn btn-default allList" href="/badge/getBadgeList" role="button">배지 전체목록 조회</a>
			</div>
		<br>
		<!-- 배지들이 어떤 의미를 가지는지? 이미지 Start-->
			<div class="row">
				<img src="/images/크립토펑크.png" style="width:1000px;" alt="..." class="img-thumbnail"><br>
				CrytoPunk(크립토 펑크) 배지를 수집하세요.
			</div>
		<!-- 배지들이 어떤 의미를 가지는지? 이미지 End -->	
		<br><br><br>
		<!-- 활동 배지 Collapse로 설명 구현 Start -->
			<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample1" aria-expanded="false" aria-controls="collapseExample1">
			  활동 배지
			</button>
			<div class="collapse" id="collapseExample1">
			  <div class="well">
			   활동 배지획득을 위한 방법은 아래와 같습니다. <br>
			   1) 가입완료			: 회원 가입시 획득 가능 					(가입시 한번 획득 가능) <br>
			   2) 친구야			: 친구 요청, 상대의 요청 수락 후 획득 가능 	(친구추가완료 = 활동 횟수량 +1) <br>
			   3) 챌린지100%달성	: 챌린지 100%달성 후 획득 가능 			(챌린지100%완료 = 활동 횟수량 +1) <br>
			   4) 인증왕			: 챌린지 진행 중, 인증사진 업로드시 획득 가능 	(챌린지 인증사진 업로드 = 활동 횟수량 +1) <br>
			   5) 레벨배지			: 챌린지 참가 후, 챌린지 완료 시 획득 가능 	(챌린지 완료 = 활동 횟수량 +1)
			  </div>
			</div>
		<!-- 활동 배지 Collapse로 설명 구현 End -->
		<br>
		<br>
		
		<!-- 활동 배지 -->
		<div class="row">
		<!-- 배지 act count 1 이상일때 나오게 하기 if문 거는 곳 -->

		
		<c:set var="i" value="0"/>
			  <c:forEach var="myBadge" items="${list1}">
            	<c:set var="i" value="${ i+1 }" />      
            	 <!-- 이미지에 for문으로 돌아간 배지 정보 담기 Start -->
            	 <div class="col-md-3" style="height: auto; width: auto;" align="center">
            	 <!-- Grade가 0일때 Default 배경이미지  -->
	            	<c:if test="${myBadge.actCount == 0 }">
						  <div class="backdefault">
						  	<img myBadgeNo="${ myBadge.badgeNo }"   class="images" 
						  		  src="/images/uploadFiles/${myBadge.badge.badgeImg }"
				                  onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'" ><br/>   
				         </div>
			         배지이름 : ${myBadge.badge.badgeName}<br>
			         활동 횟수량 : ${myBadge.actCount}
			         </c:if>
			     
			    <!-- Grade가 1일때 동색 배경이미지  -->
	            	<c:if test="${ myBadge.actCount > 0 && myBadge.actCount < 11 }">
					  <div class="backbronze">
					  	<img myBadgeNo="${ myBadge.badgeNo }"   class="images" 
					  		  src="/images/uploadFiles/${myBadge.badge.badgeImg }"
			                  onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'" ><br/>
			         </div>
			         배지이름 : ${myBadge.badge.badgeName}<br>
			         활동 횟수량 : ${myBadge.actCount}
		        <!-- Grade가 2일때 은색 배경이미지  -->
			         </c:if>
			         <c:if test="${myBadge.actCount > 10 && myBadge.actCount < 21}">
					  <div class="backsilver">
					  	<img myBadgeNo="${ myBadge.badgeNo }"   class="images" 
					  		  src="/images/uploadFiles/${myBadge.badge.badgeImg }"
			                  onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'" ><br/>
			                  
			         </div>
			         배지이름 : ${myBadge.badge.badgeName}<br>
			         활동 횟수량 : ${myBadge.actCount}
			         </c:if>
		         <!-- Grade가 3일때 금색 배경이미지  -->
			         <c:if test="${myBadge.actCount > 20 }">
					  <div class="backgold">
					  	<img badgeNo="${ badge.badgeNo }"   class="images" 
					  		  src="/images/uploadFiles/${myBadge.badge.badgeImg }"
			                  onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'" ><br/>
			                  
			         </div>
			         배지이름 : ${myBadge.badge.badgeName}<br>
			         활동 횟수량 : ${myBadge.actCount}
			         </c:if>
		         <!-- (유저용) Ajax로 배지 상세 정보 보기 클릭 Start //Ajax나오게 하려면 data-value가 필요 -->   
		               <i  type ="hidden" class="myBadgeDetailIB" id="${myBadge.badge.badgeImg }" style="font-size:15px;" 
		               		data-value="${ myBadge.badgeNo }" 
		               		title="Click : 배지정보 확인" 
		               		value="${myBadge.badgeNo}" >

		               </i>
		          <!-- (유저용) Ajax로 배지 상세 정보 보기 클릭 End -->  
		               <input type="hidden" value="${myBadge.badgeNo}" >
		               <input type="hidden" name="userEmail" value="${user.userEmail}" />
				  </div>
				 <!-- 이미지에 for문으로 돌아간 배지 정보 담기 End -->
			  </c:forEach>
		</div>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<!-- 관심사 배지 Collapse로 설명 구현 Start -->
			<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample2" aria-expanded="false" aria-controls="collapseExample2">
			  관심사 배지
			</button>
			<div class="collapse" id="collapseExample2">
			  <div class="well">
			   관심사 배지획득을 위한 방법은 아래와 같습니다. <br>
			   1) 운동왕			: 운동 관심사, 챌린지 완료시 획득 가능		(해당 관심사 챌린지 완료 = 활동 횟수량 +1) <br>
			   2) 척척박사			: 공부 관심사, 챌린지 완료시 획득 가능		(해당 관심사 챌린지 완료 = 활동 횟수량 +1) <br>
			   3) 생활의달인		: 생활 관심사, 챌린지 완료시 획득 가능		(해당 관심사 챌린지 완료 = 활동 횟수량 +1) <br>
			   4) 식샤를합시다		: 식습관 관심사, 챌린지 완료시 획득 가능		(해당 관심사 챌린지 완료 = 활동 횟수량 +1) <br>
			   5) 즐거운인생		: 취미 관심사, 챌린지 완료시 획득 가능		(해당 관심사 챌린지 완료 = 활동 횟수량 +1)
			  </div>
			</div>
		<!-- 활동 배지 Collapse로 설명 구현 End -->
		<br>
		<br>
		
		<!-- 관심사 배지 -->
		<div class="row">
		<!-- 배지 act count 1 이상일때 나오게 하기 if문 거는 곳 -->
		
		
		<c:set var="i" value="0"/>
			  <c:forEach var="myBadge" items="${list2}">
            	<c:set var="i" value="${ i+1 }" />      
            	 <!-- 이미지에 for문으로 돌아간 배지 정보 담기 Start -->
            	 <div class="col-md-3" style="height: auto; width: auto;" align="center">
            	 <!-- Grade가 0일때 Default 배경이미지  -->
	            	<c:if test="${myBadge.actCount == 0 }">
						  <div class="backdefault">
						  	<img myBadgeNo="${ myBadge.badgeNo }"   class="images" 
						  		  src="/images/uploadFiles/${myBadge.badge.badgeImg }"
				                  onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'" ><br/>
				         </div>
			         배지이름 : ${myBadge.badge.badgeName}<br>
			         활동 횟수량 : ${myBadge.actCount}
			         </c:if>
			     
			    <!-- Grade가 1일때 동색 배경이미지  -->
	            	<c:if test="${ myBadge.actCount > 0 && myBadge.actCount < 11 }">
					  <div class="backbronze">
					  	<img myBadgeNo="${ myBadge.badgeNo }"   class="images" 
					  		  src="/images/uploadFiles/${myBadge.badge.badgeImg }"
			                  onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'" ><br/>
			         </div>
			         배지이름 : ${myBadge.badge.badgeName}<br>
			         활동 횟수량 : ${myBadge.actCount}
		        <!-- Grade가 2일때 은색 배경이미지  -->
			         </c:if>
			         <c:if test="${myBadge.actCount > 10 && myBadge.actCount < 21}">
					  <div class="backsilver">
					  	<img myBadgeNo="${ myBadge.badgeNo }"   class="images" 
					  		  src="/images/uploadFiles/${myBadge.badge.badgeImg }"
			                  onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'" ><br/>
			         </div>
			         배지이름 : ${myBadge.badge.badgeName}<br>
			         활동 횟수량 : ${myBadge.actCount}
			         </c:if>
		         <!-- Grade가 3일때 금색 배경이미지  -->
			         <c:if test="${myBadge.actCount > 20 }">
					  <div class="backgold">
					  	<img badgeNo="${ badge.badgeNo }"   class="images" 
					  		  src="/images/uploadFiles/${myBadge.badge.badgeImg }"
			                  onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'" ><br/>
			         </div>
			         배지이름 : ${myBadge.badge.badgeName}<br>
			         활동 횟수량 : ${myBadge.actCount}
			         </c:if>
		         <!-- (유저용) Ajax로 배지 상세 정보 보기 클릭 Start //Ajax나오게 하려면 data-value가 필요 -->   
		               <i  type ="hidden" class="myBadgeDetailIB" id="${myBadge.badge.badgeImg }" style="font-size:15px;" 
		               		data-value="${ myBadge.badgeNo }" 
		               		title="Click : 배지정보 확인" 
		               		value="${myBadge.badgeNo}" >

		               </i>
		          <!-- (유저용) Ajax로 배지 상세 정보 보기 클릭 End -->  
		               <input type="hidden" value="${myBadge.badgeNo}" >
		               <input type="hidden" name="userEmail" value="${user.userEmail}" />
				  </div>
				 <!-- 이미지에 for문으로 돌아간 배지 정보 담기 End -->
			  </c:forEach>
		</div>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>

         <!--  화면구성 div End /////////////////////////////////////-->

		<!--  table End /////////////////////////////////////-->	

      </div>
   </form>
</body>
<!-- body 끝 -->
</html>