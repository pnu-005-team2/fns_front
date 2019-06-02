<%@ page import="com.team2.webservice.sprint1.vo.Member" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%--
  Created by IntelliJ IDEA.
  User: iseungcheon
  Date: 2019. 5. 5.
  Time: PM 6:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
    <title>FNS</title>
</head>
<body>

<jsp:include page="Post.jsp" />
<link rel="stylesheet" href="/resources/css/timeline.css" type="text/css"/>
<%
    request.setAttribute("member", (Member)session.getAttribute("login"));
    Member user = (Member)session.getAttribute("login");
    String email = user.getEmail();
    String name = user.getName();
    long uid = user.getUid();
%>
    <div class="page-container">
        <div class="left">
            <a href="chat">채팅하기</a><br/>
        </div>
        <div class="time-line">
            <c:forEach var="item" items="${postRecordlList}" step="1">
                <div class="board-item">
                    <div class="writer" >
                        <img src="${item.member.img}">
                        <strong>${item.member.name}</strong>
                    </div>
                    <div id="comment-list${item.pid}"></div>
                </div>
            </div>
        </c:forEach>
    </div>
    <div class="friendshow">
        <div class = "following" id = "following">
            <strong>Following </strong>
            <c:forEach var="item" items="${friendsRecordList}">
                <div>
                    <img class='profile' src="${item.img}" width="12%" height="15">
                    <strong style="width:58%">${item.name}</strong>
                    <div style="text-align:right" style="width:39%">
                        <input type="button" onclick="deletefriend(<%=uid%>,${item.uid});" value="unfollow" height="15"/>
                    </div>
                </div>
            </c:forEach>
        </div>

        <div class = "follower">
            <strong>Follower </strong>
            <c:forEach var="item" items="${friendedRecordList}">
                <div>
                    <img class='profile' src="${item.img}" width="12%" height="15">
                    <strong>${item.name}</strong>
                </div>
            </c:forEach>
        </div>

        <div class="friendRecommend" id="friendRecommend">
            <strong>Friend Recommend </strong>
            <c:forEach var="item" items="${friendRecommendList}">
                <div>
                    <img class='profile' src="${item.img}" width="12%" height="15">
                    <strong style="width:58%">${item.name}</strong>
                    <input type="button" onclick="addfriend(<%=uid%>,${item.uid});" value="follow" height="15"/>
                </div>
            </c:forEach>
        </div>
    </div>

</div>
<script>

    var text_number=0;
    var text_number_For_Hashtag=0;
    var text_For_Search_User = document.getElementById("search_User_text");
    var search_UserText=text_For_Search_User.value;
    var temp_value= document.getElementsByClassName("fa");

    $(document).ready(function (e) {



        setInterval(text_Check,1000);

    });


    var input1 = document.getElementById('search_User_text');
    input1.onkeydown = function(event) {
        text_number=0;
        text_number_For_Hashtag=0;

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

                    if(data.substr(0,1)=="#"){
                        //fa-2x${item.pid}
                        if(data.substr(1)!=""){



                            var jbsplit =data.substr(1).split(',');
                            if(text_number_For_Hashtag==0){
                                text_number_For_Hashtag=1;
                                for( var i in jbsplit){
                                    var temp_Data_data = { "temp_data" : jbsplit[i]
                                    }
                                    //  alert(temp_Data_data);
                                    $.ajax({
                                        type : "POST",
                                        url : "/getImage_url",
                                        data : temp_Data_data,
                                        success: function (data) {
                                            // alert(data);
                                            var search_Result_text= document.getElementById("search_Result_div");
                                            //  var comment_text_area_value = search_UserText;
                                            //#.시도
                                            var comment_text_area_post_p = document.createElement("p");

                                            var comment_textNode=document.createTextNode(data);
                                            comment_text_area_post_p.appendChild(comment_textNode);


                                            search_Result_text.appendChild(comment_text_area_post_p);

                                        }
                                    });
                                }
                            }


                        }
                    }
                    else{
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
                            search_Result_text.appendChild(comment_text_area_post_p);

                        }
                    }






                }
            });

        }
    }







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
        }

        $.ajax({
            type : "POST",
            url : "/like_btn_Value_Url",
            data : sendData,
            success: function (data) {
                alert(data);
            }
        });


        //document.getElementsByClassName("fa-thumbs-o-up")[0].setAttribute("value")="true";
        //target.classList.toggle("fa-thumbs-up",false);
    //    target.classList.toggle("fa-thumbs-up",true);

      //  target.classList.toggle("fa-thumbs-o-up",false);
/*1. 현재, 좋아요 테이블에서 받아오는 작업을 해야함
 -> 기존에 있던 부분에서 Table에서 미리 boolean 값을 받아와서 이를 확인하여
  현재의 toggle 버튼을 넣야함
 -> 첫번째로, Function을 이용해서 controller에서 처리를 다한 뒤에
 ->  이 값을 받아서 각 값에 맞게 이를 출력하면 된다.

*/


    }

    function addfriend(mypid,fripid){
        var sendData = {
            "uid1" : mypid,
            "uid2" : fripid
        }
        $.ajax({
            type : "POST",
            url : "/friendAdd",
            data : sendData,
            success: function (response) {
                alert(response);
            }
        });
    }
    function deletefriend(mypid,fripid){
        var sendData = {
            "uid1" : mypid,
            "uid2" : fripid
        }
        $.ajax({
            type : "POST",
            url : "/friendDelete",
            data : sendData,
            success: function (response) {
                alert(response);
            }
        });
    }
    // --------- 좋아요 클릭시 Event 처리 ---------
    //Todo 세부구현 필요
    function like_btn_clickevent(temppid) {
        var like_button = document.getElementById("like_btn"+temppid);

       // alert(like_button.getElementsByClassName());


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
            "<img class=\"profile\" id=\"btn_img_like_img_id\" width=\"10%\"height=\"15\""+
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