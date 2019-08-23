package com.nts.reservation.reservation.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReservationLoginController {
	@RequestMapping(value = "/bookinglogin", method = RequestMethod.GET)
	public String moveLoginPage() {
		return "bookinglogin";
	}
	
	private static final int COOKIE_TIME = 60;

	@PostMapping(path = "/bookinglogin")
	public String loginUser(@RequestParam("resrv_email") String reservationEmail,
			@CookieValue(name = "userMail", required = false) Cookie userMail, HttpServletResponse response) {
		if (userMail == null) {
			userMail = new Cookie("userMail", reservationEmail);
		} else {
			userMail.setValue(reservationEmail);
		}

		userMail.setMaxAge(COOKIE_TIME);

		response.addCookie(userMail);
		
		return "/myreservation";
	}
	@RequestMapping(value = "/myreservation", method = RequestMethod.GET)
	public String moveReservationPage() {
		return "myreservation";
	}
	

}
