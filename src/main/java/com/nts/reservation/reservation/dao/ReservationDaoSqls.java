package com.nts.reservation.reservation.dao;

public class ReservationDaoSqls {
		public static final String INSERT_RESERVE = "INSERT INTO reservation_info("
			+ "product_id, display_info_id, reservation_name, "
			+ "reservation_tel, reservation_email, reservation_date, "
			+ "cancel_flag, create_date, modify_date) "
			+ "SELECT p.id, d.id, :name, :telephone, :email, :reservationDate, 0, NOW(), NOW() "
			+ "FROM display_info AS d "
			+ "INNER JOIN product AS p ON d.product_id = p.id "
			+ "WHERE d.id = :displayInfoId "
			+ "GROUP BY p.id;";
}
