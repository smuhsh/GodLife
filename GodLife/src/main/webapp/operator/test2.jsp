<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <title>룰렛 이벤트</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
        <!--   jQuery , Bootstrap CDN  -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css">
        <link rel="stylesheet" href="//code.jquery.com/ui/1.13.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
        <script src="https://code.jquery.com/ui/1.13.1/jquery-ui.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <!-- Bootstrap Dropdown Hover CSS -->
        <link href="/css/animate.min.css" rel="stylesheet">
        <link href="/css/bootstrap-dropdownhover.min.css" rel="stylesheet">
        <!-- Bootstrap Dropdown Hover JS -->
        <script src="/javascript/bootstrap-dropdownhover.min.js"></script>
        <script src="http://cdnjs.cloudflare.com/ajax/libs/gsap/1.18.0/TweenMax.min.js"></script>
        <style>
       	  .container {
	            padding-top : 150px;
        	}
        
            @font-face {
                font-family: 'GmarketSansMedium';
                src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/GmarketSansMedium.woff') format('woff');
                font-weight: normal;
                font-style: normal;
            }

            #wrap {
                background-color: #ffffff;
                position: relative;
                overflow: hidden;
            }

            #wrap #gameContainer {
                width: 508px;
                height: 671px;
                text-align: center;
                position: relative;
                margin: 0px auto;
            }

            #wrap #gameContainer h1 {
            }

            #wrap #gameContainer .obj {
                position: absolute;
            }

            #wrap #gameContainer .board_start {
                padding: 10px;
                background: #bfff00;
                text-align: center;
                vertical-align: middle;
                line-height: 150px;
                border: 1px solid #000;
                color: #000080;
                background: #bfff00;
                text-align: center;
                vertical-align: middle;
                font-weight: bold;
                position: absolute;
                left: 165px;
                top: 210px;
                z-index: 9;
                width: 150px;
                border-radius: 100px;
            }

            #wrap #gameContainer .board_start img {
                width: 100%;
            }

            #wrap #gameContainer .board_bg {
                width: 508px;
                height: 508px;
                top: 40px;
                left: 0px;
                z-index: 2;
            }

            #wrap #gameContainer .board_bg img {
                width: 100%;
            }

            #wrap #gameContainer .board_on {
                width: 508px;
                height: 508px;
                top: 40px;
                left: 0px;
                z-index: 3;
                background-image: url('//img.babathe.com/upload/specialDisplay/htmlImage/2019/test/roulette_circle_bg.png');
                background-size: 508px;
            }

            #wrap #gameContainer .board_on img {
                position: absolute;
                width: 100px;
                top: 115px;
                height: 100px;
            }

            #wrap #gameContainer .board_on img:nth-child(1) {
                left: 55px;
                transform: rotate( -60deg );
                top: 120px;
            }

            #wrap #gameContainer .board_on img:nth-child(2) {
                left: 62px;
                transform: rotate( -113deg );
                top: 286px;
            }

            #wrap #gameContainer .board_on img:nth-child(3) {
                left: 208px;
                top: 372px;
                transform: rotate(-180deg);
            }

            #wrap #gameContainer .board_on img:nth-child(4) {
                left: 351px;
                top: 286px;
                transform: rotate(-245deg);
            }

            #wrap #gameContainer .board_on img:nth-child(5) {
                left: 351px;
                top: 125px;
                transform: rotate(-292deg);
            }

            #wrap #gameContainer .board_on img:nth-child(6) {
                left: 205px;
                top: 42px;
                transform: rotate(5deg);
            }

            #wrap #gameContainer .obj.board_arrow {
                width: 90px;
                height: 105px;
                top: 0px;
                left: 195px;
                z-index: 5;
            }

            #wrap #gameContainer .char1 {
                width: 259px;
                height: 246px;
                top: 400px;
                left: 90px;
                z-index: 1;
            }

            #wrap #gameContainer .char2 {
                width: 175px;
                height: 198px;
                top: 449px;
                left: 600px;
                z-index: 1;
            }

            #wrap #gameContainer .char3 {
                width: 112px;
                height: 108px;
                top: 540px;
                left: 550px;
                z-index: 3;
            }

            #wrap #gameContainer .txt1 {
                width: 420px;
                height: 30px;
                bottom: 20px;
                left: 260px;
                z-index: 3;
            }

            .popup {
                display: none;
                position: fixed;
                left: 50%;
                z-index: 99999;
                text-align: center;
                background: #fff;
                font-size: 20px;
                color: #000;
            }

            #fade {
                display: none;
                background: #000;
                position: fixed;
                left: 0;
                top: 0;
                width: 100%;
                height: 100%;
                opacity: .60;
                z-index: 9999;
            }

            #popup_gift {
                width: 475px;
                height: 505px;
                top: 120px;
            }

            #popup_gift .lottery_present {
                text-align: center;
            }

            #popup_gift .lottery_present img {
                display: block;
                margin: 50px auto 30px;
            }

            #popup_fail {
                width: 475px;
                height: 505px;
                top: 120px;
            }

            .popup .close {
                display: inline-block;
                width: 150px;
                hegiht: 50px;
                line-height: 50px;
                color: #fff;
                background: #333;
                margin: 30px 0 0;
            }
        </style>
        <script type="text/javascript">
            $(document).ready(function() {
                var gift;
                var present = [1000, 1000, 1000, 3000, 5000, 10000]

                iniGame = function(num) {
                    gift = num;
                    TweenLite.killTweensOf($(".board_on"));
                    TweenLite.to($(".board_on"), 0, {
                        css: {
                            rotation: rotationPos[gift]
                        }
                    });
                    TweenLite.from($(".board_on"), 5, {
                        css: {
                            rotation: -3000
                        },
                        onComplete: endGame,
                        ease: Sine.easeOut
                    });
                    console.log("gift 숫자 : " + (gift + 1) + "rotationPos" + rotationPos);
                }

                var rotationPos = new Array(60,120,180,240,300,360);

                TweenLite.to($(".board_on"), 360, {
                    css: {
                        rotation: -4000
                    },
                    ease: Linear.easeNone
                });
                function endGame() {
                    var copImg = "http://img.babathe.com/upload/specialDisplay/htmlImage/2019/test/coupon" + (gift + 1) + ".png";
                    console.log("이미지 : " + copImg);

                    $("#popup_gift .lottery_present").text(function() {
                        return "축하드립니다." + present[gift] + " 룰렛숫장" + (gift + 1) + " 당첨 되셨습니다.";
                    });
                    $('<img  src="' + copImg + '" />').prependTo("#popup_gift .lottery_present");
                    setTimeout(function() {
                        openPopup("popup_gift");
                    }, 1000);
                }

                $(".popup .btn").on("click", function() {
                    location.reload();
                });
                function openPopup(id) {
                    closePopup();
                    $('.popup').slideUp(0);
                    var popupid = id
                    $('body').append('<div id="fade"></div>');
                    $('#fade').css({
                        'filter': 'alpha(opacity=60)'
                    }).fadeIn(300);
                    var popuptopmargin = ($('#' + popupid).height()) / 2;
                    var popupleftmargin = ($('#' + popupid).width()) / 2;
                    $('#' + popupid).css({
                        'margin-left': -popupleftmargin
                    });
                    $('#' + popupid).slideDown(500);
                }
                function closePopup() {
                    $('#fade').fadeOut(300, function() {// $(".player").html('');
                    });
                    $('.popup').slideUp(400);
                    return false
                }
                $(".close").click(closePopup);

            });

            $(function() {
                var clicked = 0;
                for (i = 1; i < 7; i++) {
                    // 상품쪽 이미지는 신경 안쓰셔도 됩니다!! 책임님!!!
                    var pictures = "http://img.babathe.com/upload/specialDisplay/htmlImage/2019/test/coupon" + i + ".png";
                    $(".board_on").append('<img  src="' + pictures + '" />');
                }

                $(".join").on("mousedown", function() {
                    if (clicked <= 0) {
                        iniGame(Math.floor(Math.random() * 6));
                    } else if (clicked >= 1) {
                        event.preventDefault();
                        alert("내일 다시 참여해주세요.");
                    }
                    clicked++
                });
            })
        </script>
	<body bgcolor="#ffffff" text="#000000">
		<jsp:include page="/layout/toolbar.jsp" />	
		<div class="container">
			<div class="page-header text-info">
		       <h3>룰렛 이벤트</h3>
		</div>
        <div id="wrap">
            <div id="gameContainer">
                <div class="board_start join">시작버튼</div>
                <div class="board_on obj"></div>
            </div>
            <div id="popup_gift" class="popup">
                <div class="lottery_present"></div>
                <a href="javascript:;" class="close">닫기 </a>
            </div>
        </div>
    </body>
</html>

