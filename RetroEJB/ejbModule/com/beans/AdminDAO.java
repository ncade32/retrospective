package com.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.interfaces.AdminDAOLocal;
import com.entity.Admin;

/**
 * Session Bean implementation class AdminLocalDAO
 */
@Stateless
public class AdminDAO implements AdminDAOLocal {
	
	@PersistenceContext
	private EntityManager em;
    /**
     * Default constructor. 
     */
    public AdminDAO() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void createAccount(String first, String last, String user, String pass, String email, int scrum) {
  	   Admin admin = new Admin();
  	   admin.setFirst(first);
  	   admin.setLast(last);
  	   admin.setUser(user);
  	   admin.setPass(pass);
  	   admin.setEmail(email);
  	   admin.setScrum(scrum);
  	   
  	   em.persist(admin);
     }
    
    @Override
    public int getScrum(String uname) {
    	int scrum = (Integer) em.createQuery(
    		    "SELECT a.scrum FROM Admin a WHERE a.user LIKE :user")
    		    .setParameter("user", uname)
    		    .getSingleResult();
    	return scrum;
    }
    
    @Override
    public List<Admin> findAll(){
    	return em.createNamedQuery("Admin.findAll", Admin.class).getResultList();
    }
    
    @Override
    public void deleteRow(String uname) {
    	em.createQuery("Delete FROM Admin a WHERE a.user LIKE :user").setParameter("user", uname).executeUpdate();
    }

}
