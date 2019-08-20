package com.nts.reservation.reservation.service;

import com.nts.reservation.reservation.dto.ReservationParam;

public interface ReservationService {
	boolean postReserve(ReservationParam reservationParam);
}	
