package com.digging.gingstyle.cart;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digging.gingstyle.cart.service.CartService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 부여된 멤버변수만
@RequestMapping("/cart")
@RestController
public class CartRestController {
	private final CartService cartService;
	
	// 사용자 관련 - 장바구니 추가 API
	@PostMapping("/add")
	public Map<String, String> addCart(
			@RequestParam int productId
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(cartService.addCart(userId, productId)) {
			// 성공
			resultMap.put("result", "success");
		} else {
			// 실패
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	// 사용자 관련 - 장바구니 수량 -1
	@PutMapping("/subtract")
	public Map<String, String> subtractCart(
			@RequestParam int productId
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(cartService.subtractCart(userId, productId)) {
			// 성공
			resultMap.put("result", "success");
		} else {
			// 실패
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	// 사용자 관련 - 장바구니 상품 삭제
	@DeleteMapping("/delete")
	public Map<String, String> deleteCart(
			@RequestParam int productId
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(cartService.deleteCart(userId, productId)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	
}
