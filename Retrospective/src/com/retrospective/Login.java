package com.retrospective;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
       
   
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String userInput, passInput, user, pass;
		
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
			rs.next();
			user = rs.getString(1);
			rs = st.executeQuery("SELECT pass FROM admin");
			rs.next();
			pass = rs.getString(1);
			
			if (userInput.equals(user) && passInput.equals(pass)) {
				System.out.println("Login successful");
				HttpSession session = request.getSession();
				session.setAttribute("user", user);
				response.sendRedirect("welcome.jsp");
				return;
			}else {
				System.out.println("Login not successful");
				response.sendRedirect("loginPage.jsp");
				return;
			}
			
	}catch (SQLException e) {
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


