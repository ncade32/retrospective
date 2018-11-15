<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "com.retrospective.*" %>
<%@ page import= "java.sql.*" %>
<%@ page import= "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, intial-scale=1" charset="UTF-8">
<title>Welcome Scrum Master</title>
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
	System.out.println("Scrum Welcome Page");
	Connection conn = DbManager.connect();

	if (conn == null){
		System.out.println("Connection failed");
	}else{
		System.out.println("Connection successful");
	}
	
	//Collects all the names of the projects from database
	ArrayList<String> projNames = new ArrayList<String>();
	projNames = GetData.getProjectNames(conn);
	GetData.closeConnection(conn);
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

  
<form name = "welcomeForm" Action="SubmitData" method = "post" onsubmit="return validateWelcomeForm()" method="post">
<div class="container">
<table>
	<tbody>
		<tr>
			<td class="enterInfo">Enter Team Number: </td>
			<td><input type="text" id = "teamNum" name="teamNum" size = "1%" ></td>
		</tr>
		<tr>
			<td class="enterInfo">Choose Project :</td>	
			<td>
				<select id = "chooseProj" name = "chooseProj" >
				<option value= "">Choose a project...</option>
					<%for (int i = 0; i < projNames.size(); i++){ %>
						<option><% out.print(projNames.get(i)); %></option>
					<%} %>
				
					
				
				</select>
				
			</td>
		</tr>
		<tr>
			<td class="enterInfo">Enter Sprint Number: </td>
			<td><input type="text" id = "sprintNum" name="sprintNum" size = "1%" ></td>
		</tr>
	</tbody>
</table>


      <p>
        <label for="new-task">What went wrong?</label><input id="new-task" type="text"><button class="add-button" id = "addWrong" type = "button">Add</button>
      </p>
 

      <ul id="incomplete-tasks">
      </ul>
      
      <p>
        <label for="wellNew-task">What went Well? (Optional)</label><input size = "26.5%" id="wellNew-task" type="text"><button class="add-button" id = "addWell"type = "button">Add</button>
      </p>
      
   
      <ul id="complete-tasks">
      </ul>

      <p>
        <label for="improveNew-task">What needs improvement? (Optional)</label><input size = "26.5%" id="improveNew-task" type="text"><button class="add-button" id = "addImprove" type = "button">Add</button>
      </p>
      
     
      <ul id="improve-tasks">
      </ul>
    
    
 

<button type= "submit" class="btn" id = "submit" name = "submit">Submit</button>
</div>
</form>


<script type="text/javascript" src="restrictions.js"></script>
<script type="text/javascript" src="ListFunctionality/wrongList.js"></script> 
<script type="text/javascript" src="ListFunctionality/wellList.js"></script>
<script type="text/javascript" src="ListFunctionality/improveList.js"></script>

</body>
</body>
</html>