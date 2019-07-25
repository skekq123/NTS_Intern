package com.nts.reservation.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.nts.reservation.category.dao.CategoryDao;
import com.nts.reservation.category.dto.Category;

public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<Category> getCategories() {
		return categoryDao.selectAll();
	}
}
