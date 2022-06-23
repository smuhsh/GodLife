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

#aa{

}

</style>



<!--  자바스크립트 -->
<script type="text/javascript">
		
		//============= 수정하기 Event  처리 =============	
		 $(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			 $("#writeBtn" ).on("click" , function() {
				 self.location = "/user/updateUser?userEmail=${user.userEmail}"
				});
		});
		
		
		//============= 쿠폰사용 Event  처리 =============	
		 $(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			 $("#useRedCoupon" ).on("click" , function() {
				 if(${user.redCardCount}<1){
					 alert("지급받은 레드카드가 없습니다.");
				 }else if(${user.redCardCount}>=1){
					 if(${user.redCouponCount}<1){
						 alert("보유 쿠폰이 부족합니다.");
					 }else{
					location.href="/user/updateUserRedCouponCountUse"
					 }
				 }
				 
			});
		});
		
	</script>
	

<title>개인정보 조회</title>
</head>
<body>

	<!-- 왼쪽 레이아웃 삽입-->
	<jsp:include page="/user/mypageMain.jsp" />

	<div class="page_aticle" id="aa">
		<div class="type_form getUser" id="getUser">
			<form id="form" name="frmMember">
				<div class="field_head">
					<h3 class="tit">개인정보 조회</h3>
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
					
					
					<tr class="phone">
						<th>휴대폰 번호</th>
						<td>
								<input type="text" value="" pattern="[0-9]*" name="phone" id="phone" maxlength="13" placeholder="${user.phone}" class="inp" readonly/>
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
					
					<tr class="reportCount">
						<th>신고 보유 개수</th>
						<td>
								<input type="text" value=""  name="rc" id="rc" placeholder="${user.reportCount}"  readonly/>
						</td>
					</tr>
					
					<tr class="redCardCount">
						<th>레드카드 개수</th>
						<td>
								<input type="text" value=""  name="redCardCount" id="redCardCount" placeholder="${user.redCardCount}" readonly/>
								<button style="WIDTH: 60pt; HEIGHT: 30pt" type="button" class="btn active btn_join" id="useRedCoupon">쿠폰 사용<p style="font-size: 10px;">${user.redCouponCount}장 보유</p></button>
						</td>
					</tr>
					
					
					</table>
					
	
				<div id="formSubmit" class="form_footer">
					<div id="checkDiv" class="checkDiv"></div>
					<button type="button" class="btn active btn_join" id="writeBtn">수정하러가기</button>
				</div>
			</form>
		</div>
		
		
		
	</div>
</body>
</html>