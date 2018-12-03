package com.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.entity.Projects;
import com.interfaces.ProjectsDAOLocal;

/**
 * Session Bean implementation class ProjectsDAO
 */
@Stateless
public class ProjectsDAO implements ProjectsDAOLocal {
	
	@PersistenceContext
	private EntityManager em;

    /**
     * Default constructor. 
     */
    public ProjectsDAO() {
        // TODO Auto-generated constructor stub
    }
    
    @Override
	public List<Projects> findAll() {
		return em.createNamedQuery("Projects.findAll", Projects.class).getResultList();
	}

}
