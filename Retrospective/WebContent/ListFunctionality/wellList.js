/**
 * 
 */
var wellTaskInput= document.getElementById("wellNew-task");//Add a new task.
var wellAddButton=document.getElementsByTagName("button")[1];//first button
var completeTaskHolder= document.getElementById("complete-tasks");//ul of #incomplete-tasks



//New task list item
var createNewWellItem=function(taskString){

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



var addWellItem=function(){
	console.log("Add Task...");
	//Create a new list item with the text from the #new-task:
	var listItem=createNewWellItem(wellTaskInput.value);

	//Append listItem to completeTaskHolder
	completeTaskHolder.appendChild(listItem);
	bindTaskEventsWell(listItem, addWellItem);

	wellTaskInput.value="";
	
}

//Edit an existing task.

var editWellItem=function(){
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
var deleteWellItem= function(){
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


//Set the click handler to the addWellItem function.
wellAddButton.addEventListener("click",addWellItem);
//wellAddButton.addEventListener("click",ajaxRequest);


var bindTaskEventsWell =function(taskListItem,checkBoxEventHandler){
	console.log("bind list item events");
//select ListItems children
	var editButton=taskListItem.querySelector("button.edit");
	var deleteButton=taskListItem.querySelector("button.delete");


			//Bind editWellItem to edit button.
			editButton.onclick=editWellItem;
			//Bind deleteWellItem to delete button.
			deleteButton.onclick=deleteWellItem;
			//Bind taskCompleted to checkBoxEventHandler.
}