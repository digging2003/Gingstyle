package com.digging.gingstyle.order.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.digging.gingstyle.order.domain.OrderItems;
import com.digging.gingstyle.order.repository.OrderItemsRepository;

import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 부여된 멤버변수만
@Service
public class OrderItemsService {

	private final OrderItemsRepository orderItemsRepository;

	// 사용자 관련 - 주문 상품 등록 API
	public boolean addOrderItems(
			int orderId
			, int productId
			, int quantity) {
		
		OrderItems orderItems = OrderItems.builder()
				.orderId(orderId)
				.productId(productId)
				.quantity(quantity)
				.build();
		
		try {
			orderItemsRepository.save(orderItems);
		} catch(PersistenceException e) {
			return false;
		}
		
		return true;
		
	}
	
	// 사용자 관련 - orderId에 따른 조회
	public List<OrderItems> getOrderItems(int orderId) {
		return orderItemsRepository.findByOrderId(orderId);
	}
	
}
