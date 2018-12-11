package com.junitTest;

import static org.junit.Assert.*;
import junit.framework.TestCase;

import java.util.Hashtable;
import java.util.List;
import java.util.Properties;

import javax.ejb.embeddable.EJBContainer;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.beans.AdminDAO;
import com.entity.Admin;
import com.interfaces.AdminDAOLocal;

public class DAOTest extends TestCase{
	/*java:global/RetroProject/RetroEJB/AdminDAO!com.interfaces.AdminDAOLocal
	java:app/RetroEJB/AdminDAO!com.interfaces.AdminDAOLocal
	java:module/AdminDAO!com.interfaces.AdminDAOLocal
	ejb:RetroProject/RetroEJB/AdminDAO!com.interfaces.AdminDAOLocal
	java:global/RetroProject/RetroEJB/AdminDAO
	java:app/RetroEJB/AdminDAO
	java:module/AdminDAO*/
	
	private static EJBContainer ejbContainer;
    private static Context ctx;
    private static List<Admin> allAdmin;
   
    @BeforeClass
    public static void setUpClass() throws NamingException {
    	Properties env = new Properties();
        env.setProperty(Context.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
        env.setProperty(Context.PROVIDER_URL, "localhost:1099");
        env.setProperty("java.naming.factory.url.pkgs","org.jboss.naming:org.jnp.interfaces");
        ctx = new InitialContext(env);
        try {
            AdminDAOLocal admin = (AdminDAOLocal) ctx.lookup("java:global/RetroProject/RetroEJB/AdminDAO");
            allAdmin = admin.findAll();
        } catch (NamingException e) {
            e.printStackTrace();
        }
    }
    	 /*ejbContainer = EJBContainer.createEJBContainer();
         System.out.println("Starting the container");
         ctx = ejbContainer.getContext();
    }*/
    /*
    @AfterClass
    public static void tearDownClass() {
    	ejbContainer.close();
        System.out.println("Closing the container");
    }*/

	@Test
	public void testCreateAccount() throws NamingException {
		/*final Properties p = new Properties();
		p.put("retroDb", "new://Resource?type=DataSource");
		p.put("retroDb.JdbcDriver", "com.mysql.cj.jdbc.Driver");
		p.put("retroDb.JdbcUrl", "jdbc:mysql://localhost:3306/retrospective_schema");
		
		final Context context = EJBContainer.createEJBContainer(p).getContext();
		*/
		//AdminDAO adminDAO = (AdminDAO) ctx.lookup("java:app/RetroEJB/AdminDAO!com.interfaces.AdminDAOLocal"); 
		
		String first, last, user, pass, email, foundUser = null;
		int scrum;
		//List<Admin> allAdmin = adminDAO.findAll();
		first = "J";
		last = "Unit";
		user = "JunitUser";
		pass = "pass";
		email = "Junit@example.com";
		scrum = 0;
		
		//adminDAO.createAccount(first, last, user, pass, email, scrum);
		
		/*for(int i = 0; i < allAdmin.size(); i++) {
			if(allAdmin.get(i).getUser().equals(user)) {
				foundUser = allAdmin.get(i).getUser();
			}
		}*/
		assertEquals(4, allAdmin.size());
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
