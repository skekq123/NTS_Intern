package com.nts.reservation.category.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.nts.reservation.category.dao.CategoryDao;
import com.nts.reservation.category.dto.CategoryResponse;

public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public CategoryResponse getCategories() {
		CategoryResponse categoryResponse = new CategoryResponse();
		categoryResponse.setItems(categoryDao.selectAll());

		return categoryResponse;
	}
}
