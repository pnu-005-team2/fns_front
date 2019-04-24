<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="layoutTag" tagdir="/WEB-INF/tags"%>
<layoutTag:layout>
 
<div class="container">
    <div class="col-xs-12" style="margin:15px auto;">
        <label style="font-size:20px;"><span class="glyphicon glyphicon-list-alt"></span>게시글 상세</label>
    </div>
 
    <div class="col-xs-12">
        <form action="/insertProc" method="post">
            <dl class="dl-horizontal">
              <dt>제목</dt>
              <dd>${detail.subject}</dd>
              
              <dt>작성자</dt>
              <dd>${detail.writer}</dd>
              
              <dt>작성날짜</dt>
              <dd>
                  <fmt:formatDate value="${detail.reg_date}" pattern="yyyy.MM.dd HH:mm:ss"/>
              </dd>
              
              <dt>첨부파일</dt>
              <dd><a href="/fileDown/${files.bno}">${files.fileOriName}</a></dd>
              
              <dt>내용</dt>
              <dd>${detail.content}</dd>
            </dl>
        </form>
        <div class="btn-group btn-group-sm" role="group" style="float:right;">
          <button type="button" class="btn btn-default" onclick="location.href='/delete/${detail.bno}'">삭제</button>
          <button type="button" class="btn btn-default" onclick="location.href='/update/${detail.bno}'">수정</button>
          <button type="button" class="btn btn-default" onclick="location.href='/list'"> 목록 </button>
        </div>
    </div>
    
    <!--                     추가                         -->
    <!--  댓글  -->
    <div class="container">
        <label for="content">comment</label>
        <form name="commentInsertForm">
            <div class="input-group">
               <input type="hidden" name="bno" value="${detail.bno}"/>
               <input type="text" class="form-control" id="content" name="content" placeholder="내용을 입력하세요.">
               <span class="input-group-btn">
                    <button class="btn btn-default" type="button" name="commentInsertBtn">등록</button>
               </span>
              </div>
        </form>
    </div>
    
    <div class="container">
        <div class="commentList"></div>
    </div>
</div>
 
<!--                     추가                         -->
<%@ include file="commentS.jsp" %>
</layoutTag:layout>
