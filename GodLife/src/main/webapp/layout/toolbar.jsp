<%@ page contentType="text/html; charset=EUC-KR" %>
<%@ page pageEncoding="EUC-KR"%>

<!--  ///////////////////////// JSTL  ////////////////////////// -->
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<!-- ToolBar Start /////////////////////////////////////-->
<div class="navbar  navbar-inverse navbar-fixed-top">
	
	<div class="container">
	       
		<a class="navbar-brand" href="/index.jsp">GodLife</a>
		
		<!-- toolBar Button Start //////////////////////// -->
		<div class="navbar-header">
		    <button class="navbar-toggle collapsed" data-toggle="collapse" data-target="#target">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		    </button>
		</div>
		<!-- toolBar Button End //////////////////////// -->
		
	    <!--  dropdown hover Start -->
		<div 	class="collapse navbar-collapse" id="target" 
	       			data-hover="dropdown" data-animations="fadeInDownNew fadeInRightNew fadeInUpNew fadeInLeftNew">
	         
	         	<!-- Tool Bar 를 다양하게 사용하면.... -->
	             <ul class="nav navbar-nav">
	             
	              <!--  회원관리 DrowDown -->
	              <li class="dropdown">
	                     <a  href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
	                         <span >회원관리</span>
	                         <span class="caret"></span>
	                     </a>
	                     <ul class="dropdown-menu">
	                         <li><a href="#">개인정보조회</a></li>
	                         <li><a href="#">친구목록조회</a></li>
	                          <li><a href="#">블랙리스트목록조회</a></li>
	                          <li><a href="#">친구요청목록조회</a></li>
	                         <li><a href="#">공지사항목록</a></li>
	                         <li><a href="#">회원정보조회</a></li>
	                         <li><a href="#">공지사항관리</a></li>
	                         <li><a href="#">생성쿠폰목록조회</a></li>
	                         <li><a href="#">쿠폰발급목록조회</a></li>
	                      
	                     </ul>
	                 </li>
	                 
	                 <!--  회원관리 DrowDown -->
	              <li class="dropdown">
	                     <a  href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
	                         <span >쪽지관리</span>
	                         <span class="caret"></span>
	                     </a>
	                     <ul class="dropdown-menu">
	                         <li><a href="#">쪽지보내기</a></li>
	                         <li><a href="#">받은쪽지 목록조회</a></li>
	                          <li><a href="#">보낸쪽지 목록조회</a></li>
	                     </ul>
	                 </li>
	                 
	                 
	                 
	              <!-- 판매상품관리 DrowDown  -->
	               <c:if test="${sessionScope.user.role == 'admin'}">
		              <li class="dropdown">
		                     <a  href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
		                         <span >판매관리</span>
		                         <span class="caret"></span>
		                     </a>
		                     <ul class="dropdown-menu">
		                         <li><a href="#">판매상품등록</a></li>
		                         <li><a href="#">판매상품관리</a></li>
		                         <li><a href="#">판매목록조회</a></li>
		                     </ul>
		                </li>
	                 </c:if>

	              <!-- 배지관리 DrowDown  -->
		              <li class="dropdown">
		                     <a  href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
		                         <span >배지관리</span>
		                         <span class="caret"></span>
		                     </a>
		                     <ul class="dropdown-menu">
		                         <li><a href="#">배지전체목록</a></li>
		                         <li><a href="#">내배지전체목록</a></li>
		                     </ul>
		                </li>
		                
	              <!-- 상품관리 DrowDown  -->
		              <li class="dropdown">
		                     <a  href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
		                         <span >상품관리</span>
		                         <span class="caret"></span>
		                     </a>
		                     <ul class="dropdown-menu">
	                         <li><a href="#">쿠폰상품전체목록</a></li>
	                         <li><a href="#">상품권상품전체목록</a></li>
	                         <li><a href="#">포인트상품전체목록</a></li>
		                     </ul>
		                </li>
	                 
	              <!-- 구매관리 DrowDown -->
	              <li class="dropdown">
	                     <a  href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
	                         <span >상품구매</span>
	                         <span class="caret"></span>
	                     </a>
	                     <ul class="dropdown-menu">
	                         <li><a href="#">상 품 검 색</a></li>
	                         
	                         <c:if test="${sessionScope.user.role == 'user'}">
	                           <li><a href="#">구매이력조회</a></li>
	                         </c:if>
	                         
	                         <li><a href="#">최근 본 상품</a></li>
	                     </ul>
	                 </li>
	              <li class="dropdown">
	                     <a  href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
	                         <span >Point</span>
	                         <span class="caret"></span>
	                     </a>
	                     <ul class="dropdown-menu">
	                         <li><a href="#">기부하기</a></li>
	                         <li><a href="#">기부내역</a></li>
	                         <li><a href="#">포인트이용내역</a></li>
	         				 <li><a href="#">상품권구매내역</a></li>
	                     </ul>
	                 </li>
	             </ul>
	             
	             <ul class="nav navbar-nav navbar-right">
	                <li><a href="#">로그아웃</a></li>
	            </ul>
	            
	             <ul class="nav navbar-nav navbar-right">
	                <li><a href="#" id="addChallengeTos">챌린지 등록</a></li>
	                <li class="dropdown">
	                     <a  href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">
	                         <span >챌린지</span>
	                         <span class="caret"></span>
	                     </a>
	                     <ul class="dropdown-menu">
	                         <li><a href="#" id="listChallenge">챌린지 목록</a></li>
	                         <li><a href="#" id="listChallengeAdd">등록한 챌린지 목록</a></li>
	                         <li><a href="#" id="listChallengeJoin">참여한 챌린지 목록</a></li>
	         				 <li><a href="#" id="listChallengePick">찜한 챌린지 목록</a></li>
	                     </ul>
	                 </li>
	            </ul>
	            
		</div>
		<!-- dropdown hover END -->	       
	    
	</div>
</div>
		<!-- ToolBar End /////////////////////////////////////-->
 	
   	
   	
   	<script type="text/javascript">
	
		//============= logout Event  처리 =============	
		 $(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		 	$("a:contains('로그아웃')").on("click" , function() {
				$(self.location).attr("href","/user/logout");
				//self.location = "/user/logout"
			}); 
		 });
		
		//============= 회원정보조회 Event  처리 =============	
		 $(function() {
			//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
		 	$("a:contains('회원정보조회')").on("click" , function() {
				//$(self.location).attr("href","/user/logout");
				self.location = "/user/listUser"
			}); 
		 });
		
		//=============  개인정보조회 Event  처리 =============	
	 	$( "a:contains('개인정보조회')" ).on("click" , function() {
	 		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$(self.location).attr("href","/user/getUser?userEmail=${sessionScope.user.userEmail}");
		});
		
		//=============  친구목록조회 Event  처리 =============	
	 	$( "a:contains('친구목록조회')" ).on("click" , function() {
	 		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$(self.location).attr("href","/user/listFriend?userEmail=${sessionScope.user.userEmail}");
		});
		
	 	//=============  블랙리스트목록조회 Event  처리 =============	
	 	$( "a:contains('블랙리스트목록조회')" ).on("click" , function() {
	 		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$(self.location).attr("href","/user/listBlack?userEmail=${sessionScope.user.userEmail}");
		});
	 	
	 	//=============  친구요청목록조회 Event  처리 =============	
	 	$( "a:contains('친구요청목록조회')" ).on("click" , function() {
	 		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$(self.location).attr("href","/user/listFriendRequest");
		});
	 	
	 	
		//=============  쪽지보내기 Event  처리 =============	
	 	$( "a:contains('쪽지보내기')" ).on("click" , function() {
	 		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$(self.location).attr("href","/user/add?");
		});
		
	 	//=============  받은쪽지 목록조회 Event  처리 =============	
	 	$( "a:contains('받은쪽지 목록조회')" ).on("click" , function() {
	 		//==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
			$(self.location).attr("href","/user/listUserRecvMsg?recvEmail=${sessionScope.user.userEmail}");
		});
			
		
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
	 	
		
	 	 $(function() {
	 		//=============  판매상품등록 Event  처리 =============
		 	$("a:contains('판매상품등록')").on("click" , function() {
				self.location = "/product/addProductView.jsp"
			}); 			 		
		 });
	 	 
	 	$(function() {
	 	//=============  판매상품관리 Event  처리 =============
		 	$("a:contains('판매상품관리')").on("click" , function() {
				self.location = "/product/listProduct?menu=manage"
			}); 
	 	 });
	 	
	 	$(function() {
		//=============  상 품 검 색 Event  처리 =============
			$("a:contains('상 품 검 색')").on("click" , function() {
				self.location = "/product/listProduct?menu=search"
			}); 
		});
	 	
	 	$(function() {
		 //=============  판매목록조회 Event  처리 =============
		 	$("a:contains('판매목록조회')").on("click" , function() {
				self.location = "/purchase/listSale?menu=manage"
			}); 
		});
	 	
	 	$(function() {
		//=============  구매이력조회 Event  처리 =============
		 	$("a:contains('구매이력조회')").on("click" , function() {
				self.location = "/purchase/listPurchase"
			}); 
		});
	 	
	 	$(function() {
		 	$("a:contains('공지사항관리')").on("click" , function() {
		 		self.location = "/operator/getOperatorNoticeFaqs"
			}); 
		});
	 	
	 	$(function() {
		 	$("a:contains('공지사항목록')").on("click" , function() {
		 		self.location = "/operator/listOperatorNoticeFaqs"
			}); 
		});
///////////////////////////////////////////////////////////////	 	
	 	$(function() {
		 	$("a:contains('쿠폰상품전체목록')").on("click" , function() {
		 		self.location = "/product/getProductCouponList"
			}); 
		});
		
	
	 	$(function() {
		 	$("a:contains('상품권상품전체목록')").on("click" , function() {
		 		self.location = "/product/getProductVoucherList"
			}); 
		});
	 	
	 	$(function() {
		 	$("a:contains('포인트상품전체목록')").on("click" , function() {
		 		self.location = "/product/getProductPointList"
			}); 
		});
///////////
	 	$(function() {
		 	$("a:contains('배지전체목록')").on("click" , function() {
		 		self.location = "/badge/getBadgeList"
			}); 
		});	 	
///////////////////////////////////////////////////////////////	 	 	 	
		$(function() {
		 	$("a:contains('쿠폰발급')").on("click" , function() {
		 		self.location = "/coupon/addCouponView"
			}); 
		});
		
		$(function() {
		 	$("a:contains('생성쿠폰목록조회')").on("click" , function() {
		 		self.location = "/coupon/listCoupon"
			}); 
		});
		
		$(function() {
		 	$("a:contains('쿠폰발급목록조회')").on("click" , function() {
		 		self.location = "/coupon/listIssuedCoupon"
			}); 
		});
	 	
	 	$( "a:contains('최근 본 상품')" ).bind("click" , function() {
	 		popWin = window.open("/history.jsp", "popWin",
	 		"left=300, top=200, width=300, height=200, marginwidth=0, marginheight=0, scrollbars=no, scrolling=no, menubar=no, resizable=no");
		});
	 	$(function() {
		 	$("a:contains('기부하기')").on("click" , function() {
		 		self.location = "/point/addPointDonationView"
			}); 
		});
	 	$(function() {
		 	$("a:contains('기부내역')").on("click" , function() {
		 		self.location = "/point/getPointPurchaseDonationList"
			}); 
		});
	 	$(function() {
		 	$("a:contains('포인트이용내역')").on("click" , function() {
		 		self.location = "/point/getPointPurchaseList"
			}); 
		});
	 	$(function() {
		 	$("a:contains('상품권구매내역')").on("click" , function() {
		 		self.location = "/point/getPointPurchaseVoucherList"
			}); 
		});
		
	 	$(function(){
	 		$("a#addChallengeTos").on("click",function(){
	 			self.location = "/challenge/addChallengeTos.jsp"
	 		});
	 		
	 		$("a#listChallenge").on("click",function(){
	 			self.location = "/challenge/listChallenge" //테스트
	 		});
	 		
	 		$("a#listChallengeAdd").on("click",function(){
	 			self.location = "/challenge/listChallenge?challengeListOpt=add";
	 		});
	 		
	 		$("a#listChallengeJoin").on("click",function(){
	 			self.location = "/challenge/listChallenge?challengeListOpt=join";
	 		});
	 		
	 		$("a#listChallengePick").on("click",function(){
	 			self.location = "/challenge/listChallenge?challengeListOpt=pick";
	 		});
	 		
	 	});
		
	</script>  