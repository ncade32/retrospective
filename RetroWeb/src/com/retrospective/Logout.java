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

import com.entity.OnlineUsers;
import com.interfaces.OnlineUsersDAOLocal;

/**
 * Servlet implementation class Logout
 */
//This servlet is called every time a user logs out
@WebServlet("/Logout")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	OnlineUsersDAOLocal onlineUsers;
       
    
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setContentType("text/html");
		
		HttpSession session = request.getSession();
		System.out.println(session.getId());
		System.out.println(session.getAttribute("user"));
		String uname = session.getAttribute("user").toString();
		
		/*Check to see if the user finished his/her entry. 
		 * If not their username will show in the onlineUser table */
		List<OnlineUsers> allOnlineUsers = onlineUsers.findAll();
		
		/*If user did not complete entry it will be deleted from the 
		 *onlineUser table and the user's username will be deleted from the session*/
		for(int i = 0; i < allOnlineUsers.size(); i ++) {
			if(allOnlineUsers.get(i).getUser().equals(uname)) {
				onlineUsers.deleteRow(uname);
				session.invalidate();
				System.out.println("logged out");
				response.sendRedirect("loginPage.jsp");
				return;
			}
		}
		
		//If user completed entry or did not start one the username will just be removed from session
		session.removeAttribute("user");
		session.removeAttribute("dataEntered");
		session.invalidate();
		response.sendRedirect("loginPage.jsp");
		return;
	}

}
