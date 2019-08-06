package com.nts.reservation.comment.dao;

public class CommentSqls {
	public static final String SELECT_COMMENT_IMAGES = "SELECT file_info.content_type AS content_type, "
			+ "file_info.create_date AS create_date, file_info.delete_flag AS delete_flag, "
			+ "file_info.id AS file_id, file_info.file_name AS file_name, "
			+ "reservation_user_comment_image.id AS image_id, file_info.modify_date AS modify_date, "
			+ "reservation_user_comment_image.reservation_info_id AS reservation_info_id, "
			+ "reservation_user_comment_image.reservation_user_comment_id AS reservation_user_comment_id, "
			+ "file_info.save_file_name AS save_file_name "
			+ "FROM display_info "
			+ "INNER JOIN display_info_image ON display_info_image.display_info_id = display_info.id "
			+ "INNER JOIN file_info ON file_info.id = display_info_image.file_id "
			+ "INNER JOIN reservation_user_comment_image ON reservation_user_comment_image.file_id = file_info.id "
			+ "WHERE reservation_user_comment_image.reservation_user_comment_id = :commentId;";

	public static final String SELECT_COMMENT = "SELECT reservation_user_comment.comment AS comment, "
			+ "reservation_user_comment.id AS comment_id, reservation_user_comment.create_date AS create_date, "
			+ "reservation_user_comment.modify_date AS modify_date, reservation_user_comment.product_id AS product_id, "
			+ "DATE_FORMAT(reservation_info.reservation_date, '%Y.%m.%d') AS reservation_date, "
			+ "reservation_info.reservation_email AS reservation_email, reservation_info.id AS reservation_info_id, "
			+ "reservation_info.reservation_name AS reservation_name, reservation_info.reservation_tel AS reservation_telephone, "
			+ "reservation_user_comment.score AS score "
			+ "FROM reservation_user_comment "
			+ "INNER JOIN reservation_info ON reservation_info.id = reservation_user_comment.reservation_info_id "
			+ "WHERE reservation_info.display_info_id = :displayInfoId "
			+ "ORDER BY comment_id DESC;";
}
