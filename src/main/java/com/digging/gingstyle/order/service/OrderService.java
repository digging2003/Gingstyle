package com.digging.gingstyle.order.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.digging.gingstyle.cart.domain.Cart;
import com.digging.gingstyle.cart.dto.CartView;
import com.digging.gingstyle.cart.repository.CartRepository;
import com.digging.gingstyle.order.domain.Order;
import com.digging.gingstyle.order.domain.OrderItem;
import com.digging.gingstyle.order.dto.OrderItemView;
import com.digging.gingstyle.order.dto.OrderView;
import com.digging.gingstyle.order.repository.OrderRepository;
import com.digging.gingstyle.products.dto.Detail;
import com.digging.gingstyle.products.service.ProductService;
import com.digging.gingstyle.user.domain.User;
import com.digging.gingstyle.user.repository.UserRepository;

import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 부여된 멤버변수만
@Service
public class OrderService {
	private final OrderRepository orderRepository;
	private final ProductService productService;
	private final CartRepository cartRepository;
	private final UserRepository userRepository;

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
	
	// 사용자 관련 - 주문 진행 화면
	public List<CartView> getOrderItemViewList(List<Integer> cartIdList, int userId) {
		
		List<CartView> resultList = new ArrayList<>();
		List<Cart> cartList = new ArrayList<>();
		
		for(int id:cartIdList) {
			Cart cart = cartRepository.findById(id).get();
			cartList.add(cart);
		}
		
		for(Cart cart:cartList) {
			
			Detail detail = productService.getDetailView(cart.getProductId());
			int price = detail.getPrice();
			int quantity = cart.getQuantity();
			int totalPrice = price * quantity;
			
			CartView cartView = CartView.builder()
					.cartId(cart.getId())
					.userId(userId)
					.productId(cart.getProductId())
					.mainImagePath(detail.getMainImagePath())
					.name(detail.getName())
					.quantity(cart.getQuantity())
					.price(detail.getPrice())
					.totalPrice(totalPrice)
					.build();
			
			resultList.add(cartView);
		}
		
		return resultList;
	}
	
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
						.id(orderItem.getId())
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
					.orderItemViewList(orderItemViewList)
					.build();
			
			orderViewList.add(orderView);
		}
		
		return orderViewList;
	}
	
	
	
}
