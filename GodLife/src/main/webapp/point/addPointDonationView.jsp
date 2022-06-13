<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<html>
<head>
<title>포인트 기부</title>

<meta charset="UTF-8">

<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<!--  ///////////////////////// Bootstrap, jQuery CDN ////////////////////////// -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../javascript/calendar.js"></script>

<link href="/css/animate.min.css" rel="stylesheet">
<link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">

<!-- Bootstrap Dropdown Hover JS -->
<script src="/javascript/bootstrap-dropdownhover.min.js"></script>

<style>
body {
	padding-top: 70px;
	margin: 0px;
	background-size: 100%;
	background-image: url("/images/uploadFiles/donation.jpg")
}

p {
	padding: 30px;
	font-size: 17px;
	font-weight: bold;
	text-align: center;
	background-color: #ffffff;
	opacity: 0.3;
}
.form-control{
height: 50px;
}
.donation{
	width: 100px;
	height: 50px;
	margin: 0 auto;
}

label {
	font-size: 25px;
	color: #ffffff;
	opacity: 0.5;
}
table {
	background-color: #ffffff;
	opacity: 0.6;
}
</style>

<script type="text/javascript">
	function fncAddPointDonation() {

		var donationPlace = $(".form-control").val();
		var point = $("input[name='point']").val();
		var useStatus = $("input[name='useStatus']").val();
		var useDetail = $("input[name='useDetail']").val();

		if (donationPlace == null || donationPlace.length < 1) {
			alert("기부처는 반드시 입력하여야 합니다.");
			return;
		}
		if (point == null || point.length < 1 || point < 1000) {
			alert("기부금은 반드시 1000원 이상 입력해야 합니다.");
			return;
		}

		alert($("input[name='nick']").val() + "님의 "
				+ $("input[name='point']").val() + "포인트가 "
				+ $(".form-control").val() + "에 기부되었습니다.");
		$("form").attr("method", "POST").attr("action",
				"/point/addPointPurchaseDonation").submit()
	}

	$(function() {
		$(".donation").on("click", function() {
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

		<div class="form-group"></div>
		<div class="form-group">
			<label for="inputEmail3" class="col-sm-2 control-label">Donation
				place</label>
			<div class="col-sm-3">
				<select class="form-control" name="donationPlace">
					<option value="양로원">양로원</option>
					<option value="무료급식소">무료급식소</option>
					<option value="아동복지기관">아동복지기관</option>
					<option value="후원단체">후원단체</option>
				</select>
			</div>
			<div class="col-sm-3"></div>
			<div class="col-sm-3">
				<table class="table">
					<tr>
						<td>랭킹<br></td>
						<td>닉네임<br></td>
						<td></td>
						<td>금액</td>
					</tr>
					<tr>
					</tr>

					<c:set var="i" value="0" />
					<c:forEach var="point" items="${list }">
						<c:set var="i" value="${i+1 }" />
						<tr>
							<td><img src="/images/uploadFiles/메달${i}.png" width=20px>${i}위</td>
							<td>${point.nick}</td>
							<td></td>
							<td>${point.point}</td>
						</tr>
					</c:forEach>
				</table>

			</div>
		</div>

		<div class="form-group">
			<label for="inputPassword3" class="col-sm-2 control-label">Point</label>
			<div class="col-sm-3">
				<input type="text" class="form-control" name="point"
					placeholder="기부금"> <input type="hidden" name="userEmail"
					value="${user.userEmail}" /> <input type="hidden" name="nick"
					value="${user.nick}" /> <input type="hidden" name="totalPoint"
					value="${user.totalPoint}" /> <input type="hidden"
					name="useStatus" value="2" /> <input type="hidden"
					name="useDetail" value="7" />
			</div>
			<div class="col-sm-3"></div>
			<div class="col-sm-3"></div>
		</div>

		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-10">
				<button type="button" class="donation">기부하기</button>
			</div>
		</div>
		<div class="form-group">
			<div class="col-sm-offset-2 col-sm-3">
				<p>
					남을 도울 때 가장 덕을 보는 것은 자기 자신이고,<br> 최고의 행복을 얻는 것도 자기 자신이다.<br>
					-Dalai Lama-
				</p>

			</div>
		</div>

	</form>
</body>
</html>