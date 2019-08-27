package com.nts.reservation.commentwrite.dao;

import javax.sql.DataSource;

import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.nts.reservation.commentwrite.dto.CommentWriteRequest;

@Repository
public class CommentWriteDao {
	private NamedParameterJdbcTemplate jdbc;

	public CommentWriteDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public int insertComment(CommentWriteRequest commentWriteRequest) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("productId", commentWriteRequest.getProductId());
		params.addValue("reservationInfoId", commentWriteRequest.getReservationInfoId());
		params.addValue("comment", commentWriteRequest.getComment());
		params.addValue("score", commentWriteRequest.getScore());
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(CommentWriteSqls.INSERT_COMMENT, params, keyHolder, new String[] {"ID"});
		return keyHolder.getKey().intValue();
	}
}