package com.digging.gingstyle.order.service;

import org.springframework.stereotype.Service;

import com.digging.gingstyle.order.domain.Order;
import com.digging.gingstyle.order.repository.OrderRepository;

import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 부여된 멤버변수만
@Service
public class OrderService {
	private final OrderRepository orderRepository;

	// 사용자 관련 - 주문 등록 API
	public boolean addOrder(
			int userId
			, int totalPrice
			, String address
			, String payment) {
		
		Order order = Order.builder()
				.userId(userId)
				.totalPrice(totalPrice)
				.address(address)
				.status("pending") // 대기중
				.payment(payment)
				.build();
		
		try {
			orderRepository.save(order);
		} catch(PersistenceException e) {
			return false;
		}
		
		return true;
	}
	
}
