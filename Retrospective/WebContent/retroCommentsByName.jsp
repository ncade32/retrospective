<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.retrospective.*" %>
<%@ page import= "java.sql.*" %>
<%@ page import= "java.util.*" %>


<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width, intial-scale=1" charset="UTF-8">
<title>Retrospective Comments By Name</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
  	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
  	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  	<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.0.8/css/solid.css">
  	<script src="https://use.fontawesome.com/releases/v5.0.7/js/all.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
	<script type="text/javascript" src="dataTables.bootstrap.min.js"></script>
	<link rel="stylesheet" href="styleSheets/dataTables.bootstrap.min.css"/>
	<link rel="stylesheet" href="styleSheets/retroCommentsStyle.css" type="text/css" media="screen">
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
	
	//Connect to database
	System.out.println("View Retrospective Comments Page By Name");
	Connection conn = DbManager.connect();
	
	if (conn == null){
		System.out.println("Connection failed to view comments by name");
	}else{
		System.out.println("Connection successful to view comments by name");
	}
	
	
	ArrayList<String> allNames = new ArrayList<String>();
	int tableCols = 2;
	
	//Get all of the names that have ever entered a comment
	allNames = GetData.getAllFeedbackNames(conn);
	
	/*for(int a = 0; a < allNames.size(); a++){
		System.out.println(allNames.get(a));
	}
	System.out.println(allNames.size())	;*/
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
	<h3 align="center">Team Members</h3>
	<br/>
	<div class="table-responsive">
		<div class="col-lg-8 col-lg-offset-2">
			<form name = "nameForm" id = "nameForm" action="RetroComments" method = "post">
				<table id = "retroComments" class="table table-bordered table-hover" >
					<thead>
						<tr>
							<th>First Name</th>
							<th>Last Name</th>
						</tr>
					</thead>
					<tbody>
							<% int j = 0;%>
							<%for(int i = 0; i < (allNames.size()/tableCols); i++){ %>
								<tr onclick = "clickedName(this)" id = <%= allNames.get(j)+"-;"+allNames.get(j+1) %>>
									<%for(int k = 0; k < tableCols; k++){%>
										<td><%= allNames.get(j) %></td>
										<% System.out.println(j);	%>
										<%j++; %>			
										<td><%= allNames.get(j) %></td>
										<% System.out.println(j); %>
										<%j++; %>			
										<%k = k + tableCols; %>
									<%} %>
								</tr>
							<%} %>
					</tbody>
				</table>
				<input type="hidden" name = "clickedName" id="clickedName" value=" " />
				<input type="hidden" name = "webpage" id="webpage" value="retroCommentsByName" />
			</form>
		</div>
	</div>
</div>

<script type="text/javascript" src="retroTableFunctionality.js"></script>
<script type="text/javascript">
	$(document).ready(function(){
		$('#retroComments').DataTable();
	});

</script>
</body>
</html>

