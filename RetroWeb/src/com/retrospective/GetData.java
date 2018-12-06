package com.retrospective;

/*This class contains all of the neccesarry methods for retrieve
 *and storing data*/

public class GetData {
	
	// Concatenate comments with the separator @; to store into db
	public static String concateComments(String [] comments) {
		// If user does not enter comment enter none in db
		String commentString;
		if (comments == null) {
			commentString = "none@;";
			return commentString;
		}
		else {
		commentString = comments[0];
		}
		/* If user enters one comment concatenate 
		 *the separator and return string*/
		if(comments.length == 1) {
			commentString = commentString + "@;";
			return commentString;
		}
		/* If user enters more than one comment concatenate
		 * all of the comments and return string*/
		else {
			for(int i = 1; i < comments.length; i++) {
				commentString = commentString + "@;" + comments[i];
			}
			return commentString;
		}
	}
	
	// Split comments by @; and return them in an array
	public static String[] splitComments (String comments) {
		String[] splitted= comments.split("@;");
		return splitted;
	}
	
}
