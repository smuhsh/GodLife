<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

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
	  .container{
      	 padding-top : 200px;
        }
	  p{
	       		font-size : 14px;
	       		text-aling : center;
	       	}
       	
	textarea{
		width : 100%;
		resize: none;
	}
</style>

<script type="text/javascript">
	
	$(function(){
		$("#thumbnail").on("click",function(){
			var thumbnail = $("#thumbnail").data("param");
			
			var img = "<img src=\"/resources/images/uploadFiles/"+thumbnail+"\" style=\"width:200; height:200px;\">"+
					  "<p id=\"ex\" class=\"help-block\">이미지가 보이지 않는다면 잠시후 다시 시도해주십시오.</p>"+
					  "<a href=\"#\" id =\"cancelSummary\" class=\"btn btn-primary\" role=\"button\">그만보기</a>";
			
			$("div#img").html(img);
			
			$("a:contains('그만보기')").on("click",function(){
				$("img").remove();
				$("#ex").remove();
				$("a:contains('그만보기')").remove();
			});
		});
		
		
		$("button#updateChallenge").on("click",function(){
			$("form[name='addChallengeForm']").attr("method","GET")
			.attr("action","/challenge/updateChallenge").submit();
		});
		
		$("button#cancel").on("click",function(){
			$("form[name='addChallengeForm']").attr("method","POST")
			.attr("action","/challenge/addChallengeCancel").submit();
		});
		
		$("button#addChallenge").on("click",function(){
			
			var joinPoint = $("input[name='joinPoint']").val();
			alert(${user.totalPoint});
			alert(joinPoint);
			if(window.confirm("챌린지 등록시 입력한 입장포인트 만큼 보유포인트가 차감됩니다."+
							  "챌린지를 등록 하시겠습니까?")){
				
				if(${user.totalPoint} >= joinPoint){
					$("form[name='addChallengeForm']").attr("method","POST")
					.attr("action","/challenge/addChallenge").submit();
				}else{
					alert("보유 포인트가 부족합니다.");
					return;
				}
				
			}
			
			
			
			
		});
		
	});
	
</script>

</head>
<body>
<jsp:include page="/layout/toolbar.jsp" />

	<div class="container">
		<div class="row">
		  <div class="col-md-2"></div>
		  <div class="col-md-8">
		   <h3 class="text-center bg-info">챌린지 재확인</h3>
				
				<ul class="list-group">
				  <li class="list-group-item">
				    <form name="addChallengeForm" enctype="multipart/form-data">
				    
						  <div class="form-group">
						    <label for="exampleInputEmail1">챌린지 제목</label>
						    <input type="email" class="form-control" 
						    name="challengeTitle" id="exampleInputEmail1" value="${challenge.challengeTitle }" readOnly>
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputPassword1">입장 포인트</label>
						    <input type="number" class="form-control" 
						    name="joinPoint" id="exampleInputPassword1" value="${challenge.joinPoint }" readOnly>
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputPassword1">챌린지 소개</label>
						    <div>
						    	<textarea name="challengeDetail" cols="45" rows="6" id="detail"
						   		readOnly>${challenge.challengeDetail }</textarea>
						    </div>
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputPassword1">챌린지 규칙</label>
						    <div>
						    	<textarea name="challengeRule" cols="45" rows="6" id="rule"
						   		 readOnly>${challenge.challengeRule }</textarea>
						    </div>
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputPassword1">챌린지 관심사</label>
						    <div>
						    	<span>
						    		${challenge.challengeCategName }
						    	</span>
						    </div>
						  </div>
						  
						  <div class="form-group">
							  	<label for="exampleInputPassword1">인증 주기</label>
							  	<div>
							  	   <c:forEach var="certiCycle" items="${challenge.certiCycleName }">
										<span>${certiCycle }&nbsp;</span>
								   </c:forEach>
							    </div>	
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputEmail1">챌린지 시작일</label>
						    <div>
						    	<input type="text" name ="startDate" 
						    	id="startDatePicker" value="${challenge.startDate }" readonly>
						    </div>
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputEmail1">챌린지 종료일</label>
						    <div>
						    	<input type="text" name ="endDate" 
						    	id="endDatePicker" value="${challenge.endDate }" readonly>
						    </div>
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputEmail1">공개 범위</label>
						    <div>
						    	<c:if test="${challenge.openRange == 0}">
						    		전체 공개
						    	</c:if>
						    	<c:if test="${challenge.openRange == 1}">
						    		친구 공개
						    	</c:if>
						    </div>
						  </div>
						  
						  <div class="form-group">
						    <button type="button" id="thumbnail" class="btn btn-success" data-param="${challenge.challengeThumbnailImg }">
						    썸네일 이미지 보기
						    </button>
						    <div id="img">
						    </div>
						  </div>
						  
						  
						 <input type="hidden" name="fileName" value="${fileName }" />
						 <input type="hidden" name="challengeThumbnailImg" value="${challengeFileName }" />
						 <input type="hidden" name="challengeCategNo" value="${challenge.challengeCategNo }" />
						 <c:forEach var="certiCycle" items="${challenge.certiCycle }">
							<input type="hidden" name="certiCycle" value="${certiCycle}" />		
						 </c:forEach>
						 <input type="hidden" name="openRange" value="${challenge.openRange }" />
				    </form>	
				  </li>
				</ul>
				
		  	<center>
			  <div class="buttonGroup">
			  		<button type="button" id="cancel" class="btn btn-danger">등록 취소</button>
			  		&nbsp;&nbsp;&nbsp;
			  		<button type="button" id="updateChallenge" class="btn btn-info">수정</button>
			  		&nbsp;&nbsp;&nbsp;
			  		<button type="button" id="addChallenge" class="btn btn-primary">최종 등록</button>
			  </div>
		  </div>
			</center>
		  <div class="col-md-2"></div>
		</div>
	</div>
</body>
</html>