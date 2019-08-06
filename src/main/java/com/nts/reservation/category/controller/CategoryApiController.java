package com.nts.reservation.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.category.dto.Category;
import com.nts.reservation.category.service.CategoryService;

@RestController
@RequestMapping("/api/category")
public class CategoryApiController {

	@Autowired
	private CategoryService categoryService;

	@GetMapping
	public List<Category> getCategory() {
		return categoryService.getCategories();
	}

}
