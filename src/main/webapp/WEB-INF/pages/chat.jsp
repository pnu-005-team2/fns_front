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
    <%-- todo script 정리 필요 --%>
</head>
<body>
<jsp:include page="body.jsp"/>
<%
    Member user = (Member)session.getAttribute("login");
    String name = user.getName();
%>
<div class="container-fluid h-100">
    <div class="row justify-content-center h-100">
        <div class="col-md-4 col-xl-3 chat"><div class="card mb-sm-3 mb-md-0 contacts_card">
            <div class="card-header">
                <div class="input-group">
                    <input type="text" placeholder="Search..." name="" class="form-control search">
                    <div class="input-group-prepend">
                        <span class="input-group-text search_btn"><i class="fas fa-search"></i></span>
                    </div>
                </div>
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
            <div type = "hidden" class="card" >
                <div class="card-header msg_head">
                    <div class="d-flex bd-highlight">
                        <div class="img_cont">
                            <img src="https://devilsworkshop.org/files/2013/01/enlarged-facebook-profile-picture.jpg" class="rounded-circle user_img" id ='user_img'>
                            <span class="online_icon"></span>
                        </div>
                        <div id="user_info">
                            <span id = 'chat_room_number'>${chatRoom.name}</span>
                            <p></p>
                        </div>
                    </div>
                    <span id="action_menu_btn"><i class="fas fa-ellipsis-v"></i></span>
                    <div class="action_menu">
                        <ul>
                            <li><i class="fas fa-user-circle"></i> View profile</li>
                            <li><i class="fas fa-users"></i> Add to close friends</li>
                            <li><i class="fas fa-plus"></i> Add to group</li>
                            <li><i class="fas fa-ban"></i> Block</li>
                        </ul>
                    </div>
                </div>
                <div id = "chatOutput" class="card-body msg_card_body"></div>
                <div class="card-footer">
                    <div class="input-group">
                        <div class="input-group-append">
                            <span class="input-group-text attach_btn"><i class="fas fa-paperclip"></i></span>
                        </div>
                        <input name="" id ="chatInput" class="form-control type_msg chat" placeholder="Type your message..."></input>
                        <div class="input-group-append">
                            <span class="input-group-text send_btn"><i class="fas fa-location-arrow"></i></span>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script src="/resources/js/socket.js"></script>
<script>

    var curMember={};

    $(document).ready(function(){
        $('#action_menu_btn').click(function(){
            $('.action_menu').toggle();
        });
    });

    window.addEventListener("load", ()=>{
        var userName = "<%=name%>";
        console.log(WebSocket)
        console.log("${chatRoom.messages}")
        <c:forEach var="item" items="${chatRoom.messages}" >
        if ("${item.member.name}"=="<%=name%>"){
            $(".msg_card_body").append('<div class="d-flex justify-content-end mb-4">'+
                                            '<div class="msg_cotainer_send">"${item.content}"'+
                                                '<span class="msg_time_send">8:55 AM, Today</span>'+
                                            '</div>'+
                                            '<div class="img_cont_msg">'+
                                                '<img src="${user.img}" class="rounded-circle user_img_msg">'+
                                            '</div>'+
                                        '</div>');
        }
        else{
            $(".msg_card_body").append('<div class="d-flex justify-content-start mb-4">\n' +
                                            '<div class="img_cont_msg">\n' +
                                                '<img src="${item.member.img}" class="rounded-circle user_img_msg">\n' +
                                            '</div>\n' +
                                            '<div class="msg_cotainer">\n' +
                                                        '${item.content}\n' +
                                                '<span class="msg_time">8:40 AM, Today</span>\n' +
                                            '</div>\n' +
                                        '</div>');
        }
        </c:forEach>

        WebSocket.init(${chatRoom.cid}, ${chatRoom.members.size()-1}, userName);
    });
    $(document).ready(function(){
        $('.ToActive').click(function(){
            $('.card').show();
            $('.active').removeClass('active').addClass('ToActive');
            $(this).removeClass('ToActive').addClass('active');
            <c:forEach var="member" items="${memberList}" begin="1" step="1">
                if (${member.uid} == $(this).attr('id')){
                    curMember.name ="${member.name}";
                    curMember.img = "${member.img}";
                };
            </c:forEach>
            $('#user_name').text(curMember.name);
            $('.user_img_msg').attr('src',curMember.img);
            $('#user_img').attr('src',curMember.img);
        });
    });

</script>
</body>
</html>
