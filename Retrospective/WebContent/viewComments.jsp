<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.retrospective.*" %>
<%@ page import= "java.sql.*" %>
<%@ page import= "java.util.*" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>View Retrospective Comments</title>
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
	
	//Connects to database
	System.out.println("View Retrospective Comments Page");
	Connection conn = DbManager.connect();
	
	if (conn == null){
		System.out.println("Connection failed to view comments");
	}else{
		System.out.println("Connection successful to view comments");
	}
	
	int team, sprint;
	String project, fullName;
	
	//Get the comment that was chosen by the scrum master
	String chosenComment = RetroComments.chosenComment;
	String [] info = chosenComment.split("-;");
	/*The comment id is retrieved and split
	 *The comments id are stored as (team-;projectName-;sprint)*/
	team = Integer.parseInt(info[0]);
	project = info[1];
	sprint = Integer.parseInt(info[2]);
	
	int tableCols = 3;
	
	String[] wrong, well, improve, projInfoSplit;
	//Get the user's name of the comment that was clicked on
	fullName = RetroComments.chosenName;
	System.out.println(fullName);
	
	//Retrieve all the comments from the database
	wrong = GetData.viewWrongComments(conn, fullName, team, project, sprint);
	well = GetData.viewWellComments(conn, fullName, team, project, sprint);
	improve = GetData.viewImproveComments(conn, fullName, team, project, sprint);
	
	
	
	
%>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
	<div class="container-fluid">
		<div class="navbar-header">
		  <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
				<span class="icon-bar"></span>
			</button>
			<a href="welcomeScrum.jsp" id = "title" class = "retro-title">Retrospective</a>
		</div>
		
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="retroCommentsByName.jsp">Retrospective Comments</a>
				<li><a id="logout" href="Logout">Logout</a>
				</ul>
		</div>
	</div>
</nav>

<div class="container">
	<h3 align="center">Comments</h3>
	<br/>
	<form name = "projForm" id = "projForm" action="RetroComments" method = "post">
	<p>
	        <label for="new-task">What went wrong?</label>
	      </p>
	 
	
	      <ul>
	      		<%for (int i = 0; i < wrong.length; i++){ %>
							<li class="li-tasks-group"><% out.print(wrong[i]); %></li>
				<%} %>
	       
	      </ul>
	      
	      <p>
	        <label for="wellNew-task">What went Well?</label>
	      </p>
	      
	   
	      <ul>
	      		<%for (int i = 0; i < well.length; i++){ %>
							<li class="li-tasks-group"><% out.print(well[i]); %></li>
				<%} %>
	      </ul>
	
	      <p>
	        <label for="improveNew-task">What needs improvement?</label>
	      </p>
	      
	     
	      <ul>
	      		<%for (int i = 0; i < improve.length; i++){ %>
							<li class="li-tasks-group"><% out.print(improve[i]); %></li>
				<%} %>
	      </ul>
	
	
	<input type="hidden" name = "webpage" id="webpage" value="viewComments" />
	</form>
	<button onclick="window.history.back()" type= "submit" class="btn" id = "back" name = "back">Back</button>
</div>



<script type="text/javascript" src="retroTableFunctionality.js"></script>

</body>
</html>