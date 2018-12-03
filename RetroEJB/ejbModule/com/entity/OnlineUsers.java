package com.entity;

import java.io.Serializable;
import java.lang.String;
import javax.persistence.*;

import org.wildfly.common.annotation.NotNull;

/**
 * Entity implementation class for Entity: onlineUsers
 *
 */
@Entity
@Table(name="onlineUsers")
@NamedQuery(name="OnlineUsers.findAll", query="SELECT o FROM OnlineUsers o")
public class OnlineUsers implements Serializable {

	   
	@Id
	@NotNull
	@Column(name = "user")
	private String user;
	@Column(name = "teamNum")
	private int teamNum;
	@Column(name = "projectName")
	private String projectName;
	@Column(name = "sprintNum")
	private int sprintNum;
	@Column(name = "wrongInfo")
	private String wrongInfo;
	@Column(name = "wellInfo")
	private String wellInfo;
	@Column(name = "improveInfo")
	private String improveInfo;
	@NotNull
	@Column(name = "scrum")
	private int scrum;
	private static final long serialVersionUID = 1L;

	public OnlineUsers() {
		super();
	}   
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
	public String getWrongInfo() {
		return this.wrongInfo;
	}

	public void setWrongInfo(String wrongInfo) {
		this.wrongInfo = wrongInfo;
	}   
	public String getWellInfo() {
		return this.wellInfo;
	}

	public void setWellInfo(String wellInfo) {
		this.wellInfo = wellInfo;
	}   
	public String getImproveInfo() {
		return this.improveInfo;
	}

	public void setImproveInfo(String improveInfo) {
		this.improveInfo = improveInfo;
	}   
	public int getScrum() {
		return this.scrum;
	}

	public void setScrum(int scrum) {
		this.scrum = scrum;
	}
   
}
