<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<!-- CSS-->
<link rel="stylesheet" href="/css/getUser.css" />
<style>
img
{
border : 5px solid white;
width : 200px;
height : 200px;
float : center;
}


</style>

<!--  자바스크립트 -->
<script type="text/javascript">
		
		//============= 회원정보수정 Event  처리 =============	
		 $(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			 $( "button" ).on("click" , function() {
					self.location = "/user/updateUser?userEmail=${user.userEmail}"
				});
		});
		
	</script>

<title>개인정보 조회</title>
</head>
<body>
	<div class="page_aticle">
		<div class="type_form getUser">
			<form id="form" name="frmMember">
				<div class="field_head">
					<h3 class="tit">개인정보 조회</h3>
				</div>
				
				<table class="tbl_comm">
				
				<tr class="profileImg">
						<th>프로필이미지</th>
						<td>
						<div class="profileImg">
								<img src="/images/uploadFiles/${user.profileImg } " onerror="this.onerror=null; this.src='https://via.placeholder.com/240X200?text=No Image';" > </div>
						</td>
					</tr>
				
					<tr class="userEmail">
						<th>이메일</th>
						<td>
							<input type="text" name="userEmail" id="userEmail" maxlength="16" required="" fld_esssential="" option="regId" label="아이디" placeholder="${user.userEmail}" readonly/>
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
							<div class="phone"">
								<input type="text" value="" pattern="[0-9]*" name="phone" id="phone" maxlength="13" placeholder="${user.phone}" class="inp" readonly/>
							</div>
						</td>
					</tr>
					
					<tr class="categNo">
						<th>관심사</th>
						<td>
						<div class="categNo">
								<input type="text" value=""  name="categNo" id="categNo" placeholder="${user.categName}"  readonly/>
							</div>
						</td>
					</tr>
					
					<tr class="intro">
						<th>한줄 소개</th>
						<td>
						<div class="intro">
								<input type="text" value=""  name="intro" id="intro" placeholder="${user.intro}"  readonly/>
							</div>
						</td>
					</tr>
					
					<tr class="reportCount">
						<th>신고 보유 개수</th>
						<td>
						<div class="intro">
								<input type="text" value=""  name="reportCount" id="reportCount" placeholder="${user.reportCount}"  readonly/>
							</div>
							<button type="button" class="btn btn-info">Info</button>
							
						</td>
					</tr>
					
					<tr class="redCardCount">
						<th>레드카드 개수</th>
						<td>
						<div class="redCardCount">
								<input type="text" value=""  name="redCardCount" id="redCardCount" placeholder="${user.redCardCount}" readonly/>
							</div>
						</td>
					</tr>
					
					</table>
					
					
					
					
					
					
					
					
	
				<div id="formSubmit" class="form_footer">
					<button type="button" class="btn active btn_join" id="writeBtn">수정하기</button>
				</div>
				
			</form>
		</div>
		
		
<iframe id="ifrmRnCheck" name="ifrmRnCheck" style="display:none;"></iframe>
<iframe id="ifrmHpauth" name="ifrmHpauth" style="display:none;"></iframe>
	</div>
</body>
</html>