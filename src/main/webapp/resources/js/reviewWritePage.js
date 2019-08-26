const MAX_COMMENT_LENGTH = 400;

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
    initSubmitDisplay();
}

function initCommentFocus() {
	let targetComment = document.querySelector('.review_write_info');
	let targetText = document.querySelector('.review_textarea');
	targetComment.addEventListener('click', () => {
		targetComment.style.visibility = 'hidden';
        targetText.focus();
    });

	let targetTextLength = document.querySelector('.guide_review');
    targetText.addEventListener('keyup', e => {
        targetTextLength.firstElementChild.innerText = targetText.value.length;
        if (targetText.value.length > 400) {
            alert('글자수는 ' + MAX_COMMENT_LENGTH + '개를 넘길 수 없습니다');
        }
        initSubmitDisplay();
    });

    targetText.addEventListener('blur', () => {
        if (targetText.value.length == 0) {
            targetComment.style.visibility = 'visible';
        }
    });
}
function initImageBox() {
	let targetImageInput = document.querySelector("#reviewImageFileOpenInput");
	let targetImageBox;
	let targetImageOutput;
	targetImageInput.addEventListener('change', evt => {
        const image = evt.target.files[0];
        targetImageBox = document.querySelector('.item');
        targetImageOutput = document.querySelector('.item_thumb');
        
        targetImageBox.style.display = "block";
        targetImageOutput.src = window.URL.createObjectURL(image);
       
        targetImageBox.style.width = targetImageOutput.width + 'px';
  
    })

    let targetImageCancelBtn = document.querySelector('.spr_book');
    targetImageCancelBtn.addEventListener('click', () => {
    	targetImageInput.value = '';
        targetImageBox.style.display = "none";
        initSubmitDisplay();
    })
}
function checkWriteForm() {
	let targetScore = document.querySelector('.star_rank');
	let targetText = document.querySelector('.review_textarea');
    if (targetScore.innerText != 0 && targetText.value.length >= 5) {
        return true;
    }
    return false;
}
function initSubmitDisplay() {
	let targetSubmitBtn = document.querySelector('.bk_btn');
    
	if (checkWriteForm()) {
		targetSubmitBtn.style.display = 'block';
    } else {
    	targetSubmitBtn.style.display = 'none'
    }
}
function initSubmitBtn() {
	let targetSubmitBtn = document.querySelector('.bk_btn');
	let targetText = document.querySelector('.review_textarea');
	let targetImageInput = document.querySelector("#reviewImageFileOpenInput");
	let targetScore = document.querySelector('.star_rank');
    targetSubmitBtn.addEventListener('click', function (evt) {

        let writeForm = new FormData();
        writeForm.append('comment', targetText.value.substr(0, MAX_COMMENT_LENGTH));
        writeForm.append('score', parseInt(targetScore.innerText));
        writeForm.append('productId', getUrlParameter('productId'));
        writeForm.append('reservationInfoId', getUrlParameter('reservationInfoId'));

        if (targetImageInput.files.length > 0) {
            writeForm.append('imageFile', targetImageInput.files[0]);
        }

        requestPostAjax('/api/commentWrite', writeForm);
        alert("리뷰가 등록되었습니다.");
		location.href = "/reservation/myreservation";
    });
}
document.addEventListener('DOMContentLoaded', function () {
    requestAjax(initDisplay, 'api/displayInfo/' + getUrlParameter('productId'));
    
    initRatingBox();
    
    initCommentFocus();
    
    initImageBox();
    
    initSubmitBtn();
});