package com.nts.reservation.reservation.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nts.reservation.displayInfo.dao.DisplayInfoDao;
import com.nts.reservation.displayInfo.dto.DisplayInfo;
import com.nts.reservation.reservation.dao.ReservationDao;
import com.nts.reservation.reservation.dto.ReservationInfo;
import com.nts.reservation.reservation.dto.ReservationInfoResponse;
import com.nts.reservation.reservation.dto.ReservationParam;

@Service
public class ReservationServiceImpl implements ReservationService {
	@Autowired
	private ReservationDao reservationDao;

	@Autowired
	private DisplayInfoDao displayInfoDao;

	@Override
	@Transactional
	public boolean reserveTicket(ReservationParam reservationParam) {
		int reservationInfoId = reservationDao.insertReservation(reservationParam);

		if (reservationInfoId == 0) {
			return false;
		}
		reservationParam.getPrices().forEach(price -> {
			if (price.getCount() != 0)
				reservationDao.insertReservationPrice(price.getProductPriceId(), reservationInfoId, price.getCount());
		});
		return true;
	}

	@Override
	public ReservationInfoResponse getReservationInfoResponse(String reservationEmail) {
		List<ReservationInfo> reservations = reservationDao.getReservationInfos(reservationEmail);

		for (ReservationInfo reservation : reservations) {
			int displayInfoId = reservation.getDisplayInfoId();
			int reservationInfoId = reservation.getReservationInfoId();
			int productId = reservation.getProductId();

			DisplayInfo displayInfo = displayInfoDao.selectDisplayInfo(displayInfoId);
			int reservationTotalPrice = reservationDao.getTotalPrice(reservationEmail, productId, reservationInfoId);

			reservation.setDisplayInfo(displayInfo);
			reservation.setTotalPrice(reservationTotalPrice);
		}

		int reservationSize = reservationDao.getReservationSize(reservationEmail);

		ReservationInfoResponse reservationInfoResponse = new ReservationInfoResponse();
		reservationInfoResponse.setReservations(reservations);
		reservationInfoResponse.setSize(reservationSize);

		return reservationInfoResponse;
	}

	@Override
	public boolean cancelReserve(int reservationInfoId, String reservationEmail) {
		return reservationDao.updateReservationCancelFlag(reservationInfoId, reservationEmail) > 0;
	}
}
