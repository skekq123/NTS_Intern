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
//date형식을 2019.08.20.(화)과 같은 형식의 String 타입으로 변환
let DateFormmater = function (date) {
    let week = new Array('일', '월', '화', '수', '목', '금', '토');
    return date.getFullYear() + "." + (date.getMonth() + 1) + "." + date.getDate() + ".(" + week[date.getDay()] + ")";
}
//  입력된 min ~ max에서 랜덤 값을 뽑아내 줌
let generateRandom = function (min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
}

// Rest API로 서버로부터 해당 url의 json데이터를 가져옴 (GET)
function requestAjax(callback, url) {
    let ajaxReq = new XMLHttpRequest();
    ajaxReq.callback = callback;
    ajaxReq.addEventListener('load', evt => {
        callback(evt.target.response)
    });
    ajaxReq.open('GET', url);
    ajaxReq.responseType = 'json';
    ajaxReq.send();
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
}
//Url의 name에 해당하는 Parameter 추출
function getUrlParameter(name) {
    let params = location.href.split('?')[1].split('&');
    for (let i = 0; i < params.length; i++) {
        let paramSplited = params[i].split('=');
        let paramName = paramSplited[0];
        let paramValue = paramSplited[1];

        if (paramName === name) {
            return paramValue;
        }
    }
}
function loadDisplayInfoCallback(displayInfoData) {
    // 화면 상단 Display 설정
    initDisplayInfo(displayInfoData);
}
// DOMContentLoaded 초기 설정
document.addEventListener('DOMContentLoaded', function () {
    // 현재시간 설정
    DateObj.setDate();
    requestAjax(loadDisplayInfoCallback, 'api/displayInfo/' + getUrlParameter('id'));
});