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
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");

	if(session.getAttribute("user") == null){
		response.sendRedirect("loginPage.jsp");
	}
	System.out.println("Verify Page");
	Connection conn = DbManager.connect();
	
	if (conn == null){
		System.out.println("Connection failed to verify info");
	}else{
		System.out.println("Connection successful to verify info");
	}
	
	String uname, teamNum, projName, sprintNum, wrong, well, improve;
	String [] wrongComments, wellComments, improveComments;
	
	uname = GetData.getUname(request);
	projName = GetData.getProjectNameByUser(conn, uname);
	sprintNum = GetData.getSprintNumByUser(conn, uname);
	teamNum = GetData.getTeamNumByUser(conn, uname);
	wrong = GetData.getWrongCommentsByUser(conn, uname);
	well = GetData.getWellCommentsByUser(conn, uname);
	improve = GetData.getImproveCommentsByUser(conn, uname);
	
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

<form action="Confirmed" method="post">
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

	<input onClick = "return confirmed()" value="Done" name="submit" type="submit">
	<input formaction="editInfo.jsp" value="Edit" name="edit" type="submit">
</div>

</form>

<form action="Logout" method = "post">
	<input style = "position:absolute; right:80px; top:20px;" value="Logout" name="login" type = "submit">
</form>

</body>
</html>