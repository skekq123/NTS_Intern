package com.nts.reservation.product.service;

import com.nts.reservation.product.dto.ProductResponse;

public interface ProductService {
	ProductResponse getProducts(int start, int totalCount);
	ProductResponse getProducts(int categoryId, int start, int totalCount);
}
