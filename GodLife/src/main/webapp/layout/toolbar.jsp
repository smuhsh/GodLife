<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>GodLife</title>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>

<link rel="stylesheet" href="/resources/css/toolbar2.css" />
   <script type="text/javascript">
   
      $(document).ready(function() {
         
         //전체 카테코리 호버 이벤트
         $(".menu>.all_category").mouseover(function() {
            $(this).children(".all_category_item").stop().slideDown();
         });
         
         $(".menu>.all_category").mouseleave(function() {
            $(this).children(".all_category_item").stop().slideUp();
         });
         
      
      //마이페이지 호버이벤트
      $(".member_items .service_center_item").mouseover(function() {
         $(this).children(".service_center_detail").stop().slideDown();
      });
      
      $(".member_items .service_center_item").mouseleave(function() {
         $(this).children(".service_center_detail").stop().slideUp();
      });
      
   
      $('#all_category_item>li').click(function(){
         //alert
         location.href = '/challenge/listChallenge?challengeListOpt=total&searchCondition=2&orderCondition=' + $(this).children('a').attr('class');
      });
      
});   
      
   // //header 고정 이벤트
   //  $(function(){
   //      var top_pos= $('.menuDiv').offset().top;
   //      win = window;
   //      $(win).on('scroll',
   //         function(){
   //           var pos = $(this).scrollTop();
   //           
   //           if(pos >= top_pos){
   //              $('.menuDiv').addClass('fix');
   //           } 
   //            else{
   //              $('.menuDiv').removeClass('fix');
   //            }
   //      });         
   //    });
   // 
      
      
   </script>

<style>
@font-face {
   font-family: 'S-CoreDream-4Regular';
   src:
      url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_six@1.2/S-CoreDream-4Regular.woff')
      format('woff');
   font-weight: normal;
   font-style: normal;
}
#profile{
font-family: 'S-CoreDream-4Regular';
font-size:15px;
}
.menuDiv{

}
/*
.header{
background-color:  #EDFFFD;
backgrouund-size :  100%;
border: #EDFFFD;
/*.menuDiv : #EDFFFD;*/
/*border-color:  #EDFFFD;*/
margin : 0;
}
*/

.logo_image{
background-color:  #EDFFFD;
}
#profileImg{
   width: 50px;
    height: 50px; 
    border-radius: 70%;
    overflow: hidden;
}
div#menu-bar{
   text-align: center;
    align-items: center;
    display: flex;
    justify-content: center;
    padding-left: 300px;
}
</style>

</head>
<body>

   <!-- header -->
  <div class="menuDiv"> 
   <div class="header">

      <div align="center" class="header_bar">
         <ul class="member_items">
         
            <c:if test="${user.userEmail== null}">
               <li class="sign_up_item"><a href="/user/addUser">회원가입</a></li>
               <li class="sign_in_item"><a href="/user/login">로그인</a></li>
            </c:if>


            <c:if test="${user.userEmail != null}">
                <img src="/images/uploadFiles/${sessionScope.user.profileImg}" id="profileImg"> &nbsp; <div id="profile">닉네임 : ${sessionScope.user.nick }<br>포인트 : ${sessionScope.user.totalPoint }</div>
               <br>
                <li class="service_center_item">
               <a class="service_center"> 마이페이지</a>
               <button type="button" class="service_center_icon"></button>
               <ul class="service_center_detail">
                  <li><a href="/user/getUser?userEmail=${sessionScope.user.userEmail}">개인정보 조회</a></li>
                  <li><a href="/product/getProductPointList">포인트 구매</a></li>
                  <li><a href="/product/getProductVoucherList">상품권 구매</a></li>
                  <li><a href="/product/getProductCouponList">쿠폰 구매</a></li>
                  <li><a href="/point/addPointDonationView">기부하기</a></li>
                  
                  </ul>
                  </li>
                    
                  </c:if>
               
               
               <c:if test="${user.userEmail != null}">
               <li class="sign_in_item"><a href="/challenge/addChallengeTos.jsp">챌린지 생성</a></li>
            </c:if>
               
               
               <c:if test="${user.userEmail != null}">
               <li class="sign_in_item">   
               		               
		             <c:if test="${user.joinPath == '1'}">
		             <a href="/user/logout">로그아웃</a>
 		            </c:if>
               
		             <c:if test="${user.joinPath != '1'}">
		             <a href="https://kauth.kakao.com/oauth/logout?client_id=6d708d50985428b8450271c1e7e98b04&logout_redirect_uri=http://localhost:8080/user/logout">로그아웃</a>
		            </c:if>
		            
              </li>
               
            </c:if>
            
       
            

         </ul>
      </div>

      <div class="logo_image">
      <h1 class="logo">
      <a href="/"> 
            <img src="/images/로고.png" />
            </a>
      </h1>
      </div>
      
<!--/////////////////////////// 고정된 탑바 ////////////////////////////////////////////// -->
      
      <div id="menu-bar">
         <ul class="menu">
            <!-- == main -->
            <li class="all_category header_a_li">
                <a href="#" class="button">
                  <button type="button" class="menu_button"></button> 챌린지 관심사
            </a> 
               <ul class="all_category_item" id="all_category_item">

                  <li><a class="1">운동</a></li>
                  <li><a class="2">식습관</a></li>
                  <li><a class="3">공부</a></li>
                  <li><a class="4"> 취미</a></li>
                  <li><a class="5"> 생활</a></li>
               </ul>
            </li>

            <li class="all_category header_a_li">
                <a href="/operator/listOperatorEvents" class="button">
                  <button type="button" class="menu_button"></button>이벤트
            </a> 
               <ul class="all_category_item" id="all_category_item">
               
                     <!--  <li><a href="/operator/OperatorNewEvent">신규회원</a></li>-->
                     <li><a href="/operator/getOperatorJoinDayEvent?eventNo=1">매일출석</a></li>
                     <li><a href="/operator/getOperatorJoinRoullEvent?eventNo=2">룰렛 이벤트</a></li>
               </ul>
            </li>
               
               <li class="all_category header_a_li">
               <a   href="/operator/listOperatorNotice">공지사항</a>
               </li>
               
               <li class="all_category header_a_li">
               <a   href="/operator/listOperatorFaqs">FAQ</a></li>
               
               
               
            <!-- == 관리자 -->
            <c:if test="${user.role == '2'}">
            <li class="all_category header_a_li">
                <a href="#" class="button">
                  <button type="button" class="menu_button"></button> 관리자
            </a> 
               <ul class="all_category_item" id="all_category_item">

                  <li><a href="링크">회원 전체목록</a></li>
                    <li><a href="링크">신고관리</a></li>
                  <li><a class="링크">챌린지관리</a></li>
                  <li><a href="/product/getProductPointList">포인트 상품관리</a></li>
                  <li><a href="/product/getProductVoucherList">상품권 관리</a></li>
                  <li><a href="/product/getProductCouponList">쿠폰 관리</a></li>
                  <li><a href="/badge/getBadgeList">배지 관리</a></li>
                  <li><a href="/operator/listOperatorEvents">이벤트 관리</a></li>
                  <li><a class="링크">이벤트참여자 관리</a></li>
                  <li><a class="링크">일대일문의 관리</a></li>
                  <li><a href="/operator/listOperatorNotice">공지사항 관리</a></li>
                  <li><a href="/operator/listOperatorFaqs">FAQ 관리</a></li>
               </ul>
            </li>
                  </c:if>
         </ul>
      </div>
      </div>
   </div>



   
</body>
</html>