package com.beans;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.entity.Admin;
import com.entity.Feedback;
import com.entity.FeedbackPK;
import com.interfaces.FeedbackDAOLocal;

/**
 * Session Bean implementation class FeedbackDAO
 */
@Stateless
public class FeedbackDAO implements FeedbackDAOLocal {
	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public FeedbackDAO() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void createEntry(String user, int teamNum, String projectName, int sprintNum, String wrongInfo, String wellInfo, String improveInfo, int scrum) {
  	   Feedback feedback = new Feedback();
  	   feedback.setUser(user);
  	   feedback.setTeamNum(teamNum);
	   feedback.setProjectName(projectName);
	   feedback.setSprintNum(sprintNum);
	   feedback.setWrongInfo(wrongInfo);
	   feedback.setWellInfo(wellInfo);
	   feedback.setImproveInfo(improveInfo);
	   feedback.setScrum(scrum);
  	   
  	   em.persist(feedback);
     }
    
    @Override
    public boolean isDuplicateEntry(String uname, int teamNum, String projectName, int sprintNum) {
    	boolean isDuplicate = false;
    	boolean isEmpty = em.createQuery(
    		    "SELECT f.user, f.teamNum, f.projectName, f.sprintNum FROM Feedback f WHERE f.user LIKE :user AND f.teamNum LIKE :teamNum AND f.projectName LIKE :projectName AND f.sprintNum LIKE :sprintNum")
    		    .setParameter("user", uname)
    		    .setParameter("teamNum", teamNum)
    		    .setParameter("projectName", projectName)
    		    .setParameter("sprintNum", sprintNum)
    		    .getResultList()
    		    .isEmpty();
    	if(!isEmpty) {
    		isDuplicate = true;
    	}
    	return isDuplicate;
    }
    
	@Override
    public List<Admin> getAllFeedbackAdmins() {
    	ArrayList<Admin> allAdmin = new ArrayList<Admin>();
    	List<Admin> allAdminUsers = em.createNamedQuery("Admin.findAll", Admin.class).getResultList();
    	List<Feedback> allFeedbackUsers = em.createNamedQuery("Feedback.findAll", Feedback.class).getResultList();
    	
    	for(int i = 0; i < allAdminUsers.size(); i ++) {
    		for(int j = 0; j < allFeedbackUsers.size(); j++) {
    			if(allFeedbackUsers.get(j).getUser().equals(allAdminUsers.get(i).getUser())) {
    				allAdmin.add(allAdminUsers.get(i));
    			}
    		}
    	}
    	return allAdmin;
    }
	
	@Override
	public List<Feedback> getCommentsByProjInfo(String first, String last	) {
		ArrayList<Feedback> userEntries = new ArrayList<Feedback>();
		List<Feedback> allEntries = em.createNamedQuery("Feedback.findAll", Feedback.class).getResultList();
		String user = em.createQuery("SELECT a.user FROM Admin a WHERE a.first LIKE :first AND a.last LIKE :last")
				.setParameter("first", first)
    		    .setParameter("last", last)
    		    .getSingleResult()
    		    .toString();
		
		for(int i = 0; i < allEntries.size(); i++) {
			if(allEntries.get(i).getUser().equals(user)) {
				userEntries.add(allEntries.get(i));
			}
		}
		
		return userEntries;
	}
	

	@Override
	public List<Feedback> viewComments(String first, String last, int teamNum, String projectName, int sprintNum) {
		String user = em.createQuery("SELECT a.user FROM Admin a WHERE a.first LIKE :first AND a.last LIKE :last")
				.setParameter("first", first)
    		    .setParameter("last", last)
    		    .getSingleResult()
    		    .toString();
		
		System.out.println(user);
		
		@SuppressWarnings("unchecked")
		List<Feedback> comment = em.createQuery(
    		    "SELECT f FROM Feedback f WHERE f.user LIKE :user AND f.teamNum LIKE :teamNum AND f.projectName LIKE :projectName AND f.sprintNum LIKE :sprintNum")
    		    .setParameter("user", user)
    		    .setParameter("teamNum", teamNum)
    		    .setParameter("projectName", projectName)
    		    .setParameter("sprintNum", sprintNum)
    		    .getResultList();
		
		return comment;
	}
    	
    
    @Override
    public List<Feedback> findAll(){
    	return em.createNamedQuery("Feedback.findAll", Feedback.class).getResultList();
    }

}
