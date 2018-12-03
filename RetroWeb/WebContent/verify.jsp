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
	//Connect to database
	System.out.println("Verify Page");
	Connection conn = DbManager.connect();
	
	if (conn == null){
		System.out.println("Connection failed to verify info");
	}else{
		System.out.println("Connection successful to verify info");
	}
	
	//This handles users hitting the back button after successfully entering a comment
	if(session.getAttribute("dataEntered").equals(false)){
		if(GetData.isScrum(conn, session.getAttribute("user").toString())){
			response.sendRedirect("welcomeScrum.jsp");
			return;
		}else{
			response.sendRedirect("welcome.jsp");
			return;
		}
	}
	
	String uname, teamNum, projName, sprintNum, wrong, well, improve;
	String [] wrongComments, wellComments, improveComments;
	
	//Retrieve all neccessary info from database to appear on verification page
	uname = session.getAttribute("user").toString();
	projName = GetData.getProjectNameByUser(conn, uname);
	sprintNum = GetData.getSprintNumByUser(conn, uname);
	teamNum = GetData.getTeamNumByUser(conn, uname);
	wrong = GetData.getWrongCommentsByUser(conn, uname);
	well = GetData.getWellCommentsByUser(conn, uname);
	improve = GetData.getImproveCommentsByUser(conn, uname);
	boolean isScrum = GetData.isScrum(conn, uname);
	
	GetData.closeConnection(conn);
	
	wrongComments = GetData.splitComments(wrong);
	wellComments = GetData.splitComments(well);
	improveComments = GetData.splitComments(improve);
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
			<%if(isScrum){ %>
				<a href="welcomeScrum.jsp" id = "title" class = "retro-title">Retrospective</a>
			<%}else{ %>
				<a href="welcome.jsp" id = "title" class = "retro-title">Retrospective</a>
			<%} %>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav navbar-right">
			<%if(isScrum){ %>
				<li><a href="retroCommentsByName.jsp">Retrospective Comments</a>
				<li><a id="logout" href="Logout">Logout</a>
			<%}else{ %>
				<li><a id="logout" href="Logout">Logout</a>
			<%} %>
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
			<td id="teamNum" class="enterInfo"><%= teamNum %></td>
		</tr>
		<tr>
			<td class="enterInfo">Project :</td>	
			<td id="projName" class="enterInfo"><%= projName %></td>
		</tr>
		<tr>
			<td class="enterInfo">Sprint Number: </td>
			<td id="sprintNum" class="enterInfo"><%= sprintNum %></td>
		</tr>
	</tbody>
</table>

<p>
        <label for="new-task">What went wrong?</label>
      </p>
 

      <ul>
      		<%for (int i = 0; i < wrongComments.length; i++){ %>
						<li class="li-tasks-group"><% out.print(wrongComments[i]); %></li>
			<%} %>
       
      </ul>
      
      <p>
        <label for="wellNew-task">What went Well?</label>
      </p>
      
   
      <ul>
      		<%for (int i = 0; i < wellComments.length; i++){ %>
						<li class="li-tasks-group"><% out.print(wellComments[i]); %></li>
			<%} %>
      </ul>

      <p>
        <label for="improveNew-task">What needs improvement?</label>
      </p>
      
     
      <ul>
      		<%for (int i = 0; i < improveComments.length; i++){ %>
						<li class="li-tasks-group"><% out.print(improveComments[i]); %></li>
			<%} %>
      </ul>
	<div class="done-edit-btn">
		<button  type= "submit" class="btn" id = "done" name = "done">Done</button>
		<button formaction="editInfo.jsp" type= "submit" class="btn" id = "edit" name = "edit">Edit</button>
	</div>
</div>

</form>

</body>
</html>