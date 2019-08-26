function RatingObj(score) {
    this.score = score;
    this.starList = document.querySelectorAll('.rating_rdo');
}

RatingObj.prototype = {
	fillStar : function (){ 
	    for (let i = 0; i < 5; ++i) {
	        if (i <= this.score) {
	            this.starList[i].checked = true;
	        } else {
	            this.starList[i].checked = false;
	        }
	    }
	    let targetScore = document.querySelector('.star_rank');
	    targetScore.classList.remove('gray_star');
	    targetScore.innerText = this.score + 1;
	}
}

function initDisplay(productInfo) {
	
    let displayInfo = productInfo['displayInfo'];
    let title = displayInfo.productDescription;
    
    document.querySelector('.title').innerText = title;
}
function initRatingBox() {
    let ratingList = new Object();
    for (let i = 0; i < 5; ++i) {
        ratingList[i] = new RatingObj(i);
    }
    let targetRatingBox = document.querySelector('.rating');
    targetRatingBox.addEventListener('click', evt => {
        let target = evt.target;

        if (target.classList.contains('rating_rdo')) {
            let index = target.value - 1;
            ratingList[index].fillStar();
        }
    });
}

document.addEventListener('DOMContentLoaded', function () {
    requestAjax(initDisplay, 'api/displayInfo/' + getUrlParameter('productId'));
    
    initRatingBox();
});