package com.nts.reservation.product.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.product.dto.ProductResponse;
import com.nts.reservation.product.service.ProductService;

@RestController
@RequestMapping("/api/product")
public class ProductApiController {

	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ProductResponse products(
			@RequestParam(name = "start", required = false, defaultValue = "0") int start,
			@RequestParam(name = "categoryId", required = false, defaultValue = "0") int categoryId,
			@RequestParam(name = "requestProductCounts", required = false, defaultValue = "4") int requestProductCounts) {
		return productService.getProducts(start, categoryId, requestProductCounts);
	}
	
}
