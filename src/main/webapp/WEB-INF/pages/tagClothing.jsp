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
<form action="./tagClothingFin" method="post" enctype="multipart/form-data" >
    <img src="data:image/png;base64, ${image}" alt="test-image" width="250" height="250" onclick="javascript:showCoords(event)"/>
    <br/>
    <p id="demo"></p>

<%--    <h1>test</h1>--%>
<%--    <p id="test"></p>--%>
<%--    <p id="test2"></p>--%>


    <script>

        console.log("tagClothing script start");
        var x,y;
        function showCoords(event) {
            x = event.clientX;
            y = event.clientY;
            var coords = "X coords: " + x + ", Y coords: " + y;
            document.getElementById("demo").innerHTML = coords;
            // $("#X").val(x);
            // $("#Y").val(y);

            //test
            // var testText = "test X:" + x + ", test Y: " + y;
            // document.getElementById("test").innerHTML = testText;
            // var text1 = "test fin";
            // document.getElementById("test2").innerHTML = text1;
            return false;
        }
    </script>

    <input id="X" name = "X" type="hidden" value=444 />
    <input id="Y" name = "Y" type="hidden" value=444 />

    <input id="linktext" name = "linktext" type="text"/>
    <button type="submit">finished</button>
</form>
</body>
</html>
