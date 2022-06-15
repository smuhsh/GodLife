<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
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
	
	<!--  CSS 추가 : 툴바에 화면 가리는 현상 해결 :  주석처리 전, 후 확인-->
	<link rel="stylesheet" href="/resources/css/thumbnail.css" type="text/css">
	<link rel="stylesheet" href="/resources/css/title.css" type="text/css">
	<link rel="stylesheet" href="/resources/css/search.css" type="text/css">
   	
   	<script type="text/javascript">
   		function fncGetList(currentPage){
   			$("#currentPage").val(currentPage)
   			$("form[name='challengeList']")
   			.attr("action","/challenge/listChallenge").
   			attr("method","GET").submit();
   		}
   		$(function(){
   			$("button:contains('검색')").on("click",function(){
   				fncGetList(1);
   			});
   			
   			
   			$("a#deleteChallenge").on("click",function(){
   				
   				var challengeNo = $(this).data("param");	
				alert(challengeNo);
   				
   					if (window.confirm("챌린지를 삭제 하시겠습니까?")) {
   						$.ajax({
   							
   							url:"/challenge/challengeRest/deleteChallenge",
   							method:"POST",
   							dataType:"json",
   							headers:{
   								"Accept":"application/json",
   								"Content-Type":"application/json"
   							},
   							data:JSON.stringify({
   								challengeNo:challengeNo
   							}),
   							success:function(JSONData,status){
   								window.location.href = '/challenge/listChallenge?challengeListOpt=add';
   							}
   							
   						});
   					};
   			});
   		
				$("a#deleteChallengePick").on("click",function(){
				
					var challengeNo = $(this).data("param");	
					alert(challengeNo);
					
						if (window.confirm("찜목록에서 삭제 하시겠습니까?")) {
							$.ajax({
								
								url:"/challenge/challengeRest/deleteChallengePick",
								method:"POST",
								dataType:"json",
								headers:{
									"Accept":"application/json",
									"Content-Type":"application/json"
								},
								data:JSON.stringify({
									challengeNo:challengeNo
								}),
								success:function(JSONData,status){
									window.location.href = '/challenge/listChallenge?challengeListOpt=pick';
								}
								
							});
					};
			});
   		
   		
   		
   		
   		
   		
   		});
   		
   		
   		
   		
   		
		
   	</script>
<title>Insert title here</title>
</head>
<body>
<form name="challengeList">
			<c:if test = "${challengeListOpt == 'total'}">
	 			<input type="hidden" name ="challengeListOpt" value="total">
	 		</c:if>
	 		<c:if test = "${challengeListOpt == 'add'}">
	 			<input type="hidden" name ="challengeListOpt" value="add">
	 		</c:if>
	 		<c:if test = "${challengeListOpt == 'join'}">
	 			<input type="hidden" name ="challengeListOpt" value="join">
	 		</c:if>
	 		<c:if test = "${challengeListOpt == 'pick'}">
	 			<input type="hidden" name ="challengeListOpt" value="pick">
	 		</c:if>
				
	<div class="container">
	<div class="img_item">
	<jsp:include page="/layout/toolbar.jsp" />
	<div class="row">
			  <div class="col-xs-6 col-sm-1">
			  
			  </div>
			  <div class="col-xs-6 col-sm-10">
			  	<div class="page-header">
			  		<c:if test = "${challengeListOpt == 'total'}">
			  			<h2>챌린지 목록</h2>
			  		</c:if>
			  		<c:if test = "${challengeListOpt == 'add'}">
			  			<h2>등록한 챌린지 목록</h2>
			  		</c:if>
			  		<c:if test = "${challengeListOpt == 'join'}">
			  			<h2>참여한 챌린지 목록</h2>
			  		</c:if>
			  		<c:if test = "${challengeListOpt == 'pick'}">
			  			<h2>찜한 챌린지 목록</h2>
			  		</c:if>
				</div>
				
					<div class="row">
					  <div class="col-xs-6 col-sm-2"></div>
					  <div class="col-xs-6 col-sm-4">			
					  </div>
					  <div class="col-xs-6 col-sm-6">
						  	<div class="input-group">
						  		<div class="form-group">
								    <select class="form-control" name="orderCondition" >
								    	<option value="0">관심사</option>
										<option value="1" ${search.orderCondition == 1 ? "selected" : "" }>운동</option>
										<option value="2" ${search.orderCondition == 2 ? "selected" : ""}>식습관</option>
										<option value="3" ${search.orderCondition == 3 ? "selected" : "" }>공부</option>
										<option value="4" ${search.orderCondition == 4 ? "selected" : ""}>취미</option>
										<option value="5" ${search.orderCondition == 5 ? "selected" : "" }>생활</option>
									</select>
								 </div>
								  &nbsp;
							  	<div class="form-group">
								    <select class="form-control" name="searchCondition" >
								    	<option value="2">정렬 옵션</option>
										<option value="0" ${search.searchCondition == "0" ? "selected" : "" }>신규</option>
										<option value="1" ${search.searchCondition == "1" ? "selected" : ""}>인기</option>
									</select>
								 </div>
								  &nbsp;
						    	<input type="text" class="form-control" id="searchKeyword" name="searchKeyword" placeholder="제목 입력">
						  		 &nbsp;
						  		<button class="btn btn-default" id="search">검색</button>
						 	 </div>
					  </div>
					</div>
				
				
				
				
			<div id="challengeListContainer">
				<div id="challenge">	
				 <c:forEach var="challenge" items="${challengeList}">
				  <div class="col-sm-6 col-md-4">
				    <div class="thumbnail">
				    <div id="imgArea">
				      <a id="img" href="/challenge/getChallenge?challengeNo=${challenge.challengeNo }">
				      <img style="width:230px; height:230px;" src="/resources/images/uploadFiles/${challenge.challengeThumbnailImg }" 
				      onerror="this.src='https://dummyimage.com/230x230/1af0d4/000000.gif'">
				      </a>
				      	<div id="startEndDate">
				      			<p>기간 : ${challenge.startDate} ~ ${challenge.endDate }</p>
				      	</div>
				      </div>
				      <div class="caption">
					       <h4 id="title">${challenge.challengeTitle }</h4>
					       <h5 id="joinCount">참여자 수 : (${challenge.joinCount })</h5>
					       <h5 class="info">Host : ${challenge.hostNick }</h5>
					       <h5 class="info" id="categ">관심사 : ${challenge.challengeCategName }</h5>
					       <c:if test="${challenge.challengeStatus == 0}">
					       		<h5 class="status">상태 : 시작전</h5>
					       </c:if>
					       <c:if test="${challenge.challengeStatus == 1}">
					       		<h5 class="status">상태 : 진행중</h5>
					       </c:if>
					       <c:if test="${challenge.challengeStatus == 2}">
					       		<h5 class="status">상태 : 종료</h5>
					       </c:if>
					       <h5 id="regDate">등록일 : ${challenge.challengeRegDate }</h5>
					       <c:if test="${challengeListOpt == 'add' }">
					       		<a href="#" id="deleteChallenge" data-param="${challenge.challengeNo }" >
					       			<h5 class="delete">[삭제하기]</h5>
					       		</a>
					       </c:if>
					       <c:if test="${challengeListOpt == 'pick' }">
					       		<a href="#" id="deleteChallengePick" data-param="${challenge.challengeNo }" >
					       			<h5 class="delete">[삭제하기]</h5>
					       		</a>
					       </c:if>
				      </div>
				    </div>
				  </div>
				</c:forEach>
			</div>
		</div>
			
			
						
			  </div>
			  <div class="col-xs-6 col-sm-1">
				  	
			  </div>
		</div>
	
	</div>
	<input type="hidden" id="currentPage" name="currentPage" value=""/>
	</div>
	<div class="container">
		<jsp:include page="../common/pageNavigator_new.jsp"/>
	</div>
</form>
</body>
</html>