package com.nts.reservation.reservation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReservationLoginController {
	@RequestMapping(value = "/bookinglogin", method = RequestMethod.GET)
	public String moveLoginPage() {
		return "bookinglogin";
	}
}
