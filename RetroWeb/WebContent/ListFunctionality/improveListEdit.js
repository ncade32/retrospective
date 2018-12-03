/**
 * 
 */

let improveItemInputEdit=document.getElementById("improveNew-task2");//Add a new task.
let improveAddButtonEdit=document.getElementById("addImprove");//first button
let improveTaskHolderEdit=document.getElementById("improve-tasks2");//ul of #incomplete-tasks


//New task list item
let createNewImproveItemEdit=function(taskString){


	var listItem=document.createElement("li");
	
	//textarea
	var editInput = document.createElement("TEXTAREA");
	//button.edit
	var editButton=document.createElement("button");//edit button

	//button.delete
	var deleteButton=document.createElement("button");//delete button


	//Each elements, needs appending
	listItem.className = "li-tasks-group";
	editInput.type="text";
	editInput.name="improve";
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

let addImproveItemEdit=function(){
	console.log("Add Task...");
	//Create a new list item with the text from the #new-task:
	if(improveItemInputEdit.value != ""){
		var listItem=createNewImproveItemEdit(improveItemInputEdit.value);
		
		//Append listItem to completeTaskHolderEdit
		improveTaskHolderEdit.appendChild(listItem);
		bindTaskEventsImprove(listItem, addImproveItemEdit);
	
		improveItemInputEdit.value="";
	}
	
}

//Edit an existing task.

let editImproveItemEdit=function(){
	console.log("Edit Task...");
	console.log("Change 'edit' to 'save'");

	var listItem=this.parentNode;

	var editButton=listItem.querySelector("button.edit");
	var editInput = listItem.querySelector('textarea');

	editButton.innerText = "Save";
	editInput.readOnly = false;
	editInput.style.backgroundColor = "gray";

	var containsClass=listItem.classList.contains("editMode");
			//If class of the parent is .editmode
			if(containsClass){

			//switch to .editmode
			//label becomes the inputs value.
				editButton.innerText = "Edit";
				editInput.readOnly = true;
				editInput.style.backgroundColor = "#3b4652";
			}
			//toggle .editmode on the parent.
			listItem.classList.toggle("editMode");
	}




//Delete task.
let deleteImproveItemEdit=function(){
		console.log("Delete Task...");

		var listItem=this.parentNode;
		var ul=listItem.parentNode;
		//Remove the parent list item from the ul.
		ul.removeChild(listItem);

}

/*let ajaxRequest=function(){
	console.log("AJAX Request");
}*/

//The glue to hold it all together.


//Set the click handler to the addImproveItemEdit function.
improveAddButtonEdit.addEventListener("click",addImproveItemEdit)
//improveAddButtonEdit.addEventListener("click",ajaxRequest);


let bindTaskEventsImprove=function(taskListItem,checkBoxEventHandler){
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