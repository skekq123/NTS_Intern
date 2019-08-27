package com.nts.reservation.displayInfo.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DisplayInfoImage {
	private String contentType;
	private String createDate;
	private boolean deleteFlag;
	private int displayInfoId;
	private int displayInfoImageId;
	private int imageFileId;
	private String fileName;
	private String modifyDate;
	private MultipartFile imageFile;
}
