package com.digging.gingstyle.review.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.digging.gingstyle.common.FileManager;
import com.digging.gingstyle.review.domain.Review;
import com.digging.gingstyle.review.repository.ReviewRepository;

import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 부여된 멤버변수만
@Service
public class ReviewService {
	private final ReviewRepository reviewRepository;
	
	// 사용자 관련 - 리뷰 추가 API
	public boolean addReview(
			int userId
			, int productId
			, int rating
			, String comment
			, MultipartFile imageFile) {
		
		String imagePath = null;
		
		if(imageFile != null) {
			imagePath = FileManager.saveFile(imageFile);
		}
		
		Review review = Review.builder()
				.userId(userId)
				.productId(productId)
				.rating(rating)
				.comment(comment)
				.imagePath(imagePath)
				.build();
		
		try {
			reviewRepository.save(review);
		} catch(PersistenceException e) {
			return false;
		}
		
		return true;
	}
	
	// 사용자 관련 - 리뷰 수정 API
	
	// 사용자 관련 - 리뷰 조회 API
	public List<Review> getReviewList(
			int productId) {
		
		List<Review> reviewList = reviewRepository.findByProductId(productId);
		
		return reviewList;
		
	}

}
