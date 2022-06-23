<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>    

<html>
<head>
<meta charset="UTF-8">
   
   <!-- 참조 : http://getbootstrap.com/css/   참조 -->
   <meta name="viewport" content="width=device-width, initial-scale=1.0" />
   
   <!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
   <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
   <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
   <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
   
   <!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
   
   <!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
	  .container {
            padding-top : 150px;
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
    

    
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
<script type="text/javascript">
      
   function fncUpdateOperatorNotice() {

      $("form").attr("method", "POST").attr("action",
            "/operator/updateOperatorNotice").submit();
   }
   
   /*
   function fncUpdateOperatorNoticeImg() {
	      $("form").attr("enctype", "multipart/form-data").attr("method", "POST").attr("action",
	            "/operator/updateOperatorNotice").submit();
   }
   */
   
   function fncDeleteOperatorNotice() {

	      $("form").attr("method", "POST").attr("action",
	            "/operator/deleteOperatorNotice").submit();
	}   
   

   //==> 추가된부분 : "수정"  Event 연결
   $(function() {
      $("button.btn.btn-primary.update").on("click", function() {
    	  fncUpdateOperatorNotice();
      });
   });

   $(function() {
	  $("button.btn.btn-primary.delete").on("click", function() {
		  fncDeleteOperatorNotice();
	   });
	});   
 
   
   ////////파일 업로드
	 $(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$( ".imageUplaod" ).on("change" , function() {
				fncUpdateOperatorNoticeImg();
				alert(noticeFaqNo + ": noticeFaqNo   "+ img + ":img  " + title + ":title");	
			});
		});	
	////////파일 업로드
   
   
   
   //==> 추가된부분 : "취소"  Event 연결 및 처리
   $(function() {
      $("a[href='#' ]").on("click", function() {
         $("form")[0].reset();
      });
   });

	
	//============= 뒤로 돌아가기 Event  처리 =============	
	 $(function() {
		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		 $( "button.btn.btn-primary.back" ).on("click" , function() {
			history.go(-1);
			});
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
					<td width="93%" class="text-center ct_ttl01">공지사항 수정</td>
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
				    <form name="updateOperatorNoticeForm" enctype="multipart/form-data">
				    
						  <div class="form-group">
						    <label for="noticeFaqNo"></label>
						    <input type="hidden" class="form-control" 
						    name="noticeFaqNo" id="noticeFaqNo" value="${operatorNoticeFaqs.noticeFaqNo}">
						  </div>
						  
						  <div class="form-group">
						    <label for="userEmail">작성자</label>
						    <input type="userEmail" class="form-control" 
						    name="userEmail" id="userEmail" placeholder="admin@io.com" value="${operatorNoticeFaqs.userEmail}">
						  </div>
						  
						  <div class="form-group">
						    <label for="status">상태</label>
						    <input type="status" class="form-control" 
						    name="status" id="status" placeholder="공지사항:0 FAQ:1" value="${operatorNoticeFaqs.status}">
						  </div>
						  
						  <div class="form-group">
						    <label for="noticeMust">필수공지여부</label>
						    <input type="noticeMust" class="form-control" 
						    name="noticeMust" id="noticeMust" placeholder="일반:0 필수:1" value="${operatorNoticeFaqs.noticeMust}">
						  </div>
						  
						  <div class="form-group">
						    <label for="title">제목</label>
						    <input type="title" class="form-control" 
						    name="title" id="title" placeholder="제목을 입력하세요." value="${operatorNoticeFaqs.title}">
						  </div>
						  
						  <div class="form-group">
						    <label for="detail">내용</label>
						    <div>
						    	<textarea name="detail" cols="45" rows="6" id="detail" placeholder="내용을 입력하세요." value="${noticeFaqs.detail}"> </textarea>
						    </div>
						  </div>
						  
						  <div class="form-group">
						    <label for="img">이미지 등록</label>
						    <input type="file" name="form-control" id="imageUpload" multiple="multiple" name="imageUpload" value="${noticeFaqs.img}">
						  </div>
						  
				    </form>	
				  </li>
				</ul>




         <div class="form-group">
            <div class="col-sm-offset-4  col-sm-4 text-center">
               <button type="button" class="btn btn-primary update">수정</button>
               <button type="button" class="btn btn-primary delete">삭제</button>
               <a class="btn btn-primary btn" href="#" role="button">취소</a>
               <button type="button" class="btn btn-primary back">이전</button>          
            </div>
         </div>
   </div>


</body>

</html>