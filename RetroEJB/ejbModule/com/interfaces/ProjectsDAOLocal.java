package com.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.entity.Projects;

@Local
public interface ProjectsDAOLocal {

	List<Projects> findAll();

}
