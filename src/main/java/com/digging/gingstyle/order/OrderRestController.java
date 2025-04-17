package com.digging.gingstyle.order;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.digging.gingstyle.order.service.OrderService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 부여된 멤버변수만
@RequestMapping("/order")
@RestController
public class OrderRestController {
	private final OrderService orderService;
	
	// 사용자 관련 - 주문 등록 API
	@PostMapping("/add")
	public Map<String, String> addOrder(
			@RequestParam int totalPrice
			, @RequestParam String address
			, @RequestParam String payment
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(orderService.addOrder(userId, totalPrice, address, payment)) {
			// 성공
			resultMap.put("result", "success");
		} else {
			// 실패
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}

}
