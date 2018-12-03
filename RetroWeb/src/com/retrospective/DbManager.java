package com.retrospective;

import java.sql.*;

// This class is used to connect to the db

public class DbManager {
	
	public static Connection connect(){
		try {
			
			String url = "jdbc:mysql://localhost:3306/retrospective_schema?autoReconnect=true&useSSL=false&serverTimezone=UTC";
			String user = "root";
			String pass = "Fall2018!";
			
			
			Connection conn = DriverManager.getConnection(url, user, pass);
			return conn;
		}catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
}
