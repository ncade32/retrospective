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
	public static boolean codeValid = true;
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = DbManager.connect();

		if (conn == null){
			System.out.println("Connection failed to create new user");
		}else{
			System.out.println("Connection successful to create new user");
		}
		
		String user, pass, first, last, email, query, scrumCode;
		
		first = request.getParameter("first");
		last = request.getParameter("last");
		email = request.getParameter("email");
		user = request.getParameter("userReg");
		pass = request.getParameter("passReg");
		scrumCode = request.getParameter("code");
		int isScrum = 1;
		int notScrum = 0;
		Statement st;
		
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("select user from admin;");
			
			while(rs.next()) {
				if (user.equals(rs.getString(1))) {
					userValid = false;
					System.out.println("Error : Username taken");
					response.sendRedirect("register.jsp");
					return;
				}
			}
			if (scrumCode != null) {
				codeValid = false;
				rs = st.executeQuery("select codes from scrumCodes;");
				while(rs.next()) {
					if(scrumCode.equals(rs.getString(1))) {
						codeValid = true;
					}
				}
				if (!codeValid) {
					System.out.println("Error : Incorrect Scrum Code");
					return;
				}
			}
			if(scrumCode == null) {
				query = "insert into admin values('"+user+"','"+pass+"','"+first+"','"+last+"','"+email+"','"+notScrum+"');";
			}else {
				query = "insert into admin values('"+user+"','"+pass+"','"+first+"','"+last+"','"+email+"','"+isScrum+"');";
			}
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
