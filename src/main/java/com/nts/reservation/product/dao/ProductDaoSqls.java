package com.nts.reservation.product.dao;

public final class ProductDaoSqls {
	public static final String SELECT_PRODUCT_PAGE = "SELECT display_info.id, display_info.place_name, product.content"
			+ ", product.description, product.id, file_info.save_file_name" 
			+ "FROM product "
			+ "INNER JOIN product_image ON product.id = product_image.product_id "
			+ "INNER JOIN display_info ON product.id = display_info.product_id "
			+ "INNER JOIN file_info ON product_image.file_id = file_info.id and product_image.type='th' "
			+ "LIMIT :start, :limit;";
	public static final String SELECT_PRODUCT_PAGE_BY_CATEGORY = "SELECT display_info.id, display_info.place_name"
			+ ", product.content , product.description, product.id, file_info.save_file_name"
			+ "FROM product " 
			+ "INNER JOIN product_image ON product.id = product_image.product_id "
			+ "INNER JOIN display_info ON product.id = display_info.product_id "
			+ "INNER JOIN file_info ON product_image.file_id = file_info.id and product_image.type='th'"
			+ "WHERE product.category_id = :categoryId " 
			+ "LIMIT :start, :limit;";
	public static final String SELECT_PRODUCT_COUNT =  "SELECT COUNT(display_info.id) FROM product" 
			+ "INNER JOIN product_image ON product.id = product_image.product_id and product_image.type = 'th' "
			+ "INNER JOIN display_info ON product.id = display_info.product_id";
	public static final String SELECT_PRODUCT_COUNT_BY_CATEGORY = "SELECT COUNT(display_info.id) FROM product" 
			+ "INNER JOIN product_image ON product.id = product_image.product_id and product_image.type = 'th' "
			+ "INNER JOIN display_info ON product.id = display_info.product_id"
			+ "WHERE product.category_id = :categoryId ";
}
