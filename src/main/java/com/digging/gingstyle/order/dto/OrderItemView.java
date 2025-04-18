package com.digging.gingstyle.order.dto;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderItemView {
	
	// 주문하는 페이지에 필요한 정보
	// front로부터 전달받을 값
	private int productId;
	private int quantity;
	
	// db에서 꺼내올 값
	private String name;
	private int price;
	private String mainImagePath;
}
