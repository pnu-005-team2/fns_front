<%@ page import="com.team2.webservice.sprint1.vo.Member" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%
    request.setAttribute("member", session.getAttribute("login"));
    Member user = (Member)session.getAttribute("login");
    String email = user.getEmail();
%>
<jsp:include page="body.jsp"/>
<link rel="stylesheet" href="/resources/css/global.css" type="text/css"/>
<div class="header-wrapper">
    <div class="header-content">
        <img src="/resources/images/KakaoTalk_20190615_011550078.png?raw=true"
             style="width:40px;height:40px; border-radius:100%" onclick="location.href='/timeline'" class="pnt">
        <div class="search-wrapper">
            <input type="text" class="search-bar"
                   onfocus="this.value=''" id="search-user-text"
                   placeholder="검색"  >
            <div id="search-result-box"></div>
        </div>
        <button class="header-btn pnt" onclick="location.href='/user/edit'">정보수정</button>
        <button class="header-btn pnt" onclick="location.href='/user/mypage?email=<%=email%>'">마이피드</button>
        <button class="header-btn pnt" onclick="location.href='/chat'">채팅하기</button>
        <button class="header-btn pnt" onclick="location.href='/logout'">로그아웃</button>
    </div>
</div>

<script src="/resources/js/header.js"></script>
