package com.nts.reservation.imagefile.dao;

public class ImageFileDaoSqls {
	public static final String SELECT_PRODUCT_PAGE =  "SELECT id AS id, file_name AS filename, "
			+ "save_file_name AS saveFileName, content_type AS contentType "
			+ "FROM file_info "
			+ "WHERE id = :imageFileId ";
}