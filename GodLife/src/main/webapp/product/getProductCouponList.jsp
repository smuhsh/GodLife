<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>

<html lang="ko">

<head>
<meta charset="UTF-8">

<!-- 참조 : http://getbootstrap.com/css/   참조 -->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
   href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script
   src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>


<!-- Bootstrap Dropdown Hover CSS -->
<link href="/css/animate.min.css" rel="stylesheet">
<link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
<!-- Bootstrap Dropdown Hover JS -->
<script src="/javascript/bootstrap-dropdownhover.min.js"></script>


<!-- jQuery UI toolTip 사용 CSS-->
<link rel="stylesheet"
   href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<!-- jQuery UI toolTip 사용 JS-->
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<!--  ///////////////////////// CSS ////////////////////////// -->
<style>
body {
   padding-top: 50px;
   background-color: #708090 ;
   font-weight: bold; 
   font-family:impact;
}

fieldset {
   width: 400px;
   text-align: center;
   backgrond-color: white;
}

div.box {
   width: 500px;
   height: 50px;
   box-align:center middle;
   margin: 10px;
   padding: 10px;
   display: none;
   background-color: #ffcc00;
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

/////////////////구매 유효성 검사////////////
   function fncAddPointPurchasePoint() {
      var productNo = $('input[name="productNo"]:checked').val();
      var payOpt = $('input[name="payOpt"]:checked').val();
      var useStatus = $("input[name='useStatus']").val();
      var useDetail = $("input[name='useDetail']").val();

      alert(payOpt + " : payOpt   " + productNo + ": productNo   "
            + useStatus + ":useStatus  " + useDetail + ":useDetail");
      $("form").attr("method", "POST").attr("action",
            "/point/addPointPurchaseProduct").submit()
   }
   $(function() {
      $(".btn.btn-default").on("click", function() {
         fncAddPointPurchasePoint();
      });
   });

   function showDiv(element) {
      var tag = document.getElementsByClassName("box");

      for(var i=0; i<tag.length; i++){
         if(element.id+"Box" == tag[i].id)
            tag[i].style.display="block";
         else
            tag[i].style.display="none";
      }
   }
/////////////////////신규 포인트 상품 등록 Event  처리///////////////////////////
	 $(function() {
		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		 $( "button.btn.btn-primary.addP" ).on("click" , function() {
				self.location = "/product/addProductCouponView?productNo=${product.productNo}"
			});
	});    
////////////////////////////////////////////////

//============= productName 에 상품정보보기  Event  처리(Click) =============
	 //==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
	$(function() {
		         
	     $( ".images" ).on("click" , function() {
	    	 self.location ="/product/getProductCoupon?productNo="+$(this).attr("productNo");
	     });
	
	});
	     



</script>
</head>

<body>
   <form class="form-horizontal">
      <!-- ToolBar Start /////////////////////////////////////-->
      <jsp:include page="/layout/toolbar.jsp" />
      <!-- ToolBar End /////////////////////////////////////-->

      <!--  화면구성 div Start /////////////////////////////////////-->
      <div class="container">

         <!-- 상품 이미지 위쪽 /////////////////////////////////////-->
         <div class="row">
            <div class="col-md-6 text-left">
               <h3 class="text-primary font-weight-bold" style="color:#000000; font-weight: bold; font-family:impact;">쿠폰상품 전체목록</h3>
         	</div>
         	
         	 <div class="col-md-6 text-right"> 
				<br/>
            	<button type="button" class="btn btn-primary addP">신규 쿠폰 상품 등록</button> 
	         </div>
         </div>
         
		<!-- 상품 이미지 시작 /////////////////////////////////////-->
		<div class="row">
         <c:set var="i" value="0" />
         <c:forEach var="product" items="${list}">
            <c:set var="i" value="${ i+1 }" />

	            
	        	               
	            <div class="col-xs-6 col-md-3" style="height: auto; width: auto;">
	            <input align="center" type="radio" name="productNo" value="${product.productNo}" checked />
		            <div style="height: auto; width: auto; border: 1px solid black; margin:20px;">
		               <img productNo="${ product.productNo }"  width="400" height="400" class="images" src="../images/uploadFiles/${product.productImg }"
		                  onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'">
		               <div>${ product.productName }</div>
		               <div>${ product.productPrice }원</div>
		               <!-- 상세내용은 상세정보 창에서만 보이게 하자  -->
		               <!--  <div>${ product.productDetail }</div>-->
					</div>
            </div>
         </c:forEach>
		</div>

         <!--  table End /////////////////////////////////////-->
 			
         <div class="col-xs-6 col-md-12">
           
            </div>
            <!-- label Tag 사용 / 미사용의 차이점 : 이름 3을 Click 해보면... -->
            <div class="form-group">
               <div class="col-sm-offset-2 col-sm-10">
                  <input type="hidden" name="userEmail" value="${user.userEmail}" />
                  <input type="hidden" name="useStatus" value="1" /> <input
                     type="hidden" name="useDetail" value="1" />
               </div>
            </div>
            <hr/>
            <button type="button" class="btn btn-default">구매</button>
         </div>

         <!--  화면구성 div End /////////////////////////////////////-->
  		 </form>
</body>
</html>