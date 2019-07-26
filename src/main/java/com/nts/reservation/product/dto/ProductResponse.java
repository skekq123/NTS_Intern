package com.nts.reservation.product.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductResponse {
	private List<Product> items;
	private int totalCount;
}
