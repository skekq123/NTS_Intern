package com.nts.reservation.product.dao;

import static com.nts.reservation.product.dao.ProductDaoSqls.SELECT_PRODUCT_COUNT;
import static com.nts.reservation.product.dao.ProductDaoSqls.SELECT_PRODUCT_COUNT_BY_CATEGORY;
import static com.nts.reservation.product.dao.ProductDaoSqls.SELECT_PRODUCT_PAGE;
import static com.nts.reservation.product.dao.ProductDaoSqls.SELECT_PRODUCT_PAGE_BY_CATEGORY;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.nts.reservation.product.dto.Product;

public class ProductDao {
	private JdbcTemplate jdbc;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);

	public ProductDao(DataSource dataSource) {
		this.jdbc = new JdbcTemplate(dataSource);
	}

	public List<Product> selectPagingProducts(int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_PRODUCT_PAGE, rowMapper, params);
	}

	public List<Product> selectPagingProductsByCategory(int categoryId, int start, int limit) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("limit", limit);
		return jdbc.query(SELECT_PRODUCT_PAGE_BY_CATEGORY, rowMapper, params);
	}

	public int selectCount() {
		return jdbc.queryForObject(SELECT_PRODUCT_COUNT, Integer.class);
	}

	public int selectCountByCategory(int categoryId) {
		Map<String, Integer> params = new HashMap<>();
		params.put("categoryId", categoryId);
		return jdbc.queryForObject(SELECT_PRODUCT_COUNT_BY_CATEGORY, Integer.class, params);
	}
}
