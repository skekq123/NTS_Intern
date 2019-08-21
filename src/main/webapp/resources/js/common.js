// Url의 name에 해당하는 Parameter 추출
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
//Rest API로 서버로부터 해당 url의 json데이터를 가져옴 (GET)
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
//Rest API로  url을 서버로 json 데이터 넘김 (POST)
function requestPostAjax(url, param) {
	let ajaxReq = new XMLHttpRequest();
	
	ajaxReq.open('POST', url);
	ajaxReq.setRequestHeader('Content-Type', 'application/json; charset=UTF-8');
	ajaxReq.responseType = 'json';
	ajaxReq.send(param);
}
//입력된 숫자에 3자리수마다 ,를 추가
function addCommaInNumber(number) {
    return number.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
}

//date형식을 2019.08.20.(화)과 같은 형식의 String 타입으로 변환
let DateFormmater = function (date) {
  let week = new Array('일', '월', '화', '수', '목', '금', '토');
  return date.getFullYear() + "." + (date.getMonth() + 1) + "." + date.getDate() + ".(" + week[date.getDay()] + ")";
}
//입력된 min ~ max에서 랜덤 값을 뽑아내 줌
let generateRandom = function (min, max) {
  return Math.floor(Math.random() * (max - min + 1)) + min;
}
//date 형식 sql 타입으로 변환
Date.prototype.toMysqlFormat = function() {
	return this.getUTCFullYear() + "-" + twoDigits(1 + this.getUTCMonth()) + "-" + twoDigits(this.getUTCDate()) 
	+ " " + twoDigits(this.getUTCHours()) + ":" + twoDigits(this.getUTCMinutes()) + ":" + twoDigits(this.getUTCSeconds());
}
function twoDigits(d) {
	let result;
    if(0 <= d && d < 10) result =  "0" + d.toString();
    else result = d.toString();
    
    return result;
}