package com.digging.gingstyle.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.digging.gingstyle.order.domain.Order;
import com.digging.gingstyle.order.domain.OrderItem;
import com.digging.gingstyle.order.dto.OrderItemView;
import com.digging.gingstyle.order.dto.OrderView;
import com.digging.gingstyle.order.repository.OrderRepository;
import com.digging.gingstyle.products.dto.Detail;
import com.digging.gingstyle.products.service.ProductService;

import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 부여된 멤버변수만
@Service
public class OrderService {
	private final OrderRepository orderRepository;
	private final ProductService productService;

	// 사용자 관련 - 주문 등록 API
	public boolean addOrder(int userId, Order order) {
		
		order.setUserId(userId); // 사용자 Id로 null값 세팅
		
		try {
			for(OrderItem item:order.getOrderItems()) {
				order.addOrderItem(item);
			}
			orderRepository.save(order);
		} catch(PersistenceException e) {
			return false;
		}
		
		return true;
	}
	
	// 사용자 관련 - 주문 취소 요청 API
	
	// 사용자 관련 - 주문 내역 화면
	public List<OrderView> getOrderView(int userId) {
		
		List<Order> orderList = orderRepository.findByUserId(userId);
		List<OrderView> orderViewList = new ArrayList<>();
		
		for(Order order:orderList) {
			
			List<OrderItem> orderItems = order.getOrderItems();
			List<OrderItemView> orderItemViewList = new ArrayList<>();
			
			for(OrderItem orderItem:orderItems) {
				Detail detail = productService.getDetailView(orderItem.getProductId());
				
				OrderItemView orderItemView = OrderItemView.builder()
						.productId(orderItem.getProductId())
						.quantity(orderItem.getQuantity())
						.name(detail.getName())
						.price(detail.getPrice())
						.mainImagePath(detail.getMainImagePath())
						.build();
				
				orderItemViewList.add(orderItemView);
			}
			
			OrderView orderView = OrderView.builder()
					.totalPrice(order.getTotalPrice())
					.address(order.getAddress())
					.status(order.getStatus())
					.payment(order.getPayment())
					.orderItems(orderItemViewList)
					.build();
			
			orderViewList.add(orderView);
		}
		
		return orderViewList;
	}
	
	
	
}
