package com.retrospective;

import java.sql.*;
import java.util.ArrayList;

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
	
	public static int getId(Connection conn) throws Exception {
		int id;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM feedback");
		rs.next();
		id = rs.getInt(1);
		st.close();
		return id;
	}
	
	public static String getTeamNum(Connection conn, int id) throws SQLException {
		String teamNum;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select teamNum from feedback where idfeedback = '"+id+"';");
		rs.next();
		teamNum = rs.getString(1);
		return teamNum;
	}
	
	public static String getProjectName(Connection conn, int id) throws SQLException {
		String projName;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select projectName from feedback where idfeedback = '"+id+"';");
		rs.next();
		projName = rs.getString(1);
		return projName;
	}
	
	public static String getSprintNum(Connection conn, int id) throws SQLException {
		String sprintNum;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select sprintNum from feedback where idfeedback = '"+id+"';");
		rs.next();
		sprintNum = rs.getString(1);
		return sprintNum;
	}
	
	public static String[] splitComments (String comments) {
		String[] splitted= comments.split("@;");
		return splitted;
	}
	
	public static String getWrongComments(Connection conn, int id) throws SQLException {
		String wrong;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select wrongInfo from feedback where idfeedback = '"+id+"';");
		rs.next();
		wrong = rs.getString(1);
		return wrong;
	}
	
	public static String getWellComments(Connection conn, int id) throws SQLException {
		String well;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select wellInfo from feedback where idfeedback = '"+id+"';");
		rs.next();
		well = rs.getString(1);
		return well;
	}
	
	public static String getImproveComments(Connection conn, int id) throws SQLException {
		String improve;
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select improveInfo from feedback where idfeedback = '"+id+"';");
		rs.next();
		improve = rs.getString(1);
		return improve;
	}
	
	public static void deleteRow(Connection conn, int id) throws SQLException {
		Statement st = conn.createStatement();
		st.execute("delete from feedback where idfeedback = '"+id+"';" );
		System.out.println("Row "+ id +" deleted");
	}
	
	public static void closeConnection(Connection conn) throws Exception {
		conn.close();
		System.out.println("Connection Closed");
	}
	
	

}
