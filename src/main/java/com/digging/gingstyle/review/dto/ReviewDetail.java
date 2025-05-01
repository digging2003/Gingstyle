package com.digging.gingstyle.review.dto;

import lombok.Builder;
import lombok.Getter;

@Builder(toBuilder=true)
@Getter
public class ReviewDetail {

	private int userId;
	private String userName;
	private int productId;
	private String productName;
	private int rating; 
	private String imagePath;
	private String comment;
}
