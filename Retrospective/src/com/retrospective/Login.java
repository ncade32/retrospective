package com.retrospective;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.retrospective.beans.Admin;
import com.retrospective.session.AdminRemote;

/**
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	//@EJB
	//private AdminRemote admin;
       
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	//This servlet is called by the login page
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String userInput, passInput;
		//Retrieve all of the usernames and passwords for each account stored in the database
		ArrayList<String> users = new ArrayList<String>();
		ArrayList<String> passwords = new ArrayList<String>();
		boolean correctUser = false;
		
		//Get the username and password that the user entered
		userInput = request.getParameter("user");
		passInput = request.getParameter("pass");
		
		Connection conn = DbManager.connect();
		
		if (conn == null){
			System.out.println("Connection failed to login");
		}else{
			System.out.println("Connection successful to login");
			System.out.println("hello");
		}
		
		try {
			
		
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT user FROM admin");
			//List<Admin> allUsers = admin.findAll();
			//System.out.println(allUsers.get(0));
			while(rs.next()) {
				users.add(rs.getString(1));
			}
			//Check to see if the username entered is found in the database
			for(int i = 0; i < users.size(); i++) {
				if(userInput.equals(users.get(i))) {
					rs = st.executeQuery("SELECT pass FROM admin where user = '"+userInput+"';");
					while(rs.next()) {
						passwords.add(rs.getString(1));
					}
					//Check to see if the password matches the username found in the database
					for(i = 0; i < passwords.size(); i++) {
						if(passInput.equals(passwords.get(i))) {
							correctUser = true;
						}
					}
				}
			}
			//Check to see if the user is a scrum so he/she can be redirected to the correct welcome page
			if (correctUser) {
				System.out.println("Login successful");
				HttpSession session = request.getSession();
				session.setAttribute("user", userInput);
				if (GetData.isScrum(conn, userInput)) {
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
			
	}catch (Exception e) {
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


