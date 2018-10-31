package com.retrospective;

import java.sql.*;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class GetData {
	
	public static ArrayList<String> getProjectNames(Connection conn) throws SQLException {
		ArrayList<String> names = new ArrayList<String>();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select projectName from projects");
		
		while (rs.next()) {
			names.add(rs.getString("projectName"));
		}
		st.close();
		return names;
	}
	
	public static String storeComments(String [] comments) {
		String commentString;
		if (comments == null) {
			commentString = "none@;";
			return commentString;
		}
		else {
		commentString = comments[0];
		}
		
		if(comments.length == 1) {
			commentString = commentString + "@;";
			return commentString;
		}
		else {
			for(int i = 1; i < comments.length; i++) {
				commentString = commentString + "@;" + comments[i];
			}
			return commentString;
		}
	}
	
	public static String getUname(HttpServletRequest request) throws Exception {
		Object user;
		HttpSession session = request.getSession();
		user = session.getAttribute("user");
		String uname = user.toString();
		return uname;
	}
	
	public static String getTeamNumByUser(Connection conn, String uname) throws SQLException {
		String teamNum;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select teamNum from onlineUsers where user = '"+uname+"';");
		rs.next();
		teamNum = rs.getString(1);
		st.close();
		return teamNum;
	}
	
	public static String getProjectNameByUser(Connection conn, String uname) throws SQLException {
		String projName;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select projectName from onlineUsers where user = '"+uname+"';");
		rs.next();
		projName = rs.getString(1);
		st.close();
		return projName;
	}
	
	public static String getSprintNumByUser(Connection conn, String uname) throws SQLException {
		String sprintNum;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select sprintNum from onlineUsers where user = '"+uname+"';");
		rs.next();
		sprintNum = rs.getString(1);
		st.close();
		return sprintNum;
	}
	
	public static String[] splitComments (String comments) {
		String[] splitted= comments.split("@;");
		return splitted;
	}
	
	public static String getWrongCommentsByUser(Connection conn, String uname) throws SQLException {
		String wrong;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select wrongInfo from onlineUsers where user = '"+uname+"';");
		rs.next();
		wrong = rs.getString(1);
		st.close();
		return wrong;
	}
	
	public static String getWellCommentsByUser(Connection conn, String uname) throws SQLException {
		String well;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select wellInfo from onlineUsers where user = '"+uname+"';");
		rs.next();
		well = rs.getString(1);
		st.close();
		return well;
	}
	
	public static String getImproveCommentsByUser(Connection conn, String uname) throws SQLException {
		String improve;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select improveInfo from onlineUsers where user = '"+uname+"';");
		rs.next();
		improve = rs.getString(1);
		st.close();
		return improve;
	}
	
	public static void deleteRow(Connection conn, String uname) throws SQLException {
		Statement st = conn.createStatement();
		st.execute("delete from onlineUsers where user = '"+uname+"';" );
		System.out.println(uname + " row deleted from olineUsers table");
		st.close();
	}
	
	public static void closeConnection(Connection conn) throws Exception {
		conn.close();
		System.out.println("Connection Closed");
	}
	
	public static ArrayList<String> viewAllFirstNames(Connection conn) throws SQLException {
		ArrayList<String> first = new ArrayList<String>();
		ArrayList<String> duplicates = new ArrayList<String>();
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select retrospective_schema.admin.first from retrospective_schema.admin "
										+ "inner join retrospective_schema.feedback on "
										+ "retrospective_schema.admin.user = retrospective_schema.feedback.user;");
		while(rs.next()) {
			duplicates.add(rs.getString(1));
		}
		for(int i = 0; i < duplicates.size(); i++) {
			if (first.contains(duplicates.get(i)) == false) {
				first.add(duplicates.get(i));
			}
		}
		st.close();
		return first;
	}
	
	public static String [] viewWrongComments(Connection conn, String fullName, int teamNum, String projName, int sprintNum) throws SQLException {
		String name, proj, user, first, last;
		int team, sprint;
		name = fullName;
		proj = projName;
		team = teamNum;
		sprint = sprintNum;
		//ArrayList<String> comments = new ArrayList<String>();
		
		String [] firstAndLast = name.split("-;");
		first = firstAndLast[0];
		last = firstAndLast[1];
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select user from admin where first = '"+first+"' and last = '"+last+"';");
		rs.next();
		user = rs.getString(1);
		rs = st.executeQuery("select wrongInfo from feedback where user = '"+user+"' and teamNum = '"+team+"' "+
							"and projectName = '"+proj+"' and sprintNum = '"+sprint+"';");
		
		rs.next();
		String wrongString = rs.getString(1);
		
		String [] wrongSplit = GetData.splitComments(wrongString);
	
	st.close();
	return wrongSplit;
	}
	
	public static String [] viewWellComments(Connection conn, String fullName, int teamNum, String projName, int sprintNum) throws SQLException {
		String name, proj, user, first, last;
		int team, sprint;
		name = fullName;
		proj = projName;
		team = teamNum;
		sprint = sprintNum;
		//ArrayList<String> comments = new ArrayList<String>();
		
		String [] firstAndLast = name.split("-;");
		first = firstAndLast[0];
		last = firstAndLast[1];
		
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select user from admin where first = '"+first+"' and last = '"+last+"';");
		rs.next();
		user = rs.getString(1);
		rs = st.executeQuery("select wellInfo from feedback where user = '"+user+"' and teamNum = '"+team+"' "+
							"and projectName = '"+proj+"' and sprintNum = '"+sprint+"';");
		rs.next();
		String wellString = rs.getString(1);
		while(rs.next()) {
			wellString = wellString + rs.getString(1);
		}
		
		String [] wellSplit = GetData.splitComments(wellString);
	
	st.close();
	return wellSplit;
	}
	
	public static String [] viewImproveComments(Connection conn, String fullName, int teamNum, String projName, int sprintNum) throws SQLException {
		String name, proj, user, first, last;
		int team, sprint;
		name = fullName;
		proj = projName;
		team = teamNum;
		sprint = sprintNum;
		//ArrayList<String> comments = new ArrayList<String>();
		
		String [] firstAndLast = name.split("-;");
		first = firstAndLast[0];
		last = firstAndLast[1];
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select user from admin where first = '"+first+"' and last = '"+last+"';");
		rs.next();
		user = rs.getString(1);
		rs = st.executeQuery("select improveInfo from feedback where user = '"+user+"' and teamNum = '"+team+"' "+
							"and projectName = '"+proj+"' and sprintNum = '"+sprint+"';");
		rs.next();
		String improveString = rs.getString(1);
		while(rs.next()) {
			improveString = improveString + rs.getString(1);
		}
		
		String [] improveSplit = GetData.splitComments(improveString);
	
	st.close();
	return improveSplit;
	}
	
	public static ArrayList<Object> getProjEntryInfo(Connection conn, String firstName, String lastName) throws SQLException {
		String first, last, user;
		first = firstName;
		last = lastName;
		ArrayList<Object> projInfo = new ArrayList<Object>();
		
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select user from admin where first = '"+first+"' and last = '"+last+"';");
		rs.next();
		user = rs.getString(1);
		rs = st.executeQuery("select teamNum, projectName, sprintNum from feedback where user = '"+user+"';");
		
		while(rs.next()) {
			projInfo.add(rs.getInt(1));
			projInfo.add(rs.getString(2));
			projInfo.add(rs.getInt(3));
		}
	
	st.close();
	return projInfo;
	}
	
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
	
	

}
