<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
	      	<c:set var="wrongComments" value="${fn:split(commentViewed.get(0).getWrongInfo(), '@;')}" />
	      	<c:forEach items="${wrongComments}" var="comment" >
				<li class="li-tasks-group">${comment}</li>
			</c:forEach>
	       
	      </ul>
	      
	      <p>
	        <label for="wellNew-task">What went Well?</label>
	      </p>
	      
	   
	      <ul>
	      <c:set var="wellComments" value="${fn:split(commentViewed.get(0).getWellInfo(), '@;')}" />
	      	<c:forEach items="${wellComments}" var="well" >
				<li class="li-tasks-group">${well}</li>
			</c:forEach>
	      </ul>
	
	      <p>
	        <label for="improveNew-task">What needs improvement?</label>
	      </p>
	      
	     
	      <ul>
	      <c:set var="improveComments" value="${fn:split(commentViewed.get(0).getImproveInfo(), '@;')}" />
	      	<c:forEach items="${improveComments}" var="improve" >
				<li class="li-tasks-group">${improve}</li>
			</c:forEach>
	      </ul>
	
	
	<input type="hidden" name = "webpage" id="webpage" value="viewComments" />
	</form>
	<button onclick="window.history.back()" type= "submit" class="btn" id = "back" name = "back">Back</button>
</div>



<script type="text/javascript" src="retroTableFunctionality.js"></script>

</body>
</html>