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
		
		System.out.println(webpage);
		
		//If servlet called by retroCommentsByName page then retrieve the name that was clicked on
		if(webpage.equals("retroCommentsByName")) {
			chosenName = request.getParameter("clickedName").split("-;");
			userFirst = chosenName[0];
			userLast = chosenName[1];
			entries = feedback.getCommentsByProjInfo(userFirst, userLast);
			session.setAttribute("chosenFirst", userFirst);
			session.setAttribute("chosenLast", userLast);
			session.setAttribute("nameTitle", userFirst +" "+userLast+"'s"+" Comments");
			session.setAttribute("entries", entries);
			//chosenName = request.getParameter("clickedName");
			response.sendRedirect("retroCommentsByProject.jsp");
			return;
		}else {
			chosenComment = request.getParameter("clickedProject").split("-;");
			teamNum = Integer.parseInt(chosenComment[0]);
			projectName = chosenComment[1];
			sprintNum = Integer.parseInt(chosenComment[2]);
			System.out.println(teamNum + projectName + sprintNum);
			System.out.println(session.getAttribute("chosenFirst").toString() + session.getAttribute("chosenLast").toString());
			commentViewed = feedback.viewComments(session.getAttribute("chosenFirst").toString(), session.getAttribute("chosenLast").toString(), teamNum, projectName, sprintNum);
			
			//If servlet called by retroCommentsByProject page then retrieve the comment that was clicked on
			session.setAttribute("commentViewed", commentViewed);
			//chosenComment = request.getParameter("clickedProject");
			//System.out.println(chosenComment);
			response.sendRedirect("viewComments.jsp");
			return;
			
			
		}
		
	}

}
