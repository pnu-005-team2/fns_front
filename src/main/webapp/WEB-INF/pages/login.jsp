<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<head>
    <meta charset="UTF-8">
    <title>Calm breeze login screen</title>
    <link rel="stylesheet" href="/resources/css/login.css" type="text/css"/>

</head>

<body>
<%--<jsp:include page="header.jsp"/>--%>
<jsp:include page="body.jsp"/>


<c:if test="${isOk == 'false'}">
     <script>alert("일치하는 계정정보가 존재하지 않습니다.")</script>
</c:if>

<div class="wrapper">
    <div class="container">
        <h1>Welcome
            <span style = "color:yellow">FNS</span></h1>
        <form class="form" method="POST" action="/login/">
            <input id="email" type="email" name="memberEmail" placeholder="Email"/>
            <input id="password" type="password" name="memberPw" placeholder="Password"/>
            <%--<input class="login-btn waves-effect waves-light btn" type="submit" value="로그인" />--%>
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
<jsp:include page="body.jsp"/>
<script>
    $(function(){
        console.log("Start");
        console.log("isOk : ${isOk}");
    });


</script>
