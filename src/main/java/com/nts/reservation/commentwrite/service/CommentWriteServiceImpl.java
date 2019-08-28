package com.nts.reservation.commentwrite.service;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.commentwrite.dao.CommentWriteDao;
import com.nts.reservation.commentwrite.dto.CommentWriteRequest;

@Service
public class CommentWriteServiceImpl implements CommentWriteService {
	private static final String BASE_PATH = File.separator + "tmp" + File.separator;
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("MMddHHmmss");

	@Autowired
	private CommentWriteDao commentWriteDao;

	@Override
	@Transactional
	public void writeReview(CommentWriteRequest commentWriteRequest) {
		int reservationUserCommentId = commentWriteDao.insertComment(commentWriteRequest);
		commentWriteRequest.setReservationUserCommentId(reservationUserCommentId);

		MultipartFile imageFile = commentWriteRequest.getImageFile();
		if (imageFile != null) {
			String fileName = DATE_FORMATTER.format(new Date()) + imageFile.getOriginalFilename();

			// MultipartFile 정보를 String으로 저장
			commentWriteRequest.setFileName(fileName);
			commentWriteRequest.setSaveFileName("img_comment/" + fileName);
			commentWriteRequest.setContentType(imageFile.getContentType());

			int fileInfoId = commentWriteDao.insertFileInfo(commentWriteRequest);
			commentWriteRequest.setImageFileId(fileInfoId);
			MultipartFile commentImage = commentWriteRequest.getImageFile();
			
			if (commentImage != null) {
				try {
					saveImageFile(commentImage);
					commentWriteRequest.setImageFile(imageFile);
					commentWriteDao.insertCommentImage(commentWriteRequest);
				} catch (Exception e) {
					throw new RuntimeException("첨부 파일을 저장하는데 실패했습니다.");
				}
			}
			commentWriteDao.insertCommentImage(commentWriteRequest);
		}
	}
	public String saveImageFile(MultipartFile image) throws IllegalStateException, IOException {
		String imageFilePath = null;

		if (!image.getOriginalFilename().isEmpty()) {
			imageFilePath = "img_comment" + File.separator + DATE_FORMATTER.format(new Date()) + image.getOriginalFilename();
			image.transferTo(new File(BASE_PATH + imageFilePath));
		}
		return imageFilePath;
	}

}
