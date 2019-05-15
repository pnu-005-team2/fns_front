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
<h1>{{room.name}}({{room.id}})</h1>

<textarea id="chatOutput" name = "" class="chat-history" rows="24"></textarea>
<div class="chat-input">
    <input id="chatInput" type="text" class="chat">
</div>
<button id = "exit-room">Exit</button>
<%--채팅을 위한 Js 코드--%>
<script src="/resources/js/socket.js"></script>
</body>
</html>