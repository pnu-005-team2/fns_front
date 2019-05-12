<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>
    <!-- Required meta tags-->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="Colorlib Templates">
    <meta name="author" content="Colorlib">
    <meta name="keywords" content="Colorlib Templates">
    <!-- Title Page-->
    <title>FNS Register</title>

    <!-- Icons font CSS-->
    <link href="/resources/vendor/mdi-font/css/material-design-iconic-font.min.css" rel="stylesheet" media="all">
    <link href="/resources/vendor/font-awesome-4.7/css/font-awesome.min.css" rel="stylesheet" media="all">

    <!-- Vendor CSS-->
    <link href="/resources/vendor/select2/select2.min.css" rel="stylesheet" media="all">
    <link href="/resources/vendor/datepicker/daterangepicker.css" rel="stylesheet" media="all">

    <!-- Main CSS-->
    <link rel="stylesheet" href="/resources/css/main.css" type="text/css"/>

</head>

<body>
<div class="page-wrapper bg-gra-01 p-t-180 p-b-100 font-poppins">
    <div class="wrapper wrapper--w780">
        <div class="card card-3">
            <div class="card-heading"></div>
            <div class="card-body">
                <h2 class="title">Registration Info</h2>
                <form method="POST" action="/user/register" onsubmit="return checkPw()">
                    <div class="input-group">
                        <input class="input--style-3" type="text" placeholder="Name" name="name">
                    </div>
                    <div class="input-group">
                        <div class="rs-select2 js-select-simple select--no-search">
                            <select name="gender">
                                <option disabled="disabled" selected="selected">Gender</option>
                                <option>Male</option>
                                <option>Female</option>
                                <option>Other</option>
                            </select>
                            <div class="select-dropdown"></div>
                        </div>
                    </div>
                    <div class="input-group">
                        <input class="input--style-3" type="email" placeholder="Email" name="email">
                    </div>
                    <div class="input-group">
                        <input class="input--style-3" type="password" placeholder="Password" id = "pw" name="password">
                    </div>
                    <div class="input-group">
                        <input class="input--style-3" type="password" placeholder="Password Confrim" id = "pw-c" name="password-confirm">
                    </div>
                    <div class="p-t-10" style="text-align: center">
                        <button class="btn btn--pill btn--green" type="submit">Submit</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<!-- Jquery JS-->
<script src="/resources/vendor/jquery/jquery.min.js"></script>
<!-- Vendor JS-->
<script src="/resources/vendor/select2/select2.min.js"></script>
<script src="vendor/datepicker/moment.min.js"></script>

<!-- Main JS-->
<script src="/resources/js/global.js"></script>


<script>
    function checkPw() {
        var pw = $('#pw').val()
        var pwc = $('#pw-c').val()
        if(pw != pwc){
            alert("비밀번호가 틀렸습니다. 다시 입력해 주세요")
            return false;
        }

    }
</script>

</body><!-- This templates was made by Colorlib (https://colorlib.com) -->


</html>



