package com.digging.gingstyle.review;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.digging.gingstyle.review.domain.Review;
import com.digging.gingstyle.review.service.ReviewService;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 부여된 멤버변수만
@RequestMapping("/review")
@Controller
public class ReviewController {
	private final ReviewService reviewService;

	// 사용자 관련 - 상품별 리뷰 조회
	@GetMapping
	public String getReviewListByProductId(
			@RequestParam int productId
			, Model model) {
		
		List<Review> reviewList = reviewService.getReviewListByProductId(productId);
		
		model.addAttribute("reviewList", reviewList);
		
		return "product/review";
	}
	
	// 사용자 관련 - userId별 리뷰 조회 (이 페이지에서 수정,삭제 가능)
	@GetMapping("/user")
	public String getReviewListByUserId(
			HttpSession session
			, Model model) {
		
		int userId = (Integer)session.getAttribute("userId");
		
		List<Review> reviewList = reviewService.getReviewListByUserId(userId);
		
		model.addAttribute("reviewList", reviewList);
		
		return "user/review";
	}
}
