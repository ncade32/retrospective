/**
 * 
 */
function validateWelcomeForm(){
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

function validateEditForm(){
	var teamNum = document.forms["editForm"]["teamNum"].value;
	var projectName = document.forms["editForm"]["chooseProj"].value;
	var sprintNum = document.forms["editForm"]["sprintNum"].value;
	var wrongHolder = document.getElementById("incomplete-tasks2");
	
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

function validateRegForm(){
	var user = document.forms["registerForm"]["userReg"].value;
	var pass = document.forms["registerForm"]["passReg"].value;
	var confirmPass = document["registerForm"]["confirmPass"].value
	var email = document.forms["registerForm"]["email"].value;
	var first = document.forms["registerForm"]["first"].value;
	var last = document.forms["registerForm"]["last"].value;
	
	if (user == ""){
		window.alert("A valid username must be entered");
		return false;
	}
	else if (pass == ""){
		window.alert("A valid password must be entered");
		return false;
	}
	else if (confirmPass == ""){
		window.alert("Must confirm password");
		return false;
	}
	else if (pass != confirmPass){
		window.alert("Password and confirm password do not match");
		return false;
	}
	else if (email == ""){
		window.alert("A valid email must be entered");
		return false;
	}
	else if (first == ""){
		window.alert("A first name must be entered");
		return false;
	}
	else if (last == ""){
		window.alert("A last name must be entered");
		return false;
	}
}