let title = document.getElementById("title");
let description = document.getElementById("description");
let myForm = document.getElementById("myForm");

myForm.addEventListener("submit", function(e) {
	e.preventDefault();
	let titleValue = title.value.trim();
	let descriptionValue = description.value.trim();

	let isValid = true;

	if (titleValue === "") {
		title.style.border = "1px solid #FF0000";
		isValid = false;
	} else {
		title.style.border = "1px solid #ced4da";
	}

	if (descriptionValue === "") {
		description.style.border = "1px solid #FF0000";
		isValid = false;
	} else {
		description.style.border = "1px solid #ced4da";
	}

	if (isValid) {
		this.submit();
	}
});