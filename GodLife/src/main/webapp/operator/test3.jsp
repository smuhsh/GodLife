<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!doctype html>
<html lang="ko">
    <head>
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1"/>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        
        <title>이벤트 | 뉴스 | 메이플스토리</title>
        <link rel="stylesheet" href="https://ssl.nexon.com/s3/Game/Maplestory/sub_new.css" type="text/css"/>
        <link rel="stylesheet" href="https://ssl.nexon.com/s3/Game/Maplestory/jquery.mCustomScrollbar.css" type="text/css"/>
        <script src="https://ssl.nexon.com/s1/p2/ps.min.js" charset="utf-8" data-name="PS" data-ngm="true" data-nxlogin="true"></script>
        <script src="https://ssl.nexon.com/s1/global/ngb_head.js"></script>
        <script src="https://ssl.nexon.com/s1/Game/Maplestory/jquery-1.12.4.min.js" type="text/javascript"></script>
        <script src="https://ssl.nexon.com/s1/Game/Maplestory/jquery-ui.js"></script>
        <script src="https://ssl.nexon.com/s1/Game/Maplestory/sub_new.js" type="text/javascript"></script>
        <script src="https://ssl.nexon.com/s1/Game/Maplestory/jquery.mCustomScrollbar.concat.min.js" type="text/javascript"></script>
        <script src="/Scripts/Common/common.js?v=202206210312"></script>
        <script src="/Scripts/Common/webboard.js?v=202206210312"></script>
        
		<meta charset="EUC-KR">
		<title>이벤트 내용조회</title>
		
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
	     .menu {
		    margin-top: 80px;
		    display: -webkit-inline-box;
		    justify-content: space-between;
		    margin-left: -50px;
		}		     
		</style>
		<script type="text/javascript"/>
        <script>
            var COMMON = (function() {
                var instance;

                function initiate() {
                    return {
                        site: 'nope',
                        domain: 'maplestory.nexon.com',
                        url: 'https://maplestory.nexon.com/News/Event',
                        oid: '0',
                        isLogin: false,
                        isMembership: false,
                        isParentLogin: false,
                        isParentMembershipLogin: false,
                        isChannelingNotAgree: false,
                        articleSearchDescriptor: {},
                        maxTitleLength: 0,
                        maxCommentLength: 0
                    };
                }

                if (!instance)
                    instance = initiate();
                return instance;
            }());

            $(window).load(function() {
                var commonMessage = '';
                if (commonMessage !== '')
                    alert(commonMessage);
            });
        </script>
        <link rel="stylesheet" href="/Scripts/Common/font_NanumBarunGothic.css" type="text/css"/>
        <!-- Facebook Pixel Code -->
        <script type="text/javascript">
            !function(f, b, e, v, n, t, s) {
                if (f.fbq)
                    return;
                n = f.fbq = function() {
                    n.callMethod ? n.callMethod.apply(n, arguments) : n.queue.push(arguments)
                }
                ;
                if (!f._fbq)
                    f._fbq = n;
                n.push = n;
                n.loaded = !0;
                n.version = '2.0';
                n.queue = [];
                t = b.createElement(e);
                t.async = !0;
                t.src = v;
                s = b.getElementsByTagName(e)[0];
                s.parentNode.insertBefore(t, s)
            }(window, document, 'script', 'https://connect.facebook.net/en_US/fbevents.js');

            fbq('init', '268046720223120');
            fbq('track', "PageView");
        </script>
        <noscript>
            <img height="1" width="1" style="display:none" src="https://www.facebook.com/tr?id=268046720223120&ev=PageView&noscript=1"/>
        </noscript>
        <!-- End Facebook Pixel Code -->
        <!-- Twitter single-event website tag code -->
        <script src="//platform.twitter.com/oct.js" type="text/javascript"></script>
        <script type="text/javascript">
            twttr.conversion.trackPid('nx5i7', {
                tw_sale_amount: 0,
                tw_order_quantity: 0
            });
        </script>
        <noscript>
            <img height="1" width="1" style="display:none;" alt="" src="https://analytics.twitter.com/i/adsct?txn_id=nx5i7&p_id=Twitter&tw_sale_amount=0&tw_order_quantity=0"/>
            <img height="1" width="1" style="display:none;" alt="" src="//t.co/i/adsct?txn_id=nx5i7&p_id=Twitter&tw_sale_amount=0&tw_order_quantity=0"/>
        </noscript>
        <!-- End Twitter single-event website tag code -->
        <script type="text/javascript">
            (function(m, o, n, r, t, g) {
                t = t + '?l=' + n + '&t=' + r + '&ref=' + encodeURIComponent(o.referrer) + '&loc=' + encodeURIComponent(o.location) + '&r=' + Math.floor(Math.random() * 99999999999);
                o.write("<" + g + " src='" + t + "' style='display:none;'></" + g + ">");
            }
            )(window, document, 'CM_M_1042938', 'brand', 'https://rtb.clickmon.co.kr/RT_GATE/rt_gate.php', 'iframe');
        </script>
        <!-- Google Analytics -->
        <!-- Global site tag (gtag.js) - Google Analytics -->
        <script async src=https://www.googletagmanager.com/gtag/js?id= UA-34233427-4></script>
        <script async src=https://www.googletagmanager.com/gtag/js?id= AW-947932633></script>
        <script>
            window.dataLayer = window.dataLayer || [];
            function gtag() {
                dataLayer.push(arguments);
            }
            gtag('js', new Date());
            gtag('config', 'UA-34233427-4', {
                'custom_map': {
                    'dimension1': 'oid'
                }
            });
            gtag('config', 'G-12M3M6FCC2');
            gtag('event', 'oid', {
                'oid': 0
            })
            gtag('config', 'AW-947932633');
            gtag('event', 'conversion', {
                'send_to': 'AW-947932633/0AANCOGi_IkCENmbgcQD'
            });
            function gtag_report_conversion(url) {
                var callback = function() {
                    if (typeof (url) != 'undefined') {
                        window.location = url;
                    }
                };
                gtag('event', 'conversion', {
                    'send_to': 'AW-947932633/DKSpCOK-_IkCENmbgcQD',
                    'event_callback': callback
                });
                return false;
            }
        </script>
        <!-- End Google Analytics -->
        <!-- Kakao Pixel Code -->
        <script type="text/javascript" charset="UTF-8" src="//t1.daumcdn.net/adfit/static/kp.js"></script>
        <script type="text/javascript">
            kakaoPixel('2830029952607077079').pageView();
        </script>
        <!-- End Kakao Pixel Code -->
        <!-- Criteo 홈페이지 태그 -->
        <script type="text/javascript" src="//dynamic.criteo.com/js/ld/ld.js?a=94120" async="true"></script>
        <script type="text/javascript">
            window.criteo_q = window.criteo_q || [];
            var deviceType = /iPad/.test(navigator.userAgent) ? "t" : /Mobile|iP(hone|od)|Android|BlackBerry|IEMobile|Silk/.test(navigator.userAgent) ? "m" : "d";
            window.criteo_q.push({
                event: "setAccount",
                account: [94120, 95207]
            }, {
                event: "setSiteType",
                type: deviceType
            }, {
                event: "viewHome"
            });

            function gameStart_Criteo() {
                window.criteo_q.push({
                    event: "setAccount",
                    account: [94120, 95207]
                }, {
                    event: "setSiteType",
                    type: deviceType
                }, {
                    event: "trackTransaction",
                    id: Math.floor(Math.random() * 99999999999),
                    item: [{
                        id: "gameStart",
                        price: 1,
                        quantity: 1
                    }]
                });
            }
        </script>
        <!-- END Criteo 홈페이지 태그 -->
        <script>
            $(document).ready(function() {
                $('.word_input a').bind('click', function(e) {
                    e.preventDefault();

                    var search = $('.word_input input').val();
                    if (search === '') {
                        alert('검색어를 입력하세요');
                        return;
                    }
                    window.location.href = '/news/event?search=' + search;
                });
                $('.word_input input').bind('keydown', function(e) {
                    UTILITY.onEnterKeyDown(e.currentTarget, function() {
                        var search = $(e.currentTarget).val();
                        if (search === '') {
                            alert('검색어를 입력하세요');
                            return;
                        }
                        window.location.href = '/news/event?search=' + search;
                    });
                });
            });
            $(function() {
                board_sort_sel();
                ser_sel_cus();
            });
            $(window).load(function() {
                mnb_setting();
            });
        </script>
    </head>
    <body bgcolor="#ffffff" text="#000000">

	<!-- ToolBar Start /////////////////////////////////////-->
	<jsp:include page="/layout/toolbar.jsp" />
   	<!-- ToolBar End /////////////////////////////////////-->
	
	<!--  화면구성 div Start /////////////////////////////////////-->
	<div class="container">
		<div class="page-header text-info">
	       <h3>이벤트</h3>
	    </div>
    
        <script>
            $(document).ready(function() {
                $('.sel_login_btn a:eq(0)').bind('click', function(e) {
                    e.preventDefault();
                    LOGIN.changeID();
                });
                $('.sel_login_btn a:eq(1)').bind('click', function(e) {
                    e.preventDefault();
                    close_login_pop();
                });
            });
        </script>

        <div id="wrap">
            <!-- header str -->
            <!-- header end -->
            <!-- container str -->
            <div id="container">
                <!-- div_inner str -->
                <div class="div_inner">
                    <!-- notice_wrap str -->
                    <div class="contents_wrap">
                        <h1 class="con_title">
                            이벤트
                        
                            <div style="float:right;">
                                <span class="word_input" style="margin-top:0;">
                                    <input type="text" name="search_text" value=""/>
                                    <span class="btn">
                                        <a href="#a">
                                            <img src="https://ssl.nexon.com/s2/game/maplestory/renewal/common/board_top_search_btn.png" alt="">
                                        </a>
                                    </span>
                                </span>
                                <span class="sort_wrap fix_sort" style="margin-top:0;">
                                    <a href="#a" class="cus_sel_a">제목</a>
                                    <!--ul>
                                    <li><a href="#a">제목</a></li>
                                </!--ul>
                                <select name="search_type">
                                    <option value="t">제목</option>
                                </select-->
                                </span>
                            </div>
                        </h1>
                        <div class="new_tab_wrap">
                            <ul class="style03">
                                <li class=active>
                                    <!--  <a href="/News/Event/Ongoing">진행중인 이벤트</a>-->
                                    <a href="/operator/listOperatorEvents">진행중인 이벤트</a>
                                    <span class="tab_line"></span>
                                </li>
                                <li>
                                    <a href="/News/Event/Closed">종료된 이벤트</a>
                                    <span class="tab_line"></span>
                                </li>
                            </ul>
                        </div>
                        <div class="event_board">
                            <ul style=margin-top:auto>
                                <li>
                                    <div class="event_list_wrap">
                                        <dl>
                                            <dt>
                                                <a href="/operator/getOperatorEvents?eventNo=1">
                                                    <img src="/images/uploadFiles/operatorDayEvent.png" alt="종료된 이벤트 섬네일"/>
                                                </a>
                                            </dt>
                                            <dd class="data">
                                                <p>
                                                    <a href="/operator/getOperatorEvents?eventNo=1">매일출석 이벤트</a>
                                                </p>
                                            </dd>
                                            <dd class="date">
                                                <p>상시</p>
                                            </dd>
                                        </dl>
                                    </div>
                                </li>
                                <li>
                                    <div class="event_list_wrap">
                                        <dl>
                                            <dt>
                                                <a href="/operator/getOperatorEvents?eventNo=2">
                                                    <img src="/images/uploadFiles/operatorRoullEvent.png" alt="종료된 이벤트 섬네일"/>
                                                </a>
                                            </dt>
                                            <dd class="data">
                                                <p>
                                                    <a href="/operator/getOperatorEvents?eventNo=2">룰렛 이벤트</a>
                                                </p>
                                            </dd>
                                            <dd class="date">
                                                <p>상시</p>
                                            </dd>
                                        </dl>
                                    </div>
                                </li>
                                <li>
                                    <div class="event_list_wrap">
                                        <dl>
                                            <dt>
                                                <a href="/operator/getOperatorEvents?eventNo=0">
                                                    <img src="/images/uploadFiles/operatorNewEvent.png" alt="종료된 이벤트 섬네일"/>
                                                </a>
                                            </dt>
                                            <dd class="data">
                                                <p>
                                                    <a href="/operator/getOperatorEvents?eventNo=0" >신규회원 이벤트</a>
                                                </p>
                                            </dd>
                                            <dd class="date">
                                                <p>2022.05.10 ~ 2022.05.31</p>
                                            </dd>
                                        </dl>
                                    </div>
                                </li>

                            </ul>
                            <div class="page_numb">
                                <span class="cm_all_prev">
                                    <a>
                                        <img src="https://ssl.nexon.com/s2/game/maplestory/renewal/common/cm_all_prev.png" alt="이전 10개"/>
                                    </a>
                                </span>
                                <span class="cm_prev">
                                    <a>
                                        <img src="https://ssl.nexon.com/s2/game/maplestory/renewal/common/cm_prev.png" alt="이전"/>
                                    </a>
                                </span>
                                <a class="active">1</a>
                                <span class="cm_next">
                                    <a>
                                        <img src="https://ssl.nexon.com/s2/game/maplestory/renewal/common/cm_next.png" alt="다음"/>
                                    </a>
                                </span>
                                <span class="cm_all_next">
                                    <a>
                                        <img src="https://ssl.nexon.com/s2/game/maplestory/renewal/common/cm_all_next.png" alt="다음 10개"/>
                                    </a>
                                </span>
                            </div>
                        </div>
                    </div>
                    <!-- notice_wrap end -->

                <!-- div_inner end -->
            </div>
            <!-- container end -->
            <!-- footer str -->
            <script>
                $(document).ready(function() {
                    //$('.familysite_list a:eq(1)').click(function (e) {
                    //    e.preventDefault();
                    //    window.open($(e.currentTarget).attr('href'), 'mapleDS', 'toolbar=no,location=no,directories=no,status=no,menubar=no,scrollbars=yes,resizeable=no,copyhistory=no, width=1015, height=596');
                    //});
                    $('.footer_info ul a:eq(3)').click(function(e) {
                        e.preventDefault();
                        window.open($(e.currentTarget).attr('href'), $(e.currentTarget).text(), 'width=500, height=450; scrollbars=yes');
                    });
                    $('.licensee a').click(function(e) {
                        e.preventDefault();
                        window.open($(e.currentTarget).attr('href'), 'communicationViewPopup', 'width=750, height=700; scrollbars=yes');
                    });
                });
            </script>

            </div>
            <!-- footer end -->
        </div>
    </body>
</html>
