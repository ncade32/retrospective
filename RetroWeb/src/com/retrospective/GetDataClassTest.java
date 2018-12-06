package com.retrospective;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

class GetDataClassTest {

	@Test
	void concateCommentsTest(){
		
		String [] comments = {"apple", "bat", "ball"};
		String commentsConcate = GetData.concateComments(comments);
		
		assertEquals("apple@;bat@;ball", commentsConcate);
		
	}
	
	@Test
	void splitCommentsTest(){
		String comments = "apple@;bat@;ball";
		String [] commentsSplit = GetData.splitComments(comments);
		
		assertEquals("apple", commentsSplit[0]);
		assertEquals("bat", commentsSplit[1]);
		assertEquals("ball", commentsSplit[2]);
		
	}
}
