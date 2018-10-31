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
@WebServlet("/RetroComments")
public class RetroComments extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	public static String chosenName = "";
	public static String chosenProj = "";
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		String webpage = request.getParameter("webpage");
		System.out.println(webpage);
		
		if(webpage.equals("retroCommentsByName")) {
			chosenName = request.getParameter("clickedName");
			response.sendRedirect("retroCommentsByProject.jsp");
			return;
		}else {
			
			chosenProj = request.getParameter("clickedProject");
			System.out.println(chosenProj);
			response.sendRedirect("viewComments.jsp");
			return;
			
			
		}
		
	}

}
