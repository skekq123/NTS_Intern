package com.nts.reservation.product.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.product.dao.ProductDao;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductPrice;
import com.nts.reservation.product.dto.ProductResponse;

@Service
public class ProductServiceImpl implements ProductService {
	@Autowired
	private ProductDao productDao;

	@Override
	public ProductResponse getProducts(int start, int categoryId, int totalCount) {
		ProductResponse productResponse = new ProductResponse();
		if (isTotalCategory(categoryId)) {
			productResponse.setItems(productDao.selectPagingProducts(start, totalCount));
			productResponse.setTotalCount(productDao.selectCount());
		} else {
			productResponse.setItems(productDao.selectPagingProductsByCategory(categoryId, start, totalCount));
			productResponse.setTotalCount(productDao.selectCountByCategory(categoryId));
		}
		return productResponse;
	}

	private boolean isTotalCategory(int categoryId) {
		return categoryId == 0;
	}

	@Override
	public List<ProductImage> getProductImages(int displayInfoId) {
		return productDao.selectProductImages(displayInfoId);
	}

	@Override
	public List<ProductPrice> getProductPrices(int displayInfoId) {
		return productDao.selectProductPrices(displayInfoId);
	}
}
