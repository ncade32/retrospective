package com.beans;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.entity.ScrumCodes;
import com.interfaces.ScrumCodesDAOLocal;

/**
 * Session Bean implementation class ScrumCodesDAO
 */
@Stateless
public class ScrumCodesDAO implements ScrumCodesDAOLocal {

	@PersistenceContext
	private EntityManager em;
	
    /**
     * Default constructor. 
     */
    public ScrumCodesDAO() {
        // TODO Auto-generated constructor stub
    }

	@Override
	public List<ScrumCodes> findAll() {
		return em.createNamedQuery("ScrumCodes.findAll", ScrumCodes.class).getResultList();
	}

}
