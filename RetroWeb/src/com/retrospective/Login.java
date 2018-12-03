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
import com.interfaces.ProjectsDAOLocal;

//import com.retrospective.beans.Admin;
//import com.retrospective.session.AdminRemote;

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
       
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//This servlet is called by the login page
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String userInput, passInput;
		Admin correctUser = null;
		//Retrieve all of the usernames and passwords for each account stored in the database
		List<Admin> allUsers = admin.findAll();
		List<Projects> allProjects = projects.findAll();
		boolean loginAttempt = false;
		
		//Get the username and password that the user entered
		userInput = request.getParameter("user");
		passInput = request.getParameter("pass");
			
		
			//Statement st = conn.createStatement();
			//ResultSet rs = st.executeQuery("SELECT user FROM admin");
			//List<Admin> allUsers = admin.findAll();
			//System.out.println(allUsers.get(0));
			/*for(int i = 0; i < allUsers.size(); i++) {
				users.add(rs.getString(1));
			}*/
			//Check to see if the username entered is found in the database
			for(int i = 0; i < allUsers.size(); i++) {
				if(allUsers.get(i).getUser().equals(userInput)){
					if(allUsers.get(i).getPass().equals(passInput)) {
						correctUser = allUsers.get(i);
						loginAttempt = true;
					}
				}
			}
					/*rs = st.executeQuery("SELECT pass FROM admin where user = '"+userInput+"';");
					while(rs.next()) {
						passwords.add(rs.getString(1));
					}
					//Check to see if the password matches the username found in the database
					for(i = 0; i < passwords.size(); i++) {
						if(passInput.equals(passwords.get(i))) {
							correctUser = true;
						}
					}
				}*/
			
			//Check to see if the user is a scrum so he/she can be redirected to the correct welcome page
			if (loginAttempt) {
				System.out.println("Login successful");
				HttpSession session = request.getSession();
				session.setAttribute("user", userInput);
				session.setAttribute("allProjects", allProjects);
				if (correctUser.getScrum() == 1){
					response.sendRedirect("welcomeScrum.jsp");
					return;
				}else {
					response.sendRedirect("welcome.jsp");
					return;
				}
			}else {
				//Show error if username or password is incorrect
				System.out.println("Login not successful");
				request.setAttribute("loginError", "Username Or Password Incorrect");
				request.getRequestDispatcher("loginPage.jsp").forward(request, response);
				return;
			}
	}
}


