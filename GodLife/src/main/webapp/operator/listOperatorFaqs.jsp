<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<title>자주하는질문</title>

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
	
	
		function fncGetOperatorFaqsList(currentPage) {			
			$("#currentPage").val(currentPage)
			$("form").attr("method" , "POST").attr("action" , "/operator/listOperatorFaqs").submit();	
		}
		
		$(function() {
			
			$( "td.ct_btn01:contains('검색')" ).on("click" , function() {
				fncGetOperatorFaqsList(1);
			});
			 
			$( ".ct_list_pop td:nth-child(3)" ).on("click" , function() {
					//self.location ="/operator/getOperatorFaqs?noticeFaqNo="+$(this).data("value");
			//});
			
				var title = $(this).text().trim();
				$.ajax( 
						{
							url : "/operator/json/getOperatorFaqs/"+title ,
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
								
															+"제  목 : "+JSONData.title+"<br/>"
															+"작성자 : "+JSONData.userEmail+"<br/>"
															+"태그 : "+JSONData.faqTag+"<br/>"
															+"작성일 : "+JSONData.regDate+"<br/>"
															+"</h3>";
								//Debug...									
								//alert(displayValue);
								$("h3").remove();
								$( "#"+title+"" ).html(displayValue);
							}
					});
					
			});
		
			$(".ct_list_pop:nth-child(4n+6)" ).css("background-color" , "whitesmoke");		

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
	       <h3>자주하는질문</h3>
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
						<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>태그</option>
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
  <form action="/operator/addOperatorFaqs" method="GET">

	<!-- 새글의 계층 정보 -->
	<button type="submit" class="btn btn-secondary mb-3">글쓰기</button>
</form>		



		
<table class="table table-striped">
	<tr>
		<td colspan="11">전체 ${resultPage.totalCount} 건수, 현재 ${resultPage.currentPage } 페이지</td>
	</tr>
	<tr>
		<td>번호<br></td>
		<td></td>		
		<td>제목</td>
		<td>작성자</td>
		<td>태그</td>
		<td>작성일</td>
		<td></td>
	</tr>
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

	<c:set var = "i" value = "0"/>
	<c:forEach var ="NoticeFaqs" items ="${list }">
		<c:set var="i"  value = "${i+1 }"/>
		<tr class="ct_list_pop">
		<td align="center">${ i }</td>
			 <td></td>		
			  <td align="left">${ NoticeFaqs.title }</td>
			  <td align="left">${ NoticeFaqs.userEmail }</td>
			  <td align="left">${ NoticeFaqs.faqTag }</td>
			  <td align="left">${ NoticeFaqs.regDate }</td>
			  <td align="left"></td>
	</tr>	
	<tr>
		<td id="${NoticeFaqs.title}" colspan="11" bgcolor="D6D7D6" height="1"></td>
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