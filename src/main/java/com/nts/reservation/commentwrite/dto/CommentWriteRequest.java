package com.nts.reservation.commentwrite.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentWriteRequest {
	private int reservationUserCommentId;
	private String comment;
	private int score;
	private int productId;
	private MultipartFile imageFile;
	private int reservationInfoId;
	private int fileInfoId;
	private String fileName;
	private String saveFileName;
	private String contentType;
}
