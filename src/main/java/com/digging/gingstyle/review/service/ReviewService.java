package com.digging.gingstyle.review.service;

import java.util.List;
import java.util.Optional;

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
	public boolean updateReview(
			int id
			, int rating
			, String comment
			, MultipartFile imageFile) {
		
		// 기존 리뷰 가져오기
		Optional<Review> optionalReview = reviewRepository.findById(id);
		Review review = optionalReview.get();
		FileManager.removeFile(review.getImagePath()); // 기존 리뷰사진 지우기
		
		String imagePath = null;
		
		// 새로운 사진 저장
		if(imageFile != null) {
			imagePath = FileManager.saveFile(imageFile);
		}
		
		review = review.toBuilder()
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
	
	// 사용자 관련 - 리뷰 삭제
	public boolean deleteReview(int id) {
		Optional<Review> optionalReview = reviewRepository.findById(id);
		
		if(optionalReview.isPresent()) {
			Review review = optionalReview.get();
			try {
				reviewRepository.deleteById(id);
				FileManager.removeFile(review.getImagePath()); // 기존 리뷰사진 지우기

			} catch(PersistenceException e) {
				return false;
			}
			
		} else {
			return false;
		}
		
		return true;
	}
	
	// 사용자 관련 - 상품별 리뷰 조회
	public List<Review> getReviewListByProductId(
			int productId) {
		
		List<Review> reviewList = reviewRepository.findByProductId(productId);
		
		return reviewList;
		
	}
	
	// 사용자 관련 - userId별 리뷰 조회
	public List<Review> getReviewListByUserId(
			int userId) {
		
		List<Review> reviewList = reviewRepository.findByUserId(userId);
		
		return reviewList;
		
	}
}
