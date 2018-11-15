package com.retrospective;

import java.io.IOException;
import java.sql.*;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SubmitData
 */
//This servlet is called by the welcome page once submit is hit
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
		
		//Collect data from all of the input fields
		teamNum = request.getParameter("teamNum").toString();
		sprintNum = request.getParameter("sprintNum").toString();
		projName = request.getParameter("chooseProj").toString();
		wrong = request.getParameterValues("wrong");
		well = request.getParameterValues("well");
		improve = request.getParameterValues("improve");
		
		wrongCommentsSplit = GetData.concateComments(wrong);
		wellCommentsSplit = GetData.concateComments(well);
		improveCommentsSplit = GetData.concateComments(improve);		
		
		Connection conn = DbManager.connect();
		
		if (conn == null){
			System.out.println("Connection failed to enter data");
		}else{
			System.out.println("Connection successful to enter data");
		}
		
		try {
			HttpSession session = request.getSession();
			uname = session.getAttribute("user").toString();
			session.setAttribute("dataEntered", true);
			System.out.println(session.getId());
			Statement st = conn.createStatement();
			//Check to see if the user is a scrum
			ResultSet rs = st.executeQuery("select scrum from admin where user = '"+uname+"';");
			rs.next();
			scrum = rs.getInt(1);
			rs = st.executeQuery("select * from onlineUsers where user = '"+uname+"'");
			/*This is to catch if the user has hit the submit button then 
			 *hit the back button from the verification page*/
			if(!rs.next()) {
				//If a entry is not found just store the new comment in onlineUser table
				query = "insert into onlineUsers values('"+uname+"','"+teamNum+"','"+projName+"','"+sprintNum+"','"+wrongCommentsSplit+"','"+wellCommentsSplit+"','"+improveCommentsSplit+"','"+scrum+"')";
				st.execute(query);
				System.out.println("Data entered for username: "+ uname);
				st.close();
				response.sendRedirect("verify.jsp");
				return;
			}else {
				/*Replace this entry with the user's previous 
				 *entry if one is already found in onlineUser table*/
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
