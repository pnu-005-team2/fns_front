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
    <img src="data:image/png;base64, ${image}" alt="test-image" width="250" height="250" onclick="javascript:showCoords(event)"/>
    <br/>
    <p id="demo"></p>

    <input id="X" name = "X" type="hidden"/>
    <input id="Y" name = "Y" type="hidden"/>
    <script>
        function showCoords(event) {
            var x = event.clientX;
            var y = event.clientY;
            var coords = "X coords: " + x + ", Y coords: " + y;
            document.getElementById("demo").innerHTML = coords;
            $("#X").val(x);
            $("#Y").val(y);
        }
    </script>
    <button type="submit">finished</button>
</form>
</body>
</html>
