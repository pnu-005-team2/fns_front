<%@ page import="com.team2.webservice.sprint1.vo.Member" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
         pageEncoding="EUC-KR"%>
<!DOCTYPE html>
<html>
<head>
    <title>Edit My Profile</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/editprofile.css">
</head>
<body>

<%--
    <%= i %>는 jsp에서 값 표기법이며, i는 변수
    ${ i }는 EL에서 값의 표기법이며, i는 이름
    EL표기법에서 파라미터 값은 param키워드를 통해 가지고 올 수 있다.
    session에 있는 값은 키로만 가져 올 수 있다.
--%>

<%
  Member user = (Member)session.getAttribute("login");
  String email = user.getEmail();
  String name = user.getName();
  String gender = user.getGender();
  String img = user.getImg();
%>

<div id="editprofile">
    <h1>Edit Profile</h1>
    <div class="my container">
        <div class="avatar-upload">
            <div class="avatar-edit">
                <form method="post" action="/user/edit" enctype="multipart/form-data" onsubmit="return validateForm()">
                    <div class="avatar-preview">
                        <div id="imagePreview"></div>
                        <c:if test='${login.getImg()  eq "no img"}'>
                            <img src="/resources/images/user_img.svg">
                        </c:if>
                        <c:if test="${!img.equals('no img')}">
                            <img src="<%=img%>">
                        </c:if>

                    </div>
                    <input name = "image" type="file" accept = "image/*" class="file-input" id="imageUpload">
                    <div class="email-item">Email : <%=email%></div>
                    <input type="hidden" name="email" value= "<%=email%>">
                    <input type="text" placeholder="<%=name%>" name="name"/><br/>
                    <div class="profile-item">gender : <%=gender%></div>
                    <textarea placeholder="About me" name="intro"/></textarea><br/>
                    <input type="submit" value="Submit">
                    <input type="reset"  value="Cancel">
                </form>
            </div>
        </div>
    </div>
</div>


<script>
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
        if (gender != "male" || gender != "female") {
            return true;
        }
        else {
            alert("Invalid gender");
            return false;
        }
    }

    function readURL(input) {
        if (input.files && input.files[0]) {
            var reader = new FileReader();
            reader.onload = function(e) {
                $('#imagePreview').css('background-image', 'url('+e.target.result +')');
                $('#imagePreview').hide();
                $('#imagePreview').fadeIn(650);
            }
            reader.readAsDataURL(input.files[0]);
        }
    }
    $("#imageUpload").change(function() {
        readURL(this);
    });
</script>

</body>
</html>







