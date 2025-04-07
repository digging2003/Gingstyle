package com.digging.gingstyle.products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.digging.gingstyle.products.domain.Products;

public interface ProductsRepository extends JpaRepository<Products, Integer> {

}
