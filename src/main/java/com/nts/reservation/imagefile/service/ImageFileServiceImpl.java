package com.nts.reservation.imagefile.service;

import org.springframework.stereotype.Service;

import com.nts.reservation.imagefile.dao.ImageFileDao;
import com.nts.reservation.imagefile.dto.ImageFileDto;

@Service
public class ImageFileServiceImpl implements ImageFileService {
	private ImageFileDao imageFileDao;

	public ImageFileServiceImpl(ImageFileDao imageFileDao) {
		this.imageFileDao = imageFileDao;
	}

	@Override
	public ImageFileDto getImageFile(int imageFileId) {
		return imageFileDao.selectImageFile(imageFileId);
	}
}
