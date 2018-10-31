<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.retrospective.*" %>
<%@ page import= "java.sql.*" %>
<%@ page import= "java.util.*" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Retrospective Comments</title>
	<link rel="stylesheet" href="style.css" type="text/css" media="screen">
</head>
<body>

<%

	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	
	if(session.getAttribute("user") == null){
		response.sendRedirect("loginPage.jsp");
		return;
	}
	
	System.out.println("View Retrospective Comments Page");
	Connection conn = DbManager.connect();
	
	if (conn == null){
		System.out.println("Connection failed to view comments");
	}else{
		System.out.println("Connection successful to view comments");
	}
	
	int team, sprint;
	String project, fullName;
	
	String chosenProj = RetroComments.chosenProj;
	String [] info = chosenProj.split("-;");
	team = Integer.parseInt(info[0]);
	project = info[1];
	sprint = Integer.parseInt(info[2]);
	
	//ArrayList<String> projInfo = new ArrayList<String>();
	//projInfo = GetData.viewComments(conn, RetroComments.chosenName, team, project, sprint);
	int tableCols = 3;
	
	String[] wrong, well, improve, projInfoSplit;
	fullName = RetroComments.chosenName;
	System.out.println(fullName);
	
	wrong = GetData.viewWrongComments(conn, fullName, team, project, sprint);
	well = GetData.viewWellComments(conn, RetroComments.chosenName, team, project, sprint);
	improve = GetData.viewImproveComments(conn, RetroComments.chosenName, team, project, sprint);
	
	
	
	
%>
<form name = "projForm" id = "projForm" action="RetroComments" method = "post">
<p>
        <label for="new-task">What went wrong?</label>
      </p>
 

      <ul>
      		<%for (int i = 0; i < wrong.length; i++){ %>
						<li><% out.print(wrong[i]); %></li>
			<%} %>
       
      </ul>
      
      <p>
        <label for="wellNew-task">What went Well?</label>
      </p>
      
   
      <ul>
      		<%for (int i = 0; i < well.length; i++){ %>
						<li><% out.print(well[i]); %></li>
			<%} %>
      </ul>

      <p>
        <label for="improveNew-task">What needs improvement?</label>
      </p>
      
     
      <ul>
      		<%for (int i = 0; i < improve.length; i++){ %>
						<li><% out.print(improve[i]); %></li>
			<%} %>
      </ul>


<input type="hidden" name = "webpage" id="webpage" value="viewComments" />
</form>

<form action="Logout" method = "post">
	<input style = "position:absolute; right:80px; top:20px;" value="Logout" name="login" type = "submit">
</form>

<script type="text/javascript" src="retroTableFunctionality.js"></script>

</body>
</html>