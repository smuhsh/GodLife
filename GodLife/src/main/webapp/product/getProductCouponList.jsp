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
<title>?????? ????????????</title>


<script type="text/javascript">

/////////////////?????? ????????? ??????////////////
   function fncAddPointPurchasePoint() {
      var productNo = $('input[name="productNo"]:checked').val();
      var useStatus = $("input[name='useStatus']").val();
      var useDetail = $("input[name='useDetail']").val();
      var totalPoint = $("input[name='totalPoint']").val();
      var point = $("input[name='point']").val();
      alert("totalPoint" + totalPoint);
      if (totalPoint < point ){
         alert("????????? ???????????? ???????????????");
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
/////////////////////?????? ????????? ?????? ?????? Event  ??????///////////////////////////
	  $(function() {
		//==> DOM Object GET 3?????? ?????? ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		 $( "button.btn.btn-primary.addP" ).on("click" , function() {
				self.location = "/product/addProductCouponView?productNo=${product.productNo}"
			});
	});       
////////////////////////////////////////////////

//============= productName ??? ?????? ?????? ?????? ??????(????????? ??????)  Event  ??????(Click) =============
	 //==> DOM Object GET 3?????? ?????? ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
	$(function() {
		    var role=$("input[name='role']").val();
		    if(role=='2'){
	     $( ".images" ).on("click" , function() {
	    	 self.location ="/product/getProductCoupon?productNo="+$(this).attr("productNo");
	     });
		    }
	});
	     
//============= productName ??? ?????????????????? Ajax?????? (?????? ?????????)  Event  ??????(Click) =============
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
   
                                  <div id="first">?????? ??? :&nbsp \${JSONData.productName} </div>
                                  <br>
             					  
                                  <div id="third">??????:&nbsp \${JSONData.productPrice}??? </div>
                                  <br>
                                  
                                  <div id="second">?????? ????????????: <br/> \${JSONData.productDetail} </div>
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
		 //==> prodNo LINK Event End User ?????? ????????? ????????? 
	});
</script>
</head>

<body>
   <form class="form-horizontal">
      <!-- ToolBar Start /////////////////////////////////////-->
      <jsp:include page="/layout/toolbar.jsp" />
      <!-- ToolBar End /////////////////////////////////////-->

      <!--  ???????????? div Start /////////////////////////////////////-->
      <br><br>
      <br>
      <br>
      <br>
      <br>
      <br>
      <br>
      <br>
      <div class="container">

         <!-- ?????? ????????? ?????? /////////////////////////////////////-->
         <div class="row" style="height: 150px;">
            <div class="col-md-7 text-right" >
               <h1 class="text-primary" style="color:#f0f0f0;">???????????? ????????????</h1>
         	</div>
			<div class="col-md-6"></div>
       	    <div class="col-md-6 text-right"> 
				<br/>
				<br>
			    <br>
			    <c:if test="${sessionScope.user.role == '2'}">
            		<button type="button" class="btn btn-primary addP">?????? ?????? ?????? ??????</button>
            	</c:if>
            	<br>
            	<br>  
	        </div>
         </div>
		<!-- ?????? ????????? ?????? /////////////////////////////////////-->
		<div class="row">
         <c:set var="i" value="0" />
         <c:forEach var="product" items="${list}">
            <c:set var="i" value="${ i+1 }" />            
               <div class="col-md-6">
               <input type="radio" name="productNo" class="productName" id="productName" data-value="${ product.productNo }" title="Click : ???????????? ??????" value="${product.productNo}"  />
               
                  <div>
                  		
                     <img  src="/resources/images/uploadFiles/${product.productImg}"  class="images" productNo="${ product.productNo }"
                        onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'"><br/>
                      
                    <!--   <div class="productName" id="productName" data-value="${ product.productNo }" title="Click : ???????????? ??????">${ product.productName }</div> -->
                     <div></div>
  
                    <!-- <div>${ product.productPrice }???</div> -->
                     <!-- ??????????????? ???????????? ???????????? ????????? ??????  -->
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
               <input type="radio" id="pay" name="payOpt" value="1" onclick="showDiv(this);">&nbsp ?????? ??????
            </h4>
              
            </fieldset>
             <br>
            <div id="payBox" class="box" style="border-radius: 25% 10%;padding-left: 60px;display: block;padding-bottom: 5px;padding-top: 0px;height: 225px;" >
           <br>
		?????? ?????? ????????????<br>
		<br>
		<ol>
		<li>????????? ?????? ???????????? ???????????? ?????????. ?????? ???????????? ?????? ??? ?????? ???????????? ????????? ????????? ????????? ?????? ????????????.</li>
		<li>????????? ?????? ????????? ??????????????? - ????????? ?????? ???????????? ??? ????????????.</li>
		<li>???????????? ?????? ?????? : ???????????? ???????????? ???????????? ???????????? ?????? ??? ??? ????????????.
		<li>???????????? ?????? ?????? : ????????????????????? ???????????? ??????????????? ??????????????? ?????? ??? ???????????????.
		</ol>
            </div>  
	</div>
	<div class="col-md-6">
	<hr/>
	<h4 style="color:#f0f0f0;padding-top: 20px;">?????? ??????</h4> 
  		<div id="ajax"></div>
 		</div>
            <!-- label Tag ?????? / ???????????? ????????? : ?????? 3??? Click ?????????... -->
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
         <!--  ???????????? div End /////////////////////////////////////-->
  		 </form>
</body>
</html>