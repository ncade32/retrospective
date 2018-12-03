package com.retrospective;

import java.sql.*;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/*This class contains all of the neccesarry methods for retrieve
 *and storing data*/

public class GetData {
	
	// Retrieve the names of all the projects in db
	public static ArrayList<String> getProjectNames(Connection conn) throws SQLException {
		ArrayList<String> names = new ArrayList<String>();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select projectName from projects");
		// Add project names to arraylist so they can be returned
		while (rs.next()) {
			names.add(rs.getString("projectName"));
		}
		st.close();
		return names;
	}
	
	// Concatenate comments with the separator @; to store into db
	public static String concateComments(String [] comments) {
		// If user does not enter comment enter none in db
		String commentString;
		if (comments == null) {
			commentString = "none@;";
			return commentString;
		}
		else {
		commentString = comments[0];
		}
		/* If user enters one comment concatenate 
		 *the separator and return string*/
		if(comments.length == 1) {
			commentString = commentString + "@;";
			return commentString;
		}
		/* If user enters more than one comment concatenate
		 * all of the comments and return string*/
		else {
			for(int i = 1; i < comments.length; i++) {
				commentString = commentString + "@;" + comments[i];
			}
			return commentString;
		}
	}
	// Retrieves team number entered by user from onlineUser table in db
	public static String getTeamNumByUser(Connection conn, String uname) throws SQLException {
		String teamNum;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select teamNum from onlineUsers where user = '"+uname+"';");
		rs.next();
		teamNum = rs.getString(1);
		st.close();
		return teamNum;
	}
	
	// Retrieves project name entered by user from onlineUser table in db
	public static String getProjectNameByUser(Connection conn, String uname) throws SQLException {
		String projName;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select projectName from onlineUsers where user = '"+uname+"';");
		rs.next();
		projName = rs.getString(1);
		st.close();
		return projName;
	}
	
	// Retrieves sprint number entered by user from onlineUser table in db
	public static String getSprintNumByUser(Connection conn, String uname) throws SQLException {
		String sprintNum;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select sprintNum from onlineUsers where user = '"+uname+"';");
		rs.next();
		sprintNum = rs.getString(1);
		st.close();
		return sprintNum;
	}
	
	// Split comments by @; and return them in an array
	public static String[] splitComments (String comments) {
		String[] splitted= comments.split("@;");
		return splitted;
	}
	
	// Retrieves wrong comments entered by user from onlineUser table in db
	public static String getWrongCommentsByUser(Connection conn, String uname) throws SQLException {
		String wrong;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select wrongInfo from onlineUsers where user = '"+uname+"';");
		rs.next();
		wrong = rs.getString(1);
		st.close();
		return wrong;
	}
	
	// Retrieves well comments entered by user from onlineUser table in db
	public static String getWellCommentsByUser(Connection conn, String uname) throws SQLException {
		String well;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select wellInfo from onlineUsers where user = '"+uname+"';");
		rs.next();
		well = rs.getString(1);
		st.close();
		return well;
	}
	
	// Retrieves improve comments entered by user from onlineUser table in db
	public static String getImproveCommentsByUser(Connection conn, String uname) throws SQLException {
		String improve;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select improveInfo from onlineUsers where user = '"+uname+"';");
		rs.next();
		improve = rs.getString(1);
		st.close();
		return improve;
	}
	
	// Deletes row of the given username from the onlineUsers table in db
	public static void deleteRow(Connection conn, String uname) throws SQLException {
		Statement st = conn.createStatement();
		st.execute("delete from onlineUsers where user = '"+uname+"';" );
		System.out.println(uname + " row deleted from olineUsers table");
		st.close();
	}
	
	// Closes connection to db
	public static void closeConnection(Connection conn) throws Exception {
		conn.close();
		System.out.println("Connection Closed");
	}
	
	// Retrieves wrong comments from feedback tables in db
	public static String [] viewWrongComments(Connection conn, String fullName, int teamNum, String projName, int sprintNum) throws SQLException {
		String name, proj, user, first, last;
		int team, sprint;
		name = fullName;
		proj = projName;
		team = teamNum;
		sprint = sprintNum;
		
		// Split first and last name by -;
		String [] firstAndLast = name.split("-;");
		first = firstAndLast[0];
		last = firstAndLast[1];
		
		// Retrieve corresponding username for first and last name
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select user from admin where first = '"+first+"' and last = '"+last+"';");
		rs.next();
		user = rs.getString(1);
		/* Retrieve corresponding wrong comment for 
		 *user name, project and sprint*/
		rs = st.executeQuery("select wrongInfo from feedback where user = '"+user+"' and teamNum = '"+team+"' "+
							"and projectName = '"+proj+"' and sprintNum = '"+sprint+"';");
		
		rs.next();
		String wrongString = rs.getString(1);
		// Split wrong comment string and put in an array to be returned
		String [] wrongSplit = GetData.splitComments(wrongString);
	
	st.close();
	return wrongSplit;
	}
	
	// Retrieves well comments from feedback tables in db
	public static String [] viewWellComments(Connection conn, String fullName, int teamNum, String projName, int sprintNum) throws SQLException {
		String name, proj, user, first, last;
		int team, sprint;
		name = fullName;
		proj = projName;
		team = teamNum;
		sprint = sprintNum;
		
		// Split first and last name by -;
		String [] firstAndLast = name.split("-;");
		first = firstAndLast[0];
		last = firstAndLast[1];
		
		// Retrieve corresponding username for first and last name
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select user from admin where first = '"+first+"' and last = '"+last+"';");
		rs.next();
		user = rs.getString(1);
		/* Retrieve corresponding wrong comment for 
		 *user name, project and sprint*/
		rs = st.executeQuery("select wellInfo from feedback where user = '"+user+"' and teamNum = '"+team+"' "+
							"and projectName = '"+proj+"' and sprintNum = '"+sprint+"';");
		rs.next();
		String wellString = rs.getString(1);
		while(rs.next()) {
			wellString = wellString + rs.getString(1);
		}
		// Split wrong comment string and put in an array to be returned
		String [] wellSplit = GetData.splitComments(wellString);
	
	st.close();
	return wellSplit;
	}
	
	// Retrieves improve comments from feedback tables in db
	public static String [] viewImproveComments(Connection conn, String fullName, int teamNum, String projName, int sprintNum) throws SQLException {
		String name, proj, user, first, last;
		int team, sprint;
		name = fullName;
		proj = projName;
		team = teamNum;
		sprint = sprintNum;
		
		// Split first and last name by -;
		String [] firstAndLast = name.split("-;");
		first = firstAndLast[0];
		last = firstAndLast[1];
		
		// Retrieve corresponding username for first and last name
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select user from admin where first = '"+first+"' and last = '"+last+"';");
		rs.next();
		user = rs.getString(1);
		/* Retrieve corresponding wrong comment for 
		 *user name, project and sprint*/
		rs = st.executeQuery("select improveInfo from feedback where user = '"+user+"' and teamNum = '"+team+"' "+
							"and projectName = '"+proj+"' and sprintNum = '"+sprint+"';");
		rs.next();
		String improveString = rs.getString(1);
		while(rs.next()) {
			improveString = improveString + rs.getString(1);
		}
		// Split wrong comment string and put in an array to be returned
		String [] improveSplit = GetData.splitComments(improveString);
	
	st.close();
	return improveSplit;
	}
	
	/*Retrieves project name, team number, and sprint number for the given
	 *first and last name*/
	public static ArrayList<Object> getProjEntryInfo(Connection conn, String firstName, String lastName) throws SQLException {
		String first, last, user;
		first = firstName;
		last = lastName;
		ArrayList<Object> projInfo = new ArrayList<Object>();
		// Retrieve user name for given first and last name
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select user from admin where first = '"+first+"' and last = '"+last+"';");
		rs.next();
		user = rs.getString(1);
		/*Get team number, project name, and sprint number from the feedback
		 *table in the db*/
		rs = st.executeQuery("select teamNum, projectName, sprintNum from feedback where user = '"+user+"';");
		
		while(rs.next()) {
			projInfo.add(rs.getInt(1));
			projInfo.add(rs.getString(2));
			projInfo.add(rs.getInt(3));
		}
	
	st.close();
	return projInfo;
	}
	
	/*Retrieves all of the first and last names of the users that entered
	 *comments for a retrorspective*/
	public static ArrayList<String> getAllFeedbackNames(Connection conn) throws SQLException {
		ArrayList<String> names = new ArrayList<String>();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT retrospective_schema.admin.first, retrospective_schema.admin.last \n" + 
										"FROM retrospective_schema.admin\n" + 
										"WHERE EXISTS (SELECT * FROM retrospective_schema.feedback WHERE retrospective_schema.feedback.user = retrospective_schema.admin.user)\n" + 
										"GROUP BY retrospective_schema.admin.first, retrospective_schema.admin.last;");
		while(rs.next()) {
				names.add(rs.getString(1));
				names.add(rs.getString(2));
			}
		
		st.close();
		return names;
	}
	
	//Tells if given username is stored as a scrum master or regular user
	public static boolean isScrum(Connection conn, String uname) throws SQLException {
		int scrum;
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select scrum from admin where user = '"+uname+"';");
		rs.next();
		scrum = rs.getInt(1);
		
		if(scrum == 1) {
			return true;
		}else {
			return false;
		}
	}
	
	//Gets the user full name by the given username
	public static String[] getNameByUser(Connection conn, String uname) throws SQLException {
		String [] name = new String [2];
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select first, last from admin where user = '"+uname+"';");

		rs.next();
			name[0] = rs.getString(1);
			name[1] = rs.getString(2);
		return name;
	}
}
