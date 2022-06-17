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
@font-face {
          font-family: 'oneMobile';
          src: url('/resources/css/font/ONE Mobile Title.ttf') format('truetype');
      }


body {
   font-weight: bold; 
   font-family: 'oneMobile';
}

#productName{
width: 50px;
height: 30px;
}
.images{
width:250px;
height: 250px;
}
.container{
padding-top: 150px;
text-align: center;
align-items:center;
}



</style>

<!--  ///////////////////////// JavaScript ////////////////////////// -->
<meta charset="EUC-KR">
<title>상품 목록조회</title>

<link rel="stylesheet" href="/css/admin.css" type="text/css">

<!-- CDN(Content Delivery Network) 호스트 사용 -->
<script src="http://code.jquery.com/jquery-2.1.4.min.js"></script>

<script type="text/javascript">


</script>
</head>

<body>
   <form class="form-horizontal">
      <!-- ToolBar Start /////////////////////////////////////-->
      <jsp:include page="/layout/toolbar.jsp" />
      <!-- ToolBar End /////////////////////////////////////-->

      <!--  화면구성 div Start /////////////////////////////////////-->
      <br><br>
      <div class="container">

         <!-- 상품 이미지 위쪽 /////////////////////////////////////-->
         <div class="row" style="height: 150px;">
            <div >
               <h2 >쿠폰함</h2>
         	</div>

       	    <div> 
				<br/>
      
	        </div>
         </div>
		<!-- 상품 이미지 시작 /////////////////////////////////////-->
		<div class="row">
         <c:set var="i" value="0" />
         <c:forEach var="product" items="${list}">
            <c:set var="i" value="${ i+1 }" />            
               <div class="col-md-6">
                  <div id="product">
                  	<h2>${product.productName }</h2>
                     <img  src="../images/uploadFiles/${product.productImg}"  class="images" productNo="${ product.productNo }"
                        onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'"><br/>
                      
               </div>
            </div>
         </c:forEach>
      </div>
      <div class="row">
      <div class="col-md-6">
      <h3>${user.redCouponCount} 장 보유중</h3>
      </div>
            <div class="col-md-6">
      <h3>${user.certiCouponCount} 장 보유중</h3>
      </div>
      </div>
 
        
</div>

 </form>
</body>
</html>