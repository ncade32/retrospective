package com.retrospective;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class ValidUser {
	
	public static boolean isValidUser(String user) {
		
		Connection conn = DbManager.connect();
		
		ArrayList<String> dbUsers = new ArrayList<String>();
		Statement st;
		
		try {
			st = conn.createStatement();
			ResultSet rs = st.executeQuery("select user from admin;");
			
			while(rs.next()) {
				dbUsers.add(rs.getString(1));
			}
			
			for(int i = 0; i < dbUsers.size(); i++) {
				if (user.equals(dbUsers.get(i))) {
					return false;
				}
			}
		}catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Failed to create new user");
			e.printStackTrace();
		}
		return true;

	}

}
