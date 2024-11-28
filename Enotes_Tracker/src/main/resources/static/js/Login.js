document.getElementById("submitbtn").style.backgroundColor = "blue";
document.getElementById("submitbtn").style.color = "#fff";

let email = document.getElementById("email");
let password = document.getElementById("password");
let myForm = document.getElementById("myForm");

myForm.addEventListener("submit", function(e) {
	e.preventDefault();
	let emailValue = email.value;
	let passwordValue = password.value;

	let isValid = true;

	if (emailValue === "") {
		email.style.border = "1px solid #FF0000";
		isValid = false;
	}
	if (passwordValue === "") {
		password.style.border = "1px solid #FF0000";
		isValid = false;
	}
	if (isValid) {
		this.submit();
	}
});
