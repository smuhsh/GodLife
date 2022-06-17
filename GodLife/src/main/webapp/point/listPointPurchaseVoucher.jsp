<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<title>상품권 구매 목록</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	<link rel="stylesheet" href="/resources/css/toolbar2.css" />
	<link rel="stylesheet" href="/resources/css/purchaseList.css" type="text/css">

	<style>

	 .container{
    padding-top:220px;
    }
      #mesaageimg{
      	width: 30px;
      	height: 30px;
      }
      td{
     	 width: 30px;
      	height: 40px;
      }

	#search:focus {
		outline:0;
	}
	#search:hover{
		background: gray;
		cursor: pointer;
		box-shadow: 0 2px 4px rgba(0,79,255,0.6);
	}
   
    </style>
	<script type="text/javascript">
	

function fncGetList(currentPage) {
	
	$("#currentPage").val(currentPage)
   
	$("form").attr("method" , "POST").attr("action" , "/point/getPointPurchaseVoucherList").submit();
}

$(function(){
	$("button:contains('검색')").on("click",function(){
		fncGetList(1);
	});
});

$(function(){
	$("a:contains('전송')").click(function(){
		alert(productName+""+voucherUniqueNo+""+regDate);
		var productName = $(this).data("param1");
		var voucherUniqueNo = $(this).data("param2");
		var regDate = $(this).data("param3");
		$.ajax({
	        url:"/pointRest/sendPointVoucher?productName="+productName+
			"&voucherUniqueNo="+voucherUniqueNo+"&regDate="+regDate,
			method : "GET",
			dataType : "json",
			headers : {
				"Accept" : "application/json",
				"Content-Type" : "application/json"
			},
	        success:function(data){
	        		
	        	}
	        	});
	        });
	    });	

</script>
</head>

<body>
	
	<form class="form-inline" name="detailForm">	
	<jsp:include page="/layout/toolbar.jsp" />
	<div class="row">
	<jsp:include page="/user/mypageMain.jsp" />

	<div class="col-md-3" >
	
	<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
		<div class="page-header text-info">
	       <h3>상품권 구매목록</h3>
	    </div>
	    	    <!-- table 위쪽 검색 Start /////////////////////////////////////-->
	    <div class="row">
		    <div class="col-md-6 text-left">
		    	<p class="text-primary">
		    		전체  ${resultPage.totalCount } 건
		    	</p>
		    </div>
		    <div class="col-md-6 text-right">
		    <a href="/point/getPointPurchaseList">포인트 이용내역</a> &nbsp; &nbsp;<a href="/point/getPointPurchaseDonationList" class="link_lnb">기부 내역</a>
		    <br>
		    <div class="input-group">
				  <div class="form-group">
				    <select class="form-control" name="searchCondition" >
						<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>전체</option>
						<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>3000원권</option>
						<option value="2"  ${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>5000원권</option>
						<option value="3"  ${ ! empty search.searchCondition && search.searchCondition==3 ? "selected" : "" }>10000원권</option>
						<option value="4"  ${ ! empty search.searchCondition && search.searchCondition==4 ? "selected" : "" }>30000원권</option>
						<option value="5"  ${ ! empty search.searchCondition && search.searchCondition==5 ? "selected" : "" }>100000원권</option>
					</select>
					</div>
				  
				  
				  <button type="button" class="btn btn-default" id="search">검색</button>
				  
				  <!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
				  <input type="hidden" id="currentPage" name="currentPage" value=""/>
				  
			</div>
	    </div>
	  </div>
	
		<!-- table 위쪽 검색 Start /////////////////////////////////////-->
		
		
<table class="table table-striped">
	<tr>
		<td>번호<br></td>
		<td>상품권<br></td>
		<td></td>
		<td>고유번호</td>
		<td></td>
		<td>구매날짜</td>
		<td></td>
		<td>메세지</td>
		
	</tr>
	<c:set var = "i" value = "0"/>
	<c:forEach var ="point" items ="${list }">
		<c:set var="i"  value = "${i+1 }"/>
		<tr>
		<td>${i }</td>
		<td>${point.productName}</td>
		<td></td>
		<td>${point.voucherUniqueNo}</td>
		<td></td>
		<td>${point.regDate}</td>
		<td></td>
		<td><a href="#"
		data-param1="${point.productName}"
		data-param2="${point.voucherUniqueNo}"
		data-param3="${point.regDate}"
		><img src="/resources/images/uploadFiles/voucherMesaage.png" id="mesaageimg">전송</a></td>
	</tr>
	
	</c:forEach>
</table>	
	
<!--  페이지 Navigator 시작 -->

<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top:10px;">
	<tr>
		<td align="center">
		   <input type="hidden" id="currentPage" name="currentPage" value=""/>
	
			<jsp:include page="../common/pageNavigator_new.jsp"/>	
			
    	</td>
	</tr>
</table>
<!--  페이지 Navigator 끝 -->


</div>
</div>
<div class="col-md-1" >
	</div>
</div>
</form>
</body>
</html>