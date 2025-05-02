package com.digging.gingstyle.cart.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class CartView {

	// 장바구니 페이지에 필요한 정보
	private int cartId;
	private int userId;
	private int productId;
	private String mainImagePath;
	private String name;
	private int quantity;
	private int price;
	private int totalPrice;

	
}
