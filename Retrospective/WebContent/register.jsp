<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.retrospective.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css">
  <script src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
  <link rel="stylesheet" type="text/css" href="styleSheets/registerStyle.css">

<script type="text/javascript">


</script>
</head>
<body>

<%
	
	System.out.println("Register Page");

%>
<script type="text/javascript">
function complete(){
	window.alert("Registration Complete");
	return true;
}
</script>

<div class = "modal-dialog text-center">
		<div class="col-sm-8 main-section">
			<div class="modal-content">
				
				<form action="Register"  method="post" class="col-12" name="regForm" id="regForm">
				
					<div class="form-title">
						REGISTRATION
					</div>
					<div class="error-group" style="color: #FF0000;">${nofirstError}</div>
					<div class="form-group">
						<input type = "text" class="form-control" id = "first" name = "first" placeholder="Enter First Name" value = "${savedFirst}" >
					</div>
					<div class="error-group" style="color: #FF0000;">${nolastError}</div>
					<div class="form-group">
						<input type = "text" class="form-control" id = "last" name = "last"  placeholder="Enter Last Name" value = "${savedLast}">
					</div>
					<div class="error-group" style="color: #FF0000;">${noEmailError}</div>
					<div class="form-group">
						<input type = "text" class="form-control" id = "email" name = "email"  placeholder="Enter Email Address" value = "${savedEmail}">
					</div>
					<div class="error-group" style="color: #FF0000;">${noUserError}</div>
					<div class="error-group" style="color: #FF0000;">${userTakenError}</div>
					<div class="form-group">
						<input type = "text" class="form-control" id = "userReg" name = "userReg" placeholder="Enter Username" value = "${savedUser}">
					</div>
					<div class="error-group" style="color: #FF0000;">${noPassError}</div>
					<div class="form-group">
						<input type = "password" class="form-control" id = "passReg" name = "passReg"  placeholder="Enter Password">
						<input type = "checkbox" id = "showPass" name = "showPass" onclick = "showPassword('passReg','showPass')">
						<label id="showlabel">Show</label>
					</div>
					<div class="error-group" style="color: #FF0000;">${passConfirmError}</div>
					<div class="form-group">
						<input type = "password" class="form-control" id = "confirmPass" name = "confirmPass"  placeholder="Confirm Password">
						<input type = "checkbox" id = "showConfPass" name = "showConfPass" onclick = "showPassword('confirmPass','showConfPass')">
						<label id="showlabel2">Show</label>
					</div>
					<div class="form-check">
    					<input type="checkbox" class="form-check-input" autocomplete="off" onclick="scrumCode()" id="scrumMaster">
    					<label class="form-check-label" >Scrum</label>
  					</div>
  					<div class="error-group" id="mainErrorDiv" style="color: #FF0000;">${scrumCodeError}</div>
  					
					
					<button type= "submit" class="btn" id = "submit" name = "submit">Register</button>
					
				</form>
				
			</div>
		</div>
	</div>

</body>

<script type="text/javascript" src="restrictions.js"></script>

</html>