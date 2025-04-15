package com.digging.gingstyle.cart.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.digging.gingstyle.cart.domain.Cart;
import com.digging.gingstyle.cart.repository.CartRepository;
import com.digging.gingstyle.products.domain.Product;

import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 부여된 멤버변수만
@Service
public class CartService {
	private final CartRepository cartRepository;

	// 사용자 관련 - 장바구니 추가 API
	public boolean addCart(
			int userId
			, int productId
			, int quantity) {
		
		if(cartRepository.findByUserIdAndProductId(userId, productId) != null) { // 이미 있을 경우 수량만 올리기
			
			Cart cart = cartRepository.findByUserIdAndProductId(userId, productId);
			cart = cart.toBuilder()
					.quantity(quantity + 1)
					.build();
			
			try {
				cartRepository.save(cart);
			} catch(PersistenceException e) {
				return false;
			}
			
			return true;
			
		} else {
			
			// 장바구니에 새로 추가
			Cart cart = Cart.builder()
					.userId(userId)
					.productId(productId)
					.quantity(1)
					.build();
			
			try {
				cartRepository.save(cart);
			} catch(PersistenceException e) {
				return false;
			}
			
			return true;
			
		}
	}
	

}
