<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Retrospective Login</title>
</head>
<body>

<form action="Login" method = "post">

	Username : <input type = "text" id = "user" name = "user"><br>
	Password : <input type = "password" id = "pass" name = "pass"><br>

	<input type = "submit" value = "Login" id = "login" name = "login">
	<input formaction = "register.jsp" type = "submit" value = "Register" id = "register" name = "register">

</form>

</body>
</html>