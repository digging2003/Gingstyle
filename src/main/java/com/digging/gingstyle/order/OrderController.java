package com.digging.gingstyle.order;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.digging.gingstyle.cart.dto.CartView;
import com.digging.gingstyle.order.service.OrderService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 부여된 멤버변수만
@RequestMapping("/order")
@Controller
public class OrderController {
	private final OrderService orderService;

	// 사용자 관련 - 주문 화면
	@GetMapping
	public String cart(
			HttpSession session
			, Model model) {
		
		int userId = (Integer) session.getAttribute("userId");
		
		List<CartView> cartView = cartService.getCartList(userId);
		
		model.addAttribute("cartView", cartView);
		
		return "user/cart";
	}
	
}
