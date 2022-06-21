<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
	<meta charset="EUC-KR">
	<title>매일출석 이벤트</title>

	<meta name="viewport" content="width=device-width, initial-scale=1.0" />
		
	<!--   jQuery , Bootstrap CDN  -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" >
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" >
	<link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
	<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	<script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" ></script>
	
	<!-- Bootstrap Dropdown Hover CSS -->
   <link href="/css/animate.min.css" rel="stylesheet">
   <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
   
    <!-- Bootstrap Dropdown Hover JS -->
   <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
	
	<style>
	  .container {
            padding-top : 150px;
        }
        
        .sec_cal {
		    width: 360px;
		    margin: 0 auto;
		    font-family: "NotoSansR";
		}
		
		.sec_cal .cal_nav {
		    display: flex;
		    justify-content: center;
		    align-items: center;
		    font-weight: 700;
		    font-size: 48px;
		    line-height: 78px;
		}
		
		.sec_cal .cal_nav .year-month {
		    width: 300px;
		    text-align: center;
		    line-height: 1;
		}
		
		.sec_cal .cal_nav .nav {
		    display: flex;
		    border: 1px solid #333333;
		    border-radius: 5px;
		}
		
		.sec_cal .cal_nav .go-prev,
		.sec_cal .cal_nav .go-next {
		    display: block;
		    width: 50px;
		    height: 78px;
		    font-size: 0;
		    display: flex;
		    justify-content: center;
		    align-items: center;
		}
		
		.sec_cal .cal_nav .go-prev::before,
		.sec_cal .cal_nav .go-next::before {
		    content: "";
		    display: block;
		    width: 20px;
		    height: 20px;
		    border: 3px solid #000;
		    border-width: 3px 3px 0 0;
		    transition: border 0.1s;
		}
		
		.sec_cal .cal_nav .go-prev:hover::before,
		.sec_cal .cal_nav .go-next:hover::before {
		    border-color: #ed2a61;
		}
		
		.sec_cal .cal_nav .go-prev::before {
		    transform: rotate(-135deg);
		}
		
		.sec_cal .cal_nav .go-next::before {
		    transform: rotate(45deg);
		}
		
		.sec_cal .cal_wrap {
		    padding-top: 40px;
		    position: relative;
		    margin: 0 auto;
		}
		
		.sec_cal .cal_wrap .days {
		    display: flex;
		    margin-bottom: 20px;
		    padding-bottom: 20px;
		    border-bottom: 1px solid #ddd;
		}
		
		.sec_cal .cal_wrap::after {
		    top: 368px;
		}
		
		.sec_cal .cal_wrap .day {
		    display:flex;
		    align-items: center;
		    justify-content: center;
		    width: calc(100% / 7);
		    text-align: left;
		    color: #999;
		    font-size: 12px;
		    text-align: center;
		    border-radius:5px
		}
		
		.current.today {background: rgb(242 242 242);}
		
		.sec_cal .cal_wrap .dates {
		    display: flex;
		    flex-flow: wrap;
		    height: 290px;
		}
		
		.sec_cal .cal_wrap .day:nth-child(7n -1) {
		    color: #3c6ffa;
		}
		
		.sec_cal .cal_wrap .day:nth-child(7n) {
		    color: #ed2a61;
		}
		
		.sec_cal .cal_wrap .day.disable {
		    color: #ddd;
		}
		        
    </style>
    
	<script type="text/javascript">
		
		 $(function() {
			 $( "td.ct_btn01:contains('출석')" ).on("click" , function() {
				self.location = "/operator/addOperatorDayEvent.jsp"
			});
			
			$( "td.ct_btn01:contains('목록')" ).on("click" , function() {
				self.location = "/operator/listOperatorEvents"
			});
			
			//$( "td.ct_btn01:contains('삭제')" ).on("click" , function() {
			//	self.location = "/operator/deleteOperatorNotice?noticeFaqNo=${NoticeFaqs.noticeFaqNo}
			//});
		});
		 
		 $(document).ready(function() {
			    calendarInit();
			});
			/*
			    달력 렌더링 할 때 필요한 정보 목록 

			    현재 월(초기값 : 현재 시간)
			    금월 마지막일 날짜와 요일
			    전월 마지막일 날짜와 요일
			*/

			function calendarInit() {

			    // 날짜 정보 가져오기
			    var date = new Date(); // 현재 날짜(로컬 기준) 가져오기
			    var utc = date.getTime() + (date.getTimezoneOffset() * 60 * 1000); // uct 표준시 도출
			    var kstGap = 9 * 60 * 60 * 1000; // 한국 kst 기준시간 더하기
			    var today = new Date(utc + kstGap); // 한국 시간으로 date 객체 만들기(오늘)
			  
			    var thisMonth = new Date(today.getFullYear(), today.getMonth(), today.getDate());
			    // 달력에서 표기하는 날짜 객체
			  
			    
			    var currentYear = thisMonth.getFullYear(); // 달력에서 표기하는 연
			    var currentMonth = thisMonth.getMonth(); // 달력에서 표기하는 월
			    var currentDate = thisMonth.getDate(); // 달력에서 표기하는 일

			    // kst 기준 현재시간
			    // console.log(thisMonth);

			    // 캘린더 렌더링
			    renderCalender(thisMonth);

			    function renderCalender(thisMonth) {

			        // 렌더링을 위한 데이터 정리
			        currentYear = thisMonth.getFullYear();
			        currentMonth = thisMonth.getMonth();
			        currentDate = thisMonth.getDate();

			        // 이전 달의 마지막 날 날짜와 요일 구하기
			        var startDay = new Date(currentYear, currentMonth, 0);
			        var prevDate = startDay.getDate();
			        var prevDay = startDay.getDay();

			        // 이번 달의 마지막날 날짜와 요일 구하기
			        var endDay = new Date(currentYear, currentMonth + 1, 0);
			        var nextDate = endDay.getDate();
			        var nextDay = endDay.getDay();

			        // console.log(prevDate, prevDay, nextDate, nextDay);

			        // 현재 월 표기
			        $('.year-month').text(currentYear + '.' + (currentMonth + 1));

			        // 렌더링 html 요소 생성
			        calendar = document.querySelector('.dates')
			        calendar.innerHTML = '';
			        
			        // 지난달
			        for (var i = prevDate - prevDay + 1; i <= prevDate; i++) {
			            calendar.innerHTML = calendar.innerHTML + '<div class="day prev disable">' + i + '</div>'
			        }
			        // 이번달
			        for (var i = 1; i <= nextDate; i++) {
			            calendar.innerHTML = calendar.innerHTML + '<div class="day current">' + i + '</div>'
			        }
			        // 다음달
			        for (var i = 1; i <= (7 - nextDay == 7 ? 0 : 7 - nextDay); i++) {
			            calendar.innerHTML = calendar.innerHTML + '<div class="day next disable">' + i + '</div>'
			        }

			        // 오늘 날짜 표기
			        if (today.getMonth() == currentMonth) {
			            todayDate = today.getDate();
			            var currentMonthDate = document.querySelectorAll('.dates .current');
			            currentMonthDate[todayDate -1].classList.add('today');
			        }
			    }

			    // 이전달로 이동
			    $('.go-prev').on('click', function() {
			        thisMonth = new Date(currentYear, currentMonth - 1, 1);
			        renderCalender(thisMonth);
			    });

			    // 다음달로 이동
			    $('.go-next').on('click', function() {
			        thisMonth = new Date(currentYear, currentMonth + 1, 1);
			        renderCalender(thisMonth); 
			    });
			} 
		 
	</script>
	
</head>

<body bgcolor="#ffffff" text="#000000">

	<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->
	
	<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
		<div class="page-header text-info">
	       <h3>매일출석 이벤트</h3>
	    </div>
	    
	    <!-- table 위쪽 검색 Start /////////////////////////////////////-->
	    <div class="row">
	    
		    <div class="col-md-6 text-left">
		    	<p class="text-primary">
		    	</p>
		    </div>
		    
		    <div class="col-md-6 text-right">
		    
	    	</div>
	    	
		</div>
		<!-- table 위쪽 검색 Start /////////////////////////////////////-->

<form name="detailForm" method="post">

<table width="100%" height="37" border="0" cellpadding="0"	cellspacing="0">
	<tr>
		<td width="15" height="37"><img src="/images/ct_ttl_img01.gif"	width="15" height="37"></td>
		<td background="/images/ct_ttl_img02.gif" width="100%" style="padding-left: 10px;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="93%" class="ct_ttl01">매일출석체크하면 행운이!</td>
					<td width="20%" align="right">&nbsp;</td>
				</tr>
			</table>
		</td>
		<td width="12" height="37">
			<img src="/images/ct_ttl_img03.gif"  width="12" height="37"/>
		</td>
	</tr>
</table>

	<div class="sec_cal">
			<div class="cal_nav">
				<a href="javascript:;" class="nav-btn go-prev">prev</a>
	    	<div class="year-month"></div>
	    		<a href="javascript:;" class="nav-btn go-next">next</a>
	  		</div>
  			<div class="cal_wrap">
    		<div class="days">
				<div class="day">MON</div>
				<div class="day">TUE</div>
				<div class="day">WED</div>
				<div class="day">THU</div>
				<div class="day">FRI</div>
				<div class="day">SAT</div>
				<div class="day">SUN</div>
			</div>
				<div class="dates"></div>
			</div>
	</div>











<table width="100%" border="0" cellspacing="0" cellpadding="0"	style="margin-top: 10px;">
	<tr>
		<td width="53%"></td>
		<td align="right">

		<table border="0" cellspacing="0" cellpadding="0">
			<tr>
		
				<td width="17" height="23">
					<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
				</td>
				<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
					 출석			
				</td>
				<td width="14" height="23">
					<img src="/images/ct_btnbg03.gif" width="14" height="23">
				</td>
				<td width="30"></td>	
				
				<td width="17" height="23">
					<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
				</td>
				<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
					목록
				</td>
				<td width="14" height="23">
					<img src="/images/ct_btnbg03.gif" width="14" height="23">
				</td>
				<td width="30"></td>
				
				
				<!--  <td width="17" height="23">
					<img src="/images/ct_btnbg01.gif" width="17" height="23"/>
				</td>
				<td background="/images/ct_btnbg02.gif" class="ct_btn01" style="padding-top: 3px;">
					삭제
				</td>
				<td width="14" height="23">
					<img src="/images/ct_btnbg03.gif" width="14" height="23">-->
				</td>
			</tr>
		</table>

		</td>
	</tr>
</table>
</form>

</body>
</html>