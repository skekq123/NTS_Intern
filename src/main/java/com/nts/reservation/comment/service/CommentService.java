package com.nts.reservation.comment.service;

import java.util.List;

import com.nts.reservation.comment.dto.Comment;

public interface CommentService {
	List<Comment> getTotalComments(int displayInfoId);
}
