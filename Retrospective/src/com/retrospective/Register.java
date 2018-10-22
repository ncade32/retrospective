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

/**
 * Servlet implementation class Register
 */
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static boolean userValid = true;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = DbManager.connect();

		if (conn == null){
			System.out.println("Connection failed to create new user");
		}else{
			System.out.println("Connection successful to create new user");
		}
		
		String user, pass, first, last, email, query;
		
		first = request.getParameter("first");
		last = request.getParameter("last");
		email = request.getParameter("email");
		user = request.getParameter("userReg");
		pass = request.getParameter("passReg");
		ArrayList<String> dbUsers = new ArrayList<String>();
		Statement st;
		
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("select user from admin;");
			
			while(rs.next()) {
				dbUsers.add(rs.getString(1));
			}
			
			for(int i = 0; i < dbUsers.size(); i++) {
				if (user.equals(dbUsers.get(i))) {
					userValid = false;
					System.out.println("Error : Username taken");
					response.sendRedirect("register.jsp");
					return;
					
				}
			}
			query = "insert into admin values('"+user+"','"+pass+"','"+first+"','"+last+"','"+email+"');";
			st.execute(query);
			System.out.println("New user created");
			st.close();
			response.sendRedirect("loginPage.jsp");
			return;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to create new user");
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
