package com.retrospective;

import java.io.IOException;
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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static boolean access = true;
       
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		String userInput, passInput;
		ArrayList<String> users = new ArrayList<String>();
		ArrayList<String> passwords = new ArrayList<String>();
		int scrum;
		boolean correctUser = false;
		
		userInput = request.getParameter("user");
		passInput = request.getParameter("pass");
		
		Connection conn = DbManager.connect();
		
		if (conn == null){
			System.out.println("Connection failed to login");
		}else{
			System.out.println("Connection successful to login");
		}
		
		try {
			
		
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery("SELECT user FROM admin");
			while(rs.next()) {
				users.add(rs.getString(1));
			}
			for(int i = 0; i < users.size(); i++) {
				if(userInput.equals(users.get(i))) {
					rs = st.executeQuery("SELECT pass FROM admin where user = '"+userInput+"';");
					while(rs.next()) {
						passwords.add(rs.getString(1));
					}
					for(i = 0; i < passwords.size(); i++) {
						if(passInput.equals(passwords.get(i))) {
							correctUser = true;
						}
					}
				}
			}
			if (correctUser) {
				System.out.println("Login successful");
				HttpSession session = request.getSession();
				session.setAttribute("user", userInput);
				rs = st.executeQuery("select scrum from admin where user = '"+userInput+"';");
				rs.next();
				scrum = rs.getInt(1);
				if (scrum == 0) {
					response.sendRedirect("welcome.jsp");
					return;
				}else {
					response.sendRedirect("welcomeScrum.jsp");
					return;
				}
			}else {
				System.out.println("Login not successful");
				access = false;
				response.sendRedirect("loginPage.jsp");
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


