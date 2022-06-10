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
</script>
</head>

<body>
   <form class="form-horizontal">
      <!-- ToolBar Start /////////////////////////////////////-->
      <jsp:include page="/layout/toolbar.jsp" />
      <!-- ToolBar End /////////////////////////////////////-->

      <!--  화면구성 div Start /////////////////////////////////////-->
      <div class="container">

         <div class="page-header text-info">
            <c:set var="name" value="${user.role}" />
            <c:if test="${role =='admin' }">
               <h3>포인트상품 전체목록</h3>
            </c:if>

            <c:if test="${role =='user' }">
               <h3>상품 목록조회</h3>
            </c:if>
         </div>
         <!-- table 위쪽 검색 Start /////////////////////////////////////-->
         <div class="row">
            <div class="col-md-6 text-left">
               <h3 class="text-primary font-weight-bold">포인트상품 전체목록</h3>
            </div>
            <div class="col-md-6 text-right">
               <c:set var="name" value="${user.role}" />
               <c:if test="${role =='admin' }">
                  <button type="button" class="btn btn-primary addP">신규 포인트
                     상품 등록</button>
               </c:if>
            </div>
            <div class="col-md-6 text-right"></div>
         </div>


         <c:set var="i" value="0" />
         <c:forEach var="product" items="${list}">
            <c:set var="i" value="${ i+1 }" />
            <div class="col-xs-6 col-md-3">
               <input type="radio" name="productNo" value="${product.productNo}"
                  checked /> <img src="/images/uploadFiles/${product.productImg }"
                  onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'">
               <div>${ product.productName }</div>
               <div>${ product.productPrice }원</div>
               <div>${ product.productDetail }</div>
            </div>
         </c:forEach>


         <!--  table End /////////////////////////////////////-->

         <div class="col-xs-6 col-md-12">
            <fieldset>
               <hr />
               결제방식 : 
               <input type="radio" id="pay" name="payOpt" value="1" onclick="showDiv(this);">계좌이체 
               <input type="radio" id="card" name="payOpt" value="2" onclick="showDiv(this);" /> 카드결제 
               <input type="radio" id="kakao" name="payOpt" value="3" onclick="showDiv(this);" /> 카카오 페이
               &nbsp&nbsp
               <button type="button" class="btn btn-default">구매</button>
            </fieldset>

            <hr>

            <div id="payBox" class="box" >
               은행명 : 농협
               <hr />
               계좌번호 : 901055-56-047-268
               <hr />
               받는사람 : 유병문
               <hr />
            </div>
            
            <div id="cardBox" class="box" >
            카드결제
            <hr/>
            </div>
            <div id="kakaoBox" class="box" >
            간편결제
            <hr/>
            </div>
            <!-- label Tag 사용 / 미사용의 차이점 : 이름 3을 Click 해보면... -->
            <div class="form-group">
               <div class="col-sm-offset-2 col-sm-10">
                  <input type="hidden" name="userEmail" value="${user.userEmail}" />
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