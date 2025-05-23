package com.digging.gingstyle.admin;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.digging.gingstyle.products.dto.Detail;
import com.digging.gingstyle.products.service.ProductService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 부여된 멤버변수만
@RequestMapping("/admin")
@Controller
public class AdminController {
	
	private final ProductService productService;

	// 대시보드
	@GetMapping
	public String dashboard(Model model) {
		return "admin/dashboard";
	}
	
	// 상품 관리
	@GetMapping("/product")
	public String product(Model model) {
		List<Detail> detailList = productService.getCategoryView(null);
		
		model.addAttribute("detailList", detailList);
		
		return "admin/product";
	}
	
	// 상품 추가
	@GetMapping("/product/add")
	public String addProduct() {
		return "admin/product-add";
	}
	
	// 주문 관리
	@GetMapping("/order")
	public String order() {
		return "admin/order";
	}
	
	// 사용자 관련 - 주문 진행 화면
//	@PostMapping
//	public String orderView(
//			@RequestParam("cartId") List<Integer> cartIdList
//			, Model model
//			, HttpSession session) {
//		
//		int userId = (Integer) session.getAttribute("userId");
//		
//		List<CartView> cartViewList = orderService.getOrderItemViewList(cartIdList, userId);
//		
//		model.addAttribute("cartViewList", cartViewList);
//		
//		return "user/order";
//	}
	
}
