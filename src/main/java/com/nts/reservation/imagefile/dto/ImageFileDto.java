package com.nts.reservation.imagefile.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageFileDto {
	private int id;
	private String fileName;
	private String saveFileName;
	private String contentType;
}
