
function clickedName(element)
{
	let hiddenName = document.getElementById("clickedName");
	hiddenName.value = element.id;
	
    document.getElementById("nameForm").submit();
}

function clickedProject(element)
{
	let hiddenProj = document.getElementById("clickedProject");
	hiddenProj.value = element.id;
	
    document.getElementById("projForm").submit();
}

