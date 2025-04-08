package com.digging.gingstyle.products.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digging.gingstyle.products.domain.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

	public List<Product> findByCategoryId(int categoryId);
}
