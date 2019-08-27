package com.nts.reservation.product.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {
	private int id;
	private int displayInfoId;
	private String placeName;
	private String content;
	private String description;
	private MultipartFile imageFile;
	private int imageFileId;	
}
