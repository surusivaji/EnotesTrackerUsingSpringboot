document.getElementById("submitbtn").style.backgroundColor = "blue";
document.getElementById("submitbtn").style.color = "#fff";

let email = document.getElementById("email");
let mobile = document.getElementById("mobile");
let myForm = document.getElementById("myForm");

myForm.addEventListener("submit", function(e) {
	e.preventDefault();
	let emailValue = email.value;
	let mobileValue = mobile.value;

	let isValid = true;

	if (emailValue === "") {
		email.style.border = "1px solid #FF0000";
		isValid = false;
	}
	if (mobileValue === "") {
		mobile.style.border = "1px solid #FF0000";
		isValid = false;
	}
	if (isValid) {
		this.submit();
	}
});