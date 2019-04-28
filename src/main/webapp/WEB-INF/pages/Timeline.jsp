<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>

<%@page import="com.team2.webservice.sprint1.controller.TimeLineController" %>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
    <title>Title</title>

	<style media="screen">
    body{
      background-color: #f5f5f5;
    }
    .in-line{
      width:250px;
      height:30px;
    }
    input{
      margin:0;
    }

    input[type="text"]{
      width:70%;
      height:100%;
      border:none;
      font-size:1em;
      padding-left: 5px;
      font-style: oblique;
      display:inline;
      outline:none;
      box-sizing: border-box;
      color:black;

    }
    input[type=button]{
      width: 20%;
      height:100%;
      background-color: lightgray;
      border:none;
      background-color: white;
      font-size:1em;
      color:#042AaC;
      outline:none;
      display:inline;
      margin-left: -10px;
      box-sizing: border-box;
    }
    input[type=button]:hover{
      background-color: lightgray;
    }
  </style>


</head>
<body>
<script type="text/javascript">

    $(function(){

    });


    function comment_regist(temppid){
        var comment_text_area = document.getElementById("comment"+temppid);


        var comment_tr_Area= document.getElementById("tr_body"+temppid);

        var button_like_button = document.getElementById("like_btn"+temppid);

         //comment_text_area_value =comment_text_area.value;

        var comment_text_area_post_p = document.createElement("p");

        var comment_text_area_value = comment_text_area.value;

        var comment_textNode=document.createTextNode(comment_text_area_value);
        //comment_text_area_post_p.value=comment_text_area_value;
        comment_text_area_post_p.appendChild(comment_textNode);

        //comment_text_area_post_td.value=comment_text_area_value;
        //var comment_text_area_Id = document.getElementById("comment"+temppid);

        var today= new Date();
        var sendData = { "comment" : comment_text_area_value ,
            "cid" : temppid ,
            "pid" : temppid,
            "writer" : temppid,
            "date": today
        }

        document.getElementById("btn_group_div_group"+temppid).append(comment_text_area_post_p);

        //comment_tr_Area.appendChild(comment_text_area_post_td);
        //button_like_button.append(comment_text_area_post_p);
        //button_like_button.appendChild(comment_text_area_post_p);

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
        //comment_text_area.value=value;


    }

    function like_btn_clickevent(temppid) {
         var like_img = document.getElementById("btn_img_like_img_id"+temppid);
         var like_button = document.getElementById("like_btn"+temppid);


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



</script>
<div class="container-fluid" >
    <div class="row">
        <div class="col-xs-3">.col-xs-3</div>
        <div class="col-xs-3"  style="border-top:10px solid skyblue; border-bottom:10px solid skyblue; border-left:10px solid skyblue; border-right:10px solid skyblue; border-radius: 10px;" >
            <c:forEach var="item" items="${postRecordlList}">
                <table class="table" id="timelineTable">
                    <thead>
                    <tr>

                        <th>${item.writer}</th>

                    </tr>
                    </thead>
                    <tbody>


                    <tr>
                        <td> <img id='readAsDataURL${item.pid}'  src="background.png" width="200" height="200"/> </td>
                        <td> <img id ="itemimg${item.pid}"></td>
                    </tr>

                    <tr>

                        <td>${item.content}</td>
                    </tr>
                    </tbody>
                    <tfoot style="border-bottom:5px solid sky">
                    <tr id="tr_body${item.pid}">
                        <td>
                            <input type="button" id="like_btn${item.pid}" onclick="like_btn_clickevent(${item.pid})">
                            
                                <img class="btn-img" id="btn_img_like_img_id" width="10" height="10" src="https://pbs.twimg.com/profile_images/896261392340107266/Woo6s49S_400x400.jpg">

                        </td>

                    </tr>
                    <td>
                        <div class="btn-group btn-group-sm" role="group" style="float:left;" id="btn_group_div_group${item.pid}">                                		
                                <div class ="in-line" style="float:left;">
                                	<img class="btn-img" id="btn_img_like_img_id" width="10%" height="15"src="https://pbs.twimg.com/profile_images/896261392340107266/Woo6s49S_400x400.jpg"> 
                                	<input type="text" class="form-control" onfocus="this.value=''" id="comment${item.pid}" placeholder="댓글">
                                	<input type="button" id="comment_confirm${item.pid}" onclick="comment_regist(${item.pid});" value="등록"/>
                                </div>
                              

                               <!-- <input type="button" id="comment_confirm" onclick="document.getElementById('comment').value = ''" value="등-록"/>
                               -->
                        </div>
                    </td>

                    </tfoot>

                </table>

            </c:forEach>


        </div>
        <div class="col-xs-3">.col-xs-3</div>
        <div class="col-xs-3">.col-xs-3</div>
    </div> <div class="row"> <div class="col-sm-3">.col-sm-3</div>
    <div class="col-sm-3">.col-sm-3</div>
    <div class="col-sm-3">.col-sm-3</div>
    <div class="col-sm-3">.col-sm-3</div>
</div> <div class="row"> <div class="col-md-3">.col-md-3</div>
    <div class="col-md-3">.col-md-3</div>
    <div class="col-md-3">.col-md-3</div>
    <div class="col-md-3">.col-md-3</div>
</div> <div class="row"> <div class="col-lg-3">.col-lg-3</div>
    <div class="col-lg-3">.col-lg-3</div>
    <div class="col-lg-3">.col-lg-3</div>
    <div class="col-lg-3">.col-lg-3</div>
</div> </div>



<!--<div class="card gedf-card">
    <div class="card-header">
        <ul class="nav nav-tabs card-header-tabs" id="myTab" role="tablist">
            <li class="nav-item">
                <a class="nav-link active" id="posts-tab" data-toggle="tab" href="#posts" role="tab" aria-controls="posts" aria-selected="true">Make
                    a publication</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" id="images-tab" data-toggle="tab" role="tab" aria-controls="images" aria-selected="false" href="#images">Images</a>
            </li>
        </ul>
    </div>
    <div class="card-body">
        <form action="/post_" method="post">
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="posts" role="tabpanel" aria-labelledby="posts-tab">
                    <div class="form-group">
                        <label class="sr-only" for="message">post</label>
                        <textarea name="content" class="form-control" id="message" rows="3" placeholder="What are you thinking?"></textarea>
                    </div>
                    <div class="btn-group">
                        <button type="submit" class="btn btn-primary">share</button>
                    </div>
                </div>
                <div class="tab-pane fade" id="images" role="tabpanel" aria-labelledby="images-tab">
                    <div class="form-group">
                        <div class="custom-file">
                            <input type="file" class="custom-file-input" id="customFile">
                            <label class="custom-file-label" for="customFile">Upload image</label>
                        </div>
                    </div>
                    <div class="py-4"></div>
                    <div class="btn-group">
                        <button type="submit" class="btn btn-primary">share</button>
                    </div>
                </div>
            </div>

        </form>
    </div>
</div>
-->
</body>
</html>
