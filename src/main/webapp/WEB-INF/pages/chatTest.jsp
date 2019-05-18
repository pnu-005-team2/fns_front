<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.team2.webservice.sprint1.vo.ChatRoom" %>
<%@ page import="com.team2.webservice.sprint1.vo.Member" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Chattting</title>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>

</head>
<body>
<%
    Member user = (Member)session.getAttribute("login");
    String name = user.getName();
%>

<h1>${chatRoom.name}</h1>

<textarea id="chatOutput" name = "" class="chat-history" rows="24" style="width: 20%"></textarea>
<div class="chat-input">
    <input id="chatInput" type="text" class="chat">
</div>
<button id = "exit-room" onclick="exitChatRoom()">Exit</button>
<%--채팅을 위한 Js 코드--%>
<script src="/resources/js/socket.js"></script>
<script>
    window.addEventListener("load", ()=>{
        var userName = "<%=name%>";
        console.log(WebSocket)
        console.log("${chatRoom.messages}")
        <c:forEach var="item" items="${chatRoom.messages}" >
            $("#chatOutput").append('${item.member.name} : ' + '${item.content}' +'\n');
        </c:forEach>
        WebSocket.init(${chatRoom.cid}, ${chatRoom.members.size()-1}, userName);

    });

    function exitChatRoom() {
        WebSocket.sendEixt();
    }
</script>
</body>
</html>