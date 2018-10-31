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
	<link rel="stylesheet" type="text/css" href="https://cdn.datatables.net/1.10.19/css/jquery.dataTables.css">
	<script  src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script type="text/javascript" charset="utf8" src="https://cdn.datatables.net/1.10.19/js/jquery.dataTables.js"></script>
</head>
<body>
<script type="text/javascript">
	$(document).ready(function(){
		$('#retroComments').DataTable();
		console.log('hello');

	});
</script>

<%

	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
	response.setHeader("Pragma", "no-cache");
	response.setHeader("Expires", "0");
	
	if(session.getAttribute("user") == null){
		response.sendRedirect("loginPage.jsp");
		return;
	}
	
	System.out.println("View Retrospective Comments Page By Project");
	Connection conn = DbManager.connect();
	
	if (conn == null){
		System.out.println("Connection failed to view comments by project");
	}else{
		System.out.println("Connection successful to view comments by project");
	}
	
	String name = RetroComments.chosenName;
	String [] firstAndLast = name.split("-;");
	String first, last;
	first = firstAndLast[0];
	last = firstAndLast[1];
	
	ArrayList<Object> projInfo = new ArrayList<Object>();
	int tableCols = 3;
	
	
	projInfo = GetData.getProjEntryInfo(conn, first, last);
	
%>
<form name = "projForm" id = "projForm" action="RetroComments" method = "post">
<table id = "retroComments" class="display" style="width:100%">
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

<form>
  <input type="button" value="Back" onclick="history.back()">
</form>

<form style = "position:relative; float:right;" action="Logout" method = "post">
	<input value="Logout" name="login" type = "submit">
</form>

<script type="text/javascript" src="retroTableFunctionality.js"></script>

</body>
</html>