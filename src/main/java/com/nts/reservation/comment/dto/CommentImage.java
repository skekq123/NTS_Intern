package com.nts.reservation.comment.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentImage {
	private String contentType;
	private String createDate;
	private boolean deleteFlag;
	private int fileId;
	private String fileName;
	private int imageId;
	private String modifyDate;
	private int reservationInfoId;
	private int reservationUserCommentId;
	private String saveFileName;
}
