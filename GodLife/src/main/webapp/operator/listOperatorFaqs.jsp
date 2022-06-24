<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>자주하는질문</title>
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
	
	<style>
	  .container {
            padding-top : 150px;
        }
        
    </style>
    
	<script type="text/javascript">
		
		function fncGetList(currentPage) {			
			
			$("#currentPage").val(currentPage)
			$("form").attr("method" , "POST").attr("action" , "/operator/listOperatorFaqs").submit();	
		}
		
		$( "td.ct_btn01:contains('검색')" ).on("click" , function() {
			fncGetList(1);
		});
		
		$(function() {
			$( ".faqtitle" ).on("click" , function() {
			//alert("ajax 연습");
			var title = $(this).text().trim();
			//alert("title :"+title);
			$.ajax( 
					{
						url : "/operator/json/getOperatorFaqs/"+title ,
						method : "GET",
	                       dataType : "json",
	                       headers : {
	                          "Accept" : "application/json",
	                          "Content-Type" : "application/json"
	                       },
	                       success : function(JSONData , status) {
	                           
	                           const displayDetail = 
	                              `
	                              <div class="detail">
	                                   <div id="0">제목 :&nbsp \${JSONData.title} ? </div>
	                                   <br>
	                                   
	                                   <div id="1">내용:&nbsp \${JSONData.detail} </div>
	                                   <br>
	                                   
	                                   
	                               </div>`
	                           $("div.detail").remove();
	                           $( "#append" ).append(displayDetail);
	                           console.log(JSONData , status);
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
						<option value="0" ${search.orderCondition == 0 ? "selected" : "" }>태그</option>
						<option value="1" ${search.orderCondition == 1 ? "selected" : ""}>제목</option>				    
					</select>
				  </div>
				  
				  <div class="form-group">
				    <label class="sr-only" for="searchKeyword">검색어</label>
			    	<input type="text" class="form-control" id="searchKeyword" name="searchKeyword" placeholder="검색어">
				  </div>			  
				  <button class="btn btn-default" id="search">검색</button>				  				  
				</form>
	    	</div>
	    	
		</div>


		
<table class="table table-striped">
  	<tr>
		<br></br>	
		<td></td>		
		<td>제목</td>
		<td>작성자</td>
		<td>태그</td>
		<td>작성일</td>
		<td></td>
	</tr>

<!--  	<tr>
		<br></br>	
		<td>번호<br></td>
		<td></td>		
		<td>제목</td>
		<td>작성자</td>
		<td>태그</td>
		<td>작성일</td>
		<td></td>
	</tr>-->
	<tr>
		<td colspan="11" bgcolor="808285" height="1"></td>
	</tr>

	<c:set var = "i" value = "0"/>
	<c:forEach var ="operatorNoticeFaqs" items ="${list }">
		<c:set var="i"  value = "${i+1 }"/>
			 <td></td>		
			  <td class="faqtitle" align="left" data-value="${ operatorNoticeFaqs.title }" title="Click :내용보기">${ operatorNoticeFaqs.title }?</td>
			  
			  <td align="left">${ operatorNoticeFaqs.userEmail }</td>
			  <td align="left">${ operatorNoticeFaqs.faqTag }</td>
			  <td align="left">${ operatorNoticeFaqs.regDate }</td>
			  <td align="left"></td>
	</tr>	
	<tr>
		<td id="append">
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

<!-- 새글쓰기 누를 때 -->
<form action="/operator/addOperatorFaqs" method="GET">
	<c:if test="${user.role == '2'}">		  		
		<input type="submit" class="btn btn-primary " value="글쓰기"/>
	</c:if>
</form>		

</div>

</body>
</html>