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


<!--  ///////////////////////// JavaScript ////////////////////////// -->
<title>상품 목록조회</title>


  
<style>
	body{
		font-weight : 600;
		background-image: url('/resources/images/uploadFiles/PointBackGround.gif');
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
	width: 30px;
	height: 40px;
	}
	#cardpay{
	width: 50px;
	height: 30px;
	}
	#kakaopay{
	width: 50px;
	height: 30px;
	}
	
	.container{
	padding-top: 70px;
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


<script type="text/javascript">


/////////////////구매 유효성 검사////////////
$(function() {
      $("#buycash").on("click", function() {
    	  alert("계좌이체 하셨습니다.");
         fncAddPointPurchasePoint();
      });
   });
   function fncAddPointPurchasePoint() {
      var productNo = $('input[name="productNo"]:checked').val();
      var payOpt = $('input[name="payOpt"]:checked').val();
      var useStatus = $("input[name='useStatus']").val();
      var useDetail = $("input[name='useDetail']").val();
	  var point = $("input[name='point']").val();
      
      alert(payOpt + " : payOpt   " + productNo + ": productNo   "
            + useStatus + ":useStatus  " + useDetail + ":useDetail  "+point+":point" );
      $("form").attr("method", "POST").attr("action",
            "/point/addPointPurchaseProduct").submit()
   }
   

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
         $("#buykakao").on("click", function() {
            fncKakaoPay();
         });
      });
   
  function fncKakaoPay(){
   var productName = $('input[name="productName"]').val();
   var productPrice = $('input[name="point"]').val();
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
      $("#buycard").on("click", function() {
         fncPayco();
      });
   });
  
  function fncPayco(){
      var productName = $('input[name="productName"]').val();
      var productPrice = $('input[name="point"]').val();
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
            self.location = "/product/addProductPointView"
         });
   });    
//============= productName 에 쿠폰 상품 상세 정보(관리자 모드)  Event  처리(Click) =============
    //==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
   $(function() { 
	   $( ".images" ).on("click" , function() {
		   <c:if test="${sessionScope.user.role == '2'}">
	    	 self.location ="/product/getProductPoint?productNo="+$(this).attr("productNo");
	       </c:if>
	     });
   });
//============= productName 에 상품정보보기 Ajax이용 (관리자용)  Event  처리(Click) =============
   $(function(){
      $( ".productName" ).on("click", function() {
         var productNo = $(this).data("value");
//         var productNo = $(this).next.val();

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
                             `
                             <div class="detail" style="border-radius: 10% / 50%;margin-top: 30px;">
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
<jsp:include page="/layout/toolbar.jsp" />

</head>

<body>
<!-- ToolBar Start /////////////////////////////////////-->
 <!-- ToolBar End /////////////////////////////////////-->
   <form class="form-horizontal">
   		<br>
   		<br>
   		<br>
   		<br>
   		<br>
   		<br>
   		<br>
   		<br>
      <!--  화면구성 div Start /////////////////////////////////////-->
      <div class="container">

      <!-- 상품 이미지 위쪽 /////////////////////////////////////-->
         <div class="row" style="height: 150px; width: 1400px;">
         
         	<div class="col-md-3"></div>
	            <div class="col-md-4" align="center" >
	               <h1 style="color: #ddb542;">포인트 상품 전체목록</h1>
	            </div>
			<div class="col-md-5"></div>
			<br>
			<br>
			 <div class="col-md-5" ></div> 
              <div class="col-md-3" align="center"> 
              <br>
	            	<c:if test="${sessionScope.user.role == '2'}">
	               		<button type="button" class="btn btn-primary addP">신규 포인트 상품 등록</button>
	              	</c:if> 
           	  </div>
			 <div class="col-md-4"></div> 
            
         </div>
        
      <!-- 상품 이미지 시작 /////////////////////////////////////-->
      <div class="row">
         <c:set var="i" value="0" />
         <c:forEach var="product" items="${list}">
            <c:set var="i" value="${ i+1 }" />            
               <div class="col-md-3">
               <input type="radio" name="productNo" class="productName" id="productName" data-value="${ product.productNo }" title="Click : 상품정보 확인" value="${product.productNo}"  />
               
                  <div>
                  		
                     <img src="/resources/images/uploadFiles/${product.productImg}" width="250" height="250" class="images" productNo="${ product.productNo }"
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
               <h4 style="color: #ddb542;">결제방식 :&nbsp
               <input type="radio" id="pay" name="payOpt" value="1" onclick="showDiv(this);">계좌이체 &nbsp&nbsp&nbsp&nbsp
               <input type="radio" id="card" name="payOpt" value="2" onclick="showDiv(this);" /> 카드결제&nbsp&nbsp<img src="/resources/images/uploadFiles/card.png" id="cardpay"> &nbsp&nbsp&nbsp&nbsp
               <input type="radio" id="kakao" name="payOpt" value="3" onclick="showDiv(this);" /> 카카오페이&nbsp&nbsp<img src="/resources/images/uploadFiles/kakaopay.jpg" id="kakaopay"></button>
            </h4>
              
            </fieldset>
             <br>
            <div id="payBox" class="box" style="border-radius: 25% 10%;padding-left: 60px;display: block;padding-top: 20px;">
            결제방식 : 계좌이체 &nbsp &nbsp<img src="/resources/images/uploadFiles/buy.png" class="cashbutton" id="buycash">
            <br/>
            <br/>
      
               은행명 : 농협
            <br/>
            <br/>
               계좌번호 : 901055-56-047-268
         <br/>
         <br/>
               받는사람 : 유병문
         <br/>
        

            </div>
            
            <div id="cardBox" class="box" style="border-radius: 25% 10%; padding-left: 60px;" >
            결제방식 : 카드결제 &nbsp &nbsp <img src="/resources/images/uploadFiles/buy.png" class="cashbutton" id="buycard">
			<br/>
            결제 및 계좌 안내 시 상호명은 GodLife로 표기되니 참고 부탁드립니다.<br><br>
            
            이용약관 및 개인정보 제3자 제공사항에 대해 확인하였으며 결제에 동의합니다.<br><br>
            
            개인정보 수집/이용 등의 <button type="button" data-toggle="modal" data-target=".mymodal" id="openmodal">보기</button><br>
            개인정보 제3자 제공 동의 <button type="button" data-toggle="modal" data-target=".mymodal1" id="openmodal">보기</button><br>
            결제대행 서비스 이용약관 (주)아임포트
          <br/>
            </div>
            <div id="kakaoBox" class="box" style="border-radius: 25% 10%; padding-left: 60px;" >
            결제방식 : 간편결제 &nbsp &nbsp <img src="/resources/images/uploadFiles/buy.png" class="cashbutton" id="buykakao">
            <br/>
            결제 및 계좌 안내 시 상호명은 GodLife로 표기되니 참고 부탁드립니다.<br><br>
            
            이용약관 및 개인정보 제3자 제공사항에 대해 확인하였으며 결제에 동의합니다.<br><br>
            
            개인정보 수집/이용 등의 <button type="button" data-toggle="modal" data-target=".mymodal" id="openmodal">보기</button><br>
            개인정보 제3자 제공 동의 <button type="button" data-toggle="modal" data-target=".mymodal1" id="openmodal">보기</button><br>
            결제대행 서비스 이용약관 (주)아임포트
          <br/>
        </div>
	</div>
	<div class="col-md-6">
	<hr/>
	<h4 style="color: #ddb542;">상품 정보</h4> 
  		<div id="ajax"></div>
 		</div>

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
        
         </div>
          

         <!--  화면구성 div End /////////////////////////////////////-->

<div class="modal fade mymodal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
		개인정보 처리방침<br><br>
		1.개인정보 수집 이용 보유 파기<br>
		GodLife는 다음과 같이 개인정보를 수집∙이용하고 있습니다. 개인정보가 필요한 시점에 최소한의 정보만을 수집하며, 고지한 범위 내에서만 사용합니다. 또한 사전 동의 없이 고지한 범위를 초과하여 이용하거나 외부에 공개하지 않습니다.
		<br><br>
		1.1개인정보 수집 방법
		<ol>
		<li>특정 상품 주문 시 직접 입력 또는 취소∙환불 처리 시점에 유선으로 수집</li>
		<li>마케팅 및 분석, 프로모션 진행 시 희망자에 한해 직접 입력 또는 제공 항목 선택 제출</li>
		<li>TV 서비스 이용, 선물하기 서비스 이용 등 특정 기능 이용 시점에 직접 입력</li></ol>
		
		2. 개인정보 제3자 제공<br>
		주문과 결제가 이루어진 경우, 상담 및 배송 등 원활한 거래 이행을 위하여 관련된 정보를 필요한 범위 내에서 판매자(제3자)에게 전달합니다. 주문 시에만 판매자(제3자)에게 개인정보가 제공되며 주문 시 안내해드립니다. 판매자(제3자)에게 개인정보 제공을 거부하실 수 있으나, 이 경우 서비스 이용이 제한될 수 있습니다.
		<br>
		</div>
	</div>
</div>
<div class="modal fade mymodal1" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
		<div><p>GodLife는 거래 당사자간 원활한 의사소통 및 상담 등 거래이행을 위해 최소한의 범위내에서 개인정보를 입점업체 판매자 및 배송 업체에 아래와 같이 제공합니다.<br>"개인정보 제3자 제공 동의"를 체크하시면 아래와 같은 내용에 동의한 것으로 간주합니다.</p>
		<ul>
		<li>개인정보를 제공 받는자 : (주)아임포트</li>
		<li>제공되는 개인정보 항목
			<ul>
			<li>구매자 정보: 성명, ID, 휴대전화 번호, 메일주소, 상품 구매정보</li>
			<li>수령자 정보: 성명, 휴대전화 번호, 배송지 주소</li>
			</ul>
		</li>
		<li>개인정보를 제공받는 자의 이용 목적 : 판매자와 구매자 간 원활한 거래의 진행, 고객 상담 및 불만처리</li>
		<li>개인정보를 제공받는 자의 개인정보 보유 및 이용 기간 : 개인정보 수집 및 이용 목적 달성 시까지 보관합니다.</li>
		<li>동의 거부 시 불이익 : 동의를 거부할 수 있으며 개인정보 제공에 동의하지 않으시는 경우 거래가 제한됩니다.</li></ul></div>
		</div>
	</div>
</div>


   </form>
</body>
</html>