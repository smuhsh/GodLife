<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<title>포인트 이용 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	<script type="text/javascript" src="../javascript/calendar.js"></script>
	<style>
	  body {
            padding-top : 70px;
        }
        
    </style>
	<script type="text/javascript">
	<!-- jQuery DatePicker -->
	 $(function() {
		 $.datepicker.setDefaults({
			 dateFormat: 'yy-mm-dd'
		 });
		 
   	$( "#startDatePicker" ).datepicker();
   	$( "#endDatePicker" ).datepicker();
	 });

function fncGetList(currentPage) {
	
	$("#currentPage").val(currentPage)
   
	$("form").attr("method" , "POST").attr("action" , "/point/getPointPurchaseList").submit();
}

$(function(){
	$("button:contains('검색')").on("click",function(){
		fncGetList(1);
	});
});
</script>
</head>

<body>
	
	<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->
	
	<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
		<div class="page-header text-info">
	       <h3>포인트 이용내역 목록</h3>
	    </div>
	    
	    <!-- table 위쪽 검색 Start /////////////////////////////////////-->
	    <div class="row">
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-primary">
		    		전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지
		    	</p>
		    </div>
		    
		    <div class="col-md-6 text-right">
		    
			    <form class="form-inline" name="detailForm">
<div class="form-group">
						    <label for="exampleInputEmail1">챌린지 시작일</label>
						    <div>
						    	<input type="text" name ="startDate" id="startDatePicker" readonly>
						    </div>
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputEmail1">챌린지 종료일</label>
						    <div>
						    	<input type="text" name ="endDate" id="endDatePicker" readonly>
						    </div>
						  </div>
				  <div class="form-group">
				    <select class="form-control" name="searchCondition" >
						<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>포인트충전</option>
						<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>챌린지</option>
						<option value="2"  ${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>이벤트</option>
						<option value="3"  ${ ! empty search.searchCondition && search.searchCondition==3 ? "selected" : "" }>기부</option>					
					</select>
				  </div>
				  
				  <div class="form-group">
				    <label class="sr-only" for="searchKeyword">검색어</label>
				    <input type="text" class="form-control" id="searchKeyword" name="searchKeyword"  placeholder="검색어"
				    			 value="${! empty search.searchKeyword ? search.searchKeyword : '' }"  >
				  </div>
				  
				  <button type="button" class="btn btn-default">검색</button>
				  
				  <!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
				  <input type="hidden" id="currentPage" name="currentPage" value=""/>
				  
				</form>
	    	</div>
	    	
		</div>
		<!-- table 위쪽 검색 Start /////////////////////////////////////-->
		
		
<table class="table table-striped">
	
	<tr>
		<td>번호<br></td>
		<td>이용 유형<br></td>
		<td></td>
		<td>금액</td>
		<td></td>
		<td>내용</td>
		<td></td>
		<td>날짜</td>
		
	</tr>


	<c:set var = "i" value = "0"/>
	<c:forEach var ="point" items ="${list }">
		<c:set var="i"  value = "${i+1 }"/>
		<tr>
		<td>${i }</td>
		<td>
				<c:if test="${! empty point.useStatus && point.useStatus=='1'}">충 전</c:if>
				<c:if test="${! empty point.useStatus && point.useStatus=='2'}">소 비</c:if>
			</td>
		<td></td>
		<td>${point.point}</td>
		<td></td>
		<td>
		<c:if test="${! empty point.useDetail && point.useDetail=='1'}">포인트 충전</c:if>
		<c:if test="${! empty point.useDetail && point.useDetail=='2'}">챌린지 참가</c:if>
		<c:if test="${! empty point.useDetail && point.useDetail=='3'}">챌린지 환불</c:if>
		<c:if test="${! empty point.useDetail && point.useDetail=='4'}">챌린지 보상</c:if>
		<c:if test="${! empty point.useDetail && point.useDetail=='5'}">이벤트 참가</c:if>
		<c:if test="${! empty point.useDetail && point.useDetail=='6'}">이벤트 보상</c:if>
		<c:if test="${! empty point.useDetail && point.useDetail=='7'}">포인트 기부</c:if>
		<c:if test="${! empty point.useDetail && point.useDetail=='8'}">쿠 폰 구매</c:if>
		<c:if test="${! empty point.useDetail && point.useDetail=='9'}">상품권 구매</c:if>
		</td>
		<td></td>
		<td>${point.regDate}</td>
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

</form>

</div>

</body>
</html>