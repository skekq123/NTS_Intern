package com.nts.reservation.product.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductImage {
	private String contentType;
	private String createDate;
	private boolean deleteFlag;
	private Integer imageFileId;
	private String fileName;
	private String modifyDate;
	private Integer productId;
	private Integer productImageId;
	private MultipartFile imageFile;
	private String type;
}
