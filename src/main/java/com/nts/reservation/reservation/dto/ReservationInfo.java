package com.nts.reservation.reservation.dto;

import com.nts.reservation.displayInfo.dto.DisplayInfo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationInfo {
	private boolean cancelYn;
	private String createDate;
	private DisplayInfo displayInfo;
	private Integer displayInfoId;
	private String modifyDate;
	private Integer productId;
	private String reservationDate;
	private String reservationEmail;
	private Integer reservationInfoId;
	private String reservationName;
	private String reservationTelephone;
	private Integer totalPrice;
}
