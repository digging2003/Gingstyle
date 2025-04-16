package com.digging.gingstyle.cart.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.digging.gingstyle.cart.domain.Cart;
import com.digging.gingstyle.cart.dto.CartView;
import com.digging.gingstyle.cart.repository.CartRepository;
import com.digging.gingstyle.common.FileManager;
import com.digging.gingstyle.products.domain.Image;
import com.digging.gingstyle.products.domain.Product;
import com.digging.gingstyle.products.repository.ImageRepository;
import com.digging.gingstyle.products.repository.ProductRepository;

import jakarta.persistence.PersistenceException;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor // final이 부여된 멤버변수만
@Service
public class CartService {
	private final CartRepository cartRepository;
	private final ProductRepository productRepository;
	private final ImageRepository imageRepository;

	// 사용자 관련 - 장바구니 추가 API
	public boolean addCart(
			int userId
			, int productId) {
		
		if(cartRepository.findByUserIdAndProductId(userId, productId) != null) { // 이미 있을 경우 수량만 올리기
			
			Cart cart = cartRepository.findByUserIdAndProductId(userId, productId);
			int quantity = cart.getQuantity();
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
	
	// 사용자 관련 - 장바구니 수량 -1
	public boolean subtractCart(
			int userId
			, int productId) {
		
		Cart cart = cartRepository.findByUserIdAndProductId(userId, productId);
		int quantity = cart.getQuantity();
		cart = cart.toBuilder()
				.quantity(quantity - 1)
				.build();
		
		try {
			cartRepository.save(cart);
		} catch(PersistenceException e) {
			return false;
		}
		
		return true;
		
		
	}
	
	// 사용자 관련 - 장바구니 상품 삭제
	public boolean deleteCart(int userId, int productId) {
		
		Cart cart = cartRepository.findByUserIdAndProductId(userId, productId);
		
		try {
			cartRepository.deleteById(cart.getId());
		} catch(PersistenceException e) {
			return false;
		}
		
		return true;
		
	}
	
	// 사용자 관련 - (session)userId로 장바구니 CartView 조회
	public List<CartView> getCartList(int userId) {
			
		// userId로 장바구니에 담긴 상품 리스트 조회
		List<Cart> cartList = cartRepository.findByUserId(userId);
		
		List<CartView> cartViewList = new ArrayList<>();
		
		for(Cart cart:cartList) {
			
			int productId = cart.getProductId();
			Optional<Product> optionalProduct = productRepository.findById(productId);
			Product product = optionalProduct.get();
			String mainImagePath = imageRepository.findTopByProductId(productId).getImagePath();
			int price = product.getPrice();
			int quantity = cart.getQuantity();
			int totalPrice = price * quantity;
			
			CartView cartView = CartView.builder()
					.userId(userId)
					.productId(productId)
					.mainImagePath(mainImagePath)
					.name(product.getName())
					.quantity(quantity)
					.price(price)
					.totalPrice(totalPrice)
					.build();
			
			cartViewList.add(cartView);
		}
		
		return cartViewList;
		
	}
	
	

}
