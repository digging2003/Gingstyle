package com.digging.gingstyle.products.dto;

import java.util.List;

import com.digging.gingstyle.products.domain.Image;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class Detail {

	// 카테고리 페이지에 필요한 정보
	private String name;
	private int beforePrice; 
	private int price;
	private List<Image> imageList;
	private int categoryId;
	
	// 상품 상세페이지에 필요한 정보
	private String discription;
	private int stock;
	
	
	
}
