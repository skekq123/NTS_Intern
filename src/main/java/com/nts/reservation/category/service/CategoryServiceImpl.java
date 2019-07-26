package com.nts.reservation.category.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.category.dao.CategoryDao;
import com.nts.reservation.category.dto.Category;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryDao categoryDao;

	@Override
	public List<Category> getCategories() {
		//ObjectMapper objMapper = new ObjectMapper();
		//objMapper.writeValueAsString(value)
		return categoryDao.selectAll();
	}
}
