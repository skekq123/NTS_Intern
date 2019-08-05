package com.nts.reservation.product.dao;

public final class ProductDaoSqls {
	public static final String SELECT_PRODUCT_PAGE =  "SELECT product.id AS id, display_info.id AS displayInfoId, "
			+ "display_info.place_name AS placeName, product.content AS content, product.description AS description,"
			+ "file_info.save_file_name AS imageUrl "
			+ "FROM product "
			+ "INNER JOIN product_image ON product.id = product_image.product_id "
			+ "INNER JOIN display_info ON product.id = display_info.product_id "
			+ "INNER JOIN file_info ON product_image.file_id = file_info.id and product_image.type='th' "
			+ "LIMIT :start, :limit;";
	public static final String SELECT_PRODUCT_PAGE_BY_CATEGORY = "SELECT product.id AS id, display_info.id AS displayInfoId, "
			+ "display_info.place_name AS placeName, product.content AS content, product.description AS description,"
			+ "file_info.save_file_name AS imageUrl "
			+ "FROM product " 
			+ "INNER JOIN product_image ON product.id = product_image.product_id "
			+ "INNER JOIN display_info ON product.id = display_info.product_id "
			+ "INNER JOIN file_info ON product_image.file_id = file_info.id and product_image.type='th'"
			+ "WHERE product.category_id = :categoryId " 
			+ "LIMIT :start, :limit;";
	public static final String SELECT_PRODUCT_COUNT =  "SELECT COUNT(display_info.id) FROM product " 
			+ "INNER JOIN product_image ON product.id = product_image.product_id and product_image.type = 'th' "
			+ "INNER JOIN display_info ON product.id = display_info.product_id;";
	public static final String SELECT_PRODUCT_COUNT_BY_CATEGORY = "SELECT COUNT(display_info.id) FROM product " 
			+ "INNER JOIN product_image ON product.id = product_image.product_id and product_image.type = 'th' "
			+ "INNER JOIN display_info ON product.id = display_info.product_id "
			+ "WHERE product.category_id = :categoryId;";
	public static final String SELECT_PROUDUCT_IMAGE = "SELECT file_info.content_type AS content_type, "
			+ "file_info.create_date AS create_date, file_info.delete_flag AS delete_flag, "
			+ "file_info.id AS file_info_id, file_info.file_name AS file_name, "
			+ "file_info.modify_date AS modify_date, product_image.product_id AS product_id, "
			+ "product_image.id AS product_image_id, file_info.save_file_name AS save_file_name, "
			+ "product_image.type AS type "
			+ "FROM product_image "
			+ "INNER JOIN file_info ON file_info.id = product_image.file_id "
			+ "INNER JOIN product ON product_image.product_id = product.id "
			+ "INNER JOIN display_info ON display_info.product_id = product.id "
			+ "WHERE display_info.id = :displayInfoId;";
}
