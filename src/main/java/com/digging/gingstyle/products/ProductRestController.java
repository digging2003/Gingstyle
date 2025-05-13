package com.digging.gingstyle.products;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.digging.gingstyle.products.service.ProductService;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor // final이 부여된 멤버변수만
@RequestMapping("/admin")
@RestController
public class ProductRestController {
	
	private final ProductService productService;
	
	// 관리자 관련 - 제품 등록 API
	@PostMapping("/product/create")
	public Map<String, String> createProduct(
	    @RequestParam String name,
	    @RequestParam String description,
	    @RequestParam(required = false, defaultValue = "0") int beforePrice,
	    @RequestParam int price,
	    @RequestParam int stock,
	    @RequestParam int categoryId,
	    @RequestParam MultipartFile[] productImages) {
		
		Map<String, String> resultMap = new HashMap<>();
		
	    int productId = productService.addProduct(name, description, beforePrice, price, stock, categoryId);
		if(productService.addImages(productId, productImages) ) {
			// 성공
			resultMap.put("result", "success");
		} else {
			// 실패
			resultMap.put("result", "fail");
		}
		
		return resultMap;
		
	}
	
	
	// 관리자 관련 - 제품 수정 API
	@PutMapping("/product/update")
	public Map<String, String> updatePost(
			@RequestParam int id
			, @RequestParam String name
			, @RequestParam String description
			, @RequestParam(required = false, defaultValue = "0") int beforePrice
			, @RequestParam int price
			, @RequestParam int stock
			, @RequestParam int categoryId) {
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(productService.updateProduct(id, name, description, beforePrice, price, stock, categoryId)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	// 관리자 관련 - 제품 삭제 API
	@DeleteMapping("/product/delete")
	public Map<String, String> deletePost(
			@RequestParam int id) {
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(productService.deleteProduct(id)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	// 관리자 관련 - 제품 사진 개별 삭제 API
	@DeleteMapping("/product/delete/image")
	public Map<String, String> deleteImage(
			@RequestParam int id) {
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(productService.deleteImage(id)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
}
