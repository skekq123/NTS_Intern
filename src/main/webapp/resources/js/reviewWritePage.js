function initDisplay(productInfo) {
	
    let displayInfo = productInfo['displayInfo'];
    let title = displayInfo.productDescription;
    
    document.querySelector('.title').innerText = title;
}

document.addEventListener('DOMContentLoaded', function () {
    requestAjax(initDisplay, 'api/displayInfo/' + getUrlParameter('productId'));
});