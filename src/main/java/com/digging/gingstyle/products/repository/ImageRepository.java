package com.digging.gingstyle.products.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digging.gingstyle.products.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Integer> {

	public List<Image> findByProductId(int productId);
	
	// 대표 이미지 조회
	public Image findTopByProductId(int productId);
}
