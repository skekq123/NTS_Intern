package com.nts.reservation.displayInfo.dao;

public class DisplayInfoSqls {
	public static final String SELECT_DISPLAY_INFO_IMAGE = "SELECT file_info.content_type, file_info.create_date, "
			+ "file_info.delete_flag, display_info.id, display_info_image.id, file_info.id, "
			+ "file_info.file_name, file_info.modify_date, file_info.save_file_name "
			+ "FROM display_info_image "
			+ "INNER JOIN file_info ON display_info_image.file_id = file_info.id "
			+ "INNER JOIN display_info ON display_info.id = display_info_image.display_info_id "
			+ "WHERE display_info.id = :displayInfoId;";
}
