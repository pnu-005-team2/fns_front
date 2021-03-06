<%@ page import="com.team2.webservice.sprint1.vo.Member" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit My Profile</title>
</head>
<body style="background-color: #FF6F61">
    <link rel="stylesheet" type="text/css" href="/resources/css/global.css">
    <link rel="stylesheet" type="text/css" href="/resources/css/editprofile.css">
    <script src="//code.jquery.com/jquery.min.js"></script>

<%
    Member user = (Member)session.getAttribute("login");
    String email = user.getEmail();
    String name = user.getName();
    String gender = user.getGender();
    String intro = user.getIntro();
    String img = user.getImg();
%>
    <jsp:include page="header.jsp"/>
<div class="page-container">

<div class="secondform">
    <br/>
    <div><h2>Edit profile</h2></div>
    <div class="profile">
        <%--<img src="https://pm1.narvii.com/6797/138261efcbe6a60b64a4e701d3e5b04b4a543ae4v2_128.jpg">--%>
        <div id="imagePreview">
            <c:if test="${login.getImg() eq 'no img'}">
            <img src="/resources/images/user_img.svg">
            </c:if>
            <c:if test="${login.getImg() ne 'no img'}">
                <img src="<%=img%>">
            </c:if>
        </div>
        <strong><%=email%></strong>
    </div>
    <form name="myForm" method="POST" action="/user/edit"  enctype="multipart/form-data">
        <div>
            <label for="name">Name</label>
            <input type="text" placeholder="<%=name%>" name="name">
        </div>
        <div>
            <label for="password">Password</label>
            <input type="password" placeholder="helloworld12" name="password">
        </div>
        <div>
            <label for="gender">Gender</label>
            <input type="text" placeholder="<%=gender%>" name="gender">
        </div>
        <div>
            <label for="name">About me</label>
            <textarea type="text" name="intro" placeholder="<%=intro%>"/> </textarea>
        </div>
        <input type="hidden" name="email" value= "<%=email%>">
        <input class="profile-button pnt" type="submit" value="Update">
        &nbsp;
        <div class="file-box profile-button pnt">
            <label for="profile-img" class="pnt">Image</label>
            <input  name = "image" type="file" accept = "image/*" id = "profile-img" onchange="changeImg(this)">
        </div>
        &nbsp;
        <input class="profile-button pnt" type="reset"  onclick="window.history.go(-1); return false;" value="Cancel">
    </form>
</div>
    <script>
        window.addEventListener( "load", ()=>{
            console.log("edit load complete");
            //Todo 프로필 이미지 가지고 와서 넘겨줘야해
            // let img = document.getElementById("profile-img");
            // let blob = new Blob(img);
            // console.log(blob);
        } );

        function validateForm() {
            var name = document.forms["myForm"]["name"].value;
            var password = document.forms["myForm"]["password"].value;
            var gender = document.forms["myForm"]["gender"].value;
            var nameLength = name.length;
            var passwordLength = password.length;
            if (name == "" && password == "" && gender == "") {
                alert("All fields must be filled out");
                return false;
            }
            if (name == "") {
                alert("Name must be filled out");
                return false;
            }
            if (password == "") {
                alert("Password must be filled out");
                return false;
            }
            if (gender == "") {
                alert("Gender must be filled out");
                return false;
            }
            if (nameLength < 5) {
                alert("The Name must be at least 5 words long");
                return false;
            }
            if (passwordLength < 5) {
                alert("The password must be at least 5 symbols long");
                return false;
            }
            if (nameLength >=5 && passwordLength >=5 && (gender == "male" || gender == "female")) {
                alert("The information was successfully changed");
                return true;
            }
            if (gender == "male" || gender == "female") {
                return true;
            }
            else {
                alert("Invalid gender");
                return false;
            }
        }

        //--------프로필 사진을 뷰에서 바꿔줍니다----------
        function changeImg(target) {
            let fileList = target.files;
            let reader = new FileReader();
            reader.readAsDataURL(fileList[0]);

            reader.onload = function () {
                let img = document.querySelector("#imagePreview img");
                console.log(img);
                img.src = reader.result
            }
        }

    </script>


</body>
</html>

