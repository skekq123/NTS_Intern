package com.nts.reservation.product.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.product.dao.ProductDao;
import com.nts.reservation.product.dto.ProductResponse;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	@Override
	public ProductResponse getProducts(int start, int totalCount) {
		ProductResponse productResponse = new ProductResponse();
		productResponse.setItems(productDao.selectPagingProducts(start, totalCount));
		productResponse.setTotalCount(productDao.selectCount());
		return productResponse;
	}

	@Override
	public ProductResponse getProducts(int start, int categoryId, int totalCount) {
		ProductResponse productResponse = new ProductResponse();
		if (isTotalCategory(categoryId)) {
			productResponse = getProducts(start, totalCount);
		} else {
			productResponse.setItems(productDao.selectPagingProductsByCategory(categoryId, start, totalCount));
			productResponse.setTotalCount(productDao.selectCountByCategory(categoryId));
		}
		return productResponse;
	}

	private boolean isTotalCategory(int categoryId) {
		return categoryId == 0;
	}
}
