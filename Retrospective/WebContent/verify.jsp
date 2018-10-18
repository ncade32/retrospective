<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "com.retrospective.*" %>
<%@ page import= "java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Verification</title>
<link rel="stylesheet" href="style.css" type="text/css" media="screen">
</head>
<body>
<%
	System.out.println("Verify Page");
	Connection conn = DbManager.connect();
	
	if (conn == null){
		System.out.println("Connection failed to verify info");
	}else{
		System.out.println("Connection successful to verify info");
	}
	
	String teamNum, projName, sprintNum, wrong, well, improve;
	String [] wrongComments, wellComments, improveComments;
	int id;
	
	id = GetData.getId(conn);
	teamNum = GetData.getTeamNum(conn, id);
	projName = GetData.getProjectName(conn, id);
	sprintNum = GetData.getSprintNum(conn, id);
	wrong = GetData.getWrongComments(conn, id);
	well = GetData.getWellComments(conn, id);
	improve = GetData.getImproveComments(conn, id);
	
	GetData.closeConnection(conn);
	
	wrongComments = GetData.splitComments(wrong);
	wellComments = GetData.splitComments(well);
	improveComments = GetData.splitComments(improve);
%>
<form action="confirmed.jsp">
<div class="container">
<table>
	<tbody>
		<tr>
			<td>Team Number: </td>
			<td><%= teamNum %></td>
		</tr>
		<tr>
			<td>Project :</td>	
			<td><%= projName %></td>
		</tr>
		<tr>
			<td>Sprint Number: </td>
			<td><%= sprintNum %></td>
		</tr>
	</tbody>
</table>

<p>
        <label for="new-task">What went wrong?</label>
      </p>
 

      <ul>
      		<%for (int i = 0; i < wrongComments.length; i++){ %>
						<li><% out.print(wrongComments[i]); %></li>
			<%} %>
       
      </ul>
      
      <p>
        <label for="wellNew-task">What went Well?</label>
      </p>
      
   
      <ul>
      		<%for (int i = 0; i < wellComments.length; i++){ %>
						<li><% out.print(wellComments[i]); %></li>
			<%} %>
      </ul>

      <p>
        <label for="improveNew-task">What needs improvement?</label>
      </p>
      
     
      <ul>
      		<%for (int i = 0; i < improveComments.length; i++){ %>
						<li><% out.print(improveComments[i]); %></li>
			<%} %>
      </ul>

	<input value="Done" name="submit" type="submit">
	<input formaction="editInfo.jsp" value="Edit" name="edit" type="submit">
</div>

</form>

</body>
</html>