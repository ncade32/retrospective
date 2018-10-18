<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>

<form action="Login" method = "post">

<table>
	<tbody>
		<tr>
			<td>Username: </td>
			<td><input type = "text" id = "user" name = "user"></td>
		</tr>
		<tr>
			<td>Password : </td>
			<td><input type = "text" id = "pass" name = "pass"></td>	
		</tr>
	</tbody>
</table>

<input type = "submit" value = "Submit" id = "submit" name = "submit">

</form>

</body>
</html>