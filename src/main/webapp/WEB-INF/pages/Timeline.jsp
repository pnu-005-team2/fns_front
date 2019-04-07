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
    <title>Title</title>

</head>
<body>
<script type="text/javascript">
  /*  function Add() {
        <c:forEach var="postrecord" items="${postRecordlList}">
        var table1 = document.getElementById("timelineTable");

        table1.setAttribute("class","table");
        // tr 개체를 얻어와 속성값을 조절한다.
        var thead1 =document.createElement("thead");



        var tr1_thead = document.createElement("tr");
        //tr1.setAttribute("bgColor", "#FFFFCC"); // 배경색
        //tr1.setAttribute("height", "30"); // 높이


        var th1_thead = document.createElement("th");
        var tbody = document.createElement("tbody");
        var tr_tbody1= document.createElement("tr");
        var td_tbody1= document.createElement("td");
        var tr_tbody2= document.createElement("tr");
        var td_tbody2= document.createElement("td");


        var tfoot= document.createElement("tfoot");
        var tr_tfoot= document.createElement("tr");
        var td_tfoot= document.createElement("td");
        td_tfoot.setAttribute("colspan","2");
        var td2_tfoot = document.createElement("td");
        var td3_tfoot = document.createElement("td");


        /*var th1 = document.createElement("th");
        td1.setAttribute("width", "100"); // 넓이
        td1.innerText = document.all.txtHome.value;
        var td2 = document.createElement("td");
        td2.setAttribute("width", "200"); // 넓이
        td2.innerText = document.all.txtAway.value;


        thead1.appendChild(tr1_thead);
        tr1_thead.appendChild(th1_thead);
        tbody.appendChild(tr_tbody1);
        tr_tbody1.appendChild(td_tbody1);
        tbody.appendChild(tr_tbody2);
        tr_tbody2.appendChild(td_tbody2);

        tfoot.appendChild(tr_tfoot);
        tr_tfoot.appendChild(td_tfoot);
        tr_tfoot.appendChild(td2_tfoot);

        tfoot.appendChild(td3_tfoot);
        //tr.appendChild(td1);
        //tr.appendChild(td2);
        // 초기에 보여주는' 내용이 없습니다' 행을 지운다.
        //if (table1.firstChild.lastChild.childNodes.length == 1)
         //   table1.firstChild.removeChild(table1.firstChild.lastChild);

        // 입력된값을 넣은 tr 개체를 추가한다.

        //table1.firstChild.appendChild(thead1);
        table1.appendChild(thead1);
        table1.appendChild(tbody);
        table1.appendChild(tfoot);
        //table1.firstChild.appendChild(tr);


       //${item.lid} &nbsp;
       // ${item.pid} &nbsp;
       // ${item.uid} &nbsp;
        </c:forEach>

    }
*/


</script>
<div class="container-fluid" >
    <div class="row">
        <div class="col-xs-3">.col-xs-3</div>
        <div class="col-xs-3">

            <c:forEach var="item" items="${postRecordlList}">
                <table class="table" id="timelineTable">
                    <thead>
                    <tr>

                        <th>${item.writer}</th>

                    </tr>
                    </thead>
                    <tbody>


                    <tr>

                        <td>${item.img}</td>

                    </tr>

                    <tr>

                        <td>${item.content}</td>
                    </tr>
                    </tbody>
                    <tfoot>
                    <tr>
                        <td>좋아요</td>


                    </tr>
                    <td>
                        댓글
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
