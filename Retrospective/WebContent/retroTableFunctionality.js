/*This function retrieves the name that was clicked on 
 *retroCommentsByName and stores it in a hidden variable on that page*/
function clickedName(element)
{
	let hiddenName = document.getElementById("clickedName");
	hiddenName.value = element.id;
	
    document.getElementById("nameForm").submit();
}

/*This function retrieves the comment that was clicked on 
 *retroCommentsByProject and stores it in a hidden variable on that page*/
function clickedProject(element)
{
	let hiddenProj = document.getElementById("clickedProject");
	hiddenProj.value = element.id;
	
    document.getElementById("projForm").submit();
}

