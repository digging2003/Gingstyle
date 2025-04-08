package com.digging.gingstyle.products.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.digging.gingstyle.products.domain.Image;
import com.digging.gingstyle.products.domain.Product;
import com.digging.gingstyle.products.repository.ImageRepository;
import com.digging.gingstyle.products.repository.ProductRepository;

import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 부여된 멤버변수만
@Service
public class ProductService {
	private final ProductRepository productRepository;
	
	private final ImageRepository imageRepository;
	
	// 사용자 관련 - 카테고리별 조회 API
	public List<Product> getProductList(int categoryId) {
		return productRepository.findByCategoryId(categoryId);
	}
	
	// 사용자 관련 - 상품 조회 API
	public Product getProduct(int id) {
		Optional<Product> optionalProduct = productRepository.findById(id);
		Product product = optionalProduct.get();
		
		return product;
	}
	
	// 관리자 관련 - 제품 이미지 등록 API
	public boolean addImage(
			int productId
			, String imagePath) {
		
		Image image = Image.builder()
				.productId(productId)
				.imagePath(imagePath)
				.build();
		
		try {
			imageRepository.save(image);
		} catch(PersistenceException e) {
			return false;
		}
		
		return true;
	}
	
	// 관리자 관련 - 제품 등록 API
	public boolean addProduct(
			String name
			, String discription
			, int beforePrice
			, int price
			, int stock
			, int categoryId) {
		
		Product product = Product.builder()
				.name(name)
				.discription(discription)
				.beforePrice(beforePrice)
				.price(price)
				.stock(stock)
				.categoryId(categoryId)
				.build();
		
		try {
			productRepository.save(product);
		} catch(PersistenceException e) {
			return false;
		}
		
		return true;
		
		
	}
	
	
	// 관리자 관련 - 제품 수정 API
	public boolean updateProduct(
			int id
			, String name
			, String discription
			, int beforePrice
			, int price
			, int stock
			, int categoryId) {
		Optional<Product> optionalProducts = productRepository.findById(id);
		
		if(optionalProducts.isPresent()) {
			
			Product products = optionalProducts.get();
			
			products = products.toBuilder()
				.name(name)
				.discription(discription)
				.beforePrice(beforePrice)
				.price(price)
				.stock(stock)
				.categoryId(categoryId)
				.build();
			
			try {
				productRepository.save(products);
			} catch(PersistenceException e) {
				return false;
			}
		} else {
			return false;
		}
		
		return true;
	}
	
	// 관리자 관련 - 제품 삭제 API
	public boolean deleteProduct(int id) {
		Optional<Product> optionalProducts = productRepository.findById(id);
		if(optionalProducts.isPresent()) {
			
			Product product = optionalProducts.get();
			
			try {
				productRepository.deleteById(id);
			} catch(PersistenceException e) {
				return false;
			}
			
		} else {
			return false;
		}
		
		return true;
		
	}
}
