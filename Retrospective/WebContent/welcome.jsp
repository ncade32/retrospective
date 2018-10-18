<%@page import="com.retrospective.DbManager"%>
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
	System.out.println("Welcome Page");
	Connection conn = DbManager.connect();

	if (conn == null){
		System.out.println("Connection failed");
	}else{
		System.out.println("Connection successful");
	}
	
	ArrayList<String> projNames = new ArrayList<String>();
	projNames = GetData.getProjectNames(conn);
	GetData.closeConnection(conn);
%>
  
<form name = "welcomeForm" Action="SubmitData" method = "post" onsubmit="return validateForm()" method="post">
<div class="container">
<table>
	<tbody>
		<tr>
			<td>Enter Team Number: </td>
			<td><input type="text" id = "teamNum" name="teamNum" size = "5%" ></td>
		</tr>
		<tr>
			<td>Choose Project :</td>	
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
			<td>Enter Sprint Number: </td>
			<td><input type="text" id = "sprintNum" name="sprintNum" size = "5%" ></td>
		</tr>
	</tbody>
</table>


      <p>
        <label for="new-task">What went wrong?</label><input id="new-task" type="text"><button id = "addWrong" type = "button">Add</button>
      </p>
 

      <ul id="incomplete-tasks">
       
      </ul>
      
      <p>
        <label for="wellNew-task">What went Well? (Optional)</label><input size = "26.5%" id="wellNew-task" type="text"><button id = "addWell"type = "button">Add</button>
      </p>
      
   
      <ul id="complete-tasks">
      </ul>

      <p>
        <label for="improveNew-task">What needs improvement? (Optional)</label><input size = "26.5%" id="improveNew-task" type="text"><button id = "addImprove" type = "button">Add</button>
      </p>
      
     
      <ul id="improve-tasks">
      </ul>
    
    
 
<input type = "submit" value = "Submit" id = "submit" name = "submit">

</div>
</form>

<script type="text/javascript" src="restrictions.js"></script>
<script type="text/javascript" src="ListFunctionality/wrongList.js"></script> 
<script type="text/javascript" src="ListFunctionality/wellList.js"></script>
<script type="text/javascript" src="ListFunctionality/improveList.js"></script>

</body>

</html>