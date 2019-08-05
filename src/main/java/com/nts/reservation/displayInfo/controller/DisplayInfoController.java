package com.nts.reservation.displayInfo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class DisplayInfoController {

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	public String requestDetailPage(@RequestParam(name = "id", required = true) int id) {
		return "detail";
	}

}