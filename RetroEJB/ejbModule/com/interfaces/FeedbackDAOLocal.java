package com.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.entity.Admin;
import com.entity.Feedback;

@Local
public interface FeedbackDAOLocal {

	List<Feedback> findAll();

	void createEntry(String user, int teamNum, String projectName, int sprintNum, String wrongInfo, String wellInfo,
			String improveInfo, int scrum);

	boolean isDuplicateEntry(String uname, int teamNum, String projectName, int sprintNum);

	List<Admin> getAllFeedbackAdmins();

	List<Feedback> getCommentsByProjInfo(String first, String last);

	List<Feedback> viewComments(String first, String last, int teamNum, String projectName, int sprintNum);


}
