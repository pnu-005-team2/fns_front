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
    <link rel="stylesheet" href="/resources/css/tagcloth.css" type="text/css"/>

</head>
<body style="background-color: #FEDEDB;">
<%--    <%out.println(request.getParameter("tempImg").toString());%>--%>
<div class="content-box">
    <div class="content">
        <form action="/board/timeline"  class="input-box" method="post" enctype="multipart/form-data" >
            <img src="data:image/png;base64, ${image}" alt="test-image" onclick="javascript:showCoords(event)"/>
            <br/>
            <p id="demo"></p>


            <input id="X" name = "X" type="hidden" value=444 />
            <input id="Y" name = "Y" type="hidden" value=1000 />
            <div class="info-text">사진에서 링크를 걸 위치를 클릭하고, 아래 입력창에 링크 주소를 입력해주세요</div>
            <label for="linktext">링크 입력</label><input id="linktext" name = "linktext" type="text"/>
            <script>

                var x,y;
                function showCoords(event) {
                    x = event.clientX;
                    y = event.clientY;
                    var coords = "X coords: " + x + ", Y coords: " + y;
                    document.getElementById("demo").innerHTML = coords;
                    $("#X").val(x);
                    $("#Y").val(y);
                    return false;
                }
            </script>
            <button type="submit">finished</button>
    </div>
        <jsp:include page="body.jsp"/>
    </form>
</div>
</body>
</html>
