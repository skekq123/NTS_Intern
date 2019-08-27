package com.nts.reservation.commentwrite.dao;

public class CommentWriteSqls {
	public static final String INSERT_COMMENT = "INSERT INTO reservation_user_comment( "
			+ "reservation_info_id, product_id, comment, score, create_date, modify_date) "
			+ "VALUES( :reservationInfoId, :productId, :comment, :score, "
			+ "NOW(), NOW());";
	
	public static final String INSERT_FILE_INFO = "INSERT INTO file_info(file_name, save_file_name, "
			+ "content_type, delete_flag, create_date, modify_date) "
			+ "VALUES( :fileName, :saveFileName, :contentType, 0, NOW(), NOW());";

	public static final String INSERT_COMMENT_IMAGE = "INSERT  \r\n INTO reservation_user_comment_image(  \r\n"
			+ "reservation_info_id,  \r\n reservation_user_comment_id,  \r\n file_id)  \r\n"
			+ "VALUES(  \r\n :reservationInfoId,  \r\n :reservationUserCommentId,  \r\n :fileId);";
}
