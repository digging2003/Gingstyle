package com.digging.gingstyle.cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digging.gingstyle.cart.domain.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {
	
	public Cart findByUserIdAndProductId(int userId, int productId);

}
