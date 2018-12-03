package com.retrospective;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Confirmed
 */
//This servlet is called by the verification page once the user hits done
@WebServlet("/Confirmed")
public class Confirmed extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		Connection conn = DbManager.connect();
		
		if (conn == null){
			System.out.println("Connection failed to confirm");
		}else{
			System.out.println("Connection successful to confirm");
		}
		try {
			HttpSession session = request.getSession();
			String uname = session.getAttribute("user").toString();
			ArrayList<Object> projInfo = new ArrayList<Object>();
			String [] name = GetData.getNameByUser(conn, uname);
			String first, last;
			Object testTeamNum, testSprintNum, testProj;
			first = name[0];
			last = name[1];
		
			//Get the comment the user just submitted
			String projName = GetData.getProjectNameByUser(conn, uname);
			String sprintNum = GetData.getSprintNumByUser(conn, uname);
			String teamNum = GetData.getTeamNumByUser(conn, uname);
			
			projInfo = GetData.getProjEntryInfo(conn, first, last);
			
			/*This for loop is checking to see if the user has already 
			 *entered a comment for this team, projectName, and sprint*/
			for(int i = 0; i < projInfo.size(); i++) {
				testTeamNum = projInfo.get(i).toString();
				i++;
				testProj = projInfo.get(i);
				i++;
				testSprintNum = projInfo.get(i).toString();
				
				//If true show error code for entering same comment twice
				if(testTeamNum.equals(teamNum) && testSprintNum.equals(sprintNum) && testProj.equals(projName)) {
					out.println("<script type=\"text/javascript\">");
					out.println("alert('Comments Already Entered For This Team, Project, And Sprint');");
					out.println("location = 'verify.jsp';");
					out.println("</script>");
				}
			}
			
			//If comment is unique then store it into the database
			Statement st = conn.createStatement();
			String query = "insert into feedback (user, teamNum, projectName, sprintNum, wrongInfo, wellInfo, improveInfo, scrum) select user, teamNum, projectName, sprintNum, wrongInfo, wellInfo, improveInfo, scrum from onlineUsers where user = '"+uname+"'";
			st.execute(query);
			System.out.println("Data entered in feedback table for " + uname);
			GetData.deleteRow(conn, uname);
			ResultSet rs = st.executeQuery("select scrum from feedback where user = '"+uname+"';");
			rs.next();
			if(rs.getInt(1) == 0) {
				session.setAttribute("dataEntered", false);
				out.write("<script type=\"text/javascript\">");
				out.write("window.alert('Retrospective Comments Saved');");
				out.write("location = 'welcome.jsp';");
				out.write("</script>");
			}else {
				session.setAttribute("dataEntered", false);
				out.write("<script type=\"text/javascript\">");
				out.write("window.alert('Retrospective Comments Saved');");
				out.write("location = 'welcomeScrum.jsp';");
				out.write("</script>");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

}
