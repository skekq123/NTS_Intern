package com.nts.reservation.reservation.service;

import com.nts.reservation.reservation.dto.ReservationInfoResponse;
import com.nts.reservation.reservation.dto.ReservationParam;

public interface ReservationService {
	boolean reserveTicket(ReservationParam reservationParam);
	
	ReservationInfoResponse getReservationInfoResponse(String reservationEmail);
	
	boolean calcelReserve(int reservationInfoId, String reservationEmail);
}	
