<%@ page import="com.team2.webservice.sprint1.vo.Member" %>
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
    <%--<jsp:include page="header.jsp"/>--%>
</head>
<body>
<jsp:include page="body.jsp"/>

<%--로그인 되어있는 유저 정보를 받음--%>
<%
    Member user = (Member)session.getAttribute("login");
    String email = user.getEmail();
%>


<div class="card gedf-card">

    <div class="card-header">
    </div>
    <div class="card-body">
        <form action="/board//post_" method="post" enctype="multipart/form-data">
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="posts" role="tabpanel" aria-labelledby="posts-tab">
                    <div class="form-group">
                        <label class="sr-only" for="message">board</label>
                        <textarea name="content" class="form-control" id="message" rows="3" placeholder="What are you thinking?"></textarea>
                        <input name = "image" type="file" accept = "image/*" class="file-input" id="fileInput">
                        <input name = "writer" type="hidden" value="<%=email%>">
                    </div>
                    <div class="btn-group">
                        <button type="submit" class="btn btn-primary">글쓰기</button> &nbsp;
                        <button type="button" class="btn btn-default" onclick="location.href='/user/edit'">정보수정</button>
                    </div>
                </div>
            </div>
        </form>
    </div>
</div>

</body>
</html>
