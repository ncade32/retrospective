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
		String teamNum, sprintNum, projName, query, wrongCommentsSplit, wellCommentsSplit, improveCommentsSplit;
		int id;
		
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
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM feedback");
			rs.next();
			id = rs.getInt(1);
			id++;
			query = "insert into feedback values('"+id+"','"+teamNum+"','"+projName+"','"+sprintNum+"','"+wrongCommentsSplit+"','"+wellCommentsSplit+"','"+improveCommentsSplit+"')";
			st.execute(query);
			System.out.println("Data entered for id: "+ id);
			st.close();
			response.sendRedirect("verify.jsp");
			return;
		} catch (SQLException e) {
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
