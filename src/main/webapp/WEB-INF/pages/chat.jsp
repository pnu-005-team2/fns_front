<%@ page import="com.team2.webservice.sprint1.vo.Member" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <title>Chat</title>
    <link rel="stylesheet" href="/resources/css/chat.css">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.css">
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/malihu-custom-scrollbar-plugin/3.1.5/jquery.mCustomScrollbar.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/stomp.js/2.3.3/stomp.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"
            integrity="sha256-FgpCb/KJQlLNfOu91ta32o/NMZxltwRo8QtmkMRdAu8="
            crossorigin="anonymous"></script>
</head>
<body>
<jsp:include page="body.jsp"/>
<%
    Member user = (Member)session.getAttribute("login");
    int uid = user.getUid();
    String name = user.getName();
    String img =user.getImg();
%>
<jsp:include page="header.jsp"/>
<div class="container-fluid h-100">
    <div class="row justify-content-center h-100">
        <div class="col-md-4 col-xl-3 chat"><div class="card mb-sm-3 mb-md-0 contacts_card">
            <div class="card-header">
                <li onclick="searchFriends()" class="pnt" data-toggle="modal" data-target="#myModal" style="
                        color: white;"><i class="fas fa-plus" ></i> Create Chat Room</li>
            </div>
            <div class="card-body contacts_body">
                <ui class="contacts">
                    <c:forEach var="room" items="${RoomList}" begin="1" step="1">
                    <li class="ToActive" id = ${room.cid}>
                        <div class="d-flex bd-highlight">
                            <div class="img_cont">
                                <img src= "${room.messages[room.messages.size()-1].member.img}" class="rounded-circle user_img">
                                <span class="online_icon"></span>
                            </div>
                            <div class="user_info">
                                <span>${room.name}</span>
                                <p>${room.messages[room.messages.size()-1].content}</p>
                            </div>
                        </div>
                    </li>
                    </c:forEach>
                </ui>
            </div>
            <div class="card-footer"></div>
        </div></div>
        <div class="col-md-8 col-xl-6 chat">
            <div class="card" id="chat">
                <div class="card-header msg_head">
                    <div class="d-flex bd-highlight">
                        <div class="img_cont">
                            <img src="" class="rounded-circle user_img" id ='chatroom_img'>
                            <span class="online_icon"></span>
                        </div>
                        <div id="user_info">
                            <span id = 'chatroom_name'>hw</span>
                        </div>
                    </div>
                    <span id="action_menu_btn"><i class="fas fa-ellipsis-v"></i></span>
                    <div class="action_menu">
                        <ul>
                            <li onclick ="exitChatRoom()"><i class="fas fa-ban"></i> Exit Chat Room</li>
                        </ul>
                    </div>
                </div>
                <div id = "chatOutput" class="card-body msg_card_body"></div>
                <div class="card-footer">
                    <div class="input-group">
                        <input name="" id ="chatInput" class="form-control type_msg chat" placeholder="Type your message..."></input>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <input type ="text" name = "roomName" value ="input roomname" />
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                </div>
                <div class="modal-body" id="modal_body">
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button onclick="createChatRoom()" type="button" class="btn btn-primary">Save changes</button>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/resources/js/socket.js"></script>
<script>
    var userName = "<%=name%>";
    var activeChatroom;
    $(document).ready(function(){
        $('#action_menu_btn').click(function(){
            $('.action_menu').toggle();
        });
    });

    window.onload = function () {
        if (window.Notification) {
            Notification.requestPermission();
        }
    };

    $(document).ready(function(){
        $('.ToActive').click(function(){
            WebSocket.close();
            $('#chat').css("visibility","visible");
            $("#chatOutput").empty();
            $('.active').removeClass('active').addClass('ToActive');
            $(this).removeClass('ToActive').addClass('active');
            activeChatroom = $(this).attr('id');
            $.ajax({
                type : "POST",
                url : "/chatRoom",
                data : {"chatRoomId":$(this).attr('id')},
                success: function (data) {
                    for(let i = 0 ; i<data.messages.length ; ++i){
                        if(data.messages[i].member.name === userName){
                            $("#chatOutput").append('<div class="d-flex justify-content-end mb-4">'+
                                '<div class="msg_cotainer_send">'+ data.messages[i].content+
                                '</div>'+
                                '<div class="img_cont_msg">'+
                                '<img src="'+data.messages[i].member.img+'"class="rounded-circle user_img_msg">'+
                                '</div>'+
                                '</div>');
                        }
                        else{
                            $("#chatOutput").append('<div class="d-flex justify-content-start mb-4">' +
                                '<div class="img_cont_msg">' +
                                '<img src="'+data.messages[i].member.img+'" class="rounded-circle user_img_msg">' +
                                '</div>' +
                                '<div class="msg_cotainer">' +
                                data.messages[i].content +
                                '</div>' +
                                '</div>')
                        }
                    }
                    $("#chatOutput").scrollTop($("#chatOutput")[0].scrollHeight);

                    $("#chatroom_name").empty();
                    $("#chatroom_name").append(data.name);

                    $("#chatroom_img").attr("src",data.messages[data.messages.length-1].member.img);
                    WebSocket.init(data.cid,data.members.length-1, userName);
                }
            });
        });
    });
    // --------- 채팅방을 만듭니다. ---------
    function createChatRoom() {
        let user = [];
        user.push(userName);
        $("input[name='friends']:checked").each(function(i) {
            user.push($(this).val());
        });
        console.log(user);
        let title_room = $("input[name='roomName']").val();
        console.log(title_room);
        $.ajax({
            type : "POST",
            url  : "/create_room",
            data : {roomName : title_room, memberNames : user},
            success: response => {
                console.log("Success")
                console.log(response);
                location.reload();
            }
        })
    }
    function exitChatRoom() {
        WebSocket.sendExit(function(){location.reload()});
    }
    function searchFriends() {
        $.ajax({
            type : "POST",
            url  : "/friend//load",
            data : {uid : <%=uid%>},
            success: response => {
                $('.modal-body').empty();
                for (let i = 0; i < response.length ; i++) {
                    console.log(response[i].name);
                    $('#modal_body').append('<input type ="checkbox" name ="friends" value ="'+response[i].name + '"/><img src ="'+response[i].img + '" class="rounded-circle user_img_msg" >'
                    + response[i].name + '<br>');
                }
                console.log("Success");
                console.log(response);
            }
        })
    }


</script>
</body>
</html>
