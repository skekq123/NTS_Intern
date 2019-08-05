package com.nts.reservation.displayInfo.dao;

import java.util.HashMap;
import java.util.Map;

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
	private RowMapper<DisplayInfoImage> rowMapperDisplayInfoImage = BeanPropertyRowMapper.newInstance(DisplayInfoImage.class);
	private RowMapper<DisplayInfo> rowMapperDisplayInfo = BeanPropertyRowMapper.newInstance(DisplayInfo.class);
	
	public DisplayInfoDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}
	public DisplayInfo selectDisplayInfoByDisplayInfoId(int displayInfoId) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.queryForObject(DisplayInfoSqls.SELECT_DISPLAY_INFO, params, rowMapperDisplayInfo);
	}
	public DisplayInfoImage selectDisplayInfoImageByDisplayInfoId(int displayInfoId) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("displayInfoId", displayInfoId);
		return jdbc.queryForObject(DisplayInfoSqls.SELECT_DISPLAY_INFO_IMAGE, params, rowMapperDisplayInfoImage);
	}

}
