<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--   jQuery , Bootstrap CDN  -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
	
	<!--  CSS 추가 : 툴바에 화면 가리는 현상 해결 :  주석처리 전, 후 확인-->
	<style>
        
        @font-face {
		    font-family: 'oneMobile';
		    src: url('/resources/css/font/ONE Mobile Title.ttf') format('truetype');
		}
        
        .container{
        padding-top : 200px;
        font-family: 'oneMobile';
        }
        
       	p{
       		font-size : 14px;
       		text-aling : center;
       	}
       	
       	textarea{
       		width:100%;
       		resize: none;
       	}
       	
       
   	</style>

	
	<script type="text/javascript">
		
	
	
	
		<!-- jQuery DatePicker -->
		 $(function() {
			 $.datepicker.setDefaults({
				 dateFormat: 'yy-mm-dd'
			 });
			 
	    	$( "#startDatePicker" ).datepicker();
	    	$( "#endDatePicker" ).datepicker();
	 	 });
		 
		 
		 $(function(){
			 $("button#addChallenge").on("click",function(){

				 if($("input[name='challengeTitle']").val() == ""){
					 alert("챌린지 제목을 입력해 주세요.");
					 return;
				 }
				 if($("input[name='joinPoint']").val() == ""){
					 alert("입장 포인트를 입력해 주세요.");
					 return;
				 }else{
					 var joinPoint = $("input[name='joinPoint']").val();
					 var result = joinPoint % 1000;
					 if(joinPoint<1000){
						 alert("최소 입장포인트는 1000포인트 입니다.");
					 	 return;
					 }else if(joinPoint>100000){
						 alert("최대 입장포인트는 10만포인트 입니다.");
						 return;
					 }else if(result != 0){
						 alert("포인트 최소 단위는 1000포인트 입니다.");
						 return;
					 }
				 }
				 if($("textarea#detail").val() == ""){
					 alert("챌린지 소개를 작성해 주세요");
					 return;
				 }
				 if($("textarea#rule").val() == ""){
					 alert("챌린지 규칙을 작성해 주세요");
					 return;
				 }
				 
				 if($("input[name='challengeCategNo']").is(":checked") != true){
					 alert("챌린지 관심사를 선택해 주세요.");
					 return;
				 }
				 
				 if($("input[name='certiCycle']").is(":checked") != true){
					 alert("인증 주기를 선택해 주세요.");
					 return;
				 }
				
				 if($("input[name='startDate']").val() == ""){
					 alert("챌린지 시작 날짜를 지정해 주세요.");
					 return;
				 }else if($("input[name='endDate']").val() == ""){
					 alert("챌린지 종료 날짜를 지정해 주세요.");
					 return;
				 }else{
					 
					 var nowDate = new Date();
					 var startDate = new Date($("input[name='startDate']").val());
					 var endDate = new Date($("input[name='endDate']").val());
					 
					 if(startDate.getTime() == endDate.getTime()){
						 alert("챌린지 최소 기간은 2일입니다.");
						return;
					 }
					 if(nowDate.getTime() > startDate.getTime()){
						 alert("시작날짜는 현재 날짜와 같거나 이전이 될 수 없습니다.");
						 return;
					 }else if(nowDate.getTime() == startDate.getTime()){
						 alert("시작날짜는 현재 날짜와 같거나 이전이 될 수 없습니다.");
						 return;
					 }else if(startDate.getTime() > endDate.getTime()){
						 alert("잘못된 형식의 기간 입니다.");
					 	return;
					 }
					 
				 }
				 
				 
				 if($("input[name='openRange']").is(":checked") != true){
					 alert("챌린지 공개 범위를 선택해 주세요.");
					 return;
				 }
				
				 if($("input[name='thumbnail']").val() == ""){
					 alert("썸네일 이미지를 선택해 주세요.");
					 return;
				 }else{
					 
					 var fileval = $("input[name='thumbnail']").val();
					 
					 var ext = fileval.split('.').pop().toLowerCase();
				 
					 if($.inArray(ext, ['jpg','jpeg','gif','png']) == -1) {
				          alert("jpg, jpeg, gif, png 파일만 업로드할 수 있습니다.");
				          return;
				     }
					 
				 }
				 
				 	 
				 $("form[name='addChallengeForm']").attr("method","POST")
				 .attr("action","/challenge/addChallengeView").submit();
				 
			 });
		 });
	
	</script>


</head>
<body>
<jsp:include page="/layout/toolbar.jsp" />

	<div class="container">
		<div class="row">
		  <div class="col-md-2"></div>
		  <div class="col-md-8">
		   <h3 class="text-center bg-info">챌린지 등록</h3>
				
				<ul class="list-group">
				  <li class="list-group-item">
				    <form name="addChallengeForm" enctype="multipart/form-data">
				    
						  <div class="form-group">
						    <label for="exampleInputEmail1">챌린지 제목</label>
						    <input type="email" class="form-control" 
						    name="challengeTitle" id="exampleInputEmail1" placeholder="챌린지 제목">
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputPassword1">입장 포인트</label>
						    <input type="number" class="form-control" 
						    name="joinPoint" id="exampleInputPassword1" placeholder="최소 1000포인트 최대 10만포인트 제한입니다. 단위는 1000포인트 단위입니다.">
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputPassword1">챌린지 소개</label>
						    <div>
						    	<textarea name="challengeDetail" cols="45" rows="6" id="detail"
						   		 placeholder="ex) 하루에 한번 물 500ml마시기 챌린지 입니다."></textarea>
						    </div>
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputPassword1">챌린지 규칙</label>
						    <div>
						    	<textarea name="challengeRule" cols="45" rows="6" id="rule"
						   		 placeholder="ex) 인증사진은 숟가락과 같이 찍어 올려주세요."></textarea>
						    </div>
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputPassword1">챌린지 관심사</label>
						    <div>
						    	<span>
						    		<input type="radio" name="challengeCategNo" value="1" id="exercise">
						    		<label for="exercise">운동</label>
						    		&nbsp;
						    		<input type="radio" name="challengeCategNo" value="2" id="eat">
						    		<label for="eat">식습관</label>
						    		&nbsp;
						    		<input type="radio" name="challengeCategNo" value="3" id="statudy">
						    		<label for="statudy">공부</label>
						    		&nbsp;
						    		<input type="radio" name="challengeCategNo" value="4" id="hobby">
						    		<label for="hobby">취미</label>
						    		&nbsp;
						    		<input type="radio" name="challengeCategNo" value="5" id="life">
						    		<label for="life">생활</label>
						    		
						    	</span>
						    </div>
						  </div>
						  
						  <div class="form-group">
							  	<label for="exampleInputPassword1">인증 주기</label>
							  	<div>
							  	  <input type="checkbox" name="certiCycle" value="1" id="sunday">
							      <label for="sunday">일</label>
							  	  &nbsp;
							      <input type="checkbox" name="certiCycle" value="2" id="monday">
							      <label for="monday">월</label>
							      &nbsp;
							      <input type="checkbox" name="certiCycle" value="3" id="tuesday">
							      <label for="tuesday">화</label>
							      &nbsp;
							      <input type="checkbox" name="certiCycle" value="4" id="wednesday">
							      <label for="wednesday">수</label>
							      &nbsp;
							      <input type="checkbox" name="certiCycle" value="5" id="thursday">
							      <label for="thursday">목</label>
							      &nbsp;
							      <input type="checkbox" name="certiCycle" value="6" id="friday">
							      <label for="friday">금</label>
							      &nbsp;
							      <input type="checkbox" name="certiCycle" value="7" id="saturday">
							      <label for="saturday">토</label>
							    </div>	
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputEmail1">챌린지 시작일</label>
						    <div>
						    	<input type="text" name ="startDate" id="startDatePicker" readonly>
						    </div>
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputEmail1">챌린지 종료일</label>
						    <div>
						    	<input type="text" name ="endDate" id="endDatePicker" readonly>
						    </div>
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputEmail1">공개 범위</label>
						    <div>
						    	<input type="radio" name="openRange" value="0" id="total">
					    		<label for="total">전체 공개</label>
					    		&nbsp;
					    		<input type="radio" name="openRange" value="1" id="friend">
					    		<label for="friend">친구 공개</label>
						    </div>
						  </div>
						  
						  <div class="form-group">
						    <label for="exampleInputFile">썸네일 이미지 등록</label>
						    <input type="file" name="thumbnail" id="exampleInputFile">
						    <p class="help-block">이미지 파일만 올려주세요.</p>
						  </div>
						  
						  
						  
				    </form>	
				  </li>
				</ul>
				
		  	<center>
			  <div class="buttonGroup">
			  		<button type="button" id="cancel" class="btn btn-danger">취소</button>
			  		&nbsp;&nbsp;&nbsp;
			  		<button type="button" id="addChallenge" class="btn btn-primary">챌린지 등록</button>
			  </div>
		  </div>
			</center>
		  <div class="col-md-2"></div>
		</div>
	</div>
</body>
</html>