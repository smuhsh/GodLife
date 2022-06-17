<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
<script scr="/resources/css/addChallengeCertiImg.css"></script>
<title>Insert title here</title>

<style>


@font-face {
		    font-family: 'oneMobile';
		    src: url('/resources/css/font/ONE Mobile Title.ttf') format('truetype');
		}

#header{
	text-align:center;
	position:relative;
	top:10px;
}

.page-header{

	margin-top: auto!important;

}

.container{
	font-family: 'oneMobile';
}

.thumbnail{
	width:900px;
	height:300px;
	
}

#inbox{
	
	width:700px!important;
	height: 40px!important;
	position:relative;
	z-index: 1;
	left:77px;

}

input[type='file']{
	position:relative;
	z-index: 2;
	top:30px;
}

.buttons{
	postion:relative;
	top:300px;
}



</style>

<script type="text/javascript">
	
	$(function(){
		$("button#upload").on("click",function(){
			
			 if($("input[name='certiImgFile']").val() == ""){
				 alert("인증이미지 를 선택해 주세요.");
				 return;
			 }else{
				 
				 var fileval = $("input[name='certiImgFile']").val();
				 
				 var ext = fileval.split('.').pop().toLowerCase();
			 
				 if($.inArray(ext, ['jpg','jpeg','gif','png']) == -1) {
			          alert("jpg, jpeg, gif, png 파일만 업로드할 수 있습니다.");
			          return;
			     }
				 
			 }
			
			 alert("인증 이미지가 업로드 되었습니다.");
			 
			$("form[name='fileUpload']").attr("action","/challenge/addChallengeCertiImg").
			submit();
		});
	});
	
</script>


</head>
<body>
	<form name="fileUpload" method="POST" enctype="multipart/form-data">
	
	<input type="hidden" name="challengeNo" value="${challenge.challengeNo }">
		<div class="container">
		<div class="page-header">
  			<p id="header">인증 이미지 업로드</p>
		</div>
		  <div class="col-sm-6 col-md-4">
		    <div class="thumbnail">
		      <div class="caption">
		      <input type="file" name="certiImgFile">
		      		<div class="thumbnail" id="inbox">
		     		 <div class="caption">
		       		 </div>
		   		 </div>
		   		   <center>
		   		   	<div class="buttons">
		       			<button type="button" id="upload" class="btn btn-default">이미지 업로드</button>
		   		   	</div>
		   		   		<br/>
		   		   	<div class="buttons">
		       			<button type="button" class="btn btn-default">뒤로가기</button>
		   		   	</div>
		       	   </center>
		      </div>
		    </div>
		  </div>
	</div>
	</form>
</body>	
</html>