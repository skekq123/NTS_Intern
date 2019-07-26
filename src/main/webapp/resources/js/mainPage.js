var currentStart = 0;
function loadProductsCallback(responseData) {
    currentStart += 4;
    alert(1);
    //var totalCount = responseData.totalCount;
    alert(2);
    var items = responseData.items;
    alert(3);
    var template = document.querySelector('#itemList').innerHTML;
    alert(4);
    var containers = document.querySelectorAll('.lst_event_box');
    for (var i = 0, len = items.length; i < len; ++i) {
    	alert(10);
        containers[i % 2].innerHTML += template
                                                .replace('{productImageUrl}', items[i].imageUrl)
                                                .replace(/{description}/g, items[i].description)
                                                .replace('{id}', items[i].id)
                                                .replace('{placeName}', items[i].placeName)
                                                .replace('{content}', items[i].content);
    }
    alert(2);
    var moreViewbtn = document.querySelector('.btn');
    
    if (currentStart >= totalCount) {
    	alert(3);
        moreViewbtn.style.display = 'none';
    } else {
    	alert(4);
        moreViewbtn.style.display = 'initial';
    }
    alert(5);
    document.querySelector('.pink').innerText = totalCount + '개';
}
var currentCategory = 0;
const currentProductUnits = 4;
function setTabButton() {
	document.querySelector('.tab_lst_min').addEventListener('click', btnEvent => {
    	var selectedTab = event.target;
    	if (selectedTab.tagName === 'SPAN') {
        	selectedTab = selectedTab.parentElement;
        }
        if (selectedTab.tagName === 'A') {
            var categoryId = selectedTab.parentElement.getAttribute('data-category');
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
	var Req = new XMLHttpRequest();
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