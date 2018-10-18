/**
 * 
 */

var wellItemInputEdit= document.getElementById("wellNew-task2");//Add a new task.
var wellAddButtonEdit=document.getElementById("addWell");//first button
var completeTaskHolderEdit= document.getElementById("complete-tasks2");//ul of #incomplete-tasks
var wellHolderChild=document.getElementById("content").firstChild;


//New task list item
var createNewWellItemEdit=function(taskString){

	var listItem=document.createElement("li");

	//label
	var label=document.createElement("label");//label
	//input (text)
	var editInput=document.createElement("input");//text
	//button.edit
	var editButton=document.createElement("button");//edit button

	//button.delete
	var deleteButton=document.createElement("button");//delete button

	label.innerText=taskString;

	//Each elements, needs appending
	editInput.type="text";
	editInput.name="well";
	editInput.value = taskString;
	editButton.type = "button";
	deleteButton.type = "button";

	editButton.innerText="Edit";//innerText encodes special characters, HTML does not.
	editButton.className="edit";
	deleteButton.innerText="Delete";
	deleteButton.className="delete";



	//and appending.
	listItem.appendChild(label);
	listItem.appendChild(editInput);
	listItem.appendChild(editButton);
	listItem.appendChild(deleteButton);
	return listItem;
}



var addWellItemEdit=function(){
	console.log("Add Task...");
	//Create a new list item with the text from the #new-task:
	var listItem=createNewWellItemEdit(wellItemInputEdit.value);
	/*
	for(i = 0; i < completeTaskHolderChildren.length; i++){
		if(wellHolderChild.id.equals("none")){
			wellTaskHolderEdit.removeChild(wellTaskHolderEdit.firstChild);
		}
	}
*/
	//Append listItem to completeTaskHolderEdit
	completeTaskHolderEdit.appendChild(listItem);
	bindTaskEventsWell(listItem, addWellItemEdit);

	wellItemInputEdit.value="";
	
	
}

//Edit an existing task.

var editWellItemEdit=function(){
console.log("Edit Task...");
console.log("Change 'edit' to 'save'");


var listItem=this.parentNode;

var editInput=listItem.querySelector('input[type=text]');
var label=listItem.querySelector("label");
var containsClass=listItem.classList.contains("editMode");
var editButton=listItem.querySelector("button.edit");

editButton.innerText = "Save";
		//If class of the parent is .editmode
		if(containsClass){

		//switch to .editmode
		//label becomes the inputs value.
			label.innerText=editInput.value;
			editButton.innerText = "Edit";
		}else{
			editInput.value=label.innerText;
		}

		//toggle .editmode on the parent.
		listItem.classList.toggle("editMode");
}




//Delete task.
var deleteWellItemEdit= function(){
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


//Set the click handler to the addWellItemEdit function.
wellAddButtonEdit.addEventListener("click",addWellItemEdit);
//wellItemInputEdit.addEventListener("click",ajaxRequest);


var bindTaskEventsWell=function(taskListItem,checkBoxEventHandler){
	console.log("bind list item events");
//select ListItems children
	var editButton=taskListItem.querySelector("button.edit");
	var deleteButton=taskListItem.querySelector("button.delete");


			//Bind editWellItemEdit to edit button.
			editButton.onclick=editWellItemEdit;
			//Bind deleteWellItemEdit to delete button.
			deleteButton.onclick=deleteWellItemEdit;
			//Bind taskCompleted to checkBoxEventHandler.
}

//cycle over incompleteTaskHolderEdit ul list items
//for each list item
for (var i=0; i<completeTaskHolderEdit.children.length;i++){

	//bind events to list items chldren(tasksCompleted)
	bindTaskEventsWell(completeTaskHolderEdit.children[i],addWellItemEdit);
}