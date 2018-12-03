package com.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.entity.ScrumCodes;

@Local
public interface ScrumCodesDAOLocal {
	
	List<ScrumCodes> findAll();

}
