package com.nts.reservation.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPrice {
	private String createDate;
	private double discountRate;
	private String modifyDate;
	private Integer price;
	private String priceTypeName;
	private Integer productId;
	private Integer productPriceId;
}
