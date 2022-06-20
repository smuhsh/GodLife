<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!DOCTYPE html>

<html lang="ko">
	
<head>
	<meta charset="EUC-KR">
	
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
   
      <link href="../css/kfonts2.css" rel="stylesheet">
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
   
   
   <!-- jQuery UI toolTip 사용 CSS-->
  <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
  <!-- jQuery UI toolTip 사용 JS-->
  <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
  
  <!-- 상단바삽입 -->
	<jsp:include page="/layout/toolbar.jsp" />
	
	<!-- 왼쪽 레이아웃 삽입-->
		<jsp:include page="/user/mypageMain.jsp" />
	
	<!--  ///////////////////////// CSS ////////////////////////// -->
	<style>
	 
        h2{
                font-size: 2.3rem;
                padding-right: 100px;
            }
            
            #head_aticle{
            padding-top : 80px;
            }
            
            #container{
            padding-left: 250px;
            padding-top : 130px;
            }
            
            #caption{
            font-size: 15px;
            }
            
            h3{
            font-size: 20px;
            font-weight: bold;
            
            }
            
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
            width: 50%; /* Could be more or less, depending on screen size */                          
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
            
    </style>
    
    
     <!--  ///////////////////////// JavaScript ////////////////////////// -->
	<script type="text/javascript">
	
		//============= 검색 / page 두가지 경우 모두  Event  처리 =============	
		function fncGetList(currentPage) {
			$("#currentPage").val(currentPage)
			$("form").attr("method" , "POST").attr("action" , "/user/listUserRecvMsg?").submit();
		}
		
		//============= "검색"  Event  처리 =============	
		 $(function() {
			 //==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$( "button.btn.btn-default" ).on("click" , function() {
			fncGetList(1);
			});
		 });
		
		//=============  선택 삭제처리 =============
			
		 $(function() {
				
				$("#writeBtn1").on("click" , function() {
					
					var checkCount = $("input[name='deleteCheck']:checked").length;
				    var array = new Array();
					$("input[name='deleteCheck']:checked").each(function() {
						array.push($(this).attr('id'));  <!-- 배열의 끝에 요소를 추가  -->
				    });
					
					//Debug..
					if(checkCount != 0) {
						alert(checkCount+"개의 쪽지를 삭제하시겠습니까?")
						self.location = "/user/deleteUserRecvMsg?checkList="+array;
					} else {
						alert("선택된 쪽지가 없습니다.")						
					}
				});
			});
		
		
		
		
			//=============  모달 테스트 =============	
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
			
			
	   //==>"받는사람 이메일" 있는지 없는지 검사 
		
		function checkUserEmail(){
	        var userEmail = $('#recvEmail').val(); //id값이 "id"인 입력란의 값을 저장
	        
	        $.ajax({
	        	 url: '/user/json/checkUserEmail', //Controller에서 요청 받을 주소
	            type:'post', //POST 방식으로 전달
	            data:{userEmail:userEmail},
	       
	            success:function(cnt){ //컨트롤러에서 넘어온 cnt값을 받는다 
	                if(cnt == 0){ //cnt가 1이 아니면(=0일 경우) -> 디비에 없으니까 보낼수없는 이메일  
	                    $('.id_ok').css("display","inline-block"); 
	                    $('.id_already').css("display", "none");
	                    //alert("해당 이메일은 존재하지 않습니다.");
	                } else { // cnt가 1일 경우 -> 이미 존재하는 이메일이니까 보낼수있음
	                    $('.id_already').css("display","inline-block");
	                    $('.id_ok').css("display", "none");
	                    //alert("이메일을 다시 입력해주세요");
	                }
	            },
	            error:function(){
	                alert("에러입니다");
	            }
	        });
	        };
		
		
			//============= "답장""  Event 연결 =============
		 $(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$( "#writeBtn" ).on("click" , function() {
				fncAddUserMsg();
			});
		});	
		
			
		function fncAddUserMsg() {
			
			var recvEmail=$("input[name='recvEmail']").val();
			var name=$("input[name='title']").val();
			
			if(recvEmail == null || recvEmail.length <1){
				alert("이메일은  반드시 입력하셔야 합니다.");
				return;
			}
			
			if(name == null || name.length <1){
				alert("제목은  반드시 입력하셔야 합니다.");
				return;
			}
			
			$("form").attr("method" , "POST").attr("action" , "/user/addUserMsg").submit();
			alert("전송이 완료되었습니다")
		}
		
		
		//============= "내용 글자수 검사" =============
		$(document).ready(function() {
	    $('#detail').on('keyup', function() {
	        $('#test_cnt').html("("+$(this).val().length+" / 1000)");
	 
	        if($(this).val().length > 1000) {
	            $(this).val($(this).val().substring(0, 1000));
	            $('#test_cnt').html("(1000 / 1000)");
	        }
	    });
	});
		
		//============= "제목 글자 수 검사" =============
		
		$(document).ready(function() {
	    $('#title').on('keyup', function() {
	        $('#test_cnt1').html("("+$(this).val().length+" / 50)");
	 
	        if($(this).val().length > 50) {
	            $(this).val($(this).val().substring(0, 50));
	            $('#test_cnt1').html("(50 / 50)");
	        }
	    });
	});
		
	</script>
	
</head>

<body>
	
	<!--  화면구성 div Start /////////////////////////////////////-->

	<div class="container" id="container" >
	
		<div class="head_aticle" align="center" id = "head_aticle">
	      <h2 class="tit" style="color: #333;">받은 쪽지함</h2>
	      
	      <button type="button" class="btn active btn_join" id="writeBtn1" style="float: right;  margin-right: 10px;">선택삭제</button>
									         
									         <!-- 모달 버튼  -->
								    <button type="button" class="btn active btn_join" id="myBtn" style="float: right;  margin-right: 10px;">답장</button>
								    <!-- The Modal -->
								    <div id="myModal" class="modal">
								 
								      <!-- 답장보내기 모달 내용 p태그안에 -->
								      <div class="modal-content">
								        <span class="close">&times;</span>    
								                                                                   
				<div class="page_aticle">
		<div class="type_form getUser" id="getUser">
			<form id="form" name="frmMember" >
			
				<div class="field_head">
					<h3 class="tit">쪽지 보내기</h3>
				</div>
				
				<table class="tbl_comm">
				
				<tr class="fst">
						<th>이메일</th>
						<td>
							<input type="text" class="form-control" id="recvEmail" name="recvEmail" oninput = "checkUserEmail()" >
							<span id="helpBlock" class="id_ok">해당 이메일은 존재하지 않습니다. </span>
			 				 <span id="helpBlock" class="id_already"></span>
						</td>
					</tr>
				
					<tr>
						<th>제목</th>
						<td>
						      <input type="text" class="form-control" id="title" name="title"  style="width :455px; height : 45px;">
						      <div id="test_cnt1" style ="font-size : 13px">(0 / 50)</div>
						  </td>
					</tr>
					
					<tr class="detail">
						<th>내용</th>
						<td>
								<textarea id = "detail" name="detail" cols="50" rows="20"  style="width :455px; height : 327px;"></textarea>
								<div id="test_cnt" style ="font-size : 13px">(0 / 1000)</div>
						</td>
					</tr>
					
					</table>
	
				<div id="formSubmit" class="form_footer">
					<div id="checkDiv" class="checkDiv"></div>
					<button type="button"  class="btn active btn_join" id="writeBtn">전송</button>
				</div>
			</form>
		</div>
		
			  	
		 	  <input type="hidden" name="msgNo" id="msgNo" value="${msg.msgNo}"> 
	</div>
								        
        
      </div>
    </div>
      <!-- 모달 끝-->

	    </div>
	    
	    <br></br>
	    <!-- table 위쪽 검색 Start /////////////////////////////////////-->
	    <div class="row" >
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-secondary" >
		    		전체  ${resultPage.totalCount } 건수, 현재 ${resultPage.currentPage}  페이지
		    	</p>
		    </div>
		    
		    <div class="col-md-6 text-right">
			    <form class="form-inline" name="detailForm">
			    
			 <!--  
				  <div class="form-group">
				    <select class="form-control" name="searchCondition" >
						<option value="0"  ${ ! empty search.searchCondition && search.searchCondition==0 ? "selected" : "" }>닉네임</option>
					</select>
				  </div>
				  
				
				  <div class="form-group">
				    <label class="sr-only" for="searchKeyword">검색어</label>
				    <input type="text" class="form-control" id="searchKeyword" name="searchKeyword"  placeholder="검색어"
				    			 value="${! empty search.searchKeyword ? search.searchKeyword : '' }"  >
				  </div> 
				  
				  <button type="button" class="btn btn-default">검색</button>-->
				  
				  
				  
				  <!-- PageNavigation 선택 페이지 값을 보내는 부분 -->
				  <input type="hidden" id="currentPage" name="currentPage" value=""/>
				  
				</form>
	    
	    	
		</div>
		<!-- table 위쪽 검색 Start /////////////////////////////////////-->
		
		<br></br>
      <table class="table table-hover table-striped" >
      
        <thead>
        <tr class="bg-light">
          <th scope="col" width="10%"></th>
          <th scope="col" width="20%">닉네임</th>
          <th scope="col" width="40%" >제목</th>
          <th scope="col" width="20%">날짜</th>
        </tr>
      </thead>
       
		<tbody>
		
		  <c:set var="i" value="0" />
		  <c:forEach var="msg" items="${list}">
			<c:set var="i" value="${ i+1 }" />
			<tr>
			  <td align="center"><input type="checkbox" name="deleteCheck" id="${msg.msgNo}"></td>
			  <td align="left" > <a  href="/user/getUserTarget?userEmail=${msg.sendEmail}">${msg.nick} </a></td>
			   <td align="left" ><a  href="/user/getUserRecvMsg?msgNo=${msg.msgNo}" onclick="window.open(this.href, '_blank', 'width=600, height=550'); return false;">${msg.title}</a></td>
			   <td align="left">${msg.regDate}</td>
			   
			  <!--  <td align="left">
			  	<input type="hidden" value="${user.userEmail}">
			  	<input type="hidden" value="${friendBlack.targetEmail}">
			  </td> -->
			  
			</tr>
          </c:forEach>
        
        </tbody>
      
      </table>
	  <!--  table End /////////////////////////////////////-->
	  
 	</div>
 	</div>
 	<!--  화면구성 div End /////////////////////////////////////-->
 	
 	
 	<!-- PageNavigation Start... -->
	<jsp:include page="../common/pageNavigator_new.jsp"/>
	<!-- PageNavigation End... -->
	
</body>

</html>