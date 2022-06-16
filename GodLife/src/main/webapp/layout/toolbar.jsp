<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
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
      
      //=============  판매상품등록 Event  처리 =============   
      $(function() {
         //==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
          $("a:contains('판매상품등록')").on("click" , function() {
            //$(self.location).attr("href","/user/logout");
            self.location = "/product/addProductView.jsp"
         }); 
       });
      
      
      //=============  판매상품관리 Event  처리 =============   
      $(function() {
         //==> DOM Object GET 3가지 방법 ==> 1. $(tagName) : 2.(#id) : 3.$(.className)
          $("a:contains('판매상품관리')").on("click" , function() {
            //$(self.location).attr("href","/user/logout");
            self.location = "/product/listProduct?menu=manage"
         }); 
       });
      
   </script>

<style>
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
            
                <li class="service_center_item">
               <a class="service_center" href="/user/getUser?userEmail=${sessionScope.user.userEmail}"> 마이페이지</a>
               <button type="button" class="service_center_icon"></button>
               
               <c:if test="${user.userEmail != null}">
               <li class="sign_in_item"><a href="/challenge/addChallengeTos.jsp">챌린지 생성</a></li>
            </c:if>
               
               
               <ul class="service_center_detail">
                  <li><a href="/user/getUser?userEmail=${sessionScope.user.userEmail}">개인정보 조회</a></li>
                  <li><a href="링크">포인트 구매</a></li>
                  <li><a href="링크">상품권 구매</a></li>
                  <li><a href="링크">쿠폰 구매</a></li>
                  <li><a href="링크">기부하기</a></li>
                  
                  </ul>
                  </li>
                    
                  </c:if>
               
               <c:if test="${user.userEmail != null}">
               <li class="sign_in_item"><a href="/user/logout">로그아웃</a></li>
            </c:if>
            

         </ul>
      </div>

      <div class="logo_image">
      <h1 class="logo">
      <a href="/"> 
            <img src="/images/uploadFiles/GodLife 임시로고.png" />
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

            <li class="new-product-category">
               <a	href="/operator/listOperatorEvent" style="text-decoration:none;">이벤트</a></li>
               
               <li class="new-product-category">
               <a	href="/operator/listOperatorNotice" style="text-decoration:none;">공지사항</a></li>
               
               <li class="new-product-category">
               <a	href="/operator/listOperatorFaqs" style="text-decoration:none;">FAQ</a></li>
               
               
               
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
                  <li><a class="링크">인증이미지 게시글관리</a></li>
                  <li><a class="링크"> 개설한 챌린지관리</a></li>
                  <li><a class="링크">포인트 상품관리</a></li>
                  <li><a class="링크">상품권 관리</a></li>
                  <li><a class="링크">쿠폰 관리</a></li>
                  <li><a class="링크">배지 관리</a></li>
                  <li><a class="/operator/listOperatorEvent">이벤트 관리</a></li>
                  <li><a class="/operator/listJoinOperatorEvent">이벤트참여자 관리</a></li>
                  <li><a class="링크">일대일문의 관리</a></li>
                  <li><a class="/operator/listOperatorNotice">공지사항 관리</a></li>
                  <li><a class="/operator/listOperatorFaqs">FAQ 관리</a></li>
               </ul>
            </li>
                  </c:if>
         </ul>
		</div>
      </div>
   </div>



   
</body>
</html>