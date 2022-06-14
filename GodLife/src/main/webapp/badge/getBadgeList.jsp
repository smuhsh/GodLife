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
	   padding-top: 50px;
	   background-color: #708090 ;
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
	   background-color: #070719 ;
	   font-weight: bold; 
	   font-family:impact;
	   font-family: 'oneMobile';
	}
	

</style>

	<script type="text/javascript">
	
	
	
	
	
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
		<br>
		<div class="row">
		  <div class="col-md-12">보유배지 전체 목록</div>			
		</div>
		<br>
		<br>
		<!-- 활동 배지 Collapse로 설명 구현 Start -->
			<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample1" aria-expanded="false" aria-controls="collapseExample1">
			  활동 배지
			</button>
			<div class="collapse" id="collapseExample1">
			  <div class="well">
			    ...
			  </div>
			</div>
		<!-- 활동 배지 Collapse로 설명 구현 End -->
		<br>
		<br>
		<br>
		<div class="row">
			<c:set var="i" value="0"/>
			  <c:forEach var="badge" items="${list}">
            	<c:set var="i" value="${ i+1 }" />       
				  <div class="col-md-3"  >
				  	<img badgeNo="${ badge.badgeNo }"  width="150" height="150" class="images" src="../images/uploadFiles/${badge.badgeImg }"
		                  onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'" ><br/>
		               <i id= "${badge.badgeNo}" style="font-size:20px;" ></i>
		               <input type="hidden" value="${badge.badgeNo}" >
				  </div>
			  </c:forEach>
		</div>
		<br>
		<br>
		<!-- 관심사 배지 Collapse로 설명 구현 Start -->
			<button class="btn btn-primary" type="button" data-toggle="collapse" data-target="#collapseExample2" aria-expanded="false" aria-controls="collapseExample2">
			  관심사 배지
			</button>
			<div class="collapse" id="collapseExample2">
			  <div class="well">
			    ...
			  </div>
			</div>
		<!-- 활동 배지 Collapse로 설명 구현 End -->
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>		
		<div class="row">
		  <div class="col-md-2">배지6</div>
		  <div class="col-md-2">배지7</div>
		  <div class="col-md-2">배지8</div>
		  <div class="col-md-2">배지9</div>
		  <div class="col-md-2">배지10</div>
		</div>
		<br>
		<br>
		<br>
		<br>
		<br>
		<br>
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