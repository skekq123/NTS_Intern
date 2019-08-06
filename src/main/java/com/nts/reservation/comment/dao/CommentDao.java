package com.nts.reservation.comment.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

	public List<Comment> selectComment(int displayInfoId) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.query(CommentSqls.SELECT_COMMENT, params, rowMapperComment);
	}

	public List<CommentImage> selectCommentImages(int commentId) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("commentId", commentId);
		return jdbc.query(CommentSqls.SELECT_COMMENT_IMAGES, params, rowMapperCommentImage);
	}

}
