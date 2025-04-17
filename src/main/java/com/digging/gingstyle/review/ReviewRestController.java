package com.digging.gingstyle.review;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.digging.gingstyle.review.service.ReviewService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 부여된 멤버변수만
@RequestMapping("/review")
@RestController
public class ReviewRestController {
	private final ReviewService reviewService;
	
	// 사용자 관련 - 리뷰 추가 API
	@PostMapping("/add")
	public Map<String, String> addReview(
			@RequestParam int productId
			, @RequestParam int rating
			, @RequestParam String comment
			, @RequestParam(required=false) MultipartFile imageFile
			, HttpSession session) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(reviewService.addReview(userId, productId, rating, comment, imageFile)) {
			// 성공
			resultMap.put("result", "success");
		} else {
			// 실패
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	// 사용자 관련 - 리뷰 수정 API
	@PutMapping("/update")
	public Map<String, String> updateReview(
			@RequestParam int id
			, @RequestParam int rating
			, @RequestParam String comment
			, @RequestParam(required=false) MultipartFile imageFile) {
		
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(reviewService.updateReview(id, rating, comment, imageFile)) {
			// 성공
			resultMap.put("result", "success");
		} else {
			// 실패
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
	// 사용자 관련 - 리뷰 삭제 API
	@DeleteMapping("/delete")
	public Map<String, String> deleteReview(@RequestParam int id) {
		
		Map<String, String> resultMap = new HashMap<>();
		
		if(reviewService.deleteReview(id)) {
			resultMap.put("result", "success");
		} else {
			resultMap.put("result", "fail");
		}
		
		return resultMap;
	}
	
}
