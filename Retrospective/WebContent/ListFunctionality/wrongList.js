"use strict";
/**
 * 
 */
var wrongItemInput=document.getElementById("new-task");//Add a new task.
var wrongAddButton=document.getElementsByTagName("button")[0];//first button
var incompleteTaskHolder=document.getElementById("incomplete-tasks");//ul of #incomplete-tasks

 
//New task list item
var createNewWrongItem=function(taskString){

	var listItem=document.createElement("li");
	
	//textarea
	var editInput = document.createElement("TEXTAREA");
	//button.edit
	var editButton=document.createElement("button");//edit button

	//button.delete
	var deleteButton=document.createElement("button");//delete button


	//Each elements, needs appending
	editInput.type="text";
	editInput.name="wrong";
	editInput.value = taskString;
	editInput.readOnly = true;
	editInput.wrap = "hard";
	editInput.cols = 4;
	editButton.type = "button";
	deleteButton.type = "button";

	editButton.innerText="Edit";//innerText encodes special characters, HTML does not.
	editButton.value = "Edit";
	editButton.className="edit";
	deleteButton.innerText="Delete";
	deleteButton.className="delete";



	//and appending.
	listItem.appendChild(editInput);
	listItem.appendChild(editButton);
	listItem.appendChild(deleteButton);
	return listItem;
}



var addWrongItem=function(){
	console.log("Add Task...");
	//Create a new list item with the text from the #new-task:
	var listItem=createNewWrongItem(wrongItemInput.value);
	
	//Append listItem to incompleteTaskHolder
	incompleteTaskHolder.appendChild(listItem);
	bindTaskEventsWrong(listItem, addWrongItem);

	wrongItemInput.value="";

}

//Edit an existing task.

var editTask=function(){
console.log("Edit Task...");
console.log("Change 'edit' to 'save'");

var listItem=this.parentNode;

var editButton=listItem.querySelector("button.edit");
var editInput = listItem.querySelector('textarea');

editButton.innerText = "Save";
editInput.readOnly = false;

var containsClass=listItem.classList.contains("editMode");
		//If class of the parent is .editmode
		if(containsClass){

		//switch to .editmode
		//label becomes the inputs value.
			editButton.innerText = "Edit";
			editInput.readOnly = true;
		}
		//toggle .editmode on the parent.
		listItem.classList.toggle("editMode");
}




//Delete task.
var deleteWrongItem=function(){
		console.log("Delete Task...");

		var listItem=this.parentNode;
		var ul=listItem.parentNode;
		//Remove the parent list item from the ul.
		ul.removeChild(listItem);

}

var ajaxRequest=function(){
	console.log("AJAX Request");
}

//The glue to hold it all together.


//Set the click handler to the addWrongItem function.
wrongAddButton.addEventListener("click",addWrongItem);
//wrongAddButton.addEventListener("click",ajaxRequest);


var bindTaskEventsWrong =function(taskListItem,checkBoxEventHandler){
	console.log("bind list item events");
//select ListItems children
	var editButton=taskListItem.querySelector("button.edit");
	var deleteButton=taskListItem.querySelector("button.delete");


			//Bind editTask to edit button.
			editButton.onclick=editTask;
			//Bind deleteWrongItem to delete button.
			deleteButton.onclick=deleteWrongItem;
			//Bind taskCompleted to checkBoxEventHandler.
}