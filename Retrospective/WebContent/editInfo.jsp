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
	System.out.println("Edit Page");
	Connection conn = DbManager.connect();

	if (conn == null){
		System.out.println("Connection failed to edit info");
	}else{
		System.out.println("Connection successful to edit info");
	}
	
	ArrayList<String> projNames = new ArrayList<String>();
	projNames = GetData.getProjectNames(conn);
	
	int id;
	String teamNum, projName, sprintNum, wrong, well, improve;
	String [] wrongComments, wellComments, improveComments;
	
	id = GetData.getId(conn);
	teamNum = GetData.getTeamNum(conn, id);
	projName = GetData.getProjectName(conn, id);
	sprintNum = GetData.getSprintNum(conn, id);
	wrong = GetData.getWrongComments(conn, id);
	well = GetData.getWellComments(conn, id);
	improve = GetData.getImproveComments(conn, id);
	GetData.deleteRow(conn, id);
	
	GetData.closeConnection(conn);
	
	wrongComments = GetData.splitComments(wrong);
	wellComments = GetData.splitComments(well);
	improveComments = GetData.splitComments(improve);
	System.out.println(wellComments[0]);
%>
  
<form name = "welcomeForm" Action="SubmitData" method = "post" onsubmit="return validateForm()" method="post">
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
      			<li><label><%= wrongComments[i] %></label><input value = <%= wrongComments[i] %> name = "wrong" type="text"><button type = "button" class="edit">Edit</button><button type = "button" class="delete">Delete</button></li>
       		<%} %>
      </ul>
      
      <p>
        <label for="wellNew-task">What went Well? (Optional)</label><input size = "26.5%" id="wellNew-task2" type="text"><button id = "addWell"type = "button">Add</button>
      </p>
      
   
      <ul id="complete-tasks2">
      		<%for(int i = 0; i < wellComments.length; i++){ %>
      				<li><label><%= wellComments[i] %></label><input value = <%= wellComments[i] %> name = "well" type="text"><button type = "button" class="edit">Edit</button><button type = "button" class="delete">Delete</button></li>
       		<%} %>
       		
      </ul>

      <p>
        <label for="improveNew-task">What needs improvement? (Optional)</label><input size = "26.5%" id="improveNew-task2" type="text"><button id = "addImprove" type = "button">Add</button>
      </p>
      
     
      <ul id="improve-tasks2">
      		<%for(int i = 0; i < improveComments.length; i++){ %>
      				<li><label><%= improveComments[i] %></label><input value = <%= improveComments[i] %> name = "improve" type="text"><button type = "button" class="edit">Edit</button><button type = "button" class="delete">Delete</button></li>
       		<%} %>
      </ul>
    
    
 
<input type = "submit" value = "Submit" id = "submit" name = "submit">

</div>
</form>

<script type="text/javascript" src="restrictions.js"></script>
<script type="text/javascript" src="ListFunctionality/wrongListEdit.js"></script> 
<script type="text/javascript" src="ListFunctionality/wellListEdit.js"></script>
<script type="text/javascript" src="ListFunctionality/improveListEdit.js"></script>

</body>

</html>