package com.retrospective;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SubmitData
 */
@WebServlet("/SubmitData")
public class SubmitData extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		String [] wrong, well, improve; 
		String uname, teamNum, sprintNum, projName, query, wrongCommentsSplit, wellCommentsSplit, improveCommentsSplit;
		int scrum;
		
		teamNum = request.getParameter("teamNum").toString();
		sprintNum = request.getParameter("sprintNum").toString();
		projName = request.getParameter("chooseProj").toString();
		wrong = request.getParameterValues("wrong");
		well = request.getParameterValues("well");
		improve = request.getParameterValues("improve");
		
		wrongCommentsSplit = GetData.storeComments(wrong);
		wellCommentsSplit = GetData.storeComments(well);
		improveCommentsSplit = GetData.storeComments(improve);		
		
		Connection conn = DbManager.connect();
		
		if (conn == null){
			System.out.println("Connection failed to enter data");
		}else{
			System.out.println("Connection successful to enter data");
		}
		
		try {
			uname = GetData.getUname(request);
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("select scrum from admin where user = '"+uname+"';");
			rs.next();
			scrum = rs.getInt(1);
			rs = st.executeQuery("select * from onlineUsers where user = '"+uname+"'");
			if(!rs.next()) {
				query = "insert into onlineUsers values('"+uname+"','"+teamNum+"','"+projName+"','"+sprintNum+"','"+wrongCommentsSplit+"','"+wellCommentsSplit+"','"+improveCommentsSplit+"','"+scrum+"')";
				st.execute(query);
				System.out.println("Data entered for username: "+ uname);
				st.close();
				response.sendRedirect("verify.jsp");
				return;
			}else {
				GetData.deleteRow(conn, uname);
				query = "insert into onlineUsers values('"+uname+"','"+teamNum+"','"+projName+"','"+sprintNum+"','"+wrongCommentsSplit+"','"+wellCommentsSplit+"','"+improveCommentsSplit+"','"+scrum+"')";
				st.execute(query);
				System.out.println("Data entered for " + uname);
				st.close();
				response.sendRedirect("verify.jsp");
				return;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				GetData.closeConnection(conn);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	
	}

}
