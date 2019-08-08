package com.nts.reservation.comment.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.service.CommentService;

@RestController
@RequestMapping("/api/comments")
public class CommentApiController {

	@Autowired
	private CommentService commentServiceImpl;

	@RequestMapping(method = RequestMethod.GET)
	public List<Comment> getProduct(@RequestParam(name = "id", required = false, defaultValue = "0") int displayInfoId) {
		return commentServiceImpl.getComments(displayInfoId);
	}
}
