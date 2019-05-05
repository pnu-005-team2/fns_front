<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: iseungcheon
  Date: 2019. 5. 5.
  Time: PM 6:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<jsp:include page="body.jsp"/>
<jsp:include page="Post.jsp"/>
    <div class="Time-Line-Box">
        <c:forEach var="item" items="${postRecordlList}" step="2">
            <div class="board-item">
                <div class="writer" >${item.writer}</div>
                <div class="board-img" >
                 <img width="250" height="250"
                    src="/logoShowForStudent/${item.pid}">
                </div>
                <div class="writer" >${item.writer}</div>
            </div>
        </c:forEach>
    </div>
</body>
</html>
