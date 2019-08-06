package com.nts.reservation.product.dao;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.nts.reservation.product.dto.Product;
import com.nts.reservation.product.dto.ProductImage;

@Repository
public class ProductDao {
	private NamedParameterJdbcTemplate jdbc;
	private RowMapper<Product> rowMapper = BeanPropertyRowMapper.newInstance(Product.class);
	private RowMapper<ProductImage> rowMapperProductImage = BeanPropertyRowMapper.newInstance(ProductImage.class);

	public ProductDao(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}

	public List<Product> selectPagingProducts(int start, int limit) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("start", start);
		params.put("limit", limit);

		return jdbc.query(ProductDaoSqls.SELECT_PRODUCT_PAGE, params, rowMapper);
	}

	public List<Product> selectPagingProductsByCategory(int categoryId, int start, int limit) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("categoryId", categoryId);
		params.put("start", start);
		params.put("limit", limit);

		return jdbc.query(ProductDaoSqls.SELECT_PRODUCT_PAGE_BY_CATEGORY, params, rowMapper);
	}

	public Integer selectCount() {
		return jdbc.queryForObject(ProductDaoSqls.SELECT_PRODUCT_COUNT, Collections.<String, Integer>emptyMap(),
				Integer.class);
	}

	public int selectCountByCategory(int categoryId) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("categoryId", categoryId);
		return jdbc.queryForObject(ProductDaoSqls.SELECT_PRODUCT_COUNT_BY_CATEGORY, params, Integer.class);
	}

	public List<ProductImage> selectProductImages(int displayInfoId) {
		Map<String, Integer> params = new HashMap<String, Integer>();
		params.put("displayInfoId", displayInfoId);

		return jdbc.query(ProductDaoSqls.SELECT_PROUDUCT_IMAGE, params, rowMapperProductImage);
	}
}
