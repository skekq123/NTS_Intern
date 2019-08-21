function loadDisplayInfoCallback(displayInfoData) {
    let averageScore = displayInfoData.averageScore;
    productDescription = displayInfoData['displayInfo'].productDescription;

    // 별점 설정
    document.querySelector('div.grade_area > .text_value').firstElementChild.innerText = averageScore;
    document.querySelector('em.graph_value').style.width = (averageScore * 20) + '%';

    let displayInfoId = displayInfoData['displayInfo'].displayInfoId;
    document.querySelector('.btn_back').setAttribute('href','detail?id=' + displayInfoId);

    // Comment 설정
    requestAjax(loadCommentInfoCallback, 'comments?id=' + getUrlParameter('id'));  
}

function loadCommentInfoCallback(commentsData) {
    
    // 총 댓글 갯수
    let commentCount = commentsData.length;
    document.querySelector('span.join_count>em.green').innerText = commentCount+'건';
    
    commentsData.forEach((comment) => {
        // commentIamge가 있을 경우 saveFileName 추가
        if(comment.commentImages.length != 0) {
            comment.saveFileName = comment['commentImages'][0].saveFileName;
        }
        
        comment.productDescription = productDescription;

        let commentTemplate = document.querySelector('#commentList').innerText;
        let bindTitleTemplate = Handlebars.compile(commentTemplate);
        let commentContainer = document.querySelector('ul.list_short_review');

        commentContainer.innerHTML += bindTitleTemplate(comment);
    });
    
}

// DOMContentLoaded 초기 설정
document.addEventListener('DOMContentLoaded', function () {
    // DisplayInfo관련 설정 (averageScore, productDescription)
    requestAjax(loadDisplayInfoCallback, 'displayInfo/' + getUrlParameter('id'));
});