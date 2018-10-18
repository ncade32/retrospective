/**
 * 
 */

var improveItemInputEdit=document.getElementById("improveNew-task2");//Add a new task.
var improveAddButtonEdit=document.getElementById("addImprove");//first button
var improveTaskHolderEdit=document.getElementById("improve-tasks2");//ul of #incomplete-tasks


//New task list item
var createNewImproveItemEdit=function(taskString){

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
	editInput.name="improve";
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

var addImproveItemEdit=function(){
	console.log("Add Task...");
	//Create a new list item with the text from the #new-task:
	var listItem=createNewImproveItemEdit(improveItemInputEdit.value);

	//Append listItem to improveTaskHolderEdit
	improveTaskHolderEdit.appendChild(listItem);
	bindTaskEventsImprove(listItem, addImproveItemEdit);

	improveItemInputEdit.value="";
	

}

//Edit an existing task.

var editImproveItemEdit=function(){
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
var deleteImproveItemEdit=function(){
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


//Set the click handler to the addImproveItemEdit function.
improveAddButtonEdit.addEventListener("click",addImproveItemEdit)
//improveAddButtonEdit.addEventListener("click",ajaxRequest);


var bindTaskEventsImprove=function(taskListItem,checkBoxEventHandler){
	console.log("bind list item events");
//select ListItems children
	var editButton=taskListItem.querySelector("button.edit");
	var deleteButton=taskListItem.querySelector("button.delete");


			//Bind editImproveItemEdit to edit button.
			editButton.onclick=editImproveItemEdit;
			//Bind deleteImproveItemEdit to delete button.
			deleteButton.onclick=deleteImproveItemEdit;
			//Bind taskCompleted to checkBoxEventHandler.
}

//cycle over incompleteTaskHolder ul list items
//for each list item
for (var i=0; i<improveTaskHolderEdit.children.length;i++){

	//bind events to list items chldren(tasksCompleted)
	bindTaskEventsImprove(improveTaskHolderEdit.children[i],addImproveItemEdit);
}