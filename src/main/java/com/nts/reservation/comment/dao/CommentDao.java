package com.nts.reservation.comment.dao;

import java.util.Collections;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.comment.dto.CommentImage;

@Repository
public class CommentDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<CommentImage> rowMapperCommentImage = BeanPropertyRowMapper.newInstance(CommentImage.class);
	private RowMapper<Comment> rowMapperComment = BeanPropertyRowMapper.newInstance(Comment.class);

	public CommentDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Comment> selectComments(int displayInfoId) {
		return jdbc.query(CommentSqls.SELECT_COMMENT, Collections.singletonMap("displayInfoId", displayInfoId),
				rowMapperComment);
	}

	public List<CommentImage> selectCommentImages(int commentId) {
		return jdbc.query(CommentSqls.SELECT_COMMENT_IMAGES, Collections.singletonMap("commentId", commentId),
				rowMapperCommentImage);
	}
	
	public List<CommentImage> selectCommentImage(int commentId) {
		return jdbc.query(CommentSqls.SELECT_COMMENT_IMAGE, Collections.singletonMap("commentId", commentId),
				rowMapperCommentImage);
	}
}
