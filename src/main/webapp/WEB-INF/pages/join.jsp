<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta name="viewport" content="width=device-width" , initial-scale="1.0">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Insert title here</title>
    <jsp:include page="header.jsp"/>

</head>

<body>


<div class="container">

    <div class="col-lg-4"></div>

    <div class="col-lg-4">

        <div class="jumbotron" style="padding-top:20px;">

            <form method="post" action="joinAction.jsp">

                <h3 style="text-align:center;">회원가입 화면</h3>

                <div class="form-group">

                    <input type="text" class="form-control" placeholder="아이디" name="userID" maxlength="20">

                </div>

                <div class="form-group">

                    <input type="text" class="form-control" placeholder="비밀번호" name="userPassword" maxlength="20">

                </div>

                <div class="form-group">

                    <input type="text" class="form-control" placeholder="이름" name="userName" maxlength="20">

                </div>

                <div class="form-group" style="text-align:center;">

                    <div class="btn-group" data-toggle="buttons">

                        <label class="btn btn-primary active">
                            <input type="radio" name="userGender" autocomplete="off" value="남자" checked>남자 <br/>
                            <input type="radio" name="userGender" autocomplete="off" value="여자" checked>여자

                        </label>

                        <label class="btn btn-primary active">
                        </label>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
