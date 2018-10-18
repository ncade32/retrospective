/**
 * 
 */
function validateForm(){
	var teamNum = document.forms["welcomeForm"]["teamNum"].value;
	var projectName = document.forms["welcomeForm"]["chooseProj"].value;
	var sprintNum = document.forms["welcomeForm"]["sprintNum"].value;
	var wrongHolder = document.getElementById("incomplete-tasks");
	
	if (teamNum == ""){
		window.alert("A team's number must be entered");
		return false;
	}
	else if (projectName == ""){
		window.alert("A project much be chosen");
		return false;
	}
	else if (sprintNum == ""){
		window.alert("A sprint number must be entered");
		return false;
	}
	else if (isNaN(teamNum)){
		window.alert("Team number must be a number");
		return false;
	}
	else if(isNaN(sprintNum)){
		window.alert("Sprint number must be a number");
		return false;
	}
	else if (wrongHolder.children.length == 0){
		window.alert("What went wrong must be filled out");
		return false;
	}
}