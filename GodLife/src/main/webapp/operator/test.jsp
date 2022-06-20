<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>random number</title>
        <style>
            * {
                margin: 0;
                padding: 0;
            }
            body {
                position: relative;
                background-color: #ffeded;
            }
            .countBox {
                position: fixed;
                top: 50%;
                left: 50%;
                transform: translate(-50%,-50%);
                padding: 20px 50px;
                box-sizing: border-box;
                background-color: #ffaaaa;
                border-radius: 10px;
                box-shadow: 1px 1px 15px #ff9999;
                width: 100%;
                max-width: 360px;
                display: flex;
                flex-direction: column;
                justify-content: center;
                text-align: center;
            }
            h1 {
                color: #ffffff;
                font-size: 20px;
            }
            button {
                border: 0;
                background-color: transparent;
            }
            .countNumber {
                font-size: 50px;
                font-weight: bold;
                color: white;
                margin: 10px 0;
            }
            .countButtonWrap > button {
                color: white;
                font-weight: bold;
                border-radius: 5px;
                background-color: #ff8888;
                padding: 5px 10px;
            }
            .countButtonWrap > button:hover {
                color: #ff8888;
                background-color: white;
                transition: all ease 0.3s
            }
        </style>
    </head>
    <body>
        <div class="countBox">
            <h1>1~10 랜덤 뽑기</h1>
            <p class="countNumber">0</p>
            <div class="countButtonWrap">
                <button class="generate">뽑기</button>
            </div>
        </div>
        <script>
            let generateNum = document.querySelector(".countNumber");
            let button = document.querySelector(".generate");

            function generator(){
                const randomNum = Math.floor(Math.random() * 10 + 1);
                //Math.random 0~1 사이의 난수 생성
                //Math.floor 소수점을 내림시켜 정수로 만듦
                generateNum.innerHTML = randomNum;
            }

            button.addEventListener("click", generator);
        </script>
    </body>