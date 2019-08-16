package com.nts.reservation.displayInfo.dto;

import java.util.List;

import com.nts.reservation.comment.dto.Comment;
import com.nts.reservation.product.dto.ProductImage;
import com.nts.reservation.product.dto.ProductPrice;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DisplayInfoResponse {
	private DisplayInfoImage displayInfoImage;
	private List<ProductImage> productImages;
	private DisplayInfo displayInfo;
	private List<Comment> Comments;
	private double averageScore;
	private List<ProductPrice> productPrices;
}
