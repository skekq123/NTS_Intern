document.addEventListener('DOMContentLoaded', function () {
    requestAjax(loadDisplayInfoCallback, 'api/products/' + getUrlParameter('id'));
});

function initTitleImage(displayInfo) {
    let titleTemplate = document.querySelector('#bannerImage').innerText;
    let bindTitleTemplate = Handlebars.compile(titleTemplate);
    let titleContainer = document.querySelector('ul.detail_swipe');

    titleContainer.innerHTML += bindTitleTemplate(displayInfo);
	
    document.querySelector('div.store_details>p.dsc').innerHTML = displayInfo.productContent;
    
    // next, prev 버튼 비활성화
    document.querySelector('div.prev').style.display = 'none';
    document.querySelector('div.nxt').style.display = 'none';

    document.querySelector('div.figure_pagination').firstElementChild.classList.add('off');
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
function requestAjax(callback, url) {
	let Req = new XMLHttpRequest();
	Req.callback = callback;
	Req.addEventListener('load', evt => {
		callback(evt.target.response)
	});
	Req.open('GET', url);
	Req.responseType = 'json';
	Req.send();
}

function loadDisplayInfoCallback(responseData) {
    let displayInfoResponse = responseData;
    let displayInfo = displayInfoResponse["displayInfo"];
    let displayProductImages = displayInfoResponse["productImages"];

    let isAddtionalDisplayImage = false;
    let TitleDisplayImage = "";
//     ma 타입의 이미지 정보를 displayInfo에 추가
//     ta 타입의 이미지가 있다면 한장을 additionalDsiplayInfo에 추가
    displayProductImages.forEach(image => {
        if(image.type === 'ma') {
            displayInfo.saveFileName = image.saveFileName;
            TitleDisplayImage = image.saveFileName;
        }
        else if(image.type === 'et') {
            isAddtionalDisplayImage = true;
        }
    });
//	 TitleImage 설정
    initTitleImage(displayInfo);
    initDetailBtn();
    
    if(isAddtionalDisplayImage) { //추가적 사진이 있으면 
        let addtionalDisplayInfo = displayInfoResponse["displayInfo"];
        
        displayProductImages.forEach(image => {
        if(image.type === 'et') addtionalDisplayInfo.saveFileName = image.saveFileName;
        });
        
        TitleSlide(addtionDisplayInfo, TitleDisplayImage);
    }
}
function TitleSlide(addtionalDisplayInfo, TitleDisplayImage) {
	//TODO : 슬라이드 부분 작성
}
function initDetailBtn() {
	let openBtn = document.querySelector('a._open');	
	let closeBtn = document.querySelector('a._close');
	let openText = document.querySelector('div.store_details');
	let textArea = document.querySelector('p.dsc');

    // text가 기준치보다 많으면 DetailBtn 활성화
	if(textArea.scrollHeight > textArea.clientHeight){
		document.querySelector('div.section_store_details').addEventListener('click', evt => {
			let clickedTag  = evt.target;
			
			if(clickedTag.tagName == 'SPAN' || clickedTag.tagName == 'I'){
				clickedTag = clickedTag.parentElement;
			} 
			
			if(clickedTag.className === 'bk_more _open'){
				
				openText.classList.remove('close3');
				
				openBtn.style.display = 'none';
				closeBtn.style.display = '';
				
			}else if(clickedTag.className === 'bk_more _close'){
				
				openText.classList.add('close3');
				
				openBtn.style.display = '';
				closeBtn.style.display = 'none';
				
			}
		});
	} else {
		openBtn.style.display = 'none';
	}
}
