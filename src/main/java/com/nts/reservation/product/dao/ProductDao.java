package com.nts.reservation.product.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.product.dto.Product;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbcUsingParameter;
	private JdbcTemplate jdbc;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	public ProductDao(DataSource dataSource) {
		this.jdbcUsingParameter = new NamedParameterJdbcTemplate(dataSource);
		this.jdbc = new JdbcTemplate(dataSource);
	}

	public List<Product> selectPagingProducts(int start, int limit) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("start", start);
		params.put("limit", limit);
		
		return jdbcUsingParameter.query(ProductDaoSqls.SELECT_PRODUCT_PAGE, params, rowMapper);
	}

	public List<Product> selectPagingProductsByCategory(int categoryId, int start, int limit) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("limit", limit);

		return jdbcUsingParameter.query(ProductDaoSqls.SELECT_PRODUCT_PAGE_BY_CATEGORY, params, rowMapper);
	}

	public int selectCount() {
		return jdbc.queryForObject(ProductDaoSqls.SELECT_PRODUCT_COUNT, Integer.class);
	}

	public int selectCountByCategory(int categoryId) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("categoryId", categoryId);
		return jdbcUsingParameter.queryForObject(ProductDaoSqls.SELECT_PRODUCT_COUNT_BY_CATEGORY, params, Integer.class);
	}
}
