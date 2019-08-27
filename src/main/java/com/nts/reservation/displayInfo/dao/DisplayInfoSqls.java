package com.nts.reservation.displayInfo.dao;

public class DisplayInfoSqls {
	public static final String SELECT_DISPLAY_INFO_IMAGE = "SELECT file_info.content_type, file_info.create_date, "
			+ "file_info.delete_flag, display_info.id, display_info_image.id, file_info.id as image_file_id, "
			+ "file_info.file_name, file_info.modify_date, file_info.save_file_name "
			+ "FROM display_info_image "
			+ "INNER JOIN file_info ON display_info_image.file_id = file_info.id "
			+ "INNER JOIN display_info ON display_info.id = display_info_image.display_info_id "
			+ "WHERE display_info.id = :displayInfoId;";
	public static final String SELECT_DISPLAY_INFO = "SELECT category.id AS category_id, "
			+ "category.name AS category_name, display_info.create_date AS create_date, "
			+ "display_info.id AS display_info_id, display_info.email AS email, "
			+ "display_info.homepage AS homepage, display_info.modify_date AS modify_date, "
			+ "display_info.opening_hours AS opening_hours, display_info.place_lot AS place_lot, "
			+ "display_info.place_name AS place_name, display_info.place_street AS place_street, "
			+ "product.content AS product_content, product.description AS product_description, "
			+ "product.event AS product_event, product.id AS product_id, "
			+ "display_info.tel AS telephone "
			+ "FROM display_info "
			+ "INNER JOIN product ON product.id = display_info.product_id "
			+ "INNER JOIN category ON category.id = product.category_id "
			+ "WHERE display_info.id = :displayInfoId;";
	
	public static final String SELECT_AVERAGE_SCORE = "SELECT ROUND(AVG(reservation_user_comment.score), 1) "
			+ "AS average_score "
			+ "FROM reservation_user_comment "
	        + "INNER JOIN reservation_info ON reservation_info.id = reservation_user_comment.reservation_info_id "
	        + "WHERE reservation_info.display_info_id = :displayInfoId;";
}
