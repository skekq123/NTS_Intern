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
		
		public static final String INSERT_RESERVE_PRICE = "INSERT INTO reservation_info_price( "
			+ "reservation_info_id, product_price_id, count) "
			+ "VALUES( :reservationInfoId, :productPriceId, :count);";
		
		public static final String SELECT_RESERVATION_SIZE = "SELECT COUNT(*) "
			+ "FROM reservation_info "
			+ "WHERE reservation_info.reservation_email = :reservationEmail;";
		
		public static final String SELECT_RESERVATION_INFO = "SELECT reservation_info.cancel_flag AS cancel_yn, "
			+ "reservation_info.create_date AS create_date, reservation_info.display_info_id AS display_info_id, "
			+ "reservation_info.modify_date AS modify_date, reservation_info.product_id AS product_id, "
			+ "reservation_info.reservation_date AS reservation_date, reservation_info.reservation_email AS reservation_email, "
			+ "reservation_info.id AS reservation_info_id, reservation_info.reservation_name AS reservation_name, "
			+ "reservation_info.reservation_tel AS reservation_telephone "
			+ "FROM reservation_info "
			+ "WHERE reservation_info.reservation_email = :reservationEmail;";
		
		public static final String SELECT_TOTAL_PRICE = "SELECT SUM(reservation_info_price.count * product_price.price) AS a "
			+ "FROM reservation_info_price "
			+ "INNER JOIN reservation_info ON reservation_info.id = reservation_info_price.reservation_info_id "
			+ "INNER JOIN product_price ON product_price.id = reservation_info_price.product_price_id "
			+ "WHERE reservation_info.reservation_email = :reservationEmail "
			+ "AND reservation_info.product_id = :productId "
			+ "AND reservation_info.id = :reservationInfoId;";
}
