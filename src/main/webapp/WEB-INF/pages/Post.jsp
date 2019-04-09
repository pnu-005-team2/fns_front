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
</head>
<body>
<div class="card gedf-card">
    <div class="card-header">
    </div>
    <div class="card-body">
        <form action="/post_" method="post" enctype="multipart/form-data">
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="posts" role="tabpanel" aria-labelledby="posts-tab">
                    <div class="form-group">
                        <label class="sr-only" for="message">post</label>
                        <textarea name="content" class="form-control" id="message" rows="3" placeholder="What are you thinking?"></textarea>

<%--                        <input name = "image" type="file" accept = "image/*" class="custom-file-input" id="customFile">--%>
<%--                        <label class="form-control" for="customFile">Upload image</label>--%>
                        <input name = "image" type="file" accept = "image/*" class="file-input" id="fileInput">
<%--                        <label class="form-control" for="fileInput">Upload image</label>--%>
                    </div>
                    <div class="btn-group">
                        <button type="submit" class="btn btn-primary">share</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>
<jsp:include page="body.jsp"/>
</body>
</html>

