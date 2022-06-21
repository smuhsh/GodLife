<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>매일출석 이벤트</title>

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
            padding-top : 230px;
        }
      .img-rounded{
    block-size: 100px;
    margin-top: 10px;
    margin-bottom: 15px;
      }
      #checkpoint{
      text-align: center;
      }  
      
      #box100{
      margin-left: 30px;
      margin-right: 30px;
      }
      #box10000{
      margin-left: 30px;
      margin-right: 30px;
      }    
	  #month{
	  text-align: center;
	  }
	  #title{
	  text-align: center;
	  }          
    </style>
    
	<script type="text/javascript">
	var now = new Date();	// 현재 날짜 및 시간
	var year = now.getFullYear();
	var month = ('0' + (now.getMonth() + 1)).slice(-2);
	var day = ('0' + now.getDate()).slice(-2);
	var dateString = year + '-' + month  + '-' + day;
	console.log(dateString);
	 $(function() {
		 $("#month").append("<h2>"+month+"월</h2>");
	
	 });
	 
	 
	 $(function(){
		 for(var i =1;i<29;i++){
			$(document).on("click","#day"+i,function(){
				if($("input[name='userEmail']").val() == ""){
					location.href="/user/login";
				}else{
					if(dateString ==$("input[name='regDate']").val()){
						alert("오늘은 이미 이벤트에 참여하셨습니다.")
					}else{
						
				var j = $(this).data("param");
				alert("출석이벤트참가");
				if(j>0 && j<14 || j>14 && j<28){
				$.ajax({
					url:"/operator/operatorRest/addOperatorJoinDayEvent?eventNo=1&rewardPoint=100",
		      		method:"GET",
		      		dateType:"json",
		      		headers:{
		      			"Accept":"application/json",
		      			"content-Type":"application/json"
		      		},
					success:function(JSONData){
						$("#day"+j+"img").remove();
						$(JSONData).each(function(){
						var displayView ="<img src=\"/resources/images/success100point.png\" class=\"img-rounded\" id=\"day"+j+"img\">"
						var displayButton = "<button type=\"button\" id=\"successbutton\" class=\"btn btn-danger\" >"+j+"일차</button>"
						$(".successday"+j).append(displayView);
						$("#day"+j).remove();
						$("#button"+j).append(displayButton);
						});
					}
				});
				}else if(j==14 || j==28){
					$.ajax({
						url:"/operator/operatorRest/addOperatorJoinDayEvent?eventNo=1&rewardPoint=10000",
			      		method:"GET",
			      		dateType:"json",
			      		headers:{
			      			"Accept":"application/json",
			      			"content-Type":"application/json"
			      		},
						success:function(JSONData){
							$("#day"+j+"img").remove();
							$(JSONData).each(function(){
								var displayView ="<img src=\"/resources/images/success10000point.png\" class=\"img-rounded\" id=\"day"+j+"img\">"
								var displayButton = "<button type=\"button\" id=\"successbutton\" class=\"btn btn-danger\" >"+j+"일차</button>"
								$(".successday"+j).append(displayView);
								$("#day"+j).remove();
								$("#button"+j).append(displayButton);
							});
						}
					});
				}
			}
				}
		 });
		}
	 });
	 
	</script>
	
</head>

<body bgcolor="#ffffff" text="#000000">
<form name="detailForm" method="post">
	<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->
	<div class="container">
	<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="row">
		<div class="col-md-4"></div>
	 	<div class="col-md-4" id="title"><div class="text-info">
	       <h3>매일출석체크하면 행운이!</h3>
	    </div></div>
	  	<div class="col-md-4"></div>
	</div>
	<div class="row">
		<div class="col-md-4"></div>
	 	<div class="col-md-4" id="month"><div>
	       
	    </div></div>
	  	<div class="col-md-4"></div>
	</div>
	<div class="row">
		<div id="checkpoint">
		<c:forEach var ="i" begin ="1" end="${totalCount}">
			<div class="col-md-1" id="box100">
				<div class="point100">
					<div id="button${i}">
					<button type="button" id="successbutton" class="btn btn-danger" >${i}일차</button>
					</div>
					<div class="successday${i}">
					<c:if test="${i==14 || i==28}">
					<img src="/resources/images/success10000point.png" class="img-rounded" id="day${i}img">
					</c:if>
					<c:if test="${i>0 && i<14 || i>14 && i<28}">
					<img src="/resources/images/success100point.png" class="img-rounded" id="day${i}img">
					</c:if>
					</div>
				</div>
			</div>
			</c:forEach>
			<c:forEach var ="i" begin ="${totalCountPlus}" end="28">
			<div class="col-md-1" id="box100">
				<div class="point100">
				
					<div id="button${i}">
					<button type="button" class="btn btn-info" id="day${i}" data-param="${i}">${i}일차</button>
					</div>
		
					<div class="successday${i}">
					<c:if test="${i==14 || i==28}">
					<img src="/resources/images/10000point.png" class="img-rounded" id="day${i}img">
					</c:if>
					<c:if test="${i>0 && i<14 || i>14 && i<28}">
					<img src="/resources/images/100point.png" class="img-rounded" id="day${i}img">
					</c:if>
					</div>
				</div>
			</div>
			</c:forEach>
			
			</div>
			<input type="hidden" name="userEmail" value="${user.userEmail}">
			<input type="hidden" name="regDate" value="${operatorJoinEvent.regDate}">
</div>
</div>
</form>

</body>
</html>