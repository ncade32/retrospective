package com.retrospective;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

class GetDataClassTest {
	
	public Connection conn = DbManager.connect();

	@Test
	void projectNamesTest() throws SQLException {
		
		ArrayList<String> names = GetData.getProjectNames(conn);
		
		assertEquals("project1", names.get(0));
		assertEquals("project2", names.get(1));
		assertEquals("project3", names.get(2));
		
	}
	
	@Test
	void concateCommentsTest() throws SQLException {
		
		String [] comments = {"apple", "bat", "ball"};
		String commentsConcate = GetData.concateComments(comments);
		
		assertEquals("apple@;bat@;ball", commentsConcate);
		
	}
	
	@Test
	void getTeamNumByUserTest() throws SQLException {
		
		String num = GetData.getTeamNumByUser(conn, "JunitTest");
		
		assertEquals("1", num);
		
	}
	
	@Test
	void getProjectNameByUserTest() throws SQLException {
		
		String proj = GetData.getProjectNameByUser(conn, "JunitTest");
		
		assertEquals("project1", proj);
		
	}
	
	@Test
	void getSprintNumByUserTest() throws SQLException {
		
		String num = GetData.getSprintNumByUser(conn, "JunitTest");
		
		assertEquals("12", num);
		
	}
	
	@Test
	void splitCommentsTest() throws SQLException {
		String comments = "apple@;bat@;ball";
		String [] commentsSplit = GetData.splitComments(comments);
		
		assertEquals("apple", commentsSplit[0]);
		assertEquals("bat", commentsSplit[1]);
		assertEquals("ball", commentsSplit[2]);
		
	}
	
	@Test
	void getWrongCommentsByUserTest() throws SQLException {
		
		String wrong = GetData.getWrongCommentsByUser(conn, "JunitTest");
		
		assertEquals("apple@;", wrong);
		
	}
	
	@Test
	void getWellCommentsByUserTest() throws SQLException {

		String well = GetData.getWellCommentsByUser(conn, "JunitTest");
		
		assertEquals("bat@;", well);
		
	}
	
	@Test
	void getImproveCommentsByUserTest() throws SQLException {
		
		String improve = GetData.getImproveCommentsByUser(conn, "JunitTest");
		
		assertEquals("ball@;", improve);
		
	}
	
	@Test
	void viewWrongCommentsTest() throws Exception {
		String fullName, proj;
		int team, sprint;
		
		fullName = "j-;Unit";
		proj = "project1";
		team = 0;
		sprint = 30;
		
		String [] commentsSplit = GetData.viewWrongComments(conn, fullName, team, proj, sprint);
		
		assertEquals("cat", commentsSplit[0]);
		assertEquals("cat2", commentsSplit[1]);
		assertEquals("cat3", commentsSplit[2]);
		
	}
	
	@Test
	void viewWellCommentsTest() throws Exception {
		String fullName, proj;
		int team, sprint;
		
		fullName = "j-;Unit";
		proj = "project1";
		team = 0;
		sprint = 30;
		
		String [] commentsSplit = GetData.viewWellComments(conn, fullName, team, proj, sprint);
		
		assertEquals("belt", commentsSplit[0]);
		
	}
	
	@Test
	void viewImproveCommentsTest() throws Exception {
		String fullName, proj;
		int team, sprint;
		
		fullName = "j-;Unit";
		proj = "project1";
		team = 0;
		sprint = 30;
		
		String [] commentsSplit = GetData.viewImproveComments(conn, fullName, team, proj, sprint);
		
		assertEquals("fan", commentsSplit[0]);
		assertEquals("fan2", commentsSplit[1]);
		
	}
	
	@Test
	void getProjectEntryInfoTest() throws Exception {
		String first, last;
		
		first = "J";
		last = "Unit";
		ArrayList<Object> projInfo = new ArrayList<Object>();
		
		projInfo = GetData.getProjEntryInfo(conn, first, last);
		
		assertEquals(0, projInfo.get(0));
		assertEquals("project1", projInfo.get(1));
		assertEquals(30, projInfo.get(2));
	}
	
	@Test
	void getAllFeedbackNamesTest() throws Exception {

		ArrayList<String> names = new ArrayList<String>();
		
		names = GetData.getAllFeedbackNames(conn);
		
		//If fails check feedback table. May have changed
		assertEquals("J", names.get(0));
		assertEquals("Unit", names.get(1));
		assertEquals("Norman", names.get(2));
		assertEquals("Cade", names.get(3));
		
	}
	
	@Test
	void isScrum() throws Exception {

		assertEquals(false, GetData.isScrum(conn, "JunitTest"));
		assertEquals(true, GetData.isScrum(conn, "scrum"));
		
	}
	
	@Test
	void getNameByUser() throws Exception {
		String [] name = GetData.getNameByUser(conn, "JunitTest");
		
		assertEquals("J", name[0]);
		assertEquals("Unit", name[1]);
		
		GetData.closeConnection(conn);
	}
		

}
