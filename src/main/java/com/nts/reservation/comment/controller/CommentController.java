package com.nts.reservation.comment.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentController {

	@RequestMapping(value = "/review", method = RequestMethod.GET)
	public String mapReviewPage(
			@RequestParam(name = "displayInfoId", required = false, defaultValue = "0") int displayInfoId) {
		return "review";
	}
}
