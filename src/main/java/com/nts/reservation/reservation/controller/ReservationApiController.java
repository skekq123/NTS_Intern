package com.nts.reservation.reservation.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.reservation.dto.ReservationParam;
import com.nts.reservation.reservation.service.ReservationService;

@RestController
public class ReservationApiController {

	private ReservationService reservationServiceImpl;

	public ReservationApiController(ReservationService reservationServiceImpl) {
		this.reservationServiceImpl = reservationServiceImpl;
	}

	@RequestMapping(value = "/api/reserve", method = RequestMethod.POST)
	public Map<String, Object> reserve(@RequestBody ReservationParam reserveRequest) {
		Map<String, Object> map = new HashMap<>();

		if (reservationServiceImpl.postReserve(reserveRequest)) {
			map.put("result", "OK");
		}
		return map;
	}
}