package com.nts.reservation.reservation.controller;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.reservation.dto.ReservationInfoResponse;
import com.nts.reservation.reservation.dto.ReservationParam;
import com.nts.reservation.reservation.dto.ReservationUpdateParam;
import com.nts.reservation.reservation.service.ReservationService;

@RestController
public class ReservationApiController {

	private ReservationService reservationServiceImpl;

	public ReservationApiController(ReservationService reservationServiceImpl) {
		this.reservationServiceImpl = reservationServiceImpl;
	}

	@RequestMapping(value = "/api/reserve", method = RequestMethod.POST)
	public boolean reserve(@RequestBody ReservationParam reserveRequest) {
		return reservationServiceImpl.reserveTicket(reserveRequest);
	}

	@RequestMapping(value = "/api/reservations", method = RequestMethod.GET)
	public ReservationInfoResponse displayInfos(
			@RequestParam(name = "reservationEmail", required = true) String reservationEmail) {

		return reservationServiceImpl.getReservationInfoResponse(reservationEmail);
	}

	@RequestMapping(value = "/api/update", method = RequestMethod.POST)
	public boolean update(@RequestBody ReservationUpdateParam updateParam) {
		return reservationServiceImpl.cancelReserve(updateParam.getReservationInfoId(), updateParam.getReservationEmail());
		
	}
}