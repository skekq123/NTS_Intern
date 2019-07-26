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
	public ProductResponse getProducts(int start, int count) {
		ProductResponse productResponse = new ProductResponse();

		productResponse.setItems(productDao.selectPagingProducts(start, count));
		productResponse.setCount(productDao.selectCount());
		return productResponse;
	}

	@Override
	public ProductResponse getProducts(int categoryId, int start, int count) {
		System.out.println("Service Product()");
		ProductResponse productResponse = new ProductResponse();

		if (isTotalCategory(categoryId)) {
			productResponse = getProducts(start, count);
		} else {
			productResponse.setItems(productDao.selectPagingProductsByCategory(categoryId, start, count));
			productResponse.setCount(productDao.selectCountByCategory(categoryId));
		}
		return productResponse;
	}

	private boolean isTotalCategory(int categoryId) {
		return categoryId == 0;
	}
}
