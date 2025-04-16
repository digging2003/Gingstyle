package com.digging.gingstyle.review;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.digging.gingstyle.review.domain.Review;
import com.digging.gingstyle.review.service.ReviewService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 부여된 멤버변수만
@RequestMapping("/review")
@Controller
public class ReviewController {
	private final ReviewService reviewService;

	// 사용자 관련 - 리뷰 조회 API
	@GetMapping
	public String review(
			@RequestParam int productId
			, Model model) {
		
		List<Review> reviewList = reviewService.getReviewList(productId);
		
		model.addAttribute("reviewList", reviewList);
		
		return "product/review";
	}
}
