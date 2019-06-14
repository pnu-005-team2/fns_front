<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.team2.webservice.sprint1.vo.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!-- <link rel="stylesheet" type="text/css" href="/resources/css/personalPageCSS.css">     -->
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

</head>
<body>
<%
  Member user = (Member)session.getAttribute("login");
  String name = user.getName();
  int uid = user.getUid();
  String gender = user.getGender();
  String intro = user.getIntro();
  String img = user.getImg();
  String email = user.getEmail();
%>
<script src="https://code.jquery.com/jquery-3.4.0.min.js" integrity="sha256-BJeo0qm959uMBGb65z40ejJYGSgR7REI4+CW1fNKwOg=" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/css/personalPageCSS.css" type="text/css"/>
<link rel="stylesheet" href="/resources/css/global.css" type="text/css"/>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<jsp:include page="header.jsp"/>
<div class="container">
  <div class="innerwrap">
  <div class="card">
    <section class="section1 clearfix">
      <div>
        <div class="row grid clearfix">
          <div class="col2 first">
            <img src="${pageUser.img}" alt="">

            <!-- name of the user. should be connected with server !!!-->
            <h1>${pageUser.name}</h1>
            
            <!-- about user. should be connected with server!!! -->
            <p>${pageUser.intro}</p>

          </div>
          <div class="col2 last">
            <div class="grid clearfix">
              <div class="col3 first follow-btn" data-value="following" data-uid="${pageUser.uid}"
                   data-toggle="modal" data-target="#follow-modal" data-login="<%=uid%>">
                <h1>${pageUser.following_cnt}</h1>
                <span>Following</span>
              </div>
              <div class="col3 follow-btn" data-value="follower" data-uid="${pageUser.uid}"
                   data-toggle="modal" data-target="#follow-modal" data-login="<%=uid%>">
                  <h1>${pageUser.follower_cnt}</h1>
                  <span>Follower</span>
              </div>
              <div class="col3 last">
                  <h1>${pageUser.gender}</h1>
                  <span>Gender</span>
              </div>
            </div>
          </div>
            <c:if test="${isMine eq 'true'}">
               <button class="button button2" onclick="location.href='/user/edit'">
                   Edit profile
               </button>
            </c:if>
            <c:if test="${isMine ne 'true'}">
                <c:if test="${isFriend ne 'true'}">
                    <button class="button button2 add-follow" onclick="addfriend(<%=uid%>,${pageUser.uid}, false);">
                        Follow
                    </button>
                </c:if>
            </c:if>
        </div>
       
        <div class="row clearfix">
          <ul class="row2tab clearfix">

          </ul>
        </div>
      </div> 
      <i class="fa fa-star"></i>
      </span>
    </section>
    </div>
    <section class="section2 clearfix">
      <div class="grid">
        <c:forEach var="board" items="${boardList}" varStatus="status">
            <c:choose>
                <c:when test="${status.index%3 eq 0}">
                    <c:set value="first" var="place"/>
                </c:when>
                <c:when test="${status.index%3 eq 1}">
                    <c:set value="center" var="place"/>
                </c:when>
                <c:when test="${status.index%3 eq 2}">
                    <c:set value="last" var="place"/>
                </c:when>
            </c:choose>
            <div class="col3 ${place}">
            <div class="card board-item" data-toggle="modal" data-target="#board-modal"
                 data-mode = "normal" data-pid = "${board.pid}">
              <div class="postcont"><img src="/logoShowForStudent/${board.pid}" alt="">
              </div>
              <div class="profileinfo">
                <p>${board.content}</p>
                <p class="board-hashtag" style="color: #2c6ed5">${board.hashtag}</p>
              </div>
            </div>
          </div>
        </c:forEach>
      </div>
    </section>
  </div>
</div>


<!-- Board Modal-->
<div class="modal fade" id="board-modal" tabindex="-1" role="dialog" aria-labelledby="board-modal" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="board-modal-label">Comments</h4>
            </div>
            <div class="modal-body">
                <span class="board-img"><img src="" alt=""> </span>
                <div class="content">
                    <div class="board-profile">
                        <img>
                        <strong></strong>
                    </div>
                   
                   
                   <div class="myComment">
                    <span class="board-content"></span>
                    
                    </div>
                    <span class="board-hashtag" style="color: #2c6ed5"></span>
                    
                    <div class="comment-box" name="comment/${item.pid}">
                        <div class="comment-input-box">
                            <input type="text" class="comment-input" data-board-idx=""
                                   data-uid= "<%=uid%>" page-idx = "0"
                                   placeholder="type comment">
                            <button class="comment-btn" data-writer="<%=name%>">Enter</button>
                        </div>
                        <div class="comment-list"
                             data-board-idx="">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>


<!-- Follow Modal-->
<div class="modal fade" id="follow-modal" tabindex="-1" role="dialog" aria-labelledby="follow-modal-label" aria-hidden="true">
    <div class="modal-dialog follow-modal-dialog">
        <div class="modal-content follow-modal-content">
            <div class="modal-header">
                <button type="button" class="close" id="follow-close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="follow-modal-label"></h4>
            </div>
            <div class="modal-body">
                <div class="follow">

                </div>
            </div>
        </div>
    </div>
</div>


<script src="/resources/js/comment.js"></script>
<script src="/resources/js/board.js"></script>
<script src="/resources/js/friend.js"></script>
<script>


    window.onload = () => {
        console.log("onload");
        let boards = document.getElementsByClassName("board-item");
        let comment_btns = document.querySelectorAll(".comment-btn");
        let follow_btns = document.querySelectorAll(".follow-btn");
        let follow_close = document.querySelector("#follow-close");

        for(let item of boards) item.addEventListener('click', boardClickHandler);
        for(let item of comment_btns) item.onclick = comment_regist;
        for(let item of follow_btns) item.onclick = followClickHandler;
        follow_close.onclick = initFriends;

        tagInit();
    };


    function boardClickHandler(e) {
        let pid = e.currentTarget.getAttribute('data-pid');
        let img_position = document.querySelector(".modal-body .board-img img");
        let writer_img = document.querySelector(".board-profile img");
        let writer_name = document.querySelector(".board-profile strong");
        let board_hashtag = document.querySelector(".modal-body .board-hashtag");
        let content = document.querySelector(".board-content");
        let comment_box = document.querySelector(".comment-box");
        let comment_list = document.querySelector(".comment-list");
        let comment_input = document.querySelector(".comment-input");
        let page = comment_box.getAttribute("page-idx");
        let size = 4;


        comment_box.setAttribute("name", "comment/"+pid);
        comment_list.setAttribute("data-board-idx", pid);
        comment_input.setAttribute("data-board-idx", pid);


        $.ajax({
            type: "POST",
            url : "/board/find?size=" + size + "&page=" + page + "&sort=cid,desc",
            data : {pid : pid},
            success: function (data) {
                console.log(data);
                let more_show_btn = document.getElementsByClassName("more-show-btn");
                img_position.setAttribute("src", "/logoShowForStudent/" + data.pid);
                writer_img.setAttribute("src", data.member.img);
                writer_name.innerHTML = data.member.name;
                content.innerHTML = data.content;
                board_hashtag.innerHTML = data.hashtag;
                tagInit();
                initComment(pid);
                data.comments.forEach((item) => {
                    createComment(item);
                });

                if(data.comments.length === 0 && more_show_btn.length > 0)
                    document.getElementsByClassName("more-show-btn")[0].remove();
                else
                    MoreShowBtnHandler(pid, page)
            }
        });
    }

    function followClickHandler(e) {
        console.log("followClickHandler");
        let uid = e.currentTarget.dataset['uid'];
        let value = e.currentTarget.dataset['value'];
        let login_uid = e.currentTarget.dataset['login'];
        console.log(uid);
        console.log(value);
        initFriends();

        $.ajax({
            type : "POST",
            url : "/friend/load",
            datatype : "json",
            data : {uid : uid, keyword : value},
            success: response => {
                console.log("Success  : " + response);
                response.forEach(item=>{
                    let parent = document.querySelector(".follow");
                    let wrapper = document.createElement("div");
                    let img = document.createElement("img");
                    let name = document.createElement("strong");


                    wrapper.classList.add("friend-item");
                    img.src = item.img;
                    name.textContent = item.name;

                    wrapper.appendChild(img);
                    wrapper.appendChild(name);

                    let btn = document.createElement("button");
                    btn.classList.add("follow-btn");

                    if(login_uid === uid && value === "following") {
                        btn.textContent = "unfollow";
                        btn.onclick = deletefriend;
                    } else {
                        btn.textContent = "follow";
                        btn.onclick = addfriend;
                    }
                    wrapper.appendChild(btn);
                    parent.appendChild(wrapper);
                })
            }
        })
    }

</script>

</body>
</html>