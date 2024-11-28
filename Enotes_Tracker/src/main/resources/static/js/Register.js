document.getElementById("submitbtn").style.backgroundColor = "blue";
document.getElementById("submitbtn").style.color = "#fff";

let fullname = document.getElementById("fullname");
let mobile = document.getElementById("mobile");
let email = document.getElementById("email");
let gender = document.getElementById("gender");
let password = document.getElementById("password");
let qualification = document.getElementById("qualification");
let form = document.getElementById("myForm");

form.addEventListener("submit", function(e) {
	e.preventDefault();
	let nameValue = fullname.value;
	let mobileValue = mobile.value;
	let emailValue = email.value;
	let passwordValue = password.value;
	let qualificationValue = qualification.value;
	let genderValue = gender.value;

	let isValid = true;

	fullname.style.border = "1px solid #6EC207";
	mobile.style.border = "1px solid #6EC207";
	email.style.border = "1px solid #6EC207";
	gender.style.border = "1px solid #6EC207";
	password.style.border = "1px solid #6EC207";
	qualification.style.border = "1px solid #6EC207";

	if (nameValue === "") {
		fullname.style.border = "1px solid #FF0000";
		isValid = false;
	}
	if (mobileValue === "") {
		mobile.style.border = "1px solid #FF0000";
		isValid = false;
	}
	if (emailValue === "") {
		email.style.border = "1px solid #FF0000";
		isValid = false;
	}
	if (genderValue === "") {
		gender.style.border = "1px solid #FF0000";
		isValid = false;
	}
	if (passwordValue === "") {
		password.style.border = "1px solid #FF0000";
		isValid = false;
	}
	if (qualificationValue === "") {
		qualification.style.border = "1px solid #FF0000";
		isValid = false;
	}
	if (isValid) {
		this.submit();
	}
});