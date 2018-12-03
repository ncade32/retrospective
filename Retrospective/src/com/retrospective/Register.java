package com.retrospective;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.retrospective.session.AdminRemote;

/**
 * Servlet implementation class Register
 */
//This servlet is called by the registration page
@WebServlet("/Register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	@EJB
	private AdminRemote admin;
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Connection conn = DbManager.connect();

		if (conn == null){
			System.out.println("Connection failed to create new user");
		}else{
			System.out.println("Connection successful to create new user");
		}
		
		String user, pass, confPass, first, last, email, query, scrumCode;
		boolean codeValid = true;
		
		//Get all the data entered in each field
		first = request.getParameter("first") ;
		last = request.getParameter("last");
		email = request.getParameter("email");
		user = request.getParameter("userReg");
		pass = request.getParameter("passReg");
		confPass = request.getParameter("confirmPass");
		scrumCode = request.getParameter("code");
		
		int isScrum = 1;
		int notScrum = 0;
		Statement st;
		boolean error = false;
		
		//Make sure that the registration page form is filled out correctly
		if (first.equals("")) {
			System.out.println("First Name must be entered");
			request.setAttribute("nofirstError", "First Name Must Be Entered");
			error = true;
		}
		if (last.equals("")) {
			System.out.println("Last name must be entered");
			request.setAttribute("nolastError", "Last Name Must Be Entered");
			error = true;
		}
		if (email.equals("")) {
			System.out.println("Email must be entered");
			request.setAttribute("noEmailError", "Email Must Be Entered");
			error = true;
		}
		if (user.equals("")) {
			System.out.println("Username must be entered");
			request.setAttribute("noUserError", "Username Must Be Entered");
			error = true;
		}
		if (pass.equals("")) {
			System.out.println("Password must be entered");
			request.setAttribute("noPassError", "Password Must Be Entered");
			error = true;
		}
		if (!confPass.equals(pass)) {
			System.out.println("Passwords don't match");
			request.setAttribute("passConfirmError", "Passwords Do Not Match");
			error = true;
		}
		if(error) {
			request.setAttribute("savedFirst", request.getParameter("first").toString());
			request.setAttribute("savedLast", request.getParameter("last").toString());
			request.setAttribute("savedEmail", request.getParameter("email").toString());
			request.setAttribute("savedUser", request.getParameter("userReg").toString());
			request.getRequestDispatcher("register.jsp").forward(request, response);
			return;
		}
		
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("select user from admin;");
			// Check to see if the username is not already taken
			while(rs.next()) {
				if (user.equals(rs.getString(1))) {
					System.out.println("Error : Username taken");
					request.setAttribute("userTakenError", "Username Already Taken");
					request.setAttribute("savedFirst", request.getParameter("first").toString());
					request.setAttribute("savedLast", request.getParameter("last").toString());
					request.setAttribute("savedEmail", request.getParameter("email").toString());
					request.getRequestDispatcher("register.jsp").forward(request, response);
					return;
				}
			}
			// Check to see if the scrum code is valid
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
					request.setAttribute("scrumCodeError", "Incorrect Scrum Code");
					request.setAttribute("savedFirst", request.getParameter("first").toString());
					request.setAttribute("savedLast", request.getParameter("last").toString());
					request.setAttribute("savedEmail", request.getParameter("email").toString());
					request.setAttribute("savedUser", request.getParameter("userReg").toString());
					request.getRequestDispatcher("register.jsp").forward(request, response);
					return;
				}
			}
			// Check to see if user is registering as a scrum and store data in database accordingly
			if(scrumCode == null) {
				admin.createAccount(first, last, user, pass, email, notScrum);
				//query = "insert into admin values('"+user+"','"+pass+"','"+first+"','"+last+"','"+email+"','"+notScrum+"');";
			}else {
				admin.createAccount(first, last, user, pass, email, isScrum);
				//query = "insert into admin values('"+user+"','"+pass+"','"+first+"','"+last+"','"+email+"','"+isScrum+"');";
			}
			//st.execute(query);
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
