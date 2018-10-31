package com.retrospective;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Confirmed
 */
@WebServlet("/Confirmed")
public class Confirmed extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = DbManager.connect();
		
		if (conn == null){
			System.out.println("Connection failed to confirm");
		}else{
			System.out.println("Connection successful to confirm");
		}
		try {
			String uname = GetData.getUname(request);
			
			Statement st = conn.createStatement();
			String query = "insert into feedback (user, teamNum, projectName, sprintNum, wrongInfo, wellInfo, improveInfo, scrum) select user, teamNum, projectName, sprintNum, wrongInfo, wellInfo, improveInfo, scrum from onlineUsers where user = '"+uname+"'";
			st.execute(query);
			System.out.println("Data entered in feedback table for " + uname);
			GetData.deleteRow(conn, uname);
			ResultSet rs = st.executeQuery("select scrum from feedback where user = '"+uname+"';");
			rs.next();
			if(rs.getInt(1) == 0) {
				response.sendRedirect("welcome.jsp");
				return;
			}else {
				response.sendRedirect("welcomeScrum.jsp");
				return;
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
