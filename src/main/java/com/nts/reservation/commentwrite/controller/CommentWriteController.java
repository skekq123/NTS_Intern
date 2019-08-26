package com.nts.reservation.commentwrite.controller;

import javax.servlet.http.Cookie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class CommentWriteController {
	@RequestMapping(value = "/reviewWrite", method = RequestMethod.GET)
	public String moveReservePage(@RequestParam(name = "productId", required = true) int productId,
			@CookieValue(name = "userMail", required = false) Cookie userMail) {
		return "reviewWrite";
	}
}