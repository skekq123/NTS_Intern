package com.nts.reservation.displayInfo.dao;

import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.displayInfo.dto.DisplayInfo;
import com.nts.reservation.displayInfo.dto.DisplayInfoImage;

@Repository
public class DisplayInfoDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<DisplayInfoImage> rowMapperDisplayInfoImage = BeanPropertyRowMapper
			.newInstance(DisplayInfoImage.class);
	private RowMapper<DisplayInfo> rowMapperDisplayInfo = BeanPropertyRowMapper.newInstance(DisplayInfo.class);

	public DisplayInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public DisplayInfo selectDisplayInfo(int displayInfoId) {
		return jdbc.queryForObject(DisplayInfoSqls.SELECT_DISPLAY_INFO,
				Collections.singletonMap("displayInfoId", displayInfoId), rowMapperDisplayInfo);
	}

	public DisplayInfoImage selectDisplayInfoImage(int displayInfoId) {
		return jdbc.queryForObject(DisplayInfoSqls.SELECT_DISPLAY_INFO_IMAGE,
				Collections.singletonMap("displayInfoId", displayInfoId), rowMapperDisplayInfoImage);
	}

	public double selectAverageScore(int displayInfoId) {
		return jdbc.queryForObject(DisplayInfoSqls.SELECT_AVERAGE_SCORE,
				Collections.singletonMap("displayInfoId", displayInfoId), Double.class);
	}
}
