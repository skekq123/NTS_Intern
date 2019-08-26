function initDisplayInfo(response) {
    let reservationData = response["reservations"];
    let titles = document.querySelectorAll('.figure');
    let currentDate = (new Date()).toMysqlFormat();
    
    // 0 : 전체, 1 : 이용예정, 2 : 이용완료, 3 : 취소 및 환불
    let size = response.size;
    let todo = 0;
    let done = 0;
    let cancel = 0;
    reservationData.forEach(data => {
        if (data.cancelYn) {
            let cancelTemplate = document.querySelector('#cancelReservation').innerText;
            let bindcancelTemplate = Handlebars.compile(cancelTemplate);
            let cancelContainer = document.querySelector('li.cancel');
            
            addExtraData(data);
            cancelContainer.innerHTML += bindcancelTemplate(data);

            cancel++;
            return true;
        }

        if (data.reservationDate > currentDate) {
            let todoTemplate = document.querySelector('#todoReservation').innerText;
            let bindTodoTemplate = Handlebars.compile(todoTemplate);
            let todoContainer = document.querySelector('li.confirmed');

            addExtraData(data);
            todoContainer.innerHTML += bindTodoTemplate(data);

            todo++;
        } else {
            let doneTemplate = document.querySelector('#doneReservation').innerText;
            let bindDoneTemplate = Handlebars.compile(doneTemplate);
            let doneContainer = document.querySelector('li.used');

            addExtraData(data);
            doneContainer.innerHTML += bindDoneTemplate(data);

            done++;
        }
    });

    if (todo === 0) {
        document.querySelector('.confirmed').innerHTML += document.querySelector('#noneList').innerHTML;
    }
    if (done === 0) {
        document.querySelector('.used').innerHTML += document.querySelector('#noneList').innerHTML;
    }
    if (cancel === 0) {
        document.querySelector('.cancel').innerHTML += document.querySelector('#noneList').innerHTML;
    }


    titles[0].innerText = size;
    titles[1].innerText = todo;
    titles[2].innerText = done;
    titles[3].innerText = cancel;
}
function ReserveParam(reservationInfoId, reservationEmail) {
    this.reservationInfoId = parseInt(reservationInfoId);
    this.reservationEmail = reservationEmail;
}
// Handlebar 처리를 위한 데이터 처리
function addExtraData(data) {
    data.categoryName = data['displayInfo'].categoryName;
    data.placeStreet = data['displayInfo'].placeStreet;
    data.productDescription = data['displayInfo'].productDescription;
    data.totalPrice = addCommaInNumber(data.totalPrice);
    data.reservationDate = DateFormmater(new Date(Date.parse(data.reservationDate.replace('-', '/', 'g'))));
}
function initCancelBtn() {
	let popupTarget = document.querySelector('.popup_booking_wrapper');
	
	let id;
	let title;
	let date;
	let email;
	document.querySelector('li.confirmed').addEventListener('click', function (evt) {
		let target = evt.target;
		
		if(target.tagName === 'span') target = target.parentElement;
		if(target.className === 'btn') {
			popupTarget.style.display = 'block';
			id = target.parentElement.parentElement.parentElement.querySelector('.reserveId').innerText;
			title = target.parentElement.parentElement.parentElement.querySelector('.tit').innerText;
			date = target.parentElement.parentElement.parentElement.querySelector('.item_dsc').innerText;
			email = getCookieEmail();
		
			document.querySelector('.pop_tit').children[0].innerText = title;
			document.querySelector('.pop_tit').children[1].innerText = date;
		}
	});
	
	document.querySelector('.btn_green').addEventListener('click', function() {
		let reserveRequest = JSON.stringify(new ReserveParam(id, email));
		requestPostAjax('api/update', reserveRequest);
		location.href = '/reservation/myreservation';
		popupTarget.style.display = 'none';
	})
	
	document.querySelector('.popup_btn_close').addEventListener('click', function() {
		popupTarget.style.display = 'none';
	});
	document.querySelector('.btn_gray').addEventListener('click', function() {
		popupTarget.style.display = 'none';
	});
}
function getCookieEmail() {
	let cookieValue = document.cookie;
	let email = "";
	for(var index = 9; index < cookieValue.length; ++index) {
		email += cookieValue[index];
	}
	return email;
}

// DOMContentLoaded 초기 설정
document.addEventListener('DOMContentLoaded', function () {
	let email = getCookieEmail();
	
    requestAjax(initDisplayInfo, 'api/reservations?reservationEmail=' + email);
    
    initCancelBtn();
});