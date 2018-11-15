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
//This servlet is called every time a user logs out
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		Connection conn = DbManager.connect();
		try {
		Statement st = conn.createStatement();
		HttpSession session = request.getSession();
		System.out.println(session.getId());
		System.out.println(session.getAttribute("user"));
		String uname = session.getAttribute("user").toString();
		//Check to see if the user finished his/her entry. If not it will show in the onlineUser table
		ResultSet rs = st.executeQuery("select * from onlineUsers where user = '"+uname+"'");
		
		/*If user did not complete entry it will be deleted from the 
		 *onlineUser table and the user's username will be deleted from the session*/
		if(rs.next()) {
			GetData.deleteRow(conn, uname);
			session.removeAttribute("user");
			System.out.println(session.getAttribute("user"));
			session.removeAttribute("dataEntered");
			session.invalidate();
			System.out.println("logged out");
			response.sendRedirect("loginPage.jsp");
			return;
		
		}else {
			//If user completed entry or did not start one the username will just be removed from session
			session.removeAttribute("user");
			session.removeAttribute("dataEntered");
			session.invalidate();
			System.out.println("logged out: " + session.getAttribute("user"));
			response.sendRedirect("loginPage.jsp");
			return;
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		//response.sendRedirect("loginPage.jsp");
		//return;
	}

}
