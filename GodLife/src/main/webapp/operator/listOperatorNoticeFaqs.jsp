<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<title>공지사항</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<style>
	  body {
            padding-top : 70px;
        }
        
    </style>
	<script type="text/javascript">
	

function fncGetList(currentPage) {
	
	$("#currentPage").val(currentPage)
	$("form").attr("method" , "POST").attr("action" , "/operator/getOperatorNoticeFaqsList").submit();
	
}

$(function() {
	 
	$( ".ct_list_pop td:nth-child(1)" ).on("click" , function() {
		self.location ="/operator/getOperatorNoticeFaqs?noticeFaqNo="+$(this).children("input").val();
	});
	
	$( ".ct_list_pop td:nth-child(3)" ).on("click" , function() {
			self.location ="/operator/getOperatorNoticeFaqs?noticeFaqNo="+$(this).children("input").val();
	});
	
	$( ".ct_list_pop td:nth-child(5)" ).on("click" , function() {
		var tranNo = $(this).children("input").val().trim();
		$.ajax({
			url : "/operator/json/getOperatorNoticeFaqs/" + tranNo,
			method : "GET",
			dataType : "json",
			headers : {
				"Accept" : "application/json",
				"Content-Type" : "application/json"
			},
			success : function(JSONData, status) {

				//alert(status);
				//alert("JSONData : \n"+JSONData.tranNo);

				var displayValue = "<h3>" + 
				"제품번호 : "+JSONData.purchaseProd.prodNo + "<br/>" +
				"구매자 이름 : "+JSONData.purchaseProd.receiverName + "<br/>" +
				"재고 : "+ JSONData.quantity + "<br/>" +
				"구매자 연락처 : "+ JSONData.receiverPhone + "<br/>" +
				"구매자 주소 : "+ JSONData.divyAddr + "<br/>" +
				"구매 요청 사항: "+ JSONData.divyRequest + "<br/>" +
				"배송희망일: "+ JSONData.divyDate + "<br/>" +
				"주문일: "+ JSONData.orderDate + "<br/>" +
				"상품이미지 : <img src=/images/uploadFiles/"+ JSONData.purchaseProd.fileName+ "/><br/>" 
				"</h3>";

				//alert(displayValue);
				$("h3").remove();
				$("#" + tranNo + "").html(displayValue);
			}
		});
	});
	
	$(".ct_list_pop td:nth-child(11):contains('물건도착')" ).on("click",function() {
		//alert( $(this).children("input").val() );
		var tranNo =$(this).children("input").val().trim();
		self.location="/purchase/updateTranCode?tranNo="+$(this).children("input").val()+"&tranCode=3";
	});
	
	$(function() {
		$( ".btn.btn-default" ).on("click" , function() {
			fncAddPointDonation();
		});
	});	
	
	
	var userId = $(this).text().trim();
	$.ajax( 
			{
				url : "/operator/json/getOperatorNoticeFaqs/"+noticeFaqNo ,
				method : "GET" ,
				dataType : "json" ,
				headers : {
					"Accept" : "application/json",
					"Content-Type" : "application/json"
				},
				success : function(JSONData , status) {

					//Debug...
					//alert(status);
					//Debug...
					//alert("JSONData : \n"+JSONData);
					
					var displayValue = "<h3>"
												+"번  호 : "+JSONData.noticeFaqNo+"<br/>"
												+"제  목 : "+JSONData.title+"<br/>"
												+"작성일 : "+JSONData.regDate+"<br/>"
												+"작성자 : "+JSONData.userEmail+"<br/>"
												+"</h3>";
					//Debug...									
					//alert(displayValue);
					$("h3").remove();
					$( "#"+userId+"" ).html(displayValue);
				}
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
	       <h3>공지사항</h3>
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
				    <select class="form-control" name="searchCondition" >
						<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>제목</option>
						<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>번호</option>
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

<!-- 새글쓰기 누를 때 -->
<form action="/operator/addOperatorNoticeFaqs" method="post">

	<!-- 새글의 계층 정보 -->
	<button type="submit" class="btn btn-secondary mb-3">글쓰기</button>
</form>		
		
<table class="table table-striped">
	<tr>
		<td colspan="11">전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage } 페이지</td>
	</tr>
	<tr>
		<th scope="col" class="text-center">번호</th>
		<th scope="col" class="text-center">제목</th>
		<th scope="col" class="text-center">작성일</th>
		<th scope="col" class="text-center">작성자</th>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

	<c:set var = "i" value = "0"/>
	<c:forEach var ="NoticeFaqs" items ="${list }">
		<c:set var="i"  value = "${i+1 }"/>
		<tr>
		<td>${i }</td>
			  <td align="left" noticeFaqNo="${ NoticeFaqs.noticeFaqNo }" title="Click : 상품정보 확인">${ NoticeFaqs.title }</td>
			  <td align="left">${ NoticeFaqs.regDate }</td>
			  <td align="left">${ NoticeFaqs.userEmail }</td>
			  <td align="left">
			  	<i class="glyphicon glyphicon-ok" id= "${NoticeFaqs.noticeFaqNo}"></i>
			  	<input type="hidden" value="${NoticeFaqs.noticeFaqNo}">
			  </td>
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