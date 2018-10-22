<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import= "com.retrospective.*" %>
<%@ page import= "java.sql.*" %>
<%@ page import= "java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Retrospective</title>
<link rel="stylesheet" href="style.css" type="text/css" media="screen">
</head>
<body>

<%
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");

	if(session.getAttribute("user") == null){
		response.sendRedirect("loginPage.jsp");
	}
	System.out.println("Edit Page");
	Connection conn = DbManager.connect();

	if (conn == null){
		System.out.println("Connection failed to edit info");
	}else{
		System.out.println("Connection successful to edit info");
	}
	
	ArrayList<String> projNames = new ArrayList<String>();
	projNames = GetData.getProjectNames(conn);
	
	String uname, teamNum, projName, sprintNum, wrong, well, improve;
	String [] wrongComments, wellComments, improveComments;
	
	uname = GetData.getUname(request);
	teamNum = GetData.getTeamNum(conn, uname);
	projName = GetData.getProjectName(conn, uname);
	sprintNum = GetData.getSprintNum(conn, uname);
	wrong = GetData.getWrongComments(conn, uname);
	well = GetData.getWellComments(conn, uname);
	improve = GetData.getImproveComments(conn, uname);
	//GetData.deleteRow(conn, uname);
	
	GetData.closeConnection(conn);
	
	wrongComments = GetData.splitComments(wrong);
	wellComments = GetData.splitComments(well);
	improveComments = GetData.splitComments(improve);
%>
  
<form name = "editForm" Action="SubmitData" method = "post" onsubmit="return validateEditForm()" method="post">
<div class="container">
<table>
	<tbody>
		<tr>
			<td>Enter Team Number: </td>
			<td><input type="text" id = "teamNum" name="teamNum" value = <%= teamNum %> size = "5%" ></td>
		</tr>
		<tr>
			<td>Choose Project :</td>	
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
			<td>Enter Sprint Number: </td>
			<td><input type="text" id = "sprintNum" name="sprintNum" value = <%= sprintNum %> size = "5%" ></td>
		</tr>
	</tbody>
</table>


      <p>
        <label for="new-task">What went wrong?</label><input id="new-task2" type="text"><button id = "addWrong" type = "button">Add</button>
      </p>
 

      <ul id="incomplete-tasks2">
      		<%for(int i = 0; i < wrongComments.length; i++){ %>
      			<li><textarea name = "wrong"><%= wrongComments[i]%></textarea><button type = "button" class="edit">Edit</button><button type = "button" class="delete">Delete</button></li>
       		<%} %>
      </ul>
      
      <p>
        <label for="wellNew-task">What went Well? (Optional)</label><input size = "26.5%" id="wellNew-task2" type="text"><button id = "addWell"type = "button">Add</button>
      </p>
      
   
      <ul id="complete-tasks2">
      		<%for(int i = 0; i < wellComments.length; i++){ %>
      				<li><textarea name = "well"><%= wellComments[i]%></textarea><button type = "button" class="edit">Edit</button><button type = "button" class="delete">Delete</button></li>
       		<%} %>
       		
      </ul>

      <p>
        <label for="improveNew-task">What needs improvement? (Optional)</label><input size = "26.5%" id="improveNew-task2" type="text"><button id = "addImprove" type = "button">Add</button>
      </p>
      
     
      <ul id="improve-tasks2">
      		<%for(int i = 0; i < improveComments.length; i++){ %>
      				<li><textarea name = "improve"><%= improveComments[i]%></textarea><button type = "button" class="edit">Edit</button><button type = "button" class="delete">Delete</button></li>
       		<%} %>
      </ul>
    
    
 
<input type = "submit" value = "Submit" id = "submit" name = "submit">

</div>
</form>

<form action="Logout" method = "post">
	<input style = "position:absolute; right:80px; top:20px;" value="Logout" name="login" type = "submit">
</form>

<script type="text/javascript" src="restrictions.js"></script>
<script type="text/javascript" src="ListFunctionality/wrongListEdit.js"></script> 
<script type="text/javascript" src="ListFunctionality/wellListEdit.js"></script>
<script type="text/javascript" src="ListFunctionality/improveListEdit.js"></script>

</body>

</html>