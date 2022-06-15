<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
<title>공지사항</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>	
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	<!--  <script type="text/javascript" src="../javascript/calendar.js"></script> -->
	<script type="text/javascript"></script> 
	
	<style>
	  body {
            padding-top : 70px;
        }
        
    </style>
	<script type="text/javascript">
	
		function fncAddOperatorFaqs() {
			
			var userEmail = $("input[name='userEmail']").val();
			var status = $("input[name='status']").val();
			var noticeMust = $("input[name='noticeMust']").val();
			var title = $("input[name='title']").val();
			var detail = $("input[name='detail']").val();
			var img = $("input[name='img']").val();
			var faqTag = $("input[name='faqTag']").val();
			var regDate = $("input[name='regDate']").val();
			
			if(userEmail == null || userEmail.length<1){
				alert("작성자는 반드시 입력하여야 합니다.");
				return;
			}
			if(status == null || status.length<1){
				alert("상태는 반드시 입력하셔야 합니다.");
				return;
			}
			if(noticeMust == null || noticeMust.length<1){
				alert("필수사항은 반드시 입력하셔야 합니다.");
				return;
			}
			if(title == null || title.length<1){
				alert("제목은 반드시 입력하여야 합니다.");
				return;
			}
			if(detail == null || detail.length<1){
				alert("내용은 반드시 입력하셔야 합니다.");
				return;
			}
		
			$("form").attr("method", "POST").attr("action", "/operator/addOperatorFaqs").submit();
			
		}
		
		$(function() {
			$("td.ct_btn01:contains('등록')").on("click", function(){
				alert($("td.ct_btn01:contains('등록')").html());
				fncAddOperatorNotice();
			});
			$("td.ct_btn01:contains('취소')").on("click", function(){
				//Debug..
				alert($("td.ct_btn01:contains('취소')").html());
				$("form")[0].reset();
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
	    
		    
		    <div class="col-md-6 text-right">
		    
			    <form class="form-inline" name="detailForm">
				  <!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
				  <input type="hidden" id="currentPage" name="currentPage" value=""/>
				  
				</form>
	    	</div>
	    	
		</div>
		<!-- table 위쪽 검색 Start /////////////////////////////////////-->

		
<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37">
			<img src="/images/ct_ttl_img01.gif" width="15" height="37"/>
		</td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">자주하는질문 등록</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif"	width="12" height="37"/>
		</td>
	</tr>
</table>





	<tr class="mb-3">
	  <label for="userEmail" class="form-label">작성자</label>
	  <input type="text" name = "userEmail" class="form-control" id="userEmail" placeholder="name@email.com">
	</tr>
	
	<tr class="mb-3">
	  <label for="status" class="form-label">상태</label>
	  <input type="text" name = "status" class="form-control" id="status" placeholder="공지사항:0 FAQ:1">
	</tr>
	
	<tr class="mb-3">
	  <label for="noticeMust" class="form-label">필수공지여부</label>
	  <input type="text" name = "noticeMust" class="form-control" id="noticeMust" placeholder="일반:0 필수:1">
	</tr>

	<tr class="mb-3">
	  <label for="title" class="form-label">제목 </label>
	  <input type="text" name = "title" class="form-control" id="title" placeholder="제목">
	</tr>

	<tr class="mb-3">
	  <label for="detail" class="form-label">내용 </label>
	  <input type="text" name = "detail" class="form-control" id="detail" placeholder="내용">
	</tr>

	<tr class="mb-3">
	  <label for="regDate" class="form-label">작성일 </label>
	  <input type="text" name = "regDate" class="form-control" id="deregDatetail" placeholder="작성일">
	</tr>

<!--  	<tr>
		<td width="104" class="ct_write">
			작성일 <img src="/images/ct_icon_red.gif" width="3" height="3" align="absmiddle"/>
		</td>
		<td bgcolor="D6D6D6" width="1"></td>
		<td class="ct_write01">
			<input type="text" name="regDate" readonly="readonly" class="ct_input_g"  
						style="width: 100px; height: 19px"	maxLength="10" minLength="6"/>
				&nbsp;<img src="../images/ct_icon_date.gif" width="15" height="15" 
										onclick="show_calendar('document.detailForm.regDate', document.detailForm.regDate.value)"/>
		</td>
	</tr>-->



<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td width="53%"></td>
		<td align="right">
		<table border="0" cellspacing="0" cellpadding="0">
		

				<tr class="form-group">
				    <input type="file" id="exampleInputFile">
				</tr>	
					
				<tr>				
				<td width="17" height="23">
					<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
				</td>
				<td background="/images/ct_btnbg02.gif" class="ct_btn01"  style="padding-top: 3px;">
					 등록
				</td>
				<td width="14" height="23">
					<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
				</td>
				<td width="30"></td>
				
				<td width="17" height="23">
					<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
				</td>
				<td background="/images/ct_btnbg02.gif" class="ct_btn01"	 style="padding-top: 3px;">
					 취소
				</td>
				<td width="14" height="23">
					<img src="/images/ct_btnbg03.gif" width="14" height="23"/>
				</td>
			</tr>
		</table>
		</td>
	</tr>
</table>	

	

</body>
</html>