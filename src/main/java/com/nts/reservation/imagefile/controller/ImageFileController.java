package com.nts.reservation.imagefile.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.imagefile.dto.ImageFileDto;
import com.nts.reservation.imagefile.service.ImageFileService;

@RestController
@RequestMapping("/file")
public class ImageFileController {

	private static final String BASE_PATH = File.separator + "tmp" + File.separator;
	private ImageFileService imageFileService;
	
	public ImageFileController(ImageFileService imageFileService) {
		this.imageFileService = imageFileService;
	}
	
	@GetMapping(path = "/img/{imageFileId}", produces = "application/text; charset=utf-8")
	public void commentImageDownload(HttpServletResponse response, @PathVariable int imageFileId) throws IOException {
		ImageFileDto imageFile = imageFileService.getImageFile(imageFileId);

		response.setHeader("Content-Disposition", "attachment; filename=\"" + imageFile.getFileName() + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		response.setHeader("Content-Type", imageFile.getContentType());
		response.setHeader("Content-Length", "" + new File(BASE_PATH + imageFile.getSaveFileName()).length());
		response.setHeader("Pragma", "no-cache;");
		response.setHeader("Expries", "-1");

		FileInputStream fileInStream = new FileInputStream(BASE_PATH + imageFile.getSaveFileName());
		BufferedOutputStream outStream = new BufferedOutputStream(response.getOutputStream());

		FileCopyUtils.copy(fileInStream, outStream);
	}
}