package com.nts.reservation.displayInfo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.displayInfo.dto.DisplayInfoResponse;
import com.nts.reservation.displayInfo.service.DisplayInfoService;

@RestController
@RequestMapping("/api/products")
public class DisplayInfoApiController {

	@Autowired
	private DisplayInfoService displayInfoServiceImpl;

	@RequestMapping(value = "/{displayInfoId}", method = RequestMethod.GET)
	public DisplayInfoResponse getDisplayInfo(@PathVariable("displayInfoId") int displayInfoId) {

		return displayInfoServiceImpl.getDisplayInfos(displayInfoId);
	}
}