<%--
  Created by IntelliJ IDEA.
  User: iyeeun
  Date: 2019-04-08
  Time: 02:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Tag on your clothing information</title>
</head>
<body>
<h1>Tag on your clothing information</h1>
<%--    <%out.println(request.getParameter("tempImg").toString());%>--%>
<form action="/timeline" method="post" enctype="multipart/form-data">
    <img src="<%request.getParameter("tempImg");%>" alt="test-image" sizes="900" />
    <br/>
    <button type="submit">finished</button>
</form>
</body>
</html>
