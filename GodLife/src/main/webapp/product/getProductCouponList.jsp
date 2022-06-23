<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page pageEncoding="UTF-8"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>

<html lang="ko">

<head>
<meta charset="UTF-8">
<!--   jQuery , Bootstrap CDN  -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   <link rel="stylesheet" href="/resources/css/toolbar2.css" />
   
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>

<!--  ///////////////////////// CSS ////////////////////////// -->
<style>
	body{
		font-weight : 600;
		background-image: url('/resources/images/uploadFiles/CouponBackGround.gif');
		background-repeat: no-repeat;
		background-size: cover;
	}	
	.images{
		border-radius: 30px;
	}

	#productName{
	width: 50px;
	height: 30px;
	}
	.cashbutton{
	width: 100px;
	height: 60px;
	}
	.images{
	width:250px;
	height: 250px;
	}
	.container{
	padding-top: 50px;
	}
	
	fieldset {
	   width: 560px;
	   text-align: center;
	   backgrond-color: white;
	
	}
	
	div.box {
	   width: 500px;
	   height: 220px;
	   box-align:center middle;
	   margin: 0;
	   padding: 5px;
	   display: none;
	   background-color: #9edbff;
	
	}
	
	.detail {
	   color : white ;
	   margin : 0;
	   width: 550px;
	   height: auto;
	   padding-top: 1px;
	   background-color: #070719 ;
	   text-align: center;
	   font-size: 22px;
	}
	
	#openmodal{
	color:blue;
	}

</style>

<!--  ///////////////////////// JavaScript ////////////////////////// -->
<meta charset="EUC-KR">
<title>상품 목록조회</title>


<script type="text/javascript">

/////////////////구매 유효성 검사////////////
   function fncAddPointPurchasePoint() {
      var productNo = $('input[name="productNo"]:checked').val();
      var useStatus = $("input[name='useStatus']").val();
      var useDetail = $("input[name='useDetail']").val();
      var totalPoint = $("input[name='totalPoint']").val();
      var point = $("input[name='point']").val();
      alert("totalPoint" + totalPoint);
      if (totalPoint < point ){
         alert("소지한 포인트가 부족합니다");
         return;
      }
      alert( productNo + ": productNo   "
            + useStatus + ":useStatus  " + useDetail + ":useDetail");
      $("form").attr("method", "POST").attr("action",
            "/point/addPointPurchaseProduct").submit()
   }
   $(function() {
      $("#buycash").on("click", function() {
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

//============= productName 에 쿠폰 상품 상세 정보(관리자 모드)  Event  처리(Click) =============
	 //==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
	$(function() {
		    var role=$("input[name='role']").val();
		    if(role=='2'){
	     $( ".images" ).on("click" , function() {
	    	 self.location ="/product/getProductCoupon?productNo="+$(this).attr("productNo");
	     });
		    }
	});
	     
//============= productName 에 상품정보보기 Ajax이용 (일반 회원용)  Event  처리(Click) =============
	$(function(){

		$( ".productName" ).on("click", function() {
			
			var productNo = $(this).data("value");
//			var productNo = $(this).next.val();

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
	                    	
	                       const displayDetail = 
	                    	    `<div class="detail" style="border-radius: 10% / 50%;margin-top: 50px;">
                          <br>
   
                                  <div id="first">상품 명 :&nbsp \${JSONData.productName} </div>
                                  <br>
             					  
                                  <div id="third">가격:&nbsp \${JSONData.productPrice}원 </div>
                                  <br>
                                  
                                  <div id="second">상품 상세정보: <br/> \${JSONData.productDetail} </div>
                                  <br>
                                  <input type="hidden" name="productName" value="\${JSONData.productName}" />
                                  <input type="hidden" name="point" value="\${JSONData.productPrice}" />
                                  
                              </div>`
	                        $("div.detail").remove();
                          $( "#ajax" ).append(displayDetail);
                          console.log(JSONData , status);
	                 }
	              });
	           
	        });
		//=========================================================//
		 //==> prodNo LINK Event End User 에게 보일수 있도록 
	});
</script>
</head>

<body>
   <form class="form-horizontal">
      <!-- ToolBar Start /////////////////////////////////////-->
      <jsp:include page="/layout/toolbar.jsp" />
      <!-- ToolBar End /////////////////////////////////////-->

      <!--  화면구성 div Start /////////////////////////////////////-->
      <br><br>
      <br>
      <br>
      <br>
      <br>
      <br>
      <br>
      <br>
      <div class="container">

         <!-- 상품 이미지 위쪽 /////////////////////////////////////-->
         <div class="row" style="height: 150px;">
            <div class="col-md-7 text-right" >
               <h1 class="text-primary" style="color:#f0f0f0;">쿠폰상품 전체목록</h1>
         	</div>
			<div class="col-md-6"></div>
       	    <div class="col-md-6 text-right"> 
				<br/>
				<br>
			    <br>
			    <c:if test="${sessionScope.user.role == '2'}">
            		<button type="button" class="btn btn-primary addP">신규 쿠폰 상품 등록</button>
            	</c:if>
            	<br>
            	<br>  
	        </div>
         </div>
		<!-- 상품 이미지 시작 /////////////////////////////////////-->
		<div class="row">
         <c:set var="i" value="0" />
         <c:forEach var="product" items="${list}">
            <c:set var="i" value="${ i+1 }" />            
               <div class="col-md-6">
               <input type="radio" name="productNo" class="productName" id="productName" data-value="${ product.productNo }" title="Click : 상품정보 확인" value="${product.productNo}"  />
               
                  <div>
                  		
                     <img  src="/resources/images/uploadFiles/${product.productImg}"  class="images" productNo="${ product.productNo }"
                        onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'"><br/>
                      
                    <!--   <div class="productName" id="productName" data-value="${ product.productNo }" title="Click : 상품정보 확인">${ product.productName }</div> -->
                     <div></div>
  
                    <!-- <div>${ product.productPrice }원</div> -->
                     <!-- 상세내용은 상세정보 창에서만 보이게 하자  -->
                     <!--  <div>${ product.productDetail }</div>-->
               </div>
            </div>
         </c:forEach>
      </div>
         <!--  table End /////////////////////////////////////-->
 			
         <div class="row">
         <div class="col-md-6">
            <fieldset>
               <hr />
               <h4 style="color:#f0f0f0;">
               <img src="/resources/images/uploadFiles/buy.png" class="cashbutton" id="buycash">
               &nbsp&nbsp&nbsp
               <input type="radio" id="pay" name="payOpt" value="1" onclick="showDiv(this);">&nbsp 쿠폰 설명
            </h4>
              
            </fieldset>
             <br>
            <div id="payBox" class="box" style="border-radius: 25% 10%;padding-left: 60px;display: block;padding-bottom: 5px;padding-top: 0px;height: 225px;" >
           <br>
		쿠폰 구매 유의사항<br>
		<br>
		<ol>
		<li>쿠폰은 보유 포인트로 구매가능 합니다. 보유 포인트가 구매 할 쿠폰 가격보다 적으면 구매가 이루어 지지 않습니다.</li>
		<li>보유한 쿠폰 개수는 마이페이지 - 쿠폰함 에서 확인하실 수 있습니다.</li>
		<li>인증사진 대처 쿠폰 : 챌린지를 참여하여 인증사진 업로드를 대처 할 수 있습니다.
		<li>레드카드 소멸 쿠폰 : 마이페이지에서 지급받은 레드카드가 존재한다면 사용 할 수있습니다.
		</ol>
            </div>  
	</div>
	<div class="col-md-6">
	<hr/>
	<h4 style="color:#f0f0f0;padding-top: 20px;">상품 정보</h4> 
  		<div id="ajax"></div>
 		</div>
            <!-- label Tag 사용 / 미사용의 차이점 : 이름 3을 Click 해보면... -->
            <div class="form-group">
               <div class="col-sm-offset-2 col-sm-10">
                  <input type="hidden" name="userEmail" value="${user.userEmail}" />
                  <input type="hidden" name="role" value="${user.role}" />
                  <input type="hidden" name="totalPoint" value="${user.totalPoint}" />
                  <input type="hidden" name="useStatus" value="2" /> <input
                     type="hidden" name="useDetail" value="8" />
               </div>
            </div>

         </div>
</div>
         <!--  화면구성 div End /////////////////////////////////////-->
  		 </form>
</body>
</html>