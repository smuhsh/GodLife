<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	
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
	  body {
            padding-top : 50px;
        }
    </style>
    
     <!--  ///////////////////////// JavaScript ////////////////////////// -->

<head>
	<meta charset="EUC-KR">
	<title>상품 목록조회</title>

	<link rel="stylesheet" href="/css/admin.css" type="text/css">

	<!-- CDN(Content Delivery Network) 호스트 사용 -->
	<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
	<script type="text/javascript">

	//=====기존Code 주석 처리 후  jQuery 변경 ======//
	// 검색 / page 두가지 경우 모두 Form 전송을 위해 JavaScrpt 이용
	
	function fncGetUserList(currentPage) {
			//document.getElementById("currentPage").value = currentPage;
			$("#currentPage").val(currentPage)
		   	//document.detailForm.submit();
			$("form").attr("method" , "POST").attr("action", "/product/listProduct").submit();
		};
	//==========================================================//
   	//==> 추가된부분 : "검색" ,  prodName link  Event 연결 및 처리
   	
	$(function (){
			
	      //==> 검색 Event 연결처리부분
	      //==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
	      //==> 1 과 3 방법 조합 : $("tagName.className:filter함수") 사용함. 
			$("button.btn.btn-default").on("click", function(){
		    //Debug..
		    //alert(  $( "td.ct_btn01:contains('검색')" ).html() );
			fncGetUserList(1);	
			});
		});
	
	      
	//============= productName 에 상품정보보기  Event  처리(Click) =============
	$(function() {
		         
	     $( "td:nth-child(2)" ).on("click" , function() {
	        if(${param.menu == 'manage'}) {
	        	self.location ="/product/updateProductCoupn?prouctdNo="+$(this).attr("productNo");
	        } else {
	        	self.location ="/product/getProductCoupon?productNo="+$(this).attr("productNo");
	        }
	     });
		         
	//==> prodName LINK Event End User 에게 보일수 있도록 
	     $( "td:nth-child(2)" ).css("color" , "red");   
		         
	    });
	
    //============= prodName 에 쿠폰 상품정보보기  Event  처리 (double Click)=============	
	
	$(function(){
		
	$( "td:nth-child(5) > i" ).on("click", function() {
		
		var productNo = $(this).next().val();
		
		 $.ajax( 
                 {
                    url : "/product/json/getProductCoupon/"+productNo,
                    method : "GET",
                    dataType : "json",
                    headers : {
                       "Accept" : "application/json",
                       "Content-Type" : "application/json"
                    },
                    success : function(JSONData , status) {

                       var displayValue = "<h6>"
                                            +"상품번호 : "+JSONData.productNo+"<br/>"
                                            +"상품명 : "+JSONData.productName+"<br/>"                                             
                                            +"상품이미지 : "+JSONData.productImg+"<br/>"                                             
                                            +"상품상세정보 : "+JSONData.productDetail+"<br/>"                                                                                         
                                            +"가  격 : "+JSONData.productPrice+"<br/>"
                                            +"상태값 : "+JSONData.status+"<br/>"
                                            +"</h6>";
                       $("h6").remove();
                       $( "#"+JSONData.productNo+"" ).append(displayValue);
                 }
              });
           
        });
	

 	//=========================================================//
	 //==> productNo LINK Event End User 에게 보일수 있도록 
    $( ".ct_list_pop td:nth-child(3)" ).css("color" , "red");
    $("h7").css("color" , "red");
    
    //==> 아래와 같이 정의한 이유는 ??
    $(".ct_list_pop:nth-child(4n+6)" ).css("background-color" , "whitesmoke");
 });
    
    $(function() {
       
    $( "td:nth-child(5)> i" ).on("click" , function() {
       
       var productNo = $(this).next.val();
       
          $.ajax( 
                {
                   url : "/product/json/getProductCoupon/"+productNo,
                   method : "GET",
                   dataType : "json",
                   headers : {
                      "Accept" : "application/json",
                      "Content-Type" : "application/json"
                   },
                   success : function(JSONData , status) {

                      var displayValue = "<h3>"
					                          +"상품번호 : "+JSONData.productNo+"<br/>"
					                          +"상품명 : "+JSONData.productName+"<br/>"                                             
					                          +"상품이미지 : "+JSONData.productImg+"<br/>"                                             
					                          +"상품상세정보 : "+JSONData.productDetail+"<br/>"                                                                                         
					                          +"가  격 : "+JSONData.productPrice+"<br/>"
					                          +"상태값 : "+JSONData.status+"<br/>"
                                           +"</h3>";
                      $("h3").remove();
                      $( "#"+JSONData.productNo+"" ).append(displayValue);
                }
             });
          
       });
    
 	//==========================================================//
	  //==> UI 수정 추가부분  :  userId LINK Event End User 에게 보일수 있도록 
      $( ".ct_list_pop td:nth-child(3)" ).css("color" , "red");
      $("h7").css("color" , "red");
 	
 	
 	      //==> 아래와 같이 정의한 이유는 ??
 	      //==> 아래의 주석을 하나씩 풀어 가며 이해하세요.               
 	      $(".ct_list_pop:nth-child(4n+6)" ).css("background-color" , "whitesmoke");
 	      //console.log ( $(".ct_list_pop:nth-child(1)" ).html() );
 	      //console.log ( $(".ct_list_pop:nth-child(2)" ).html() );
 	      //console.log ( $(".ct_list_pop:nth-child(3)" ).html() );
 	      //console.log ( $(".ct_list_pop:nth-child(4)" ).html() ); //==> ok
 	      //console.log ( $(".ct_list_pop:nth-child(5)" ).html() ); 
 	      console.log ( $(".ct_list_pop:nth-child(6)" ).html() ); //==> ok
 	      //console.log ( $(".ct_list_pop:nth-child(7)" ).html() ); 
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
			<c:set var="name" value="${param.menu}"/>
				<c:if test="${name =='manage' }">
						<h3>상품 관리</h3>
				</c:if>
				
				<c:if test="${name =='search' }">
						<h3>상품 목록조회</h3>
				</c:if>
	    </div>

		

 
	    <!-- table 위쪽 검색 Start /////////////////////////////////////-->
	    <div class="row">
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-primary">
		    		전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지
		    	</p>
		    </div>


		    <div class="col-md-6 text-right">
			    <form class="form-inline" name="detailForm">
			    
				  <div class="form-group">
				    <select class="form-control" name="searchCondition" >
						<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>상품번호</option>
						<option value="1"  ${ ! empty search.searchCondition && search.searchCondition==1 ? "selected" : "" }>상품명</option>
						<option value="2"  ${ ! empty search.searchCondition && search.searchCondition==2 ? "selected" : "" }>상품가격</option>
					</select>
				  </div>
				  
				  <div class="form-group">
				    <label class="sr-only" for="searchKeyword">검색어</label>
				    <input type="text" class="form-control" id="searchKeyword" name="searchKeyword"  placeholder="검색어"
				    			 value="${! empty search.searchKeyword ? search.searchKeyword : '' }"  >
				  </div>
				  
				  <button type="button" class="btn btn-default">검색</button>
				  
				  <!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
				  <input type="hidden" id="currentPage" name="currentPage" value=""/>
				  
				</form>
	    	</div>
	    	
		</div>
		<!-- table 위쪽 검색 Start /////////////////////////////////////-->
		
		
      <!--  table Start /////////////////////////////////////-->
      <table class="table table-hover table-striped" >
      
        <thead>
          <tr>
            <th align="center">No</th>
            <th align="left" >쿠폰 상품명</th>
            <th align="left">쿠폰 가격</th>
            <th align="left">쿠폰 내용</th>
            <th align="left">쿠폰 이미지</th>
            <th align="left">상태값</th>
            
          </tr>
        </thead>
       
		<tbody>
		
		  <c:set var="i" value="0" />
		  <c:forEach var="product" items="${list}">
			<c:set var="i" value="${ i+1 }" />
			<tr>
			  <td align="center">${ i }</td>
			  <td align="left" productNo="${ product.productNo }" title="Click : 상품정보 확인">${ product.productName }</td>
			  <td align="left">${ product.productPrice }</td>
			  <td align="left">${ product.productDetail }</td>
			  <td align="left">
			  	<i class="glyphicon glyphicon-ok" id= "${product.productNo}"></i>
			  	<input type="hidden" value="${product.productNo}">
			  </td>
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