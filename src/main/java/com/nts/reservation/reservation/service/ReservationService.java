package com.nts.reservation.reservation.service;

import com.nts.reservation.reservation.dto.ReservationInfoResponse;
import com.nts.reservation.reservation.dto.ReservationParam;

public interface ReservationService {
	boolean postReserve(ReservationParam reservationParam);
	
	ReservationInfoResponse getReservationInfoResponse(String reservationEmail);
	
	boolean updateReserve(int reservationInfoId, String reservationEmail);
}	
