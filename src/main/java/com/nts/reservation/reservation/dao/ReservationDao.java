package com.nts.reservation.reservation.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.nts.reservation.reservation.dto.ReservationInfo;
import com.nts.reservation.reservation.dto.ReservationParam;

@Repository
public class ReservationDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ReservationInfo> rowMapperReservationInfo = BeanPropertyRowMapper
			.newInstance(ReservationInfo.class);

	public ReservationDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public int insertReservation(ReservationParam reservationParam) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("name", reservationParam.getReservationName());
		params.addValue("telephone", reservationParam.getReservationTelephone());
		params.addValue("email", reservationParam.getReservationEmail());
		params.addValue("displayInfoId", reservationParam.getDisplayInfoId());
		params.addValue("reservationDate", reservationParam.getReservationYearMonthDay());

		KeyHolder keyHolder = new GeneratedKeyHolder();
		jdbc.update(ReservationDaoSqls.INSERT_RESERVE, params, keyHolder, new String[] { "ID" });
		return keyHolder.getKey().intValue();
	}

	public int insertReservationPrice(int productPriceId, int reservationInfoId, int count) {
		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("productPriceId", productPriceId);
		params.addValue("reservationInfoId", reservationInfoId);
		params.addValue("count", count);
		return jdbc.update(ReservationDaoSqls.INSERT_RESERVE_PRICE, params);
	}

	public int getReservationSize(String reservationEmail) {
		return jdbc.queryForObject(ReservationDaoSqls.SELECT_RESERVATION_SIZE,
				Collections.singletonMap("reservationEmail", reservationEmail), Integer.class);
	}

	public List<ReservationInfo> getReservationInfos(String reservationEmail) {
		return jdbc.query(ReservationDaoSqls.SELECT_RESERVATION_INFO,
				Collections.singletonMap("reservationEmail", reservationEmail), rowMapperReservationInfo);
	}

	public int getTotalPrice(String reservationEmail, int productId, int reservationInfoId) {
		Map<String, Object> params = new HashMap<>();
		params.put("reservationEmail", reservationEmail);
		params.put("productId", productId);
		params.put("reservationInfoId", reservationInfoId);
		return jdbc.queryForObject(ReservationDaoSqls.SELECT_TOTAL_PRICE, params, Integer.class);
	}
}