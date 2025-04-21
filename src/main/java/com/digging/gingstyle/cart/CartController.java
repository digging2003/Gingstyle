package com.digging.gingstyle.cart;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.digging.gingstyle.cart.dto.CartView;
import com.digging.gingstyle.cart.service.CartService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 부여된 멤버변수만
@RequestMapping("/cart")
@Controller
public class CartController {
	private final CartService cartService;

	// 사용자 관련 - 장바구니 조회 API
	@GetMapping
	public String cart(
			HttpSession session
			, Model model) {
		
		if(session.getAttribute("userId") == null) {
			
			return "user/login"; // 비회원일 경우 로그인 페이지로 이동
		}
		
		int userId = (Integer) session.getAttribute("userId");
		
		List<CartView> cartView = cartService.getCartList(userId);
		
		if(cartView.isEmpty()) {
			model.addAttribute("cartView", "nothing");
		} else {
			model.addAttribute("cartView", cartView);
		}
		
		return "user/cart";
	}
}
