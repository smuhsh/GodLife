<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>

<html lang="ko">

<head>
<meta charset="UTF-8">

<!--   jQuery , Bootstrap CDN  -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   <link rel="stylesheet" href="/resources/css/toolbar2.css" />
   
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
<!--  ///////////////////////// JavaScript ////////////////////////// -->
<title>상품 목록조회</title>


  
<style>
	body{
		font-weight : 600;
	}	

#fromCenter { 
  color: #000;
  display:inline-block; 
  margin:0;
  text-transform:uppercase; }
  
#fromCenter:after {
  display:block;
  content: '';
  border-bottom: solid 3px #ea2129;  
  transform: scaleX(0);  
  transition: transform 250ms ease-in-out;
}
#fromCenter:hover:after { transform: scaleX(1); }




	#dotted {
	  border: dotted;
	}

	.col-sm-offset-2 col-sm-1{
	margin-left: 5; 
	width: 180px;
	}
	
	 .container{
    padding-top:220px;
    }

	
	fieldset {
	   width: 400px;
	   text-align: center;
	   backgrond-color: white;

	}
		
	.detail {
	   color : white ;
	   margin : 20px;
	   width: 380px;
	   height: auto;
	   padding-top: 1px;

	}

	.backdefault{
	width: 180px;
	height: 180px;
	background-image: url("/resources/images/uploadFiles/DefaultBackGround.PNG");
	align-items:center;
	display: flex;
	 justify-content:center;
	}
	.backbronze{
	width: 180px;
	height: 180px;
	background-image: url("/resources/images/uploadFiles/badgeBronze.jpg");
	align-items:center;
	display: flex;
	 justify-content:center;
	}
	.backsilver{
	width: 180px;
	height: 180px;
	background-image: url("/resources/images/uploadFiles/badgeSilver.jpg");
	align-items:center;
	display: flex;
	 justify-content:center;
	}
	.backgold{
	width: 180px;
	height: 180px;
	background-image: url("/resources/images/uploadFiles/badgeGold.jpg");
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

	//==============신규 배지 등록 Event  처리=========================
	 $(function() {
		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		 $( "button.btn.btn-primary.addB" ).on("click" , function() {
				self.location = "/badge/addBadgeView?badgeNo=${badge.badgeNo}"
			});
	});    	

	 $(function() {
		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		 $( "button.btn.btn-success.getBadgeMyList" ).on("click" , function() {
				self.location = "/myBadge/getBadgeMyList"
			});
	});    	

	
	
	//============= images 에 배지 상세 정보(관리자 모드/수정 삭제로 들어가기)  Event  처리(Click) =============
	 //==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)

		 $(function() { 
			 
		     $( ".images" ).on("click" , function() {
		    	 
		    	 if(${sessionScope.user.role == '2'}){
		    		 
		    	 	self.location ="/badge/getBadge?badgeNo="+$(this).attr("badgeNo");
		    	 	
		    	 }
		    	 if(${sessionScope.user.role == '1'})
		    	 {
		    			//============= 배지 이미지 클릭시 배지 정보보기 Ajax이용 (일반 회원용)  Event  처리(Click) =============
		    					const badgeNo = $(this).data("param");
		    			
		    					 $.ajax( 
		    			                 {
		    			                    url : "/badge/json/getBadge/"+badgeNo,
		    			                    method : "GET",
		    			                    dataType : "json",
		    			                    headers : {
		    			                       "Accept" : "application/json",
		    			                       "Content-Type" : "application/json"
		    			                    },

		    			                    success : function(JSONData , status) {
		    			                    	$(JSONData).each(function(){
		    			                       const displayView =
		    			                    	   
		    			                    	   "<div class=\"row\" style=\"width: 500px; background-color:#66c871; padding-left: 30px; border-radius: 50% 20% / 10% 40%;\">" +
		    			                    	   		"<div class=\"detail\" style=\"width: 450px; \"> <b>:: 배지 명 ::</b> <br>" +
		    			                    	   		"&nbsp&nbsp&nbsp"  + this.badgeName +"<br>" +
		    			                    	   		
		    			                    	   		"<br> <b> :: 배지 상세정보 :: </b> <br>" +
		    			                    	   		 this.badgeDetail +
		    			                    	   	"</div>"		

		    			                       	$("div.detail").remove();

		    			                       $( "#badgeDetail" ).append(displayView);
		    			                       console.log(JSONData , status);
		    			                       
		    			                       $( "div.detail" ).css("color" , "#daffe4");

		    			                       
		    			                    	})
		    			                 }
		    			              }); 
		    			        }

		    			});	 
		    	 });	 	
		    	 	
	 
	 

	</script>
	
</head>
<!-- body시작 -->
<body>

   <form class="form-horizontal" style="margin: 40px;">
      <!-- ToolBar Start /////////////////////////////////////-->
      <c:if test="${sessionScope.user.role == '2'}">
      <jsp:include page="/layout/toolbar.jsp" />
      </c:if>
      <!-- ToolBar End /////////////////////////////////////-->
      
	  
      <!--  화면구성 div Start /////////////////////////////////////-->
      <c:if test="${sessionScope.user.role == '1'}">
      <div class="containerUser" >
      </c:if>
      <c:if test="${sessionScope.user.role == '2'}">
      <div class="container" >
      </c:if>
		<div class="row" style="font-size:30px;" >
			<c:if test="${sessionScope.user.role == '1'}">
				<div class="col-md-3" ></div>
				  <h2 class="col-md-6" id="fromCenter" align="center" style="font-size: larger;">전체 배지 목록 상세 조회</h2>
				<div class="col-md-3" ></div>	
			</c:if>
			<c:if test="${sessionScope.user.role == '2'}">
				<div class="col-md-3" ></div>
				  <h2 class="col-md-6" id="fromCenter" align="center" style="font-size: larger;">전체 배지 목록 (관리자 모드)</h2>
				<div class="col-md-3" ></div>		
			</c:if>
		</div>
		<br>
			<c:if test="${sessionScope.user.role == '2'}">
				<div class="col-md-12 text-right">
					<button type="button" class="btn btn-primary addB">신규 배지 등록</button> 
				</div>
			</c:if>
		<br>
		<br>
		<!-- 배지들이 어떤 의미를 가지는지? 이미지 Start-->
			<div class="row">
				<img src="/resources/images/uploadFiles/크립토펑크.PNG" style="width:800px;" alt="..." class="img-thumbnail"><br>
				<div class="col-md-4" id="fromCenter" >CrytoPunk(크립토 펑크) 배지를 수집하세요.</div>
			</div>
		<!-- 배지들이 어떤 의미를 가지는지? 이미지 End -->	
		<br>
		<br>
		<!-- 배지 등급 이미지 설명 입력 Start -->	
		<div class="row" id="dotted" style="width: 807.5px; color: #66c871;">
				<div class="row" 
					style="width: 800px;padding-left: 20px;border-right-width: 600px;padding-right: 600px;"
				 	id="fromCenter">
					활동 횟수량에 따른 등급 변화 
				</div>
			<br>	
		    <div class="col-sm-offset-1 col-sm-1" style="margin-left: 5; width: 180px; color:black ">
		    	Bronze Badge<br>
		    	활동 횟수량 : 1 ~ 10
		    	<img src="/resources/images/uploadFiles/BronzeBadgeBackGround.PNG" alt="..." class="img-rounded" style="width:150px; height:150px;"
		    			onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'">
		    </div>

		    <div class="col-sm-offset-1 col-sm-1" style="margin-left: 30; width: 180px; color:black">
		    	Silver Badge<br>
		    	활동 횟수량 : 11 ~ 20
		    	<img src="/resources/images/uploadFiles/SilverBadgeBackGround.PNG" alt="..." class="img-rounded" style="width:150px; height:150px;"
		    			onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'">
		    </div>
			    
		    <div class="col-sm-offset-1 col-sm-1" style="margin-left: 30; width: 180px; color:black">
		    	Gold Badge<br>
		    	활동 횟수량 : 21 ~ 
		    	<img src="/resources/images/uploadFiles/GoldBadgeBackGround.PNG" alt="..." class="img-rounded" style="width:150px; height:150px;"
		    			onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'">
		    </div>
		</div>	
		<!-- 배지 등급 이미지 설명 입력 end -->		
		<br>
		<br>
		<!-- 활동 배지 Collapse로 설명 구현 Start -->
			<button class="btn btn-success" type="button">
			  활동 배지
			</button>

		<!-- 활동 배지 Collapse로 설명 구현 End -->
		<br>
		<br>
		
		<!-- 활동 배지 -->
		<div class="row">
		<!-- 배지 act count 1 이상일때 나오게 하기 if문 거는 곳 -->
		
		
			<c:set var="i" value="0"/>
			  <c:forEach var="badge" items="${list1}">
            	<c:set var="i" value="${ i+1 }" />      
            	 <!-- 이미지에 for문으로 돌아간 배지 정보 담기 Start -->
            	 <div class="col-md-3" style="height: auto; width: auto;" align="center">
            	 <!-- Grade가 0일때 Default 배경이미지  -->
	            	<c:if test="${badge.actCount == 0 }">
						  <div class="backdefault" >
						  	<img badgeNo="${ badge.badgeNo }"   class="images" data-param="${ badge.badgeNo }"
						  		  src="/resources/images/uploadFiles/${badge.badgeImg }"
				                  onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'" ><br/>
				         </div>
			         </c:if>
			    <!-- Grade가 1일때 동색 배경이미지  -->
	            	<c:if test="${ badge.actCount > 0 && badge.actCount < 11 }">
					  <div class="backbronze">
					  	<img badgeNo="${ badge.badgeNo }"   class="images" data-param="${ badge.badgeNo }"
					  		  src="/resources/images/uploadFiles/${badge.badgeImg }"
			                  onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'" ><br/>
			         </div>
		        <!-- Grade가 2일때 은색 배경이미지  -->
			         </c:if>
			         <c:if test="${badge.actCount > 10 && badge.actCount < 21}">
					  <div class="backsilver">
					  	<img badgeNo="${ badge.badgeNo }"   class="images" data-param="${ badge.badgeNo }"
					  		  src="/resources/images/uploadFiles/${badge.badgeImg }"
			                  onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'" ><br/>
			         </div>
			         </c:if>
		         <!-- Grade가 3일때 금색 배경이미지  -->
			         <c:if test="${badge.actCount > 20 }">
					  <div class="backgold">
					  	<img badgeNo="${ badge.badgeNo }"   class="images" data-param="${ badge.badgeNo }"
					  		  src="/resources/images/uploadFiles/${badge.badgeImg }"
			                  onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'" ><br/>
			         </div>
			         </c:if>
		         <!-- (유저용) Ajax로 배지 상세 정보 보기 클릭 Start //Ajax나오게 하려면 data-value가 필요 -->   
		               		<div id="fromCenter">
		               		${badge.badgeName}
		               		</div>

		          <!-- (유저용) Ajax로 배지 상세 정보 보기 클릭 End -->  
		               <input type="hidden" value="${badge.badgeNo}" >
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
			<button class="btn btn-success" type="button">
			  관심사 배지
			</button>
			
		<!-- 활동 배지 Collapse로 설명 구현 End -->
		<br>
		<br>
		
		<!-- 관심사 배지 -->
		<div class="row">
		<!-- 배지 act count 1 이상일때 나오게 하기 if문 거는 곳 -->
		
		
			<c:set var="i" value="0"/>
			  <c:forEach var="badge" items="${list2}">
            	<c:set var="i" value="${ i+1 }" />      
            	 <!-- 이미지에 for문으로 돌아간 배지 정보 담기 Start -->
            	 <div class="col-md-3" style="height: auto; width: auto;" align="center">
            	 <!-- Grade가 0일때 Default 배경이미지  -->
	            	<c:if test="${badge.actCount == 0 }">
						  <div class="backdefault">
						  	<img badgeNo="${ badge.badgeNo }"   class="images" data-param="${ badge.badgeNo }"
						  		  src="/resources/images/uploadFiles/${badge.badgeImg }"
				                  onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'" ><br/>
				         </div>
			         </c:if>
			    <!-- Grade가 1일때 동색 배경이미지  -->
	            	<c:if test="${ badge.actCount > 0 && badge.actCount < 11 }">
					  <div class="backbronze">
					  	<img badgeNo="${ badge.badgeNo }"   class="images" data-param="${ badge.badgeNo }"
					  		  src="/resources/images/uploadFiles/${badge.badgeImg }"
			                  onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'" ><br/>
			         </div>
		        <!-- Grade가 2일때 은색 배경이미지  -->
			         </c:if>
			         <c:if test="${badge.actCount > 10 && badge.actCount < 21}">
					  <div class="backsilver" >
					  	<img badgeNo="${ badge.badgeNo }"   class="images" data-param="${ badge.badgeNo }"
					  		  src="/resources/images/uploadFiles/${badge.badgeImg }"
			                  onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'" ><br/>
			         </div>
			         </c:if>
		         <!-- Grade가 3일때 금색 배경이미지  -->
			         <c:if test="${badge.actCount > 20 }">
					  <div class="backgold">
					  	<img badgeNo="${ badge.badgeNo }"   class="images" data-param="${ badge.badgeNo }"
					  		  src="/resources/images/uploadFiles/${badge.badgeImg }"
			                  onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'" ><br/>
			         </div>
			         </c:if>
		         <!-- (유저용) Ajax로 배지 상세 정보 보기 클릭 Start //Ajax나오게 하려면 data-value가 필요 -->   
		               		<div id="fromCenter">
		               		${badge.badgeName}
		               		</div>

		          <!-- (유저용) Ajax로 배지 상세 정보 보기 클릭 End -->  
		               <input type="hidden" value="${badge.badgeNo}" >
				  </div>
				 <!-- 이미지에 for문으로 돌아간 배지 정보 담기 End -->
			  </c:forEach>
			  <br><br><br><br><br><br><br><br><br><br><br><br><hr/>
			  
			  <div class="row">
				  <div class="col-md-1" >
				  	<div type="hidden" id="badgeDetail" name="badgeNo" class="badgeDetail" value="${badge.badgeNo}"></div>
				  </div>
			  </div>		  
		</div>
		<br>
		<br>


         <!--  화면구성 div End /////////////////////////////////////-->

		<!--  table End /////////////////////////////////////-->	
      </div>
   </form>
</body>
<!-- body 끝 -->
</html>