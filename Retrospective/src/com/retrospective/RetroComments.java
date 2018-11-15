package com.retrospective;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RetroComments
 */
// This servlet is to retrieve the user name and comment that the scrum master chose
@WebServlet("/RetroComments")
public class RetroComments extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	public static String chosenName = "";
	public static String chosenComment = "";
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		// Check to see what webpage called the servlet
		String webpage = request.getParameter("webpage");
		System.out.println(webpage);
		
		//If servlet called by retroCommentsByName page then retrieve the name that was clicked on
		if(webpage.equals("retroCommentsByName")) {
			chosenName = request.getParameter("clickedName");
			response.sendRedirect("retroCommentsByProject.jsp");
			return;
		}else {
			//If servlet called by retroCommentsByProject page then retrieve the comment that was clicked on
			chosenComment = request.getParameter("clickedProject");
			System.out.println(chosenComment);
			response.sendRedirect("viewComments.jsp");
			return;
			
			
		}
		
	}

}
