/**
 * 
 */
//This function makes sure the welcome page form is filled out correctly
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
		window.alert("A project must be chosen");
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
	else if (wrongHolder.children.length == 0 || wrongHolder.chil){
		window.alert("What went wrong must be filled out");
		return false;
	}
}

//This function makes sure that the editInfo page form is filled out correctly
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

//This function makes sure that the registration page form is filled out correctly
function validateRegForm(){
	var user = document.forms["regForm"]["userReg"].value;
	var pass = document.forms["regForm"]["passReg"].value;
	var confirmPass = document["regForm"]["confirmPass"].value
	var email = document.forms["regForm"]["email"].value;
	var first = document.forms["regForm"]["first"].value;
	var last = document.forms["regForm"]["last"].value;
	var scrum = document.forms["regForm"]["scrumMaster"];
	
	if (first == ""){
		window.alert("A first name must be entered");
		return false;
	}
	else if (last == ""){
		window.alert("A last name must be entered");
		return false;
	}
	else if (email == ""){
		window.alert("A valid email must be entered");
		return false;
	}
	else if (user == ""){
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
	else if(scrum.checked){
		var code = document.forms["regForm"]["code"].value;
		if (code == ""){
			window.alert("A scrum code must be entered");
			return false;
		}
	}
}

//This function implements the show password checkbox on the registration page
function showPassword(pass, checkbox){
	var showPass = document.getElementById(checkbox);
	var password = document.getElementById(pass);
	
	if (showPass.checked){
		password.type = "text";
	}else {
		password.type = "password";
	}
}

//This function implements the ability to enter the scrum code on the registration page
function scrumCode(){
	 
	var scrumMaster = document.getElementById("scrumMaster");
	var form = document.getElementById("regForm");
	var mainErrorDiv = document.getElementById("mainErrorDiv");
	var errorMsg = mainErrorDiv.innerHTML;
	
	if (scrumMaster.checked){
		var div = document.createElement("div");
		var errorDiv = document.createElement("div");
		//var error = document.createElement("text");
		var input = document.createElement("input");
		var checkbox = document.createElement("input");
		var label = document.createElement("label");
		
		errorDiv.innerHTML = errorMsg;
		errorDiv.id = "errorDiv";
		errorDiv.name = "errorDiv";
		
		div.classList.add('scrum-code');
		input.type = "password";
		input.name = "code";
		input.id = "code";
		input.placeholder = "Enter Code";
		
		checkbox.type = "checkbox";
		checkbox.id = "showCode";
		checkbox.name = "showCode";
		checkbox.value = "Show";
		checkbox.classList.add('showpass-check-input');
		
		label.innerHTML = "Show";
		label.classList.add('showpass-label');
		
		
		div.appendChild(input);
		div.appendChild(checkbox);
		div.appendChild(label);
		
		form.insertBefore(div, form.childNodes[31]);
		//form.insertBefore(errorDiv, form.childNodes[20]);
		
		document.getElementById("showCode").setAttribute("onClick", "showPassword('code', 'showCode')");

	}else{
		let errorDiv = document.getElementById("errorDiv");
		form.removeChild(form.childNodes[31]);
	}
	
}