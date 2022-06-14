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


<!--  ///////////////////////// JavaScript ////////////////////////// -->
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<!-- CDN(Content Delivery Network) 호스트 사용 -->
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>
<!-- iamport.payment.js -->
  <script type="text/javascript" src="https://cdn.iamport.kr/js/iamport.payment-1.2.0.js"></script>
  
<style>
@font-face {
		    font-family: 'oneMobile';
		    src: url('/resources/css/font/ONE Mobile Title.ttf') format('truetype');
		}


body {
   padding-top: 50px;
   background-color: #708090 ;
   font-weight: bold; 
   font-family: 'oneMobile';
}

fieldset {
   width: 400px;
   text-align: center;
   backgrond-color: white;
   font-family: 'oneMobile';
}

div.box {
   width: 500px;
   height: 50px;
   box-align:center middle;
   margin: 10px;
   padding: 10px;
   display: none;
   background-color: #ffcc00;
   font-family: 'oneMobile';
}

.detail {
   color : white ;
   margin : 20px;
   width: 380px;
   height: auto;
   padding-top: 1px;
   background-color: #070719 ;
   font-weight: bold; 
   font-family:impact;
   font-family: 'oneMobile';
}


</style>
<!--  ///////////////////////// JavaScript ////////////////////////// -->
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
   };
   
   $(function() {
	      $(".kakao").on("click", function() {
	    	  fncKakaoPay();
	      });
	   });
   
  function fncKakaoPay(){
	var productName = $('input[name="productName"]').val();
	var productPrice = $('input[name="productPrice"]').val();
	var userEmail = $('input[name="userEmail"]').val();
	var nick = $('input[name="nick"]').val();
	var phone = $('input[name="phone"]').val();
	  
	var IMP = window.IMP;
	IMP.init('imp68438670');
	IMP.request_pay({
	    pg : 'kakaopay',
	    pay_method : 'card', //생략 가능
	    merchant_uid: 'merchant_'+new Date().getTime(), // 상점에서 관리하는 주문 번호
	    name : productName,
	    amount : productPrice,
	    buyer_email : userEmail,
	    buyer_name : nick,
	    buyer_tel : phone
	    
	}, function(rsp) { // callback 로직
		if(rsp.success){
			   alert("완료-> imp_uid: "+rsp.imp_uid+" / merchant_uid(orderkey): "+rsp.merchant_uid);
			   fncAddPointPurchasePoint();
		   } else{
			   alert("실패 : 코드("+rsp.error_code+") / 메세지(" + rsp.error_msg +")");
		   }
	});
  }
  
  $(function() {
      $(".payco").on("click", function() {
    	  fncPayco();
      });
   });
  
  function fncPayco(){
		var productName = $('input[name="productName"]').val();
		var productPrice = $('input[name="productPrice"]').val();
		var userEmail = $('input[name="userEmail"]').val();
		var nick = $('input[name="nick"]').val();
		var phone = $('input[name="phone"]').val();
		  
		var IMP = window.IMP;
		IMP.init('imp68438670');
		IMP.request_pay({
		    pg : 'payco',
		    pay_method : 'card', //생략 가능
		    merchant_uid: 'merchant_'+new Date().getTime(), // 상점에서 관리하는 주문 번호
		    name : productName,
		    amount : productPrice,
		    buyer_email : userEmail,
		    buyer_name : nick,
		    buyer_tel : phone
		    
		}, function(rsp) { // callback 로직
			if(rsp.success){
				   alert("완료-> imp_uid: "+rsp.imp_uid+" / merchant_uid(orderkey): "+rsp.merchant_uid);
				   fncAddPointPurchasePoint();
			   } else{
				   alert("실패 : 코드("+rsp.error_code+") / 메세지(" + rsp.error_msg +")");
			   }
		});
	  }

/////////////////////신규 쿠폰 상품 등록 Event  처리///////////////////////////
	 $(function() {
		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		 $( "button.btn.btn-primary.addP" ).on("click" , function() {
				self.location = "/product/addProductPointView?productNo=${product.productNo}"
			});
	});    
//============= productName 에 쿠폰 상품 상세 정보(관리자 모드)  Event  처리(Click) =============
	 //==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
	$(function() { 
	     $( ".images" ).on("click" , function() {
	    	 self.location ="/product/getProductPoint?productNo="+$(this).attr("productNo");
	     });
	});
//============= productName 에 상품정보보기 Ajax이용 (일반 회원용)  Event  처리(Click) =============
	$(function(){
		$( ".productName" ).on("click", function() {
			var productNo = $(this).data("value");
//			var productNo = $(this).next.val();

			 $.ajax( 
	                 {
	                    url : "/product/json/getProductPoint/"+productNo,
	                    method : "GET",
	                    dataType : "json",
	                    headers : {
	                       "Accept" : "application/json",
	                       "Content-Type" : "application/json"
	                    },

	                    success : function(JSONData , status) {
	                    	
	                       const displayDetail = 
	                    	   `<div class="row">
		                         	<div class="detail">상품 명 :&nbsp \${JSONData.productName} </div>
		                         	<div class="detail">상품 상세정보: <br/> \${JSONData.productDetail} </div>
	                     		</div>`
	                       $("div.detail").remove();
	                       $( "#"+JSONData.productNo+"" ).append(displayDetail);
	                       console.log(JSONData , status);
	                 }
	              }); 
	        });
		//=========================================================//
		 //==> prodNo LINK Event End User 에게 보일수 있도록 
	    $( "div#productName" ).css("color" , "red");
	    $("div#productName").css("font-size", "20px");
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
         <div class="row" style="height: 150px; width: 1400px;">
         
         
            <div class="col-md-6 text-right">
               <h1 class="text-primary font-weight-bold" style="color:#000000; font-weight: bold; font-family: 'oneMobile';">포인트 상품 전체목록</h1>
         	</div>

       	    <div class="col-md-6 text-right"> 
				<br/>
            	<button type="button" class="btn btn-primary addP">신규 포인트 상품 등록</button> 
	        </div>

	         
         </div>
         <div class="col-md-8 text-left"> 
         	* Radio박스를 클릭하면 상세 정보를 조회 할 수 있습니다.<br>
         	* RadioBox를 클릭 후, 구매를 눌러주시면 구매가 가능 합니다.<br>
         	* 구현 예정 기능<br>
         	1) serach<br>
         	2) RestController 써서 Get으로 상세 정보가 뜨면 안없어짐.<br>
         	   : 없어지는 버튼 하나 만들자<br>
         	3) Ajax 글자 꾸미기 <br>
         	4) Admin 일때, 유저 일때 보이는게 다르게 해야함 <br>
         	   : 관리자 : 이미지 클릭으로 상세 정보 창으로 이동 => 수정, 삭제 가능 <br>
         	   : 유저 : radioBox 체크로 상품 상세 정보 및, 상품 구매 가능
         	   
         </div>
		<!-- 상품 이미지 시작 /////////////////////////////////////-->
		<div class="row">
         <c:set var="i" value="0" />
         <c:forEach var="product" items="${list}">
            <c:set var="i" value="${ i+1 }" />            
	            <div class="col-xs-6 col-md-3" style="height: auto; width: 472px;">
	            <input type="radio" name="radio" class="productName" id="productName" data-value="${ product.productNo }" title="Click : 상품정보 확인" value="${product.productNo}" checked />
	            
		            <div style="height: auto; width: auto; border: 1px solid black; margin:20px; background-color: black;">
		               <img productNo="${ product.productNo }" width="400" height="400" class="images" src="../images/uploadFiles/${product.productImg }"
		                  onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'"><br/>
		                
		              <!--   <div class="productName" id="productName" data-value="${ product.productNo }" title="Click : 상품정보 확인">${ product.productName }</div> -->
		               <div></div>
		               
		               <i id= "${product.productNo}" style="font-size:20px; "></i>
		               <input type="hidden" value="${product.productNo}">
		              <!-- <div>${ product.productPrice }원</div> -->
		               <!-- 상세내용은 상세정보 창에서만 보이게 하자  -->
		               <!--  <div>${ product.productDetail }</div>-->
					</div>
            </div>
         </c:forEach>
		</div>
			<button type="button" class="btn btn-default">구매</button>

         <!--  table End /////////////////////////////////////-->

         <div class="col-xs-6 col-md-12">
            <fieldset>
               <hr />
               결제방식 : 
               <input type="radio" id="pay" name="payOpt" value="1" onclick="showDiv(this);">계좌이체 
               <input type="radio" id="card" name="payOpt" value="2" onclick="showDiv(this);" /> 카드결제 
               <input type="radio" id="kakao" name="payOpt" value="3" onclick="showDiv(this);" /> 카카오 페이
               &nbsp&nbsp
               
            </fieldset>

            <hr>

            <div id="payBox" class="box" >
               은행명 : 농협
               <hr />
               계좌번호 : 901055-56-047-268
               <hr />
               받는사람 : 유병문
               <hr />
               <button type="button" class="btn btn-default">구매</button>
            </div>
            
            <div id="cardBox" class="box" >
            카드결제
            <button type="button" class="payco">구매</button>
            <hr/>
            </div>
            <div id="kakaoBox" class="box" >
            간편결제
            <button type="button" class="kakao">구매</button>
            <hr/>
            </div>
            <!-- label Tag 사용 / 미사용의 차이점 : 이름 3을 Click 해보면... -->
            <div class="form-group">
               <div class="col-sm-offset-2 col-sm-10">
                  <input type="hidden" name="userEmail" value="${user.userEmail}" />
                  <input type="hidden" name="nick" value="${user.nick}" />
                  <input type="hidden" name="phone" value="${user.phone}" />
                  <input type="hidden" name="useStatus" value="1" /> <input
                     type="hidden" name="useDetail" value="1" />
               </div>
            </div>
            <hr />
         </div>

         <!--  화면구성 div End /////////////////////////////////////-->



      </div>
   </form>
</body>
</html>