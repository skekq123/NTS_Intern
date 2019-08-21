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
// DOMContentLoaded 초기 설정
document.addEventListener('DOMContentLoaded', function () {
    // 임시로 이메일값 선언 (로그인 구현 후, 로그인 정보를 통해 이메일값을 가져올 예정)
	let email = "skekq123@naver.com";
    requestAjax(initDisplayInfo, 'api/reservations?reservationEmail=' + email);
});