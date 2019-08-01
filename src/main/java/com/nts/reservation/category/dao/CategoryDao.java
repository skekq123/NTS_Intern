package com.nts.reservation.category.dao;

import com.nts.reservation.category.dao.CategoryDaoSqls;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.nts.reservation.category.dto.Category;

@Repository
public class CategoryDao {
	private JdbcTemplate jdbc;
	private RowMapper<Category> rowMapper = BeanPropertyRowMapper.newInstance(Category.class);

	public CategoryDao(DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
	}

	public List<Category> selectAll() {
		return jdbc.query(CategoryDaoSqls.SELECT_ALL, rowMapper);
	}
}
