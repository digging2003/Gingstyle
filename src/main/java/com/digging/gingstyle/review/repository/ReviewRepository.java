package com.digging.gingstyle.review.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digging.gingstyle.review.domain.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

	public List<Review> findByProductId(int productId);
}
