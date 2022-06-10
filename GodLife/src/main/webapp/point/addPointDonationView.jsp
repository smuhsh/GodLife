<%@ page contentType="text/html; charset=UTF-8" %>



<html>
<head>
<title>포인트 기부</title>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0" />
	
	<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
<script type="text/javascript" src="../javascript/calendar.js"></script>

<link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>

	<style>
	  body {
            padding-top : 70px;
        }
        
    </style>

<script type="text/javascript">
function fncAddPointDonation(){
	 
			var donationPlace=$("input[name='donationPlace']").val();
			var point=$("input[name='point']").val();
			var useStatus =$("input[name='useStatus']").val();
			var useDetail =$("input[name='useDetail']").val();

			if(donationPlace == null || donationPlace.length<1){
				alert("기부처는 반드시 입력하여야 합니다.");
				return;
			}
			if(point == null || point.length<1 || point<1000){
				alert("기부금은 반드시 1000원 이상 입력해야 합니다.");
				return;
			}

			alert($("input[name='nick']").val()+"님의 "+$("input[name='point']").val()+"포인트가 "+$("input[name='donationPlace']").val()+"에 기부되었습니다.");
			$("form").attr("method", "POST").attr("action","/point/addPointPurchaseDonation").submit()
}

$(function() {
	$( ".btn.btn-default" ).on("click" , function() {
		fncAddPointDonation();
	});
});	


</script>

</head>

<body bgcolor="#ffffff" text="#000000">

<form class="form-horizontal">
<div class="row">
<jsp:include page="/layout/toolbar.jsp" />
</div>
<div class="form-group"> 
  </div>
  <div class="form-group">
    <label for="inputEmail3" class="col-sm-2 control-label">Donation place</label>
    <div class="col-sm-6">
      <input type="text" class="form-control" name="donationPlace" placeholder="기부처">
    </div>
  </div>
  <div class="form-group">
    <label for="inputPassword3" class="col-sm-2 control-label">Point</label>
    <div class="col-sm-6">
      <input type="text" class="form-control" name="point" placeholder="기부금">
      <input type="hidden" name="userEmail"  value="${user.userEmail}"/>
      <input type="hidden" name="nick"  value="${user.nick}"/>
      <input type="hidden" name="totalPoint"  value="${user.totalPoint}"/>
      <input type="hidden" name="useStatus"  value="2"/>
      <input type="hidden" name="useDetail"  value="7"/>
    </div>
  </div>

  <div class="form-group">
    <div class="col-sm-offset-2 col-sm-10">
      <button type="button" class="btn btn-default">기부하기</button>
    </div>
  </div>
</form>
</body>
</html>