package com.nts.reservation.commentwrite.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.commentwrite.dao.CommentWriteDao;
import com.nts.reservation.commentwrite.dto.CommentWriteRequest;

@Service
public class CommentWriteServiceImpl implements CommentWriteService {

	@Autowired
	private CommentWriteDao commentWriteDao;

	@Override
	public void writeReview(CommentWriteRequest commentWriteRequest) {
		commentWriteDao.insertComment(commentWriteRequest);
	}
}
