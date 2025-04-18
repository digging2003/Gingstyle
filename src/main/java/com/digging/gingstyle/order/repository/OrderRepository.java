package com.digging.gingstyle.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digging.gingstyle.order.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

	public List<Order> findByUserId(int userId);
}
