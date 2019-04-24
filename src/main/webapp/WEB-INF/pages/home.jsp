<%--
  Created by IntelliJ IDEA.
  User: iseungcheon
  Date: 2019. 4. 7.
  Time: PM 7:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
    <title>로그인 성공</title>
    <jsp:include page="header.jsp"/>
</head>
<body>

<c:if test="${isOk == 'true'}">
    <script>alert("로그인에 성공하였습니다.");</script>
</c:if>

${email}님 환영합니다.
<a href="/login/logout">로그아웃</a>

<jsp:include page="body.jsp"/>
<script>
    $(function(){
        console.log("Start");
        console.log("isOk : " + ${isOk});
    });

    function logut() {
        console.log("Click");
        lcoation.href("/login/logout");
    }
</script>
</body>
</html>
