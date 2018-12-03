package com.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.entity.OnlineUsers;

@Local
public interface OnlineUsersDAOLocal {

	List<OnlineUsers> findAll();

	void createOnlineUser(String user, int teamNum, String projectName, int sprintNum, String wrongInfo,
			String wellInfo, String improveInfo, int scrum);

}
