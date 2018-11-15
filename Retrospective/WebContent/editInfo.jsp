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
	
	System.out.println(session.getAttribute("user"));
	
	if(session.getAttribute("user") == null){
		response.sendRedirect("loginPage.jsp");
		return;
	}
	
	//Connect to database
	System.out.println("Edit Page");
	Connection conn = DbManager.connect();

	if (conn == null){
		System.out.println("Connection failed to edit info");
	}else{
		System.out.println("Connection successful to edit info");
	}
	
	ArrayList<String> projNames = new ArrayList<String>();
	//Get all of the project names
	projNames = GetData.getProjectNames(conn);
	
	String uname, teamNum, projName, sprintNum, wrong, well, improve;
	String [] wrongComments, wellComments, improveComments;
	//Retrieve all of the info entered by the user for this comment that is being edited
	uname = session.getAttribute("user").toString();
	teamNum = GetData.getTeamNumByUser(conn, uname);
	projName = GetData.getProjectNameByUser(conn, uname);
	sprintNum = GetData.getSprintNumByUser(conn, uname);
	wrong = GetData.getWrongCommentsByUser(conn, uname);
	well = GetData.getWellCommentsByUser(conn, uname);
	improve = GetData.getImproveCommentsByUser(conn, uname);
	
	boolean isScrum = GetData.isScrum(conn, uname);
	
	GetData.closeConnection(conn);
	
	wrongComments = GetData.splitComments(wrong);
	wellComments = GetData.splitComments(well);
	improveComments = GetData.splitComments(improve);
%>

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
  
<form name = "editForm" Action="SubmitData" method = "post" onsubmit="return validateEditForm()" method="post">
<div class="container">
<h1 align="center">Edit Comments</h1>
	<br/>
<table>
	<tbody>
		<tr>
			<td class="enterInfo">Enter Team Number: </td>
			<td><input type="text" id = "teamNum" name="teamNum" value = <%= teamNum %> size = "5%" ></td>
		</tr>
		<tr>
			<td class="enterInfo">Choose Project :</td>	
			<td>
				<select id = "chooseProj" name = "chooseProj" >
				<option value= "">Choose a project...</option>
					<%for (int i = 0; i < projNames.size(); i++){ %>
						<%if (projNames.get(i).equals(projName)){ %>
							<option selected = "selected"><% out.print(projNames.get(i)); %></option>
						<%}
						else{ %>
							<option><% out.print(projNames.get(i)); %></option>
					<%	}
					  }%>
				</select>
				
			</td>
		</tr>
		<tr>
			<td class="enterInfo">Enter Sprint Number: </td>
			<td><input type="text" id = "sprintNum" name="sprintNum" value = <%= sprintNum %> size = "5%" ></td>
		</tr>
	</tbody>
</table>


      <p>
        <label for="new-task">What went wrong?</label><input id="new-task2" type="text"><button class="add-button" id = "addWrong" type = "button">Add</button>
      </p>
 

      <ul id="incomplete-tasks2">
      		<%for(int i = 0; i < wrongComments.length; i++){ %>
      			<li class="li-tasks-group"><textarea readonly name = "wrong" rows="" cols=""><%= wrongComments[i]%></textarea><button type = "button" class="edit">Edit</button><button type = "button" class="delete">Delete</button></li>
       		<%} %>
      </ul>
      
      <p>
        <label for="wellNew-task">What went Well? (Optional)</label><input  id="wellNew-task2" type="text"><button class="add-button" id = "addWell"type = "button">Add</button>
      </p>
      
   
      <ul id="complete-tasks2">
      		<%for(int i = 0; i < wellComments.length; i++){ %>
      				<li class="li-tasks-group"><textarea readonly name = "well"><%= wellComments[i]%></textarea><button type = "button" class="edit">Edit</button><button type = "button" class="delete">Delete</button></li>
       		<%} %>
       		
      </ul>

      <p>
        <label for="improveNew-task">What needs improvement? (Optional)</label><input  id="improveNew-task2" type="text"><button class="add-button" id = "addImprove" type = "button">Add</button>
      </p>
      
     
      <ul id="improve-tasks2">
      		<%for(int i = 0; i < improveComments.length; i++){ %>
      				<li class="li-tasks-group"><textarea readonly name = "improve"><%= improveComments[i]%></textarea><button type = "button" class="edit">Edit</button><button type = "button" class="delete">Delete</button></li>
       		<%} %>
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