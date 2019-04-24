<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">

<nav class="navbar navbar-default">

    <div class="naver-header">

        <button type="button" class="navbar-toggle collapsed"

                data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"

                aria-expanded="false">


            <span class="icon-bar"></span>

            <span class="icon-bar"></span>

            <span class="icon-bar"></span>


        </button>

        <a class="navbar-brand" href="main.jsp">FNS</a>

    </div>


    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">

        <ul class="nav navbar-nav">

            <li><a href="main.jsp">메인</a>

            <li><a href="bbs.jsp">게시판</a>

        </ul>

        <ul class="nav navbar-nav navbar-right">

            <li class="dropdown">

                <a href="#" class="dropdown-toggle"

                   data-toggle="dropdown" role="button" aria-haspopup="true"

                   aria-expanded="false">접속하기<span class="caret"></span></a>


                <ul class="dropdown-menu">

                    <li class="active"><a href="login">로그인</a></li>

                    <li><a href="join.jsp">회원가입</a></li>

                </ul>

            </li>

        </ul>

    </div>

</nav>
