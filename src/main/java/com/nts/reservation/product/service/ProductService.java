package com.nts.reservation.product.service;

import com.nts.reservation.product.dto.ProductResponse;

public interface ProductService {
	ProductResponse getProducts(int start, int count);
	ProductResponse getProducts(int categoryId, int start, int count);
}
