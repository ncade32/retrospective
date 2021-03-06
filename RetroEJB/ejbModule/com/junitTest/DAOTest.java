package com.junitTest;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

//import org.junit.Test;

import com.interfaces.AdminDAOLocal;

public class DAOTest extends TestCase{
	/*java:global/RetroProject/RetroEJB/AdminDAO!com.interfaces.AdminDAOLocal
	java:app/RetroEJB/AdminDAO!com.interfaces.AdminDAOLocal
	java:module/AdminDAO!com.interfaces.AdminDAOLocal
	ejb:RetroProject/RetroEJB/AdminDAO!com.interfaces.AdminDAOLocal
	java:global/RetroProject/RetroEJB/AdminDAO
	java:app/RetroEJB/AdminDAO
	java:module/AdminDAO*/
	
	

	//@Test
	public void testCreateAccount() throws NamingException {
		
		/*EJBContainer ejbContainer = EJBContainer.createEJBContainer();
        System.out.println("Starting the container");
        Context context = ejbContainer.getContext();*/

        final Context context = EJBContainer.createEJBContainer().getContext();
		
		
		AdminDAOLocal adminDAO = (AdminDAOLocal) context.lookup("java:global/RetroEJB/AdminDAO"); 
		
		assertNotNull(adminDAO);
		
		/*String first, last, user, pass, email, foundUser = null;
		int scrum;
		List<Admin> allAdmin = adminDAO.findAll();
		first = "J";
		last = "Unit";
		user = "JunitUser";
		pass = "pass";
		email = "Junit@example.com";
		scrum = 0;
		
		//adminDAO.createAccount(first, last, user, pass, email, scrum);
		
		for(int i = 0; i < allAdmin.size(); i++) {
			if(allAdmin.get(i).getUser().equals(user)) {
				foundUser = allAdmin.get(i).getUser();
			}
		}
		assertEquals(4, allAdmin.size());*/
	}
	
	//@Test
	public void testGetScrum() {
		fail("Not yet implemented");
	}

	//@Test
	public void testFindAll() {
		fail("Not yet implemented");
	}

	//@Test
	public void testDeleteRow() {
		fail("Not yet implemented");
	}

}
