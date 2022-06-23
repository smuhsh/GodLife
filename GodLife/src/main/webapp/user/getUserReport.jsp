<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>
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
   
   
   <!-- jQuery UI toolTip 사용 CSS-->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <!-- jQuery UI toolTip 사용 JS-->
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
	
	.container{
    padding-top:220px;
    }
    
    h2{
                font-size: 2.3rem;
                padding-right: 100px;
                font-weight: bold;
            }
    
	
    </style>
    
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">
	
	//===============레드카드 발급======================
	
	$(function() {
		 //==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		$( "writeBtn1" ).on("click" , function() {
		
			
		});
	 });
	
	/*
		//=============    검색 / page 두가지 경우 모두  Event  처리 =============	
		function fncGetList(currentPage) {
			$("#currentPage").val(currentPage)
			$("form").attr("method" , "POST").attr("action" , "/user/listUserReport").submit();
		}
		
		//============= "검색"  Event  처리 =============	
		 $(function() {
			 //==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$( "button.btn.btn-default" ).on("click" , function() {
			fncGetList(1);
			});
		 });
		
		*/
		
		
		/*
		//============= userEmail 에 회원정보보기  Event  처리(Click) =============	
		 $(function() {
		
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$( "td:nth-child(2)" ).on("click" , function() {
				 self.location ="/user/getUser?userEmail="+$(this).text().trim();
			});
						
			//==> userEmail LINK Event End User 에게 보일수 있도록 
			//$( "td:nth-child(2)" ).css("color" , "black");
		});	
		
		*/
		
			
	</script>
	
</head>

<body>
	   	
	   		<!-- ToolBar Start /////////////////////////////////////-->
			<jsp:include page="/layout/toolbar.jsp" />
   			<!-- ToolBar End /////////////////////////////////////-->
	   	
	<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
	
	<div class="head_aticle" align="center" id = "head_aticle">
	      <h2 class="tit" style="color: #333;">신고 회원 상세 목록조회(관리자용)</h2>
	    </div>
	    
	    <br></br>
	    <!-- table 위쪽 검색 Start /////////////////////////////////////-->
	    <div class="row">
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-light">
		    		전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지
		    	</p>
		    </div>
		    
		    <div class="col-md-6 text-right">
			    <form class="form-inline" name="detailForm">
			    
			    <!--  
				  <div class="form-group">
				    <select class="form-control" name="searchCondition" >
						<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>회원 이메일</option>
						<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>닉네임</option>
						<option value="2"  ${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>신고 개수</option>
						<option value="3"  ${ ! empty search.searchCondition && search.searchCondition==3 ? "selected" : "" }>레드카드 개수</option>
					</select>
				  </div>
				  
				  <div class="form-group">
				    <label class="sr-only" for="searchKeyword">검색어</label>
				    <input type="text" class="form-control" id="searchKeyword" name="searchKeyword"  placeholder="검색어"
				    			 value="${! empty search.searchKeyword ? search.searchKeyword : '' }"  >
				  </div>-->
				  
				<!-- <button type="button" class="btn active btn_join" id="writeBtn1" style="float: right;  margin-right: 10px;">레드카드 발급</button>-->
				  
				  <!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
				  <input type="hidden" id="currentPage" name="currentPage" value=""/>
				  
				</form>
	    	</div>
	    	
		<!-- table 위쪽 검색 Start /////////////////////////////////////-->
		
		<br></br>
      <!--  table Start /////////////////////////////////////-->
      <table class="table table-hover table-striped">
      
       <thead>
       <tr class="bg-light">
          <th scope="col" width="10%">신고번호</th>
            <th scope="col" width="20%">신고자</th>
            <!--  <th align="left" >대상</th> -->
             <th scope="col" width="20%">날짜</th>
            <th scope="col" width="10%" >사유</th>
            <th scope="col" width="10%" >유형</th>
            <th scope="col" width="10%" >인증이미지</th>
            <th scope="col" width="10%" >댓글</th>
             <th scope="col" width="10%" >쪽지</th>
          </tr>
        </thead>
       
		<tbody>
		
		  <c:set var="i" value="0" />
		  <c:forEach var="report" items="${list}">
			<c:set var="i" value="${ i+1 }" />
			<tr>
			 <!-- <td align="center">${ i }</td>--> 
			<td align="center" >${report.reportNo}</td>
			  <td align="left" >${report.reporterEmail}</td>
			  <!-- <td align="left">${report.targetEmail}</td>-->
			    <td align="left">${report.regDate}</td>
			  <td align="left">
			  
			   <c:if test = "${report.reason.trim() == '1'}">
			  욕설
			  </c:if>
			  <c:if test = "${report.reason.trim() == '2'}">
			  광고
			  </c:if>
			  <c:if test = "${report.reason.trim() == '3'}">
			  사기
			  </c:if>
			  <c:if test = "${report.reason.trim() == '4'}">
			  음란물
			  </c:if>
			   <c:if test = "${report.reason.trim() == '5'}">
			  도배
			  </c:if>
			  
			  </td>
			  
			   <td align="left">
			   <c:if test = "${report.reportPlace.trim() == '1'}">
			  인증이미지
			  </c:if>
			  <c:if test = "${report.reportPlace.trim() == '2'}">
			  댓글
			  </c:if>
			  <c:if test = "${report.reportPlace.trim() == '3'}">
			  쪽지
			  </c:if>
			 </td>
			   
			   <td align="left">${report.certiImgNO}
			   </td>
			   
			   <td align="left">${report.commentNo}</td>
			   <td align="left"><a  href="/user/getUserRecvMsg?msgNo=${report.msgNo}">${report.msgNo}</a></td>
			  
			  <td align="left">
			  	<input type="hidden" value="${report.targetEmail}">
			  </td>
			  
			</tr>
          </c:forEach>
        
        </tbody>
      
      </table>
	  <!--  table End /////////////////////////////////////-->
	  
 	</div>
 		</div>
 	<!--  화면구성 div End /////////////////////////////////////-->
 	
 	
 	<!-- PageNavigation Start... -->
	<jsp:include page="../common/pageNavigator_new.jsp"/>
	<!-- PageNavigation End... -->
	
</body>

</html>