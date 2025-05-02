package com.digging.gingstyle.order;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.digging.gingstyle.cart.dto.CartView;
import com.digging.gingstyle.order.dto.OrderView;
import com.digging.gingstyle.order.service.OrderService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 부여된 멤버변수만
@RequestMapping("/order")
@Controller
public class OrderController {
	private final OrderService orderService;

	// 사용자 관련 - 주문 진행 화면
	@PostMapping
	public String orderView(
			@RequestParam("cartId") List<Integer> cartIdList
			, Model model
			, HttpSession session) {
		
		int userId = (Integer) session.getAttribute("userId");
		
		List<CartView> cartViewList = orderService.getOrderItemViewList(cartIdList, userId);
		
		model.addAttribute("cartViewList", cartViewList);
		
		return "user/order";
	}
	
	// 사용자 관련 - 주문 내역 화면
	@PostMapping("/history")
	public String orderHistoryView(
			HttpSession session
			, Model model) {
		
		int userId = (Integer) session.getAttribute("userId");
		
		List<OrderView> orderViewList = orderService.getOrderView(userId);
		
		model.addAttribute("orderViewList", orderViewList);
		
		return "user/orderHistory";
	}
	
}
