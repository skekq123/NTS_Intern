package com.nts.reservation.displayInfo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.service.CommentService;
import com.nts.reservation.displayInfo.dao.DisplayInfoDao;
import com.nts.reservation.displayInfo.dto.DisplayInfo;
import com.nts.reservation.displayInfo.dto.DisplayInfoImage;
import com.nts.reservation.displayInfo.dto.DisplayInfoResponse;
import com.nts.reservation.product.dao.ProductDao;
import com.nts.reservation.product.dto.ProductImage;

@Service
public class DisplayInfoServiceImpl implements DisplayInfoService {

	@Autowired
	private DisplayInfoDao displayInfoDao;
	@Autowired
	private ProductDao productDao;
	@Autowired
	private CommentService commentServiceImpl;

	@Override
	public DisplayInfoResponse getDisplayInfos(int displayInfoId) {
		DisplayInfoResponse displayInfoResponse = new DisplayInfoResponse();

		DisplayInfo selectDisplayInfo = displayInfoDao.selectDisplayInfoByDisplayInfoId(displayInfoId);
		DisplayInfoImage selectDisplayInfoImage = displayInfoDao.selectDisplayInfoImageByDisplayInfoId(displayInfoId);
		List<ProductImage> selectProductImages = productDao.selectProductImages(displayInfoId);
		List<Comment> selectComments = commentServiceImpl.getComments(displayInfoId);
		double selectAverageScore = 0;

		// 댓글이 있으면 평균 점수 가져오기
		if (!selectComments.isEmpty()) {
			selectAverageScore = displayInfoDao.selectAverageScore(displayInfoId);
		}
		displayInfoResponse.setDisplayInfoImage(selectDisplayInfoImage);
		displayInfoResponse.setProductImages(selectProductImages);
		displayInfoResponse.setDisplayInfo(selectDisplayInfo);
		displayInfoResponse.setComments(selectComments);
		displayInfoResponse.setAverageScore(selectAverageScore);
		return displayInfoResponse;
	}
}
