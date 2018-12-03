package com.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.entity.OnlineUsers;
import com.interfaces.OnlineUsersDAOLocal;

/**
 * Session Bean implementation class OnlineUsersDAO
 */
@Stateless
public class OnlineUsersDAO implements OnlineUsersDAOLocal {
	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public OnlineUsersDAO() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
    public void createOnlineUser(String user, int teamNum, String projectName, int sprintNum, String wrongInfo, String wellInfo, String improveInfo, int scrum) {
  	   OnlineUsers onlineUsers = new OnlineUsers();
  	   onlineUsers.setUser(user);
  	   onlineUsers.setTeamNum(teamNum);
  	   onlineUsers.setProjectName(projectName);
  	   onlineUsers.setSprintNum(sprintNum);
  	   onlineUsers.setWrongInfo(wrongInfo);
  	   onlineUsers.setWellInfo(wellInfo);
  	   onlineUsers.setImproveInfo(improveInfo);
  	   onlineUsers.setScrum(scrum);
  	   
  	   em.persist(onlineUsers);
     }
    
    @Override
	public List<OnlineUsers> findAll() {
		return em.createNamedQuery("OnlineUsers.findAll", OnlineUsers.class).getResultList();
	}

}
