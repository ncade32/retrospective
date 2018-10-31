<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.retrospective.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Retrospective Login</title>
</head>
<body>

<script>
	function accessGranted(){
		if (<%= Login.access%> == false){
			window.alert("Username or password incorrect. Try again.");
			<% Login.access = true;%>
			return false;
		}
	}
	window.onload = accessGranted;
</script>

<form action="Login" method = "post" >

	Username : <input type = "text" id = "user" name = "user"><br>
	Password : <input type = "password" id = "pass" name = "pass"><br>

	<input type = "submit" value = "Login" id = "login" name = "login">
	<input formaction = "register.jsp" type = "submit" value = "Register" id = "register" name = "register">
	
</form>

</body>

<script type="text/javascript" src="restrictions.js"></script>

</html>