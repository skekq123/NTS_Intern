package com.nts.reservation.product.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
	private long id;
	private long displayInfoId;
	private String placeName;
	private String content;
	private String description;
	private String imageUrl;
}
