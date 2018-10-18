/**
 * 
 */
var improveTaskInput=document.getElementById("improveNew-task");//Add a new task.
var improveAddButton=document.getElementsByTagName("button")[2];//first button
var improveTaskHolder=document.getElementById("improve-tasks");//ul of #incomplete-tasks


//New task list item
var createNewImproveItem=function(taskString){

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

var addImproveItem=function(){
	console.log("Add Task...");
	//Create a new list item with the text from the #new-task:
	var listItem=createNewImproveItem(improveTaskInput.value);

	//Append listItem to improveTaskHolder
	improveTaskHolder.appendChild(listItem);
	bindTaskEventsImprove(listItem, addImproveItem);

	improveTaskInput.value="";
	

}

//Edit an existing task.

var editImproveItem=function(){
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
var deleteImproveItem=function(){
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


//Set the click handler to the addImproveItem function.
improveAddButton.addEventListener("click",addImproveItem)
//improveAddButton.addEventListener("click",ajaxRequest);


var bindTaskEventsImprove =function(taskListItem,checkBoxEventHandler){
	console.log("bind list item events");
//select ListItems children
	var editButton=taskListItem.querySelector("button.edit");
	var deleteButton=taskListItem.querySelector("button.delete");


			//Bind editImproveItem to edit button.
			editButton.onclick=editImproveItem;
			//Bind deleteImproveItem to delete button.
			deleteButton.onclick=deleteImproveItem;
			//Bind taskCompleted to checkBoxEventHandler.
}