package com.digging.gingstyle.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/admin")
@Controller
public class AdminController {

	// 대시보드
	@GetMapping
	public String dashboard() {
		return "admin/dashboard";
	}
	
	// 상품 관리
	@GetMapping("/product")
	public String product() {
		return "admin/product";
	}
	
	// 주문 관리
	@GetMapping("/order")
	public String order() {
		return "admin/order";
	}
	
	
}
