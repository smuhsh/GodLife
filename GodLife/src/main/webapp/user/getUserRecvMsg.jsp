<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
       }
       
      #getUser{
       padding-top : 130px;
       }
       
       #myBtn, #writeBtn,#writeBtn33,#writeBtn18 {
		width : 140px;
		height : 40px;
		}
       
       .id_ok{
	color:#064ACB;
	display: none;
	font-size : 14px;
	}
	
	.id_already{
	color:#064ACB; 
	display: none;
	font-size : 14px;
	}
	
	.id_ok2{
	color:#064ACB;
	display: none;
	font-size : 14px;
	}
	.id_already2{
	color:#064ACB; 
	display: none;
	font-size : 14px;
	}
	
	.doubleChk{
	color:#064ACB;
	font-size : 14px;
	
	}
	
	input::placeholder {
  color: black;
  font-size : 14px;
}
textarea::placeholder {
  color: black;
  font-size : 14px;
}



/*답장모달임*/
/* The Modal (background) */
    .modal {
        display: none; /* Hidden by default */
        position: fixed; /* Stay in place */
        z-index: 1; /* Sit on top */
        left: 0;
        top: 0;
        width: 100%; /* Full width */
        height: 100%; /* Full height */
        overflow: auto; /* Enable scroll if needed */
        background-color: rgb(0,0,0); /* Fallback color */
        background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
    }

    /* Modal Content/Box */
    .modal-content {
        background-color: #fefefe;
        margin: 15% auto; /* 15% from the top and centered */
        padding: 20px;
        border: 1px solid #888;
        width: 40%; /* Could be more or less, depending on screen size */                          
    }
    /* The Close Button */
    .close {
        color: #aaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
    }
    .close:hover,
    .close:focus {
        color: black;
        text-decoration: none;
        cursor: pointer;
    }
    
    /*신고모달임*/
/* The Modal (background) */
    .modal1 {
        display: none; /* Hidden by default */
        position: fixed; /* Stay in place */
        z-index: 1; /* Sit on top */
        left: 0;
        top: 0;
        width: 100%; /* Full width */
        height: 100%; /* Full height */
        overflow: auto; /* Enable scroll if needed */
        background-color: rgb(0,0,0); /* Fallback color */
        background-color: rgba(0,0,0,0.4); /* Black w/ opacity */
    }

    /* Modal Content/Box */
    .modal-content1 {
        background-color: #fefefe;
        margin: 15% auto; /* 15% from the top and centered */
        padding: 20px;
        border: 1px solid #888;
        width: 40%; /* Could be more or less, depending on screen size */                          
    }
    /* The Close Button */
    .close1 {
        color: #aaa;
        float: right;
        font-size: 28px;
        font-weight: bold;
    }
    .close1:hover,
    .close1:focus {
        color: black;
        text-decoration: none;
        cursor: pointer;
    }
    
    
	
 </style>

<style>

img
{
border : 5px solid white;
width : 200px;
height : 200px;
float : center;
}

</style>


<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->
   	
<!-- 왼쪽 레이아웃 삽입-->
<jsp:include page="/user/mypageMain.jsp" />

  <!--  자바스크립트 -->
<script type="text/javascript">

//===================쪽지 답장 모달 =========================
$(function() {
	
        // Get the modal
    var modal = document.getElementById('myModal');

    // Get the button that opens the modal
    var btn = document.getElementById("myBtn");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close")[0];                                          

    // When the user clicks on the button, open the modal 
    btn.onclick = function() {
        modal.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
});	

	//============= "쪽지보내기"  Event 연결 =============
	 $(function() {
		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		$( "#writeBtn5" ).on("click" , function() {
			fncAddUserMsg();
		});
	});	
	
	function fncAddUserMsg() {
		
		var name=$("#title1").val();
		//var ok= $.trim($('.id_ok').text());
		//console.log("이거제발나와...."+ok);
		//alert("이거나오니...."+ok);
		
		/*
		if(ok == '해당 이메일은 존재하지 않습니다.'){
			alert("이메일을 다시 확인해주세요");
			return;
		}
		*/
		
		if(name == null || name.length <1){
			alert("제목은  반드시 입력하셔야 합니다.");
			return;
		}
		
		$("form").attr("method" , "POST").attr("action" , "/user/addUserMsg").submit();
	}


//============= "내용 글자수 검사" =============
	
$(document).ready(function() {
$('#detail1').on('keyup', function() {
 $('#test_cnt').html("("+$(this).val().length+" / 1000)");

 if($(this).val().length > 1000) {
     $(this).val($(this).val().substring(0, 1000));
     $('#test_cnt').html("(1000 / 1000)");
 }
});
});

//============= "제목 글자 수 검사" =============

$(document).ready(function() {
$('#title1').on('keyup', function() {
 $('#test_cnt1').html("("+$(this).val().length+" / 50)");

 if($(this).val().length > 50) {
     $(this).val($(this).val().substring(0, 50));
     $('#test_cnt1').html("(50 / 50)");
 }
});
});


//===================신고 모달 =========================
$(function() {
	
        // Get the modal
    var modal = document.getElementById('myModal1');

    // Get the button that opens the modal
    var btn = document.getElementById("writeBtn33");

    // Get the <span> element that closes the modal
    var span = document.getElementsByClassName("close1")[0];                                          

    // When the user clicks on the button, open the modal 
    btn.onclick = function() {
        modal.style.display = "block";
    }

    // When the user clicks on <span> (x), close the modal
    span.onclick = function() {
        modal.style.display = "none";
    }

    // When the user clicks anywhere outside of the modal, close it
    window.onclick = function(event) {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    }
});	


//===========신고 접수 ==============
	
	$(function() {
		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		$( "#writeBtn18" ).on("click" , function() {
			$("form").attr("method" , "POST").attr("action" , "/user/addUserReport").submit();
		});
	});	


	</script>


<title>받은 쪽지 상세조회</title>
</head>
<body>
	<div class="page_aticle">
		<div class="type_form getUser" id="getUser">
			<form id="form" name="frmMember" >
			
				<div class="field_head">
					<h3 class="tit">받은 쪽지 상세조회</h3>
				</div>
				
				<table class="tbl_comm">
				
				<tr class="fst">
						<th>보낸 사람</th>
						<td>
							<input type="text" class="form-control" id="sendEmail" name="sendEmail" placeholder="${msg.nick}"  readonly style="width:450px; height:40px; background-color: #f2f2f2; border:0; outline:0;">
						</td>
					</tr>
				
					<tr>
						<th>제목</th>
						<td>
						      <input type="text" class="form-control" id="title" name="title"  style="width :455px; height : 45px;" placeholder="${msg.title}"  readonly style="width:450px; height:40px; background-color: #f2f2f2; border:0; outline:0;">
						  </td>
					</tr>
					
					<tr class="detail">
						<th>내용</th>
						<td>
								<textarea id = "detail" name="detail" cols="50" rows="20"  style="width :455px; height : 327px;" placeholder="${msg.detail}" readonly/></textarea>
						</td>
					</tr>
					
					</table>
	
				<div id="formSubmit" class="form_footer">
					<div id="checkDiv" class="checkDiv"></div>
					  <button type="button" class="btn active btn_join" id="myBtn">답장</button>
			
			
 <!--------------------------------------------------------------------- 답장 모달 시작------------------------------- -->						
    <!-- The Modal -->
    <div id="myModal" class="modal">
 
      <!-- Modal content -->
      <div class="modal-content" id="modal-content">
        <span class="close">&times;</span>                                                               
        
        <div class="page_aticle">
		<div class="type_form getUser" id="getUser1">
			<form id="form" name="frmMember" >
			
				<div class="field_head">
					<h3 class="tit">쪽지 보내기</h3>
				</div>
				
				<table class="tbl_comm">
				
				<tr class="fst">
						<th>이메일</th>
						<td>
							<input type="text" class="form-control" id="recvEmail" name="recvEmail" value = "${msg.sendEmail}"  placeholder="${msg.sendEmail}" oninput = "checkUserEmail()"  readonly/>
							<!-- <span id="helpBlock" class="id_ok">해당 이메일은 존재하지 않습니다. </span>-->
			 				 <span id="helpBlock" class="id_already"></span>
						</td>
					</tr>
				
					<tr>
						<th>제목</th>
						<td>
						      <input type="text" class="form-control" id="title1" name="title"  style="width :455px; height : 45px;">
						      <div id="test_cnt1" style ="font-size : 13px">(0 / 50)</div>
						  </td>
					</tr>
					
					<tr class="detail">
						<th>내용</th>
						<td>
								<textarea id = "detail1" name="detail" cols="50" rows="20"  style="width :455px; height : 327px;"></textarea>
								<div id="test_cnt" style ="font-size : 13px">(0 / 1000)</div>
						</td>
					</tr>
					
					</table>
	
				<div id="formSubmit" class="form_footer">
					<div id="checkDiv" class="checkDiv"></div>
					<button type="button"  class="btn active btn_join" id="writeBtn5">전송</button>
				</div>
			</form>
		</div>
	</div>
        
        
      </div>
 
    </div>
  
    <!------------------답장 모달끝------------------------------------------------- ----->
    
    
			  <button type="button" class="btn active btn_join" id="writeBtn33">신고</button>
<!--------------------------------------------------------------------- 신고 모달 시작------------------------------- -->	

 <!-- The Modal -->
    <div id="myModal1" class="modal1">
 
      <!-- Modal content -->
      <div class="modal-content1" id="modal-content1">
        <span class="close1">&times;</span>                    
        
        <div class="page_aticle">
		<div class="type_form getUser" id="getUser1">
			
			
				<div class="field_head">
					<h3 class="tit">신고</h3>
				</div>
				
				<table class="tbl_comm">
				
				<tr class="fst">
						<th>쪽지번호</th>
						<td>
							<input type="text" class="form-control" id="msgNo" name="msgNo" value = "${msg.msgNo}"  placeholder="${msg.msgNo}"  readonly/>
						</td>
					</tr>
				
				<tr class="fst">
						<th>신고대상</th>
						<td>
							<input type="text" class="form-control" id="targetEmail" name="targetEmail"  placeholder="${msg.sendEmail}"  value = "${msg.sendEmail}" readonly/>
						</td>
					</tr>
					
					
					<tr>
						<th>신고사유</th>
						<td>
						      <label class="checked">
								<input type="radio" name="reason" value="1" checked="checked">
								<span class="ico"></span>욕설
							</label>
							<label class="">
								<input type="radio" name="reason" value="2">
								<span class="ico"></span>광고
							</label>
							<label class="">
								<input type="radio" name="reason" value="3">
								<span class="ico"></span>사기
							</label>
							<label class="">
								<input type="radio" name="reason" value="4">
								<span class="ico"></span>음란물
							</label>
							<label class="">
								<input type="radio" name="reason" value="5">
								<span class="ico"></span>도배
							</label>
						  </td>
					</tr>
					
					</table>
	
				<div id="formSubmit" class="form_footer">
					<div id="checkDiv" class="checkDiv"></div>
					<button type="button"  class="btn active btn_join" id="writeBtn18">접수</button>
				</div>
			
		</div>
	</div>
        
        
      </div>
 
    </div> 
			   
			
			<!------------------신고 모달끝------------------------------------------------- ----->
			
				</div>
			</form>
		</div>
		
		
		
	</div>
</body>
</html>