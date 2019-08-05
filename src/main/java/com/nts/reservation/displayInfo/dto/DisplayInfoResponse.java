package com.nts.reservation.displayInfo.dto;

import java.util.List;

import com.nts.reservation.product.dto.ProductImage;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DisplayInfoResponse {
	private DisplayInfoImage displayInfoImage;
	private List<ProductImage> productImages;
	private DisplayInfo displayInfo;
}
