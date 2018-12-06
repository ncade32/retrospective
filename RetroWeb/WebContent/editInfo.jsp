<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "com.retrospective.*" %>
<%@ page import= "java.sql.*" %>
<%@ page import= "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, intial-scale=1" charset="UTF-8">
<title>Edit Retrospective</title>
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
	
	if(session.getAttribute("user") == null){
		response.sendRedirect("loginPage.jsp");
		return;
	}
	
	System.out.println("Edit Page");
	
%>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
		  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
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
  
<form name = "editForm" Action="SubmitData" method = "post" onsubmit="return validateEditForm()" method="post">
<div class="container">
<h1 align="center">Edit Comments</h1>
	<br/>
<table>
	<tbody>
		<tr>
			<td class="enterInfo">Enter Team Number: </td>
			<td><input type="text" id = "teamNum" name="teamNum" value = "${teamNum}" size = "5%" ></td>
		</tr>
		<tr>
			<td class="enterInfo">Choose Project :</td>	
			<td>
				<select id = "chooseProj" name = "chooseProj" >
				<option value= "">Choose a project...</option>
					<c:forEach items="${allProjects}" var="project">
						<c:choose>
							<c:when test="${project.getProjectName().equals(projectName)}">
								<option selected = "selected">${projectName}</option>
							</c:when>
								<c:otherwise><option>${project.getProjectName()}</option></c:otherwise>
						</c:choose>
					</c:forEach>
				</select>
				
			</td>
		</tr>
		<tr>
			<td class="enterInfo">Enter Sprint Number: </td>
			<td><input type="text" id = "sprintNum" name="sprintNum" value = "${sprintNum}" size = "5%" ></td>
		</tr>
	</tbody>
</table>


      <p>
        <label for="new-task">What went wrong?</label><input id="new-task2" type="text"><button class="add-button" id = "addWrong" type = "button">Add</button>
      </p>
 

      <ul id="incomplete-tasks2">
      		<c:forEach items="${wrongInfoComments}" var="wrong">
      			<li class="li-tasks-group"><textarea readonly name = "wrong" rows="" cols="">${wrong}</textarea><button type = "button" class="edit">Edit</button><button type = "button" class="delete">Delete</button></li>
       		</c:forEach>
      </ul>
      
      <p>
        <label for="wellNew-task">What went Well? (Optional)</label><input  id="wellNew-task2" type="text"><button class="add-button" id = "addWell"type = "button">Add</button>
      </p>
      
   
      <ul id="complete-tasks2">
      		<c:forEach items="${wellInfoComments}" var="well">
      			<c:if test="${well != 'none' }">
      				<li class="li-tasks-group"><textarea readonly name = "well" rows="" cols="">${well}</textarea><button type = "button" class="edit">Edit</button><button type = "button" class="delete">Delete</button></li>
       			</c:if>
       		</c:forEach>
       		
      </ul>

      <p>
        <label for="improveNew-task">What needs improvement? (Optional)</label><input  id="improveNew-task2" type="text"><button class="add-button" id = "addImprove" type = "button">Add</button>
      </p>
      
     
      <ul id="improve-tasks2">
      		<c:forEach items="${improveInfoComments}" var="improve">
      			<c:if test="${improve != 'none' }">
      				<li class="li-tasks-group"><textarea readonly name = "improve" rows="" cols="">${improve}</textarea><button type = "button" class="edit">Edit</button><button type = "button" class="delete">Delete</button></li>
       			</c:if>
       		</c:forEach>
      </ul>
    
    
<button type= "submit" class="btn" id = "submit" name = "submit">Submit</button>
</div>
</form>


<script type="text/javascript" src="restrictions.js"></script>
<script type="text/javascript" src="ListFunctionality/wrongListEdit.js"></script> 
<script type="text/javascript" src="ListFunctionality/wellListEdit.js"></script>
<script type="text/javascript" src="ListFunctionality/improveListEdit.js"></script>

</body>

</html>