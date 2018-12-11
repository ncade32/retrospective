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
import com.interfaces.AdminDAOLocal;
import com.interfaces.OnlineUsersDAOLocal;

/**
 * Servlet implementation class SubmitData
 */
//This servlet is called by the welcome page once submit is hit
@WebServlet("/SubmitData")
public class SubmitData extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB
	private AdminDAOLocal admin;
	@EJB
	private OnlineUsersDAOLocal onlineUsers;
       
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html");
		
		String [] wrong, well, improve; 
		String uname, projName, wrongCommentsConcate, wellCommentsConcate, improveCommentsConcate;
		int teamNum, sprintNum;
		
		//Collect data from all of the input fields
		teamNum = Integer.parseInt(request.getParameter("teamNum"));
		sprintNum = Integer.parseInt(request.getParameter("sprintNum"));
		projName = request.getParameter("chooseProj").toString();
		wrong = request.getParameterValues("wrong");
		well = request.getParameterValues("well");
		improve = request.getParameterValues("improve");
		
		/*Make all comments entered into a string for each 
		 * category so data can be entered into the database*/
		wrongCommentsConcate = GetData.concateComments(wrong);
		wellCommentsConcate = GetData.concateComments(well);
		improveCommentsConcate = GetData.concateComments(improve);

		
		HttpSession session = request.getSession();
		uname = session.getAttribute("user").toString();
		
		int scrum = (Integer) session.getAttribute("scrum");
		
		//Set attributes for this session to be use on verify.jsp
		session.setAttribute("dataEntered", true);
		session.setAttribute("teamNum", teamNum);
		session.setAttribute("sprintNum", sprintNum);
		session.setAttribute("projectName", projName);
		session.setAttribute("wrongInfoComments", GetData.splitComments(wrongCommentsConcate));
		session.setAttribute("wellInfoComments", GetData.splitComments(wellCommentsConcate));
		session.setAttribute("improveInfoComments", GetData.splitComments(improveCommentsConcate));
		
		List<OnlineUsers> allOnlineUsers = onlineUsers.findAll();
		
		/*This is to catch if the user has hit the submit button then 
		 *hit the back button from the verification page*/
		for(int i = 0; i < allOnlineUsers.size(); i++) {
			/*Replace this entry with the user's previous 
			 *entry if one is already found in onlineUser table*/
			if(allOnlineUsers.get(i).getUser().equals(uname)) {
				onlineUsers.deleteRow(uname);
				onlineUsers.createOnlineUser(uname, teamNum, projName, sprintNum, wrongCommentsConcate, wellCommentsConcate, improveCommentsConcate, scrum);
				System.out.println("Data entered for username: "+ uname);
				response.sendRedirect("verify.jsp");
				return;
			}
		}
		//If a entry is not found just store the new comment in onlineUser table
		onlineUsers.createOnlineUser(uname, teamNum, projName, sprintNum, wrongCommentsConcate, wellCommentsConcate, improveCommentsConcate, scrum);
		System.out.println("Data entered for username: "+ uname);
		response.sendRedirect("verify.jsp");
		return;
	}
}
			