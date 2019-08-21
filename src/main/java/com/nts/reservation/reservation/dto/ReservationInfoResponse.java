package com.nts.reservation.reservation.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationInfoResponse {
	private List<ReservationInfo> reservations;
	private int size;
}