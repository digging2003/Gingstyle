package com.digging.gingstyle.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digging.gingstyle.order.domain.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {

}
