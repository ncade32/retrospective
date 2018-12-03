package com.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;

import org.wildfly.common.annotation.NotNull;

import javax.persistence.*;

/**
 * Entity implementation class for Entity: Admin
 *
 */
@Entity
@Table(name="admin")
@NamedQuery(name="Admin.findAll", query="SELECT a FROM Admin a")
public class Admin implements Serializable {

	   
	@Id
	@NotNull
	@Column(name = "user")
	private String user;
	@Column(name = "pass")
	private String pass;
	@Column(name = "first")
	private String first;
	@Column(name = "last")
	private String last;
	@Column(name = "email")
	private String email;
	@NotNull
	@Column(name = "scrum")
	private int scrum;
	private static final long serialVersionUID = 1L;

	public Admin() {
		super();
	}   
	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}   
	public String getPass() {
		return this.pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}   
	public String getFirst() {
		return this.first;
	}

	public void setFirst(String first) {
		this.first = first;
	}   
	public String getLast() {
		return this.last;
	}

	public void setLast(String last) {
		this.last = last;
	}   
	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}   
	public int getScrum() {
		return this.scrum;
	}

	public void setScrum(int scrum) {
		this.scrum = scrum;
	}
   
}
