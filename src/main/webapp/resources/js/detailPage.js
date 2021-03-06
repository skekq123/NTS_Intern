document.addEventListener('DOMContentLoaded', function () {
    requestAjax(loadDisplayInfoCallback, 'api/displayInfo/' + getUrlParameter('id'));
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
function loadDisplayInfoCallback(responseData) {
    let displayInfoResponse = responseData;
    let displayInfo = displayInfoResponse["displayInfo"];
    let displayProductImages = displayInfoResponse["productImages"];

    
    let isAddtionalDisplayImage = false;
    let TitleDisplayImage = "";
// ma 타입의 이미지 정보를 displayInfo에 추가
// et 타입의 이미지가 있다면 한장을 additionalDsiplayInfo에 추가
 
    let TitleSlideImageCount = 2;
    let displayProductImageCount = displayProductImages.length;
    if(displayProductImageCount >= TitleSlideImageCount) isAddtionalDisplayImage = true;
    
    displayInfo.imageFileId = displayProductImages[0].imageFileId;
    TitleDisplayImage = displayProductImages[0].imageFileId;
   
   
// TitleImage 설정
    initTitleImage(displayInfo);
    initDetailBtn();
    
    if(isAddtionalDisplayImage) { // 추가적 사진이 있으면
        let addtionalDisplayInfo = displayInfoResponse["displayInfo"];
        addtionalDisplayInfo.imageFileId = displayProductImages[1].imageFileId;
        TitleSlide(addtionalDisplayInfo, TitleDisplayImage); 
    }
    
    // productDescription 정보를 displayCommentInfo에 추가
    let displayCommentInfo = displayInfoResponse["comments"];
    displayCommentInfo.forEach(comment => {
        comment.productDescription = displayInfo.productDescription;
    });

    // averageScore 정보를 displayCommentInfo에 추가
    displayCommentInfo.averageScore = displayInfoResponse.averageScore;
    initComment(displayCommentInfo, displayCommentInfo.length);
    // Comment 더보기 버튼 설정
    initMoreCommentBtn(displayInfo.displayInfoId);
    // 상세정보, 오시는길
    initDetailPathTab(displayInfoResponse);
    
    // 예매 버튼
    let reserveBtn = document.querySelector('.bk_btn');
    reserveBtn.addEventListener("click", () => {
    	location.href = "/reservation/reserve?id=" + getUrlParameter('id');
    });
    
}
function TitleSlide(addtionalDisplayInfo, TitleDisplayImage) {
    let titleTemplate = document.querySelector('#bannerImage').innerText;
    let bindTitleTemplate = Handlebars.compile(titleTemplate);
    let titleContainer = document.querySelector('ul.detail_swipe');
    
	titleContainer.innerHTML += bindTitleTemplate(addtionalDisplayInfo);
    document.querySelector('div.store_details>p.dsc').innerHTML = addtionalDisplayInfo.productContent;
   
    let currentTitle = 2;
    let titleImageList = document.querySelector('.visual_img');
 
	let firstChild = titleImageList.firstElementChild;
	let lastChild = titleImageList.lastElementChild;
	titleImageList.insertAdjacentHTML('afterbegin', lastChild.cloneNode(true).outerHTML);
	titleImageList.insertAdjacentHTML('beforeend', firstChild.cloneNode(true).outerHTML);
	
    let promotionLength = 4;
    let leftDistance = -100;
    let btnNext = document.querySelector('a.btn_nxt');
    let btnPrev = document.querySelector('a.btn_prev');
    
    // next, prev 버튼 활성화
    document.querySelector('div.prev').style.display = '';
    document.querySelector('div.nxt').style.display = '';
    
    document.querySelector('div.figure_pagination').firstElementChild.classList.remove('off');
    document.querySelector('div.figure_pagination').lastElementChild.innerText = '/ 2';

    
    btnNext.addEventListener('click', nextArrowEventHandler);
    btnPrev.addEventListener('click', prevArrowEventHandler);
    
    titleImageList.style.left = leftDistance + '%';
    titleImageList.style.transitionDuration = '1s';
    
    titleImageList.addEventListener("transitionend", function() {
    	if (currentTitle >= promotionLength) {
    		currentTitle = 2;
    		leftDistance = -100;
    		titleImageList.style.transitionDuration = '0s';
    		titleImageList.style.left = leftDistance + '%';
    	} else if (currentTitle <= 1) {
    		currentTitle = 3;
    		leftDistance = -200;
    		titleImageList.style.transitionDuration = '0s';
    		titleImageList.style.left = leftDistance + '%';
    	}
    	let pagination;
    	if(currentTitle == 1 || currentTitle == 3) pagination = 2;
    	else if(currentTitle == 2 || currentTitle == 4) pagination = 1;
    	document.querySelector('div.figure_pagination').firstElementChild.innerText = pagination;
    	btnNext.addEventListener('click', nextArrowEventHandler);
        btnPrev.addEventListener('click', prevArrowEventHandler);
	})
    
    function nextArrowEventHandler() {
    	btnNext.removeEventListener('click', nextArrowEventHandler);
    	btnPrev.removeEventListener('click', prevArrowEventHandler);
    	
    	titleImageList.style.transitionDuration = '1s';
    	++currentTitle;
    	leftDistance -= 100;
    	titleImageList.style.left = leftDistance + '%';
    }
    
    function prevArrowEventHandler() {
    	btnNext.removeEventListener('click', nextArrowEventHandler);
    	btnPrev.removeEventListener('click', prevArrowEventHandler);
    	
    	titleImageList.style.transitionDuration = '1s';
    	--currentTitle;
   		leftDistance += 100;
   		titleImageList.style.left = leftDistance + '%';
    }
}

const commentViewSize = 3;
function initComment(displayCommentInfo, totalComments) {
    let commentTemplate = document.querySelector('#commentItem').innerText;
    let bindCommentTemplate = Handlebars.compile(commentTemplate);
    let commentContainer = document.querySelector('ul.list_short_review');

    if(totalComments <= commentViewSize){
        displayCommentInfo.forEach(comment => {
            if(comment.commentImages.length != 0) {
                comment.imageFileId = comment.commentImages[0].imageFileId;
            }

            commentContainer.innerHTML += bindCommentTemplate(comment);
        });
    } else {
        for(let i = 0; i < commentViewSize; ++i) {
            if(displayCommentInfo[i].commentImages.length != 0) {
                displayCommentInfo[i].imageFileId = displayCommentInfo[i].commentImages[0].imageFileId;
            }
            commentContainer.innerHTML += bindCommentTemplate(displayCommentInfo[i]);
        }
    }

    // 총 댓글 갯수
    let commentCount = totalComments;
	document.querySelector('span.join_count>em.green').innerText = commentCount+'건';
	
	// 평균 점수
	let averageScore = displayCommentInfo.averageScore;
	
	document.querySelector('em.graph_value').style.width = (averageScore*20) + '%';
	document.querySelector('.text_value>span').innerText = averageScore;
	
	// 댓글 더보기 버튼
	let reviewMoreBtn = document.querySelector('a.btn_review_more');
	if(totalComments > 3){
		reviewMoreBtn.setAttribute('href','/');
	} else{
		reviewMoreBtn.style.display = 'none';
	}
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
//
function initDetailPathTab(displayInfoResponse) {
	let displayInfo = displayInfoResponse["displayInfo"];
	let displayInfoImage = displayInfoResponse["displayInfoImage"];
	
	// 소개 - 글
	document.querySelector('p.in_dsc').innerText = displayInfo.productContent;
	
	// 오시는 길 - 이미지
	document.querySelector('.store_map').setAttribute('src', "file/img/"+displayInfoImage.imageFileId);
	
	// 오시는 길 - 장소명
	document.querySelector('.store_name').innetText = displayInfo.placeName;
	
	// 오시는길 - 주소
	let address = document.querySelector('.store_addr_wrap').querySelectorAll('p');
	address[0].innerText = displayInfo.placeStreet;
	address[1].querySelectorAll('span')[1].innerText = displayInfo.placeLot;
	address[2].innerText = displayInfo.placeName;
	
	// 오시는 길 - 전화번호
	let telephone = document.querySelector('.store_tel');
	telephone.setAttribute('href', displayInfo.telephone);
	telephone.innerText = displayInfo.telephone;
	
	// 상세 ~ 오시는길 전환하는 탭
	let detailTab = document.querySelector('ul.info_tab_lst>._detail');
	let detailBody = document.querySelector('.detail_area_wrap');
	
	let pathTab = document.querySelector('ul.info_tab_lst>._path');
	let pathBody = document.querySelector('.detail_location');
	
	let currentTab = 1; // 1 : detail, 2 : path
	document.querySelector('ul.info_tab_lst').addEventListener('click', evt=>{
		let clickedTab = evt.target;
		if(clickedTab.tagName === 'SPAN') {
			clickedTab = clickedTab.parentElement.parentElement;
		}
		else if(clickedTab.tageName === 'A') {
			clickedTab = clickedTab.parentElement;
		}
		
		
		
		if(currentTab == 2 && clickedTab.className.indexOf('_detail') != -1){ // 첫번째
																				// 탭 클릭
			currentTab = 1;
			pathTab.classList.remove('active');
			pathTab.firstElementChild.classList.remove('active');
			
			pathBody.classList.add('hide');
			detailBody.classList.remove('hide');
			
			detailTab.classList.add('active');
			detailTab.firstElementChild.classList.add('active');
			
		}else if(currentTab == 1 && clickedTab.className.indexOf('_path') != -1){ // 두번째
																					// 탭 클릭
			currentTab = 2;
			detailTab.classList.remove('active');
			detailTab.firstElementChild.classList.remove('active');
			
			detailBody.classList.add('hide');
			pathBody.classList.remove('hide');
			
			pathTab.classList.add('active');
			pathTab.firstElementChild.classList.add('active');
		}

	})
}
// Comment 더보기 버튼 설정
function initMoreCommentBtn(displayInfoId) {
	 document.querySelector('.btn_review_more').setAttribute('href','review?id=' + displayInfoId);
}