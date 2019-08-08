package com.nts.reservation.promotion.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nts.reservation.promotion.dto.Promotion;
import com.nts.reservation.promotion.service.PromotionService;

@RestController
@RequestMapping("/api/promotion")
public class PromotionApiController {
	private PromotionService promotionService;

	public PromotionApiController(PromotionService promotionService) {
		this.promotionService = promotionService;
	}

	@GetMapping
	public List<Promotion> getPromotion() {
		return promotionService.getPromotions();
	}
}
