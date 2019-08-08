package com.nts.reservation.comment.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Comment {
	private String comment;
	private int commentId;
	private List<CommentImage> commentImages;
	private String reservationDate;
	private String reservationEmail;
	private String reservationName;
	private String reservationTelephone;
	private double score;
}
