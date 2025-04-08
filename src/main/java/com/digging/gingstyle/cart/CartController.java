package com.digging.gingstyle.cart;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RequestMapping("/cart")
@Controller
public class CartController {

	// 사용자 관련 - 장바구니 조회 API
	@GetMapping
	public String cart() {
		
		return "products/cart";
	}
}
