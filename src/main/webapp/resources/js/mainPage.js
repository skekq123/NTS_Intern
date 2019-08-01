let currentStart = 0;
function loadProductsCallback(responseData) {
    currentStart += 4;
    let totalCount = responseData.totalCount;
    let items = responseData.items;
    let template = document.querySelector('#itemList').innerHTML;
    let containers = document.querySelectorAll('.lst_event_box');
    
    items.forEach((items,index) => {
    	containers[index%2].innerHTML += template
        .replace('{imageUrl}', items.imageUrl)
        .replace(/{description}/g, items.description)
        .replace('{id}', items.id)
        .replace('{placeName}', items.placeName)
        .replace('{content}', items.content);
    });
    
    let moreViewBtn = document.querySelector('.see_more_btn');
    
    if (currentStart >= totalCount) { 
        moreViewBtn.style.display = 'none'; // 비활성화
    } else {
        moreViewBtn.style.display = 'initial';
    }
    document.querySelector('.pink').innerText = totalCount + '개';
}

let currentCategory = 0;

function setTabButton() {
	document.querySelector('.tab_lst_min').addEventListener('click', btnEvent => {
		let selectedTab = event.target;
    	if (selectedTab.tagName === 'SPAN') {
        	selectedTab = selectedTab.parentElement;
        }
        let categoryId = selectedTab.parentElement.getAttribute('data-category');
        if (categoryId != currentCategory) {
            currentCategory = categoryId;
            currentStart = 0;
            document.querySelectorAll('a.anchor').forEach(element => element.classList.remove('active'));
            selectedTab.classList.add('active');
            document.querySelectorAll('.lst_event_box').forEach(element => element.innerHTML = '');
            requestAjax(loadProductsCallback, makeProductApiUrl(currentCategory, currentStart));
            }
	});

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
function makeProductApiUrl(categoryId, start) {
	return 'api/product?categoryId=' + categoryId + '&start=' + start;
}

document.addEventListener('DOMContentLoaded', function () {
	requestAjax(loadProductsCallback, makeProductApiUrl(0, 0));
	setTabButton();
	
	document.querySelector('.see_more_btn').addEventListener('click', () => {
		requestAjax(loadProductsCallback, makeProductApiUrl(currentCategory, currentStart))
	});
	requestAjax(loadPromotionsCallback, 'api/promotion');
});

function loadPromotionsCallback(responseData) {
	let items = responseData;
	let promotionLength = items.length;
	let promotionList = document.querySelector(".visual_img");
	let template = document.querySelector('#promotionItem').innerHTML;
	
	items.forEach((items) => {
		promotionList.insertAdjacentHTML('beforeend', template
		    	.replace(/{productImageUrl}/g, items.productImageUrl));
    });
	promotionList.insertAdjacentHTML('beforeend', template
	    	.replace(/{productImageUrl}/g, items[0].productImageUrl));
	
    promotionSlideAnimation(promotionList, promotionLength);
}
function promotionSlideAnimation(promotionList, promotionLength) {
	let leftDistance = 0;
	let currentImgIndex = 0;

	promotionList.addEventListener("transitionend", function() {
		leftDistance += 414;
		++currentImgIndex;
		if(currentImgIndex === (promotionLength + 1)) {
			promotionList.style.transition = null;
			promotionList.style.transform = "translateX(0)";
		}
	});
	
	promotionList.style.transition = "0.5s";
	
	function slide() {
		setTimeout(() => {
			if(currentImgIndex === (promotionLength + 1)) {
				currentImgIndex = 1;
				leftDistance = 414;
				promotionList.style.transition = "0.5s";
			}
			
			promotionList.style.transform = `translateX(-${leftDistance}px)`;
			requestAnimationFrame(slide);
		}, 1000);
	}
	requestAnimationFrame(slide);
}