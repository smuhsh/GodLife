<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>마이 페이지</title>


<style>
	li .list-group-item{
		align-items: center;
	}
	#content {
	    min-width: 1050px;
	    padding-bottom: 120px;
	    position: relative;
	}
	#header, #container, #content {
	    position: relative;
	}
	div, th, td, li, dt, dd, p {
	    word-break: break-all;
	}
	*, *:after, *:before {
	    -webkit-box-sizing: border-box;
	    -moz-box-sizing: border-box;
	    box-sizing: border-box;
	    margin: 0;
	    padding: 0;
	}
	div {
	    display: block;
	}
	body, input, select, textarea, button {
	    font-family: noto sans,malgun gothic,AppleGothic,dotum;
	    line-height: 1;
	    letter-spacing: -.05em;
	    color: #4c4c4c;
	    font-size: 12px;
	    max-width: 100%;
	}
	script {
	    display: none;
	}
	b, strong {
	    font-weight: 700;
	}
	.mypage_top {
	    width: 100%;
	    padding: 50px 0;
	    margin-bottom: -20px;
	    background-color: #f7f7f7;
	}
	.page_aticle button, .page_aticle input, .page_aticle select, .page_aticle * {
	    font-family: noto sans;
	    font-weight: 400;
	    letter-spacing: 0;
	}
	.mypage_top .mypagetop_user {
	    overflow: hidden;
	    width: 1050px;
	    margin: 0 auto;
	}
	.mypage_top .inner_mypagetop {
	    overflow: hidden;
	    width: 100%;
	    background: url(https://res.kurly.com/pc/service/common/1904/bg_mypagetop.png) repeat-y 0 0;
	}
	.mypage_top .grade_user {
	    float: left;
	    width: 444px;
	    height: 200px;
	    padding: 28px 0 30px 30px;
	    background-color: #fff;
	}
	.mypage_top .grade_user .grade {
	    overflow: hidden;
	    width: 100%;
	    padding-right: 26px;
	}
	.mypage_top .grade_user .class0 {
	    border: 1px solid #5f0080;
	    background-color: #fff;
	    color: #5f0080;
	}
	.mypage_top .grade_user .ico_grade {
	    float: left;
	    width: 64px;
	    height: 64px;
	    margin: 2px 20px 0 0;
	    border-radius: 3px;
	    font-size: 16px;
	    color: #fff;
	    text-align: center;
	    word-break: break-all;
	}
	.mypage_top .grade_user .in_grade {
	    display: table-cell;
	    width: 100%;
	    vertical-align: middle;
	    line-height: 18px;
	    word-break: keep-all;
	}
	.mypage_top .grade_user .inner_grade {
	    display: table;
	    width: 100%;
	    height: 64px;
	    padding-bottom: 3px;
	}
	.mypage_top .grade_bnenfit {
	    overflow: hidden;
	}
	.mypage_top .grade_user .user {
	    overflow: hidden;
	    padding: 2px 0 14px;
	}
	.mypage_top .name {
	    overflow: hidden;
	    float: left;
	    max-width: 263px;
	    font-weight: 700;
	    font-size: 20px;
	    color: #333;
	    line-height: 28px;
	    white-space: nowrap;
	    text-overflow: ellipsis;
	}
	.mypage_top .txt {
	    float: left;
	    padding-left: 5px;
	    font-size: 16px;
	    line-height: 28px;
	}
	.mypage_top .benefit .tit {
	    color: #5f0080;
	}
	.mypage_top .next {
	    overflow: hidden;
	    width: 100%;
	    padding: 22px 0 0 84px;
	}
	.mypage_top .next a {
	    float: left;
	    height: 30px;
	    padding-top: 4px;
	    margin-right: 10px;
	    border: 1px solid #f4f4f3;
	    border-radius: 15px;
	    background-color: #f4f4f4;
	    font-size: 14px;
	    color: #333;
	    line-height: 18px;
	    text-align: center;
	}
	.mypage_top .total_grade {
	    width: 121px;
	}
	.mypage_top .next a {
	    float: left;
	    height: 30px;
	    padding-top: 4px;
	    margin-right: 10px;
	    border: 1px solid #f4f4f3;
	    border-radius: 15px;
	    background-color: #f4f4f4;
	    font-size: 14px;
	    color: #333;
	    line-height: 18px;
	    text-align: center;
	}
	.mypage_top .next_month {
	    width: 169px;
	}
	.mypage_top .list_mypage {
	    float: right;
	    width: 602px;
	}
	.mypage_top .list_mypage li {
	    float: left;
	    width: 190px;
	    margin-right: 4px;
	    background-color: #fff;
	}
	.mypage_top .list_mypage .link {
	    float: left;
	    width: 100%;
	    padding: 29px 0 0 30px;
	}
	.mypage_top .list_mypage .tit {
	    padding-bottom: 26px;
	    font-size: 14px;
	    line-height: 18px;
	    color: #333;
	}
		.mypage_top .list_mypage .info {
	    font-weight: 700;
	    font-size: 20px;
	    color: #5f0080;
	    line-height: 28px;
	}
	.mypage_top .list_mypage .info img {
	    width: 28px;
	    height: 28px;
	    margin: 1px 0 0 -4px;
	    vertical-align: top;
	}
	.mypage_top .list_mypage .info .date {
	    display: block;
	    padding-top: 12px;
	    font-size: 12px;
	    color: #999;
	    line-height: 16px;
	}
	.mypage_top .list_mypage .user_kurlypass {
	    margin-right: 0;
	}
	.mypage_top .list_mypage .link {
	    float: left;
	    width: 100%;
	    padding: 29px 0 0 30px;
	}
/* 	.page_aticle.aticle_type2 {
	    padding-top: 65px;
	} */
	.page_aticle {
	    width: 1050px;
	    margin: 0 auto;
	}
	#snb {
	    float: left;
	    width: 200px;
	}
	#snb .tit_snb {
	    padding: 8px 0 33px 1px;
	    font-weight: 700;
	    font-size: 30px;
	    line-height: 34px;
	    color: #333;
	    letter-spacing: -.5px;
	}
	#snb .inner_snb {
	    border: 1px solid #f2f2f2;
	    border-bottom: 0;
	}
	ol, ul {
	    list-style-type: none;
	}
	#snb .list_menu li {
	    border-bottom: 1px solid #f2f2f2;
	}
	#snb .list_menu li.on a, #snb .list_menu li a:hover {
	    background: #fafafa url(https://res.kurly.com/pc/ico/2008/ico_arrow_6x11_on.svg) no-repeat 174px 52%;
	    background-size: 6px 11px;
	    font-weight: 700;
	    color: #5f0080;
	}
	#snb .list_menu li a {
	    display: block;
	    overflow: hidden;
	    padding: 15px 0 15px 20px;
	    background: #fff url(https://res.kurly.com/pc/ico/2008/ico_arrow_6x11.svg) no-repeat 174px 52%;
	    background-size: 6px 11px;
	    font-size: 14px;
	    color: #666;
	    line-height: 20px;
	    letter-spacing: -.3px;
	}
	#snb .link_inquire {
	    display: block;
	    overflow: hidden;
	    width: 200px;
	    height: 60px;
	    margin-top: 20px;
	    padding: 9px 0 0 21px;
	    border-radius: 0 100px 100px 0;
	    background: #fafafa url(https://res.kurly.com/pc/ico/2008/ico_arrow_6x11_on.svg) no-repeat 174px 25px;
	    background-size: 6px 11px;
	    font-size: 12px;
	    color: #333;
	    line-height: 20px;
	}
	#snb .link_inquire .emph {
	    display: block;
	    padding-bottom: 1px;
	    font-weight: 700;
	    font-size: 14px;
	}
	.page_aticle button, .page_aticle input, .page_aticle select, .page_aticle * {
	    font-family: noto sans;
	    font-weight: 400;
	    letter-spacing: 0;
	}
	img {
	    border: 0;
	}
	img, video, canvas {
	    max-width: 100%;
	}
	element.style {
	    background-image: url(//img-cf.kurly.com/shop/data/skin/designgj/img/banner/ae8763d….jpg);
	}
	.mypage_top .bnr_event {
	    display: block;
	    overflow: hidden;
	    width: 100%;
	    height: 60px;
	    margin-top: 20px;
	    background-size: 1050px 60px;
	    background-repeat: no-repeat;
	    background-position: 50% 50%;
	}
	.page_aticle button, .page_aticle input, .page_aticle select, .page_aticle * {
	    font-family: noto sans;
	    font-weight: 400;
	    letter-spacing: 0;
	}
	body a {
	    background-color: transparent;
	    text-decoration: none;
	    color: inherit;
	}
  </style>
  
</head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1">
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
<body>


<div id="content">
<div id="qnb" class="quick-navigation" style="top: 2856.18px;"></div>
<div id="myPageTop" class="page_aticle mypage_top">
	<div class="mypagetop_user">
		<div class="inner_mypagetop">
			<div class="grade_user">
				<div class="grade">
					<span class="screen_out">일반 등급</span> 
						<span class="ico_grade class0">
							<span class="inner_grade">
								<span class="in_grade">일반</span>
							</span>
						</span> 
					<div class="grade_bnenfit">
						<div class="user">
							<strong class="name">${sessionScope.loginfo.name}</strong> <span class="txt">님</span></div> 
								 <div class="benefit"><strong class="tit"></strong>적립 0.5%</div> <!---->
						 			<div class="benefit">
						 		</div>
						 	</div>
						 </div> 
						<!--  	<div class="next">
						 		<a href="/shop/event/lovers/lovers.php" class="total_grade">전체등급 보기</a> 
						 		<a href="/shop/proc/my_benefit.php?id=benefit" class="next_month">다음 달 예상등급 보기</a>
						 	</div> -->
						 </div> 
					<ul class="list_mypage">
						<li class="user_reserve">
							<div class="link">
								<div class="tit">적립금 <!----></div>
									 <a href="/shop/mypage/mypage_emoney.php" class="info">${sessionScope.loginfo.mpoint} 원
									 <img src="https://res.kurly.com/pc/service/common/1905/ico_arrow_56x56.png" alt="자세히 보기"> 
								 	<span class="date">소멸 예정 0 원</span></a>
								 </div>
						</li> 
						<li class="user_coupon">
							<div class="link">
								<div class="tit">쿠폰 <!----></div> 
									<a href="/shop/mypage/mypage_coupon.php" class="info"> 0 개 
									<img src="https://res.kurly.com/pc/service/common/1905/ico_arrow_56x56.png" alt="자세히 보기">
									</a>
								</div>
						</li> 
						<li class="user_kurlypass">
							<div class="link">
								<div class="tit">컬리패스</div> <!----> <a href="/shop/mypage/kurlypass.php" class="info info_link">
								  알아보기  <img src="https://res.kurly.com/pc/service/common/1905/ico_arrow_56x56.png" alt="자세히 보기"></a>
							</div>
						</li>
					</ul>
				</div> 
					<a href="https://www.kurly.com/shop/main/html.php?htmid=event/kurly.htm&amp;name=friend" class="bnr_event" style="background-image: url(&quot;//img-cf.kurly.com/shop/data/skin/designgj/img/banner/ae8763da478b227a3fdf0241c923f953.jpg&quot;);">
						<img src="https://res.kurly.com/pc/service/common/1904/bg_1050x60.png" alt="할인혜택보러가기">
					</a>
				</div>
				</div>

	<div class="page_aticle aticle_type2">
		<div id="snb" class="snb_my">
			<h2 class="tit_snb">마이컬리</h2>
		<div class="inner_snb">
			<ul class="list_menu">
				<li class="on">
				</li>
				<li>
						onclick="KurlyTracker.setAction('select_shipping_address_list').sendData();location.href = '/shop/mypage/destination/list.php';">
						배송지 관리
					</a>
				</li>
				<li>
				</li>
				<li>
				</li>
				<li>
				</li>
				<li>
					<a href="/shop/mypage/mypage_coupon.php">쿠폰 / x</a>
				</li>
			</ul>
		</div>
			<a href="/shop/mypage/mypage_qna_register.php?mode=add_qna" class="link_inquire">
				<span class="emph">도움이 필요하신가요 ?</span> 1:1 문의하기</a>
	</div>	
	
	<script src="/common_js/common_filter.js?ver=1.26.6"></script>
	<script src="/common_js/orderlist_v1.js?ver=1.26.6"></script>

<!-- </div>	
</div>
 -->

<!-- 다른 페이지 들어갈 공간. -->
</body>
</html>