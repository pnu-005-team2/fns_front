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
    <title>postedtest</title>
</head>
<body>
<h1>image upload test</h1>
<%--    <%out.println(request.getParameter("tempImg").toString());%>--%>
    <img src="<%request.getParameter("tempImg");%>" alt="test-image" sizes="900" />

</body>
</html>
