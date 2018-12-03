package com.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

import org.wildfly.common.annotation.NotNull;

/**
 * Entity implementation class for Entity: Projects
 *
 */
@Entity
@Table(name="projects")
@NamedQuery(name="Projects.findAll", query="SELECT p FROM Projects p")
public class Projects implements Serializable {

	@Id
	@NotNull
	@Column(name = "projectId")
	private int projectId;
	
	@Column(name = "projectName")
	private String projectName;
	
	private static final long serialVersionUID = 1L;

	public Projects() {
		super();
	}   
	public int getProjectId() {
		return this.projectId;
	}

	public void setProjectId(int projectId) {
		this.projectId = projectId;
	}   
	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
   
}
