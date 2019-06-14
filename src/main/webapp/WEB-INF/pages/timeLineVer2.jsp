<%@ page import="com.team2.webservice.sprint1.vo.Member" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: iseungcheon
  Date: 2019. 5. 5.ch
  Time: PM 6:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>FNS</title>
</head>
<body>
<link rel="stylesheet" href="/resources/css/timeline.css" type="text/css"/>
<link rel="stylesheet" href="/resources/css/global.css" type="text/css"/>
<%
    request.setAttribute("member", session.getAttribute("login"));
    Member user = (Member)session.getAttribute("login");
    String name = user.getName();
    String email = user.getEmail();
    int uid = user.getUid();
%>

<jsp:include page="header.jsp"/>
<div class="page-container">
    <div class="left-wrapper">
        <div class="left-content">
            <div class="musinsa-list">
                <c:forEach var="item" items="${musinsaList}" >
                    <div class="musinsa-item pnt" onclick="location.href = '${item.link}';">
                        <img src="${item.img_url}">
                        <div class="musinsa-title">${item.title}</div>
                        <div class="musinsa-description">${item.description}</div>
                    </div>
                </c:forEach>
            </div>
        </div>
    </div>
    <div class="time-line">
        <jsp:include page="Post.jsp" />
        <c:forEach var="item" items="${postRecordlList}" step="1">
            <div class="board-item" name="board/${item.pid}" style="width :100%;">
                <div class="writer" >
                    <img class="pnt" onclick = "location.href='user/mypage?email=${item.member.email}'" src="${item.member.img}">
                    <strong class="pnt" onclick = "location.href='user/mypage?email=${item.member.email}'">${item.member.name}</strong>
                </div>
                <div class="board-img" style="position: relative;padding-top:100%;overflow: hidden;">
                    <img src="/logoShowForStudent/${item.pid}" style="position: absolute; top:0; bottom: 0;left:0;right: 0;max-width: 100%;height: auto;">
                    <div class="linkimg pnt" style="z-index:1;position:absolute;left:${item.productLinks[0].position_x}px;top:${ item.productLinks[0].position_y}px;">
                        <a href="${item.productLinks[0].linktext}" target="_blank"><img src="https://cdn4.iconfinder.com/data/icons/geomicons/32/672366-x-128.png" style="width:10px;height:10px;z-index:2;"></a>
                    </div>
                </div>
                <div class="board-content">${item.content}</div>
                <div class="board-hashtag pnt">${item.hashtag}</div>
                <div class="board-funtion">
                    <span class="function-item">
                    <i id="like_btn${item.pid}" class="fa fa-thumbs-o-up fa-2x pnt"
                       onclick="likeToggle(this), like_btn_clickevent(${item.pid})"></i>
                    </span>
                    <span class="function-item">
                        <i class="comment-icon fa fa-comments-o fa-2x pnt" onclick="loadComment(event)" data-board-idx="${item.pid}"
                            data-uid = "<%=uid%>"></i>
                    </span>
                </div>
                <%--Todo data-board-idx를 name으로 바꾸고 /단위로 잘라서 정보를 담을 수 있게 해보자--%>
                <div class="comment-box" name="comment/${item.pid}">
                    <div class="comment-input-box">
                        <input type="text" class="comment-input" data-board-idx="${item.pid}"
                               data-uid= "<%=uid%>" page-idx = "0" onkeydown="tagFriend(event)"
                               placeholder="re">
                        <button class="comment-btn" data-writer="<%=name%>">Enter</button>
                    </div>
                    <div class="comment-list"
                         data-board-idx="${item.pid}">
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>

    <div class="friendshow">
        <div class = "following" id = "following">
            <strong>Following </strong>
            <c:forEach var="item" items="${friendsRecordList}">
                <div class="friend-item" data-friend-index="${item.uid}">
                    <img onclick = "location.href='user/mypage?email=${item.email}'" class='profile pnt' src="${item.img}" width="12%" height="15">
                    <strong class="pnt" onclick = "location.href='user/mypage?email=${item.email}'" style="width:58%">${item.name}</strong>
                    <button class="follow-btn" data-friend-btn-idx="${item.uid}" data-uid = <%=uid%> >unfollow</button>
                </div>
            </c:forEach>
        </div>

        <div class = "follower">
            <strong>Follower </strong>
            <c:forEach var="item" items="${friendedRecordList}">
                <div>
                    <img onclick = "location.href='user/mypage?email=${item.email}'" class='profile pnt' src="${item.img}" width="12%" height="15">
                    <strong class="pnt" onclick = "location.href='user/mypage?email=${item.email}'" >${item.name}</strong>
                </div>
            </c:forEach>
        </div>

        <div class="friendRecommend" id="friendRecommend">
            <strong>Friend Recommend </strong>
            <c:forEach var="item" items="${friendRecommendList}">
                <div class="recommend-friend-item" data-recommend-index ="${item.uid}">
                    <img class='profile pnt' onclick = "location.href='user/mypage?email=${item.email}'" src="${item.img}" width="12%" height="15">
                    <strong class="pnt" onclick = "location.href='user/mypage?email=${item.email}'" style="width:58%">${item.name}</strong>
                    <button class="follow-btn pnt" onclick="addfriend(<%=uid%>,${item.uid});" height="15">follow</button>
                </div>
            </c:forEach>
        </div>
    </div>
</div>
<script src="/resources/js/friend.js"></script>
<script src="/resources/js/comment.js"></script>
<script>

    let text_number_For_Hashtag=0;
    let temp_value= document.getElementsByClassName("fa");

    $(document).ready(function (e) {
        console.log("Ready");
        let unfollow_btns = document.querySelectorAll("button[data-friend-btn-idx]");
        let comment_btns = document.querySelectorAll(".comment-btn");
        let comment_del_btns = document.querySelectorAll(".comment-item .delete-btn");
        let hashtags = document.getElementsByClassName("board-hashtag");
        console.log(hashtags);
        // -------- Event를 바인딩합니다. ----------
        for(let item of unfollow_btns) item.onclick = deletefriend;
        for(let item of comment_btns) item.onclick = comment_regist;
        for(let item of comment_del_btns) item.onclick = deleteComment;
        for(let item of hashtags) {
            console.log(item.textContent);
            item.textContent = item.textContent.replace(/,/g, "#");
        }
    });

    // --------- 좋아요 클릭시 아이콘 Toggle ---------
    function likeToggle(target,temp_pid) {
        console.log("LikeToggle");
        target.classList.toggle("fa-thumbs-up");

        temp_value= document.getElementsByClassName("fa-2x"+temp_pid)[0].getAttribute("value");

        console.log(temp_value.value);
        console.log("xxxxxxxx_____"+temp_value);
        if(temp_value=="true"){
            temp_value="false";
            document.getElementsByClassName("fa-2x"+temp_pid)[0].setAttribute("value","false");
        }
        else{
            temp_value="true";
            document.getElementsByClassName("fa-2x"+temp_pid)[0].setAttribute("value","true");

        }

        alert("temp pid "+ temp_pid);
        var sendData = { "like_Value" : temp_value,
            "post_Pid": temp_pid
        };

        $.ajax({
            type : "POST",
            url : "/like_btn_Value_Url",
            data : sendData,
            success: function (data) {
                alert(data);
            }
        });

        //  target.classList.toggle("fa-thumbs-o-up",false);
        /*1. 현재, 좋아요 테이블에서 받아오는 작업을 해야함
         -> 기존에 있던 부분에서 Table에서 미리 boolean 값을 받아와서 이를 확인하여
          현재의 toggle 버튼을 넣야함
         -> 첫번째로, Function을 이용해서 controller에서 처리를 다한 뒤에
         ->  이 값을 받아서 각 값에 맞게 이를 출력하면 된다.
        */
    }


    // --------- 좋아요 클릭시 Event 처리 ---------
    //Todo 세부구현 필요
    function like_btn_clickevent(temppid) {
        var like_button = document.getElementById("like_btn"+temppid);

        console.log("ClickEvent");
        if(like_button.style.display=="none"){
            var sendData = { "lid" : temppid ,
                "uid" : temppid ,
                "pid" : temppid,
                "like_boolean" : "false"
            }
        }
        else{
            var sendData = { "lid" : temppid ,
                "uid" : temppid ,
                "pid" : temppid,
                "like_boolean" : "true"
            }
        }
    }

</script>
</body>
</html>