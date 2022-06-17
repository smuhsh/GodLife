<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
	
	
	
<!-- CSS-->
<link rel="stylesheet" href="/css/getUser.css" />

<style>

body {
           padding-top : 50px;
           padding-right: :5px;
       }
       
  #getUser{
       padding-top : 130px;
       }
        
img
{
border : 5px solid white;
width : 200px;
height : 200px;
float : center;
}

#writeBtn{
width : 140px;
height : 40px;
}

#writeBtn1{
width : 140px;
height : 40px;
}

</style>

<!-- 상단바삽입 -->
	<jsp:include page="/layout/toolbar.jsp" />
	<!-- 왼쪽 레이아웃 삽입-->
	<jsp:include page="/user/mypageMain.jsp" />

<!--  자바스크립트 -->
<script type="text/javascript">
$(function() {
	//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
	$( "#writeBtn" ).on("click" , function() {
		alert("친구 등록이 완료되었습니다.")
		$("form").attr("method" , "POST").attr("action" , "/user/addFriend?targetEmail=${user.userEmail}").submit();
	});
});	

$(function() {
	//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
	$( "#writeBtn1" ).on("click" , function() {
		alert("블랙리스트 등록이 완료되었습니다.")
		$("form").attr("method" , "POST").attr("action" , "/user/addBlack?targetEmail=${user.userEmail}").submit();
	});
});	

	</script>

<title>유저 상세정보 조회</title>
</head>
<body>
	<div class="page_aticle">
		<div class="type_form getUser" id="getUser">
			<form id="form" name="frmMember">
				<div class="field_head">
					<h3 class="tit">유저 상세정보 조회</h3>
				</div>
				
				<table class="tbl_comm">
				
				
				<tr class="profileImg">
						<th>프로필이미지</th>
						<td>
								<img src="/images/uploadFiles/${user.profileImg} " onerror="this.onerror=null; this.src='https://via.placeholder.com/240X200?text=No Image';" > 
						</td>
					</tr>
				
					<tr class="userEmail">
						<th>이메일</th>
						<td>
							<input type="text" name="userEmail" id="userEmail" required="" fld_esssential="" option="regId" label="아이디" placeholder="${user.userEmail}" readonly/>
						</td>
					</tr>
					<tr>
					
					<tr class="nick">
						<th>닉네임</th>
						<td>
							<input type="text" name="nick" id="nick" value="" required="" fld_esssential="" label="닉네임" placeholder="${user.nick}" readonly/>
						</td>
					</tr>
					
					
					<tr class="categNo">
						<th>관심사</th>
						<td>
								<input type="text" value=""  name="categNo" id="categNo" placeholder="${user.categName}"  readonly/>
						</td>
					</tr>
					
					<tr class="intro">
						<th>자기소개</th>
						<td>
							<textarea id = "intro" name="intro" cols="30" rows="10" placeholder="${user.intro}"  readonly/></textarea>
						</td>
					</tr>
					
					<!-- 챌린지랑 배지 관련 넣어야함 -->
					
					</table>
	
	
				<div id="formSubmit" class="form_footer">
					<div id="checkDiv" class="checkDiv"></div>
					<button type="button" class="btn active btn_join" id="writeBtn">친구 등록</button>
					<button type="button" class="btn active btn_join" id="writeBtn1">블랙리스트 등록</button>
				</div>
			</form>
		</div>
		
		
	</div>
</body>
</html>