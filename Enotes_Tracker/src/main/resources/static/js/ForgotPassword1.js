document.getElementById("submitbtn").style.backgroundColor = "blue";
document.getElementById("submitbtn").style.color = "#fff";

let password = document.getElementById("password");
let cpassword = document.getElementById("cpassword");
let myForm = document.getElementById("myForm");

myForm.addEventListener("submit", function(e) {
	e.preventDefault();
	let passwordValue = password.value;
	let cpasswordValue = cpassword.value;

	let isValid = true;

	if (passwordValue === "") {
		password.style.border = "1px solid #FF0000";
		isValid = false;
	}
	if (cpasswordValue === "") {
		cpassword.style.border = "1px solid #FF0000";
		isValid = false;
	}
	if (isValid) {
		this.submit();
	}
});