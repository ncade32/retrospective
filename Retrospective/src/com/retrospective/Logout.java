package com.retrospective;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Logout
 */
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = DbManager.connect();
		try {
		Statement st = conn.createStatement();
		String uname = GetData.getUname(request);
		ResultSet rs = st.executeQuery("select * from onlineUsers where user = '"+uname+"'");
		if(rs.next()) {
			GetData.deleteRow(conn, uname);
			HttpSession session = request.getSession();
			session.removeAttribute("user");
			session.invalidate();
			System.out.println("logged out");
			response.sendRedirect("loginPage.jsp");
		
		}else {
			HttpSession session = request.getSession();
			session.removeAttribute("user");
			session.invalidate();
			System.out.println("logged out");
			response.sendRedirect("loginPage.jsp");
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
