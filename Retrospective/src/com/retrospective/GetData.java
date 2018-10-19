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
	
	public static String getTeamNum(Connection conn, String uname) throws SQLException {
		String teamNum;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select teamNum from onlineUsers where user = '"+uname+"';");
		rs.next();
		teamNum = rs.getString(1);
		return teamNum;
	}
	
	public static String getProjectName(Connection conn, String uname) throws SQLException {
		String projName;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select projectName from onlineUsers where user = '"+uname+"';");
		rs.next();
		projName = rs.getString(1);
		return projName;
	}
	
	public static String getSprintNum(Connection conn, String uname) throws SQLException {
		String sprintNum;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select sprintNum from onlineUsers where user = '"+uname+"';");
		rs.next();
		sprintNum = rs.getString(1);
		return sprintNum;
	}
	
	public static String[] splitComments (String comments) {
		String[] splitted= comments.split("@;");
		return splitted;
	}
	
	public static String getWrongComments(Connection conn, String uname) throws SQLException {
		String wrong;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select wrongInfo from onlineUsers where user = '"+uname+"';");
		rs.next();
		wrong = rs.getString(1);
		return wrong;
	}
	
	public static String getWellComments(Connection conn, String uname) throws SQLException {
		String well;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select wellInfo from onlineUsers where user = '"+uname+"';");
		rs.next();
		well = rs.getString(1);
		return well;
	}
	
	public static String getImproveComments(Connection conn, String uname) throws SQLException {
		String improve;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select improveInfo from onlineUsers where user = '"+uname+"';");
		rs.next();
		improve = rs.getString(1);
		return improve;
	}
	
	public static void deleteRow(Connection conn, String uname) throws SQLException {
		Statement st = conn.createStatement();
		st.execute("delete from onlineUsers where user = '"+uname+"';" );
		System.out.println(uname + " row deleted from olineUsers table");
	}
	
	public static void closeConnection(Connection conn) throws Exception {
		conn.close();
		System.out.println("Connection Closed");
	}
	
	

}
