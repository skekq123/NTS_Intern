package com.nts.reservation.comment.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.comment.dao.CommentDao;
import com.nts.reservation.comment.dto.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	private CommentDao commentDao;

	@Override
	public List<Comment> getComments(int displayInfoId) {
		List<Comment> comment = commentDao.selectComments(displayInfoId);
		comment.forEach(commentItem -> {
			commentItem.setCommentImages(commentDao.selectCommentImages(commentItem.getCommentId()));
		});
		return comment;
	}
	
	@Override
	public List<Comment> getComment(int displayInfoId) {
		List<Comment> comment = commentDao.selectComments(displayInfoId);
		comment.forEach(commentItem -> {
			commentItem.setCommentImages(commentDao.selectCommentImage(commentItem.getCommentId()));
		});
		return comment;
	}
}