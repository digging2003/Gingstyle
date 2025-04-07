package com.digging.gingstyle.products.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.digging.gingstyle.products.domain.Products;
import com.digging.gingstyle.products.repository.ProductsRepository;

import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 부여된 멤버변수만
@Service
public class ProductsService {
	private final ProductsRepository productsRepository;
	
	// 관리자 관련 - 제품 등록 API
	public boolean addProduct(
			String name
			, String discription
			, int beforePrice
			, int price
			, int stock
			, int categoryId
			, String imagePath) {
		
		Products products = Products.builder()
				.name(name)
				.discription(discription)
				.beforePrice(beforePrice)
				.price(price)
				.stock(stock)
				.categoryId(categoryId)
				.imagePath(imagePath)
				.build();
		
		try {
			productsRepository.save(products);
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
			, int categoryId
			, String imagePath) {
		Optional<Products> optionalProducts = productsRepository.findById(id);
		
		if(optionalProducts.isPresent()) {
			
			Products products = optionalProducts.get();
			
			products = products.toBuilder()
				.name(name)
				.discription(discription)
				.beforePrice(beforePrice)
				.price(price)
				.stock(stock)
				.categoryId(categoryId)
				.imagePath(imagePath)
				.build();
			
			try {
				productsRepository.save(products);
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
		Optional<Products> optionalProducts = productsRepository.findById(id);
		if(optionalProducts.isPresent()) {
			
			Products products = optionalProducts.get();
			
			try {
				productsRepository.deleteById(id);
			} catch(PersistenceException e) {
				return false;
			}
			
		} else {
			return false;
		}
		
		return true;
		
	}
}
