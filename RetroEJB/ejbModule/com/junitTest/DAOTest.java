package com.junitTest;

import static org.junit.Assert.*;

import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.Test;

import com.entity.Admin;
import com.interfaces.AdminDAOLocal;

public class DAOTest {
	/*java:global/RetroProject/RetroEJB/AdminDAO!com.interfaces.AdminDAOLocal
	java:app/RetroEJB/AdminDAO!com.interfaces.AdminDAOLocal
	java:module/AdminDAO!com.interfaces.AdminDAOLocal
	ejb:RetroProject/RetroEJB/AdminDAO!com.interfaces.AdminDAOLocal
	java:global/RetroProject/RetroEJB/AdminDAO
	java:app/RetroEJB/AdminDAO
	java:module/AdminDAO*/

	@Test
	public void testCreateAccount() throws NamingException {
		
		AdminDAOLocal admin = (AdminDAOLocal)InitialContext.doLookup("java:module/AdminDAO"); 
		
		String first, last, user, pass, email, foundUser = null;
		int scrum;
		List<Admin> allAdmin = admin.findAll();
		
		first = "J";
		last = "Unit";
		user = "JunitUser";
		pass = "pass";
		email = "Junit@example.com";
		scrum = 0;
		
		admin.createAccount(first, last, user, pass, email, scrum);
		
		for(int i = 0; i < allAdmin.size(); i++) {
			if(allAdmin.get(i).getUser().equals(user)) {
				foundUser = allAdmin.get(i).getUser();
			}
		}
		assertEquals(user, foundUser);
	}

	@Test
	public void testGetScrum() {
		fail("Not yet implemented");
	}

	@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	@Test
	public void testDeleteRow() {
		fail("Not yet implemented");
	}

}
