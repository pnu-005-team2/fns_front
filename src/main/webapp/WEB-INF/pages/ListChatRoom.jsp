<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: iseungcheon
  Date: 2019. 5. 16.
  Time: PM 7:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Chat List</title>
</head>
<body>
<c:forEach var="item" items="${ChatList}" >
    <div onclick="location.href = 'chat?cid=${item.cid}'">
        <div>${item.name}</div>
        <%--<div>${item.messages.get(0).content}</div>--%>
    </div>
    <a href=""></a>
</c:forEach>
</body>
</html>
