<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <jsp:include page="header.jsp"/>
    <script src="//code.jquery.com/jquery-1.11.0.min.js"></script>

</head>
<body>
<div class="card gedf-card">
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
        <div class="tab-content" id="myTabContent">
            <div class="tab-pane fade show active" id="posts" role="tabpanel" aria-labelledby="posts-tab">
                <div class="form-group">
                    <label class="sr-only" for="message">post</label>
                    <textarea class="form-control" id="message" rows="3" placeholder="What are you thinking?"></textarea>
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
            </div>
        </div>
        <div class="btn-toolbar justify-content-between">
            <div class="btn-group">
                <button type="submit" class="btn btn-primary">share</button>
                <button type="button" class="btn btn" id = "execute">Like Register</button>
            </div>
            <div class="btn-group">
                <button id="btnGroupDrop1" type="button" class="btn btn-link dropdown-toggle" data-toggle="dropdown" aria-haspopup="true"
                        aria-expanded="false">
                    <i class="fa fa-globe"></i>
                </button>
                <div class="dropdown-menu dropdown-menu-right" aria-labelledby="btnGroupDrop1">
                    <a class="dropdown-item" href="#"><i class="fa fa-globe"></i> Public</a>
                    <a class="dropdown-item" href="#"><i class="fa fa-users"></i> Friends</a>
                    <a class="dropdown-item" href="#"><i class="fa fa-user"></i> Just me</a>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="body.jsp"/>
<script>
    $('#execute').click(function(){
        var lid = 1;
        var pid = 1;
        var uid = "¿ÃΩ¬√µ";
        var isLike = true;
        console.log("»Æ¿Œ")
        $.ajax({
            type: "POST",
            url:'like_btn',
            data: {lid : lid, pid : pid, uid : uid, like_boolean : isLike},
            success: function(data){
                console.log(data)
            },
            error: function(xhr, status, error){
                console.log(error)
            }
        });
    })

</script>
    </body>
</html>

