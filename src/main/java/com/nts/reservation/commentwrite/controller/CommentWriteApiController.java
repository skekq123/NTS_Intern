package com.nts.reservation.commentwrite.controller;

import java.util.Collections;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.commentwrite.dto.CommentWriteRequest;
import com.nts.reservation.commentwrite.service.CommentWriteService;

@RestController
@RequestMapping("/api/commentWrite")
public class CommentWriteApiController {

	private CommentWriteService commentWriteServiceImpl;

	public CommentWriteApiController(CommentWriteService commentWriteServiceImpl) {
		this.commentWriteServiceImpl = commentWriteServiceImpl;
	}

	@PostMapping()
	public Map<String, Object> registerComment(CommentWriteRequest commentWriteRequest) {

		commentWriteServiceImpl.writeReview(commentWriteRequest);

		return Collections.singletonMap("result", "OK");
	}

}