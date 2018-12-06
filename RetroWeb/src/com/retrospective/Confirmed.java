package com.retrospective;

import java.io.IOException;
import java.io.PrintWriter;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.interfaces.FeedbackDAOLocal;
import com.interfaces.OnlineUsersDAOLocal;

/**
 * Servlet implementation class Confirmed
 */
//This servlet is called by the verification page once the user hits done
@WebServlet("/Confirmed")
public class Confirmed extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private FeedbackDAOLocal feedback;
	@EJB
	private OnlineUsersDAOLocal onlineUsers;
       

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		PrintWriter out = response.getWriter();
		
			HttpSession session = request.getSession();
			String uname = session.getAttribute("user").toString();
			String projName, wrongInfo, wellInfo, improveInfo;
			int teamNum, sprintNum, scrum;
		
			//Get all the fields so that it can be displayed on editInfo.jsp
			teamNum = (Integer) session.getAttribute("teamNum");
			projName = session.getAttribute("projectName").toString();
			sprintNum = (Integer) session.getAttribute("sprintNum");
			wrongInfo = GetData.concateComments((String[]) session.getAttribute("wrongInfoComments"));
			wellInfo = GetData.concateComments((String[]) session.getAttribute("wellInfoComments"));
			improveInfo = GetData.concateComments((String[]) session.getAttribute("improveInfoComments"));
			scrum = (Integer) session.getAttribute("scrum");
			
			
			/*This is checking to see if the user has already 
			 *entered a comment for this team, projectName, and sprint*/
			if(feedback.isDuplicateEntry(uname, teamNum, projName, sprintNum)) {
				//If true show error code for entering same comment twice
				System.out.println("duplicate");
				out.println("<script type=\"text/javascript\">");
				out.println("alert('Comments Already Entered For This Team, Project, And Sprint');");
				out.println("location = 'verify.jsp';");
				out.println("</script>");
				return;
			}
			
			//If comment is unique then store it into the database
			feedback.createEntry(uname, teamNum, projName, sprintNum, wrongInfo, wellInfo, improveInfo, scrum);
			onlineUsers.deleteRow(uname);
			
			session.setAttribute("dataEntered", false);
			out.write("<script type=\"text/javascript\">");
			out.write("window.alert('Retrospective Comments Saved');");
			out.write("location = 'welcome.jsp';");
			out.write("</script>");
	}

}
