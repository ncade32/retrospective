<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.retrospective.*" %>
<%@ page import= "java.sql.*" %>
<%@ page import= "java.util.*" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Retrospective Comments By Project</title>
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
<script type="text/javascript">
	$(document).ready(function(){
		$('#retroComments').DataTable();

	});
</script>

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
	System.out.println("View Retrospective Comments Page By Project");
	Connection conn = DbManager.connect();
	
	if (conn == null){
		System.out.println("Connection failed to view comments by project");
	}else{
		System.out.println("Connection successful to view comments by project");
	}
	
	//Get the name that was chosen by the scrum master
	String name = RetroComments.chosenName;
	String [] firstAndLast = name.split("-;");
	/*The name id is retrieved and split
	 *The names id are stored as (first-;last)*/
	String first, last;
	first = firstAndLast[0];
	last = firstAndLast[1];
	String nameTitle = first +" "+last+"'s"+" Comments";
	
	ArrayList<Object> projInfo = new ArrayList<Object>();
	int tableCols = 3;
	
	//Get all the comments entered by this name
	projInfo = GetData.getProjEntryInfo(conn, first, last);
	
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
	<h3 align="center"><%=nameTitle%></h3>
	<br/>
	<div class="table-responsive">
		<div class="col-lg-8 col-lg-offset-2">
			<form name = "projForm" id = "projForm" action="RetroComments" method = "post">
			<table id = "retroComments" class="table table-bordered table-hover" >
				<thead>
					<tr>
						<th>Team</th>
						<th>Project Name</th>
						<th>Sprint</th>
					</tr>
				</thead>
				<tbody>
						<% int j = 0;%>
						<%for(int i = 0; i < (projInfo.size()/tableCols); i++){ %>
							<tr onclick = "clickedProject(this)" id = <%= projInfo.get(j)+"-;"+projInfo.get(j+1)+"-;"+projInfo.get(j+2) %>>
								<%for(int k = 0; k < tableCols; k++){%>
									<td align="center"><%= projInfo.get(j) %></td>
									<% System.out.println(j);	%>
									<%j++; %>			
									<td align="center"><%= projInfo.get(j) %></td>
									<% System.out.println(j); %>
									<%j++; %>
									<td  align="center"><%= projInfo.get(j) %></td>
									<% System.out.println(j); %>
									<%j++; %>						
									<%k = k + tableCols; %>
								<%} %>
							</tr>
						<%} %>
						
				
				</tbody>
			</table>
			
			<input type="hidden" name = "clickedProject" id="clickedProject" value=" " />
			<input type="hidden" name = "webpage" id="webpage" value=" retroCommentsByProject" />
			</form>
		</div>
	</div>
</div>



<script type="text/javascript" src="retroTableFunctionality.js"></script>

</body>
</html>