<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.retrospective.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>

<script type="text/javascript">
function isValid(){
	var user = document.getElementById("userReg");
	
	if(<%= Register.userValid%> == false){
		window.alert("Username already taken");
		user.value = "";
		<%Register.userValid = true;%>
		return false;
	}else if (<%= Register.codeValid%> == false){
		window.alert("Incorrect Scrum Code Entered");
		<%Register.codeValid = true;%>
		return false;
	}
}
window.onload = isValid;

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

<form name = "registerForm" Action="Register" onsubmit = "return validateRegForm()" method = "post">
<table id = "regTable">
	<tbody>
		<tr>
			<td>First Name: </td>
			<td><input type="text" id = "first" name="first" ></td>
		</tr>
		<tr>
			<td>Last Name :</td>	
			<td><input type="text" id = "last" name="last" ></td>
		</tr>
		<tr>
			<td>Email: </td>
			<td><input type="text" id = "email" name="email"></td>
		</tr>
		<tr>
			<td>Username: </td>
			<td><input autocomplete="new-password" type="text" id = "userReg" name="userReg"></td>
		</tr>
		<tr>
			<td>Password: </td>
			<td><input autocomplete="new-password" type="password" id = "passReg" name="passReg"><input onClick = "showPassword('passReg', 'showPass1')" type="checkbox" value="Show" id="showPass1">Show</td>
		</tr>
		<tr>
			<td>Confirm Password: </td>
			<td><input type="password" id = "confirmPass" ><input onClick = "showPassword('confirmPass', 'showPass2')" type="checkbox" value="Show" id="showPass2">Show</td>	
		</tr>
		<tr>
			<td>Scrum Master</td>
			<td><input onClick = "scrumCode()" type="checkbox"  id="scrumMaster"></td>	
		</tr>
	</tbody>
</table>
<input type="submit" value="Submit" id="submit"/>
</form>
</body>

<script type="text/javascript" src="restrictions.js"></script>

</html>