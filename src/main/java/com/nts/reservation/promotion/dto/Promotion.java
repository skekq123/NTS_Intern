package com.nts.reservation.promotion.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Promotion {
	private int id;
	private int productId;
	private int imageFileId;
	private MultipartFile imageFile;
}
