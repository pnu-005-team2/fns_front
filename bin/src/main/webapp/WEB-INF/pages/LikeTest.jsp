<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
    <c:forEach var="item" items="${LikeList}">
        ${item.lid} &nbsp;
        ${item.pid} &nbsp;
        ${item.uid} &nbsp;
    </c:forEach>
    µÇ¶ó!
</body>
</html>