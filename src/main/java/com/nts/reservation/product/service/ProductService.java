package com.nts.reservation.product.service;

import java.util.List;

import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductResponse;

public interface ProductService {
	ProductResponse getProducts(int categoryId, int start, int totalCount);

	List<ProductImage> getProductImages(int displayInfoId);
}
