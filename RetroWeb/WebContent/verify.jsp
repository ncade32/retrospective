<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "com.retrospective.*" %>
<%@ page import= "java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, intial-scale=1" charset="UTF-8">
<title>Verification</title>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css">
  	<script src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
 	<link rel="stylesheet" href="styleSheets/style.css" type="text/css" media="screen"> 
</head>
<body>
<%
	/*This code makes sure that the user can not revisit the page 
 	*by hitting the back button after logging out*/
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	
	System.out.println(session.getAttribute("user"));
	if(session.getAttribute("user") == null){
		response.sendRedirect("loginPage.jsp");
		return;
	}
	
	System.out.println("Verify Page");
	
	//This handles users hitting the back button after successfully entering a comment
	if(session.getAttribute("dataEntered").equals(false)){
		if(Integer.parseInt(session.getAttribute("scrum").toString()) == 1){
			response.sendRedirect("welcomeScrum.jsp");
			return;
		}else{
			response.sendRedirect("welcome.jsp");
			return;
		}
	}
	
%>
<script type="text/javascript">
function confirmed(){
	window.alert("Retrospective Comments Saved");
	return true;
}
</script>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
		  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<c:if test="${scrum == 1 }">
				<a href="welcomeScrum.jsp" id = "title" class = "retro-title">Retrospective</a>
			</c:if>
				<a href="welcome.jsp" id = "title" class = "retro-title">Retrospective</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav navbar-right">
			<c:if test="${scrum == 1 }">
				<li><a href="retroCommentsByName.jsp">Retrospective Comments</a>
				<li><a id="logout" href="Logout">Logout</a>
			</c:if>
			<c:if test="${scrum == 0 }">
				<li><a id="logout" href="Logout">Logout</a>
			</c:if>
			</ul>
		</div>
	</div>
</nav>

<form action="Confirmed" method="post" >
<div class="container">
<h1 align="center">Verify Comments</h1>
	<br/>
<table>
	<tbody>
		<tr>
			<td class="enterInfo">Team Number: </td>
			<td id="teamNum" class="enterInfo">${teamNum}</td>
		</tr>
		<tr>
			<td class="enterInfo">Project :</td>	
			<td id="projName" class="enterInfo">${projectName}</td>
		</tr>
		<tr>
			<td class="enterInfo">Sprint Number: </td>
			<td id="sprintNum" class="enterInfo">${sprintNum}</td>
		</tr>
	</tbody>
</table>

<p>
        <label for="new-task">What went wrong?</label>
      </p>
 

      <ul>
      		<c:forEach items="${wrongInfoComments}" var="wrong">
						<li class="li-tasks-group">${wrong}</li>
			</c:forEach>
       
      </ul>
      
      <p>
        <label for="wellNew-task">What went Well?</label>
      </p>
      
   
      <ul>
      		<c:forEach items="${wellInfoComments}" var="well">
						<li class="li-tasks-group">${well}</li>
			</c:forEach>
      </ul>

      <p>
        <label for="improveNew-task">What needs improvement?</label>
      </p>
      
     
      <ul>
      		<c:forEach items="${improveInfoComments}" var="improve">
						<li class="li-tasks-group">${improve}</li>
			</c:forEach>
      </ul>
	<div class="done-edit-btn">
		<button  type= "submit" class="btn" id = "done" name = "done">Done</button>
		<button formaction="editInfo.jsp" type= "submit" class="btn" id = "edit" name = "edit">Edit</button>
	</div>
</div>

</form>

</body>
</html>