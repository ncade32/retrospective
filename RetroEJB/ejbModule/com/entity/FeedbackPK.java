package com.entity;

import java.io.Serializable;
import java.lang.String;

import javax.persistence.NamedQuery;

/**
 * ID class for entity: Feedback
 *
 */ 
@NamedQuery(name="FeedbackPK.findAll", query="SELECT f.user, f.teamNum, f.projectName, f.sprintNum FROM Feedback f")
public class FeedbackPK  implements Serializable {   
   
	         
	private String user;         
	private int teamNum;         
	private String projectName;         
	private int sprintNum;
	private static final long serialVersionUID = 1L;

	public FeedbackPK() {}

	

	public String getUser() {
		return this.user;
	}

	public void setUser(String user) {
		this.user = user;
	}
	

	public int getTeamNum() {
		return this.teamNum;
	}

	public void setTeamNum(int teamNum) {
		this.teamNum = teamNum;
	}
	

	public String getProjectName() {
		return this.projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	

	public int getSprintNum() {
		return this.sprintNum;
	}

	public void setSprintNum(int sprintNum) {
		this.sprintNum = sprintNum;
	}
	
   
	/*
	 * @see java.lang.Object#equals(Object)
	 */	
	public boolean equals(Object o) {
		if (o == this) {
			return true;
		}
		if (!(o instanceof FeedbackPK)) {
			return false;
		}
		FeedbackPK other = (FeedbackPK) o;
		return true
			&& (getUser() == null ? other.getUser() == null : getUser().equals(other.getUser()))
			&& getTeamNum() == other.getTeamNum()
			&& (getProjectName() == null ? other.getProjectName() == null : getProjectName().equals(other.getProjectName()))
			&& getSprintNum() == other.getSprintNum();
	}
	
	/*	 
	 * @see java.lang.Object#hashCode()
	 */	
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (getUser() == null ? 0 : getUser().hashCode());
		result = prime * result + getTeamNum();
		result = prime * result + (getProjectName() == null ? 0 : getProjectName().hashCode());
		result = prime * result + getSprintNum();
		return result;
	}
   
   
}
