package com.nts.reservation.commentwrite.dao;

public class CommentWriteSqls {
	public static final String INSERT_COMMENT = "INSERT INTO reservation_user_comment( "
			+ "reservation_info_id, product_id, comment, score, create_date, modify_date) "
			+ "VALUES( :reservationInfoId, :productId, :comment, :score, "
			+ "NOW(), NOW());";
}
