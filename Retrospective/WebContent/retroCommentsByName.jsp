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
	
	System.out.println("View Retrospective Comments Page By Name");
	Connection conn = DbManager.connect();
	
	if (conn == null){
		System.out.println("Connection failed to view comments by name");
	}else{
		System.out.println("Connection successful to view comments by name");
	}
	
	
	ArrayList<String> allNames = new ArrayList<String>();
	int tableCols = 2;
	
	
	allNames = GetData.getAllFeedbackNames(conn);
	
	for(int a = 0; a < allNames.size(); a++){
		System.out.println(allNames.get(a));
	}
	System.out.println(allNames.size())	;
%>
<form name = "nameForm" id = "nameForm" action="RetroComments" method = "post">
	<table style="margin-top:100px; margin-left:10px;" id = "retroComments" class="display" style="width: 100%;">
		<thead>
			<tr>
				<th>First Name</th>
				<th>Last Name</th>
			</tr>
		</thead>
		<tbody>
				<% int j = 0;%>
				<%for(int i = 0; i < (allNames.size()/tableCols); i++){ %>
					<tr onclick = "clickedName(this)" id = <%= allNames.get(j)+"-;"+allNames.get(j+1) %>>
						<%for(int k = 0; k < tableCols; k++){%>
							<td align="center"><%= allNames.get(j) %></td>
							<% System.out.println(j);	%>
							<%j++; %>			
							<td align="center"><%= allNames.get(j) %></td>
							<% System.out.println(j); %>
							<%j++; %>			
							<%k = k + tableCols; %>
						<%} %>
					</tr>
				<%} %>
				
		
		</tbody>
	</table>

<input type="hidden" name = "clickedName" id="clickedName" value=" " />
<input type="hidden" name = "webpage" id="webpage" value="retroCommentsByName" />
</form>
		
<form>
  <input type="button" value="Back" onclick="history.back()">
</form>

<script type="text/javascript" src="retroTableFunctionality.js"></script>

<form style = "position:relative; float:right;" action="Logout" method = "post">
	<input value="Logout" name="login" type = "submit">
</form>

</body>
</html>