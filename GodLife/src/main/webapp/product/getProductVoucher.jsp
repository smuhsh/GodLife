<%@ page contentType="text/html; charset=UTF-8" %>
<%@ page pageEncoding="UTF-8"%>


<!DOCTYPE html>

<html lang="ko">
	
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
@font-face {
		    font-family: 'oneMobile';
		    src: url('/resources/css/font/ONE Mobile Title.ttf') format('truetype');
		}	
		
 		body {

		    font-weight: bold; 
		    font-family: 'oneMobile';
        }
        
          
		textarea {
		    width: 100%;
		    height: 6.25em;
		    border: none;
		    resize: none;
		    font-family: 'oneMobile';
		  }        
        
        
        
     </style>
    
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">
	
		//============= 확인 Event  처리 =============	
		 $(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			 $( "button.btn.btn-primary.getList" ).on("click" , function() {
				history.go(-1);
				});
		});

		//============= 수정 Event  처리 =============	
		 $(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			 $( "button.btn.btn-primary.update" ).on("click" , function() {
					self.location = "/product/updateProductVoucherView?productNo=${product.productNo}"
				});
		});

		
		
		
		
	</script>
	
</head>

<body>

	<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->
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
	
		<div class="page-header">
	       <h3 class=" text-info" style="color:#000000; font-weight: bold; font-family:impact;">상품권 상품 상세조회</h3>
	    </div>
	    
<!-- 이미지 미리 보기 -->	
		<div class="row">
	  		<div class="col-xs-4 col-md-2 " ><strong>상품이미지</strong></div>
			<img  productNo="${ product.productNo }" width="400" height="400" src="/resources/images/uploadFiles/${product.productImg}" alt="..." 
					onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'"/>
		<hr/>
<!-- 이미지 미리 보기 -->
		<div class="row">
	  		<div class="col-xs-4 col-md-2"><strong>상품번호</strong></div>
			<div class="col-xs-8 col-md-4">${product.productNo}</div>
		</div>
		<br>
		
		<hr/>
		
		<div class="row">
	  		<div class="col-xs-4 col-md-2 "><strong>상품명</strong></div>
			<div class="col-xs-8 col-md-4">${product.productName}</div>
			
			<div class="col-xs-4 col-md-2 "><strong>상품가격</strong></div>
			<div class="col-xs-8 col-md-4">${product.productPrice}</div>
		</div>
		<br>

		
		<div class="row">
	  		<div class="col-xs-4 col-md-2 " ><strong>상품상세정보</strong></div>
			<div class="col-xs-8 col-md-4">
				<textarea cols="50" rows="10" readonly="readOnly" placeholder="Disabled" >${product.productDetail}</textarea>
			</div>
		</div>
		
		


		<div class="row">
	  		<div class="col-xs-4 col-md-2 "></div>
			<div class="col-xs-8 col-md-4"></div>
			<input type="hidden" class="form-control" id="status" name="status" value="2">
		</div>

		
		<hr/>
		
		
		<div class="form-group">
			<div class="row">
		  		<div class="col-md-12 text-center ">
		  			<button type="button" class="btn btn-primary update">수정</button>
		  			<button type="button" class="btn btn-primary getList">리스트로 돌아가기</button>
		  		</div>
			</div>		
		</div>


		
		<br/>
		
 	</div>
 	<!--  화면구성 div Start /////////////////////////////////////-->

</body>

</html>