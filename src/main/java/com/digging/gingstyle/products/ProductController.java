package com.digging.gingstyle.products;

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
@RequestMapping("/product")
@Controller
public class ProductController {
	
	private final ProductService productService;
	
	// 사용자 관련 - 카테고리별 상품리스트 조회 API
	@GetMapping
	public String getProducts(@RequestParam(required=false) Integer categoryId
			, Model model) {
		
		List<Detail> detailList = productService.getDetailView(categoryId);
		
		model.addAttribute("detailList", detailList);
		
		return "product/category";
	}
	
	// 사용자 관련 - 상품 상세페이지 조회 API
	@GetMapping("/detail")
	public String productDetail() {
		
		
		return "product/detail";
	}
	
	
}
