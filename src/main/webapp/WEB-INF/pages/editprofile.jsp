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
<div class="secondform">
  <br />
  <h2> &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; Edit profile</h2>
  <br/>
  <div class="profile"><img src="https://pm1.narvii.com/6797/138261efcbe6a60b64a4e701d3e5b04b4a543ae4v2_128.jpg">
    <h1>sch0115@naver.com</h1>
  </div>
  <form name="myForm" action="/user/edit" onsubmit="return validateForm()" method="POST">
    <div>
      <label for="name">Name</label>
      <input type="text" placeholder="Suzy" name="name">
    </div>
      
    <div>
      <label for="password">Password</label>
      <input type="password" placeholder="helloworld12" name="password">
    </div>
    <div>
      <label for="gender">Gender</label>
      <input type="text" placeholder="female" name="gender">
    </div>
    <div>
      <label for="name">About me</label>
      <textarea type="text" name="intro"/> </textarea>
    </div>
    &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
    <input type="submit" value="Update">
    &nbsp; &nbsp;
    <input type="reset"  value="Cancel">
  </form> 
</div>
</body>
</html>

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
  if (gender == "male" || gender == "female") {
    return true;
  }
  else {
    alert("Invalid gender");
    return false;
  }
}

<%
  Member user = (Member)session.getAttribute("login");
  String email = user.getEmail();
  String name = user.getName();
  String gender = user.getGender();
  String img = user.getImg();
%>







