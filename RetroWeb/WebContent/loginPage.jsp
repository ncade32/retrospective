<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.retrospective.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Retrospective Login</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
  <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css">
  <script src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
  <link rel="stylesheet" type="text/css" href="styleSheets/loginStyle.css">
</head>
<body>
<%//System.out.println(session.getAttribute("user"));
%>
<div class = "modal-dialog text-center">
		<div class="col-sm-8 main-section">
			<div class="modal-content">
			
				<!-- <div class="col-12 user-img">
					<img  src="img/face.png">
				</div> -->
				
				<form action="Login" method="post" class="col-12">
				
					<div class="form-title">
						RETROSPECTIVE
					</div>
					<div id="loginError" class="error-group" style="color: #FF0000;">${loginError}</div>
					<div class="form-group">
						<input type = "text" class="form-control" id = "user" name = "user" placeholder="Enter Username">
					</div>
					<div class="form-group">
						<input type = "password" class="form-control" id = "pass" name = "pass"  placeholder="Enter Password">
					</div>
					<button type= "submit" class="btn" id = "login" name = "login"><i class="fas fa-sign-in-alt"></i>Login</button>
					
				</form>
				
				<div class="col-12 register">
					<a href="register.jsp">Register Account</a>
				</div>
				
			</div>
		</div>
	</div>

</body>

<script type="text/javascript" src="restrictions.js"></script>

</html>