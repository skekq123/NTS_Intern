package com.nts.reservation.promotion.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nts.reservation.promotion.dao.PromotionDao;
import com.nts.reservation.promotion.dto.Promotion;

@Service
public class PromotionServiceImpl implements PromotionService{
	@Autowired
	private PromotionDao promotionDao;
	@Override
	public List<Promotion> getPromotions() {
		return promotionDao.selectPagingCategories();
	}
}
