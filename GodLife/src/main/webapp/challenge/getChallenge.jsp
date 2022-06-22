<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!--   jQuery , Bootstrap CDN  -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-3.5.0.js"></script>
	<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
   <script src="https://developers.kakao.com/sdk/js/kakao.js"></script>
	<link rel="stylesheet" href="/resources/css/getChallenges.css" type="text/css">
	<link rel="stylesheet" href="/resources/css/title.css" type="text/css">
	<link rel="stylesheet" href="/resources/css/search.css" type="text/css">

	<script type="text/javascript">
		$(function(){
			$("button#back").on("click",function(){
				window.history.back();
			});
			
			$("button#join").on("click",function(){
				if(window.confirm("참여시 입장 포인트 만큼 포인트가 차감됩니다.\n"+
								  "참여 하시겠습니까?")){
					var joinPoint = ${challenge.joinPoint}
					var userPoint = ${user.totalPoint}
					if(${sessionScope.user != null}){
						if(userPoint < joinPoint){
							alert("보유 포인트가 부족합니다.");
							return;
						}
						
						$("form[name='challenge']").attr("method","POST").attr("action","/challenge/addChallengeJoin")
						.submit();
					}else{
						alert("로그인 후 이용해 주세요.");
					}
					
				}
				
			});
			
			$("button#exit").on("click",function(){
				if(window.confirm("챌린지에서 나가시겠습니까?")){
					if(${user.userEmail == challenge.hostEmail}){
						alert("회원님은 챌린지의 호스트입니다.\n"+
								  "챌린지에서 나갈시 챌린지가 삭제됩니다.");
						if(window.confirm("챌린지에서 나가시겠습니까?")){
							$("form[name='challenge']").attr("method","POST").attr("action","/challenge/deleteChallengeJoin")
							.submit();
						}
					}
					
					$("form[name='challenge']").attr("method","POST").attr("action","/challenge/deleteChallengeJoin")
					.submit();
				}
				
			});
			
			$("button#pick").on("click",function(){
				if(window.confirm("챌린지를 찜 하시겠습니까?")){
					
					
					$.ajax({
						
						url:"/challenge/challengeRest/getChallengePick?challengeNo=${challenge.challengeNo}",
						method:"GET",
						dataType:"json",
						headers:{
							"Accept":"application/json",
							"Content-Type":"application/json"
						},
						success:function(JSONData,status){
							if(JSONData.result != null ){
								alert("이미 찜한 챌린지 입니다.");
								return
							}else{
								alert("챌린지가 찜 목록에 등록 되었습니다.")
								$("form[name='challenge']").attr("method","POST").attr("action","/challenge/addChallengePick")
								.submit();
							}
							
						}
						
					});
					
				}
			});
		});
		
		
		
		$(function(){
			$("button#myCertiImgList").on("click",function(){
				window.
				open("/challenge/listChallengeJoinCertiImg?challengeNo=${challenge.challengeNo}&totalCertiCount=${challenge.totalCertiCount}"
						,"인증이미지 목록","left=300,top=200,width=1000,height=400");
			});
		
		
			$("button#joinUserList").on("click",function(){
				window.
				open("/challenge/listChallengeJoinUser?challengeNo=${challenge.challengeNo}"
						,"참여 회원목록","left=300,top=200,width=1000,height=400");
			});
			
		});
		
		
		
		
		
	</script>
<title>Insert title here</title>
</head>
<body>
<form name="challenge">
	<jsp:include page="/layout/toolbar.jsp" />
		<input type="hidden" name="challengeNo" value="${challenge.challengeNo }" >
		<input type="hidden" name="joinPoint" value="${challenge.joinPoint }" >
		<input type="hidden" name="hostEmail" value="${challenge.hostEmail }" >
		<input type="hidden" name="totalCertiCount" value="${challenge.totalCertiCount }" >
		<input type="hidden" name="certiDate" value="${challenge.certiDate }" >
	<div class="container">
			  <div class="col-xs-6 col-sm-1">
			  </div>
			  <div class="col-xs-6 col-sm-10">
			  	<div class="page-header">
			  		<h2>챌린지 조회</h2>
				</div>
				
			    <div class="thumbnail">
			    <div id="imgArea">
			    	 <img src="/resources/images/uploadFiles/${challenge.challengeThumbnailImg }" 
				      onerror="this.src='https://dummyimage.com/850x230/1af0d4/000000.gif'">
			    </div>
			      <div class="caption">
			        <p id="title">${challenge.challengeTitle }</p>
			        <p class="rightInfo" id="joinCount">참가 인원수 : ${challenge.joinCount } 명</p>
			        <p class="rightInfo" >시작일 : ${challenge.startDate } </p>
			        <p class="rightInfo" >종료일 : ${challenge.endDate }</p>
			        <div  id="certiyCycle">
				        <p class="rightInfo" >인증 주기 : 
				        <c:forEach var="certiCycle" items="${challenge.certiCycleName }">
				        	${certiCycle }
				        </c:forEach>
				        </p>
			        </div>
			        <p class="rightInfo" >입장 포인트 : ${challenge.joinPoint }</p>
			        <p class="rightInfo" >총 포인트 : ${challenge.joinPoint * challenge.joinCount}</p>
			        <p class="rightInfo" ></p>
			        
			       
			        
			        
			        
			        <div id="leftInfo">
			        	<div id="left-detail">
				        	<p>호스트</p>
				        	<div id="hostArea">
				        		<img id="profile" src="/resources/images/uploadFiles/${hostUser.profileImg }"
				        		onerror="this.src='/resources/images/Default-Profile-Picture-Free-PNG-Image.png'">
				        		<p id="nick">${challenge.hostNick }</p>
				        	</div>
				        	<p id="categ">관심사 : ${challenge.challengeCategName }</p>
			        	</div>
			        	
			        	<c:if test="${challenge.challengeStatus == 2}">
				        	<div id="notice">
				        			<p id="ending">${challenge.challengeTitle } 챌린지가 종료 되었습니다.</p>
				        	</div>
			        	</c:if>
			        	
						
			        	<div>
			        		<p id="detail">챌린지 소개</p>
			        		<textarea readonly>${challenge.challengeDetail }</textarea>
			        		
			        		<p id="rule">챌린지 규칙</p>
			        		<textarea readonly>${challenge.challengeRule }</textarea>
			        	</div>
			        	&nbsp;
			        </div>
			        
			        
			        	<c:if test="${challenge.challengeStatus == 1}">
			        	  <center>
			        		<div>
			        			<button type="button" id="myCertiImgList" class="btn btn-default abc">내 인증 이미지 목록 조회</button>
			        		</div>
			        			&nbsp;
			        		<div>
			        			<button type="button" id="joinUserList" class="btn btn-default abc">참여 회원 목록 조회</button>
			        		</div>
			              </center>
			              &nbsp;
			        	</c:if>
			        <div id="center">
			        		<c:if test="${challenge.challengeStatus == 2}">
			        			<div>
			        				<button type="button" id="reward" class="btn btn-default abc">포인트 환급 받기</button>
			        			</div>
			        		</c:if>
			        		
				        	<c:if test="${challenge.challengeStatus == 0}">
				        		<button type="button" id="back" class="btn btn-default abc">뒤로가기</button>
				        	</c:if>
				        	<c:if test="${challenge.challengeStatus != 0}">
				        		<div id="procceding">
				        			<button type="button" id="back" class="btn btn-default abc">뒤로가기</button>
				        		</div>
				        	</c:if>
				        		<c:forEach var="i" begin="1" step="1" end="20">
				        		&nbsp;
				        		</c:forEach>
				        	<c:if test="${challenge.challengeJoinFlag == 0 }">
				        		<button type="button" id="join" class="btn btn-default abc">참여하기</button>
				        	</c:if>
				        <c:if test="${challenge.challengeStatus == 0}">	
				        	<c:if test="${challenge.challengeJoinFlag == 1 }">
				        		<button type="button" id="exit" class="btn btn-default abc">나가기</button>
				        	</c:if>
				        </c:if>
			        </div>
			        
			        <div id="rightButton">
			        	<c:if test="${challenge.challengeJoinFlag == 0 }">
			        		<button type="button" id="pick" class="btn btn-default abc">찜하기</button>
			        	</c:if>
			        	<c:if test="${challenge.challengeStatus == 0}">
				        	<a id="kakao-link-btn" href="javascript:;">
							  <img
							    src="https://developers.kakao.com/assets/img/about/logos/kakaotalksharing/kakaotalk_sharing_btn_medium.png"
							    alt="카카오톡 공유 보내기 버튼" style="width:40px; height:40px; bottom:40px;"
							  />
							</a>
			        	</c:if>
			        </div>
			      </div>
			    </div>
				
			
			 </div>
			 
			 <div class="col-xs-6 col-sm-1">
			 </div>
		</div>
	
</form>
</body>
<script type="text/javascript">

Kakao.init('e7335f87c89936115650c79a74731cde');
console.log(Kakao.isInitialized());

Kakao.Link.createDefaultButton({
	  container: '#kakao-link-btn',
objectType: 'feed',
content: {
  title: '${challenge.challengeTitle }',
  description: '#${challenge.challengeCategName }',
  imageUrl:
    "http://192.168.0.16:8080/resources/images/uploadFiles/${challenge.challengeThumbnailImg }",
    link: {
	    mobileWebUrl:
	      'http://192.168.0.16:8080/challenge/getChallenge?challengeNo=${challenge.challengeNo}',
	    webUrl:
	      'http://192.168.0.16:8080/challenge/getChallenge?challengeNo=${challenge.challengeNo}',
	  },
	},
	social: {
	  subscriberCount: ${challenge.joinCount },
	},
	buttons: [
	  {
	    title: '웹으로 보기',
	    link: {
		    mobileWebUrl:
		      'http://192.168.0.16:8080/challenge/getChallenge?challengeNo=${challenge.challengeNo}',
		    webUrl:
		      'http://192.168.0.16:8080/challenge/getChallenge?challengeNo=${challenge.challengeNo}',
		  },
	  },
	  {
	    title: '앱으로 보기',
	    link: {
		    mobileWebUrl:
		      'http://192.168.0.16:8080/challenge/getChallenge?challengeNo=${challenge.challengeNo}',
		    webUrl:
		      'http://192.168.0.16:8080/challenge/getChallenge?challengeNo=${challenge.challengeNo}',
		  },
	  },
	]
});
</script>
</html>