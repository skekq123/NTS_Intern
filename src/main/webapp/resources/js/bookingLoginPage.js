
function checkEmailValidate() {
	document.querySelector('.ng-pristine').addEventListener("submit", function (evt) {
		evt.preventDefault();
		
		let emailValue = document.querySelector("[name='resrv_email']").value;
		let emailValid = (/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i).test(emailValue);
		
		if(!emailValid) {
			alert("올바르지 않은 이메일 형식입니다.");
		}
		else {
			this.submit();			
		}
	});
}

document.addEventListener('DOMContentLoaded', function () {
	checkEmailValidate();
});