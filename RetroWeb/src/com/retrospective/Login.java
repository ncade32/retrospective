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
import com.entity.Projects;
import com.interfaces.AdminDAOLocal;
import com.interfaces.FeedbackDAOLocal;
import com.interfaces.ProjectsDAOLocal;


/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private AdminDAOLocal admin;
	@EJB
	private ProjectsDAOLocal projects;
	@EJB
	private FeedbackDAOLocal feedback;
	
       
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//This servlet is called by the login page
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String userInput, passInput;
		Admin correctUser = null;
		
		//Retrieve all of the user accounts, user accounts that have feedback, and projects for each account stored in the database
		List<Admin> allUsers = admin.findAll();
		List<Projects> allProjects = projects.findAll();
		List<Admin> allFeedbackAdmins = feedback.getAllFeedbackAdmins();
		boolean loginAttempt = false;
		
		//Get the username and password that the user entered
		userInput = request.getParameter("user");
		passInput = request.getParameter("pass");
			
		//Check to see if the username and password entered is correct
		for(int i = 0; i < allUsers.size(); i++) {
			if(allUsers.get(i).getUser().equals(userInput)){
				if(allUsers.get(i).getPass().equals(passInput)) {
					correctUser = allUsers.get(i);
					loginAttempt = true;
				}
			}
		}
			
		//If credentials entered are correct grant user access to welcome.jsp
		if (loginAttempt) {
			System.out.println("Login successful");
			HttpSession session = request.getSession();
			session.setAttribute("user", userInput);
			session.setAttribute("allProjects", allProjects);
			session.setAttribute("allFeedbackAdmins", allFeedbackAdmins);
			int scrum = correctUser.getScrum();
			session.setAttribute("scrum", scrum);
			
			response.sendRedirect("welcome.jsp");
			return;
		}else {
			//If credentials entered are incorrect showe error and reload loginPage.jsp
			System.out.println("Login not successful");
			request.setAttribute("loginError", "Username Or Password Incorrect");
			request.getRequestDispatcher("loginPage.jsp").forward(request, response);
			return;
		}
	}
}


