<%@ page contentType="text/html; charset=euc-kr" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>

<html lang="ko">
<head>
	<meta charset="EUC-KR">
	<title>상품등록</title>


	<!-- 참조 : http://getbootstrap.com/css/   참조 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
	   body {
	   		margin: 100px;
	   }
	
       body > div.container{
        	border: 3px solid #D6CDB7;
            margin-top: 10px;
        }
    </style>
    
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">


//=====기존Code 주석 처리 후  jQuery 변경 ======//
function fncAddProduct(){

	//document.detailForm.action='/product/addProduct';
	//document.detailForm.submit();
	
	//파일 업로드를 위해 아래와 같이 씀///아직 미구현
	//$("form").attr("method", "POST").attr("enctype", "multipart/form-data").attr("action", "/product/addProduct").submit();
	$("form").attr("method", "POST").attr("action", "/product/addProductPoint").submit();

	
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
		 $( "button.btn.btn-primary" ).on("click" , function() {
				//Debug..
				//alert(  $( "td.ct_btn01:contains('등록')" ).html() );
				fncAddProduct();
		});
	});	
	
///////////////////나중에 밑에 부분 바꾸고 여기 등록과 취소 부분도 바꿔야 함//////////////////////////	

	</script>
</head>

<body>



	<!-- ToolBar Start /////////////////////////////////////-->

   	
    <jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->

	<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
	
		<h1 class="bg-primary text-center">포인트 상품등록</h1>
		
		<!-- form Start /////////////////////////////////////-->
		<form class="form-horizontal">
		  
		  <div class="form-group">
		    <label for="productName" class="col-sm-offset-1 col-sm-3 control-label">상품명</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="productName" name="productName" placeholder="상품명">
		    </div>
		  </div>

		  <div class="form-group">
		    <label for="productPrice" class="col-sm-offset-1 col-sm-3 control-label">가격</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="productPrice" name="productPrice" placeholder="상품가격"> 
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="productDetail" class="col-sm-offset-1 col-sm-3 control-label">상품상세정보</label>
		    <div class="col-sm-4">
		      <input type="text" class="form-control" id="productDetail" name="productDetail" placeholder="상품 상세정보">
		    </div>
		  </div>
		  
		  <div class="form-group">
		    <label for="productImg" class="col-sm-offset-1 col-sm-3 control-label">상품이미지</label>
		    <div class="col-sm-4">
		    <input type="text" class="form-control" id="productImg" name="productImg" placeholder="파일이미지">
		    </div>
		  </div>

		  <div class="form-group">
		    <label for="status" class="col-sm-offset-1 col-sm-3 control-label">3번 넣어라</label>
		    <div class="col-sm-4">
		    <input type="text" class="form-control" id="status" name="status" placeholder="status">
		    </div>
		  </div>

		
		  <div class="form-group">
		    <div class="col-sm-offset-4  col-sm-4 text-center">
		      <button type="button" class="btn btn-primary"  >등 &nbsp;록</button>
			  <a class="btn btn-primary btn" href="#" role="button">취&nbsp;소</a>
		    </div>
		  </div>
		</form>
		<!-- form Start /////////////////////////////////////-->
		
 	</div>
	<!--  화면구성 div end /////////////////////////////////////-->
	
</body>

</html>
		
		
							
					
					
					
		