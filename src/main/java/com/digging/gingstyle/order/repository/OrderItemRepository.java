package com.digging.gingstyle.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digging.gingstyle.order.domain.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Integer> {

	public List<OrderItem> findByOrderId(int orderId);
}
