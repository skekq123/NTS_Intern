package com.nts.reservation.promotion.dao;

public class PromotionDaoSqls {
	public static final String SELECT_PROMOTION_PAGE = "SELECT promotion.id AS id, promotion.product_id AS productId"
			+ ", file_info.save_file_name AS productImageUrl, file_info.id AS image_file_id "
			+ "FROM promotion "
			+ "INNER JOIN product ON promotion.product_id = product.id " 
			+ "INNER JOIN product_image ON promotion.product_id = product_image.product_id "
			+ "INNER JOIN file_info ON product_image.file_id = file_info.id "
			+ "WHERE product_image.type = 'th'; ";
}