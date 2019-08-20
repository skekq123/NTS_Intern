package com.nts.reservation.reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.reservation.dao.ReservationDao;
import com.nts.reservation.reservation.dto.ReservationParam;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private ReservationDao reservationDao;
	@Override
	public boolean postReserve(ReservationParam reservationParam) {
		int reservationInfoId = reservationDao.insertReservation(reservationParam);

		if (reservationInfoId == 0) {
			return false;
		}
		return true;
	}
}
