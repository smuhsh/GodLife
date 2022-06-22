<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>



<html>
<head>
<meta charset="UTF-8">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap.min.css" 
integrity="sha384-HSMxcRTRxnN+Bdg0JdbxYKrThecOKuH5zCYotlSAcp1+c8xmyTe9GYg1l9a69psu" crossorigin="anonymous">

<!-- Optional theme -->
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/css/bootstrap-theme.min.css" 
integrity="sha384-6pzBo3FDv/PJ8r2KRkGHifhEocL+1X2rVCTTkUfGk7/0pbek5mMa1upzvWbrUbOZ" crossorigin="anonymous">

<!-- Latest compiled and minified JavaScript -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@3.4.1/dist/js/bootstrap.min.js" 
integrity="sha384-aJ21OjlMXNL5UyIl/XNwTMqvzeRMZH2w8c5cRVpzpU8Y5bApTppSuUkhZXN0VxHd" 
crossorigin="anonymous"></script>
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>

<link href="/resources/css/listCertiImg.css" rel="stylesheet">
<style>
#deleteButton{
     position: relative;
    left: 550px;
    height: 30px;
}
</style>

<script type="text/javascript">
     
$(function() {
    $("#deleteButton").on("click", function() {
    	if(window.confirm("인증 이미지 삭제시 달성률에 적용되며.\n"+
				  "인증 가능한 날짜가 아니면 재 업로드가 불가능합니다.\n"+
				  "삭제 하시겠습니까?"))
       fncDeleteChallengeCertiImg();
    });
 });
 function fncDeleteChallengeCertiImg(){
	 $("form").attr("method", "POST").attr("action","/challenge/deleteChallengeCertiImg").submit()
 };
</script>

<title>Insert title here</title>
</head>
<body>
<jsp:include page="/layout/toolbar.jsp" />
<form>
   <div class="container">
   <div class="row">
   	<div class="col-md-4"></div>
   	<div class="col-md-4">
      <div class="page-header">
            <p id="header">상세 인증 이미지</p>
            <input type="hidden" name="certiImgNo" value="${certiImg.certiImgNo}">
      </div>
   	</div>
   	<div class="col-md-4"></div>
   </div>
      <div id="infinit-scroll">
         <div id="flat">
            <div id="pages">
               <input type="hidden" name="page" value="${page }">
            </div>
         </div>
      
             <div class="thumbnail">
                  <img style="width:700px; height:700px; position:relative; top:30px;" src="/resources/images/uploadFiles/${certiImg.certiImg }" 
                  onerror="this.src='https://dummyimage.com/700x700/1af0d4/000000.gif'">
		               <div class="caption">
		                  <div id="user-info">
		                     <img id="profile" src="/resources/images/uploadFiles/${certiImt.user.profileImg }"
		                          onerror="this.src='/resources/images/Default-Profile-Picture-Free-PNG-Image.png'">
		                    <p id="user">${certiImg.user.nick }</p>
		                    <c:if test="${user.userEmail == certiImg.user.userEmail}">
		                    <button type="button" id="deleteButton" class="btn btn-danger">삭제</button>
		                    </c:if>
		                 </div>
                   <div class="info">
                      <p class="font-size">관심사 : ${certiImg.categName }<p>
                   </div>
                   <div id="bord"></div>
                   <div id="review">
                      <div class="like-dislike">
                          <p class="like-dislike-model"><span class="glyphicon glyphicon-thumbs-up" aria-hidden="true"></span></p>
                         &nbsp;
                         <p class="font-size" id="like">${certiImg.like }<p>
                         <p class="like-dislike-model"><span class="glyphicon glyphicon-thumbs-down" aria-hidden="true"></span></p>
                         &nbsp;
                         <p class="font-size" id="dislike">${certiImg.dislike }<p>
                      </div>
                   </div>
                   <center id="com-btn${certiImg.certiImgNo }">
                      <button type="button" id="comment-btn-open" class="btn btn-default" 
                      data-param="${certiImg.certiImgNo }">댓글 펼치기/접기</button> 
                 </center>
                 <div id="comment${certiImg.certiImgNo }">
                   </div>
               </div>
             </div>
             </div>
   </div>
   </form>
</body>
</html>