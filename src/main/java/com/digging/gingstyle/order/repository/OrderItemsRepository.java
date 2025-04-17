package com.digging.gingstyle.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digging.gingstyle.order.domain.OrderItems;

public interface OrderItemsRepository extends JpaRepository<OrderItems, Integer> {

	public List<OrderItems> findByOrderId(int orderId);
}
