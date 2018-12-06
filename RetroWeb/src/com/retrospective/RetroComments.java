package com.retrospective;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.entity.Admin;
import com.entity.Feedback;
import com.interfaces.FeedbackDAOLocal;

/**
 * Servlet implementation class RetroComments
 */
// This servlet is to retrieve the user name and comment that the scrum master chose
@WebServlet("/RetroComments")
public class RetroComments extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private FeedbackDAOLocal feedback;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		// Check to see what webpage called the servlet
		String webpage = request.getParameter("webpage");
		
		HttpSession session = request.getSession();
		String[] chosenName, chosenComment;
		String userFirst, userLast, projectName;
		int teamNum, sprintNum;
		List<Feedback> entries, commentViewed;
		
		//Get all the user accounts that have feedback
		List<Admin> allFeedbackAdmins = feedback.getAllFeedbackAdmins();
		
		System.out.println("Servlet called by " + webpage);
		
		//Complete If statements depending on which webpage called the servlet
		if(webpage.equals("retroCommentsByName")) {
			//Get full name that was chosen and split string into first and last name
			chosenName = request.getParameter("clickedName").split("-;");
			userFirst = chosenName[0];
			userLast = chosenName[1];
			
			//Get all the feedback given by this person
			entries = feedback.getCommentsByProjInfo(userFirst, userLast);
			
			//Set attributes to be used by retroCommentsByProject.jsp
			session.setAttribute("chosenFirst", userFirst);
			session.setAttribute("chosenLast", userLast);
			session.setAttribute("nameTitle", userFirst +" "+userLast+"'s"+" Comments");
			session.setAttribute("entries", entries);
			response.sendRedirect("retroCommentsByProject.jsp");
			return;
		}else if(webpage.equals("retroCommentsByProject")) {
			/*Get the feedback entry that was chosen and split
			 * into teamNum, projectName, and sprintNum*/
			chosenComment = request.getParameter("clickedProject").split("-;");
			teamNum = Integer.parseInt(chosenComment[0]);
			projectName = chosenComment[1];
			sprintNum = Integer.parseInt(chosenComment[2]);
			
			System.out.println(teamNum + projectName + sprintNum);
			System.out.println(session.getAttribute("chosenFirst").toString() + session.getAttribute("chosenLast").toString());
			
			//Get the wrong, well, and improve comments for the chosen feedback entry
			commentViewed = feedback.viewComments(session.getAttribute("chosenFirst").toString(), session.getAttribute("chosenLast").toString(), teamNum, projectName, sprintNum);
	
			session.setAttribute("commentViewed", commentViewed);
			response.sendRedirect("viewComments.jsp");
			return;
		}else if(webpage.equals("welcome")) {
			session.setAttribute("allFeedbackAdmins", allFeedbackAdmins);
			response.sendRedirect("retroCommentsByName.jsp");
			return;
		}
		
	}

}
