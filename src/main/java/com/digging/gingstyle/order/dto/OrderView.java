package com.digging.gingstyle.order.dto;

import java.util.List;

import com.digging.gingstyle.order.domain.Order;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class OrderView {
	
	// 주문내역 페이지에 필요한 정보
	
	private int totalPrice;
	private String address;
	private String status;
	private String payment;
	private List<OrderItemView> orderItems;
	
}
