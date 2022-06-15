<%@ page contentType="text/html; charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>

<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>상품등록</title>


	<!-- 참조 : http://getbootstrap.com/css/   참조 -->
	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
@font-face {
		    font-family: 'oneMobile';
		    src: url('/resources/css/font/ONE Mobile Title.ttf') format('truetype');
		}
	
	
 		body {
            padding-top : 50px;
            background-color: #708090 ;
		    font-weight: bold; 
		    font-family: 'oneMobile';
        }
        
        body > div.container{
        	border: 3px;
            margin-top: 10px;
            font-family: 'oneMobile';
        }

		textarea {
		    width: 100%;
		    height: 6.25em;
		    border: none;
		    resize: none;
		    font-family: 'oneMobile';
		    
		  }           

		label {
		    margin-left: 0px;
		    display: block;
		    text-align: right;
		    width: 100px;
		} 
        
        
    </style>
    
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">


	//=====FNC 모음 Start======//
	//배지 추가
	function fncAddBadge(){
		$("form").attr("method", "POST").attr("action", "/badge/addBadge").submit();
	}
	//배지 리스트로 돌아가기
   function fncGetBadgeList() {
      $("form").attr("method", "GET").attr("action",
            "/badge/getBadgeList").submit();
   }
	//=====FNC 모음 End======//

	//============= "리셋"  Event 처리 및  연결 ==========
	$(function() {
		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		$("a[href='#' ]").on("click" , function() {
			$("form")[0].reset();
		});
	});	
	//========== "등록"  Event 처리 및  연결==============
	$(function(){
		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		 $( "button.btn.btn-primary.addB" ).on("click" , function() {
				fncAddBadge();
		});
	});	
	//============= 뒤로 돌아가기 Event  처리 =============	
	$(function() {
	     $("button.btn.btn-primary.getList").on("click", function() {
			   fncGetBadgeList();
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
	<div class="container">
	
		<h2 class="bg-dark text-center" style="color:#000000; font-weight: bold; font-family: 'oneMobile';">신규 배지 등록</h2>
		
		<!-- form Start /////////////////////////////////////-->
		<form class="form-horizontal" encType="multipart/form-data" style="border-color: black;">

<!-- Form 그룹으로 묶자.  -->
		  <div class="form-group">
<!--  Status를 줘서 가능 활동, 관심사 배지 Status 구분해서 등록 (활동배지 : 0, 관심사배지 : 1 ) start // 이것을 드랍박스로 만들어 보자 -->	
			<label for="inputEmail3" class="col-sm-offset-1 col-sm-1 control-label">배지 태그</label>
			<div class="col-sm-3">
				<select class="form-control" name="status">
					<option value="0">활동 배지</option>
					<option value="1">관심사 배지</option>
				</select>
			</div>		  
<!--  Status를 줘서 가능 활동, 관심사 배지 Status 구분해서 등록 (활동배지 : 0, 관심사배지 : 1 ) end -->

<!-- 배지 이름 입력 Start -->
            <label for="badgeName" class="col-sm-offset-1 col-sm-1 control-label" >배 지 명</label>
            <div class="col-sm-4">
               <input type="text" class="form-control" id="badgeName" name="badgeName" placeholder="배지명을 입력해주세요" 
                  value="${badge.badgeName}">
            </div>
<!-- 배지 이름 입력 End -->
			  
			<br><br><br>
<!-- 배지 Detail 입력 Start -->			
            <label for="badgeDetail" class="col-sm-offset-1 col-sm-3 control-label">배지상세정보</label>
            <div class="col-sm-4">
               <textarea cols="60" rows="10" class="form-control" id="badgeDetail" name="badgeDetail" 
                  value="${badge.badgeDetail}" placeholder="배지내용을 입력해주세요">${badge.badgeDetail}</textarea>
            </div>			
<!-- 배지 Detail 입력 End -->

<!-- 배지 이미지 입력 Start -->				
			<br><br><br><br><br><br><br><br><br><br><br><br>

		    <label for="badgeImg" class="col-sm-offset-2 col-sm-2 control-label">배지이미지</label>
		    <div class="col-sm-2">
		    	<img  badgeNo="${ badge.badgeNo }" width="300" height="300" src="../images/uploadFiles/${badge.badgeImg}" alt="..." 
					onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'"/>
					<input type="file" class="form-control" id="imageUpload" multiple="multiple" name="imageUpload">
		    </div>

<!-- 배지 이미지 입력 End -->

<!-- 배지 등급 이미지 입력 Start -->
			<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>


			    <label for="badgeImg1" class="col-sm-offset-2 col-sm-1 control-label" style="margin-left: 50px;">배지 동 등급 이미지</label>
			    <div class="col-sm-2">
			    	<img  badgeNo="${ badge.badgeNo }" width="150" height="150" src="../images/uploadFiles/${badge.badgeImg}" alt="..." 
						onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'"/>
						<input type="file" class="form-control" id="imageUpload" multiple="multiple" name="imageUpload">
			    </div>
 

			  	<label for="badgeImg2" class="col-sm-offset-2 col-sm-1 control-label" style="margin-left: 50px;">배지 은 등급 이미지</label>
			    <div class="col-sm-2">
			    	<img  badgeNo="${ badge.badgeNo }" width="150" height="150" src="../images/uploadFiles/${badge.badgeImg}" alt="..." 
						onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'"/>
						<input type="file" class="form-control" id="imageUpload" multiple="multiple" name="imageUpload">
			    </div>


			  	<label for="badgeImg3" class="col-sm-offset-2 col-sm-1 control-label" style="margin-left: 50px;">배지 금 등급 이미지</label>
			    <div class="col-sm-2">
			    	<img  badgeNo="${ badge.badgeNo }" width="150" height="150" src="../images/uploadFiles/${badge.badgeImg}" alt="..." 
						onerror="this.src='https://dummyimage.com/280x250/1af0d4/000000.gif'"/>
						<input type="file" class="form-control" id="imageUpload" multiple="multiple" name="imageUpload">
			    </div>
<!-- 배지 등급 이미지 입력 End -->			    





		  </div>

			<br>
		  
<!-- 버튼 모음 -->
		  <div class="form-group">
		    <div class="col-sm-offset-4  col-sm-4 text-center">
		      <button type="button" class="btn btn-primary addB"  >등록 완료</button>
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
		
		
							