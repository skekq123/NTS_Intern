package com.nts.reservation.imagefile.dao;

import java.util.Collections;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.imagefile.dto.ImageFileDto;

@Repository
public class ImageFileDao {

	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<ImageFileDto> rowMapper = BeanPropertyRowMapper.newInstance(ImageFileDto.class);

	public ImageFileDao(DataSource dataSource) {

		this.jdbc = new NamedParameterJdbcTemplate(dataSource);

	}

	public ImageFileDto selectImageFile(int imageFileId) {
		return jdbc.queryForObject(ImageFileDaoSqls.SELECT_PRODUCT_PAGE,
				Collections.singletonMap("imageFileId", imageFileId), rowMapper);
	}
}
