<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<title>공지사항 작성</title>
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

        body {
            padding-top : 70px;
            background-size: cover;
        }
        
       	p{
       		font-size : 14px;
       		text-aling : center;
       	}
       	
       	textarea{
       		width:100%;
       		resize: none;
       	}
       	   
   	</style>
   	
	<script type="text/javascript">
	
	
	 $(function(){
		 $("button#addOperatorJoinDayEvent").on("click",function(){	
			
			 if($("input[name='userEmail']").val() == ""){
				 alert("작성자는 반드시 입력하여야 합니다.");
				 return;
			 }
			 if($("input[name='status']").val() == ""){
				 alert("상태는 반드시 입력하셔야 합니다.");
				 return;
			 }
			 if($("input[name='noticeMust']").val() == ""){
				 alert("필수사항은 반드시 입력하셔야 합니다.");
				 return;
			 }
			 if($("input[name='title']").val() == ""){
				 alert("제목은 반드시 입력하여야 합니다.");
				 return;
			 }
			 if($("input[name='detail']").val() == ""){
				 alert("내용은 반드시 입력하셔야 합니다.");
				 return;
			 }
		
			$("form[name='addOperatorJoinDayEventForm']").attr("method", "POST").attr("action", "/operator/addOperatorJoinDayEvent").submit();
			
		 });
		
		 $(function() {
			 $( "td.ct_btn01:contains('등록')" ).on("click" , function() {
				self.location = "/operator/addOperatorNotice?noticeFaqNo=${NoticeFaqs.noticeFaqNo}"
			});
			
			$( "td.ct_btn01:contains('취소')" ).on("click" , function() {
				history.go(-1);
			});
		});

		 
		/*$(function() {
			$("td.ct_btn01:contains('등록')").on("click", function(){
				alert($("td.ct_btn01:contains('등록')").html());
				fncAddOperatorNotice();
			});
			$("td.ct_btn01:contains('취소')").on("click", function(){
				//Debug..
				alert($("td.ct_btn01:contains('취소')").html());
				$("form")[0].reset();
			});
		});*/
	 	
	 });	
		
	</script>
</head>

<body>
<jsp:include page="/layout/toolbar.jsp" />
	<div class="container">
		<div class="page-header text-info">
	       <h3>공지사항</h3>
	    </div>
	    <div class="row">
		    <div class="col-md-6 text-right">
		    
			    <form class="form-inline" name="detailForm">
				  <!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
				  <input type="hidden" id="currentPage" name="currentPage" value=""/>
				  
				</form>
	    	</div>
	    	
		</div>

		
<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="text-center ct_ttl01">공지사항 등록</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif"	width="12" height="37"/>
		</td>
	</tr>
</table>
				<ul class="list-group">
				  <li class="list-group-item">
				    <form name="addOperatorJoinDayEventForm" enctype="multipart/form-data">
				    
						  <div class="form-group">
						    <label for="userEmail">작성자</label>
						    <input type="userEmail" class="form-control" 
						    name="userEmail" id="userEmail" placeholder="admin@io.com">
						  </div>
						  
						  <div class="form-group">
						    <label for="status">상태</label>
						    <input type="status" class="form-control" 
						    name="status" id="status" placeholder="공지사항:0 FAQ:1">
						  </div>
						  
						  <div class="form-group">
						    <label for="noticeMust">필수공지여부</label>
						    <input type="noticeMust" class="form-control" 
						    name="noticeMust" id="noticeMust" placeholder="일반:0 필수:1">
						  </div>
						  
						  <div class="form-group">
						    <label for="title">제목</label>
						    <input type="title" class="form-control" 
						    name="title" id="title" placeholder="제목을 입력하세요.">
						  </div>
						  
						  <div class="form-group">
						    <label for="detail">내용</label>
						    <div>
						    	<textarea name="detail" cols="45" rows="6" id="detail" placeholder="내용을 입력하세요."></textarea>
						    </div>
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputFile">이미지 등록</label>
						    <input type="file" name="thumbnail" id="exampleInputFile">
						  </div>
						  
				    </form>	
				  </li>
				</ul>






</div>	
</body>
</html>