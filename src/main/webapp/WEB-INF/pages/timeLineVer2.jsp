<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: iseungcheon
  Date: 2019. 5. 5.
  Time: PM 6:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FNS</title>
</head>
<body>
<div style="position:relative; z-index: 1;">
    <input type="text" class="comment-input"
           onfocus="this.value=''" id="search_User_text"
           placeholder="검색"  >
    <div style="position: relative; z-index: 2; left:10px; top:20px;" id="search_Result_div">

    </div>
</div>

<jsp:include page="Post.jsp"/>
<link rel="stylesheet" href="/resources/css/timeline.css" type="text/css"/>

    <div class="page-container">
        <div class="left"></div>
        <div class="time-line">
            <c:forEach var="item" items="${postRecordlList}" step="1">
                <div class="board-item">
                    <div class="writer" >
                        <img src="${item.member.img}">
                        <strong>${item.member.name}</strong>
                    </div>
                    <div class="board-img" >
                     <img
                        src="/logoShowForStudent/${item.pid}">
                    </div>
                    <div class="board-content" >${item.content}</div>
                    <div class="board-hashtag" >${item.hashtag}</div>
                    <div class="board-funtion" >
                        <i id="like_btn${item.pid}"
                                class = "fa fa-thumbs-o-up fa-2x"
                                onclick="likeToggle(this), like_btn_clickevent(${item.pid})"></i>
                        <i class="comment-icon fa-comments-o fa-2x"></i>
                    </div>
                    <div class="comment-box"
                         id="btn_group_div_group${item.pid}">
                        <div class="comment-input-box">
                        <input type="text" class="comment-input"
                               onfocus="this.value=''" id="comment${item.pid}"
                               placeholder="re">
                        <input type="button"
                               class="enter-key"
                               id="comment_confirm${item.pid}"
                               onclick="comment_regist(${item.pid});" value="enter" />
                        </div>
                        <div id="comment-list${item.pid}"></div>
                    </div>
                </div>
            </c:forEach>
        </div>
        <div class="right"></div>
    </div>
<script>

    var text_number=0;
    var text_For_Search_User = document.getElementById("search_User_text");
    var search_UserText=text_For_Search_User.value;

    $(document).ready(function (e) {


        setInterval(text_Check,1000);

    });


    var input1 = document.getElementById('search_User_text');
    input1.onkeydown = function(event) {
        text_number=0;

        var temp=   document.getElementById("search_Result_div");


        if(temp.getElementsByTagName("p").length!=0){



            while(temp.hasChildNodes()){

                temp.removeChild(temp.lastChild);
            }

        }


    }


    var text_Check_flag=false;

    function text_Check(){
        var text_For_Search_User = document.getElementById("search_User_text");

        search_UserText= text_For_Search_User.value;



        var sendData = { "For_Search_User_Text" : search_UserText
        }


        if(text_number==0){
            $.ajax({
                type : "POST",
                url : "/text_Check",
                data : sendData,
                success: function (data) {
                    if(data!="null"){
                        text_number=1;
                        var search_Result_text= document.getElementById("search_Result_div");
                        var comment_text_area_value = search_UserText;
                        //#.시도
                        var comment_text_area_post_p = document.createElement("p");
                        comment_text_area_post_p.innerHTML+="<div><div class=\"in-line\">"+
                            "<img class=\"btnclass-img\" width=\"5%\"height=\"5%\""+
                            "src="+ data+ ">" +"&nbsp;"+ comment_text_area_value + "</div>" +
                            "<br></div>";

                       /* var comment_textNode=document.createTextNode(comment_text_area_value);
                        comment_text_area_post_p.appendChild(comment_textNode);*/
                        search_Result_text.appendChild(comment_text_area_post_p);


                        //#.답
                     /*   var comment_text_area_post_p = document.createElement("p");

                        var comment_text_area_value = search_UserText;

                        var comment_textNode=document.createTextNode(comment_text_area_value);
                        comment_text_area_post_p.appendChild(comment_textNode);
                        search_Result_text.appendChild(comment_text_area_post_p);*/

                    }

                    // alert(data);
                }
            });

        }








    }







    // --------- 좋아요 클릭시 아이콘 Toggle ---------
    function likeToggle(target) {
        console.log("LikeToggle")
        target.classList.toggle("fa-thumbs-up");
    }

    // --------- 좋아요 클릭시 Event 처리 ---------
    //Todo 세부구현 필요
    function like_btn_clickevent(temppid) {
        var like_button = document.getElementById("like_btn"+temppid);

        console.log("ClickEvent")
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

        $.ajax({
            type : "POST",
            url : "/like_btn",
            data : sendData,
            success: function (data) {
                alert(data);
            }
        });

        like_button.style.display="none";
    }


    // --------- 댓글등록 ---------
    function comment_regist(temppid){
        var comment_text_area = document.getElementById("comment"+temppid);

        var comment_text_area_post_p = document.createElement("p");

        var comment_text_area_value = comment_text_area.value;

        var comment_textNode=document.createTextNode(comment_text_area_value);
        comment_text_area_post_p.appendChild(comment_textNode);


        var today= new Date();
        var sendData = { "comment" : comment_text_area_value ,
            "cid" : temppid ,
            "pid" : temppid,
            "writer" : temppid,
            "date": today
        }

        document.getElementById("comment-list"+temppid).innerHTML +=
            "<div><div class=\"in-line\">"+
            "<img class=\"btn-img\" id=\"btn_img_like_img_id\" width=\"10%\"height=\"15\""+
            "src=\"https://pbs.twimg.com/profile_images/896261392340107266/Woo6s49S_400x400.jpg\">" +
            comment_text_area.value + "</div>" +
            "<br></div>";

        $.ajax({
            type : "POST",
            url : "/comment",
            datatype : "text",
            data : sendData,
            success: function (data) {
                alert(data);
            }
        });

        comment_text_area.value='';

    }
</script>
</body>
</html>
