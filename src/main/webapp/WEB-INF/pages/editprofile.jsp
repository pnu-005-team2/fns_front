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

<body>

<div id="editprofile">
  <h1>Edit Profile</h1>
  <div class="my">

   <div class="container">
    <div class="avatar-upload">
        <div class="avatar-edit">
            <input type='file' id="imageUpload" accept=".png, .jpg, .jpeg" />
            <label for="imageUpload"></label>
        </div>
        <div class="avatar-preview">
            <div id="imagePreview" style="background-image: url(https://www.google.com/url?sa=i&rct=j&q=&esrc=s&source=images&cd=&cad=rja&uact=8&ved=2ahUKEwi-8NO5zvDhAhXJXrwKHX3uCO4QjRx6BAgBEAU&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Fperson%2F&psig=AOvVaw3iDNhiJC3MKjuw-tPOts-o&ust=1556466158474197);">
            </div>
        </div>
      </div>
  </div>


    <div class="email-item"> sch0115@naver.com </div>
     <form name="myForm" action="/action_page.php" onsubmit="return validateForm()" method="post">
     Name: <input type="text"  name="name">
     Password: <input type="password" placeholder="Password" name = "password"/>
     Gender: <input type="text" placeholder="male/female" name = "gender"/>
     About: <textarea placeholder="About me" name = "intro"/></textarea>
     <input type="submit" value="Submit">
     <input type="reset"  value="Cancel">
    </form>
  </div>
</div>

</body>
</html>







