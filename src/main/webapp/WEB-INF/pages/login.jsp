<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Calm breeze login screen</title>
    <link rel="stylesheet" href="/resources/css/login.css" type="text/css"/>
    <jsp:include page="body.jsp"></jsp:include>
</head>

<body style="background-color: #FF6F61">


<c:if test="${isOk == 'false'}">
     <script>alert("일치하는 계정정보가 존재하지 않습니다.")</script>
</c:if>

<div class="wrapper">
    <div class="container">
        <div class="logo">
            <%--<img src="https://github.com/pnu-005-team2/fns_front/blob/master/src/main/resources/static/images/KakaoTalk_20190615_011550078.png?raw=true"/>--%>
            <img src="/resources/images/KakaoTalk_20190615_011550078.png?raw=true"/>
        </div>
        <h1 style="color: white">Welcome
            <span style = "color:yellow"> FNS</span>
        </h1>
        <form class="form" method="POST" action="/login">
            <input id="email" type="email" name="memberEmail" placeholder="Email"/>
            <input id="password" type="password" name="memberPw" placeholder="Password"/>
            <button type="button" id="join-button">Join</button>
            <button type="submit" id="login-button">Login</button>
        </form>
    </div>

    <ul class="bg-bubbles">
        <li></li>
        <li></li>
        <li></li>
        <li></li>
    </ul>
</div>
<script>
    $(function(){
        console.log("Start");
        console.log("isOk : ${isOk}");
    });

    $("#join-button").click(function () {
        console.log("조인 버튼 클릭");
        location.href = '/join/register';
    })

</script>
</body>
</html>

