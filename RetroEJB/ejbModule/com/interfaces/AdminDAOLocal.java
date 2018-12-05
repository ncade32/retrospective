package com.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.entity.Admin;

@Local
public interface AdminDAOLocal {
	
	List<Admin> findAll();

	void createAccount(String first, String last, String user, String pass, String email, int scrum);
	
	int getScrum(String uname);

	void deleteRow(String uname);

}
