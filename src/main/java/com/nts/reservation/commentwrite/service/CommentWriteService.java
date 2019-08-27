package com.nts.reservation.commentwrite.service;

import com.nts.reservation.commentwrite.dto.CommentWriteRequest;

public interface CommentWriteService {
	void writeReview(CommentWriteRequest commentWriteRequest);
}
