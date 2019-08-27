package com.nts.reservation.commentwrite.service;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.nts.reservation.commentwrite.dao.CommentWriteDao;
import com.nts.reservation.commentwrite.dto.CommentWriteRequest;

@Service
public class CommentWriteServiceImpl implements CommentWriteService {
	private static final String ROOT_DIR_COMMNET_IMAGE = "c:/tmp/img_comment/";
	private static final SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("MMddHHmmss");
	
	@Autowired
	private CommentWriteDao commentWriteDao;

	@Override
	@Transactional
	public void writeReview(CommentWriteRequest commentWriteRequest) {
		int reservationUserCommentId = commentWriteDao.insertComment(commentWriteRequest);
		commentWriteRequest.setReservationUserCommentId(reservationUserCommentId);
		
		MultipartFile imageFile = commentWriteRequest.getImageFile();
		if(imageFile != null) {
			String fileName = DATE_FORMATTER.format(new Date()) + imageFile.getOriginalFilename();
			String fileDir = ROOT_DIR_COMMNET_IMAGE + fileName;
			
				//MultipartFile 정보를 String으로 저장
				commentWriteRequest.setFileName(fileName);
				commentWriteRequest.setSaveFileName("img_comment/" + fileName);
				commentWriteRequest.setContentType(imageFile.getContentType());

				int fileInfoId = commentWriteDao.insertFileInfo(commentWriteRequest);
				commentWriteRequest.setImageFileId(fileInfoId);

				commentWriteDao.insertCommentImage(commentWriteRequest);
		}
	}
}
