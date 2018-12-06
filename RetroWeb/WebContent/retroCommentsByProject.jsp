<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	
	System.out.println("View Retrospective Comments Page By Project");
	
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
				<li><a href="retroCommentsByName.jsp">Retrospective Comments</a>
				<li><a id="logout" href="Logout">Logout</a>
				</ul>
		</div>
	</div>
</nav>

<div class="container">
	<h3 align="center">${nameTitle}</h3>
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
					<fmt:parseNumber var="parsedNumber" integerOnly="true" type="number" value="${fn:length(entries) - 1}" />
							<c:forEach var="i"  begin= "0" end="${parsedNumber}" >
							<c:set var="id" value="${entries.get(i).getTeamNum()}-;${entries.get(i).getProjectName()}-;${entries.get(i).getSprintNum()}"/>
								<tr onclick = "clickedProject(this)" id = "${id}">
									<td align="center">${entries.get(i).getTeamNum()}</td>		
									<td align="center">${entries.get(i).getProjectName()}</td>
									<td align="center">${entries.get(i).getSprintNum()}</td>
							</c:forEach>
				
				</tbody>
			</table>
			
			<input type="hidden" name = "clickedProject" id="clickedProject" value=" " />
			<input type="hidden" name = "webpage" id="webpage" value="retroCommentsByProject" />
			</form>
		</div>
	</div>
</div>



<script type="text/javascript" src="retroTableFunctionality.js"></script>

</body>
</html>