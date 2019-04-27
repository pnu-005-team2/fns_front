<!DOCTYPE html>
<html>
<head>
<title>Edit My Profile</title>
<link rel="stylesheet" type="text/css" href="/resources/css/editprofile.css">
</head>
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
</script>

<body>

<div id="editprofile">
  <h1>Edit Profile</h1>
  <div class="my">
    <div class="email-item"> sch0115@naver.com </div>
     <form name="myForm" action="/user/edit" onsubmit="return validateForm()" method="POST">
     Name: <input type="text"  name="name">
     Password: <input type="password" placeholder="Password" name = "password"/>
     Gender: <input type="text" placeholder="male/female" name = "gender"/>
     About: <textarea placeholder="About me" name = "intro"/></textarea>
     <input type="submit" value="Submit">
    </form>
  </div>
</div>

</body>
</html>





