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
   
      <link href="../css/kfonts2.css" rel="stylesheet">
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
   
   
   <!-- jQuery UI toolTip 사용 CSS-->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <!-- jQuery UI toolTip 사용 JS-->
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
	 
        h2{
                font-size: 2.3rem;
                padding-right: 100px;
            }
            
            #head_aticle{
            padding-top : 80px;
            }
            
            
            .container{
            padding-left: 250px;
            padding-top : 130px;
            }
            
            
            #caption{
            font-size: 15px;
            }
            
            h3{
            font-size: 15px;
            }
    </style>
    
    
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">
	
		//=============    검색 / page 두가지 경우 모두  Event  처리 =============	
			
		function fncGetList(currentPage) {
			//alert("검색");
			$("#currentPage").val(currentPage)
			$("form").attr("method" , "POST").attr("action" , "/user/listUserRecvMsg").submit();
		}
		
		//============= "검색"  Event  처리 =============	
		$(function(){
				$("button:contains('검색')").on("click",function(){
					fncGetList(1);
				});
		});
		
		//=============  선택 삭제처리 =============
		 $(function() {
				
				$("#writeBtn1").on("click" , function() {
					
					var checkCount = $("input[name='deleteCheck']:checked").length;
				    var array = new Array();
					$("input[name='deleteCheck']:checked").each(function() {
						array.push($(this).attr('id'));  <!-- 배열의 끝에 요소를 추가  -->
				    });
					
					//Debug..
					if(checkCount != 0) {
						alert(checkCount+"개의 쪽지를 삭제하시겠습니까?")
						self.location = "/user/deleteUserRecvMsg?checkList="+array;
					} else {
						alert("선택된 쪽지가 없습니다.")						
					}
				});
			});
		
		
	</script>
	
</head>

<body>

<!-- 왼쪽 레이아웃 삽입-->
		<jsp:include page="/user/mypageMain.jsp" />
	
	<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
	
		<div class="head_aticle" align="center" id = "head_aticle">
	      <h2 class="tit" style="color: #333;">받은 쪽지함</h2>
	   
	      <!-- <button type="button" class="btn active btn_join" id="writeBtn" style="float: right;  margin-right: 10px;">답장</button>-->
	    </div>
	    
	    
	    <br></br>
	    <!-- table 위쪽 검색 Start /////////////////////////////////////-->
	    <div class="row" >
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-secondary" >
		    		전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지
		    	</p>
		    </div>
		    
		    
		    <div class="col-md-6 text-right">
			    <form class="form-inline" name="detailForm">
			    
			  
				  <div class="form-group">
				    <select class="form-control" name="searchCondition" >
						<option value="0" ${search.searchCondition == "0" ? "selected" : ""}>닉네임</option>
						<option value="1" ${search.searchCondition == "1" ? "selected" : ""}>제목</option>
					</select>
				  </div>
				  
				
				  <div class="form-group">
				    <label class="sr-only" for="searchKeyword">검색어</label>
				    <input type="text" class="form-control" id="searchKeyword" name="searchKeyword"  placeholder="검색어"
				    			 value="${! empty search.searchKeyword ? search.searchKeyword : '' }"  >
				  </div> 
				  
				   <button type="button" class="btn btn-default" id="search">검색</button>
				   <button type="button" class="btn btn-default" id="writeBtn1" style="float: right;  margin-right: 10px;">선택삭제</button>
				  
				  <!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
				  <input type="hidden" id="currentPage" name="currentPage" value=""/>
				  
				</form>
	    
	    	
		</div>
			</div>
		<!-- table 위쪽 검색 Start /////////////////////////////////////-->
		

      <table class="table table-hover table-striped" >
      
        <thead>
        <tr class="bg-light">
          <th scope="col" width="10%"></th>
          <th scope="col" width="20%">닉네임</th>
          <th scope="col" width="40%" >제목</th>
          <th scope="col" width="20%">날짜</th>
        </tr>
      </thead>
       
		<tbody>
		
		  <c:set var="i" value="0" />
		  <c:forEach var="msg" items="${list}">
			<c:set var="i" value="${ i+1 }" />
			<tr>
			  <td align="center"><input type="checkbox" name="deleteCheck" id="${msg.msgNo}"></td>
			  <td align="left" > <a  href="/user/getUserTarget?userEmail=${msg.sendEmail}">${msg.nick} </a></td>
			  <td align="left" ><a  href="/user/getUserRecvMsg?msgNo=${msg.msgNo}">${msg.title}</a></td> 
			   <td align="left">${msg.regDate}</td>
			   
			  <!--  <td align="left">
			  	<input type="hidden" value="${user.userEmail}">
			  	<input type="hidden" value="${friendBlack.targetEmail}">
			  </td> -->
			  
			</tr>
          </c:forEach>
        
        </tbody>
      
      </table>
	  <!--  table End /////////////////////////////////////-->
	  
 	</div>
 	<!--  화면구성 div End /////////////////////////////////////-->
 	
 	
 	<!-- PageNavigation Start... -->
	<jsp:include page="../common/pageNavigator_new.jsp"/>
	<!-- PageNavigation End... -->
	
</body>

</html>