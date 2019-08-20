package com.nts.reservation.reservation.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationPrice {
	private Integer count;
	private Integer productPriceId;
	private Integer reservationInfoId;
	private Integer reservationInfoPriceId;
}
