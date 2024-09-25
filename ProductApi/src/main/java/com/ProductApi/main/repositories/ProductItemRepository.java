package com.ProductApi.main.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ProductApi.main.entity.ProductItem;

public interface ProductItemRepository extends JpaRepository<ProductItem, Integer>{
	ProductItem findByName(String name);
}
