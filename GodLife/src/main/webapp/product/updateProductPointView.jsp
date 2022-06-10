<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>    

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
   
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
   
   <!--  ///////////////////////// CSS ////////////////////////// -->
   <style>
   body {
      padding-top: 50px;
   }
   
   </style>
    

    
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
<script type="text/javascript">
      
   function fncUpdateProduct() {

      $("form").attr("method", "POST").attr("action",
            "/product/updateProductPoint").submit();
   }

   function fncDeleteProduct() {

	      $("form").attr("method", "POST").attr("action",
	            "/product/deleteProductPoint").submit();
	   }   
   

   //==> 추가된부분 : "수정"  Event 연결
   $(function() {
      $("button.btn.btn-primary.update").on("click", function() {
         fncUpdateProduct();
      });
   });

   $(function() {
	  $("button.btn.btn-primary.delete").on("click", function() {
	     fncDeleteProduct();
	   });
	});   
   
   
   
   //==> 추가된부분 : "취소"  Event 연결 및 처리
   $(function() {
      $("a[href='#' ]").on("click", function() {
         $("form")[0].reset();
      });
   });
   
   $(function (){
      /*    ：startView: 2,
       maxViewMode: 1,
       minViewMode:1,
            ，           ，0  ，1  ，2  ，
                    ，
                  。          。
      */
        //     startView: 4, minView: 4, format: 'yyyy', 
      $('.form_date').datetimepicker({
      format: 'yyyy', 
      startView:4, 
      minView:4,
      language: 'zh-CN' ,
      forceParse: false, 
      autoclose:true,
        pickerPosition: "bottom-left" 
        });
       //      
        $('.form_date').datetimepicker({
        language: 'zh-CN',
        minView: 'month', 
        format: 'yyyy-mm-dd',
        autoclose: true,
       startView: 2,
        showMeridian: 1,
        pickerPosition: "bottom-left"
       });
      });
</script>
   
</head>

<body>

   <!-- ToolBar Start /////////////////////////////////////-->
   <jsp:include page="/layout/toolbar.jsp" />
   <!-- ToolBar End /////////////////////////////////////-->

   <!--  화면구성 div Start /////////////////////////////////////-->
   <div class="container">

      <div class="page-header text-center">
         <h3 class=" text-info">포인트 상품정보수정</h3>
         <h5 class="text-muted">
            포인트 상품을 <strong class="text-danger">최신정보로 관리</strong>해 주세요.
         </h5>
      </div>

      <!-- form Start /////////////////////////////////////-->
      <form class="form-horizontal">

         <div class="form-group">
            <label for="productNo" class="col-sm-offset-1 col-sm-3 control-label"></label>
            <div class="col-sm-4">
               <input type="hidden" class="form-control" id="productNo" name="productNo" 
                  value="${product.productNo}">
            </div>
         </div>

         <div class="form-group">
            <label for="productName" class="col-sm-offset-1 col-sm-3 control-label">상 품 명</label>
            <div class="col-sm-4">
               <input type="text" class="form-control" id="productName" name="productName" 
                  value="${product.productName}">
            </div>
         </div>

         <div class="form-group">
            <label for="productPrice" class="col-sm-offset-1 col-sm-3 control-label">가격</label>
            <div class="col-sm-4">
               <input type="number" class="form-control" id="price" name="price"
                  value="${product.productPrice}">
            </div>
         </div>

         <div class="form-group">
            <label for="productDetail" class="col-sm-offset-1 col-sm-3 control-label">상품상세정보</label>
            <div class="col-sm-4">
               <input type="text" class="form-control" id="prodDetail" name="prodDetail" 
                  value="${product.productDetail}">
            </div>
         </div>

         <div class="form-group">
            <label for="productImg" class="col-sm-offset-1 col-sm-3 control-label">상품이미지</label>
            <div class="col-sm-4">
               <input type="text" class="form-control" id="productImg"
                  name="productImg" value="${product.productImg}">
            </div>
         </div>

		 <div class="form-group">
            <label for="status" class="col-sm-offset-1 col-sm-3 control-label"></label>
            <div class="col-sm-4">
               <input type="hidden" class="form-control" id="status"
                  name="status" value="3">
            </div>
         </div>


         <div class="form-group">
            <div class="col-sm-offset-4  col-sm-4 text-center">
               <button type="button" class="btn btn-primary update">수 &nbsp;정</button>
               <button type="button" class="btn btn-primary delete">삭 &nbsp;제</button>
               <a class="btn btn-primary btn" href="#" role="button">되돌리기</a>
            </div>
         </div>
      </form>
      <!-- form Start /////////////////////////////////////-->

   </div>
   <!--  화면구성 div Start /////////////////////////////////////-->

</body>

</html>