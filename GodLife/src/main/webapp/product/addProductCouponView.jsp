<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>

<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>상품등록</title>
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
	}	
	       
        body > div.container{
        	border: 3px;
            margin-top: 10px;
        }

		textarea {
		    width: 100%;
		    height: 6.25em;
		    border: none;
		    resize: none;
		    
		  }           
        
        
    </style>
    
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">


//=====기존Code 주석 처리 후  jQuery 변경 ======//
function fncAddProductCoupon(){
	$("form").attr("method", "POST").attr("action", "/product/addProductCoupon").submit();
}

//function fncAddProduct(){

	//document.detailForm.action='/product/addProduct';
	//document.detailForm.submit();
	
	//파일 업로드를 위해 아래와 같이 씀///아직 미구현
	//$("form").attr("method", "POST").attr("enctype", "multipart/form-data").attr("action", "/product/addProduct").submit();
	//$("form").attr("method", "POST").attr("action", "/product/addProductCoupon").submit();

	
//}


   function fncGetProductCouponList() {

      $("form").attr("method", "GET").attr("action",
            "/product/getProductCouponList").submit();
   }


//============= "취소"  Event 처리 및  연결 =============
$(function() {
	//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
	$("a[href='#' ]").on("click" , function() {
		$("form")[0].reset();
	});
});	
	
	///////////////////////////////////////////////////////////////
	//==> 추가된부분 : "등록"  Event 처리 및  연결
	$(function(){
		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		//==> 1 과 3 방법 조합 : $("tagName.className:filter함수") 사용함.	
		 $( "button.btn.btn-primary.addP" ).on("click" , function() {
				//Debug..
				//alert(  $( "td.ct_btn01:contains('등록')" ).html() );
				fncAddProductCoupon();
		});
	});	
	
///////////////////나중에 밑에 부분 바꾸고 여기 등록과 취소 부분도 바꿔야 함//////////////////////////	
	//============= 뒤로 돌아가기 Event  처리 =============	

	   $(function() {
			  $("button.btn.btn-primary.getList").on("click", function() {
			     fncGetProductCouponList();
			   });
			});    



	</script>
</head>

<body>



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
	
		<h2 class="bg-dark text-center" style="color:#000000; ">쿠폰 상품등록</h2>
		
		<!-- form Start /////////////////////////////////////-->
		<form class="form-horizontal" encType="multipart/form-data" style="border-color: black;">

		  <div class="form-group">
		    <label for="productImg" class="col-sm-offset-1 col-sm-3 control-label">상품이미지</label>
		    <div class="col-sm-4">
		    	<img  productNo="${ product.productNo }" width="400" height="400" src="/resources/images/uploadFiles/${product.productImg}" alt="..." 
					onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'"/>
					<input type="file" class="form-control" id="imageUpload" multiple="multiple" name="imageUpload">
			<hr/>
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
               <input type="number" class="form-control" id="productPrice" name="productPrice"
                  value="${product.productPrice}">
            </div>
         </div>
		  
         <div class="form-group">
            <label for="productDetail" class="col-sm-offset-1 col-sm-3 control-label">상품상세정보</label>
            <div class="col-sm-4">
               <textarea cols="50" rows="10" class="form-control" id="productDetail" name="productDetail" 
                  value="${product.productDetail}">${product.productDetail}</textarea>
            </div>
         </div>
		  

		  <div class="form-group">
		    <label for="status" class="col-sm-offset-1 col-sm-3 control-label"></label>
		    <div class="col-sm-4">
		    <input type="hidden" class="form-control" id="status" name="status" value="1">
		    </div>
		  </div>

		
		  <div class="form-group">
		    <div class="col-sm-offset-4  col-sm-4 text-center">
		      <button type="button" class="btn btn-primary addP"  >등록 완료</button>
			  <a class="btn btn-primary reset" href="#" role="button">리셋</a>
			  <button type="button" class="btn btn-primary getList">리스트로 돌아가기</button>
		    </div>
		  </div>
		</form>
		<!-- form Start /////////////////////////////////////-->
		
 	</div>
	<!--  화면구성 div end /////////////////////////////////////-->
	
</body>

</html>
		
		
							
					
					
					
		