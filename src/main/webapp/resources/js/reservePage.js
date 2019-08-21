let DateObj = {
	currentDate: "",
	lastDate: "",
	randomDate: "",

	setDate: function () {
		let date = new Date();
		this.currentDate = DateFormmater(date);
	        
		/* 타임스탬프 + 5 * 1000*60*60*24ms(=5일) */
		date.setTime(date.getTime() + 5 * 1000 * 60 * 60 * 24);
		this.lastDate = DateFormmater(date);

		/* 타임스탬프 + [0~5] * 1000*60*60*24ms(=[오늘~5]일) */
		date.setTime(date.getTime() + generateRandom(0, -5) * 1000 * 60 * 60 * 24);
		this.randomDate = date;
	}
}
function TicketObj(target, index, price) {
    this.target = target;
    this.index = index;
    this.price = price;
    
    target[0].classList.add('minusBtn' + index);
    target[1].classList.add('plusBtn' + index);
}
TicketObj.prototype = {
		addPlusClickEvent : function () {
	        let index = this.index;
	        document.querySelector('.plusBtn' + index).addEventListener('click', function () {
	            if (this.parentElement.children[1].value === "0") {
	                this.parentElement.querySelector('.ico_minus3').classList.remove('disabled');
	                this.parentElement.querySelector('.count_control_input').classList.remove('disabled');
	                this.parentElement.parentElement.querySelector('.individual_price').classList.add('on_color');
	            }
	            this.parentElement.children[1].setAttribute('value', String(Number(this.parentElement.children[1].value) + 1));

	            productPricesList[index].count = parseInt(this.parentElement.children[1].value);
	            changePriceEvent();
	            checkInputDataComplete();
	        });
	    }, 
	    addMinusClickEvent : function () {
	        let index = this.index;
	        document.querySelector('.minusBtn' + index).addEventListener('click', function () {
	            if (this.parentElement.children[1].value in ["1", "0"]) {
	                this.parentElement.querySelector('.ico_minus3').classList.add('disabled');
	                this.parentElement.querySelector('.count_control_input').classList.add('disabled');
	                this.parentElement.parentElement.querySelector('.individual_price').classList.remove('on_color');
	                this.parentElement.children[1].setAttribute('value', "0");
	            } else {
	                this.parentElement.children[1].setAttribute('value', String(Number(this.parentElement.children[1].value) - 1));
	            }
	            productPricesList[index].count = parseInt(this.parentElement.children[1].value);
	            changePriceEvent();
	            checkInputDataComplete();
	        });
	    }
}
function ReservePrices(count, productPriceId) {
    this.count = count;
    this.productPriceId = parseInt(productPriceId);
};

let productPricesList = new Array();
function addReservePrices(productPrices){

    productPrices.forEach(productPrice=>{
        productPricesList.push(new ReservePrices(0, productPrice.productPriceId));
    });
}
function initDisplayInfo(displayInfoData) {
    // 이전화면 이동
    document.querySelector('.btn_back').setAttribute('href', '/reservation/detail?id=' + getUrlParameter('id'));

    let displayProductImages = displayInfoData["productImages"];
    // ma 타입의 이미지 정보를 추가
    displayProductImages.forEach(image => {
        if (image.type === 'ma') {
            document.querySelector('li.item > img').setAttribute('src', "resources/" + image.saveFileName);
        }
    });

    let ImageTextTarget = document.querySelectorAll('.preview_txt_dsc')[1];
    ImageTextTarget.innerText = DateObj.currentDate + "~" + DateObj.lastDate + ", 잔여티켓 2769매";
    
    //기간
    let discription = document.querySelectorAll('.dsc');
    discription[0].innerHTML = "기간 : " + DateObj.currentDate + "~" + DateObj.lastDate;

    // 관람시간
    let displayInfo = displayInfoData["displayInfo"];
    discription[1].innerText = displayInfo.openingHours.replace('/-/g', '<br>- ');
    
    // 요금
    let prices = displayInfoData["productPrices"];
    let priceTarget = discription[2];
    let minPrice = 1000000;
    prices.forEach(price => {
        if (minPrice > price.price) minPrice = price.price;

        priceTarget.innerText += mapPriceType.get(price.priceTypeName);
        priceTarget.innerText += addCommaInNumber(price.price) + '원\r\n';
    });

    document.querySelector('.preview_txt_dsc').innerText = "₩ " + addCommaInNumber(minPrice) + " ~ ";
}
let mapPriceType = new Map([
    ['A', '성인'],
    ['Y', '청소년'],
    ['B', '어린이'],
    ['S', '세트'],
    ['D', '장애인'],
    ['C', '지역주민'],
    ['E', '얼리버드'],
    ['V', 'VIP'],
    ['R', 'R석'],
    ['D', '평일']
]);
let ticketItems = new Object();
function initTickectBox(productPrices) {
    let ticketTemplate = document.querySelector('#ticketItem').innerText;
    let bindticketTemplate = Handlebars.compile(ticketTemplate);
    let ticketContainer = document.querySelector('div.ticket_body');

    productPrices.forEach((price, index) => {
        price.priceTypeName = mapPriceType.get(price.priceTypeName);
        let itemPrice = price.price;
        price.price = addCommaInNumber(itemPrice);
        ticketContainer.innerHTML += bindticketTemplate(price);
        ticketItems[index] = new TicketObj(ticketContainer.lastElementChild.querySelectorAll('.btn_plus_minus'), index, itemPrice);
    });
    
    productPrices.forEach((price, index) => {
    	ticketItems[index].addMinusClickEvent();
    	ticketItems[index].addPlusClickEvent();
    });
    document.querySelector('.selected').innerText = DateFormmater(DateObj.randomDate) + ', 총 ' + 0 + '매';
}

let reserveCount = 0;
//금액 정보를 갱신해주는 Event
let changePriceEvent = function () {
    let totalCount = 0;
    reserveCount = 0;
    document.querySelectorAll('.count_control_input').forEach((ticketItem, index) => {
        let itemPrice = ticketItem.value * ticketItems[index].price;

        let ticketItemTotalPrice = ticketItem.parentElement.parentElement.children[1].children[0];
        ticketItemTotalPrice.innerText = addCommaInNumber(itemPrice);
        reserveCount += Number(ticketItem.value);
        totalCount += Number(ticketItem.value);
    });
   
    document.querySelector('.selected').innerText = DateFormmater(DateObj.randomDate) + ', 총 ' + totalCount + '매';
}
function initAgreementBtn() {
    let agreementTarget = document.querySelectorAll('.btn_agreement');

    // 약관 보기/접기 토글 설정
    agreementTarget.forEach(target => {
        target.addEventListener('click', function () {
            if (target.tagName === 'I' || target.tagName === 'SPAN') {
                target = target.parentElement;
            }

            if (target.children[0].innerText === "보기") {
                target.parentElement.classList.add('open');
                target.children[0].innerText = "접기";
            } else {
                target.parentElement.classList.remove('open');
                target.children[0].innerText = "보기";
            }


        });
    });
}
function checkInputDataComplete() {
    let name = document.querySelector('input.text').value;
    let tel = document.querySelector('input.tel').value;
    let email = document.querySelector('input.email').value;
    let agreement = document.querySelector('#chk3').checked;
    let bookingBtn = document.querySelector('.bk_btn_wrap');
    
    // name, tel, email, agreement가 모두 check되고, 하나라도 골랐을 경우 bookingBtn 활성화
    if (name != '' && tel != '' && email != '' && agreement && reserveCount > 0) {
        bookingBtn.classList.remove('disable');
    } else {
        bookingBtn.classList.add('disable');
    }
    // 중간 - 삽입 
    document.querySelector('input.tel').value = tel.replace( /(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/, "$1-$2-$3" );
}
function initInputDataForm() {
    let inputForm = document.querySelector('.form_horizontal');

    inputForm.addEventListener('change', function (evt) {
    let changedTarget = evt.target;
    if (changedTarget.tagName === 'INPUT') {
        InputDataValidator(changedTarget);
    }
	});
    
    let agreementForm = document.querySelector('#chk3');

    agreementForm.addEventListener('click', function () {
        checkInputDataComplete();
    })
    changePriceEvent();
}
function InputDataValidator(changedTarget) {
    let inputType = changedTarget.name;
    let inputArea = changedTarget;
    let warningArea = inputArea.nextElementSibling;

    let isCorrectInput;
    switch (inputType) {
        case 'name':
            isCorrectInput = inputArea.value.length > 0;
            break;
        case 'tel':
            let tel = inputArea.value;
            tel = tel.replace(/\-/g, '');
            let telReg = (/(^02.{0}|^01.{1}|[0-9]{3})([0-9]+)([0-9]{4})/g);
            isCorrectInput = telReg.test(tel);
            break;
        case 'email':
            let email = inputArea.value;

            let emailReg = (/^[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_.]?[0-9a-zA-Z])*.[a-zA-Z]{2,3}$/i);
            isCorrectInput = emailReg.test(email);
            break;
        default:
            isCorrectInput = false;
    }

    if (!isCorrectInput) {
        inputArea.value = '';
        warningArea.style.visibility = 'visible';
        setTimeout(function () {
            warningArea.style.visibility = 'hidden';
        }, 1500);
        checkInputDataComplete();
    } else {
        checkInputDataComplete();
    }
}
function ReserveRequest(displayInfoId, prices, productId, reservationName, reservationTelephone, reservationEmail) {
    this.displayInfoId = parseInt(displayInfoId);
    this.prices = prices;
    this.productId = parseInt(productId);
    this.reservationName = reservationName;
    this.reservationTelephone = reservationTelephone;
    this.reservationEmail = reservationEmail;
    this.reservationYearMonthDay = DateObj.randomDate.toMysqlFormat();
}
function initBookingBtn(displayInfoData) {
    let bookingBtn = document.querySelector('.bk_btn_wrap');

    document.querySelector('.bk_btn').addEventListener('click', function () {
        if(!bookingBtn.classList.contains('disable')){
            let displayInfo = displayInfoData['displayInfo'];

            let displayInfoId = displayInfo.displayInfoId;
            let reservePrices = productPricesList;
            let productId = displayInfo.productId;
            let name = document.querySelector('input.text').value;
            let tel = document.querySelector('input.tel').value;
            let email = document.querySelector('input.email').value;

            let reserveRequest = JSON.stringify(new ReserveRequest(displayInfoId, reservePrices, productId, name, tel, email));

            requestPostAjax('api/reserve', reserveRequest);
            alert("예약되었습니다!");
            location.href = "/reservation";
        }
    });
}
function loadDisplayInfoCallback(displayInfoData) {
    // 화면 상단 Display 설정
    initDisplayInfo(displayInfoData);
    
    let productPrices = displayInfoData["productPrices"];
    // 화면 중단 TicketBox 설정
    initTickectBox(productPrices);
    
    // ReservePrices List 추가
    addReservePrices(productPrices);
    
    //BookingBtn 설정
    initBookingBtn(displayInfoData);
}
// DOMContentLoaded 초기 설정
document.addEventListener('DOMContentLoaded', function () {
    // 현재시간 설정
    DateObj.setDate();
    
    // 약관 button 설정
    initAgreementBtn();
    
    //데이터 form 설정
    initInputDataForm(); 
    requestAjax(loadDisplayInfoCallback, 'api/displayInfo/' + getUrlParameter('id'));
});