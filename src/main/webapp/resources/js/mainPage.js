let currentStart = 0;
function loadProductsCallback(responseData) {
    currentStart += 4;
    let totalCount = responseData.totalCount;
    let items = responseData.items;
    let template = document.querySelector('#itemList').innerHTML;
    let containers = document.querySelectorAll('.lst_event_box');
    for (let i = 0, len = items.length; i < len; ++i) {
        containers[i % 2].innerHTML += template
                                                .replace('{imageUrl}', items[i].imageUrl)
                                                .replace(/{description}/g, items[i].description)
                                                .replace('{id}', items[i].id)
                                                .replace('{placeName}', items[i].placeName)
                                                .replace('{content}', items[i].content);
    }
    let moreViewbtn = document.querySelector('.btn');
    
    if (currentStart >= totalCount) { 
        moreViewbtn.style.display = 'none'; //비활성화
    } else {
        moreViewbtn.style.display = 'initial';
    }
    document.querySelector('.pink').innerText = totalCount + '개';
}

let currentCategory = 0;
const currentProductUnits = 4;

function setTabButton() {
	document.querySelector('.tab_lst_min').addEventListener('click', btnEvent => {
		let selectedTab = event.target;
    	if (selectedTab.tagName === 'SPAN') {
        	selectedTab = selectedTab.parentElement;
        }
        if (selectedTab.tagName === 'A') {
        	let categoryId = selectedTab.parentElement.getAttribute('data-category');
            if (categoryId != currentCategory) {
            	currentCategory = categoryId;
            	currentStart = 0;
            	document.querySelectorAll('a.anchor').forEach(element => element.classList.remove('active'));
            	selectedTab.classList.add('active');
            	document.querySelectorAll('.lst_event_box').forEach(element => element.innerHTML = '');
            	requestAjax(loadProductsCallback, mapProductParameters(currentCategory, currentStart, currentProductUnits));
            }
        }
	});

}

function requestAjax(callback, url) {
	let Req = new XMLHttpRequest();
	Req.callback = callback;
	Req.addEventListener('load', evt => {
		callback(evt.target.response)
	});
	Req.open('GET', '/api' + url);
	Req.responseType = 'json';
	Req.send();
}
function mapProductParameters(categoryId, start) {
	return 'products?categoryId=' + categoryId + '&start=' + start;
}

document.addEventListener('DOMContentLoaded', function () {
	requestAjax(loadProductsCallback, mapProductParameters(0, 0));
	setTabButton();
	
	document.querySelector('.btn').addEventListener('click', () => {
		requestAjax(loadProductsCallback, mapProductParameters(currentCategory, currentStart, currentProductUnits))
	});
});