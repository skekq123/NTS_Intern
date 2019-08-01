package com.nts.reservation.promotion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.promotion.dto.Promotion;
import com.nts.reservation.promotion.service.PromotionService;

@RestController
@RequestMapping("/api/promotion")
public class PromotionApiController {

	@Autowired
	private PromotionService promotionService;

	@GetMapping
	public List<Promotion> getPromotion() {
		return promotionService.getPromotions();
	}
}
